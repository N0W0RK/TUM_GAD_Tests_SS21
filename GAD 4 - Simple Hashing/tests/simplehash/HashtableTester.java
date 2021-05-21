package tests.simplehash;

import java.time.Duration;
import java.util.*;

import gad.simplehash.Hashtable;
import gad.simplehash.Pair;
import org.junit.jupiter.api.Assertions;
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
				//Duration may need to be adjustet to fit your execution times
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

	@Test
	void randomInsertRemove() {

		Map<Integer, List<Integer>> map = new HashMap<>();
		Random random = new Random();
		Hashtable<Integer, Integer> hashtable = new Hashtable<>(128, new int[]{1,2,3,4,5,6,7,8,9,69});

		for (int i = 0; i < 1e4; i++) {

			int key = random.nextInt((int) 1e5);
			int val = random.nextInt();
			hashtable.insert(key, val, mH);

			if (map.containsKey(key)) {
				map.get(key).add(val);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(val);
				map.put(key, list);
			}
		}

		for (int key : map.keySet()) {
			assertArrayEquals(map.get(key).toArray(), hashtable.findAll(key, mH).toArray(), String.format("findAll(%d) did not return expected values", key));
		}
	}

	@Test
	void testRandomInsertions() {

		Random random = new Random();
		//dependend on h()

		int[] a = new int[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt((int) Math.sqrt(Integer.MAX_VALUE));
		}
		Hashtable<Integer, Integer> hashtable = new Hashtable<>(16, a);

		for (int i = 0; i < 1e5; i++) {
			int key = random.nextInt(Integer.MAX_VALUE);
			int val = random.nextInt();
			int hash = hashtable.h(key,mH);

			List<Pair<Integer, Integer>> listH = new ArrayList<>(hashtable.getTable()[hash]);

			hashtable.insert(key,val,mH);
			listH.add(new Pair<>(key, val));

			assertArrayEquals(hashtable.getTable()[hash].toArray(), listH.toArray(), String.format("List at table index %d not updated as expected", hash));
		}
	}

	@Test
	void precalculatedInserts() {

		Hashtable<Integer, Integer> hashtable = new Hashtable<>(4, new int[] {1,2,3,4});
		List<Pair<Integer, Integer>>[] controlTable = new List[4];

		for (int i = 0; i < controlTable.length; i++) {
			controlTable[i] = new ArrayList<>();
		}

		hashtable.insert(212534970, 0, mH);
		controlTable[0].add(new Pair<>(212534970, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(707377108, 0, mH);
		controlTable[0].add(new Pair<>(707377108, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(586986483, 0, mH);
		controlTable[2].add(new Pair<>(586986483, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1309368880, 0, mH);
		controlTable[2].add(new Pair<>(1309368880, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(838820326, 0, mH);
		controlTable[3].add(new Pair<>(838820326, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(2145033582, 0, mH);
		controlTable[3].add(new Pair<>(2145033582, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1831522046, 0, mH);
		controlTable[1].add(new Pair<>(1831522046, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(291465426, 0, mH);
		controlTable[1].add(new Pair<>(291465426, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(789296130, 0, mH);
		controlTable[2].add(new Pair<>(789296130, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1076901949, 0, mH);
		controlTable[0].add(new Pair<>(1076901949, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(235528489, 0, mH);
		controlTable[2].add(new Pair<>(235528489, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(2080976692, 0, mH);
		controlTable[0].add(new Pair<>(2080976692, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1465569820, 0, mH);
		controlTable[1].add(new Pair<>(1465569820, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1364700421, 0, mH);
		controlTable[0].add(new Pair<>(1364700421, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(881236295, 0, mH);
		controlTable[1].add(new Pair<>(881236295, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1146496453, 0, mH);
		controlTable[2].add(new Pair<>(1146496453, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(864775393, 0, mH);
		controlTable[1].add(new Pair<>(864775393, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1070085018, 0, mH);
		controlTable[2].add(new Pair<>(1070085018, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(621995847, 0, mH);
		controlTable[3].add(new Pair<>(621995847, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(764433472, 0, mH);
		controlTable[2].add(new Pair<>(764433472, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1360722012, 0, mH);
		controlTable[3].add(new Pair<>(1360722012, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1723338882, 0, mH);
		controlTable[2].add(new Pair<>(1723338882, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(833396419, 0, mH);
		controlTable[1].add(new Pair<>(833396419, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(326680025, 0, mH);
		controlTable[2].add(new Pair<>(326680025, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1580731869, 0, mH);
		controlTable[3].add(new Pair<>(1580731869, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(83447335, 0, mH);
		controlTable[0].add(new Pair<>(83447335, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(2008707253, 0, mH);
		controlTable[1].add(new Pair<>(2008707253, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1439407618, 0, mH);
		controlTable[0].add(new Pair<>(1439407618, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1977993717, 0, mH);
		controlTable[3].add(new Pair<>(1977993717, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1414959033, 0, mH);
		controlTable[3].add(new Pair<>(1414959033, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(620334664, 0, mH);
		controlTable[3].add(new Pair<>(620334664, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1733257178, 0, mH);
		controlTable[0].add(new Pair<>(1733257178, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1687144605, 0, mH);
		controlTable[0].add(new Pair<>(1687144605, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(477362078, 0, mH);
		controlTable[1].add(new Pair<>(477362078, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1422485196, 0, mH);
		controlTable[3].add(new Pair<>(1422485196, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(181670451, 0, mH);
		controlTable[0].add(new Pair<>(181670451, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1145471350, 0, mH);
		controlTable[1].add(new Pair<>(1145471350, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1010678679, 0, mH);
		controlTable[1].add(new Pair<>(1010678679, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(21500612, 0, mH);
		controlTable[2].add(new Pair<>(21500612, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1427561022, 0, mH);
		controlTable[1].add(new Pair<>(1427561022, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1280697899, 0, mH);
		controlTable[1].add(new Pair<>(1280697899, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1571659888, 0, mH);
		controlTable[3].add(new Pair<>(1571659888, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1460215322, 0, mH);
		controlTable[0].add(new Pair<>(1460215322, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1297565398, 0, mH);
		controlTable[1].add(new Pair<>(1297565398, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1172587762, 0, mH);
		controlTable[0].add(new Pair<>(1172587762, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1758082915, 0, mH);
		controlTable[3].add(new Pair<>(1758082915, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(400130931, 0, mH);
		controlTable[3].add(new Pair<>(400130931, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1512887909, 0, mH);
		controlTable[1].add(new Pair<>(1512887909, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(484806962, 0, mH);
		controlTable[1].add(new Pair<>(484806962, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1071011595, 0, mH);
		controlTable[2].add(new Pair<>(1071011595, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(874433180, 0, mH);
		controlTable[2].add(new Pair<>(874433180, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1903780215, 0, mH);
		controlTable[1].add(new Pair<>(1903780215, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(302994495, 0, mH);
		controlTable[3].add(new Pair<>(302994495, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1565846016, 0, mH);
		controlTable[0].add(new Pair<>(1565846016, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(810019, 0, mH);
		controlTable[1].add(new Pair<>(810019, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1338694617, 0, mH);
		controlTable[3].add(new Pair<>(1338694617, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(565946432, 0, mH);
		controlTable[2].add(new Pair<>(565946432, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1416416531, 0, mH);
		controlTable[1].add(new Pair<>(1416416531, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1095318531, 0, mH);
		controlTable[2].add(new Pair<>(1095318531, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(633323117, 0, mH);
		controlTable[3].add(new Pair<>(633323117, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1089322802, 0, mH);
		controlTable[2].add(new Pair<>(1089322802, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(251270951, 0, mH);
		controlTable[2].add(new Pair<>(251270951, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1808891255, 0, mH);
		controlTable[1].add(new Pair<>(1808891255, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1940652941, 0, mH);
		controlTable[3].add(new Pair<>(1940652941, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(990943237, 0, mH);
		controlTable[2].add(new Pair<>(990943237, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(97895594, 0, mH);
		controlTable[1].add(new Pair<>(97895594, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1560404463, 0, mH);
		controlTable[1].add(new Pair<>(1560404463, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(24199194, 0, mH);
		controlTable[3].add(new Pair<>(24199194, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(548447204, 0, mH);
		controlTable[1].add(new Pair<>(548447204, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1160929455, 0, mH);
		controlTable[0].add(new Pair<>(1160929455, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1727083808, 0, mH);
		controlTable[2].add(new Pair<>(1727083808, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(2027619104, 0, mH);
		controlTable[3].add(new Pair<>(2027619104, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(461061522, 0, mH);
		controlTable[0].add(new Pair<>(461061522, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1073472938, 0, mH);
		controlTable[1].add(new Pair<>(1073472938, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1805785142, 0, mH);
		controlTable[3].add(new Pair<>(1805785142, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1685669415, 0, mH);
		controlTable[1].add(new Pair<>(1685669415, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1650128112, 0, mH);
		controlTable[2].add(new Pair<>(1650128112, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(59944903, 0, mH);
		controlTable[0].add(new Pair<>(59944903, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1347489317, 0, mH);
		controlTable[1].add(new Pair<>(1347489317, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1585797145, 0, mH);
		controlTable[3].add(new Pair<>(1585797145, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1471533211, 0, mH);
		controlTable[1].add(new Pair<>(1471533211, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(77396711, 0, mH);
		controlTable[1].add(new Pair<>(77396711, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1481489181, 0, mH);
		controlTable[2].add(new Pair<>(1481489181, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1199197387, 0, mH);
		controlTable[0].add(new Pair<>(1199197387, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1002620850, 0, mH);
		controlTable[0].add(new Pair<>(1002620850, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1403520567, 0, mH);
		controlTable[2].add(new Pair<>(1403520567, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(831219996, 0, mH);
		controlTable[1].add(new Pair<>(831219996, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1230019370, 0, mH);
		controlTable[2].add(new Pair<>(1230019370, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1472058805, 0, mH);
		controlTable[2].add(new Pair<>(1472058805, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(478779247, 0, mH);
		controlTable[0].add(new Pair<>(478779247, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(310641565, 0, mH);
		controlTable[3].add(new Pair<>(310641565, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1667052547, 0, mH);
		controlTable[1].add(new Pair<>(1667052547, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(680929928, 0, mH);
		controlTable[1].add(new Pair<>(680929928, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1334065060, 0, mH);
		controlTable[1].add(new Pair<>(1334065060, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1917443182, 0, mH);
		controlTable[3].add(new Pair<>(1917443182, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1313799022, 0, mH);
		controlTable[0].add(new Pair<>(1313799022, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(111679639, 0, mH);
		controlTable[2].add(new Pair<>(111679639, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(448537547, 0, mH);
		controlTable[3].add(new Pair<>(448537547, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1272425375, 0, mH);
		controlTable[2].add(new Pair<>(1272425375, 0));
		tableinsertionTester(controlTable, hashtable);
		hashtable.insert(1414147800, 0, mH);
		controlTable[2].add(new Pair<>(1414147800, 0));
		tableinsertionTester(controlTable, hashtable);


	}
	
	void tableinsertionTester(List<Pair<Integer, Integer>>[] compare, Hashtable<Integer, Integer> hashtable) {

		List<Pair<Integer, Integer>>[] table = hashtable.getTable();

		for (int i = 0; i < table.length; i++) {
			assertArrayEquals(compare[i].toArray(), table[i].toArray(), String.format("The internal structure of the hashtable is not as expected. Table index %d is not as expected", i));
		}
	}

	/**
	 * This test uses predefines values to check the module of 2 numbers
	 * Note, this does not check the speed of the modulo
	 */
	@Test
	void fastModulo() {
		assertEquals(36, Hashtable.fastModulo(420, 128));
		assertEquals(85, Hashtable.fastModulo(42069, 256));
		assertEquals(69, Hashtable.fastModulo(35397, 512));
		assertEquals(420, Hashtable.fastModulo(35748, 512) );
		assertEquals(42069, Hashtable.fastModulo(828501, 131072) );
	}

	private void contentTester(int[] keys, List<Pair<Integer,Integer>> vals, Hashtable<Integer,Integer> table) {

		for (int k : keys) {
			assertArrayEquals(vals.stream().filter(x -> x.one()==k).map(x -> x.two()).toArray(), table.findAll(k,mH).toArray(), "Elements in the table dont match the expected elements");
		}
	}

	/**
	 * This test adds certain values to the HashTable  and then removes them
	 * to see if the expected behaviour matches the one required by the question
	 */

	@Test
	void insert() {
		Hashtable<Integer, Integer> table = new Hashtable<>(5, new int[] { 0 });
		List<Pair<Integer, Integer>> vals = new ArrayList<>();
		vals.add(new Pair<>(1,1));
		vals.add(new Pair<>(1,2));
		vals.add(new Pair<>(1,3));
		vals.add(new Pair<>(2,69));
		vals.add(new Pair<>(3,420));
		vals.add(new Pair<>(2,230));

		for (Pair<Integer, Integer> pair : vals) {
			table.insert(pair.one(), pair.two(), mH);
		}

		int[] keys = {1,2,3};

		contentTester(keys, vals, table);

		table.remove(2, mH);
		vals.remove(3);
		vals.remove(4);

		contentTester(keys, vals, table);

		table.insert(2, 420420, mH);
		vals.add(new Pair<>(2, 420420));

		contentTester(keys, vals, table);

		table.stream().forEach(System.out::println);
	}

	/**
	 * This test is taken from SimonK from Zulip
	 * And does a very basic check of adding values with 1 key
	 * and then removing it
	 * A very helpful test to start off with
	 */
	@Test
	public void simonK() {
		Hashtable<Integer, Integer> table = new Hashtable<>(3, new int[] { 0 });
		List<Pair<Integer, Integer>> vals = new ArrayList<>();
		vals.add(new Pair<>(1,1));
		vals.add(new Pair<>(1,2));
		vals.add(new Pair<>(1,3));

		for (Pair<Integer, Integer> pair : vals) {
			table.insert(pair.one(), pair.two(), mH);
		}

		int[] keys = {1};

		contentTester(keys, vals,table);

		table.remove(1, mH);

		List<Pair<Integer,Integer>>[] tableA = table.getTable();
		for (List<Pair<Integer,Integer>> list: tableA) {
			assertEquals(0, list.size(), "Hashtable should be empty after removal of all elements");
		}
	}

	/**
	 * This test uses predefines values to check hash values with a predifines ModuleHelper
	 * This brute forces through all the combinations possible
	 */
	@Test
	public void testH() {

		System.out.println("Testing known hashes:");
		Hashtable<Integer, Integer> table = new Hashtable<>(5, new int[] {1,2,3,4,5,6,7,8,9,10,12,13,69,420,911,1283,123879});

		assertEquals(0, table.h(69, mH));
		assertEquals(0, table.h(420, mH));
		assertEquals(6, table.h(911, mH));
		assertEquals(5, table.h(42069, mH));
		assertEquals(5, table.h(213, mH));
		assertEquals(1, table.h(1281, mH));

		table = new Hashtable<>(101, new int[] {1123,2213,32,41,1,36,741,38,91,1410,112,3,69,420,911,1283,123879,101,132});

		assertEquals(31, table.h(69, mH));
		assertEquals(86, table.h(420, mH));
		assertEquals(64, table.h(911, mH));
		assertEquals(53, table.h(42069, mH));
		assertEquals(75, table.h(213, mH));
		assertEquals(6, table.h(1281, mH));
		assertEquals(68, table.h(269, mH));
		assertEquals(68, table.h(731, mH));
		assertEquals(68, table.h(735, mH));
		assertEquals(68, table.h(739, mH));

		assertEquals(16, table.h(0, mH));
		assertEquals(115, table.h(1, mH));
		assertEquals( 86 , table.h(2, mH));
		assertEquals( 57 , table.h(3, mH));
		assertEquals( 28 , table.h(4, mH));
		assertEquals( 127 , table.h(5, mH));
		assertEquals( 98 , table.h(6, mH));
		assertEquals( 69 , table.h(7, mH));
		assertEquals( 40 , table.h(8, mH));
		assertEquals( 11 , table.h(9, mH));
		assertEquals( 99 , table.h(10, mH));
		assertEquals( 8 , table.h(11, mH));
		assertEquals( 45 , table.h(12, mH));
		assertEquals( 82 , table.h(13, mH));
		assertEquals( 119 , table.h(14, mH));
		assertEquals( 28 , table.h(15, mH));
		assertEquals( 65 , table.h(16, mH));
		assertEquals( 102 , table.h(17, mH));
		assertEquals( 11 , table.h(18, mH));
		assertEquals( 48 , table.h(19, mH));
		assertEquals( 70 , table.h(20, mH));
		assertEquals( 107 , table.h(21, mH));
		assertEquals( 16 , table.h(22, mH));
		assertEquals( 53 , table.h(23, mH));
		assertEquals( 90 , table.h(24, mH));
		assertEquals( 127 , table.h(25, mH));
		assertEquals( 36 , table.h(26, mH));
		assertEquals( 73 , table.h(27, mH));
		assertEquals( 110 , table.h(28, mH));
		assertEquals( 19 , table.h(29, mH));
		assertEquals( 41 , table.h(30, mH));
		assertEquals( 78 , table.h(31, mH));
		assertEquals( 115 , table.h(32, mH));
		assertEquals( 24 , table.h(33, mH));
		assertEquals( 61 , table.h(34, mH));
		assertEquals( 98 , table.h(35, mH));
		assertEquals( 7 , table.h(36, mH));
		assertEquals( 44 , table.h(37, mH));
		assertEquals( 81 , table.h(38, mH));
		assertEquals( 118 , table.h(39, mH));
		assertEquals( 12 , table.h(40, mH));
		assertEquals( 49 , table.h(41, mH));
		assertEquals( 86 , table.h(42, mH));
		assertEquals( 123 , table.h(43, mH));
		assertEquals( 32 , table.h(44, mH));
		assertEquals( 69 , table.h(45, mH));
		assertEquals( 106 , table.h(46, mH));
		assertEquals( 15 , table.h(47, mH));
		assertEquals( 52 , table.h(48, mH));
		assertEquals( 89 , table.h(49, mH));
		assertEquals( 111 , table.h(50, mH));
		assertEquals( 20 , table.h(51, mH));
		assertEquals( 57 , table.h(52, mH));
		assertEquals( 94 , table.h(53, mH));
		assertEquals( 3 , table.h(54, mH));
		assertEquals( 40 , table.h(55, mH));
		assertEquals( 77 , table.h(56, mH));
		assertEquals( 114 , table.h(57, mH));
		assertEquals( 23 , table.h(58, mH));
		assertEquals( 60 , table.h(59, mH));
		assertEquals( 82 , table.h(60, mH));
		assertEquals( 119 , table.h(61, mH));
		assertEquals( 28 , table.h(62, mH));
		assertEquals( 65 , table.h(63, mH));
		assertEquals( 102 , table.h(64, mH));
		assertEquals( 11 , table.h(65, mH));
		assertEquals( 48 , table.h(66, mH));
		assertEquals( 85 , table.h(67, mH));
		assertEquals( 122 , table.h(68, mH));
		assertEquals( 31 , table.h(69, mH));
		assertEquals( 53 , table.h(70, mH));
		assertEquals( 90 , table.h(71, mH));
		assertEquals( 127 , table.h(72, mH));
		assertEquals( 36 , table.h(73, mH));
		assertEquals( 73 , table.h(74, mH));
		assertEquals( 110 , table.h(75, mH));
		assertEquals( 19 , table.h(76, mH));
		assertEquals( 56 , table.h(77, mH));
		assertEquals( 93 , table.h(78, mH));
		assertEquals( 2 , table.h(79, mH));
		assertEquals( 24 , table.h(80, mH));
		assertEquals( 61 , table.h(81, mH));
		assertEquals( 98 , table.h(82, mH));
		assertEquals( 7 , table.h(83, mH));
		assertEquals( 44 , table.h(84, mH));
		assertEquals( 81 , table.h(85, mH));
		assertEquals( 118 , table.h(86, mH));
		assertEquals( 27 , table.h(87, mH));
		assertEquals( 64 , table.h(88, mH));
		assertEquals( 101 , table.h(89, mH));
		assertEquals( 123 , table.h(90, mH));
		assertEquals( 32 , table.h(91, mH));
		assertEquals( 69 , table.h(92, mH));
		assertEquals( 106 , table.h(93, mH));
		assertEquals( 15 , table.h(94, mH));
		assertEquals( 52 , table.h(95, mH));
		assertEquals( 89 , table.h(96, mH));
		assertEquals( 126 , table.h(97, mH));
		assertEquals( 35 , table.h(98, mH));
		assertEquals( 72 , table.h(99, mH));
		assertEquals( 99 , table.h(100, mH));
		assertEquals( 3 , table.h(101, mH));
		assertEquals( 35 , table.h(102, mH));
		assertEquals( 67 , table.h(103, mH));
		assertEquals( 99 , table.h(104, mH));
		assertEquals( 3 , table.h(105, mH));
		assertEquals( 35 , table.h(106, mH));
		assertEquals( 67 , table.h(107, mH));
		assertEquals( 99 , table.h(108, mH));
		assertEquals( 3 , table.h(109, mH));
		assertEquals( 8 , table.h(110, mH));
		assertEquals( 40 , table.h(111, mH));
		assertEquals( 72 , table.h(112, mH));
		assertEquals( 104 , table.h(113, mH));
		assertEquals( 8 , table.h(114, mH));
		assertEquals( 40 , table.h(115, mH));
		assertEquals( 72 , table.h(116, mH));
		assertEquals( 104 , table.h(117, mH));
		assertEquals( 8 , table.h(118, mH));
		assertEquals( 40 , table.h(119, mH));
		assertEquals( 45 , table.h(120, mH));
		assertEquals( 77 , table.h(121, mH));
		assertEquals( 109 , table.h(122, mH));
		assertEquals( 13 , table.h(123, mH));
		assertEquals( 45 , table.h(124, mH));
		assertEquals( 77 , table.h(125, mH));
		assertEquals( 109 , table.h(126, mH));
		assertEquals( 13 , table.h(127, mH));
		assertEquals( 45 , table.h(128, mH));
		assertEquals( 77 , table.h(129, mH));
		assertEquals( 82 , table.h(130, mH));
		assertEquals( 114 , table.h(131, mH));
		assertEquals( 18 , table.h(132, mH));
		assertEquals( 50 , table.h(133, mH));
		assertEquals( 82 , table.h(134, mH));
		assertEquals( 114 , table.h(135, mH));
		assertEquals( 18 , table.h(136, mH));
		assertEquals( 50 , table.h(137, mH));
		assertEquals( 82 , table.h(138, mH));
		assertEquals( 114 , table.h(139, mH));
		assertEquals( 119 , table.h(140, mH));
		assertEquals( 23 , table.h(141, mH));
		assertEquals( 55 , table.h(142, mH));
		assertEquals( 87 , table.h(143, mH));
		assertEquals( 119 , table.h(144, mH));
		assertEquals( 23 , table.h(145, mH));
		assertEquals( 55 , table.h(146, mH));
		assertEquals( 87 , table.h(147, mH));
		assertEquals( 119 , table.h(148, mH));
		assertEquals( 23 , table.h(149, mH));
		assertEquals( 28 , table.h(150, mH));
		assertEquals( 60 , table.h(151, mH));
		assertEquals( 92 , table.h(152, mH));
		assertEquals( 124 , table.h(153, mH));
		assertEquals( 28 , table.h(154, mH));
		assertEquals( 60 , table.h(155, mH));
		assertEquals( 92 , table.h(156, mH));
		assertEquals( 124 , table.h(157, mH));
		assertEquals( 28 , table.h(158, mH));
		assertEquals( 60 , table.h(159, mH));
		assertEquals( 65 , table.h(160, mH));
		assertEquals( 97 , table.h(161, mH));
		assertEquals( 1 , table.h(162, mH));
		assertEquals( 33 , table.h(163, mH));
		assertEquals( 65 , table.h(164, mH));
		assertEquals( 97 , table.h(165, mH));
		assertEquals( 1 , table.h(166, mH));
		assertEquals( 33 , table.h(167, mH));
		assertEquals( 65 , table.h(168, mH));
		assertEquals( 97 , table.h(169, mH));
		assertEquals( 102 , table.h(170, mH));
		assertEquals( 6 , table.h(171, mH));
		assertEquals( 38 , table.h(172, mH));
		assertEquals( 70 , table.h(173, mH));
		assertEquals( 102 , table.h(174, mH));
		assertEquals( 6 , table.h(175, mH));
		assertEquals( 38 , table.h(176, mH));
		assertEquals( 70 , table.h(177, mH));
		assertEquals( 102 , table.h(178, mH));
		assertEquals( 6 , table.h(179, mH));
		assertEquals( 11 , table.h(180, mH));
		assertEquals( 43 , table.h(181, mH));
		assertEquals( 75 , table.h(182, mH));
		assertEquals( 107 , table.h(183, mH));
		assertEquals( 11 , table.h(184, mH));
		assertEquals( 43 , table.h(185, mH));
		assertEquals( 75 , table.h(186, mH));
		assertEquals( 107 , table.h(187, mH));
		assertEquals( 11 , table.h(188, mH));
		assertEquals( 43 , table.h(189, mH));
		assertEquals( 48 , table.h(190, mH));
		assertEquals( 80 , table.h(191, mH));
		assertEquals( 112 , table.h(192, mH));
		assertEquals( 16 , table.h(193, mH));
		assertEquals( 48 , table.h(194, mH));
		assertEquals( 80 , table.h(195, mH));
		assertEquals( 112 , table.h(196, mH));
		assertEquals( 16 , table.h(197, mH));
		assertEquals( 48 , table.h(198, mH));
		assertEquals( 80 , table.h(199, mH));
		assertEquals( 70 , table.h(200, mH));
		assertEquals( 102 , table.h(201, mH));
		assertEquals( 6 , table.h(202, mH));
		assertEquals( 38 , table.h(203, mH));
		assertEquals( 70 , table.h(204, mH));
		assertEquals( 102 , table.h(205, mH));
		assertEquals( 6 , table.h(206, mH));
		assertEquals( 38 , table.h(207, mH));
		assertEquals( 70 , table.h(208, mH));
		assertEquals( 102 , table.h(209, mH));
		assertEquals( 107 , table.h(210, mH));
		assertEquals( 11 , table.h(211, mH));
		assertEquals( 43 , table.h(212, mH));
		assertEquals( 75 , table.h(213, mH));
		assertEquals( 107 , table.h(214, mH));
		assertEquals( 11 , table.h(215, mH));
		assertEquals( 43 , table.h(216, mH));
		assertEquals( 75 , table.h(217, mH));
		assertEquals( 107 , table.h(218, mH));
		assertEquals( 11 , table.h(219, mH));
		assertEquals( 16 , table.h(220, mH));
		assertEquals( 48 , table.h(221, mH));
		assertEquals( 80 , table.h(222, mH));
		assertEquals( 112 , table.h(223, mH));
		assertEquals( 16 , table.h(224, mH));
		assertEquals( 48 , table.h(225, mH));
		assertEquals( 80 , table.h(226, mH));
		assertEquals( 112 , table.h(227, mH));
		assertEquals( 16 , table.h(228, mH));
		assertEquals( 48 , table.h(229, mH));
		assertEquals( 53 , table.h(230, mH));
		assertEquals( 85 , table.h(231, mH));
		assertEquals( 117 , table.h(232, mH));
		assertEquals( 21 , table.h(233, mH));
		assertEquals( 53 , table.h(234, mH));
		assertEquals( 85 , table.h(235, mH));
		assertEquals( 117 , table.h(236, mH));
		assertEquals( 21 , table.h(237, mH));
		assertEquals( 53 , table.h(238, mH));
		assertEquals( 85 , table.h(239, mH));
		assertEquals( 90 , table.h(240, mH));
		assertEquals( 122 , table.h(241, mH));
		assertEquals( 26 , table.h(242, mH));
		assertEquals( 58 , table.h(243, mH));
		assertEquals( 90 , table.h(244, mH));
		assertEquals( 122 , table.h(245, mH));
		assertEquals( 26 , table.h(246, mH));
		assertEquals( 58 , table.h(247, mH));
		assertEquals( 90 , table.h(248, mH));
		assertEquals( 122 , table.h(249, mH));
		assertEquals( 127 , table.h(250, mH));
		assertEquals( 31 , table.h(251, mH));
		assertEquals( 63 , table.h(252, mH));
		assertEquals( 95 , table.h(253, mH));
		assertEquals( 127 , table.h(254, mH));
		assertEquals( 31 , table.h(255, mH));
		assertEquals( 63 , table.h(256, mH));
		assertEquals( 95 , table.h(257, mH));
		assertEquals( 127 , table.h(258, mH));
		assertEquals( 31 , table.h(259, mH));
		assertEquals( 36 , table.h(260, mH));
		assertEquals( 68 , table.h(261, mH));
		assertEquals( 100 , table.h(262, mH));
		assertEquals( 4 , table.h(263, mH));
		assertEquals( 36 , table.h(264, mH));
		assertEquals( 68 , table.h(265, mH));
		assertEquals( 100 , table.h(266, mH));
		assertEquals( 4 , table.h(267, mH));
		assertEquals( 36 , table.h(268, mH));
		assertEquals( 68 , table.h(269, mH));
		assertEquals( 73 , table.h(270, mH));
		assertEquals( 105 , table.h(271, mH));
		assertEquals( 9 , table.h(272, mH));
		assertEquals( 41 , table.h(273, mH));
		assertEquals( 73 , table.h(274, mH));
		assertEquals( 105 , table.h(275, mH));
		assertEquals( 9 , table.h(276, mH));
		assertEquals( 41 , table.h(277, mH));
		assertEquals( 73 , table.h(278, mH));
		assertEquals( 105 , table.h(279, mH));
		assertEquals( 110 , table.h(280, mH));
		assertEquals( 14 , table.h(281, mH));
		assertEquals( 46 , table.h(282, mH));
		assertEquals( 78 , table.h(283, mH));
		assertEquals( 110 , table.h(284, mH));
		assertEquals( 14 , table.h(285, mH));
		assertEquals( 46 , table.h(286, mH));
		assertEquals( 78 , table.h(287, mH));
		assertEquals( 110 , table.h(288, mH));
		assertEquals( 14 , table.h(289, mH));
		assertEquals( 19 , table.h(290, mH));
		assertEquals( 51 , table.h(291, mH));
		assertEquals( 83 , table.h(292, mH));
		assertEquals( 115 , table.h(293, mH));
		assertEquals( 19 , table.h(294, mH));
		assertEquals( 51 , table.h(295, mH));
		assertEquals( 83 , table.h(296, mH));
		assertEquals( 115 , table.h(297, mH));
		assertEquals( 19 , table.h(298, mH));
		assertEquals( 51 , table.h(299, mH));
		assertEquals( 41 , table.h(300, mH));
		assertEquals( 73 , table.h(301, mH));
		assertEquals( 105 , table.h(302, mH));
		assertEquals( 9 , table.h(303, mH));
		assertEquals( 41 , table.h(304, mH));
		assertEquals( 73 , table.h(305, mH));
		assertEquals( 105 , table.h(306, mH));
		assertEquals( 9 , table.h(307, mH));
		assertEquals( 41 , table.h(308, mH));
		assertEquals( 73 , table.h(309, mH));
		assertEquals( 78 , table.h(310, mH));
		assertEquals( 110 , table.h(311, mH));
		assertEquals( 14 , table.h(312, mH));
		assertEquals( 46 , table.h(313, mH));
		assertEquals( 78 , table.h(314, mH));
		assertEquals( 110 , table.h(315, mH));
		assertEquals( 14 , table.h(316, mH));
		assertEquals( 46 , table.h(317, mH));
		assertEquals( 78 , table.h(318, mH));
		assertEquals( 110 , table.h(319, mH));
		assertEquals( 115 , table.h(320, mH));
		assertEquals( 19 , table.h(321, mH));
		assertEquals( 51 , table.h(322, mH));
		assertEquals( 83 , table.h(323, mH));
		assertEquals( 115 , table.h(324, mH));
		assertEquals( 19 , table.h(325, mH));
		assertEquals( 51 , table.h(326, mH));
		assertEquals( 83 , table.h(327, mH));
		assertEquals( 115 , table.h(328, mH));
		assertEquals( 19 , table.h(329, mH));
		assertEquals( 24 , table.h(330, mH));
		assertEquals( 56 , table.h(331, mH));
		assertEquals( 88 , table.h(332, mH));
		assertEquals( 120 , table.h(333, mH));
		assertEquals( 24 , table.h(334, mH));
		assertEquals( 56 , table.h(335, mH));
		assertEquals( 88 , table.h(336, mH));
		assertEquals( 120 , table.h(337, mH));
		assertEquals( 24 , table.h(338, mH));
		assertEquals( 56 , table.h(339, mH));
		assertEquals( 61 , table.h(340, mH));
		assertEquals( 93 , table.h(341, mH));
		assertEquals( 125 , table.h(342, mH));
		assertEquals( 29 , table.h(343, mH));
		assertEquals( 61 , table.h(344, mH));
		assertEquals( 93 , table.h(345, mH));
		assertEquals( 125 , table.h(346, mH));
		assertEquals( 29 , table.h(347, mH));
		assertEquals( 61 , table.h(348, mH));
		assertEquals( 93 , table.h(349, mH));
		assertEquals( 98 , table.h(350, mH));
		assertEquals( 2 , table.h(351, mH));
		assertEquals( 34 , table.h(352, mH));
		assertEquals( 66 , table.h(353, mH));
		assertEquals( 98 , table.h(354, mH));
		assertEquals( 2 , table.h(355, mH));
		assertEquals( 34 , table.h(356, mH));
		assertEquals( 66 , table.h(357, mH));
		assertEquals( 98 , table.h(358, mH));
		assertEquals( 2 , table.h(359, mH));
		assertEquals( 7 , table.h(360, mH));
		assertEquals( 39 , table.h(361, mH));
		assertEquals( 71 , table.h(362, mH));
		assertEquals( 103 , table.h(363, mH));
		assertEquals( 7 , table.h(364, mH));
		assertEquals( 39 , table.h(365, mH));
		assertEquals( 71 , table.h(366, mH));
		assertEquals( 103 , table.h(367, mH));
		assertEquals( 7 , table.h(368, mH));
		assertEquals( 39 , table.h(369, mH));
		assertEquals( 44 , table.h(370, mH));
		assertEquals( 76 , table.h(371, mH));
		assertEquals( 108 , table.h(372, mH));
		assertEquals( 12 , table.h(373, mH));
		assertEquals( 44 , table.h(374, mH));
		assertEquals( 76 , table.h(375, mH));
		assertEquals( 108 , table.h(376, mH));
		assertEquals( 12 , table.h(377, mH));
		assertEquals( 44 , table.h(378, mH));
		assertEquals( 76 , table.h(379, mH));
		assertEquals( 81 , table.h(380, mH));
		assertEquals( 113 , table.h(381, mH));
		assertEquals( 17 , table.h(382, mH));
		assertEquals( 49 , table.h(383, mH));
		assertEquals( 81 , table.h(384, mH));
		assertEquals( 113 , table.h(385, mH));
		assertEquals( 17 , table.h(386, mH));
		assertEquals( 49 , table.h(387, mH));
		assertEquals( 81 , table.h(388, mH));
		assertEquals( 113 , table.h(389, mH));
		assertEquals( 118 , table.h(390, mH));
		assertEquals( 22 , table.h(391, mH));
		assertEquals( 54 , table.h(392, mH));
		assertEquals( 86 , table.h(393, mH));
		assertEquals( 118 , table.h(394, mH));
		assertEquals( 22 , table.h(395, mH));
		assertEquals( 54 , table.h(396, mH));
		assertEquals( 86 , table.h(397, mH));
		assertEquals( 118 , table.h(398, mH));
		assertEquals( 22 , table.h(399, mH));
		assertEquals( 12 , table.h(400, mH));
		assertEquals( 44 , table.h(401, mH));
		assertEquals( 76 , table.h(402, mH));
		assertEquals( 108 , table.h(403, mH));
		assertEquals( 12 , table.h(404, mH));
		assertEquals( 44 , table.h(405, mH));
		assertEquals( 76 , table.h(406, mH));
		assertEquals( 108 , table.h(407, mH));
		assertEquals( 12 , table.h(408, mH));
		assertEquals( 44 , table.h(409, mH));
		assertEquals( 49 , table.h(410, mH));
		assertEquals( 81 , table.h(411, mH));
		assertEquals( 113 , table.h(412, mH));
		assertEquals( 17 , table.h(413, mH));
		assertEquals( 49 , table.h(414, mH));
		assertEquals( 81 , table.h(415, mH));
		assertEquals( 113 , table.h(416, mH));
		assertEquals( 17 , table.h(417, mH));
		assertEquals( 49 , table.h(418, mH));
		assertEquals( 81 , table.h(419, mH));
		assertEquals( 86 , table.h(420, mH));
		assertEquals( 118 , table.h(421, mH));
		assertEquals( 22 , table.h(422, mH));
		assertEquals( 54 , table.h(423, mH));
		assertEquals( 86 , table.h(424, mH));
		assertEquals( 118 , table.h(425, mH));
		assertEquals( 22 , table.h(426, mH));
		assertEquals( 54 , table.h(427, mH));
		assertEquals( 86 , table.h(428, mH));
		assertEquals( 118 , table.h(429, mH));
		assertEquals( 123 , table.h(430, mH));
		assertEquals( 27 , table.h(431, mH));
		assertEquals( 59 , table.h(432, mH));
		assertEquals( 91 , table.h(433, mH));
		assertEquals( 123 , table.h(434, mH));
		assertEquals( 27 , table.h(435, mH));
		assertEquals( 59 , table.h(436, mH));
		assertEquals( 91 , table.h(437, mH));
		assertEquals( 123 , table.h(438, mH));
		assertEquals( 27 , table.h(439, mH));
		assertEquals( 32 , table.h(440, mH));
		assertEquals( 64 , table.h(441, mH));
		assertEquals( 96 , table.h(442, mH));
		assertEquals( 0 , table.h(443, mH));
		assertEquals( 32 , table.h(444, mH));
		assertEquals( 64 , table.h(445, mH));
		assertEquals( 96 , table.h(446, mH));
		assertEquals( 0 , table.h(447, mH));
		assertEquals( 32 , table.h(448, mH));
		assertEquals( 64 , table.h(449, mH));
		assertEquals( 69 , table.h(450, mH));
		assertEquals( 101 , table.h(451, mH));
		assertEquals( 5 , table.h(452, mH));
		assertEquals( 37 , table.h(453, mH));
		assertEquals( 69 , table.h(454, mH));
		assertEquals( 101 , table.h(455, mH));
		assertEquals( 5 , table.h(456, mH));
		assertEquals( 37 , table.h(457, mH));
		assertEquals( 69 , table.h(458, mH));
		assertEquals( 101 , table.h(459, mH));
		assertEquals( 106 , table.h(460, mH));
		assertEquals( 10 , table.h(461, mH));
		assertEquals( 42 , table.h(462, mH));
		assertEquals( 74 , table.h(463, mH));
		assertEquals( 106 , table.h(464, mH));
		assertEquals( 10 , table.h(465, mH));
		assertEquals( 42 , table.h(466, mH));
		assertEquals( 74 , table.h(467, mH));
		assertEquals( 106 , table.h(468, mH));
		assertEquals( 10 , table.h(469, mH));
		assertEquals( 15 , table.h(470, mH));
		assertEquals( 47 , table.h(471, mH));
		assertEquals( 79 , table.h(472, mH));
		assertEquals( 111 , table.h(473, mH));
		assertEquals( 15 , table.h(474, mH));
		assertEquals( 47 , table.h(475, mH));
		assertEquals( 79 , table.h(476, mH));
		assertEquals( 111 , table.h(477, mH));
		assertEquals( 15 , table.h(478, mH));
		assertEquals( 47 , table.h(479, mH));
		assertEquals( 52 , table.h(480, mH));
		assertEquals( 84 , table.h(481, mH));
		assertEquals( 116 , table.h(482, mH));
		assertEquals( 20 , table.h(483, mH));
		assertEquals( 52 , table.h(484, mH));
		assertEquals( 84 , table.h(485, mH));
		assertEquals( 116 , table.h(486, mH));
		assertEquals( 20 , table.h(487, mH));
		assertEquals( 52 , table.h(488, mH));
		assertEquals( 84 , table.h(489, mH));
		assertEquals( 89 , table.h(490, mH));
		assertEquals( 121 , table.h(491, mH));
		assertEquals( 25 , table.h(492, mH));
		assertEquals( 57 , table.h(493, mH));
		assertEquals( 89 , table.h(494, mH));
		assertEquals( 121 , table.h(495, mH));
		assertEquals( 25 , table.h(496, mH));
		assertEquals( 57 , table.h(497, mH));
		assertEquals( 89 , table.h(498, mH));
		assertEquals( 121 , table.h(499, mH));
		assertEquals( 111 , table.h(500, mH));
		assertEquals( 15 , table.h(501, mH));
		assertEquals( 47 , table.h(502, mH));
		assertEquals( 79 , table.h(503, mH));
		assertEquals( 111 , table.h(504, mH));
		assertEquals( 15 , table.h(505, mH));
		assertEquals( 47 , table.h(506, mH));
		assertEquals( 79 , table.h(507, mH));
		assertEquals( 111 , table.h(508, mH));
		assertEquals( 15 , table.h(509, mH));
		assertEquals( 20 , table.h(510, mH));
		assertEquals( 52 , table.h(511, mH));
		assertEquals( 84 , table.h(512, mH));
		assertEquals( 116 , table.h(513, mH));
		assertEquals( 20 , table.h(514, mH));
		assertEquals( 52 , table.h(515, mH));
		assertEquals( 84 , table.h(516, mH));
		assertEquals( 116 , table.h(517, mH));
		assertEquals( 20 , table.h(518, mH));
		assertEquals( 52 , table.h(519, mH));
		assertEquals( 57 , table.h(520, mH));
		assertEquals( 89 , table.h(521, mH));
		assertEquals( 121 , table.h(522, mH));
		assertEquals( 25 , table.h(523, mH));
		assertEquals( 57 , table.h(524, mH));
		assertEquals( 89 , table.h(525, mH));
		assertEquals( 121 , table.h(526, mH));
		assertEquals( 25 , table.h(527, mH));
		assertEquals( 57 , table.h(528, mH));
		assertEquals( 89 , table.h(529, mH));
		assertEquals( 94 , table.h(530, mH));
		assertEquals( 126 , table.h(531, mH));
		assertEquals( 30 , table.h(532, mH));
		assertEquals( 62 , table.h(533, mH));
		assertEquals( 94 , table.h(534, mH));
		assertEquals( 126 , table.h(535, mH));
		assertEquals( 30 , table.h(536, mH));
		assertEquals( 62 , table.h(537, mH));
		assertEquals( 94 , table.h(538, mH));
		assertEquals( 126 , table.h(539, mH));
		assertEquals( 3 , table.h(540, mH));
		assertEquals( 35 , table.h(541, mH));
		assertEquals( 67 , table.h(542, mH));
		assertEquals( 99 , table.h(543, mH));
		assertEquals( 3 , table.h(544, mH));
		assertEquals( 35 , table.h(545, mH));
		assertEquals( 67 , table.h(546, mH));
		assertEquals( 99 , table.h(547, mH));
		assertEquals( 3 , table.h(548, mH));
		assertEquals( 35 , table.h(549, mH));
		assertEquals( 40 , table.h(550, mH));
		assertEquals( 72 , table.h(551, mH));
		assertEquals( 104 , table.h(552, mH));
		assertEquals( 8 , table.h(553, mH));
		assertEquals( 40 , table.h(554, mH));
		assertEquals( 72 , table.h(555, mH));
		assertEquals( 104 , table.h(556, mH));
		assertEquals( 8 , table.h(557, mH));
		assertEquals( 40 , table.h(558, mH));
		assertEquals( 72 , table.h(559, mH));
		assertEquals( 77 , table.h(560, mH));
		assertEquals( 109 , table.h(561, mH));
		assertEquals( 13 , table.h(562, mH));
		assertEquals( 45 , table.h(563, mH));
		assertEquals( 77 , table.h(564, mH));
		assertEquals( 109 , table.h(565, mH));
		assertEquals( 13 , table.h(566, mH));
		assertEquals( 45 , table.h(567, mH));
		assertEquals( 77 , table.h(568, mH));
		assertEquals( 109 , table.h(569, mH));
		assertEquals( 114 , table.h(570, mH));
		assertEquals( 18 , table.h(571, mH));
		assertEquals( 50 , table.h(572, mH));
		assertEquals( 82 , table.h(573, mH));
		assertEquals( 114 , table.h(574, mH));
		assertEquals( 18 , table.h(575, mH));
		assertEquals( 50 , table.h(576, mH));
		assertEquals( 82 , table.h(577, mH));
		assertEquals( 114 , table.h(578, mH));
		assertEquals( 18 , table.h(579, mH));
		assertEquals( 23 , table.h(580, mH));
		assertEquals( 55 , table.h(581, mH));
		assertEquals( 87 , table.h(582, mH));
		assertEquals( 119 , table.h(583, mH));
		assertEquals( 23 , table.h(584, mH));
		assertEquals( 55 , table.h(585, mH));
		assertEquals( 87 , table.h(586, mH));
		assertEquals( 119 , table.h(587, mH));
		assertEquals( 23 , table.h(588, mH));
		assertEquals( 55 , table.h(589, mH));
		assertEquals( 60 , table.h(590, mH));
		assertEquals( 92 , table.h(591, mH));
		assertEquals( 124 , table.h(592, mH));
		assertEquals( 28 , table.h(593, mH));
		assertEquals( 60 , table.h(594, mH));
		assertEquals( 92 , table.h(595, mH));
		assertEquals( 124 , table.h(596, mH));
		assertEquals( 28 , table.h(597, mH));
		assertEquals( 60 , table.h(598, mH));
		assertEquals( 92 , table.h(599, mH));
		assertEquals( 82 , table.h(600, mH));
		assertEquals( 114 , table.h(601, mH));
		assertEquals( 18 , table.h(602, mH));
		assertEquals( 50 , table.h(603, mH));
		assertEquals( 82 , table.h(604, mH));
		assertEquals( 114 , table.h(605, mH));
		assertEquals( 18 , table.h(606, mH));
		assertEquals( 50 , table.h(607, mH));
		assertEquals( 82 , table.h(608, mH));
		assertEquals( 114 , table.h(609, mH));
		assertEquals( 119 , table.h(610, mH));
		assertEquals( 23 , table.h(611, mH));
		assertEquals( 55 , table.h(612, mH));
		assertEquals( 87 , table.h(613, mH));
		assertEquals( 119 , table.h(614, mH));
		assertEquals( 23 , table.h(615, mH));
		assertEquals( 55 , table.h(616, mH));
		assertEquals( 87 , table.h(617, mH));
		assertEquals( 119 , table.h(618, mH));
		assertEquals( 23 , table.h(619, mH));
		assertEquals( 28 , table.h(620, mH));
		assertEquals( 60 , table.h(621, mH));
		assertEquals( 92 , table.h(622, mH));
		assertEquals( 124 , table.h(623, mH));
		assertEquals( 28 , table.h(624, mH));
		assertEquals( 60 , table.h(625, mH));
		assertEquals( 92 , table.h(626, mH));
		assertEquals( 124 , table.h(627, mH));
		assertEquals( 28 , table.h(628, mH));
		assertEquals( 60 , table.h(629, mH));
		assertEquals( 65 , table.h(630, mH));
		assertEquals( 97 , table.h(631, mH));
		assertEquals( 1 , table.h(632, mH));
		assertEquals( 33 , table.h(633, mH));
		assertEquals( 65 , table.h(634, mH));
		assertEquals( 97 , table.h(635, mH));
		assertEquals( 1 , table.h(636, mH));
		assertEquals( 33 , table.h(637, mH));
		assertEquals( 65 , table.h(638, mH));
		assertEquals( 97 , table.h(639, mH));
		assertEquals( 102 , table.h(640, mH));
		assertEquals( 6 , table.h(641, mH));
		assertEquals( 38 , table.h(642, mH));
		assertEquals( 70 , table.h(643, mH));
		assertEquals( 102 , table.h(644, mH));
		assertEquals( 6 , table.h(645, mH));
		assertEquals( 38 , table.h(646, mH));
		assertEquals( 70 , table.h(647, mH));
		assertEquals( 102 , table.h(648, mH));
		assertEquals( 6 , table.h(649, mH));
		assertEquals( 11 , table.h(650, mH));
		assertEquals( 43 , table.h(651, mH));
		assertEquals( 75 , table.h(652, mH));
		assertEquals( 107 , table.h(653, mH));
		assertEquals( 11 , table.h(654, mH));
		assertEquals( 43 , table.h(655, mH));
		assertEquals( 75 , table.h(656, mH));
		assertEquals( 107 , table.h(657, mH));
		assertEquals( 11 , table.h(658, mH));
		assertEquals( 43 , table.h(659, mH));
		assertEquals( 48 , table.h(660, mH));
		assertEquals( 80 , table.h(661, mH));
		assertEquals( 112 , table.h(662, mH));
		assertEquals( 16 , table.h(663, mH));
		assertEquals( 48 , table.h(664, mH));
		assertEquals( 80 , table.h(665, mH));
		assertEquals( 112 , table.h(666, mH));
		assertEquals( 16 , table.h(667, mH));
		assertEquals( 48 , table.h(668, mH));
		assertEquals( 80 , table.h(669, mH));
		assertEquals( 85 , table.h(670, mH));
		assertEquals( 117 , table.h(671, mH));
		assertEquals( 21 , table.h(672, mH));
		assertEquals( 53 , table.h(673, mH));
		assertEquals( 85 , table.h(674, mH));
		assertEquals( 117 , table.h(675, mH));
		assertEquals( 21 , table.h(676, mH));
		assertEquals( 53 , table.h(677, mH));
		assertEquals( 85 , table.h(678, mH));
		assertEquals( 117 , table.h(679, mH));
		assertEquals( 122 , table.h(680, mH));
		assertEquals( 26 , table.h(681, mH));
		assertEquals( 58 , table.h(682, mH));
		assertEquals( 90 , table.h(683, mH));
		assertEquals( 122 , table.h(684, mH));
		assertEquals( 26 , table.h(685, mH));
		assertEquals( 58 , table.h(686, mH));
		assertEquals( 90 , table.h(687, mH));
		assertEquals( 122 , table.h(688, mH));
		assertEquals( 26 , table.h(689, mH));
		assertEquals( 31 , table.h(690, mH));
		assertEquals( 63 , table.h(691, mH));
		assertEquals( 95 , table.h(692, mH));
		assertEquals( 127 , table.h(693, mH));
		assertEquals( 31 , table.h(694, mH));
		assertEquals( 63 , table.h(695, mH));
		assertEquals( 95 , table.h(696, mH));
		assertEquals( 127 , table.h(697, mH));
		assertEquals( 31 , table.h(698, mH));
		assertEquals( 63 , table.h(699, mH));
		assertEquals( 53 , table.h(700, mH));
		assertEquals( 85 , table.h(701, mH));
		assertEquals( 117 , table.h(702, mH));
		assertEquals( 21 , table.h(703, mH));
		assertEquals( 53 , table.h(704, mH));
		assertEquals( 85 , table.h(705, mH));
		assertEquals( 117 , table.h(706, mH));
		assertEquals( 21 , table.h(707, mH));
		assertEquals( 53 , table.h(708, mH));
		assertEquals( 85 , table.h(709, mH));
		assertEquals( 90 , table.h(710, mH));
		assertEquals( 122 , table.h(711, mH));
		assertEquals( 26 , table.h(712, mH));
		assertEquals( 58 , table.h(713, mH));
		assertEquals( 90 , table.h(714, mH));
		assertEquals( 122 , table.h(715, mH));
		assertEquals( 26 , table.h(716, mH));
		assertEquals( 58 , table.h(717, mH));
		assertEquals( 90 , table.h(718, mH));
		assertEquals( 122 , table.h(719, mH));
		assertEquals( 127 , table.h(720, mH));
		assertEquals( 31 , table.h(721, mH));
		assertEquals( 63 , table.h(722, mH));
		assertEquals( 95 , table.h(723, mH));
		assertEquals( 127 , table.h(724, mH));
		assertEquals( 31 , table.h(725, mH));
		assertEquals( 63 , table.h(726, mH));
		assertEquals( 95 , table.h(727, mH));
		assertEquals( 127 , table.h(728, mH));
		assertEquals( 31 , table.h(729, mH));
		assertEquals( 36 , table.h(730, mH));
		assertEquals( 68 , table.h(731, mH));
		assertEquals( 100 , table.h(732, mH));
		assertEquals( 4 , table.h(733, mH));
		assertEquals( 36 , table.h(734, mH));
		assertEquals( 68 , table.h(735, mH));
		assertEquals( 100 , table.h(736, mH));
		assertEquals( 4 , table.h(737, mH));
		assertEquals( 36 , table.h(738, mH));
		assertEquals( 68 , table.h(739, mH));
		assertEquals( 73 , table.h(740, mH));
		assertEquals( 105 , table.h(741, mH));
		assertEquals( 9 , table.h(742, mH));
		assertEquals( 41 , table.h(743, mH));
		assertEquals( 73 , table.h(744, mH));
		assertEquals( 105 , table.h(745, mH));
		assertEquals( 9 , table.h(746, mH));
		assertEquals( 41 , table.h(747, mH));
		assertEquals( 73 , table.h(748, mH));
		assertEquals( 105 , table.h(749, mH));
		assertEquals( 110 , table.h(750, mH));
		assertEquals( 14 , table.h(751, mH));
		assertEquals( 46 , table.h(752, mH));
		assertEquals( 78 , table.h(753, mH));
		assertEquals( 110 , table.h(754, mH));
		assertEquals( 14 , table.h(755, mH));
		assertEquals( 46 , table.h(756, mH));
		assertEquals( 78 , table.h(757, mH));
		assertEquals( 110 , table.h(758, mH));
		assertEquals( 14 , table.h(759, mH));
		assertEquals( 19 , table.h(760, mH));
		assertEquals( 51 , table.h(761, mH));
		assertEquals( 83 , table.h(762, mH));
		assertEquals( 115 , table.h(763, mH));
		assertEquals( 19 , table.h(764, mH));
		assertEquals( 51 , table.h(765, mH));
		assertEquals( 83 , table.h(766, mH));
		assertEquals( 115 , table.h(767, mH));
		assertEquals( 19 , table.h(768, mH));
		assertEquals( 51 , table.h(769, mH));
		assertEquals( 56 , table.h(770, mH));
		assertEquals( 88 , table.h(771, mH));
		assertEquals( 120 , table.h(772, mH));
		assertEquals( 24 , table.h(773, mH));
		assertEquals( 56 , table.h(774, mH));
		assertEquals( 88 , table.h(775, mH));
		assertEquals( 120 , table.h(776, mH));
		assertEquals( 24 , table.h(777, mH));
		assertEquals( 56 , table.h(778, mH));
		assertEquals( 88 , table.h(779, mH));
		assertEquals( 93 , table.h(780, mH));
		assertEquals( 125 , table.h(781, mH));
		assertEquals( 29 , table.h(782, mH));
		assertEquals( 61 , table.h(783, mH));
		assertEquals( 93 , table.h(784, mH));
		assertEquals( 125 , table.h(785, mH));
		assertEquals( 29 , table.h(786, mH));
		assertEquals( 61 , table.h(787, mH));
		assertEquals( 93 , table.h(788, mH));
		assertEquals( 125 , table.h(789, mH));
		assertEquals( 2 , table.h(790, mH));
		assertEquals( 34 , table.h(791, mH));
		assertEquals( 66 , table.h(792, mH));
		assertEquals( 98 , table.h(793, mH));
		assertEquals( 2 , table.h(794, mH));
		assertEquals( 34 , table.h(795, mH));
		assertEquals( 66 , table.h(796, mH));
		assertEquals( 98 , table.h(797, mH));
		assertEquals( 2 , table.h(798, mH));
		assertEquals( 34 , table.h(799, mH));
		assertEquals( 24 , table.h(800, mH));
		assertEquals( 56 , table.h(801, mH));
		assertEquals( 88 , table.h(802, mH));
		assertEquals( 120 , table.h(803, mH));
		assertEquals( 24 , table.h(804, mH));
		assertEquals( 56 , table.h(805, mH));
		assertEquals( 88 , table.h(806, mH));
		assertEquals( 120 , table.h(807, mH));
		assertEquals( 24 , table.h(808, mH));
		assertEquals( 56 , table.h(809, mH));
		assertEquals( 61 , table.h(810, mH));
		assertEquals( 93 , table.h(811, mH));
		assertEquals( 125 , table.h(812, mH));
		assertEquals( 29 , table.h(813, mH));
		assertEquals( 61 , table.h(814, mH));
		assertEquals( 93 , table.h(815, mH));
		assertEquals( 125 , table.h(816, mH));
		assertEquals( 29 , table.h(817, mH));
		assertEquals( 61 , table.h(818, mH));
		assertEquals( 93 , table.h(819, mH));
		assertEquals( 98 , table.h(820, mH));
		assertEquals( 2 , table.h(821, mH));
		assertEquals( 34 , table.h(822, mH));
		assertEquals( 66 , table.h(823, mH));
		assertEquals( 98 , table.h(824, mH));
		assertEquals( 2 , table.h(825, mH));
		assertEquals( 34 , table.h(826, mH));
		assertEquals( 66 , table.h(827, mH));
		assertEquals( 98 , table.h(828, mH));
		assertEquals( 2 , table.h(829, mH));
		assertEquals( 7 , table.h(830, mH));
		assertEquals( 39 , table.h(831, mH));
		assertEquals( 71 , table.h(832, mH));
		assertEquals( 103 , table.h(833, mH));
		assertEquals( 7 , table.h(834, mH));
		assertEquals( 39 , table.h(835, mH));
		assertEquals( 71 , table.h(836, mH));
		assertEquals( 103 , table.h(837, mH));
		assertEquals( 7 , table.h(838, mH));
		assertEquals( 39 , table.h(839, mH));
		assertEquals( 44 , table.h(840, mH));
		assertEquals( 76 , table.h(841, mH));
		assertEquals( 108 , table.h(842, mH));
		assertEquals( 12 , table.h(843, mH));
		assertEquals( 44 , table.h(844, mH));
		assertEquals( 76 , table.h(845, mH));
		assertEquals( 108 , table.h(846, mH));
		assertEquals( 12 , table.h(847, mH));
		assertEquals( 44 , table.h(848, mH));
		assertEquals( 76 , table.h(849, mH));
		assertEquals( 81 , table.h(850, mH));
		assertEquals( 113 , table.h(851, mH));
		assertEquals( 17 , table.h(852, mH));
		assertEquals( 49 , table.h(853, mH));
		assertEquals( 81 , table.h(854, mH));
		assertEquals( 113 , table.h(855, mH));
		assertEquals( 17 , table.h(856, mH));
		assertEquals( 49 , table.h(857, mH));
		assertEquals( 81 , table.h(858, mH));
		assertEquals( 113 , table.h(859, mH));
		assertEquals( 118 , table.h(860, mH));
		assertEquals( 22 , table.h(861, mH));
		assertEquals( 54 , table.h(862, mH));
		assertEquals( 86 , table.h(863, mH));
		assertEquals( 118 , table.h(864, mH));
		assertEquals( 22 , table.h(865, mH));
		assertEquals( 54 , table.h(866, mH));
		assertEquals( 86 , table.h(867, mH));
		assertEquals( 118 , table.h(868, mH));
		assertEquals( 22 , table.h(869, mH));
		assertEquals( 27 , table.h(870, mH));
		assertEquals( 59 , table.h(871, mH));
		assertEquals( 91 , table.h(872, mH));
		assertEquals( 123 , table.h(873, mH));
		assertEquals( 27 , table.h(874, mH));
		assertEquals( 59 , table.h(875, mH));
		assertEquals( 91 , table.h(876, mH));
		assertEquals( 123 , table.h(877, mH));
		assertEquals( 27 , table.h(878, mH));
		assertEquals( 59 , table.h(879, mH));
		assertEquals( 64 , table.h(880, mH));
		assertEquals( 96 , table.h(881, mH));
		assertEquals( 0 , table.h(882, mH));
		assertEquals( 32 , table.h(883, mH));
		assertEquals( 64 , table.h(884, mH));
		assertEquals( 96 , table.h(885, mH));
		assertEquals( 0 , table.h(886, mH));
		assertEquals( 32 , table.h(887, mH));
		assertEquals( 64 , table.h(888, mH));
		assertEquals( 96 , table.h(889, mH));
		assertEquals( 101 , table.h(890, mH));
		assertEquals( 5 , table.h(891, mH));
		assertEquals( 37 , table.h(892, mH));
		assertEquals( 69 , table.h(893, mH));
		assertEquals( 101 , table.h(894, mH));
		assertEquals( 5 , table.h(895, mH));
		assertEquals( 37 , table.h(896, mH));
		assertEquals( 69 , table.h(897, mH));
		assertEquals( 101 , table.h(898, mH));
		assertEquals( 5 , table.h(899, mH));
		assertEquals( 123 , table.h(900, mH));
		assertEquals( 27 , table.h(901, mH));
		assertEquals( 59 , table.h(902, mH));
		assertEquals( 91 , table.h(903, mH));
		assertEquals( 123 , table.h(904, mH));
		assertEquals( 27 , table.h(905, mH));
		assertEquals( 59 , table.h(906, mH));
		assertEquals( 91 , table.h(907, mH));
		assertEquals( 123 , table.h(908, mH));
		assertEquals( 27 , table.h(909, mH));
		assertEquals( 32 , table.h(910, mH));
		assertEquals( 64 , table.h(911, mH));
		assertEquals( 96 , table.h(912, mH));
		assertEquals( 0 , table.h(913, mH));
		assertEquals( 32 , table.h(914, mH));
		assertEquals( 64 , table.h(915, mH));
		assertEquals( 96 , table.h(916, mH));
		assertEquals( 0 , table.h(917, mH));
		assertEquals( 32 , table.h(918, mH));
		assertEquals( 64 , table.h(919, mH));
		assertEquals( 69 , table.h(920, mH));
		assertEquals( 101 , table.h(921, mH));
		assertEquals( 5 , table.h(922, mH));
		assertEquals( 37 , table.h(923, mH));
		assertEquals( 69 , table.h(924, mH));
		assertEquals( 101 , table.h(925, mH));
		assertEquals( 5 , table.h(926, mH));
		assertEquals( 37 , table.h(927, mH));
		assertEquals( 69 , table.h(928, mH));
		assertEquals( 101 , table.h(929, mH));
		assertEquals( 106 , table.h(930, mH));
		assertEquals( 10 , table.h(931, mH));
		assertEquals( 42 , table.h(932, mH));
		assertEquals( 74 , table.h(933, mH));
		assertEquals( 106 , table.h(934, mH));
		assertEquals( 10 , table.h(935, mH));
		assertEquals( 42 , table.h(936, mH));
		assertEquals( 74 , table.h(937, mH));
		assertEquals( 106 , table.h(938, mH));
		assertEquals( 10 , table.h(939, mH));
		assertEquals( 15 , table.h(940, mH));
		assertEquals( 47 , table.h(941, mH));
		assertEquals( 79 , table.h(942, mH));
		assertEquals( 111 , table.h(943, mH));
		assertEquals( 15 , table.h(944, mH));
		assertEquals( 47 , table.h(945, mH));
		assertEquals( 79 , table.h(946, mH));
		assertEquals( 111 , table.h(947, mH));
		assertEquals( 15 , table.h(948, mH));
		assertEquals( 47 , table.h(949, mH));
		assertEquals( 52 , table.h(950, mH));
		assertEquals( 84 , table.h(951, mH));
		assertEquals( 116 , table.h(952, mH));
		assertEquals( 20 , table.h(953, mH));
		assertEquals( 52 , table.h(954, mH));
		assertEquals( 84 , table.h(955, mH));
		assertEquals( 116 , table.h(956, mH));
		assertEquals( 20 , table.h(957, mH));
		assertEquals( 52 , table.h(958, mH));
		assertEquals( 84 , table.h(959, mH));
		assertEquals( 89 , table.h(960, mH));
		assertEquals( 121 , table.h(961, mH));
		assertEquals( 25 , table.h(962, mH));
		assertEquals( 57 , table.h(963, mH));
		assertEquals( 89 , table.h(964, mH));
		assertEquals( 121 , table.h(965, mH));
		assertEquals( 25 , table.h(966, mH));
		assertEquals( 57 , table.h(967, mH));
		assertEquals( 89 , table.h(968, mH));
		assertEquals( 121 , table.h(969, mH));
		assertEquals( 126 , table.h(970, mH));
		assertEquals( 30 , table.h(971, mH));
		assertEquals( 62 , table.h(972, mH));
		assertEquals( 94 , table.h(973, mH));
		assertEquals( 126 , table.h(974, mH));
		assertEquals( 30 , table.h(975, mH));
		assertEquals( 62 , table.h(976, mH));
		assertEquals( 94 , table.h(977, mH));
		assertEquals( 126 , table.h(978, mH));
		assertEquals( 30 , table.h(979, mH));
		assertEquals( 35 , table.h(980, mH));
		assertEquals( 67 , table.h(981, mH));
		assertEquals( 99 , table.h(982, mH));
		assertEquals( 3 , table.h(983, mH));
		assertEquals( 35 , table.h(984, mH));
		assertEquals( 67 , table.h(985, mH));
		assertEquals( 99 , table.h(986, mH));
		assertEquals( 3 , table.h(987, mH));
		assertEquals( 35 , table.h(988, mH));
		assertEquals( 67 , table.h(989, mH));
		assertEquals( 72 , table.h(990, mH));
		assertEquals( 104 , table.h(991, mH));
		assertEquals( 8 , table.h(992, mH));
		assertEquals( 40 , table.h(993, mH));
		assertEquals( 72 , table.h(994, mH));
		assertEquals( 104 , table.h(995, mH));
		assertEquals( 8 , table.h(996, mH));
		assertEquals( 40 , table.h(997, mH));
		assertEquals( 72 , table.h(998, mH));
		assertEquals( 104 , table.h(999, mH));

	}
}
