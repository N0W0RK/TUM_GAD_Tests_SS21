package tests.simplesort;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class CustomAssertion extends Assertions {

    /**
     * Custom assert for multiple expected.
     * @param expected first expected
     * @param expected2 second expected
     * @param actual actual value to be tested
     */
    public static void assertEquals(int expected, int expected2, int actual) {
        if ((expected != actual) && (expected2 != actual)) {
            assertEquals(expected, actual, "Should be: " + expected + " or " + expected2 + " but was " + actual);
        }
    }

    /**
     * Custom assert for multiple expected.
     * @param expected first expected
     * @param expected2 second expected
     * @param expected3 third expected
     * @param actual actual value to be tested
     */
    public static void assertEquals(int expected, int expected2, int expected3, int actual) {
        if ((expected != actual) && (expected2 != actual) && (expected3 != actual)) {
            assertEquals(expected, actual, "Should be: " + expected + ", " + expected2 + " or " + expected3 + " but was " + actual);
        }
    }

    /**
     * Custom assert for multiple expected.
     * @param expected first expected
     * @param expected2 second expected
     * @param actual actual value to be tested
     */
    public static void assertArrayEquals(int[] expected, int[] expected2, int[] actual) {
        if (!Arrays.equals(expected, actual) && !Arrays.equals(expected2, actual)) {
            assertEquals(expected, actual, "Should be: " + Arrays.toString(expected) + " or " + Arrays.toString(expected2)
                    + " but was " + Arrays.toString(actual));
        }
    }

    /**
     * Custom assert for multiple expected.
     * @param expected first expected
     * @param expected2 second expected
     * @param expected3 third expected
     * @param actual actual value to be tested
     */
    public static void assertArrayEquals(int[] expected, int[] expected2, int[] expected3, int[] actual) {
        if (!Arrays.equals(expected, actual) && !Arrays.equals(expected2, actual) && !Arrays.equals(expected3, actual)) {
            assertEquals(expected, actual, "Should be: " + Arrays.toString(expected) + ", " + Arrays.toString(expected2)
                    + " or " + Arrays.toString(expected3) + " but was " + Arrays.toString(actual));
        }
    }

}
