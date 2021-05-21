package tests.simplesort;

import gad.simplesort.PivotFinder;
import gad.simplesort.Quicksort;
import gad.simplesort.SilentResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

class QuicksortTest {

    @Nested
    @DisplayName("Repetitive Random Tests")
    class repetitiveRandomTests {
        /**
         * The concept is that you can extend the tests as much as you want, by simply adding
         * new Arguments.arguments in the corresponding Stream. Which place is which parameter
         * is stated in the header.
         */

        /**
         * LAST PIVOT: SELECTION_SORT_SIZE, SEED, ITERATIONS, MAX_LENGTH, MIN_LENGTH, NUMBERS
         */
        static Stream<Arguments> testCorrectSortingLast() {
            return Stream.of(
                    Arguments.arguments(1, 42, 100, 1, 0, 100),
                    Arguments.arguments(1, 43, 100, 10, 1, 100),
                    Arguments.arguments(2, 44, 100, 100, 1, 100),
                    Arguments.arguments(2, 45, 100, 1000, 1, 100),
                    Arguments.arguments(3, 46, 100, 69, 1, 100),
                    Arguments.arguments(10, 47, 100, 10000, 1, 100)
            );
        }

        /**
         * MID PIVOT: SELECTION_SORT_SIZE, SEED, ITERATIONS, MAX_LENGTH, MIN_LENGTH, NUMBERS
         */
        static Stream<Arguments> testCorrectSortingMid() {
            return Stream.of(
                    Arguments.arguments(1, 42, 100, 1, 0, 100),
                    Arguments.arguments(1, 43, 100, 10, 1, 100),
                    Arguments.arguments(2, 44, 100, 100, 1, 100),
                    Arguments.arguments(2, 45, 100, 1000, 1, 100),
                    Arguments.arguments(3, 46, 100, 69, 1, 100),
                    Arguments.arguments(10, 47, 100, 10000, 1, 100)
            );
        }

        /**
         * RANDOM PIVOT: SELECTION_SORT_SIZE, SEED, ITERATIONS, MAX_LENGTH, MIN_LENGTH, NUMBERS
         */
        static Stream<Arguments> testCorrectSortingRandom() {
            return Stream.of(
                    Arguments.arguments(1, 42, 100, 1, 0, 100),
                    Arguments.arguments(1, 43, 100, 10, 1, 100),
                    Arguments.arguments(2, 44, 100, 100, 1, 100),
                    Arguments.arguments(2, 45, 100, 1000, 1, 100),
                    Arguments.arguments(3, 46, 100, 69, 1, 100),
                    Arguments.arguments(10, 47, 100, 10000, 1, 100)
            );
        }

        /**
         * MEDIAN FRONT PIVOT: SELECTION_SORT_SIZE, SEED, ITERATIONS, CONSIDERED_ELEMENTS, MAX_LENGTH, MIN_LENGTH, NUMBERS
         */
        static Stream<Arguments> testCorrectSortingMedianFront() {
            return Stream.of(
                    Arguments.arguments(1, 42, 100, 2, 1, 0, 100),
                    Arguments.arguments(1, 43, 100, 2, 10, 1, 100),
                    Arguments.arguments(2, 44, 100, 3, 100, 1, 100),
                    Arguments.arguments(2, 45, 100, 3, 1000, 1, 100),
                    Arguments.arguments(3, 46, 100, 5, 69, 1, 100),
                    Arguments.arguments(10, 47, 100, 5, 10000, 1, 100)
            );
        }

