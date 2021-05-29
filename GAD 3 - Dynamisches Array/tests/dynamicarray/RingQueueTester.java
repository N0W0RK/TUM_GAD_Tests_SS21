package tests.dynamicarray;

import static org.junit.jupiter.api.Assertions.assertTrue;

import gad.dynamicarray.RingQueue;
import org.junit.jupiter.api.Test;

class RingQueueTester {

	@Test
	void test() {
		RingQueue r = new RingQueue(3, 4, new TestResult());
		r.insert(1);
		assertTrue(r.toString().equals("[1, 0, 0], size: 1"));
		r.insert(2);
		assertTrue(r.toString().equals("[1, 2, 0], size: 2"));
		r.insert(3);
		assertTrue(r.toString().equals("[1, 2, 3], size: 3"));
		r.insert(4);
		assertTrue(r.toString().equals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], size: 4"));
		assertTrue(r.remove() == 1);
		assertTrue(r.toString().equals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], size: 3"));
		assertTrue(r.remove() == 2);
		assertTrue(r.toString().equals("[3, 4, 0, 0, 0, 0], size: 2"));
		assertTrue(r.remove() == 3);
		assertTrue(r.toString().equals("[4, 0, 0], size: 1"));
		r.insert(5);
		assertTrue(r.toString().equals("[4, 5, 0], size: 2"));
		r.insert(6);
		assertTrue(r.toString().equals("[4, 5, 6], size: 3"));
		assertTrue(r.remove() == 4);
		r.insert(7);
		assertTrue(r.toString().equals("[7, 5, 6], size: 3"));
		r.insert(8);
		assertTrue(r.toString().equals("[5, 6, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0], size: 4"));
	}

}
