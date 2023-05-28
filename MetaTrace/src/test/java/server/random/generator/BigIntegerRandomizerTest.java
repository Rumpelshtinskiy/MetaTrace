package server.random.generator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.Optional;

public class BigIntegerRandomizerTest {

    @ParameterizedTest(name = "Should generate random number with size \"{0}\"")
    @CsvSource({
            "10",
            "0123",
            "-11"})
    void testGenerate(String sizeString) {
        //Init
        BigInteger size = new BigInteger(sizeString);
        BigIntegerRandomizer bigIntegerRandomizer = new BigIntegerRandomizer();
        //Act
        Optional<BigInteger> random = bigIntegerRandomizer.getRandom(size);
        //Assert
        Assertions.assertThat(random).isPresent();
    }

    @Test
    @DisplayName("Should return empty optional when size is zero")
    void testFail() {
        //Init
        BigInteger size = new BigInteger("-0");
        BigIntegerRandomizer bigIntegerRandomizer = new BigIntegerRandomizer();
        //Act
        Optional<BigInteger> random = bigIntegerRandomizer.getRandom(size);
        //Assert
        Assertions.assertThat(random).isEmpty();
    }
}
