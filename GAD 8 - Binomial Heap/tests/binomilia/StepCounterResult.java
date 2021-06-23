package tests.binomilia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import gad.binomilia.*;

public class StepCounterResult implements Result {
    private List<Integer> steps = new ArrayList<>();

    @Override
    public void startInsert(int value, Collection<BinomialTreeNode> heap) {
        steps.clear();
    }

    @Override
    public void startInsert(int value, BinomialTreeNode[] heap) {
        steps.clear();
    }

    @Override
    public void startDeleteMin(Collection<BinomialTreeNode> heap) {
        steps.clear();
    }

    @Override
    public void startDeleteMin(BinomialTreeNode[] heap) {
        steps.clear();
    }

    @Override
    public void logIntermediateStep(Collection<BinomialTreeNode> heap) {
        // heap.size() should return the amount of trees in the heap. So it should
        // return the size of the Collection where u save the trees. If you do it
        // another way, this test may fail
        steps.add(heap.size());
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
        int tmp = steps.remove(steps.size() - 1);
        tmp += heap.size();
        steps.add(tmp);
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode[] heap) {
        addToIntermediateStep(Arrays.stream(heap).toList());
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode tree) {
        addToIntermediateStep(List.of(tree));
    }

    public List<Integer> getSteps() {
        return steps;
    }
}
