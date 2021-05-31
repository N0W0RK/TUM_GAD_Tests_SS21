package tests.simplesort;

import gad.simplesort.DualPivotFinder;
import gad.simplesort.Result;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TesterDualQuicksortResult implements Result {
    int[] numbersAtStart = null;
    int[] indicesOfLog = new int[6];
    int countThroughLog = 0;
    DualPivotFinder pivotFinder;

    public TesterDualQuicksortResult(DualPivotFinder pivotFinder) {
        this.pivotFinder = pivotFinder;
    }

    @Override
    public void startSelectionsort(int[] array, int from, int to) {

    }

    @Override
    public void startMergesort(int[] array, int from, int to) {

    }

    @Override
    public void startQuicksort(int[] array, int from, int to) {

    }

    @Override
    public void startDualPivotQuicksort(int[] array, int from, int to) {
        numbersAtStart = Arrays.copyOf(array, array.length);
    }

    @Override
    public void startJavaSort(int[] array, int from, int to) {

    }

    @Override
    public void logPartialArray(int[] array, int from, int to) {
        indicesOfLog[countThroughLog] = from;
        countThroughLog = (countThroughLog + 1) % 6;
        indicesOfLog[countThroughLog] = to;
        countThroughLog = (countThroughLog + 1) % 6;

        if (countThroughLog == 0) {
            analyseLogsOfDualPivot(array);
        }

    }

    private void analyseLogsOfDualPivot(int[] endResult) {
        if (numbersAtStart == null || endResult == null) {
            throw new IllegalArgumentException("Array: " + Arrays.toString(endResult) + "\n" + "\nIn range: " + indicesOfLog[0] +
                    " to " + indicesOfLog[5] + "\n" + "\nLogged partial Arrays without logging startResult or endResult");
        }

        int[] numbersAtStartPartial = Arrays.copyOfRange(numbersAtStart, indicesOfLog[0], indicesOfLog[5] + 1);
        Arrays.sort(numbersAtStartPartial);
        int[] copyEndResult = Arrays.copyOfRange(endResult, indicesOfLog[0], indicesOfLog[5] + 1);
        Arrays.sort(copyEndResult);

        assertEquals(numbersAtStartPartial.length, copyEndResult.length, "Array: " + Arrays.toString(endResult) + "\nIn range: "
                + indicesOfLog[0] + " to " + indicesOfLog[5] + "\nThe arrays before and after the sort dont have the same length anymore, " +
                "you probably changed the to or from pointer");

        assertArrayEquals(copyEndResult, numbersAtStartPartial, "Array: " + Arrays.toString(endResult) + "\nIn range: " +
                indicesOfLog[0] + " to " + indicesOfLog[5] + "\nThe arrays before and after the sort have different numbers");

        int[] pivots = pivotFinder.findPivot(numbersAtStart, indicesOfLog[0], indicesOfLog[5]);
        int valuePivotStartFirst = numbersAtStart[pivots[0]];
        int valuePivotStartSecond = numbersAtStart[pivots[1]];

        int pivotOne = endResult[indicesOfLog[1] + 1];
        int pivotTwo = endResult[indicesOfLog[3] + 1];

        if (!(valuePivotStartFirst == pivotOne && valuePivotStartSecond == pivotTwo) &&
                !(valuePivotStartFirst == pivotTwo && valuePivotStartSecond == pivotOne)) {
            fail("Array: " + Arrays.toString(endResult) + "\nIn range: " + indicesOfLog[0] + " to " + indicesOfLog[5] +
                    "\nThe pivots of the pivot finder do not correspond to the log Pivots");
        }

        if (pivotOne > pivotTwo) {
            fail("Array: " + Arrays.toString(endResult) + "\nIn range: " + indicesOfLog[0] + " to " + indicesOfLog[5] +
                    "\nThe second pivot is smaller than the first one");
        }

        for (int i = indicesOfLog[0]; i <= indicesOfLog[1]; i++) {
            if (endResult[i] > pivotOne) {
                fail("Array: " + Arrays.toString(endResult) + "\nIn range: " + indicesOfLog[0] + " to " + indicesOfLog[5] +
                        "\nIn the first part is at index " + (indicesOfLog[0] + i) + " a number bigger than the first pivot");
            }
        }

        for (int i = indicesOfLog[2]; i <= indicesOfLog[3]; i++) {
            if (endResult[i] < pivotOne || endResult[i] > pivotTwo) {
                fail("Array: " + Arrays.toString(endResult) + "\nIn range: " + indicesOfLog[0] + " to " + indicesOfLog[5] +
                        "\nIn the second part is at index " + (indicesOfLog[2] + i) + " a number than shouldn't be there");
            }
        }

        for (int i = indicesOfLog[4]; i <= indicesOfLog[5]; i++) {
            if (endResult[i] < pivotTwo) {
                fail("Array: " + Arrays.toString(endResult) + "\nIn range: " + indicesOfLog[0] + " to " + indicesOfLog[5] +
                        "\nIn the third part is at index " + (indicesOfLog[4] + i) + " a number smaller than the second pivot");
            }
        }
    }
}