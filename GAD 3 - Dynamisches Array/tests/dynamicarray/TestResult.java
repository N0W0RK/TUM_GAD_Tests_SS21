package tests.dynamicarray;

import gad.dynamicarray.Result;

public class TestResult implements Result {

    private int[] array;

    @Override
    public void logArray(int[] toLog) {
        array = toLog;
    }

    public int[] getArray() {
        return array;
    }
}
