package tests.radix;

import gad.radix.Result;

public class TestResult implements Result {

	int[] array;

	@Override
	public void logArray(int[] array) {
		this.array = array;
	}

	public int[] getArray() {
		return array;
	}
}
