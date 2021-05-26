package tests.binarysearch;

import gad.binarysearch.Interval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static gad.binarysearch.BinSea.search;

public class IntervalSearchTester {

    @Test
    void test1(){
        Interval expected = new Interval.NonEmptyInterval(2,4);
        Interval actual = search(new int[]{1, 27, 100, 127, 3000}, new Interval.NonEmptyInterval(80, 10000), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void test2(){
        Interval expected = new Interval.NonEmptyInterval(0,4);
        Interval actual = search(new int[]{1, 27, 100, 127, 3000}, new Interval.NonEmptyInterval(0, 10000), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void test3(){
        Interval expected = new Interval.NonEmptyInterval(0,4);
        Interval actual = search(new int[]{1, 27, 100, 127, 3000}, new Interval.NonEmptyInterval(-10, 10000), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void test4(){
        Interval expected = new Interval.NonEmptyInterval(0,4);
        Interval actual = search(new int[]{1, 1, 1, 1, 1}, new Interval.NonEmptyInterval(1, 1), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void test5(){
        Interval expected = Interval.EmptyInterval.getEmptyInterval();
        Interval actual = search(new int[]{}, new Interval.NonEmptyInterval(-10, 10000), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void test6(){
        Interval expected = Interval.EmptyInterval.getEmptyInterval();
        Interval actual = search(new int[]{-100, -50, -20, 10100, 10200}, new Interval.NonEmptyInterval(-10, 10000), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void test7(){
        Interval expected = new Interval.NonEmptyInterval(1,5);
        Interval actual = search(new int[]{-150, -100, -50, -20, 10100, 10200}, new Interval.NonEmptyInterval(-100, 10200), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void test8(){
        Interval expected = new Interval.NonEmptyInterval(0,3);
        Interval actual = search(new int[]{-10, -10, 10000, 10000}, new Interval.NonEmptyInterval(-10, 10000), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void test9(){
        Interval expected = new Interval.NonEmptyInterval(1,6);
        Interval actual = search(new int[]{-12, 0, 0, 0, 0, 0, 0, 12}, new Interval.NonEmptyInterval(-10, 10), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void test10(){
        Interval expected = new Interval.NonEmptyInterval(7,7);
        Interval actual = search(new int[]{-12, 0, 0, 0, 0, 0, 0, 12}, new Interval.NonEmptyInterval(1, 15), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void test11(){
        Interval expected = new Interval.NonEmptyInterval(0,0);
        Interval actual = search(new int[]{-12, 0, 0, 0, 0, 0, 0, 12}, new Interval.NonEmptyInterval(-12, -1), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void test12(){
        Interval expected = new Interval.NonEmptyInterval(0,0);
        Interval actual = search(new int[]{1}, new Interval.NonEmptyInterval(1,1), new TestResult(), new TestResult());
        Assertions.assertEquals(expected,actual);
    }
}
