package tests.dynamicarray;

import gad.dynamicarray.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Tests included from Zulip:
 * https://zulip.in.tum.de/#narrow/stream/419-GAD-E03-Dynamisches.20Array/topic/Tests/near/177724
 * @author Aamin
 */
class DynamicArrayTester {

    @Test
    void testIllegalArguments() {

        try {
            new DynamicArray(4, 4);
        } catch(IllegalArgumentException ignored) {
            Assertions.fail("IllegalArgumentException was thrown");
        }

        try {
            new DynamicArray(-1, 4);
            Assertions.fail("No IllegalArgumentException was thrown");
        } catch(IllegalArgumentException ignored) {}

        try {
            new DynamicArray(0, 4);
            Assertions.fail("No IllegalArgumentException was thrown");
        } catch(IllegalArgumentException ignored) {}

        try {
            new DynamicArray(1, 4);
        } catch(IllegalArgumentException ignored) {
            Assertions.fail("IllegalArgumentException was thrown");
        }

        try {
            new DynamicArray(4, -1);
            Assertions.fail("No IllegalArgumentException was thrown");
        } catch(IllegalArgumentException ignored) {}

        try {
            new DynamicArray(4, 0);
            Assertions.fail("No IllegalArgumentException was thrown");
        } catch(IllegalArgumentException ignored) {}

        try {
            new DynamicArray(4, 1);
            Assertions.fail("No IllegalArgumentException was thrown");
        } catch(IllegalArgumentException ignored) {}

        try {
            new DynamicArray(-1, -1);
            Assertions.fail("No IllegalArgumentException was thrown");
        } catch(IllegalArgumentException ignored) {}

        try {
            new DynamicArray(0, 0);
            Assertions.fail("No IllegalArgumentException was thrown");
        } catch(IllegalArgumentException ignored) {}


        try {
            new DynamicArray(3, 4);
        } catch(IllegalArgumentException e) {
            Assertions.fail("IllegalArgumentException was thrown");
        }

        try {
            new DynamicArray(6, 9);
        } catch (IllegalArgumentException ignored) {
            Assertions.fail("IllegalArgumentException was thrown");
        }

        try {
            new DynamicArray(69, 69);
        } catch (IllegalArgumentException ignored) {
            Assertions.fail("IllegalArgumentException was thrown");
        }

        try {
            new DynamicArray(420, 69);
            Assertions.fail("No IllegalArgumentException was thrown");
        } catch (IllegalArgumentException ignored) {}

    }

    @Test
    void test69() {
        DynamicArray dynamicArray = new DynamicArray(3, 4);
        Assertions.assertEquals("[]", dynamicArray.toString());

        dynamicArray.set(0, 0);
        Assertions.assertEquals("[0, 0, 0]", dynamicArray.toString());

        dynamicArray.set(4, 4);
        Assertions.assertEquals("[0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", dynamicArray.toString());

    }

    @Test
    void DynamicArrayArtemisTest() {

        DynamicArray myArray = new DynamicArray(3, 4);
        Assertions.assertEquals("[]", myArray.toString());

        myArray.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 1);
        Assertions.assertEquals("[0, 0, 0]", myArray.toString());

        myArray.set(2, 1);
        Assertions.assertEquals("[0, 0, 1]", myArray.toString());

        myArray.set(0, 3);
        Assertions.assertEquals("[3, 0, 1]", myArray.toString());

        Interval interval = myArray.reportUsage(new Interval.NonEmptyInterval(1, 2), 4);
        Assertions.assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", myArray.toString());
        Assertions.assertEquals("[0; 1]", interval.toString());

        interval = myArray.reportUsage(new Interval.NonEmptyInterval(3, 6), 4);
        Assertions.assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", myArray.toString());
        Assertions.assertEquals("[3; 6]", interval.toString());

        interval = myArray.reportUsage(new Interval.NonEmptyInterval(1, 1), 1);
        Assertions.assertEquals("[1, 0, 0]", myArray.toString());
        Assertions.assertEquals("[0; 0]", interval.toString());

