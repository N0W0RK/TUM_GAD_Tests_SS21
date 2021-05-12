package gad.dynamicarray;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestForStackyQueue {

	@Test
	void test() {
		StackyQueue s = new StackyQueue(3, 4, new StudentResult(), new StudentResult());
		s.insert(1);
		assertTrue(s.toString().equals("[1, 0, 0], length: 1, [], length: 0"));
		s.insert(2);
		assertTrue(s.toString().equals("[1, 2, 0], length: 2, [], length: 0"));
		s.insert(3);
		assertTrue(s.toString().equals("[1, 2, 3], length: 3, [], length: 0"));
		s.insert(4);
		assertTrue(s.toString().equals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], length: 4, [], length: 0"));
		assertEquals(1, s.remove());
		assertTrue(s.toString().equals("[], length: 0, [4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0], length: 3"));
		assertEquals(2, s.remove());
		assertTrue(s.toString().equals("[], length: 0, [4, 3, 0, 0, 0, 0], length: 2"));
		assertEquals(3, s.remove());
		assertTrue(s.toString().equals("[], length: 0, [4, 0, 0], length: 1"));
		s.insert(5);
		assertTrue(s.toString().equals("[5, 0, 0], length: 1, [4, 0, 0], length: 1"));
		s.insert(6);
		assertTrue(s.toString().equals("[5, 6, 0], length: 2, [4, 0, 0], length: 1"));
		assertEquals(4, s.remove());
		assertTrue(s.toString().equals("[5, 6, 0], length: 2, [], length: 0"));
		assertEquals(5, s.remove());
		assertTrue(s.toString().equals("[], length: 0, [6, 5, 0], length: 1"));
	}

}
