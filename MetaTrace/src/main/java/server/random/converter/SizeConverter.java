package server.random.converter;

import server.random.dto.Size;

import java.math.BigInteger;

public class SizeConverter {
    public Size convert(String message) {
        return new Size(new BigInteger(message));
    }
}
