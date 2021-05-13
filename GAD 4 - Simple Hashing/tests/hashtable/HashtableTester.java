package gad.simplehash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class HashtableTester {

	@Test
	void test() {
		// Hashtable.getNextPowerOfTwo(i) tester
		assertEquals(2, Hashtable.getNextPowerOfTwo(2));
		assertEquals(128, Hashtable.getNextPowerOfTwo(66));
		assertEquals(256, Hashtable.getNextPowerOfTwo(130));
		assertEquals(1, Hashtable.getNextPowerOfTwo(1));
		assertEquals(1024, Hashtable.getNextPowerOfTwo(1000));
		assertEquals(64, Hashtable.getNextPowerOfTwo(64));
		assertEquals(2, Hashtable.getNextPowerOfTwo(2));
		assertEquals(8192, Hashtable.getNextPowerOfTwo(5000));

		// fastModuloTester random Numbers
		assertEquals(5, Hashtable.fastModulo(13, 8));
		assertEquals(11, Hashtable.fastModulo(75, 64));
		assertEquals(3500 % 1024, Hashtable.fastModulo(3500, 1024));
		assertEquals(3954 - 2048, Hashtable.fastModulo(3954, 2048));
		assertEquals(5540 - 4096, Hashtable.fastModulo(5540, 4096));
		assertEquals((Integer.MAX_VALUE / 5) % 8, Hashtable.fastModulo(Integer.MAX_VALUE / 5, 8));
		assertEquals(Integer.MAX_VALUE % 8, Hashtable.fastModulo(Integer.MAX_VALUE, 8));

		Hashtable<Integer, Integer> hashmap = new Hashtable<>(8, new int[] { 5, 6, 2, 7, 1, 4, 3 });
		// testing insert via find()
		hashmap.insert(71, 89, null);
		hashmap.insert(71, 99, null);
		hashmap.insert(312, 98, null);

		// test find
		assertEquals(89, hashmap.find(71, null).orElse(-1));
		assertEquals(98, hashmap.find(312, null).orElse(-1));

		// prepare correct list for findAll()
		List<Integer> testList = new ArrayList<>();
		testList.add(89);
		testList.add(99);
		assertTrue(compareElements(testList, hashmap.findAll(71, null)),
				"The returned list from findAll() should be equal to testList.");
		assertTrue(compareElements(new ArrayList<>(), hashmap.findAll(1, null)),
				"Empty list since V with K = 1 does not exist");

		// remove tester -> does not test, if the table has the correct list after
		// remove.
		assertTrue(hashmap.remove(71, null), "Removing a V with K = 71 should work");
		assertTrue(hashmap.remove(312, null), "Removing a V with K = 312 should work");
		assertFalse(hashmap.remove(1, null), "Removing a V with non existent key should return false.");
	}

	/**
	 * A method to test, if the list in the table has correctly changed
	 * 
	 * @param testList
	 * @param findAll
	 * @return
	 */
	private boolean compareElements(List<Integer> testList, List<Integer> findAll) {
		try {
			for (int i = 0; i < testList.size(); i++) {
				if (!testList.get(i).equals(findAll.get(i))) {
					return false;
				}
			}
			return true;
		} catch (IndexOutOfBoundsException ioe) {
			return false;
		}
	}
}
