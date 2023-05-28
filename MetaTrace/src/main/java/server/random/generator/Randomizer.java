package server.random.generator;

import java.math.BigInteger;

public interface Randomizer<T> {
    T getRandom(BigInteger size);
}
