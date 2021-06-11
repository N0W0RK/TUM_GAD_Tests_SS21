package tests.binomilia;

import gad.binomilia.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FastResult implements Result {

    private List<BinomialTreeNode> currentHeap = new ArrayList<>();
    private List<List<String>> logs = new ArrayList<>();
    private List<String> currentLog = new ArrayList<>();
    // Use this in a method to print the current Heap:
    // System.out.println(BinomialHeap.dot(currentHeap));

    @Override
    public void startInsert(int value, Collection<BinomialTreeNode> heap) {
        //System.out.println("Starting insert to " + heap.size() + " trees");
        //if (!currentLog.isEmpty())]
        logs.add(currentLog);
        currentLog = new ArrayList<>();
        currentHeap.clear();
        currentHeap.addAll(heap);
    }

    @Override
    public void startInsert(int value, BinomialTreeNode[] heap) {
        //System.out.println("Starting insert to " + heap.length + " trees");
        //if (!currentLog.isEmpty())
        logs.add(currentLog);
        currentLog = new ArrayList<>();
        currentHeap.clear();
        currentHeap.addAll(Arrays.stream(heap).toList());
    }

    @Override
    public void startDeleteMin(Collection<BinomialTreeNode> heap) {
        //System.out.println("Starting deletion of " + heap.size() + " trees");
        //if (!currentLog.isEmpty())
        logs.add(currentLog);
        currentLog = new ArrayList<>();
        currentHeap.clear();
        currentHeap.addAll(heap);
    }

    @Override
    public void startDeleteMin(BinomialTreeNode[] heap) {
        //System.out.println("Starting deletion of " + heap.length + " trees");
        logs.add(currentLog);
        currentLog = new ArrayList<>();
        currentHeap.clear();
        currentHeap.addAll(Arrays.stream(heap).toList());
    }

    @Override
    public void logIntermediateStep(Collection<BinomialTreeNode> heap) {
        //System.out.println("Intermediate step with " + heap.size() + " trees");
        currentHeap.clear();
        currentHeap.addAll(heap);
        currentLog.add(BinomialHeap.dot(currentHeap));
    }

    @Override
    public void logIntermediateStep(BinomialTreeNode[] heap) {
        //System.out.println("Intermediate step with " + heap.length + " trees");
        currentHeap.clear();
        currentHeap.addAll(Arrays.stream(heap).toList());
        currentLog.add(BinomialHeap.dot(currentHeap));
    }

    @Override
    public void logIntermediateStep(BinomialTreeNode tree) {
        //System.out.println("Intermediate step with 1 tree");
        currentHeap.clear();
        currentHeap.add(tree);
        currentLog.add(BinomialHeap.dot(currentHeap));
    }

    @Override
    public void addToIntermediateStep(Collection<BinomialTreeNode> additionalHeap) {
        //System.out.println(additionalHeap.size() + " additional trees were added to the intermediate step");
        currentHeap.addAll(additionalHeap);
        currentLog.remove(currentLog.size() - 1);
        currentLog.add(BinomialHeap.dot(currentHeap));
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode[] additionalHeap) {
        //System.out.println(additionalHeap.length + " additional trees were added to the intermediate step");
        currentHeap.addAll(Arrays.stream(additionalHeap).toList());
        currentLog.remove(currentLog.size() - 1);
        currentLog.add(BinomialHeap.dot(currentHeap));
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode tree) {
        //System.out.println("1 Additional tree was added to the intermediate step");
        currentHeap.add(tree);
        currentLog.remove(currentLog.size() - 1);
        currentLog.add(BinomialHeap.dot(currentHeap));
    }

    /**
     * Creates a list of all the logs as strings and returns log. <br>
     * Each element of this list is when log was called for the first time <br>
     * <i>Note: The 0th element of the logs is always null, first 1st element is the actual 1st element</i>
     * @return all the logs
     */
    public List<List<String>> getLogs() {
        if (!logs.contains(currentLog)) {
            logs.add(currentLog);
        }

        return logs;
    }

    /**
     * Method to return only the latest log element.
     * @return The last logged method
     */
    public List<String> getLastLog() {
        return getLogs().get(getLogs().size() - 1);
    }

}
