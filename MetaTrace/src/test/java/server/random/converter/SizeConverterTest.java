package server.random.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import server.random.dto.Size;

public class SizeConverterTest {

    @ParameterizedTest(name = "Should convert \"{0}\" to \"{1}\"")
    @CsvSource({
            "10, 10",
            "0123456789, 123456789",
            "-1, -1",
            "0, 0"})
    void testConvert(String size, String expected) {
        //Init
        SizeConverter sizeConverter = new SizeConverter();
        //Act
        Size m = sizeConverter.convert(expected);
        //Assert
        Assertions.assertThat(m.size().toString()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Should throw exception when message is \"{0}\"")
    @CsvSource({
            "45 4657",
            "s 1",
            "1 s",
            "one"})
    void testFailConvert(String size) {
        //Init
        SizeConverter sizeConverter = new SizeConverter();
        //Act
        //Assert
        Assertions.assertThatThrownBy(() -> sizeConverter.convert(size))
                .isInstanceOf(NumberFormatException.class);
    }

}
