package tests.dynamicarray;

import gad.dynamicarray.Result;

public class TestResult implements Result {

	private int[] array;

	@Override
	public void logArray(int[] array) {
		this.array = array;
	}

	public int[] getArray() {
		return array;
	}
}
