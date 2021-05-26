package tests.binarysearch;

import gad.binarysearch.Interval;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinSeaTester {

	/**
	 * Tests from examples from exercise 1
	 */
	@Test
	public void aufgabe1() {

		int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001};

		TestResult result = new TestResult();

		int index = gad.binarysearch.BinSea.search(array,7,result);

		List<Integer> resList = result.getSteps();
		assertEquals(7, array[index], "Found wrong index");
		assertFalse(resList.size()<2,"Logged not enough Steps.");
		assertFalse(resList.size()>2,"Logged too many Steps.");
		assertArrayEquals(new Integer[]{3,1},resList.toArray(new Integer[resList.size()]), "Logged wrong steps");

		result = new TestResult();

		index = gad.binarysearch.BinSea.search(array,100,result);

		resList = result.getSteps();
		assertTrue(index == 4 || index == 5, "Found wrong index");
		assertFalse(resList.size()<2,"Logged not enough Steps.");
		assertFalse(resList.size()>2,"Logged too many Steps.");
		assertArrayEquals(new Integer[]{3,5},resList.toArray(new Integer[resList.size()]), "Logged wrong steps");

	}

	/**
	 * Tests from examples from exercise 2
	 */
	@Test
	public void aufgabe2() {

		int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001};

		TestResult result = new TestResult();

		int index = gad.binarysearch.BinSea.search(array,7, false, result);

		List<Integer> resList = result.getSteps();

		assertEquals(2,index, "Found wrong index");
		assertFalse(resList.size()<2,"Logged not enough Steps.");
		assertFalse(resList.size()>2,"Logged too many Steps.");
		assertArrayEquals(new Integer[]{3,1},resList.toArray(new Integer[resList.size()]), "Logged wrong steps");

		result = new TestResult();

		index = gad.binarysearch.BinSea.search(array,100, true,result);

		resList = result.getSteps();

		assertEquals(5,index,  "Found wrong index");
		assertFalse(resList.size()<2,"Logged not enough Steps.");
		assertFalse(resList.size()>2,"Logged too many Steps.");
		assertArrayEquals(new Integer[]{3,5},resList.toArray(new Integer[resList.size()]), "Logged wrong steps");

		result = new TestResult();

		index = gad.binarysearch.BinSea.search(array, 1, false,result);

		resList = result.getSteps();

		assertEquals(-1,index,  "Found wrong index");
		assertFalse(resList.size()<2,"Logged not enough Steps.");
		assertFalse(resList.size()>2,"Logged too many Steps.");
		assertArrayEquals(new Integer[]{3,1},resList.toArray(new Integer[resList.size()]), "Logged wrong steps");
	}

	/**
	 * Tests from examples from exercise 3
	 */
	@Test
	public void aufgabe3() {

		int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001};

		TestResult highResult = new TestResult();
		TestResult lowResult = new TestResult();

		Interval interval = gad.binarysearch.BinSea.search(array,Interval.fromArrayIndices(7,1500),lowResult,highResult);

		List<Integer> resList = lowResult.getSteps();

		assertEquals(Interval.fromArrayIndices(1,5),interval, "Found wrong Interval");
		assertFalse(resList.size()<2,"Logged not enough Steps.");
		assertFalse(resList.size()>2,"Logged too many Steps.");
		assertArrayEquals(new Integer[]{3,1},resList.toArray(new Integer[resList.size()]), "Logged wrong steps");

		resList = highResult.getSteps();

		assertFalse(resList.size()>3, "Logged too manny steps");
		assertFalse(resList.size()<3, "Logged not enough steps");
		assertArrayEquals(new Integer[]{3,5,6}, resList.toArray(new Integer[3]), "Logged wrong steps");


		highResult = new TestResult();
		lowResult = new TestResult();

		interval = gad.binarysearch.BinSea.search(array, Interval.fromArrayIndices(9002,10000), lowResult, highResult);

		resList = lowResult.getSteps();

		assertEquals(Interval.EmptyInterval.getEmptyInterval(), interval, "Found wrong interval");
		assertFalse(resList.size()>3, "Logged too manny steps");
		assertFalse(resList.size()<3, "Logged not enough steps");
		assertArrayEquals(new Integer[]{3,5,6}, resList.toArray(new Integer[3]), "Logged wrong steps");

		resList = highResult.getSteps();

		assertEquals(0, resList.size(), "Logged too many steps");

	}
}
