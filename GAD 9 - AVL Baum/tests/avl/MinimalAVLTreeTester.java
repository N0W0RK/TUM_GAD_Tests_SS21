package tests.avl;

import gad.avl.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinimalAVLTreeTester {

    @Test
    public void testEmptyTree() {
        AVLTree tree = new AVLTree();
        assertNull(tree.getRoot(), "empty tree should not have any root");
        assertEquals(0, tree.height(), "empty tree should have height 0");
        assertTrue(tree.validAVL(), "empty tree is a valid AVL tree");
        assertFalse(tree.find(0), "empty tree should not find any element");
    }

    @Test
    public void testHeight() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(12);
        tree.setRoot(root);
        assertEquals(1, tree.height(), "height of one element should be 1");

        AVLTreeNode a = new AVLTreeNode(4);
        AVLTreeNode a1 = new AVLTreeNode(4);  // equal keys should work
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

    @Test
    public void testValidAVL() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(12);
        tree.setRoot(root);
        assertTrue(tree.validAVL());

        AVLTreeNode a = new AVLTreeNode(4);
        AVLTreeNode a1 = new AVLTreeNode(4);  // equal keys should work
        AVLTreeNode a2 = new AVLTreeNode(8);
        AVLTreeNode b = new AVLTreeNode(15);
        AVLTreeNode b1 = new AVLTreeNode(13);
        AVLTreeNode b2 = new AVLTreeNode(25);

        root.setLeft(a);
        assertFalse(tree.validAVL(), "incorrect balance at root node");
        root.setBalance(2);
        assertFalse(tree.validAVL(), "too high balance value at root node");
        root.setBalance(0);

        a.setRight(root);
        try {
            assertFalse(tree.validAVL(), "a tree must not contain circles");
        } catch (StackOverflowError exc) {
            fail("StackOverflow: fix problems with circular trees");
        }
        a.setRight(null);

        root.setLeft(b);
        assertFalse(tree.validAVL(), "left value was bigger than right value");

        root.setLeft(a);
        root.setRight(b);
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
        root.setBalance(-1);
        assertTrue(tree.validAVL(), "correct AVL tree");

        root.setBalance(0);
        a.setBalance(1);
        assertFalse(tree.validAVL(), "wrong balance at node A");

        a.setLeft(null);
        assertTrue(tree.validAVL(), "correct AVL tree");
    }
}
