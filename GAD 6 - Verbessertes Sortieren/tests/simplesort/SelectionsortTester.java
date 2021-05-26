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

    @Test
    public void MergesortSimpleTester() {
        MergesortSimpleTester mergesortSimpleTester = new MergesortSimpleTester();
        mergesortSimpleTester.mergeSortSimpleOptimised();
    }

    @Test
    public void MergesortTester() {
        MergesortTester mergesortTester = new MergesortTester();
        mergesortTester.mergeSortOptimised();
    }

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

}
