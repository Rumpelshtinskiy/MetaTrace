import org.java_websocket.server.WebSocketServer;
import server.Server;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        WebSocketServer server = new Server(new InetSocketAddress("127.0.0.1", 3000));
        server.run();
    }
}