        interval = myArray.reportUsage(new Interval.NonEmptyInterval(2, 0), 4);
        Assertions.assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", myArray.toString());
        Assertions.assertEquals("[0; 1]", interval.toString());

        interval = myArray.reportUsage(new Interval.NonEmptyInterval(5,1), 9);
        Assertions.assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", myArray.toString());
        Assertions.assertEquals("[5; 1]", interval.toString());

        interval = myArray.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
        Assertions.assertEquals("[]", myArray.toString());
        Assertions.assertEquals("[]", interval.toString());
    }

    @Test
    void dynamicArray() {
        DynamicArray dynamicArray = new DynamicArray(3, 4);

        dynamicArray.set(0, 1);
        dynamicArray.set(1, 2);
        dynamicArray.set(2, 3);
        dynamicArray.set(3, 4);
        Assertions.assertEquals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0]", dynamicArray.toString());
        Assertions.assertEquals(new Interval.NonEmptyInterval(0, 1), dynamicArray.reportUsage(new Interval.NonEmptyInterval(2, 3), 2));
        Assertions.assertEquals("[3, 4, 0, 0, 0, 0]", dynamicArray.toString());

        dynamicArray.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
        dynamicArray.set(0, 6100);
        dynamicArray.set(1, 6918);
        dynamicArray.set(2, 6965);
        dynamicArray.set(3, 1926);
        dynamicArray.set(4, 6570);
        dynamicArray.set(5, 8511);
        dynamicArray.set(6, 9021);
        Assertions.assertEquals("[6100, 6918, 6965, 1926, 6570, 8511, 9021, 0, 0, 0, 0, 0]", dynamicArray.toString());
        dynamicArray.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
        dynamicArray.set(0, 2439);
        dynamicArray.set(1, 652);
        dynamicArray.set(2, 7266);
        dynamicArray.set(3, 4183);
        dynamicArray.set(4, 7092);
        dynamicArray.set(5, 9408);
        dynamicArray.set(6, 3411);

        dynamicArray.reportUsage(new Interval.NonEmptyInterval(0, 5), 6);
        Assertions.assertEquals("[2439, 652, 7266, 4183, 7092, 9408, 3411, 0, 0, 0, 0, 0]", dynamicArray.toString());

        Assertions.assertEquals(new Interval.NonEmptyInterval(5, 6), dynamicArray.reportUsage(new Interval.NonEmptyInterval(5, 6), 3));
        Assertions.assertEquals("[2439, 652, 7266, 4183, 7092, 9408, 3411, 0, 0, 0, 0, 0]", dynamicArray.toString());

        Assertions.assertEquals(Interval.EmptyInterval.getEmptyInterval(), dynamicArray.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0));
        Assertions.assertEquals("[]", dynamicArray.toString());

        dynamicArray.set(4, 5);

        for (int i = 0; i < 15; i++) {
            dynamicArray.set(i, i + 1);
        }

        /*
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", dynamicArray.toString());
        Assertions.assertEquals(new Interval.NonEmptyInterval(0, 0), dynamicArray.reportUsage(new Interval.NonEmptyInterval(0, 0), 4));
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", dynamicArray.toString());
        Assertions.assertEquals(new Interval.NonEmptyInterval(0, 3), dynamicArray.reportUsage(new Interval.NonEmptyInterval(6, 2), 3));
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", dynamicArray.toString());
        Assertions.assertEquals(new Interval.NonEmptyInterval(2, 2), dynamicArray.reportUsage(new Interval.NonEmptyInterval(2, 2), 4));
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", dynamicArray.toString());
        Assertions.assertEquals(new Interval.NonEmptyInterval(3, 3), dynamicArray.reportUsage(new Interval.NonEmptyInterval(3, 3), 4));
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", dynamicArray.toString());
        Assertions.assertEquals(new Interval.NonEmptyInterval(4, 4), dynamicArray.reportUsage(new Interval.NonEmptyInterval(4, 4), 4));
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", dynamicArray.toString());

         */

    }

    @Test
    void dynamicStack() {
        DynamicStack ds = new DynamicStack(3, 4, new TestResult());
        ds.insert(1);
        Assertions.assertEquals("[1, 0, 0], length: 1", ds.toString());
        ds.insert(2);
        Assertions.assertEquals("[1, 2, 0], length: 2", ds.toString());
        ds.insert(3);
        Assertions.assertEquals("[1, 2, 3], length: 3", ds.toString());
        ds.insert(4);
        Assertions.assertEquals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], length: 4", ds.toString());
        Assertions.assertEquals(4, ds.remove());
        Assertions.assertEquals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], length: 3", ds.toString());
        ds.insert(5);
        Assertions.assertEquals("[1, 2, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0], length: 4", ds.toString());
        Assertions.assertEquals(5, ds.remove());
        Assertions.assertEquals(3, ds.remove());
        Assertions.assertEquals("[1, 2, 0, 0, 0, 0], length: 2", ds.toString());
        Assertions.assertEquals(2, ds.remove());
        Assertions.assertEquals("[1, 0, 0], length: 1", ds.toString());
        Assertions.assertEquals(1, ds.remove());
        Assertions.assertEquals("[], length: 0", ds.toString());
    }

    @Test
    void ringQueue() {
        RingQueue ringQueue = new RingQueue(3, 4, new TestResult());
        Assertions.assertEquals("[], size: 0", ringQueue.toString());
        ringQueue.insert(1);
        Assertions.assertEquals("[1, 0, 0], size: 1", ringQueue.toString());
        ringQueue.insert(2);
        Assertions.assertEquals("[1, 2, 0], size: 2", ringQueue.toString());
        ringQueue.insert(3);
        Assertions.assertEquals("[1, 2, 3], size: 3", ringQueue.toString());
        ringQueue.insert(4);
        Assertions.assertEquals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], size: 4", ringQueue.toString());
        Assertions.assertEquals(1, ringQueue.remove());
        Assertions.assertEquals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], size: 3", ringQueue.toString());
        Assertions.assertEquals(2, ringQueue.remove());
        Assertions.assertEquals("[3, 4, 0, 0, 0, 0], size: 2", ringQueue.toString());
        Assertions.assertEquals(3, ringQueue.remove());
        Assertions.assertEquals("[4, 0, 0], size: 1", ringQueue.toString());
        ringQueue.insert(5);
        Assertions.assertEquals("[4, 5, 0], size: 2", ringQueue.toString());
        ringQueue.insert(6);
        Assertions.assertEquals("[4, 5, 6], size: 3", ringQueue.toString());
        Assertions.assertEquals(4, ringQueue.remove());
        Assertions.assertEquals("[4, 5, 6], size: 2", ringQueue.toString());
        ringQueue.insert(7);
        Assertions.assertEquals("[7, 5, 6], size: 3", ringQueue.toString());
        ringQueue.insert(8);
        Assertions.assertEquals("[5, 6, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0], size: 4", ringQueue.toString());
        Assertions.assertEquals(5, ringQueue.remove());
        Assertions.assertEquals("[5, 6, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0], size: 3", ringQueue.toString());
        Assertions.assertEquals(6, ringQueue.remove());
        Assertions.assertEquals("[7, 8, 0, 0, 0, 0], size: 2", ringQueue.toString());
        Assertions.assertEquals(7, ringQueue.remove());
        Assertions.assertEquals("[8, 0, 0], size: 1", ringQueue.toString());
        Assertions.assertEquals(8, ringQueue.remove());
        Assertions.assertEquals("[], size: 0", ringQueue.toString());
        ringQueue.insert(9);
        Assertions.assertEquals("[9, 0, 0], size: 1", ringQueue.toString());
        ringQueue.insert(10);
        Assertions.assertEquals("[9, 10, 0], size: 2", ringQueue.toString());
        ringQueue.insert(11);
        Assertions.assertEquals("[9, 10, 11], size: 3", ringQueue.toString());
        ringQueue.insert(12);
        Assertions.assertEquals("[9, 10, 11, 12, 0, 0, 0, 0, 0, 0, 0, 0], size: 4", ringQueue.toString());
        ringQueue.insert(13);
        Assertions.assertEquals("[9, 10, 11, 12, 13, 0, 0, 0, 0, 0, 0, 0], size: 5", ringQueue.toString());
        ringQueue.insert(14);
        Assertions.assertEquals("[9, 10, 11, 12, 13, 14, 0, 0, 0, 0, 0, 0], size: 6", ringQueue.toString());
        Assertions.assertEquals(9, ringQueue.remove());
        Assertions.assertEquals("[9, 10, 11, 12, 13, 14, 0, 0, 0, 0, 0, 0], size: 5", ringQueue.toString());
        Assertions.assertEquals(10, ringQueue.remove());
        Assertions.assertEquals("[9, 10, 11, 12, 13, 14, 0, 0, 0, 0, 0, 0], size: 4", ringQueue.toString());
        Assertions.assertEquals(11, ringQueue.remove());
        Assertions.assertEquals("[9, 10, 11, 12, 13, 14, 0, 0, 0, 0, 0, 0], size: 3", ringQueue.toString());
        Assertions.assertEquals(12, ringQueue.remove());
        Assertions.assertEquals("[13, 14, 0, 0, 0, 0], size: 2", ringQueue.toString());
        Assertions.assertEquals(13, ringQueue.remove());
        Assertions.assertEquals("[14, 0, 0], size: 1", ringQueue.toString());
        Assertions.assertEquals(14, ringQueue.remove());
        Assertions.assertEquals("[], size: 0", ringQueue.toString());
        ringQueue.insert(15);
        Assertions.assertEquals("[15, 0, 0], size: 1", ringQueue.toString());
        ringQueue.insert(16);
        Assertions.assertEquals("[15, 16, 0], size: 2", ringQueue.toString());
        ringQueue.insert(17);
        Assertions.assertEquals("[15, 16, 17], size: 3", ringQueue.toString());
        ringQueue.insert(18);
        Assertions.assertEquals("[15, 16, 17, 18, 0, 0, 0, 0, 0, 0, 0, 0], size: 4", ringQueue.toString());
        ringQueue.insert(19);
        Assertions.assertEquals("[15, 16, 17, 18, 19, 0, 0, 0, 0, 0, 0, 0], size: 5", ringQueue.toString());
        ringQueue.insert(20);
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 0, 0, 0, 0, 0, 0], size: 6", ringQueue.toString());
        ringQueue.insert(21);
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 21, 0, 0, 0, 0, 0], size: 7", ringQueue.toString());
        ringQueue.insert(22);
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 21, 22, 0, 0, 0, 0], size: 8", ringQueue.toString());
        ringQueue.insert(23);
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 0, 0], size: 9", ringQueue.toString());
        ringQueue.insert(24);
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0, 0], size: 10", ringQueue.toString());
        Assertions.assertEquals(15, ringQueue.remove());
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0, 0], size: 9", ringQueue.toString());
        Assertions.assertEquals(16, ringQueue.remove());
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0, 0], size: 8", ringQueue.toString());
        Assertions.assertEquals(17, ringQueue.remove());
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0, 0], size: 7", ringQueue.toString());
        ringQueue.insert(25);
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 0], size: 8", ringQueue.toString());
        ringQueue.insert(26);
        Assertions.assertEquals("[15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26], size: 9", ringQueue.toString());
        ringQueue.insert(27);
        Assertions.assertEquals("[27, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26], size: 10", ringQueue.toString());
        ringQueue.insert(28);
        Assertions.assertEquals("[27, 28, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26], size: 11", ringQueue.toString());
        ringQueue.insert(29);
        Assertions.assertEquals("[27, 28, 29, 18, 19, 20, 21, 22, 23, 24, 25, 26], size: 12", ringQueue.toString());
        ringQueue.insert(30);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 13", ringQueue.toString());
        Assertions.assertEquals(18, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 12", ringQueue.toString());
        Assertions.assertEquals(19, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 11", ringQueue.toString());
        Assertions.assertEquals(20, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 10", ringQueue.toString());
        ringQueue.insert(31);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 11", ringQueue.toString());
        ringQueue.insert(32);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 12", ringQueue.toString());
        Assertions.assertEquals(21, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 11", ringQueue.toString());
        Assertions.assertEquals(22, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 10", ringQueue.toString());
        ringQueue.insert(33);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 11", ringQueue.toString());
        ringQueue.insert(34);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 12", ringQueue.toString());
        ringQueue.insert(35);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 13", ringQueue.toString());
        ringQueue.insert(36);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 14", ringQueue.toString());
        Assertions.assertEquals(23, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 13", ringQueue.toString());
        Assertions.assertEquals(24, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 12", ringQueue.toString());
        Assertions.assertEquals(25, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 11", ringQueue.toString());
        Assertions.assertEquals(26, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 10", ringQueue.toString());
        ringQueue.insert(37);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 11", ringQueue.toString());
        ringQueue.insert(38);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 12", ringQueue.toString());
        ringQueue.insert(39);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 13", ringQueue.toString());
        Assertions.assertEquals(27, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 12", ringQueue.toString());
        ringQueue.insert(40);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 13", ringQueue.toString());
        ringQueue.insert(41);
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 14", ringQueue.toString());
        Assertions.assertEquals(28, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 13", ringQueue.toString());
        Assertions.assertEquals(29, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 12", ringQueue.toString());
        Assertions.assertEquals(30, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 11", ringQueue.toString());
        Assertions.assertEquals(31, ringQueue.remove());
        Assertions.assertEquals("[18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 10", ringQueue.toString());
        Assertions.assertEquals(32, ringQueue.remove());
        Assertions.assertEquals("[33, 34, 35, 36, 37, 38, 39, 40, 41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], size: 9", ringQueue.toString());
    }

    @Test
    void ringQueueHalfJohannes() {
        RingQueue ringQueue = new RingQueue(2, 5, new TestResult());
        Assertions.assertEquals("[], size: 0", ringQueue.toString());
        ringQueue.insert(4);
        Assertions.assertEquals("[4, 0], size: 1", ringQueue.toString());
        ringQueue.insert(6);
        Assertions.assertEquals("[4, 6], size: 2", ringQueue.toString());
        Assertions.assertEquals(4, ringQueue.remove());
        Assertions.assertEquals("[4, 6], size: 1", ringQueue.toString());
        ringQueue.insert(7);
        Assertions.assertEquals("[7, 6], size: 2", ringQueue.toString());
        Assertions.assertEquals(6, ringQueue.remove());

    }

    @Test
    void ringQueueBrutalLoop() {
        RingQueue ringQueue = new RingQueue(3, 4, new TestResult());
        int[] insertedValues = new int[100000];
        int index = 0;
        int lastAdded;
        int randInt;

        for (lastAdded = 0; lastAdded < 1000; lastAdded++) {
            randInt = new Random().nextInt(10000);
            insertedValues[lastAdded] = randInt;
            ringQueue.insert(randInt);
        }

        for (int i = 0; i < 10000; i++) {

            if (new Random().nextInt(10) < 7 && index < lastAdded) {
                Assertions.assertEquals(insertedValues[index++],  ringQueue.remove());
            } else {
                randInt = new Random().nextInt(10000);
                ringQueue.insert(randInt);
                insertedValues[lastAdded++] = randInt;
            }
        }

        ringQueue = new RingQueue(3, 4, new TestResult());
        insertedValues = new int[20000];
        index = 0;

        for (int i = 0; i < 10000; i++) {
            randInt = new Random().nextInt(10000);
            ringQueue.insert(randInt);
            insertedValues[i] = randInt;

            if (new Random().nextInt(10) < 3) {
                Assertions.assertEquals(insertedValues[index++], ringQueue.remove());
            }
        }

    }
}
