package tests.radix;

import gad.radix.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastResult implements Result {

    private final List<Integer[]> logs;
    private int count;

    public FastResult() {
        this.logs = new ArrayList<>();
        count = 0;
    }

    @Override
    public void logArray(int[] array) {
        logs.add(Arrays.stream(array).boxed().toArray(Integer[]::new));
    }

    public List<Integer[]> getLogs() {
        return logs;
    }
}
