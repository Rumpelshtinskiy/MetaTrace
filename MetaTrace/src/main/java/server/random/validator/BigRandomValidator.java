package server.random.validator;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.CloseFrame;

import java.util.regex.Pattern;

public class BigRandomValidator {

    private static final String POSITIVE = "^[1-9]\\d*$";
    private static final Pattern POSITIVE_PATTERN = Pattern.compile(POSITIVE);

    public void validate(String message) throws InvalidDataException {
        if(!isPositiveNumber(message)) {
            throw new InvalidDataException(CloseFrame.POLICY_VALIDATION, "Size must be positive and not zero");
        }
    }

    private static boolean isPositiveNumber(String s) {
        return POSITIVE_PATTERN.matcher(s).matches();
    }
}
