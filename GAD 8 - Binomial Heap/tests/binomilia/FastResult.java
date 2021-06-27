package tests.binomilia;

import gad.binomilia.*;

import java.util.*;
import java.util.stream.Collectors;

public class FastResult implements Result {

	private final List<List> logs;
	private List<BinomialTreeNode> currentHeap;

	private final Comparator<BinomialTreeNode> comparator = Comparator.comparing(BinomialTreeNode::rank).reversed().thenComparing(BinomialTreeNode::min);

	public FastResult() {
		logs = new ArrayList<>();
	}

	@Override
	public void startInsert(int value, Collection<BinomialTreeNode> heap) {

		addToLogs(heap);
		currentHeap = null;
	}

	@Override
	public void startInsert(int value, BinomialTreeNode[] heap) {
		startInsert(value, Arrays.asList(heap));
	}

	@Override
	public void startDeleteMin(Collection<BinomialTreeNode> heap) {

		addToLogs(heap);
		currentHeap = null;
	}

	@Override
	public void startDeleteMin(BinomialTreeNode[] heap) {
		startDeleteMin(Arrays.asList(heap));
	}

	@Override
	public void logIntermediateStep(Collection<BinomialTreeNode> heap) {

		currentHeap = new ArrayList<>();
		currentHeap.addAll(heap);
		addToLogs(heap);
	}

	@Override
	public void logIntermediateStep(BinomialTreeNode[] heap) {
		logIntermediateStep(Arrays.asList(heap));
	}

	@Override
	public void logIntermediateStep(BinomialTreeNode tree) {
		logIntermediateStep(List.of(tree));
	}

	@Override
	public void addToIntermediateStep(Collection<BinomialTreeNode> additionalHeap) {
		logs.remove(logs.size()-1);
		currentHeap.addAll(additionalHeap);
		addToLogs(currentHeap);
	}

	@Override
	public void addToIntermediateStep(BinomialTreeNode[] additionalHeap) {
		addToIntermediateStep(Arrays.asList(additionalHeap));
	}

	@Override
	public void addToIntermediateStep(BinomialTreeNode tree) {
		addToIntermediateStep(List.of(tree));
	}

	private void addToLogs(Collection<BinomialTreeNode> heap) {

		logs.add(heap.stream().filter(Objects::nonNull).sorted(comparator).map(e -> treeToList(e)).toList());
	}

	public List<String> getLogs() {
		return logs.stream().map(e -> e.stream().map(Object::toString).collect(Collectors.joining(", ", "{", "}"))).map(String::valueOf).toList();
	}

	private List treeToList(BinomialTreeNode tree) {

		List res = new ArrayList<>();

		int rank = tree.rank();

		res.add(tree.getElement());

		for (int i = 0; i < rank; i++) {
			res.add(treeToList(tree.getChildWithRank(i)));
		}

		return res;
	}

}
