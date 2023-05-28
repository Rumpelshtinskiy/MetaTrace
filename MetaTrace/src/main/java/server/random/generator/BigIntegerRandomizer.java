package server.random.generator;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Random;

public class BigIntegerRandomizer implements Randomizer<Optional<BigInteger>>{

    private static final String NUMBERS = "0123456789";
    private static final int NUMBERS_LENGTH = NUMBERS.length();

    private final Random random = new Random();
    @Override
    public Optional<BigInteger> getRandom(BigInteger size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getSign());
        BigInteger sizeNumber = size.abs();
        while (!sizeNumber.equals(BigInteger.ZERO)) {
            int index = random.nextInt(NUMBERS_LENGTH);
            char randomChar = NUMBERS.charAt(index);

            stringBuilder.append(randomChar);
            sizeNumber = sizeNumber.subtract(BigInteger.ONE);
        }
        try {
            return Optional.of(new BigInteger(stringBuilder.toString()));
        }
        catch (NumberFormatException e){
            return Optional.empty();
        }
    }

    private String getSign() {
        return random.nextBoolean() ? "-" : "";
    }
}
