package tests.avl;

import gad.avl.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MinimalAVLTreeTester {

    /**
     * Test to check various attributes of an empty AVL tree.
     */
    @Test
    public void testEmptyTree() {
        AVLTree tree = new AVLTree();
        assertNull(tree.getRoot(), "empty tree should not have any root");
        assertEquals(0, tree.height(), "empty tree should have height 0");
        assertTrue(tree.validAVL(), "empty tree is a valid AVL tree");
        assertFalse(tree.find(0), "empty tree should not find any element");
    }

    /**
     * Test to check the height() method of the tree in various easy settings.
     */
    @Test
    public void testHeight() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(12);
        tree.setRoot(root);
        assertEquals(1, tree.height(), "height of one element should be 1");

        AVLTreeNode a = new AVLTreeNode(4);
        AVLTreeNode a1 = new AVLTreeNode(4);
        AVLTreeNode a2 = new AVLTreeNode(8);
        AVLTreeNode b = new AVLTreeNode(15);
        AVLTreeNode b2 = new AVLTreeNode(25);

        root.setLeft(a);
        assertEquals(2, tree.height());
        root.setRight(b);
        assertEquals(2, tree.height());

        a.setLeft(a1);
        assertEquals(3, tree.height());
        a.setRight(a2);
        assertEquals(3, tree.height());
        b.setRight(b2);
        assertEquals(3, tree.height());

        b2.setRight(new AVLTreeNode(42));
        assertEquals(4, tree.height());
    }

    /**
     * Test to check the validAVL() method. This test only checks
     * for some of the mandatory attributes the AVL tree needs to
     * fulfill, e.g. it checks for the balance difference, no
     * circular references in it and that some node's values are
     * sorted correctly. Be careful if your implementation depends
     * on certain functionality of insert() to fix your tree
     * layout, since this check only uses setters to build the tree.
     */
    @Test
    public void testValidAVL() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(12);
        tree.setRoot(root);
        assertTrue(tree.validAVL(), "correct AVL tree");

        try {
            root.setRight(root);
            root.setBalance(1);
            assertFalse(tree.validAVL(), "the root node can't be its own child");
            root.setRight(null);
            root.setBalance(0);
            assertTrue(tree.validAVL(), "correct AVL tree");
        } catch (StackOverflowError exc) {
            fail("StackOverflow: fix problems with circular trees");
        }

        AVLTreeNode a = new AVLTreeNode(4);
        AVLTreeNode a1 = new AVLTreeNode(4);
        AVLTreeNode a2 = new AVLTreeNode(8);
        AVLTreeNode b = new AVLTreeNode(15);
        AVLTreeNode b1 = new AVLTreeNode(13);
        AVLTreeNode b2 = new AVLTreeNode(25);

        root.setLeft(a);
        root.setBalance(2);
        assertFalse(tree.validAVL(), "too high balance value at root node");
        root.setBalance(1);
        assertFalse(tree.validAVL(), "wrong balance value 1 at root node");
        root.setBalance(0);
        assertFalse(tree.validAVL(), "wrong balance value 0 at root node");
        root.setBalance(-1);
        assertTrue(tree.validAVL(), "correct AVL tree");

        try {
            a.setRight(root);
            a.setBalance(1);
            assertFalse(tree.validAVL(), "a tree must not contain circles");
            a.setRight(null);
            a.setBalance(0);
            assertTrue(tree.validAVL(), "correct AVL tree");
        } catch (StackOverflowError exc) {
            fail("StackOverflow: fix problems with circular trees");
        }

        root.setLeft(b);
        assertFalse(tree.validAVL(), "left key was bigger than root key");

        root.setLeft(a);
        root.setRight(b);
        root.setBalance(0);
        assertTrue(tree.validAVL(), "correct AVL tree");

        a.setLeft(a1);
        a.setRight(a2);
        b.setLeft(b1);
        b.setRight(b2);
        assertTrue(tree.validAVL(), "correct AVL tree");

        b.setRight(b1);
        b.setLeft(b2);
        assertFalse(tree.validAVL(), "node B children's keys have right < left");

        b.setLeft(b1);
        b.setRight(null);
        assertFalse(tree.validAVL(), "wrong balance at node B");

        b.setBalance(-1);
        assertTrue(tree.validAVL(), "correct AVL tree");

        root.setBalance(0);
        a.setBalance(1);
        assertFalse(tree.validAVL(), "wrong balance at node A");

        a.setLeft(null);
        assertTrue(tree.validAVL(), "correct AVL tree");
    }

    /**
     * Test to check whether the combination of find() and insert()
     * work as expected. Note that no tree layout checks are performed
     * by this unit test, it will actually only verify the find()
     * method. But that's enough to make the test fail if insert() does
     * not work. Also note that multiple same keys are not checked.
     */
    @Test
    public void testFindInsert() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(12);
        tree.setRoot(root);
        assertTrue(tree.find(12));
        assertFalse(tree.find(5));
        assertFalse(tree.find(0));
        assertFalse(tree.find(-12));
        tree.setRoot(null);

        Random random = new Random();
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int v = random.nextInt(2000) - 1000;
            values.add(v);
            tree.insert(v);
            assertTrue(tree.find(v));
        }

        for (int i = -1000; i < 1000; i++) {
            assertEquals(values.contains(i), tree.find(i));
        }
    }

    /**
     * Test to check if the validAVL() method works for pretty filled
     * AVL trees. Note that this check can't ensure that your returned
     * value is <i>correct</i>, since it provides no valid AVL tree for
     * comparison. But it can force the AVL tree to be definitively
     * wrong, which must be detected by your method of course. The check
     * uses the leftmost and rightmost nodes and alters certain values
     * of them to break various different AVL tree rules.
     */
    @Test
    public void testInsertDeeplyCorrectAVL() {
        AVLTree tree = new AVLTree();
        Random random = new Random();
        for (int i = 0; i < 300; i++) {
            int v = random.nextInt(2000) - 1000;
            tree.insert(v);
        }

        assertTrue(Math.abs(tree.getRoot().getBalance()) <= 1, "the abs balance must be lower than 2");

        AVLTreeNode leftmost;
        AVLTreeNode rightmost;
        try {
            leftmost = tree.getRoot().getLeft();
            while (leftmost.getLeft() != null) {
                leftmost = leftmost.getLeft();
            }
            rightmost = tree.getRoot().getLeft();
            while (rightmost.getLeft() != null) {
                rightmost = rightmost.getLeft();
            }
        } catch (NullPointerException exc) {
            fail("NullPointerException: you need to implement insert() first");
            return;
        }

        int leftmostBalance = leftmost.getBalance();
        leftmost.setBalance(5);
        assertFalse(tree.validAVL(), "leftmost balance is obviously wrong");
        leftmost.setBalance(leftmostBalance);

        int rightmostBalance = rightmost.getBalance();
        rightmost.setBalance(-2);
        assertFalse(tree.validAVL(), "rightmost balance is obviously wrong");
        rightmost.setBalance(rightmostBalance);

        assertTrue(leftmost.getKey() <= rightmost.getKey(),
                "the keys of the leftmost and rightmost nodes should be ascending or equal");

        leftmost.setRight(leftmost);
        assertFalse(tree.validAVL(), "leftmost is child of itself");
        leftmost.setRight(rightmost);
        assertFalse(tree.validAVL(), "rightmost is a child of leftmost");
        leftmost.setRight(new AVLTreeNode(leftmost.getKey() - 1));
        assertFalse(tree.validAVL(), "right child of leftmost has lower key");
        leftmost.setRight(null);

        rightmost.setLeft(rightmost);
        assertFalse(tree.validAVL(), "rightmost is child of itself");
        rightmost.setLeft(leftmost);
        assertFalse(tree.validAVL(), "leftmost is a child of rightmost");
        rightmost.setLeft(new AVLTreeNode(rightmost.getKey() + 1));
        assertFalse(tree.validAVL(), "left child of rightmost has higher key");
        rightmost.setLeft(null);
    }
}
