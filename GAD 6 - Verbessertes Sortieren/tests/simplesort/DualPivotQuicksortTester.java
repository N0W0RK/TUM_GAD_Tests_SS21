package tests.simplesort;

import gad.simplesort.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

class DualPivotQuicksortTester {

    @BeforeEach
    void printLine() {
        System.out.println("-------------------------");
    }

    @Nested
    @DisplayName("Repetitive Random Tests")
    class repetitive_random_tests {
        /**
         The concept is that you can extend the tests as much as you want, by simply adding
         new Arguments.arguments in the corresponding Stream. Which place is which parameter
         is stated in the header.
         */


        /**
         * LAST PIVOT: SELECTION_SORT_SIZE, SEED, ITERATIONS, MAX_LENGTH, MIN_LENGTH, NUMBERS
         */
        static Stream<Arguments> test_correct_sorting_last() {
            return Stream.of(
                    Arguments.arguments(1, 42, 100, 1, 0, 100),
                    Arguments.arguments(1, 43, 100, 10, 1, 100),
                    Arguments.arguments(2, 44, 100, 100, 1, 100),
                    Arguments.arguments(2, 45, 100, 1000, 1, 100),
                    Arguments.arguments(3, 46, 100, 69, 1, 100),
                    Arguments.arguments(10, 47, 100, 1000, 1, 100)
            );
        }

        /**
         * RANDOM PIVOT: SELECTION_SORT_SIZE, SEED, ITERATIONS, MAX_LENGTH, MIN_LENGTH, NUMBERS
         */
        static Stream<Arguments> test_correct_sorting_random() {
            return Stream.of(
                    Arguments.arguments(1, 42, 100, 1, 0, 100),
                    Arguments.arguments(1, 43, 100, 10, 1, 100),
                    Arguments.arguments(2, 44, 100, 100, 1, 100),
                    Arguments.arguments(2, 45, 100, 1000, 1, 100),
                    Arguments.arguments(3, 46, 100, 69, 1, 100),
                    Arguments.arguments(10, 47, 100, 1000, 1, 100)
            );
        }

        /**
         * MEDIAN FRONT PIVOT: SELECTION_SORT_SIZE, SEED, ITERATIONS, CONSIDERED_ELEMENTS, MAX_LENGTH, MIN_LENGTH, NUMBERS
         */
        static Stream<Arguments> test_correct_sorting_median_front() {
            return Stream.of(
                    Arguments.arguments(1, 42, 100, 2, 1, 0, 100),
                    Arguments.arguments(1, 43, 100, 2, 10, 1, 100),
                    Arguments.arguments(2, 44, 100, 3, 100, 1, 100),
                    Arguments.arguments(2, 45, 100, 3, 1000, 1, 100),
                    Arguments.arguments(3, 46, 100, 5, 69, 1, 100),
                    Arguments.arguments(10, 47, 100, 5, 100, 1, 100)
            );
        }

        /**
         * MEDIAN DISTRIBUTED PIVOT: SELECTION_SORT_SIZE, SEED, ITERATIONS, CONSIDERED_ELEMENTS, MAX_LENGTH, MIN_LENGTH, NUMBERS
         */
        static Stream<Arguments> test_correct_sorting_median_dist() {
            return Stream.of(
                    Arguments.arguments(1, 42, 100, 2, 1, 0, 100),
                    Arguments.arguments(1, 43, 100, 2, 10, 1, 100),
                    Arguments.arguments(2, 44, 100, 3, 100, 1, 100),
                    Arguments.arguments(2, 45, 100, 3, 1000, 1, 100),
                    Arguments.arguments(3, 46, 100, 5, 69, 1, 100),
                    Arguments.arguments(10, 47, 100, 5, 10000, 1, 100)
            );
        }

        void random_test_factory(DualPivotFinder pivotFinder, int selectSortSize, int seed, int iterations,
                                 int maxLength, int minLength, int numbers) {
            try {
                DualPivotQuicksort quicksort = new DualPivotQuicksort(pivotFinder, selectSortSize);
                Random random = new Random(seed);

                assertAll("", IntStream.range(0, iterations).mapToObj(i -> () -> {
                    int arrLen = random.nextInt(maxLength - minLength) + minLength;
                    int[] arr = IntStream.range(0, arrLen).map(x -> random.nextInt(numbers) - numbers / 2).toArray();
                    int[] arrSorted = Arrays.copyOf(arr, arr.length);
                    int[] arrOriginal = Arrays.copyOf(arr, arr.length);

                    Arrays.sort(arrSorted);
                    quicksort.sort(arr, new TesterDualQuicksortResult());

                    if (!Arrays.equals(arrSorted, arr))
                        fail("\nThe given array was: " + Arrays.toString(arrOriginal) +
                                "\nYou array was:     " + Arrays.toString(arr) + "\nBut should be: "
                                + Arrays.toString(arrSorted) + "\nPivot was: " + pivotFinder);
                }));
            } catch (Error e) {
                System.out.println(e.toString());
            }
        }

