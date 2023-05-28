package server.random.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.CloseFrame;
import server.random.dto.Answer;

public class AnswerConverter {

    public String toResource(Answer answer) {
        try {
            return new ObjectMapper().writeValueAsString(answer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
