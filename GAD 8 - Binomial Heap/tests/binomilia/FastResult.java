package tests.binomilia;

import gad.binomilia.*;

import java.util.*;

public class FastResult implements Result {

    private final List<BinomialTreeNode> currentHeap = new ArrayList<>();
    private final List<String> logs = new ArrayList<>();

    private final Comparator<BinomialTreeNode> comparator = Comparator.comparing(BinomialTreeNode::rank).reversed().thenComparing(BinomialTreeNode::min);

    @Override
    public void startInsert(int value, Collection<BinomialTreeNode> heap) {

        logs.add(BinomialHeap.dot(heap.stream().sorted(comparator).toList()));
        currentHeap.clear();
        currentHeap.addAll(heap.stream().sorted(comparator).toList());
    }

    @Override
    public void startInsert(int value, BinomialTreeNode[] heap) {
        Arrays.sort(heap, comparator);
        logs.add(BinomialHeap.dot(heap));
        currentHeap.clear();
        currentHeap.addAll(Arrays.stream(heap).toList());
    }

    @Override
    public void startDeleteMin(Collection<BinomialTreeNode> heap) {
        logs.add(BinomialHeap.dot(heap.stream().sorted(comparator).toList()));
        currentHeap.clear();
        currentHeap.addAll(heap.stream().sorted(comparator).toList());
    }

    @Override
    public void startDeleteMin(BinomialTreeNode[] heap) {
        Arrays.sort(heap, comparator);
        logs.add(BinomialHeap.dot(heap));
        currentHeap.clear();
        currentHeap.addAll(Arrays.stream(heap).toList());
    }

    @Override
    public void logIntermediateStep(Collection<BinomialTreeNode> heap) {
        logs.add(BinomialHeap.dot(heap.stream().sorted(comparator).toList()));
        currentHeap.clear();
        currentHeap.addAll(heap.stream().toList());
    }

    @Override
    public void logIntermediateStep(BinomialTreeNode[] heap) {
        Arrays.sort(heap, comparator);
        logs.add(BinomialHeap.dot(heap));
        currentHeap.clear();
        currentHeap.addAll(Arrays.stream(heap).toList());
    }

    @Override
    public void logIntermediateStep(BinomialTreeNode tree) {
        currentHeap.clear();
        currentHeap.add(tree);
        logs.add(BinomialHeap.dot(currentHeap));
    }

    @Override
    public void addToIntermediateStep(Collection<BinomialTreeNode> additionalHeap) {
        logs.remove(logs.size() - 1);
        currentHeap.addAll(additionalHeap.stream().toList());
        logs.add(BinomialHeap.dot(currentHeap.stream().sorted(comparator).toList()));
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode[] additionalHeap) {
        logs.remove(logs.size() - 1);
        currentHeap.addAll(Arrays.stream(additionalHeap).toList());
        logs.add(BinomialHeap.dot(currentHeap.stream().sorted(comparator).toList()));
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode tree) {
        logs.remove(logs.size() - 1);
        currentHeap.add(tree);
        logs.add(BinomialHeap.dot(currentHeap.stream().sorted(comparator).toList()));
    }

    public List<String> getLogs() {
        return logs;
    }

}
