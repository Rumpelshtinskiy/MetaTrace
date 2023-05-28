package server.random.validator;

import org.assertj.core.api.Assertions;
import org.java_websocket.exceptions.InvalidDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BigRandomValidatorTest {

    @Test
    @DisplayName("Should not throw exception")
    void testValidate() throws InvalidDataException {
        //Init
        BigRandomValidator bigRandomValidator = new BigRandomValidator();
        //Act
        bigRandomValidator.validate("3654748");
        //Assert
    }

    @ParameterizedTest(name = "Should throw exception when message is \"{0}\"")
    @CsvSource({
            "0123456789",
            "-1",
            "0",
            "45 4657",
            "s 1",
            "1 s",
            "one"})
    void shouldThrowException(String message) {
        //Init
        BigRandomValidator bigRandomValidator = new BigRandomValidator();
        //Act
        //Assert
        Assertions.assertThatThrownBy(() -> bigRandomValidator.validate(message))
                .isInstanceOf(InvalidDataException.class);
    }
}
