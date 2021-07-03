package tests.binomilia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gad.binomilia.*;

/**
 *@author Mihir Mahajan
 *
 *        These tests only test if your BinomialHeap/BinomialTree itself works.
 *        They don't test the logging. Still, they may not work if the logging
 *        is incorrect!
 *
 *        I can not guarantee the correctness or accuracy of these tests. I
 *        hope they help, enjoy!
 */

public class BinomialHeapTester {

    private BinomialHeap heap;
    private StudentResult result;

    @BeforeEach
    void init() {

        heap = new BinomialHeap();
        result = new StudentResult();
    }

    @Test
    void testIllegal() {
        assertThrows(RuntimeException.class, () -> heap.min(),
                "Please throw an exception when heap is empty, since there can't "
                + "be a minimal element in an empty heap. (NoSuchElementExeption would be cool)");
        assertThrows(RuntimeException.class, () -> heap.deleteMin(result),
                "Please throw an exception when heap is empty, since deleteMin() "
                + "is illegal on an empty heap. (NoSuchElementExeption would be cool)");
    }

    @Test
    void testMin() {
        for (int i = 0; i < 500; i++) {
            heap.insert(i, result);
            assertEquals(0, heap.min(), "Wrong value for min!");
        }
    }

    @Test
    void testDeleteMin() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            heap.insert(i, result);
            nums.add(i);
        }

        for (int x : nums) {
            assertEquals(x, heap.min(), "Wrong value for min!");
            int deleted = heap.deleteMin(result);
            assertEquals(x, deleted,
                    "Either did not delete the smallest number or the expected number wasnt in the heap");
        }
    }

    @Test
    void testBigNumbers() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int num = new Random().nextInt();
            nums.add(num);
            heap.insert(num, result);
            int minList = nums.stream().min((x, y) -> Integer.compare(x, y)).get();
            int minHeap = heap.min();
            assertEquals(minList, minHeap, "Wrong minimum " + i);
        }
        for (int i = 0; i < 500; i++) {
            int num = new Random().nextInt();
            nums.add(num);
            heap.insert(num, result);
            Integer min = nums.stream().min((x, y) -> Integer.compare(x, y)).get();
            nums.remove(min);
            assertEquals(min, heap.deleteMin(result), "Wrong number removed " + i);
        }
    }

    @Test
    void testAmountOFTrees() {
        List<Integer> nums = new ArrayList<>();
        TreeCounterResult myResult = new TreeCounterResult();

        for (int i = 0; i < 500; i++) {
            int num = new Random().nextInt();
            nums.add(num);
            heap.insert(num, myResult);
            int expectedSize = Integer.bitCount(nums.size());
            int trees = myResult.getSize();
            assertEquals(expectedSize, trees, "Wrong amount of Trees in heap. If only this test fails, "
                    + "check if there is an Integer-Overflow, since these Numbers are pretty big.");
        }
    }

    /**
     * It's possible to get a 100% score, even if this test does not pass. However,
     * it provides a good first starting point to implement the logging properly.
     */
    @Test
    void zwischenSchritte() {
        StepCounterResult myResult = new StepCounterResult();

        for (int i = 0; i < 500; i++) {
            int num = new Random().nextInt();
            heap.insert(num, myResult);
            for (int j = 1; j < myResult.getSteps().size(); j++) {
                if (myResult.getSteps().get(j) + 1 != myResult.getSteps().get(j - 1)) {
                    fail("Es wurden nicht genau zwei Bäume zu einem gemergt. Stattdessen wurden in einem "
                            + "Zwischenschritt " + (myResult.getSteps().get(j - 1) - myResult.getSteps().get(j))
                            + "vorhandene Bäume entfernt, anstatt genau einem");
                }
            }
        }

        for (int i = 0; i < 500; i++) {
            heap.deleteMin(myResult);
            for (int j = 1; j < myResult.getSteps().size(); j++) {
                if (myResult.getSteps().get(j) + 1 != myResult.getSteps().get(j - 1)) {
                    fail("Es wurden nicht genau zwei Bäume zu einem gemergt. Stattdessen wurden in einem Zwischenschritt "
                            + (myResult.getSteps().get(j - 1) - myResult.getSteps().get(j))
                            + " vorhandene Bäume entfernt, anstatt genau einem");
                }
            }
        }
    }
}
