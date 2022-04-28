package tests.abtree;

import gad.abtree.ABTree;
import gad.abtree.ABTreeInnerNode;
import gad.abtree.ABTreeLeaf;
import gad.abtree.ABTreeNode;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ABTreeTester {

    @Test
    void artemisExample() {

        int[] insert = {109, 23, 49, 180, 120, 163, 172, 130, 95, 156, 99, 39, 178, 197, 71, 194, 118, 88};
        int[] remove = {95, 194, 23, 118, 109, 178, 71, 88, 197, 156, 99, 163, 49, 172, 120, 130, 180, 39};

        ABTree tree = new ABTree(2, 4);

        StringBuilder sb = new StringBuilder();

        for (int i : insert) {
            assertDoesNotThrow(() -> {
                tree.insert(i);
                assertTrue(tree.validAB(), "Not valid on %d".formatted(i));
                assertTrue(tree.find(i), "Insert failed on %d".formatted(i));
            }, "Insert failed on %d".formatted(i));
            assertTrue(tree.find(i), "Insert failed on %d".formatted(i));
            sb.append("Inserting: %d%n%s%n%n".formatted(i, tree));
        }

        for (int i : remove) {
            assertDoesNotThrow(() -> {
                assertTrue(tree.remove(i), "Romove of value %d did not work".formatted(i));
                assertTrue(tree.validAB(), "Not valid on %d".formatted(i));
                assertFalse(tree.find(i), "Remove failed on %d".formatted(i));
            }, "Remove failed on %d".formatted(i));
            sb.append("Deleting: %d%n%s%n%n".formatted(i, tree));
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("result.txt")));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void height() {

        ABTree tree = new ABTree(3, 5);

        for (int i = 0; i < 16; i++) {
            tree.insert(i);
            assertEquals(i < 4 ? 1 : 2, tree.height(), "Expected different height at %d".formatted(i));
        }

        System.out.println(tree);
    }

    @Test
    void randomRandom() {

        Random random = new Random(1);
        StringBuilder sb = new StringBuilder();

        int limit = 10;
        ABTree tree;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("randomMyResult.txt")));

            for (int a = 2; 2 * a - 1 <= limit; a++) {

                for (int b = 2 * a - 1; b < limit; b++) {

                    tree = new ABTree(a, b);
                    sb.append("New %d,%dTree: %n".formatted(a, b));

                    for (int i = 0; i < 100; i++) {
                        int val = random.nextInt();
                        tree.insert(val);
                        assertTrue(tree.validAB());
                        assertTrue(tree.find(val));
                        sb.append("Inserting: %d%n%s%n%n".formatted(val, tree));
                    }
                    bw.write(sb.toString());
                    sb = new StringBuilder();
                }
            }
            bw.write(sb.toString());
            bw.close();
        } catch (Exception ignored) {
        }
    }

    @Test
    void decreasing() {

        ABTree tree = new ABTree(3, 5);

        for (int i = 5; i > 0; i--) {
            tree.insert(i);
            assertTrue(tree.find(i));
            assertTrue(tree.validAB());
        }
        System.out.println(tree);
    }

    @Test
    void increasing() {
        ABTree tree = new ABTree(2, 3);

        for (int i = 0; i < 6; i++) {
            tree.insert(i);
            assertTrue(tree.find(i));
            assertTrue(tree.validAB());
            System.out.println(i + ":\n" + tree);
        }
    }

    @Test
    void slides() {

        ABTree tree = new ABTree(2, 4);

        ABTreeNode[] children = new ABTreeNode[4];
        Arrays.fill(children, new ABTreeLeaf(2, 4));

        List<ABTreeNode> rootChildren = new ArrayList<>();

        rootChildren.add(new ABTreeInnerNode(new ArrayList<>(Arrays.stream((new Integer[]{1, 3, 5})).toList()), new ArrayList<>(Arrays.stream(children).toList()), 2, 4));

        children = new ABTreeNode[2];
        Arrays.fill(children, new ABTreeLeaf(2, 4));

        rootChildren.add(new ABTreeInnerNode(new ArrayList<>(Arrays.stream(new Integer[]{14}).toList()), new ArrayList<>(Arrays.stream(children).toList()), 2, 4));
        rootChildren.add(new ABTreeInnerNode(new ArrayList<>(Arrays.stream(new Integer[]{28}).toList()), new ArrayList<>(Arrays.stream(children).toList()), 2, 4));

        ABTreeInnerNode root = new ABTreeInnerNode(new ArrayList<>(Arrays.stream(new Integer[]{10, 19}).toList()), rootChildren, 2, 4);

        tree.setRoot(root);

        System.out.println(tree);

        tree.insert(8);

        System.out.println(tree);
    }
}
