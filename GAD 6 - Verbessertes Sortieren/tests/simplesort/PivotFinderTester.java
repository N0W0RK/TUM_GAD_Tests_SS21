package tests.simplesort;

import gad.simplesort.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PivotFinderTester {

    @BeforeEach
    void printLine() {
        System.out.println("-------------------------");
    }

    @Test
    void lastPivot() {
    }

    @Test
    void midPivot() {
    }

    @Test
    void randomPivot() {
    }

    @Test
    void medianPivotFront() {
    }

    @Test
    void medianPivotDistributed() {
    }
    
    @Test
    void testAll(){
        bigArraysTestHelper(PivotFinder.getMidPivot(),300,30);
        bigArraysTestHelper(PivotFinder.getRandomPivot(50),300,30);
        bigArraysTestHelper(PivotFinder.getMedianPivotFront(3),300,30);
        bigArraysTestHelper(PivotFinder.getMedianPivotFront(5),300,30);
        bigArraysTestHelper(PivotFinder.getMedianPivotDistributed(3),300,30);
        bigArraysTestHelper(PivotFinder.getMedianPivotDistributed(5),300,30);
    }
    
    /**
        Beware: This test helper is in some regards *stricter* than the Artemis tests. Good for finding exceptions and general edge cases regardless.
        maxArrayLength 30 is recommended for general testing, 3 for the small array tests.
        
        Provided by the man, the myth, the legend: Ralg
    */
   static void bigArraysTestHelper(PivotFinder f, int seed, int maxArrayLength) {
        Random ralg = new Random(seed);
        for (int i = 0; i < 10000; i++) {
            final int[] a = IntStream.generate(() -> ralg.nextInt(200) - 100).limit(i % maxArrayLength + 1).toArray();
            int from = ralg.nextInt(a.length);
            int to = ralg.nextInt(a.length);
            if (from == to) continue;
            if (to < from) {
                int temp = to;
                to = from;
                from = temp;
            }
            final int result = f.findPivot(a, from, to);
            assertTrue(result >= from && result <= to);
            System.out.println("Array: " + Arrays.toString(a) + "\tfrom " + from + " to " + to + ":\t"
                    + result);
        }
    }
}
