package tests.dynamicarray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gad.dynamicarray.StackyQueue;
import org.junit.jupiter.api.Test;

class StackyQueueTester {

    @Test
    void test() {
        StackyQueue s = new StackyQueue(3, 4, new TestResult(), new TestResult());
        s.insert(1);
        assertEquals("[1, 0, 0], length: 1, [], length: 0", s.toString());
        s.insert(2);
        assertEquals("[1, 2, 0], length: 2, [], length: 0", s.toString());
        s.insert(3);
        assertEquals("[1, 2, 3], length: 3, [], length: 0", s.toString());
        s.insert(4);
        assertEquals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], length: 4, [], length: 0", s.toString());
        assertEquals(1, s.remove());
        assertEquals("[], length: 0, [4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0], length: 3", s.toString());
        assertEquals(2, s.remove());
        assertEquals("[], length: 0, [4, 3, 0, 0, 0, 0], length: 2", s.toString());
        assertEquals(3, s.remove());
        assertEquals("[], length: 0, [4, 0, 0], length: 1", s.toString());
        s.insert(5);
        assertEquals("[5, 0, 0], length: 1, [4, 0, 0], length: 1", s.toString());
        s.insert(6);
        assertEquals("[5, 6, 0], length: 2, [4, 0, 0], length: 1", s.toString());
        assertEquals(4, s.remove());
        assertEquals("[5, 6, 0], length: 2, [], length: 0", s.toString());
        assertEquals(5, s.remove());
        assertEquals("[], length: 0, [6, 5, 0], length: 1", s.toString());
    }

}
