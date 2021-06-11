package tests.binomilia;

import gad.binomilia.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <i> Note: It is too difficult to create hard coded tests which test if the your binomial tree is a valid tree or not
 * without actually comparing it with a proper implementation. <br>
 * Therefore it is very likely that your insertions are correct, however my tests except a different order. <br>
 * In that case you'll have to manually compare both the answers and manually go ahead! <br>
 * Sorry for that but I don't have enough time to create perfect tests. If you want to improve it, you're gladly welcome to do so <br>
 * Which is why I'm leaving a part of code underneath, which'll allow you to create these tests. Feel free to use it!
 * </i>
 * @author Aamin
 */

public class BinomialHeapTester {

    /**
     * <p>
     * Simple tests to see if numbers can be added in ascending order or not. <br>
     * Only positive integers are added! <br>
     * </p>
     * Tests solely {@link BinomialHeap#insert(int, Result)} <br> <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void simpleInsert() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult = new FastResult();
        List<String> expectedLogs;


        heap.insert(1, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(2, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t}\n\tsubgraph cluster_1 {\n\t\t1 [label=\"2\"];\n\t}\n\t0 -> 1;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(3, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"3\"];\n\t}\n\t0 -> 2;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(4, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"3\"];\n\t}\n\tsubgraph cluster_3 {\n\t\t3 [label=\"4\"];\n\t}\n\t0 -> 2;\n\t2 -> 3;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(5, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t}\n\t0 -> 4;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(6, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"6\"];\n\t}\n\t0 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\t0 -> 4;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(7, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"7\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(8, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"7\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"8\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(9, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t}\n\t0 -> 8;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(10, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"10\"];\n\t}\n\t0 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\t0 -> 8;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(11, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"11\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(12, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"11\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"12\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\t0 -> 8;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(13, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(14, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t}\n\tsubgraph cluster_13 {\n\t\t13 [label=\"14\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 13;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());


        heap.insert(15, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"15\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
    }

    /**
     * Simple tests to see if numbers can be added in descending order or not. <br>
     * Both positive and negative integers are added! <br>
     * Tests both {@link BinomialHeap#insert(int, Result)} and {@link BinomialHeap#min()} <br> <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void simpleInsertReverse() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult = new FastResult();
        List<String> expectedLogs;


        heap.insert(15, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(15, heap.min(), "You returned wrong value of min! Expected <15> but got <" + heap.min() + ">");


        heap.insert(14, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t}\n\tsubgraph cluster_1 {\n\t\t1 [label=\"14\"];\n\t}\n\t0 -> 1;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"14\"];\n\t\t0 -> 1;\n\t\t1 [label=\"15\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(14, heap.min(), "You returned wrong value of min! Expected <14> but got <" + heap.min() + ">");


        heap.insert(13, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"14\"];\n\t\t0 -> 1;\n\t\t1 [label=\"15\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"13\"];\n\t}\n\t0 -> 2;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(13, heap.min(), "You returned wrong value of min! Expected <13> but got <" + heap.min() + ">");


        heap.insert(12, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"14\"];\n\t\t0 -> 1;\n\t\t1 [label=\"15\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"13\"];\n\t}\n\tsubgraph cluster_3 {\n\t\t3 [label=\"12\"];\n\t}\n\t0 -> 2;\n\t2 -> 3;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"14\"];\n\t\t0 -> 1;\n\t\t1 [label=\"15\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"12\"];\n\t\t2 -> 3;\n\t\t3 [label=\"13\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"13\"];\n\t\t0 -> 2;\n\t\t2 [label=\"14\"];\n\t\t2 -> 3;\n\t\t3 [label=\"15\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(12, heap.min(), "You returned wrong value of min! Expected <12> but got <" + heap.min() + ">");


        heap.insert(11, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"13\"];\n\t\t0 -> 2;\n\t\t2 [label=\"14\"];\n\t\t2 -> 3;\n\t\t3 [label=\"15\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t}\n\t0 -> 4;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(11, heap.min(), "You returned wrong value of min! Expected <11> but got <" + heap.min() + ">");


        heap.insert(10, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"13\"];\n\t\t0 -> 2;\n\t\t2 [label=\"14\"];\n\t\t2 -> 3;\n\t\t3 [label=\"15\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"10\"];\n\t}\n\t0 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"13\"];\n\t\t0 -> 2;\n\t\t2 [label=\"14\"];\n\t\t2 -> 3;\n\t\t3 [label=\"15\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"11\"];\n\t}\n\t0 -> 4;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(10, heap.min(), "You returned wrong value of min! Expected <10> but got <" + heap.min() + ">");


        heap.insert(9, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"13\"];\n\t\t0 -> 2;\n\t\t2 [label=\"14\"];\n\t\t2 -> 3;\n\t\t3 [label=\"15\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"11\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"9\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(9, heap.min(), "You returned wrong value of min! Expected <9> but got <" + heap.min() + ">");


        heap.insert(8, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"13\"];\n\t\t0 -> 2;\n\t\t2 [label=\"14\"];\n\t\t2 -> 3;\n\t\t3 [label=\"15\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"11\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"9\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"8\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"13\"];\n\t\t0 -> 2;\n\t\t2 [label=\"14\"];\n\t\t2 -> 3;\n\t\t3 [label=\"15\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"11\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"8\"];\n\t\t6 -> 7;\n\t\t7 [label=\"9\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"13\"];\n\t\t0 -> 2;\n\t\t2 [label=\"14\"];\n\t\t2 -> 3;\n\t\t3 [label=\"15\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"8\"];\n\t\t4 -> 5;\n\t\t5 [label=\"9\"];\n\t\t4 -> 6;\n\t\t6 [label=\"10\"];\n\t\t6 -> 7;\n\t\t7 [label=\"11\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(8, heap.min(), "You returned wrong value of min! Expected <8> but got <" + heap.min() + ">");


        heap.insert(7, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t}\n\t0 -> 8;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(7, heap.min(), "You returned wrong value of min! Expected <7> but got <" + heap.min() + ">");


        heap.insert(6, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"6\"];\n\t}\n\t0 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"6\"];\n\t\t8 -> 9;\n\t\t9 [label=\"7\"];\n\t}\n\t0 -> 8;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(6, heap.min(), "You returned wrong value of min! Expected <6> but got <" + heap.min() + ">");


        heap.insert(5, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"6\"];\n\t\t8 -> 9;\n\t\t9 [label=\"7\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"5\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(5, heap.min(), "You returned wrong value of min! Expected <5> but got <" + heap.min() + ">");


        heap.insert(4, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"6\"];\n\t\t8 -> 9;\n\t\t9 [label=\"7\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"5\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"6\"];\n\t\t8 -> 9;\n\t\t9 [label=\"7\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"4\"];\n\t\t10 -> 11;\n\t\t11 [label=\"5\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"4\"];\n\t\t8 -> 9;\n\t\t9 [label=\"5\"];\n\t\t8 -> 10;\n\t\t10 [label=\"6\"];\n\t\t10 -> 11;\n\t\t11 [label=\"7\"];\n\t}\n\t0 -> 8;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(4, heap.min(), "You returned wrong value of min! Expected <4> but got <" + heap.min() + ">");


        heap.insert(3, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"4\"];\n\t\t8 -> 9;\n\t\t9 [label=\"5\"];\n\t\t8 -> 10;\n\t\t10 [label=\"6\"];\n\t\t10 -> 11;\n\t\t11 [label=\"7\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(3, heap.min(), "You returned wrong value of min! Expected <3> but got <" + heap.min() + ">");


        heap.insert(2, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"4\"];\n\t\t8 -> 9;\n\t\t9 [label=\"5\"];\n\t\t8 -> 10;\n\t\t10 [label=\"6\"];\n\t\t10 -> 11;\n\t\t11 [label=\"7\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t}\n\tsubgraph cluster_13 {\n\t\t13 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 13;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"4\"];\n\t\t8 -> 9;\n\t\t9 [label=\"5\"];\n\t\t8 -> 10;\n\t\t10 [label=\"6\"];\n\t\t10 -> 11;\n\t\t11 [label=\"7\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"2\"];\n\t\t12 -> 13;\n\t\t13 [label=\"3\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(2, heap.min(), "You returned wrong value of min! Expected <2> but got <" + heap.min() + ">");


        heap.insert(1, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"4\"];\n\t\t8 -> 9;\n\t\t9 [label=\"5\"];\n\t\t8 -> 10;\n\t\t10 [label=\"6\"];\n\t\t10 -> 11;\n\t\t11 [label=\"7\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"2\"];\n\t\t12 -> 13;\n\t\t13 [label=\"3\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"1\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");


        heap.insert(0, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"4\"];\n\t\t8 -> 9;\n\t\t9 [label=\"5\"];\n\t\t8 -> 10;\n\t\t10 [label=\"6\"];\n\t\t10 -> 11;\n\t\t11 [label=\"7\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"2\"];\n\t\t12 -> 13;\n\t\t13 [label=\"3\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"1\"];\n\t}\n\tsubgraph cluster_15 {\n\t\t15 [label=\"0\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 15;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"4\"];\n\t\t8 -> 9;\n\t\t9 [label=\"5\"];\n\t\t8 -> 10;\n\t\t10 [label=\"6\"];\n\t\t10 -> 11;\n\t\t11 [label=\"7\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"2\"];\n\t\t12 -> 13;\n\t\t13 [label=\"3\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"0\"];\n\t\t14 -> 15;\n\t\t15 [label=\"1\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"4\"];\n\t\t8 -> 9;\n\t\t9 [label=\"5\"];\n\t\t8 -> 10;\n\t\t10 [label=\"6\"];\n\t\t10 -> 11;\n\t\t11 [label=\"7\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"0\"];\n\t\t12 -> 13;\n\t\t13 [label=\"1\"];\n\t\t12 -> 14;\n\t\t14 [label=\"2\"];\n\t\t14 -> 15;\n\t\t15 [label=\"3\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"8\"];\n\t\t0 -> 1;\n\t\t1 [label=\"9\"];\n\t\t0 -> 2;\n\t\t2 [label=\"10\"];\n\t\t2 -> 3;\n\t\t3 [label=\"11\"];\n\t\t0 -> 4;\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"13\"];\n\t\t4 -> 6;\n\t\t6 [label=\"14\"];\n\t\t6 -> 7;\n\t\t7 [label=\"15\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"0\"];\n\t\t8 -> 9;\n\t\t9 [label=\"1\"];\n\t\t8 -> 10;\n\t\t10 [label=\"2\"];\n\t\t10 -> 11;\n\t\t11 [label=\"3\"];\n\t\t8 -> 12;\n\t\t12 [label=\"4\"];\n\t\t12 -> 13;\n\t\t13 [label=\"5\"];\n\t\t12 -> 14;\n\t\t14 [label=\"6\"];\n\t\t14 -> 15;\n\t\t15 [label=\"7\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"0\"];\n\t\t0 -> 1;\n\t\t1 [label=\"1\"];\n\t\t0 -> 2;\n\t\t2 [label=\"2\"];\n\t\t2 -> 3;\n\t\t3 [label=\"3\"];\n\t\t0 -> 4;\n\t\t4 [label=\"4\"];\n\t\t4 -> 5;\n\t\t5 [label=\"5\"];\n\t\t4 -> 6;\n\t\t6 [label=\"6\"];\n\t\t6 -> 7;\n\t\t7 [label=\"7\"];\n\t\t0 -> 8;\n\t\t8 [label=\"8\"];\n\t\t8 -> 9;\n\t\t9 [label=\"9\"];\n\t\t8 -> 10;\n\t\t10 [label=\"10\"];\n\t\t10 -> 11;\n\t\t11 [label=\"11\"];\n\t\t8 -> 12;\n\t\t12 [label=\"12\"];\n\t\t12 -> 13;\n\t\t13 [label=\"13\"];\n\t\t12 -> 14;\n\t\t14 [label=\"14\"];\n\t\t14 -> 15;\n\t\t15 [label=\"15\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(0, heap.min(), "You returned wrong value of min! Expected <0> but got <" + heap.min() + ">");


        heap.insert(-1, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"0\"];\n\t\t0 -> 1;\n\t\t1 [label=\"1\"];\n\t\t0 -> 2;\n\t\t2 [label=\"2\"];\n\t\t2 -> 3;\n\t\t3 [label=\"3\"];\n\t\t0 -> 4;\n\t\t4 [label=\"4\"];\n\t\t4 -> 5;\n\t\t5 [label=\"5\"];\n\t\t4 -> 6;\n\t\t6 [label=\"6\"];\n\t\t6 -> 7;\n\t\t7 [label=\"7\"];\n\t\t0 -> 8;\n\t\t8 [label=\"8\"];\n\t\t8 -> 9;\n\t\t9 [label=\"9\"];\n\t\t8 -> 10;\n\t\t10 [label=\"10\"];\n\t\t10 -> 11;\n\t\t11 [label=\"11\"];\n\t\t8 -> 12;\n\t\t12 [label=\"12\"];\n\t\t12 -> 13;\n\t\t13 [label=\"13\"];\n\t\t12 -> 14;\n\t\t14 [label=\"14\"];\n\t\t14 -> 15;\n\t\t15 [label=\"15\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-1\"];\n\t}\n\t0 -> 16;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-1, heap.min(), "You returned wrong value of min! Expected <-1> but got <" + heap.min() + ">");


        heap.insert(-2, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"0\"];\n\t\t0 -> 1;\n\t\t1 [label=\"1\"];\n\t\t0 -> 2;\n\t\t2 [label=\"2\"];\n\t\t2 -> 3;\n\t\t3 [label=\"3\"];\n\t\t0 -> 4;\n\t\t4 [label=\"4\"];\n\t\t4 -> 5;\n\t\t5 [label=\"5\"];\n\t\t4 -> 6;\n\t\t6 [label=\"6\"];\n\t\t6 -> 7;\n\t\t7 [label=\"7\"];\n\t\t0 -> 8;\n\t\t8 [label=\"8\"];\n\t\t8 -> 9;\n\t\t9 [label=\"9\"];\n\t\t8 -> 10;\n\t\t10 [label=\"10\"];\n\t\t10 -> 11;\n\t\t11 [label=\"11\"];\n\t\t8 -> 12;\n\t\t12 [label=\"12\"];\n\t\t12 -> 13;\n\t\t13 [label=\"13\"];\n\t\t12 -> 14;\n\t\t14 [label=\"14\"];\n\t\t14 -> 15;\n\t\t15 [label=\"15\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-1\"];\n\t}\n\tsubgraph cluster_17 {\n\t\t17 [label=\"-2\"];\n\t}\n\t0 -> 16;\n\t16 -> 17;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"0\"];\n\t\t0 -> 1;\n\t\t1 [label=\"1\"];\n\t\t0 -> 2;\n\t\t2 [label=\"2\"];\n\t\t2 -> 3;\n\t\t3 [label=\"3\"];\n\t\t0 -> 4;\n\t\t4 [label=\"4\"];\n\t\t4 -> 5;\n\t\t5 [label=\"5\"];\n\t\t4 -> 6;\n\t\t6 [label=\"6\"];\n\t\t6 -> 7;\n\t\t7 [label=\"7\"];\n\t\t0 -> 8;\n\t\t8 [label=\"8\"];\n\t\t8 -> 9;\n\t\t9 [label=\"9\"];\n\t\t8 -> 10;\n\t\t10 [label=\"10\"];\n\t\t10 -> 11;\n\t\t11 [label=\"11\"];\n\t\t8 -> 12;\n\t\t12 [label=\"12\"];\n\t\t12 -> 13;\n\t\t13 [label=\"13\"];\n\t\t12 -> 14;\n\t\t14 [label=\"14\"];\n\t\t14 -> 15;\n\t\t15 [label=\"15\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-2\"];\n\t\t16 -> 17;\n\t\t17 [label=\"-1\"];\n\t}\n\t0 -> 16;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-2, heap.min(), "You returned wrong value of min! Expected <-2> but got <" + heap.min() + ">");


        heap.insert(-3, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"0\"];\n\t\t0 -> 1;\n\t\t1 [label=\"1\"];\n\t\t0 -> 2;\n\t\t2 [label=\"2\"];\n\t\t2 -> 3;\n\t\t3 [label=\"3\"];\n\t\t0 -> 4;\n\t\t4 [label=\"4\"];\n\t\t4 -> 5;\n\t\t5 [label=\"5\"];\n\t\t4 -> 6;\n\t\t6 [label=\"6\"];\n\t\t6 -> 7;\n\t\t7 [label=\"7\"];\n\t\t0 -> 8;\n\t\t8 [label=\"8\"];\n\t\t8 -> 9;\n\t\t9 [label=\"9\"];\n\t\t8 -> 10;\n\t\t10 [label=\"10\"];\n\t\t10 -> 11;\n\t\t11 [label=\"11\"];\n\t\t8 -> 12;\n\t\t12 [label=\"12\"];\n\t\t12 -> 13;\n\t\t13 [label=\"13\"];\n\t\t12 -> 14;\n\t\t14 [label=\"14\"];\n\t\t14 -> 15;\n\t\t15 [label=\"15\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-2\"];\n\t\t16 -> 17;\n\t\t17 [label=\"-1\"];\n\t}\n\tsubgraph cluster_18 {\n\t\t18 [label=\"-3\"];\n\t}\n\t0 -> 16;\n\t16 -> 18;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-3, heap.min(), "You returned wrong value of min! Expected <-3> but got <" + heap.min() + ">");

    }

    /**
     * Tests if randomly numbers added result in expected behaviour or not. <br>
     * Both positive and negative integers are added! <br>
     * Tests both {@link BinomialHeap#insert(int, Result)} and {@link BinomialHeap#min()} <br> <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void randomInsert() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult = new FastResult();
        List<String> expectedLogs;


        heap.insert(248, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"248\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(248, heap.min(), "You returned wrong value of min! Expected <248> but got <" + heap.min() + ">");


        heap.insert(-640, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"248\"];\n\t}\n\tsubgraph cluster_1 {\n\t\t1 [label=\"-640\"];\n\t}\n\t0 -> 1;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(-263, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"-263\"];\n\t}\n\t0 -> 2;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(335, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"-263\"];\n\t}\n\tsubgraph cluster_3 {\n\t\t3 [label=\"335\"];\n\t}\n\t0 -> 2;\n\t2 -> 3;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(405, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"405\"];\n\t}\n\t0 -> 4;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(-78, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"405\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"-78\"];\n\t}\n\t0 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\t0 -> 4;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(837, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"837\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(987, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"837\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"987\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(-344, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t}\n\t0 -> 8;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(997, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\t0 -> 8;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(591, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"591\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(-305, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"591\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"-305\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\t0 -> 8;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(246, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(492, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t}\n\tsubgraph cluster_13 {\n\t\t13 [label=\"492\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 13;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t\t12 -> 13;\n\t\t13 [label=\"492\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(955, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t\t12 -> 13;\n\t\t13 [label=\"492\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");


        heap.insert(-799, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t\t12 -> 13;\n\t\t13 [label=\"492\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"955\"];\n\t}\n\tsubgraph cluster_15 {\n\t\t15 [label=\"-799\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 15;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t\t12 -> 13;\n\t\t13 [label=\"492\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"-799\"];\n\t\t14 -> 15;\n\t\t15 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-799\"];\n\t\t12 -> 13;\n\t\t13 [label=\"955\"];\n\t\t12 -> 14;\n\t\t14 [label=\"246\"];\n\t\t14 -> 15;\n\t\t15 [label=\"492\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-799\"];\n\t\t8 -> 9;\n\t\t9 [label=\"955\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-344\"];\n\t\t12 -> 13;\n\t\t13 [label=\"997\"];\n\t\t12 -> 14;\n\t\t14 [label=\"-305\"];\n\t\t14 -> 15;\n\t\t15 [label=\"591\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-799, heap.min(), "You returned wrong value of min! Expected <-799> but got <" + heap.min() + ">");


        heap.insert(321, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"321\"];\n\t}\n\t0 -> 16;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-799, heap.min(), "You returned wrong value of min! Expected <-799> but got <" + heap.min() + ">");


        heap.insert(-241, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"321\"];\n\t}\n\tsubgraph cluster_17 {\n\t\t17 [label=\"-241\"];\n\t}\n\t0 -> 16;\n\t16 -> 17;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\t0 -> 16;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-799, heap.min(), "You returned wrong value of min! Expected <-799> but got <" + heap.min() + ">");


        heap.insert(-650, fastResult);
        expectedLogs = new ArrayList<>();
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\tsubgraph cluster_18 {\n\t\t18 [label=\"-650\"];\n\t}\n\t0 -> 16;\n\t16 -> 18;\n}");

        if (expectedLogs.size() < fastResult.getLastLog().size()) {
            fail("You logged too much");
        } else if (expectedLogs.size() > fastResult.getLastLog().size()) {
            fail("You didn't log enough");
        }
        assertEquals(expectedLogs, fastResult.getLastLog());
        assertEquals(-799, heap.min(), "You returned wrong value of min! Expected <-799> but got <" + heap.min() + ">");

    }


    /**
     * Alright, this is the code I've used to generate the tests. <br>
     * The explanations are inside the code itself <br>
     * <b>Warning: It's a nightmare to understand this</b>
     * @author Aamin
     */
    private void generateInsertCode() {
        //Initialising all the variables
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult = new FastResult();
        //List<String> expectedLogs = new ArrayList<>();    //I thought of this idea but instead I directly used it from fastResult.getLogs()
        List<Integer> numbersAdded = new ArrayList<>();     //The list of numbers in the order in which they are added
        Random random = new Random(696969);             //Nice randomness to add random numbers
        int randomAddition;

        //Print the initialisation of the variables
        System.out.println("BinomialHeap heap = new BinomialHeap();");
        System.out.println("FastResult fastResult = new FastResult();");
        System.out.println("List<String> expectedLogs;");
        System.out.println();

        //Insert random numbers
        for (int i = 1; i < 20; i++) {
            randomAddition = random.nextInt(2000) - 1000;
            //Set randomAddition to i or i + const to have insertions in ascending/descending order
            numbersAdded.add(randomAddition);
            heap.insert(randomAddition, fastResult);
        }

        System.out.println();
        //The fastResult.getLogs() has the 0th element always as empty because of the structure, so starting iterations with 1
        for (int i = 1; i < fastResult.getLogs().size(); i++) {
            //Reprint the insertions now
            System.out.println("heap.insert(" + numbersAdded.get(i - 1) + ", fastResult);");
            System.out.println("expectedLogs = new ArrayList<>();");
            //Get the logs at ith index and add it to the expectedLogs
            for (int j = 0; j < fastResult.getLogs().get(i).size(); j++) {
                System.out.println("expectedLogs.add(\""
                        + fastResult.getLogs().get(i).get(j)
                        //The reason I'm doing replaceAll newlines and tabs with the respective backslash notations is if the student has a different tab spacing set
                        //This makes it more convenient to remove all the uncertainties
                        .replaceAll("\n", "\\\\n")      //For some reason I had to add 4 backslashes for it to print 1 backslash, don't know why
                        .replaceAll("\t", "\\\\t")      //Same
                        .replaceAll("\"", "\\\\\"")     //Had to print 5 here otherwise it just straight up wouldn't print
                        + "\");");
            }
            //Print if it was logged too much or too little
            System.out.print("\nif (expectedLogs.size() < fastResult.getLastLog().size()) {\n\tfail(\"You logged too much\");\n} ");
            System.out.println("else if (expectedLogs.size() > fastResult.getLastLog().size()) {\n\tfail(\"You didn't log enough\");\n}");
            //Assert the expected logs
            System.out.println("assertEquals(expectedLogs, fastResult.getLastLog());");
            //Assert the min value, use findMinimum for it
            System.out.println("assertEquals("
                    + findMinimum(numbersAdded, i - 1)
                    + ", heap.min(), \"You returned wrong value of min! Expected <" + findMinimum(numbersAdded, i - 1) + "> but got <\" + heap.min() + \">\");");
            System.out.println();
            System.out.println();
        }

    }

    /**
     * This function returns the smallest element of a list in the first i number of elements where are considered.
     * @param numbersAdded The Integer list of all the numbers in the order they were added
     * @param i The number of elements to be considered
     * @return the smallest element in that list
     * @author Aamin
     */
    private int findMinimum(List<Integer> numbersAdded, int i) {
        int smallest = numbersAdded.get(0);

        for (int j = 0; j <= i; j++) {
            if (numbersAdded.get(j) < smallest) {
                smallest = numbersAdded.get(j);
            }
        }

        return smallest;
    }

}
