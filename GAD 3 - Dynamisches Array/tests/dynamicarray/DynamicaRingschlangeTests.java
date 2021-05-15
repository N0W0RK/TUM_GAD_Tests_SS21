package tests.dynamicarray;

import gad.dynamicarray.*;
import gad.dynamicarray.Interval.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests mainly translated from Artemis examples
 * @Author Konrad Gößmann
 */
public class DynamicaRingschlangeTests {

	/**
	 * Tests examples from the exercise statement for the dynamic array
	 */
	@Test
	public void dynamicArrayExamples() {

		TestResult result = new TestResult();

		DynamicArray dynArr = new DynamicArray(3,4);

		dynArr.reportArray(result);
		assertArrayEquals(new int[0], result.getArray(), "Array not initialized empty");

		Interval interval = dynArr.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 1);
		assertEquals(Interval.EmptyInterval.getEmptyInterval(), interval, "Returned wrong interval");
		dynArr.reportArray(result);
		assertArrayEquals(new int[]{0,0,0}, result.getArray(), "Elements do not match expected");

		dynArr.set(2,1);
		dynArr.reportArray(result);
		assertArrayEquals(new int[]{0,0,1}, result.getArray(), "Elements do not match expected");

		assertEquals(0, dynArr.get(1), "get(int) returned wrong value");

		dynArr.set(0,3);
		dynArr.reportArray(result);
		assertArrayEquals(new int[]{3,0,1}, result.getArray(), "Elements do not match expected");

		int[] stdArray = {0,1,0,0,0,0,0,0,0,0,0,0};

		interval = dynArr.reportUsage(new Interval.NonEmptyInterval(1,2), 4);
		assertEquals(new Interval.NonEmptyInterval(0, 1), interval, "Returned wrong interval");
		dynArr.reportArray(result);
		assertArrayEquals(stdArray, result.getArray(), "Elements do not match expected");

		interval = dynArr.reportUsage(new Interval.NonEmptyInterval(3,6), 4);
		assertEquals(new Interval.NonEmptyInterval(3, 6), interval, "Returned wrong interval");
		dynArr.reportArray(result);
		assertArrayEquals(stdArray, result.getArray(), "Elements do not match expected");

		interval = dynArr.reportUsage(new Interval.NonEmptyInterval(1,1), 1);
		assertEquals(new Interval.NonEmptyInterval(0, 0), interval, "Returned wrong interval");
		dynArr.reportArray(result);
		assertArrayEquals(new int[] {1,0,0}, result.getArray(), "Elements do not match expected");

