package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static String readAsString(String fileName) {
        try (InputStream input = TestDataUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalStateException("Unable to find test data file: " + fileName);
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to read test data file: " + fileName, exception);
        }
    }
}
