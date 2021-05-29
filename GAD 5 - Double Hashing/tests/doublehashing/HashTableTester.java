package tests.doublehashing;

import gad.doublehashing.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTester {

    /**
     * Class taken from <a href="#{@link}">{@link https://stackoverflow.com/a/24006293}</a>
     * and modified to generate prime numbers in different ranges
     * @author Aamin
     */
    public static class Prime_Number_Generator {

        /**
         * A function to generate random relatively big prime numbers
         * north of 10007
         * @author Aamin
         * @return integer prime number in the range of [10007, 2147483647]
         */
        public static int getBigPrime() {
            int num;
            Random rand = new Random(); // generate a random number
            num = rand.nextInt(Integer.MAX_VALUE) + 10000;

            while (isNotPrime(num)) {
                num = rand.nextInt(Integer.MAX_VALUE) + 100000;
            }

            return num;
        }

        /**
         * A function to generate random relatively small prime numbers
         * no greater than 10099
         * @author Aamin
         * @return integer prime number in the range of [2, 10099]
         */
        public static int getSmallPrime() {
            int num;
            Random rand = new Random(); // generate a random number
            num = rand.nextInt(10000) + 100;

            while (isNotPrime(num)) {
                num = rand.nextInt(10000) + 100;
            }

            return num;
        }

        /**
         * A function to generate random relatively very small prime numbers
         * no greater than 109, useful for generating arrays of prime size
         * @author Aamin
         * @return integer prime number in the range of [2, 109]
         */
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
         * @author Aamin
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

    /**
     * A method to generate a random string of UTF8 chars of a specified length
     * Code taken from <a href="#{@link}">{@link https://www.baeldung.com/java-random-string}</a>
     * @author Aamin
     * @param length which will generate a string of that exact length
     * @return String of the specified length
     */
    private String generateRandomString(int length) {
        byte[] array = new byte[length]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    /**
     * Method to round off doubles (for the purpose of the outputs) up to 2 decimal places
     * Code taken from <a href="#{@link}">{@link https://stackoverflow.com/a/2808648}</a>
     * @author Aamin
     * @param value to be rounded off
     * @return double the value rounded off up to 2 decimal places
     */
    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Method to display all the statistics of the processed hashes in a hopefully sensible manner
     * In general you should get a variance of less than 5% for integers and 10% of the strings
     * I recommend running the tests multiple times and hope that average of the average of averages is less than the required percentages
     * @author Aamin
     * @param varianceHash the array with the variance from the average hash value
     * @param varianceHashTick the array with the variance from the average hash tick value
     * @param prime the size of the array (which is a prime number)
     * @param isInt boolean specifier to see if the variance calculated is for a Int or String
     */
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
        double maxHashDeviation = Math.abs(round(Arrays.stream(distributionHash).summaryStatistics().getMax()));
        double minHashDeviation = Math.abs(round(Arrays.stream(distributionHash).summaryStatistics().getMin()));

        double averageMeanHashTickDeviation = Math.abs(round(Arrays.stream(Arrays.copyOfRange(distributionHashTick, 1, prime-1)).summaryStatistics().getAverage()));
        double maxHashTickDeviation = Math.abs(round(Arrays.stream(Arrays.copyOfRange(distributionHashTick, 1, prime-1)).summaryStatistics().getMax()));
        double minHashTickDeviation = Math.abs(round(Arrays.stream(Arrays.copyOfRange(distributionHashTick, 1, prime-1)).summaryStatistics().getMin()));


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
        System.out.println("Minimum hash deviation      = " + minHashDeviation + "%");
        System.out.println("Maximum hash deviation      = " + maxHashDeviation + "%");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Statistical Analysis for Hash Tick");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Mean hash tick deviation array   = " + Arrays.toString(offsetHashTick));
        System.out.println("Hash Tick Deviation in %         = " + Arrays.toString(distributionHashTick));
        System.out.println("Average mean hash tick deviation = " + averageMeanHashTickDeviation + "%");
        System.out.println("Minimum hash tick deviation      = " + minHashTickDeviation + "%");
        System.out.println("Maximum hash tick deviation      = " + maxHashTickDeviation + "%");

        double lim = isInt?5.0:10.0;

        assertTrue(maxHashDeviation < lim, String.format("Your maximum deviation of hash() of %5.2f%% is greater than %1.0f%%!", maxHashDeviation, lim));
        assertTrue(maxHashTickDeviation < lim, String.format("Your maximum deviation of hashTick() of %5.2f%% is greater than %1.0f%%!", maxHashDeviation, lim));


	//NOTE : IF THESE TESTS FAIL, TRY RUNNING IT AGAIN, IT'S WEIRD! I KNOW, BUT I MIGHT NOT HAVE IMPLEMENTED THEM CORRECTLY
	//Normally, you need a big sample size, for these tests to give accurate results (if they are correct in the first place that is)
	//If you fail these tests consistently but pass those on Artemis, lemme know, I'll fix it.
	//If this part of the test annoys you, just comment it out
    }

    /**
     * A Test which runs millions of random numbers and sees the average variance of the hashed
     * In general, the variance should be less than 5% (according to my algorithm)
     * Run the tests multiple times to attain assurance
     * @author Aamin
     * @throws java.lang.IndexOutOfBoundsException when you return an illegal hash value
     */
    @Test
    public void testHashingInt() {
        //This test is taken from Maximilian from Zulip and modified a bit to be more random and test both hash and hash tick
        int prime = Prime_Number_Generator.getVerySmallPrime();
        DoubleHashInt test = new DoubleHashInt(prime);
        int[] varianceHash = new int[prime];
        int[] varianceHashTick = new int[prime];

        for (int i = -100; i < 1000001; i++) {
            try {
                varianceHash[test.hash(i)]++;
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                fail("An exception was thrown! This means your hash returned an illegal value. aKa < 0 || >= table size");
            }
        }

        for (int i = -100; i < 1000001; i++) {
            try {
                varianceHashTick[test.hashTick(i)]++; // hash() function of DoubleHashInt
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                fail("An exception was thrown! This means your hash returned an illegal value. aKa < 0 || >= table size");
            }
        }

        assertEquals(0, varianceHashTick[0],"Your HashTick returned a value of 0, according to the lecture script formula, this should not have happened!" +
                    "NOTE: This does not guarantee, that your hash tick may actually be wrong! Artemis tests are final tests" +
                    "Because double hashing scales along h' a case of h'(x) = 0 would not resolve collisions");

        printStatistics(varianceHash, varianceHashTick, prime, true);
    }

    /**
     * A Test which 100K+ of random strings and sees the average variance of the hashed values of the strings
     * In general, the variance should be less than 10% (according to my algorithm)
     * Run the tests multiple times to attain assurance
     * @author Aamin
     * @throws java.lang.IndexOutOfBoundsException when you return an illegal hash value
     */
    @Test
    public void testHashingString() {
        //NOTE THIS TEST IS EXTREMELY SLOW (takes roughly 15s on my computer)
        //but I couldn't care less, so just wait, it's still faster than artemis
        int prime = Prime_Number_Generator.getVerySmallPrime();
        DoubleHashString test = new DoubleHashString(prime);
        int[] varianceHash = new int[prime];
        int[] varianceHashTick = new int[prime];

        for (int i = -100; i < 100001; i++) {
            try {
                varianceHash[test.hash(generateRandomString(prime))]++;
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                fail("An exception was thrown! This means your hash returned an illegal value. aKa < 0 || >= table size");
            }
        }

        for (int i = -100; i < 100001; i++) {
            try {
                varianceHashTick[test.hashTick(generateRandomString(prime))]++; // hash() function of DoubleHashInt
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                fail("An exception was thrown! This means your hash returned an illegal value. aKa < 0 || >= table size");
            }
        }

        assertEquals(0, varianceHashTick[0], "Your HashTick returned a value of 0, according to the lecture script formula, this should not have happened!" +
                    "NOTE: This does not guarantee, that your hash tick may actually be wrong! Artemis tests are final tests" +
                    "Because double hashing scales along h' a case of h'(x) = 0 would not resolve collisions");

        printStatistics(varianceHash, varianceHashTick, prime, false);
    }


    /**
     * A test to check your hashing algorithm of integers against the expected values
     * IF YOU HAVE USED THE EXACT ALGORITHM MENTIONS IN THE LECTURE + SLIDES
     * NOTE: YOU DO NOT HAVE TO HAVE PASSED. YOU CAN ALSO FAIL THIS TEST EVEN WITH A VALID HASHING ALGORITHM
     * IT ONLY WORKS IF YOUR HASHING ALGORITHM IS 100% SIMILAR TO THE ONE FROM THE LECTURE + SLIDES
     * @author Aamin
     */
    @Test
    public void DoubleHashTableHashInteger() {
        DoubleHashTable<Integer, String> table = new DoubleHashTable<>(17, new IntHashableFactory());

        assertEquals(2, table.hash(420, 69));
        assertEquals(2, table.hash(69, 69));
        assertEquals(6, table.hash(420, 420));
        assertEquals(10, table.hash(1233, 213));
        assertEquals(0, table.hash(1, 2));
        assertEquals(1, table.hash(-1, 2));
        assertEquals(11, table.hash(0, 0));
        assertEquals(12, table.hash(-6, 9));
        assertEquals(12, table.hash(0, 69));
        assertEquals(13, table.hash(69, 0));
        assertEquals(6, table.hash(91142069, 0));
        assertEquals(12, table.hash(91142069, 1));
        assertEquals(1, table.hash(91142069, 2));
        assertEquals(7, table.hash(91142069, 3));
        assertEquals(13, table.hash(91142069, 4));
        assertEquals(2, table.hash(91142069, 5));
        assertEquals(0, table.hash(69, 420));

    }

    /**
     * A test to check your hashing algorithm of strings against the expected values
     * IF YOU HAVE USED THE EXACT ALGORITHM MENTIONS IN THE LECTURE + SLIDES
     * NOTE: YOU DO NOT HAVE TO HAVE PASSED. YOU CAN ALSO FAIL THIS TEST EVEN WITH A VALID HASHING ALGORITHM
     * IT ONLY WORKS IF YOUR HASHING ALGORITHM IS 100% SIMILAR TO THE ONE FROM THE LECTURE + SLIDES
     * @author Aamin
     */
    @Test
    public void DoubleHashTableHashString() {
        DoubleHashTable<String, String> table = new DoubleHashTable<>(97, new StringHashableFactory());

        assertEquals(8, table.hash("PGDP", 420));
        assertEquals(71, table.hash("Bruh this is really overkill", 69));
        assertEquals(41, table.hash("Artemis tests are really hard", 12));
        assertEquals(2, table.hash("696969696969696969696969696969696969", 420));
        assertEquals(25, table.hash("69", 69));
        assertEquals(23, table.hash("420", 420));
        assertEquals(23, table.hash("nice", 1));
        assertEquals(69, table.hash("nice", 57));
        assertEquals(69, table.hash("69", 96));
        assertEquals(70, table.hash("Yes I 9 years old and I find these values very funny", 42091169));
    }


    /**
     * Test to try adding random Integer values and see if that table can add the integers
     * regarding the size constrains. Also sees if the table was able to retrieve the added value after adding
     * Additionally, it checks the number of collisions in the hashing algorithm and the number of maxRehashes
     * NOTE: If you fail this test, it can also mean that your hashing method may by wrong, but there's a 60% chance
     * that you've messed insert or a 35% chance that you've messed up get()
     * @author Aamin
     */
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

    /**
     * Test to try adding random strings (intentionally non ASCII) values and see if that table can add the integers
     * regarding the size constrains. Also sees if the table was able to retrieve the added value after adding
     * Additionally, it checks the number of collisions in the hashing algorithm and the number of maxRehashes
     * NOTE: If you fail this test, it can also mean that your hashing method may by wrong, but there's a 60% chance
     * that you've messed insert or a 35% chance that you've messed up get()
     * NOTE NOTE: You may also fail the tests because of the non ASCII characters, I just wanted to spice things up, lol sorry
     * @author Aamin
     */
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
			Optional<Integer> opt = x.find(y);
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