		interval = dynArr.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 2);
		assertEquals(Interval.EmptyInterval.getEmptyInterval(), interval, "Returned wrong interval");
		dynArr.reportArray(result);
		assertArrayEquals(new int[] {1,0,0}, result.getArray(), "Elements do not match expected");

		interval = dynArr.reportUsage(new Interval.NonEmptyInterval(2,0), 4);
		assertEquals(new Interval.NonEmptyInterval(0, 1), interval, "Returned wrong interval");
		dynArr.reportArray(result);
		assertArrayEquals(stdArray, result.getArray(), "Elements do not match expected");

		interval = dynArr.reportUsage(new Interval.NonEmptyInterval(5,1), 9);
		assertEquals(new Interval.NonEmptyInterval(5, 1), interval, "Returned wrong interval");
		dynArr.reportArray(result);
		assertArrayEquals(stdArray, result.getArray(), "Elements do not match expected");

		interval = dynArr.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
		assertEquals(Interval.EmptyInterval.getEmptyInterval(), interval, "Returned wrong interval");
		dynArr.reportArray(result);
		assertArrayEquals(new int[0], result.getArray(), "Elements do not match expected");
	}



	/**
	 * Tests from https://zulip.in.tum.de/#narrow/stream/419-GAD-E03-Dynamisches.20Array/topic/Tests/near/176136
	 * @Author Elias Singer
	 */

	@Test
	void test() {

		try {
			new DynamicArray(0, -1);
			fail("No IllegalArgumentException was thrown");
		}catch(IllegalArgumentException ignored) {}

		//grothFactor = 3;
		//maxOverhead = 4
		DynamicArray dyAr = new DynamicArray(3, 4);

		//minSize = 1 -> was 0 -> make 3*1 = 3
		dyAr.reportUsage(EmptyInterval.getEmptyInterval(), 1);
		assertEquals(3, dyAr.getLength());
		assertEquals("[0, 0, 0]", dyAr.toString());

		//minSize = 4 -> make 4* 3 = 12
		dyAr.reportUsage(new NonEmptyInterval(0, 2), 4);
		assertEquals(12, dyAr.getLength());
		assertEquals("[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", dyAr.toString());

		Interval interval = dyAr.reportUsage(EmptyInterval.getEmptyInterval(), 1);
		assertEquals(3, dyAr.getLength());
		assertEquals("[]", interval.toString());

		interval = dyAr.reportUsage(EmptyInterval.getEmptyInterval(), 1);
		assertEquals(3, dyAr.getLength());
		assertEquals("[]", interval.toString());

	}

	@Test
	void defaultTest() {

		DynamicArray myArray = new DynamicArray(3, 4);
		assertEquals("[]", myArray.toString());

		myArray.reportUsage(EmptyInterval.getEmptyInterval(), 1);
		assertEquals("[0, 0, 0]", myArray.toString());

		myArray.set(2, 1);
		assertEquals("[0, 0, 1]", myArray.toString());

		myArray.set(0, 3);
		assertEquals("[3, 0, 1]", myArray.toString());

		Interval interval = myArray.reportUsage(new NonEmptyInterval(1, 2), 4);
		assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", myArray.toString());
		assertEquals("[0; 1]", interval.toString());

		interval = myArray.reportUsage(new NonEmptyInterval(3, 6), 4);
		assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", myArray.toString());
		assertEquals("[3; 6]", interval.toString());

		interval = myArray.reportUsage(new NonEmptyInterval(1, 1), 1);
		assertEquals("[1, 0, 0]", myArray.toString());
		assertEquals("[0; 0]", interval.toString());

		interval = myArray.reportUsage(new NonEmptyInterval(2, 0), 4);
		assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", myArray.toString());
		assertEquals("[0; 1]", interval.toString());
	}

	/* End of Zulip Tests */

	/**
	 * Tests examples from the exercise statement for the dynamic stack
	 */
	@Test
	public void dynamicStackExamples() {

		TestResult result = new TestResult();

		DynamicStack stack = new DynamicStack(3,4,result);

		assertNull( result.getArray(), "Array should be empty after initialization");

		int[] cmpArr = {1,0,0};

		stack.insert(1);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 1 to be inserted at the beginning of the Array");

		cmpArr[1] = 2;
		stack.insert(2);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 2 to be inserted at index 1");

		cmpArr[2] = 3;
		stack.insert(3);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 3 to be inserted at index 2");

		cmpArr = new int[]{1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0};
		stack.insert(4);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 4 to be inserted at index 3");


		assertEquals(4, stack.remove(), "Returned wrong value from the top of the stack");
		assertArrayEquals(cmpArr, result.getArray(), "Expected Array to be unchanged");

		cmpArr[3] = 5;
		stack.insert(5);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 5 to be inserted at index 3");

		assertEquals(5, stack.remove(), "Returned wrong value from the top of the stack");
		assertArrayEquals(cmpArr, result.getArray(), "Expected Array to be unchanged");

		assertEquals(3, stack.remove(), "Returned wrong value from the top of the stack");
		cmpArr = new int[]{1,2,0,0,0,0};
		assertArrayEquals(cmpArr, result.getArray(), "Expected Array to be reduced to lower size and only contain 1 and 2");
	}

	@Test
	public void removeFromEmptyStack() {

		DynamicStack stack = new DynamicStack(3, 4, new TestResult());
		assertThrows(IndexOutOfBoundsException.class, stack::remove,"Removing from empty Stack should not work");
	}

	@Test
	public void dynamicRingQueueExamples() {

		TestResult result = new TestResult();
		RingQueue queue = new RingQueue(3,4,result);
		assertNull(result.getArray(), "There should not have been any modifications to the array yet");

		int[] cmpArr = {1,0,0};

		queue.insert(1);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 1 to be inserted at index 0");

		cmpArr[1] = 2;
		queue.insert(2);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 2 to be inserted at index 1");

		cmpArr[2] = 3;
		queue.insert(3);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 3 to be inserted at index 2");

		cmpArr = new int[] {1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0};
		queue.insert(4);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 4 to be inserted at index 3 and array to be increased");

		assertEquals(1, queue.remove(), "Expected 1 to be removed from beginning of queue");
		assertArrayEquals(cmpArr, result.getArray(), "Expected array to not be changed");

		cmpArr = new int[] { 3, 4, 0, 0, 0, 0};
		assertEquals(2, queue.remove(), "Expected 2 to be removed from beginning of queue");
		assertArrayEquals(cmpArr, result.getArray(), "Expected array to be reduced to below maxOverhead");

		cmpArr = new int[] { 4, 0, 0};
		assertEquals(3, queue.remove(), "Expected 3 to be removed from beginning of queue");
		assertArrayEquals(cmpArr, result.getArray(), "Expected array to be reduced to below maxOverhead");

		cmpArr[1] = 5;
		queue.insert(5);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 5 to be inserted at index 1");

		cmpArr[2] = 6;
		queue.insert(6);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 6 to be inserted at index 2");

		assertEquals(4, queue.remove(), "Expected 4 to be removed from beginning of queue");
		assertArrayEquals(cmpArr, result.getArray(), "Expected array to not be changed");

		cmpArr[0] = 7;
		queue.insert(7);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 7 to be inserted at index 0");

		cmpArr = new int[] {5, 6, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0};
		queue.insert(8);
		assertArrayEquals(cmpArr, result.getArray(), "Expected 8 to be inserted at index 3 and array size to be increased");

		assertEquals(5, queue.remove(), "Expected 5 to be removed from beginning of queue");
		assertArrayEquals(cmpArr, result.getArray(), "Expected array to not be changed");

		cmpArr = new int[] { 7, 8, 0, 0, 0, 0};
		assertEquals(6, queue.remove(), "Expected 6 to be removed from beginning of queue");
		assertArrayEquals(cmpArr, result.getArray(), "Expected array to be reduced to below maxOverhead");

		cmpArr = new int[] { 8, 0, 0};
		assertEquals(7, queue.remove(), "Expected 3 to be removed from beginning of queue");
		assertArrayEquals(cmpArr, result.getArray(), "Expected array to be reduced to below maxOverhead");

		assertEquals(8, queue.remove(), "Expected 8 to be removed from beginning of queue");
		assertArrayEquals(new int[0], result.getArray(), "Expected array to be reduced to below maxOverhead");

		assertThrows(IndexOutOfBoundsException.class, queue::remove,"You should not be able to remove an Item from empty queue");
	}

	@Test
	public void randomRingLoop() {

		Random random = new Random();

		for (int i = 0; i < 100; i++) {
			TestResult result = new TestResult();
			RingQueue queue = new RingQueue(3,4,result);
			int lim = random.nextInt(1000);
			List<Integer> vals = new ArrayList<>();
			for (int j = 0; j < lim; j++) {
				int val = random.nextInt();
				vals.add(val);
				queue.insert(val);
			}
			for (int j = 0; j < lim; j++) {
				assertEquals(vals.get(0), queue.remove(), "Value does not match");
				vals.remove(0);
			}

			assertEquals(0, result.getArray().length, "Array not empty after removing all Elements");
		}
	}

	@Test
	public void stackyQueueExamples() {

		TestResult result1 = new TestResult();
		TestResult result2 = new TestResult();
		StackyQueue queue = new StackyQueue(3,4,result1,result2);

		int[] cmpArr1 = null;
		int[] cmpArr2 = null;

		assertNull(result1.getArray(), "Stack 1 should be empty upon initialization");
		assertNull(result2.getArray(), "Stack 2 should be empty upon initialization");

		queue.insert(1);
		cmpArr1 = new int[]{1,0,0};

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected 1 to be inserted into Stack 1");
		assertNull(result2.getArray(), "Stack 2 should not be changed");

		queue.insert(2);
		cmpArr1[1] = 2;

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected 2 to be inserted into Stack 1");
		assertNull(result2.getArray(), "Stack 2 should not be changed");

		queue.insert(3);
		cmpArr1[2] = 3;

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected 3 to be inserted into Stack 1");
		assertNull(result2.getArray(), "Stack 2 should not be changed");

		queue.insert(4);
		cmpArr1 = new int[]{1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0};

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected 4 to be inserted into Stack 1");
		assertNull(result2.getArray(), "Stack 2 should not be changed");

		assertEquals(1, queue.remove(), "Expected remove to return the first item");
		cmpArr2 = new int[]{4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0};
		cmpArr1 = new int[0];

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected Stack 1 to be empty");
		assertArrayEquals(cmpArr2, result2.getArray(), "Expected contents of Stack 1 to be moved into Stack 2");

		assertEquals(2, queue.remove(), "Expected remove to return the second item");
		cmpArr2 = new int[]{4, 3, 0, 0, 0, 0};

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected Stack 1 to be unchanged");
		assertArrayEquals(cmpArr2, result2.getArray(), "Expected Stack 2 to be reduced");

		assertEquals(3, queue.remove(), "Expected remove to return the third item");
		cmpArr2 = new int[]{4, 0, 0};

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected Stack 1 to be unchanged");
		assertArrayEquals(cmpArr2, result2.getArray(), "Expected Stack 2 to be reduced");

		queue.insert(5);
		cmpArr1 = new int[]{5,0,0};

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected 5 to be inserted into Stack 1");
		assertArrayEquals(cmpArr2, result2.getArray(), "Expected Stack 2 to be unchanged");

		queue.insert(6);
		cmpArr1 = new int[]{5,6,0};

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected 6 to be inserted into Stack 1");
		assertArrayEquals(cmpArr2, result2.getArray(), "Expected Stack 2 to be unchanged");

		assertEquals(4, queue.remove(), "Expected remove to return the fourth item");
		cmpArr2 = new int[0];

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected Stack 1 to be unchanged");
		assertArrayEquals(cmpArr2, result2.getArray(), "Expected Stack 2 to be empty");

		assertEquals(5, queue.remove(), "Expected remove to return the fifth item");
		cmpArr2 = new int[]{6, 5, 0};
		cmpArr1 = new int[0];

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected Stack 1 to be empty");
		assertArrayEquals(cmpArr2, result2.getArray(), "Expected contents of Stack 1 to be inserted into Stack 2");

		assertEquals(6, queue.remove(), "Expected remove to return the sixth item");
		cmpArr2 = new int[0];

		assertArrayEquals(cmpArr1, result1.getArray(), "Expected Stack 1 to be empty");
		assertArrayEquals(cmpArr2, result2.getArray(), "Expected Stack 2 to be empty");

		assertThrows(IndexOutOfBoundsException.class, queue::remove, "IndexOutOfBounds Exceptions should be thrown when removing element from empty queue");
	}

	@Test
	public void reportUsageLength() {

		Random random = new Random();
		Interval interval = EmptyInterval.getEmptyInterval();
		TestResult result = new TestResult();

		int growthFactor = 3;
		int maxoverhead = 4;
		DynamicArray array = new DynamicArray(growthFactor, maxoverhead);

		for (int i = 0; i < 200; i++) {
			array.reportArray(result);
			int length = result.getArray().length;
			int newMin = random.nextInt(250);
			array.reportUsage(interval,newMin);
			array.reportArray(result);

			if (newMin*maxoverhead < length) {
				assertEquals(newMin*growthFactor, result.getArray().length, String.format("Expected array to be reduced because minLength*maxOverhead => %d*%d = %d was smaller then the previous length %d and should now be minLength*growthfactor => %d*%d = %d", newMin,maxoverhead,newMin*maxoverhead,length,newMin,growthFactor,growthFactor*newMin));
			} else if (newMin > length) {
				assertEquals(newMin*growthFactor,result.getArray().length, String.format("Expected Array to grow because minLength %d is bigger then length %d. New length should be minLength*growthFactor => %d*%d = %d", newMin, length, newMin, growthFactor, newMin*growthFactor));
			} else {
				assertEquals(length, result.getArray().length, String.format("Array length should not have changed. minLength %d is neither bigger then length %d nor is minLength*maxOverhead %d*%d = %d smaller then length %d", newMin, length, newMin, maxoverhead, newMin*maxoverhead, length));
			}

		}
	}
}
