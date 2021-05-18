package tests.simplehash;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gad.simplehash.Hashtable;
import gad.simplehash.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashtableTester {

	TestModuloHelper mH = new TestModuloHelper();
	
	@BeforeEach
	void printLine() {
		System.out.println("-------------------------");
	}

	@Test
	void powerOfTwoLoop() {
		int expectedPower = 1;
		for(int i = 1; i <1050; i++) {
			assertEquals(expectedPower, Hashtable.getNextPowerOfTwo(i));
			if(expectedPower <= i) {
				expectedPower *= 2;
			}
		}

	}

	@Test
	void fastModuloLoop() {

		System.out.println("Times include overhead from assertions");

		Random random = new Random();

		long fastest = Long.MAX_VALUE;
		long slowest = 0;
		int[][] vals = new int[2][3];
		int loopC = 0;
		long sum = 0;

		for (int i = 1; i < 30; i+=1e3) {
			int div = (int) Math.pow(2,i);

			for (int j = 0; j< Integer.MAX_VALUE-1e4; j+= random.nextInt((int) 1e4)) {
				int mod = j%div;
				int finalJ = j;
				long start = System.nanoTime();
				assertTimeout(Duration.ofMillis(500),() -> {
					assertEquals(mod, Hashtable.fastModulo(finalJ,div), String.format("Modulo of %d%%%d should be %d", finalJ,div,mod));
				}, "Fast modulo operation took too long");
				start = System.nanoTime()-start;
				if (start < fastest) {
					fastest = start;
					vals[0][0] = j;
					vals[0][1] = div;
					vals[0][2] = mod;
				}
				if (start > slowest) {
					slowest = start;
					vals[1][0] = j;
					vals[1][1] = div;
					vals[1][2] = mod;
				}
				sum += start;
				loopC++;
			}
		}
		System.out.printf("Slowest calculation %d%%%d = %d with duration %fs%n", vals[1][0], vals[1][1], vals[1][2], slowest*1e-9);
		System.out.printf("Slowest calculation %d%%%d = %d with duration %fs%n", vals[0][0], vals[0][1], vals[0][2], fastest*1e-9);
		System.out.printf("Average time of %fs for %d calculations%n",(sum*1e-9)/loopC, loopC);
	}



	@Test
	void test() {

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
		hashmap.insert(71, 89, mH);
		hashmap.insert(71, 99, mH);
		hashmap.insert(312, 98, mH);

		// test find
		assertEquals(89, hashmap.find(71, mH).orElse(-1), "Expected to return first element inserted with key 71");
		assertEquals(98, hashmap.find(312, mH).orElse(-1), "Expected to return first element inserted with key 312");

		// prepare correct list for findAll()
		List<Integer> testList = new ArrayList<>();
		testList.add(89);
		testList.add(99);
		assertArrayEquals(testList.toArray(), hashmap.findAll(71, mH).toArray(),
				"The returned list from findAll() should be equal to testList.");
		assertArrayEquals((new ArrayList<>()).toArray(), hashmap.findAll(1, mH).toArray(),
				"Empty list since V with K = 1 does not exist");

		// remove tester -> does not test, if the table has the correct list after
		// remove.
		assertTrue(hashmap.remove(71, mH), "Removing a V with K = 71 should work");
		assertTrue(hashmap.remove(312, mH), "Removing a V with K = 312 should work");
		assertFalse(hashmap.remove(1, mH), "Removing a V with non existent key should return false.");

		List<Pair<Integer,Integer>>[] table = hashmap.getTable();
		for (List<Pair<Integer,Integer>> list: table) {
			assertEquals(0, list.size(), "Hashtable should be empty after removal of all elements");
		}
	}
}
