package tests.radix;

import org.junit.jupiter.api.Assertions;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;

/**
 * Custom {@link org.junit.jupiter.api.Assertions#assertArrayEquals(int[], int[], String)} implementation to enable easier debugging.
 * @author Konrad G
 */
public class CustomAssertions extends Assertions {

    /**
     * See {@link CustomAssertions#assertArrayEquals(int[], int[], String)}.
     * @param expected Expected array
     * @param actual Actual array
     */
    public static void assertArrayEquals(int[] expected, int[] actual) {
        assertArrayEquals(expected, actual, (String) null);
    }

    /**
     * Custom {@link org.junit.jupiter.api.Assertions#assertArrayEquals(int[], int[], String)} implementation.
     * The output of {@code assertEquals()} is concatenated after the output of {@code assertArrayEquals()}.
     * @param expected Expected array
     * @param actual Actual array
     * @param message Message to be prepended in front of the error message
     */
    public static void assertArrayEquals(int[] expected, int[] actual, String message) {

        if (expected == actual) {
            return;
        }
        assertArraysNotNull(expected, actual, message);
        assertArraysHaveSameLength(expected.length, actual.length, message);

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) {
                failArraysNotEqual(expected, actual, i, message);
            }
        }
    }


    // All following methods mirror required methods from junit that are private.
    // This is quite ugly and we need a better solution for that.


    private static void failArraysNotEqual(int[] expected, int[] actual, int index,
                                           String pref) {

        String prefix = pref + ": ";
        String message = "array contents differ at index [" + index + "], " + formatValues(expected[index], actual[index]);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual), prefix + message);
    }

    private static void assertArraysNotNull(Object expected, Object actual, String message) {

        if (expected == null) {
            fail(buildPrefix(message) + "expected array was <null>");
        }
        if (actual == null) {
            fail(buildPrefix(message) + "actual array was <null>");
        }
    }

    private static void assertArraysHaveSameLength(int expected, int actual, String pref) {

        if (expected != actual) {
            String prefix = buildPrefix(pref);
            String message = "array lengths differ, expected: <" + expected
                    + "> but was: <" + actual + ">";
            fail(prefix + message);
        }
    }

    private static String buildPrefix(String message) {

        if (message == null) {
            return "";
        } else {
            return message + " ==> ";
        }
    }

    static String formatValues(Object expected, Object actual) {
        String expectedString = toString(expected);
        String actualString = toString(actual);
        return String.format("expected: <%s> but was: <%s>", expectedString, actualString);
    }

    private static String toString(Object obj) {
        return StringUtils.nullSafeToString(obj);
    }
}
