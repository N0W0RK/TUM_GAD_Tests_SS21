package tests.binomilia;

import gad.binomilia.*;

import java.util.*;

public class FastResult implements Result {

    private final List<BinomialTreeNode> currentHeap = new ArrayList<>();
    private final List<String> logs = new ArrayList<>();

    private Comparator<BinomialTreeNode> comparator = Comparator.comparing(BinomialTreeNode::rank).reversed().thenComparing(BinomialTreeNode::min);

    /**
     * Johannes's 1st public code for printing the tree.
     * @param trees The tree to be printed
     * @return The string of the tree
     */
    public static String dot(BinomialTreeNode[] trees) {
        return dot(Arrays.stream(trees).toList());
    }

    /**
     * Johannes's 1st public code for printing the tree.
     * @param trees The tree to be printed
     * @return The string of the tree
     */
    public static String dot(Collection<BinomialTreeNode> trees) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {").append(System.lineSeparator());
        int id = 0;
        List<Integer> roots = new ArrayList<>();
        for (BinomialTreeNode tree : trees) {
            sb.append(String.format("\tsubgraph cluster_%d {%n", id));
            roots.add(id);
            id = tree.dotNode(sb, id);
            sb.append(String.format("\t}%n"));
        }
        for (int i = 0; i < roots.size() - 1; i++) {
            sb.append(String.format("\t%d -> %d;%n", roots.get(i), roots.get(i + 1)));
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void startInsert(int value, Collection<BinomialTreeNode> heap) {

        logs.add(dot(heap.stream().sorted(comparator).toList()));
        currentHeap.clear();
        currentHeap.addAll(heap.stream().sorted(comparator).toList());
    }

    @Override
    public void startInsert(int value, BinomialTreeNode[] heap) {
        Arrays.sort(heap, comparator);
        logs.add(dot(heap));
        currentHeap.clear();
        currentHeap.addAll(Arrays.stream(heap).toList());
    }

    @Override
    public void startDeleteMin(Collection<BinomialTreeNode> heap) {
        logs.add(dot(heap.stream().sorted(comparator).toList()));
        currentHeap.clear();
        currentHeap.addAll(heap.stream().sorted(comparator).toList());
    }

    @Override
    public void startDeleteMin(BinomialTreeNode[] heap) {
        Arrays.sort(heap, comparator);
        logs.add(dot(heap));
        currentHeap.clear();
        currentHeap.addAll(Arrays.stream(heap).toList());
    }

    @Override
    public void logIntermediateStep(Collection<BinomialTreeNode> heap) {
        logs.add(dot(heap.stream().sorted(comparator).toList()));
        currentHeap.clear();
        currentHeap.addAll(heap.stream().toList());
    }

    @Override
    public void logIntermediateStep(BinomialTreeNode[] heap) {
        Arrays.sort(heap, comparator);
        logs.add(dot(heap));
        currentHeap.clear();
        currentHeap.addAll(Arrays.stream(heap).toList());
    }

    @Override
    public void logIntermediateStep(BinomialTreeNode tree) {
        currentHeap.clear();
        currentHeap.add(tree);
        logs.add(dot(currentHeap));
    }

    @Override
    public void addToIntermediateStep(Collection<BinomialTreeNode> additionalHeap) {
        logs.remove(logs.size() - 1);
        currentHeap.addAll(additionalHeap.stream().toList());
        logs.add(dot(currentHeap.stream().sorted(comparator).toList()));
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode[] additionalHeap) {
        logs.remove(logs.size() - 1);
        currentHeap.addAll(Arrays.stream(additionalHeap).toList());
        logs.add(dot(currentHeap.stream().sorted(comparator).toList()));
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode tree) {
        logs.remove(logs.size() - 1);
        currentHeap.add(tree);
        logs.add(dot(currentHeap.stream().sorted(comparator).toList()));
    }

    public List<String> getLogs() {
        return logs;
    }

}
