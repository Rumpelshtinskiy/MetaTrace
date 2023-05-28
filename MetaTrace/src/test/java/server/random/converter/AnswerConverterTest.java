package server.random.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import server.random.dto.Answer;

import java.math.BigInteger;

public class AnswerConverterTest {
    private static final String EXPECTED = "-4356";

    @Test
    void testConvert() {
        //Init
        AnswerConverter converter = new AnswerConverter();
        Answer answer = new Answer(new BigInteger(EXPECTED));
        //Act
        String ret = converter.toResource(answer);
        //Assert
        Assertions.assertThat(ret).isEqualTo("{\"randomBigInteger\":" + EXPECTED + "}");
    }
}
