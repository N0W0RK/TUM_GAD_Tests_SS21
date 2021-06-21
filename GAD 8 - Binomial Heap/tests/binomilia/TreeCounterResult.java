package gad.tests;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import gad.binomilia.*;

public class TreeCounterResult implements Result {

	private int[] size = new int[1];

	@Override
	public void startInsert(int value, Collection<BinomialTreeNode> heap) {
	}

	@Override
	public void startInsert(int value, BinomialTreeNode[] heap) {
	}

	@Override
	public void startDeleteMin(Collection<BinomialTreeNode> heap) {
	}

	@Override
	public void startDeleteMin(BinomialTreeNode[] heap) {
	}

	@Override
	public void logIntermediateStep(Collection<BinomialTreeNode> heap) {
		size[0] = heap.size();
	}

	@Override
	public void logIntermediateStep(BinomialTreeNode[] heap) {
		logIntermediateStep(Arrays.stream(heap).toList());

	}

	@Override
	public void logIntermediateStep(BinomialTreeNode tree) {
		logIntermediateStep(List.of(tree));

	}

	@Override
	public void addToIntermediateStep(Collection<BinomialTreeNode> heap) {
		// heap.size() should return the amount of trees in the heap. So it should
		// return the size of the Collection where u save the trees. If you do it
		// another way, this test may fail
		size[0] = heap.size();
	}

	@Override
	public void addToIntermediateStep(BinomialTreeNode[] heap) {
		addToIntermediateStep(Arrays.stream(heap).toList());
	}

	@Override
	public void addToIntermediateStep(BinomialTreeNode tree) {
		addToIntermediateStep(List.of(tree));
	}

	public int getSize() {
		return size[0];
	}

}
