package gad.binomilia;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Mihir Mahajan
 * 
 *         These tests only test if your BinomialHeap/BinomialTree itself works.
 *         They don't test the logging. Still, they may not work if the logging
 *         is incorrect!
 * 
 *         I can not guarantee the correctness or accuracy of these tests. I
 *         hope they help, enjoy!
 */

public class HeapTester {

	BinomialHeap heap = new BinomialHeap();
	StudentResult result = new StudentResult();

	@Test
	void testIllegal() {
		assertThrows(RuntimeException.class, () -> heap.min(), "Please throw an exception when heap is empty... thats the least u can do");
		assertThrows(RuntimeException.class, () -> heap.deleteMin(result),"Would u please just throw me an exception >.<");
	}

	@Test
	void testMin() {
		for (int i = -300; i < 300; i++)
			heap.insert(i, result);
		assertEquals(-300, heap.min());
	}

	@Test
	void testDeleteMin() {
		List<Integer> nums = new ArrayList<>();
		for (int i = -420; i < 420; i++) {
			heap.insert(i, result);
			nums.add(i);
		}

		for (int x : nums) {
			assertEquals(x, heap.min(), "Wrong value for min!");
			int deleted = heap.deleteMin(result);
			assertEquals(x, deleted,
					"Either did not delete the smallest number or the expected number wasnt in the heap");
		}
	}

	@Test
	void testBigNumbers() {
		List<Integer> nums = new ArrayList<>();
		for (int i = -420; i < 1000; i++) {
			int num = new Random().nextInt();
			nums.add(num);
			heap.insert(num, result);
			int minList = nums.stream().min((x, y) -> Integer.compare(x, y)).get();
			int minHeap = heap.min();
			assertEquals(minList, minHeap, "Wrong minimum " + i);
		}
		for (int i = 0; i < 500; i++) {
			int num = new Random().nextInt();
			nums.add(num);
			heap.insert(num, result);
			Integer min = nums.stream().min((x, y) -> Integer.compare(x, y)).get();
			nums.remove(min);
			assertEquals(min, heap.deleteMin(result), "Wrong number removed " + i);
		}
	}

	@Test
	void testAmountOFTrees() {
		List<Integer> nums = new ArrayList<>();
		int[] size = new int[1];

		Result myResult = new Result() {

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

		};

		for (int i = -420; i < 420; i++) {
			int num = new Random().nextInt();
			nums.add(num);
			heap.insert(num, myResult);
			int expectedSize = Integer.bitCount(nums.size());
			int trees = size[0];
			assertEquals(expectedSize, trees, "Wrong amount of Trees in heap");
		}
	}

	@Test
	void zwischenSchritte() {
		List<Integer> steps = new ArrayList<>();

		Result myResult = new Result() {

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
				steps.add(heap.size());
			}

			@Override
			public void addToIntermediateStep(BinomialTreeNode[] heap) {
				addToIntermediateStep(Arrays.stream(heap).toList());
			}

			@Override
			public void addToIntermediateStep(BinomialTreeNode tree) {
				addToIntermediateStep(List.of(tree));
			}

		};

		for (int i = -420; i < 420; i++) {
			int num = new Random().nextInt();
			heap.insert(num, myResult);
			for (int j = 1; j < steps.size(); j++)
				if (steps.get(j) + 1 != steps.get(j - 1))
					fail("Es wurden nicht genau zwei Bäume zu einem gemergt. Stattdessen wurden in einem Zwischenschritt "
							+ (steps.get(j - 1) - steps.get(j)) + " vorhandene Bäume entfernt, anstatt genau einem");
		}

		for (int i = -420; i < 420; i++) {
			heap.deleteMin(myResult);
			for (int j = 1; j != steps.size(); j++)
				if (steps.get(j) + 1 != steps.get(j - 1))
					fail("Es wurden nicht genau zwei Bäume zu einem gemergt. Stattdessen wurden in einem Zwischenschritt "
							+ (steps.get(j - 1) - steps.get(j)) + " vorhandene Bäume entfernt, anstatt genau einem: "
							+ steps);
		}
	}
}
