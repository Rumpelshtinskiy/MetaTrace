package server.service;

import model.Random;
import org.java_websocket.exceptions.InvalidDataException;
import orm.OrmUtils;
import server.random.dto.Size;
import server.random.generator.BigIntegerRandomizer;

import java.math.BigInteger;

public class RandomService {

    private final BigIntegerRandomizer randomizer = new BigIntegerRandomizer();
    public BigInteger getRandomNumber(Size size) throws InvalidDataException {
        BigInteger randomNumber;
        do {
             randomNumber = randomizer.getRandom(size.size())
                    .orElseThrow(() -> new InvalidDataException(500, "Internal server error. The number was not generated"));
        }
        while (OrmUtils.isExists(randomNumber.toString()));
        OrmUtils.save(new Random(randomNumber.toString()));
        return randomNumber;
    }
}
