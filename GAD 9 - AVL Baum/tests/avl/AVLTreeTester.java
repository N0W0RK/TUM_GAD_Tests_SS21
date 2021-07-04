package tests.avl;

import gad.avl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTester {

    private AVLTree tree;

    /**
     * Creates a new tree for every test.
     */
    @BeforeEach
    public void setup() {
        tree = new AVLTree();
    }

    /**
     * Test to check various attributes of an empty AVL tree.
     */
    @Test
    public void testEmptyTree() {
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
     * Tests to check the validAVL() method. This test only checks
     * for some of the mandatory attributes the AVL tree needs to
     * fulfill, e.g. it checks for the balance difference, no
     * circular references in it and that some node's values are
     * sorted correctly. Be careful if your implementation depends
     * on certain functionality of insert() to fix your tree
     * layout, since this check only uses setters to build the tree.
     */
    @Nested
    @DisplayName("validAVL() Tests")
    class TestValidAVL {

        private AVLTreeNode root;
        private AVLTreeNode a;
        private AVLTreeNode a1;
        private AVLTreeNode a2;
        private AVLTreeNode b;
        private AVLTreeNode b1;
        private AVLTreeNode b2;

        @BeforeEach
        public void setupValidAVL() {
            root = new AVLTreeNode(12);
            a = new AVLTreeNode(4);
            a1 = new AVLTreeNode(4);
            a2 = new AVLTreeNode(8);
            b = new AVLTreeNode(15);
            b1 = new AVLTreeNode(13);
            b2 = new AVLTreeNode(25);
        }

        @Test
        @DisplayName("Simple root tree")
        public void simpleRootTree() {
            tree.setRoot(root);
            assertTrue(tree.validAVL(), "Did not recognize correct AVL tree");
        }

        @Test
        @DisplayName("Circle along path")
        public void circleAlongPath() {

            assertDoesNotThrow(() -> {
                tree.setRoot(root);
                root.setRight(root);
                root.setBalance(1);
                assertFalse(tree.validAVL(), "The root node can't be its own child");
            }, "Circular tree leads to exception");
        }

        @Test
        @DisplayName("Long circle along path")
        public void longCircleAlongPath() {

            assertDoesNotThrow(() -> {
                tree.setRoot(root);
                root.setLeft(a);
                root.setBalance(-1);
                a.setRight(root);
                a.setBalance(1);
                assertFalse(tree.validAVL(), "a tree must not contain circles");
            }, "Circular tree leads to exception");
        }

        @Test
        @DisplayName("Circle across paths")
        public void circleAcrossPaths() {

            assertDoesNotThrow(() -> {
                tree.setRoot(root);
                root.setLeft(a);
                root.setRight(b);

                a.setRight(a2);
                a.setBalance(1);
                b.setLeft(a2);
                b.setBalance(-1);

                assertFalse(tree.validAVL(), "One child cant have multiple parents");
            });
        }

        @Test
        @DisplayName("Circle across paths and heights")
        public void circleAcrossPathsHeight() {

            assertDoesNotThrow(() -> {
                tree.setRoot(root);
                root.setLeft(a);
                root.setRight(b);

                a.setRight(a1);
                a.setBalance(1);
                a1.setRight(a2);
                a1.setBalance(1);

                b.setLeft(a2);
                b.setBalance(-1);

                assertFalse(tree.validAVL(), "One child cant have multiple parents");
            });
        }

        @Test
        @DisplayName("Wrong Balance")
        public void wrongBalance() {

            tree.setRoot(root);
            root.setLeft(a);
            root.setBalance(2);
            assertFalse(tree.validAVL(), "too high balance value at root node");
            root.setBalance(1);
            assertFalse(tree.validAVL(), "wrong balance value 1 at root node");
            root.setBalance(0);
            assertFalse(tree.validAVL(), "wrong balance value 0 at root node");
            root.setBalance(-1);
            assertTrue(tree.validAVL(), "correct AVL tree");

        }

        @Test
        @DisplayName("Children on wrong side")
        public void childrenWrongSide() {

            tree.setRoot(root);
            root.setRight(a);
            root.setBalance(1);

            assertFalse(tree.validAVL(), "Node 'a' should be on the left of the root");

            root.setLeft(b);
            root.setBalance(0);

            assertFalse(tree.validAVL(), "Children of root are in the wrong order");

            root.setRight(null);
            root.setBalance(-1);

            assertFalse(tree.validAVL(), "Node 'b' should be on the right of the root");
        }

        @Test
        @DisplayName("Unsorted children")
        public void unsortedChildren() {

            tree.setRoot(root);
            root.setLeft(a);
            root.setRight(b);

            b.setRight(b2);
            b.setLeft(a2);

            assertFalse(tree.validAVL(), "The left child of node 'b' has a lower key than root");
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
        @DisplayName("Deeply correct AVL with insert()")
        public void testInsertDeeplyCorrectAVL() {
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
                rightmost = tree.getRoot().getRight();
                while (rightmost.getRight() != null) {
                    rightmost = rightmost.getRight();
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

    @Nested
    @DisplayName("Insert tests")
    class InsertTests {
        /**
         * Count the number of nodes in the subtree, including the root itself.
         *
         * @param subtree root of a subtree
         * @return number of nodes in the subtree
         */
        private int count(AVLTreeNode subtree) {
            int l = subtree.getLeft() == null ? 0 : count(subtree.getLeft());
            int r = subtree.getRight() == null ? 0 : count(subtree.getRight());
            return 1 + l + r;
        }

        /**
         * Test to check that insert() works to insert new values.
         * No checks on the layout of the AVL tree are performed in
         * this test. It will only check that the number of nodes
         * in the tree is always correct after every insert() call.
         */
        @Test
        public void testInsertNumberOfValues() {
            tree.insert(42);
            try {
                assertEquals(42, tree.getRoot().getKey(), "root node has wrong value");
            } catch (NullPointerException exc) {
                fail("you need to implement insert() first");
            }

            assertEquals(1, count(tree.getRoot()), "expected one element in the tree");
            Random random = new Random();
            for (int i = 0; i < 999; i++) {
                tree.insert(random.nextInt());
                assertEquals(2 + i, count(tree.getRoot()), "expected " + (2 + i) + " nodes in the tree");
            }
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
         * Test to check if the tree arranges the lowest key to the
         * leftmost element. This test uses a total of 255 elements in
         * the tree to try to get a completely filled binary search
         * tree, even though that that's not a strict requirement.
         */
        @Test
        public void testLeftmost() {
            tree.insert(33);
            AVLTreeNode root = tree.getRoot();
            for (int i = 0; i < 254; i++) {
                tree.insert(42);
            }

            AVLTreeNode leftmost;
            try {
                leftmost = tree.getRoot().getLeft();
                while (leftmost.getLeft() != null) {
                    leftmost = leftmost.getLeft();
                }
            } catch (NullPointerException exc) {
                fail("NullPointerException: you need to implement insert() first (or did you forget to rotate?)");
                return;
            }

            assertEquals(33, leftmost.getKey(), "leftmost node needs a key of 33");
            assertSame(root, leftmost, "leftmost node must be previous root node (no new nodes created)");
            assertTrue(8 <= tree.height() && tree.height() <= 9, "height() of the tree must be 8 or 9");
            assertEquals(255, count(tree.getRoot()), "the tree should contain a total of 255 nodes");
            assertEquals(1, count(leftmost), "leftmost element should not have children");
        }
    }

    @Nested
    @DisplayName("Speed analysis")
    class SpeedAnalysis {

        /**
         * All: Gives singular runtimes and an average.
         * Average: Only outputs an average.
         * None: No speed output.
         */
        private enum OutputType {
            All, Average, None
        }

        /**
         * Stores output type.
         */
        private static final OutputType OUTPUT_TYPE = OutputType.All;

        /**
         * Limits the max size of elements in the tree to {@code 10**SIZE_LIMIT}.
         */
        private static final int SIZE_LIMIT = 4;

        /**
         * Amount of runs per test.
         * Used for calculating the average.
         */
        private static final int RUNS = 5;

        static Stream<Arguments> increasingSizes() {

            List<Arguments> args = new ArrayList<>();
            Random random = new Random(69);

            for (int i = 0; i < SIZE_LIMIT; i++) {

                args.add(Arguments.of((int) Math.pow(10, i), random.nextLong()));
            }

            return args.stream();
        }

        @ParameterizedTest(name = "{index} | Inserts {0} random elements")
        @DisplayName("Random insertions speed")
        @MethodSource("increasingSizes")
        void randomInsertTest(int limit, long seed) {

            Random random = new Random(seed);
            Executable executable = () -> {

                AVLTree avlTree = new AVLTree();
                for (int i = 0; i < limit; i++) {

                    int val = random.nextInt();
                    avlTree.insert(val);
                    assertTrue(avlTree.find(val), "The tree should find the value that was just added");
                    assertTrue(avlTree.validAVL(), "The tree should still be valid after insertion");
                }
            };

            tester(executable);
        }

        @ParameterizedTest(name = "{index} | Inserts {0} increasing elements")
        @DisplayName("Increasing insertions speed")
        @MethodSource("increasingSizes")
        void increasingInsertTest(int limit, long seed) {

            Random random = new Random(seed);
            Executable executable = () -> {

                AVLTree avlTree = new AVLTree();
                int start = random.ints(1, Integer.MIN_VALUE, Integer.MAX_VALUE - limit).toArray()[0];
                for (int i = 0; i < limit; i++) {

                    avlTree.insert(start + i);
                    assertTrue(avlTree.find(start + i), "The tree should find the value that was just added");
                    assertTrue(avlTree.validAVL(), "The tree should still be valid after insertion");
                }
            };

            tester(executable);
        }

        @ParameterizedTest(name = "{index} | Inserts {0} decreasing elements")
        @DisplayName("Decreasing insertions speed")
        @MethodSource("increasingSizes")
        void decreasingInsertTest(int limit, long seed) {

            Random random = new Random(seed);
            Executable executable = () -> {

                AVLTree avlTree = new AVLTree();
                int start = random.ints(1, Integer.MIN_VALUE + limit, Integer.MAX_VALUE).toArray()[0];
                for (int i = 0; i < limit; i++) {

                    avlTree.insert(start - i);
                    assertTrue(avlTree.find(start - i), "The tree should find the value that was just added");
                    assertTrue(avlTree.validAVL(), "The tree should still be valid after insertion");
                }
            };

            tester(executable);
        }

        @ParameterizedTest(name = "{index} | Inserts {0} times the same element")
        @DisplayName("Same element insertions speed")
        @MethodSource("increasingSizes")
        void sameInsertTest(int limit, long seed) {

            Random random = new Random(seed);
            Executable executable = () -> {

                AVLTree avlTree = new AVLTree();
                int val = random.nextInt();
                for (int i = 0; i < limit; i++) {

                    avlTree.insert(val);
                    assertTrue(avlTree.find(val), "The tree should find the value that was just added");
                    assertTrue(avlTree.validAVL(), "The tree should still be valid after insertion");
                }
            };

            tester(executable);

        }

        private void tester(Executable executable) {

            List<Long> times = new ArrayList<>();

            Executable func = () -> {

                long time = System.nanoTime();
                executable.execute();
                times.add(System.nanoTime() - time);
            };

            Executable[] funcs = new Executable[RUNS];
            Arrays.fill(funcs, func);
            assertAll("Some part of your implementation failed a test:", funcs);

            StringBuilder ret = new StringBuilder();
            long avg = 0;

            for (long time : times) {

                if (OUTPUT_TYPE.equals(OutputType.All)) {
                    ret.append("%f, ".formatted(time * 1e-9));
                }
                avg += time;
            }

            avg /= times.size();
            ret.append("Average : %fs".formatted(avg * 1e-9));

            if (!OUTPUT_TYPE.equals(OutputType.None)) {
                System.out.println(ret);
            }
        }
    }
}
