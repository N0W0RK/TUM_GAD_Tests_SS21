package tests.simplesort;

import gad.simplesort.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fast logging class which doesn't print anything to the console
 * but stores all outputs that would have been printed in a list
 *
 * Abbreviations list:
 *   SS = SelectionSort
 *   MS = MergeSort
 *   QS = QuickSort
 *   DPQS = DualPivotQuickSort
 *   JS = JavaSort
 *   LPA = LoggingPartialArray
 * @author Aamin
 */
public class FastLoggingResult implements Result {

    private final List<String> outputs = new ArrayList<>();

    /**
     * To log selection sort
     * SS = SelectionSort
     * @param array The array to be logged
     * @param from The first index from which it'll be logged (inclusive)
     * @param to The last index up to which it'll be logged (inclusive)
     * @author Aamin
     */
    @Override
    public void startSelectionsort(int[] array, int from, int to) {
        outputs.add("\nSS : " + Arrays.toString(array) + " in [" + from + ", " + to + "]");
    }

    /**
     * To log merge sort
     * MS = MergeSort
     * @param array The array to be logged
     * @param from The first index from which it'll be logged (inclusive)
     * @param to The last index up to which it'll be logged (inclusive)
     * @author Aamin
     */
    @Override
    public void startMergesort(int[] array, int from, int to) {
        outputs.add("\nMS : " + Arrays.toString(array) + " in [" + from + ", " + to + "]");
    }

    /**
     * To log quick sort
     * QS = QuickSort
     * @param array The array to be logged
     * @param from The first index from which it'll be logged (inclusive)
     * @param to The last index up to which it'll be logged (inclusive)
     * @author Aamin
     */
    @Override
    public void startQuicksort(int[] array, int from, int to) {
        outputs.add("\nQS : " + Arrays.toString(array) + " in [" + from + ", " + to + "]");
    }

    /**
     * To log DualPivotQuickSort
     * DQPS = DualPivotQuickSort
     * @param array The array to be logged
     * @param from The first index from which it'll be logged (inclusive)
     * @param to The last index up to which it'll be logged (inclusive)
     * @author Aamin
     */
    @Override
    public void startDualPivotQuicksort(int[] array, int from, int to) {
        outputs.add("\nDPQS : " + Arrays.toString(array) + " in [" + from + ", " + to + "]");
    }

    /**
     * To log java sort
     * JS = JavaSort
     * @param array The array to be logged
     * @param from The first index from which it'll be logged (inclusive)
     * @param to The last index up to which it'll be logged (inclusive)
     * @author Aamin
     */
    @Override
    public void startJavaSort(int[] array, int from, int to) {
        outputs.add("\nJS : " + Arrays.toString(array) + " in [" + from + ", " + to + "]");
    }

    /**
     * To log a partial array
     * LPA = LoggingPartialArray
     * @param array The array to be logged
     * @param from The first index from which it'll be logged (inclusive)
     * @param to The last index up to which it'll be logged (inclusive)
     * @author Aamin
     */
    @Override
    public void logPartialArray(int[] array, int from, int to) {
        outputs.add("\n\tLPA : " + Arrays.toString(array) + " in [" + from + ", " + to + "]");
    }

    /**
     * To be used at the end of the sorting to compare with the expected sort
     * @return List of String
     * @author Aamin
     */
    public List<String> getOutputs() {
        return outputs;
    }
}