        /**
         * MEDIAN DISTRIBUTED PIVOT: SELECTION_SORT_SIZE, SEED, ITERATIONS, CONSIDERED_ELEMENTS, MAX_LENGTH, MIN_LENGTH, NUMBERS
         */
        static Stream<Arguments> testCorrectSortingMedianDist() {
            return Stream.of(
                    Arguments.arguments(1, 42, 100, 2, 1, 0, 100),
                    Arguments.arguments(1, 43, 100, 2, 10, 1, 100),
                    Arguments.arguments(2, 44, 100, 3, 100, 1, 100),
                    Arguments.arguments(2, 45, 100, 3, 1000, 1, 100),
                    Arguments.arguments(3, 46, 100, 5, 69, 1, 100),
                    Arguments.arguments(10, 47, 100, 5, 10000, 1, 100)
            );
        }

        void randomTestFactory(PivotFinder pivotFinder, int selectSortSize, int seed, int iterations,
                               int maxLength, int minLength, int numbers) {
            Quicksort quicksort = new Quicksort(pivotFinder, selectSortSize);
            Random random = new Random(seed);

            assertAll("", IntStream.range(0, iterations).mapToObj(i -> () -> {
                int arrLen = random.nextInt(maxLength - minLength) + minLength;
                int[] arr = IntStream.range(0, arrLen).map(x -> random.nextInt(numbers) - numbers / 2).toArray();
                int[] arrSorted = Arrays.copyOf(arr, arr.length);
                int[] arrOriginal = Arrays.copyOf(arr, arr.length);

                Arrays.sort(arrSorted);
                quicksort.sort(arr, new SilentResult());

                if (!Arrays.equals(arrSorted, arr))
                    fail("\nThe given array was: " + Arrays.toString(arrOriginal) + "\nYou array was: " + Arrays.toString(arr)
                            + "\nBut should be: " + Arrays.toString(arrSorted) + "\nPivot was: " + pivotFinder);
            }));

        }

        @DisplayName("Last Index")
        @ParameterizedTest(name = "{index} | Sorts correct: Pivot: {0}, SelectionSize: {1}, Iterations: {3}")
        @MethodSource
        void testCorrectSortingLast(int selectSortSize, int seed, int iterations, int maxLength, int minLength, int numbers) {
            randomTestFactory(PivotFinder.getLastPivot(), selectSortSize, seed, iterations, maxLength, minLength, numbers);
        }

        @DisplayName("Middle Index")
        @ParameterizedTest(name = "{index} | Sorts correct: SelectionSize: {1}, Iterations: {3}")
        @MethodSource
        void testCorrectSortingMid(int selectSortSize, int seed, int iterations, int maxLength, int minLength, int numbers) {
            randomTestFactory(PivotFinder.getMidPivot(), selectSortSize, seed, iterations, maxLength, minLength, numbers);
        }

        @DisplayName("Random Index")
        @ParameterizedTest(name = "{index} | Sorts correct: SelectionSize: {1}, Iterations: {3}")
        @MethodSource
        void testCorrectSortingRandom(int selectSortSize, int seed, int iterations, int maxLength, int minLength, int numbers) {
            randomTestFactory(PivotFinder.getRandomPivot(seed), selectSortSize, seed, iterations, maxLength, minLength, numbers);
        }

        @DisplayName("Median Front Index")
        @ParameterizedTest(name = "{index} | Sorts correct: SelectionSize: {1}, Iterations: {3}")
        @MethodSource
        void testCorrectSortingMedianFront(int selectSortSize, int seed, int iterations, int considered, int maxLength,
                                           int minLength, int numbers) {
            randomTestFactory(PivotFinder.getMedianPivotFront(considered), selectSortSize, seed, iterations, maxLength,
                    minLength, numbers);
        }

        @DisplayName("Median Distributed Index")
        @ParameterizedTest(name = "{index} | Sorts correct: SelectionSize: {1}, Iterations: {3}")
        @MethodSource
        void testCorrectSortingMedianDist(int selectSortSize, int seed, int iterations, int considered, int maxLength,
                                          int minLength, int numbers) {
            randomTestFactory(PivotFinder.getMedianPivotDistributed(considered), selectSortSize, seed, iterations,
                    maxLength, minLength, numbers);
        }
    }
}