        @DisplayName("First-Last Index")
        @ParameterizedTest(name = "{index} | Sorts correct: SelectionSize: {0}, Iterations: {2}")
        @MethodSource
        void test_correct_sorting_last(int selectSortSize, int seed, int iterations,
                                       int maxLength, int minLength, int numbers) {
            random_test_factory(DualPivotFinder.getFirstLastPivot(), selectSortSize, seed, iterations,
                    maxLength, minLength, numbers);
        }

        @DisplayName("Random Index")
        @ParameterizedTest(name = "{index} | Sorts correct: SelectionSize: {0}, Iterations: {2}")
        @MethodSource
        void test_correct_sorting_random(int selectSortSize, int seed, int iterations,
                                         int maxLength, int minLength, int numbers) {
            random_test_factory(DualPivotFinder.getRandomPivot(seed), selectSortSize, seed, iterations,
                    maxLength, minLength, numbers);
        }

        @DisplayName("Median Front Index")
        @ParameterizedTest(name = "{index} | Sorts correct: SelectionSize: {0}, Iterations: {2}")
        @MethodSource
        void test_correct_sorting_median_front(int selectSortSize, int seed, int iterations, int considered,
                                               int maxLength, int minLength, int numbers) {
            random_test_factory(DualPivotFinder.getMedianPivotFront(considered), selectSortSize, seed, iterations,
                    maxLength, minLength, numbers);
        }

        @DisplayName("Median Distributed Index")
        @ParameterizedTest(name = "{index} | Sorts correct: SelectionSize: {0}, Iterations: {2}")
        @MethodSource
        void test_correct_sorting_median_dist(int selectSortSize, int seed, int iterations, int considered,
                                              int maxLength, int minLength, int numbers) {
            random_test_factory(DualPivotFinder.getMedianPivotDistributed(considered), selectSortSize, seed, iterations,
                    maxLength, minLength, numbers);
        }
    }

    @Nested
    @DisplayName("Tests for Stack Overflows - YOU MIGHT PASS ARTEMIS WITHOUT THEM!")
    class stack_overflow_test {
        /**
         * Add more stack overflow candidates:
         * ARRAY_LEN, GENERATOR FOR ARRAY (OPERAND IS A RISING NUMBER), SHORT DESCRIPTION
         */
        static Stream<Arguments> test_for_stack_overflows() {
            Random random = new Random(420);

            return Stream.of(
                    Arguments.arguments(500_000, (IntUnaryOperator) operand -> random.nextInt(200), "Just random"),
                    Arguments.arguments(2_500_000, (IntUnaryOperator) operand -> 1, "Maaaany ones"),
                    Arguments.arguments(10_000, (IntUnaryOperator) operand -> operand, "Rising"),
                    Arguments.arguments(10_000, (IntUnaryOperator) operand -> -1 * (operand + 10_000), "Descendant")
            );
        }


        void test_factory_with_pivots(int arrLen, IntUnaryOperator mapper, String whatsThat) {

            assertAll("", Stream.of(DualPivotFinder.getFirstLastPivot(),
                    DualPivotFinder.getRandomPivot(42), DualPivotFinder.getRandomPivot(69),
                    DualPivotFinder.getMedianPivotFront(2), DualPivotFinder.getMedianPivotFront(3),
                    DualPivotFinder.getMedianPivotFront(5), DualPivotFinder.getMedianPivotDistributed(2),
                    DualPivotFinder.getMedianPivotDistributed(3), DualPivotFinder.getMedianPivotDistributed(5))
                    .map(dualPivotFinder -> () -> {
                        DualPivotQuicksort quicksort = new DualPivotQuicksort(dualPivotFinder, 1);
                        int[] arr = IntStream.range(0, arrLen).map(mapper).toArray();

                        try {
                            quicksort.sort(arr, new SilentResult(), 0, arrLen - 1);
                        } catch (java.lang.StackOverflowError e) {
                            fail("StackOverFlow with repetitive numbers: " + whatsThat + " and Pivot: " + dualPivotFinder);
                        }
                    }));
        }

        @DisplayName("Random Index")
        @ParameterizedTest(name = "{index} | Array length: {0} with: {2}")
        @MethodSource
        void test_for_stack_overflows(int arrLen, IntUnaryOperator mapper, String text) {
            test_factory_with_pivots(arrLen, mapper, text);
        }
    }
}
