package tests.dynamicarray;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gad.dynamicarray.RingQueue;
import org.junit.jupiter.api.Test;

class RingQueueTester {

    @Test
    void test() {
        RingQueue r = new RingQueue(3, 4, new TestResult());
        r.insert(1);
        assertEquals("[1, 0, 0], size: 1", r.toString());
        r.insert(2);
        assertEquals("[1, 2, 0], size: 2", r.toString());
        r.insert(3);
        assertEquals("[1, 2, 3], size: 3", r.toString());
        r.insert(4);
        assertEquals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], size: 4", r.toString());
        assertEquals(1, r.remove());
        assertEquals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], size: 3", r.toString());
        assertEquals(2, r.remove());
        assertEquals("[3, 4, 0, 0, 0, 0], size: 2", r.toString());
        assertEquals(3, r.remove());
        assertEquals("[4, 0, 0], size: 1", r.toString());
        r.insert(5);
        assertEquals("[4, 5, 0], size: 2", r.toString());
        r.insert(6);
        assertEquals("[4, 5, 6], size: 3", r.toString());
        assertEquals(4, r.remove());
        r.insert(7);
        assertEquals("[7, 5, 6], size: 3", r.toString());
        r.insert(8);
        assertEquals("[5, 6, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0], size: 4", r.toString());
    }

}
