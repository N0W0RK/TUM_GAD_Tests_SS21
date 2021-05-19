package tests.doublehashing;

import gad.doublehashing.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTests {

    public static class Prime_Number_Generator {

        public static int getBigPrime() {
            int num;
            Random rand = new Random(); // generate a random number
            num = rand.nextInt(Integer.MAX_VALUE) + 10000;

            while (isNotPrime(num)) {
                num = rand.nextInt(Integer.MAX_VALUE) + 100000;
            }

            return num;
        }

        public static int getSmallPrime() {
            int num;
            Random rand = new Random(); // generate a random number
            num = rand.nextInt(10000) + 100;

            while (isNotPrime(num)) {
                num = rand.nextInt(10000) + 100;
            }

            return num;
        }

        public static int getVerySmallPrime() {
            int num;
            Random rand = new Random(); // generate a random number
            num = rand.nextInt(100) + 10;

            while (isNotPrime(num)) {
                num = rand.nextInt(100) + 10;
            }

            return num;
        }

        /**
         * Checks to see if the requested value is prime.
         */

        private static boolean isNotPrime(int inputNum){
            if (inputNum <= 3 || inputNum % 2 == 0)
                return inputNum != 2 && inputNum != 3; //this returns false if number is <=1 & true if number = 2 or 3
            int divisor = 3;
            while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0))
                divisor += 2; //iterates through all possible divisors
            return inputNum % divisor == 0; //returns true/false
        }
    }

    private String generateRandomString(int length) {
        byte[] array = new byte[length]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private void printStatistics(int[] varianceHash, int[] varianceHashTick, int prime, boolean isInt) {
        int[] offsetHash = new int[prime];
        double[] distributionHash = new double[prime];

        int[] offsetHashTick = new int[prime];
        double[] distributionHashTick = new double[prime];

        long sumHash = Arrays.stream(varianceHash).sum();
        int averageHash = (int) (sumHash / prime);

        long sumHashTick = Arrays.stream(varianceHashTick).sum();
        int averageHashTick = (int) (sumHashTick / prime);


        for (int i = 0; i < prime; i++) {
            offsetHash[i] = varianceHash[i] - averageHash;
            offsetHashTick[i] = varianceHashTick[i] - averageHashTick;
        }

        for (int i = 0; i < prime; i++) {
            if (varianceHash[i] == 0)
                distributionHash[i] = 0.0;
            else
                distributionHash[i] = round((((double) offsetHash[i] / varianceHash[i]) * 100.0));

            if (varianceHashTick[i] == 0)
                distributionHashTick[i] = 0.0;
            else
                distributionHashTick[i] = round((((double) offsetHashTick[i] / varianceHashTick[i]) * 100.0));
        }

        double averageMeanHashDeviation = Math.abs(round(Arrays.stream(distributionHash).summaryStatistics().getAverage()));
        double averageMeanHashTickDeviation = Math.abs(round(Arrays.stream(distributionHashTick).summaryStatistics().getAverage()));

        System.out.println("Sum of the hashes     = " + sumHash);
        System.out.println("Average value of hash = " + averageHash);
        System.out.println("Normal Hash           = " + Arrays.toString(varianceHash));
        System.out.println("Hash Tick             = " + Arrays.toString(varianceHashTick));
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Statistical Analysis for Normal Hash");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Mean hash deviation array        = " + Arrays.toString(offsetHash));
        System.out.println("Hash Deviation in %              = " + Arrays.toString(distributionHash));
        System.out.println("Average mean hash deviation      = " + averageMeanHashDeviation + "%");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Statistical Analysis for Hash Tick");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Mean hash tick deviation array   = " + Arrays.toString(offsetHashTick));
        System.out.println("Hash Tick Deviation in %         = " + Arrays.toString(distributionHashTick));
        System.out.println("Average mean hash tick deviation = " + averageMeanHashTickDeviation + "%");

        if (isInt) {
            if (averageMeanHashDeviation > 5.0)
                fail("The average mean deviation of hash() is greater than 5%!");
            else if (averageMeanHashTickDeviation > 5.0)
                fail("The average mean deviation of hashTick() is greater than 5%!");
        } else {
            if (averageMeanHashDeviation > 10.0)
                fail("The average mean deviation of hash() is greater than 5%!");
            else if (averageMeanHashTickDeviation > 10.0)
                fail("The average mean deviation of hashTick() is greater than 5%!");
        }
	//NOTE : IF THESE TESTS FAIL, TRY RUNNING IT AGAIN, IT'S WEIRD! I KNOW, BUT I MIGHT NOT HAVE IMPLEMENTED THEM CORRECTLY
	//Normally, you need a big sample size, for these tests to give accurate results (if they are correct in the first place that is)
	//If you fail these tests consistently but pass those on Artemis, lemme know, I'll fix it.
	//If this part of the test annoys you, just comment it out
    }

    @Test
    public void testHashingInt() {
        //This test is taken from Maximilian from Zulip and modified a bit to be more random and test both hash and hash tick
        int prime = Prime_Number_Generator.getVerySmallPrime();
        DoubleHashInt test = new DoubleHashInt(prime);
        int[] varianceHash = new int[prime];
        int[] varianceHashTick = new int[prime];

        for (int i = -100; i < 100001; i++) {
            try {
                varianceHash[test.hash(i)]++;
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                fail("An exception was thrown! This means your hash returned an illegal value. aKa < 0 || >= table size");
            }
        }

        for (int i = -100; i < 100001; i++) {
            try {
                varianceHashTick[test.hashTick(i)]++; // hash() function of DoubleHashInt
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                fail("An exception was thrown! This means your hash returned an illegal value. aKa < 0 || >= table size");
            }
        }

        if (varianceHashTick[0] != 0)
            fail("Your HashTick returned a value of 0, according to the lecture script formula, this should not have happened!" +
                    "NOTE: This does not guarantee, that your hash tick may actually be wrong! Artemis tests are final tests" +
                    "This is just an assumption based on the formula of the lecture script");

        printStatistics(varianceHash, varianceHashTick, prime, true);
    }

    @Test
    public void testHashingString() {
        //NOTE THIS TEST IS EXTREMELY SLOW (takes roughly 15s on my computer)
        //but I couldn't care less, so just wait, it's still faster than artemis
        int prime = Prime_Number_Generator.getVerySmallPrime();
        DoubleHashString test = new DoubleHashString(prime);
        int[] varianceHash = new int[prime];
        int[] varianceHashTick = new int[prime];

        for (int i = -100; i < 10001; i++) {
            try {
                varianceHash[test.hash(generateRandomString(prime))]++;
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                fail("An exception was thrown! This means your hash returned an illegal value. aKa < 0 || >= table size");
            }
        }

        for (int i = -100; i < 10001; i++) {
            try {
                varianceHashTick[test.hashTick(generateRandomString(prime))]++; // hash() function of DoubleHashInt
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                fail("An exception was thrown! This means your hash returned an illegal value. aKa < 0 || >= table size");
            }
        }

        if (varianceHashTick[0] != 0)
            fail("Your HashTick returned a value of 0, according to the lecture script formula, this should not have happened!" +
                    "NOTE: This does not guarantee, that your hash tick may actually be wrong! Artemis tests are final tests" +
                    "This is just an assumption based on the formula of the lecture script");

        printStatistics(varianceHash, varianceHashTick, prime, false);
    }

    @Test
    public void DoubleHashIntegerTable() {
        DoubleHashTable<Integer, Integer> doubleHashTable = new DoubleHashTable<>(67, new IntHashableFactory());

        assertEquals(Optional.empty(), doubleHashTable.find(42));

        assertTrue(doubleHashTable.insert(42, 50));

        assertEquals(50, doubleHashTable.find(42).get().intValue());

        assertTrue(doubleHashTable.insert(42, 60));
        assertTrue(doubleHashTable.insert(42, 13));

        assertEquals(0, doubleHashTable.collisions());
        assertEquals(0, doubleHashTable.maxRehashes());
        assertEquals(13, doubleHashTable.find(42).get().intValue());

        assertTrue(doubleHashTable.insert(42, 14908));
        assertTrue(doubleHashTable.insert(42, 2103980));

        assertEquals(2103980, doubleHashTable.find(42).get().intValue());

        assertTrue(doubleHashTable.insert(0, 1231));
        assertTrue(doubleHashTable.insert(1231, 1231));

        assertEquals(0, doubleHashTable.maxRehashes());
        assertEquals(0, doubleHashTable.collisions());

        assertEquals(1231, doubleHashTable.find(0).get().intValue());
        assertEquals(1231, doubleHashTable.find(1231).get().intValue());

        for (int i = 0; i < 66; i++) {
            assertTrue(doubleHashTable.insert(i, i));
        }

        assertFalse(doubleHashTable.insert(69, 69));


        assertEquals(55, doubleHashTable.find(55).get().intValue());
        assertEquals(1231, doubleHashTable.find(1231).get().intValue());
        assertEquals(0, doubleHashTable.find(0).get().intValue());



    }

    @Test
    public void DoubleHashStringTable() {
        DoubleHashTable<String, String> doubleHashTable = new DoubleHashTable<>(67, new StringHashableFactory());
        assertTrue(doubleHashTable.insert("ääääääää", "üüüüüüüüüüüü"));
        assertTrue(doubleHashTable.insert("öööööööö", "ääääääääääää"));
        assertTrue(doubleHashTable.insert("üüüüüüüü", "öööööööööööö"));
        assertTrue(doubleHashTable.insert("Ыыыыыыыы", "ßßßßßßßßßßßß"));
        assertTrue(doubleHashTable.insert("ääääääääääää", "öööööööööööö"));

        assertNotEquals("ßßßßßßßßßßßßß", doubleHashTable.find("Ыыыыыыыы").get());
        assertEquals("ßßßßßßßßßßßß", doubleHashTable.find("Ыыыыыыыы").get());
        assertTrue(doubleHashTable.insert("Ыыыыыыыы", "Я не гаварю па-русски"));
        assertNotEquals("ßßßßßßßßßßßß", doubleHashTable.find("Ыыыыыыыы").get());
        assertEquals("Я не гаварю па-русски", doubleHashTable.find("Ыыыыыыыы").get());


        assertNotEquals("öööööööö", doubleHashTable.find("ääääääääääää"));
        assertTrue(doubleHashTable.insert("И донт кнов вхат то сав хир", "ääääääääääää"));

        assertTrue(doubleHashTable.insert("key", "value"));
        assertTrue(doubleHashTable.insert("key", "value"));
        assertEquals("value", doubleHashTable.find("key").get());

        assertTrue(doubleHashTable.insert("finally", "english"));

        for (int i = 0; i < 59; i++) {
            assertTrue(doubleHashTable.insert(String.valueOf(i), String.valueOf(i)));
        }

        assertFalse(doubleHashTable.insert("I should not be able to insert this", "value"));
    }
	
	ArrayList<Integer> usedInts = new ArrayList<>();

	public int uniqueRandomInt() {
		int x = ThreadLocalRandom.current().nextInt(0, 900);
		while (usedInts.contains(x))
			x = ThreadLocalRandom.current().nextInt(0, 900);
		usedInts.add(x);
		return x;
	}

	@Test
	public void testInsertAndFind() { //bis jetzt nur mit ints
		int size = 157;
		DoubleHashTable<Integer, Integer> x = new DoubleHashTable<>(size, new IntHashableFactory());
		Map<Integer, Integer> success = new HashMap<Integer, Integer>();
		for (int i = 0; i < size; i++) {
			int one = uniqueRandomInt();
			int two = ThreadLocalRandom.current().nextInt(0, 900);
			if (x.insert(one, two))
				success.put(one, two);
		}
		for (int i = 0; i < 500; i++) {
			int one = ThreadLocalRandom.current().nextInt(0, 900);
			int two = ThreadLocalRandom.current().nextInt(0, 900);
			if (x.insert(one, two))
				success.put(one, two);
		}
		System.out.println("inserted " + success);

		for (Integer y : success.keySet()) {
			System.out.println();
			System.out.println("searching for: " + y);
			if (x.find(y).equals(Optional.empty())) {
				System.out.println("got " + x.find(y) + ", should be " + success.get(y));

			} else {
				System.out.println("got " + x.find(y).get() + ", should be " + success.get(y));

			}
			assertTrue(x.find(y).get().equals(success.get(y)));
		}
		System.out.println("success");
	}
}
