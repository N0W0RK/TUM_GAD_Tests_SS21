package tests.dynamicarray;

import static org.junit.jupiter.api.Assertions.assertTrue;

import gad.dynamicarray.DynamicStack;
import org.junit.jupiter.api.Test;

public class DynamicStackTester {

	@Test
	void defaultTest() {
		DynamicStack ds = new DynamicStack(3, 4, new TestResult());
		ds.insert(1);
		assertTrue(ds.toString().equals("[1, 0, 0], length: 1"));
		ds.insert(2);
		assertTrue(ds.toString().equals("[1, 2, 0], length: 2"));
		ds.insert(3);
		assertTrue(ds.toString().equals("[1, 2, 3], length: 3"));
		ds.insert(4);
		assertTrue(ds.toString().equals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], length: 4"));
		ds.remove();
		assertTrue(ds.toString().equals("[1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0], length: 3"));
		ds.insert(5);
		assertTrue(ds.toString().equals("[1, 2, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0], length: 4"));
		ds.remove();
		ds.remove();
		assertTrue(ds.toString().equals("[1, 2, 0, 0, 0, 0], length: 2"));
		ds.remove();
		assertTrue(ds.toString().equals("[1, 0, 0], length: 1"));
		ds.remove();
		assertTrue(ds.toString().equals("[], length: 0"));

	}

}
