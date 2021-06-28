package tests.avl;

import gad.avl.AVLTree;
import gad.avl.AVLTreeNode;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTester {

	private AVLTree tree = new AVLTree();

	@BeforeEach
	void init() {
		tree = new AVLTree();
		System.out.println("------------------------------------");
	}

	@Test
	void stupidInserts() {

		AVLTree tree = new AVLTree();

		for (int i = 0; i < 100; i++) {
			tree.insert(i);
			assertDoesNotThrow(() -> {assertTrue(tree.validAVL());}, String.format("Failed at %d", i));
		}
		for (int i = 0; i < 100; i++) {
			int finalI = i;
			assertDoesNotThrow(() -> {assertTrue(tree.find(finalI));}, String.format("Failed at %d", i));
		}
		for (int i = -100; i < 0; i++) {
			int finalI = i;
			assertDoesNotThrow(() -> {assertFalse(tree.find(finalI));}, String.format("Failed at %d", i));
		}
		for (int i = 100; i < 200; i++) {
			int finalI = i;
			assertDoesNotThrow(() -> {assertFalse(tree.find(finalI));}, String.format("Failed at %d", i));
		}

		AVLTree tree2 = new AVLTree();

		for (int i = 100; i > 0; i--) {
			int finalI = i;
			assertDoesNotThrow(() -> tree2.insert(finalI), String.format("Failed at %d", i));
			assertDoesNotThrow(() -> {assertTrue(tree2.validAVL());}, String.format("Failed at %d", i));
		}
		for (int i = 1; i <= 100; i++) {
			int finalI = i;
			assertDoesNotThrow(() -> {assertTrue(tree2.find(finalI));}, String.format("Failed at %d", i));
		}
		for (int i = -100; i < 1; i++) {
			int finalI = i;
			assertDoesNotThrow(() -> {assertFalse(tree2.find(finalI));}, String.format("Failed at %d", i));
		}
		for (int i = 101; i < 200; i++) {
			int finalI = i;
			assertDoesNotThrow(() -> {assertFalse(tree2.find(finalI));}, String.format("Failed at %d", i));
		}
	}

	@Test
	void random() {

		AVLTree tree = new AVLTree();
		List<Integer> ints = new ArrayList<>();
		Random random = new Random(65132);

		for (int i = 0; i < 100; i++) {
			int rand = random.nextInt();
			assertDoesNotThrow(() -> {tree.insert(rand);}, String.format("Failed at %d", rand));
			ints.add(rand);
			assertDoesNotThrow(() -> {assertTrue(tree.validAVL());}, String.format("Failed at %d", rand));
			assertDoesNotThrow(() -> {assertTrue(tree.find(rand));}, String.format("Failed at %d", rand));
		}

		for (Integer i : ints) {

			assertDoesNotThrow(() -> {assertTrue(tree.find(i));}, String.format("Failed at %d", i));
		}
	}

	@Nested
	@DisplayName("validAVL()")
	class valid {


		@Test
		@DisplayName("Bigger value in left tree")
		void bigValLeft() {

			tree.setRoot(new AVLTreeNode(0));
			tree.getRoot().setLeft(new AVLTreeNode(-1));
			tree.getRoot().getLeft().setRight(new AVLTreeNode(1));
			assertFalse(tree.validAVL());
		}

		@Test
		@DisplayName("Bigger value in right tree")
		void bigValRight() {

			tree.setRoot(new AVLTreeNode(0));

			//Test if child with invalid key i s identified
			tree.getRoot().setRight(new AVLTreeNode(-1));

			//Test if invalid key is still identified, with a degree of separation
			tree.getRoot().setRight(new AVLTreeNode(1));
			tree.getRoot().getRight().setLeft(new AVLTreeNode(-1));
			assertFalse(tree.validAVL());
		}

		@Nested
		@DisplayName("Circle Testing")
		class circles {

			@Test
			@DisplayName("Self reference")
			void circle() {

				AVLTreeNode node = new AVLTreeNode(1);
				tree.setRoot(node);
				node.setLeft(node);

				assertFalse(tree.validAVL(), "Root is a child of itself, but the tree is accepted as valid.");
			}

			@Test
			@DisplayName("Multiple references ont node")
			void multRef() {

				tree.setRoot(new AVLTreeNode(0));
				tree.getRoot().setLeft(new AVLTreeNode(-1));
				tree.getRoot().setRight(new AVLTreeNode(1));

				AVLTreeNode node = new AVLTreeNode(0);
				tree.getRoot().getLeft().setRight(node);
				tree.getRoot().getRight().setLeft(node);

				assertFalse(tree.validAVL(), "Two elements reference the same node. This forms a circle over the root.");

			}

		}
	}
}
