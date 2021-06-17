package tests.simplesort;

import gad.simplesort.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SelectionsortTester {

    @BeforeEach
    void printLine() {
        System.out.println("-------------------------");
    }

    /**
     * Tests the selection sort based optmisation of simple merge sort
     * Since the tests are already in that class, it just simply calls them from this class
     * Note: You may fail these tests if you have a different implementation of logging (There are 2 correct afaik)
     * @author Aamin
     */
    @Test
    public void MergesortSimpleTester() {
        MergesortSimpleTester mergesortSimpleTester = new MergesortSimpleTester();
        mergesortSimpleTester.mergeSortSimpleOptimised();
    }

    /**
     * Tests the selection sort based optmisation of merge sort
     * Since the tests are already in that class, it just simply calls them from this class
     * Note: You may fail these tests if you have a different implementation of logging (There are 2 correct afaik)
     * @author Aamin
     */
    @Test
    public void MergesortTester() {
        MergesortTester.FullRunTest fullRunTest = new MergesortTester.FullRunTest();
        fullRunTest.mergeSortOptimised();
    }

    /**
     * Tests the selection sort based optmisation of single pivot quick sort
     * Since the tests are already in that class, it just simply calls them from this class
     * @author Aamin
     */
    @Test
    public void SinglePivotQuickSortTester() {
        QuickSortTester quickSortTester = new QuickSortTester();
        quickSortTester.testQuickSortOptimised();
        quickSortTester.testQuickSortOptimisedMidPivot();
        quickSortTester.testQuickSortOptimisedRandomPivot();
        quickSortTester.testQuickSortOptimisedMedianPivotFront3();
        quickSortTester.testQuickSortOptimisedMedianPivotFront5();
        quickSortTester.testQuickSortOptimisedDistributedMedianPivotFront3();
        quickSortTester.testQuickSortOptimisedDistributedMedianPivotFront5();
        quickSortTester.testQuickSortOptimisedBRUTAL();
    }

    /**
     * Tests the selection sort based optmisation of dual pivot quick sort
     * Since the tests are already in that class, it just simply calls them from this class
     * Although this is not on artemis directly under selection sort, I added it additionally under selection sort
     * @author Aamin
     */
    @Test
    public void DualPivotQuickSortTester() {
        DualPivotQuicksortTester.LoggingTest dualPivotQuickSortTester = new DualPivotQuicksortTester().new LoggingTest();
        dualPivotQuickSortTester.optimisedDualPivotQuickSortFirstLastPivot();
        dualPivotQuickSortTester.optimisedDualPivotQuickSortRandomPivot();
        dualPivotQuickSortTester.optimisedDualPivotQuickSortMedianPivot();
        dualPivotQuickSortTester.optimisedDualPivotQuickSortDistributedMedianPivot();
        dualPivotQuickSortTester.HugeArrayOptimisedDualPivotQuickSort();
    }

}
