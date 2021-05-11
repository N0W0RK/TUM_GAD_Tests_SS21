package tests.binarysearch;

import gad.binarysearch.Result;

import java.util.ArrayList;
import java.util.List;

public class TestResult implements Result {

	private List<Integer> steps;

	public TestResult() {
		this.steps = new ArrayList<>();
	}

	@Override
	public void addStep(int index) {
		steps.add(index);
	}

	public List<Integer> getSteps() {
		return steps;
	}
}
