package server;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;
import server.random.converter.AnswerConverter;
import server.random.converter.SizeConverter;
import server.random.dto.Answer;
import server.random.dto.Size;
import server.random.validator.BigRandomValidator;
import server.service.RandomService;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashSet;

public class Server extends WebSocketServer {

    private final HashSet<InetAddress> connectedClients = new HashSet<>();
    private final BigRandomValidator bigRandomValidator = new BigRandomValidator();
    private final SizeConverter sizeConverter = new SizeConverter();
    private final AnswerConverter answerConverter = new AnswerConverter();
    private final RandomService randomService = new RandomService();

    public Server(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server. Enter the size of the number you want to generate");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        try {
            bigRandomValidator.validate(message);
            Size size = sizeConverter.convert(message);
            BigInteger randomNumber = randomService.getRandomNumber(size);
            conn.send(answerConverter.toResource(new Answer(randomNumber)));
        } catch (InvalidDataException e) {
            conn.send(e.getMessage());
        }
    }

    @Override
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        if(!connectedClients.add(conn.getRemoteSocketAddress().getAddress())) {
            throw new InvalidDataException(CloseFrame.POLICY_VALIDATION, "Already connected");
        }
        return super.onWebsocketHandshakeReceivedAsServer(conn, draft, request);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        conn.send(ex.getMessage());
        if (conn != null) {
            connectedClients.remove(conn.getRemoteSocketAddress().getAddress());
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started at an address: " + getAddress().getHostString() + ":" + getAddress().getPort());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connectedClients.remove(conn.getRemoteSocketAddress().getAddress());
    }
}
