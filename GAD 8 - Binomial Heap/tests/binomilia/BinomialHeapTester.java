package tests.binomilia;

import gad.binomilia.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <i> Note: It is too difficult to create hard coded tests which test if the your binomial tree is a valid tree or not
 * without actually comparing it with a proper implementation. <br>
 * Therefore it is very likely that your insertions are correct, however my tests expect a different order. <br>
 * My FastResult automatically sorts the nodes based on their ranks in decreasing order (and if the rank is same, based on their min values). <br>
 * In that case you'll have to manually compare both the answers and manually go ahead! <br>
 * Sorry for that but I don't have enough time to create perfect tests. If you want to improve it, you're gladly welcome to do so. <br>
 * If I tried making an algorithm which actually compared if the tree is valid or not, it would be a very obfuscated recursive one
 * which wouldn't have helped at all. However, with Strings it is very easy to visualise what it looks like, which is my motive behind the tests. <br>
 * @see <a href="http://webgraphviz.com">Graph visualiser</a> If you fail a test, and compare the differences yourself. <br>
 * @see <a href="https://zulip.in.tum.de/#narrow/stream/442-GAD-E08-Binomial.20Heap/topic/Sortieren/near/210373"> The order of the nodes within a list should not matter </a>
 * </i>
 * @author Aamin
 */
public class BinomialHeapTester {

    /**
     * <p>
     * Simple tests to see if numbers can be added in ascending order in the range [1, 20) or not. <br>
     * Only positive integers are added! <br>
     * </p>
     * Tests both {@link BinomialHeap#insert(int, Result)} and {@link BinomialHeap#min()} <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void simpleInsert() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult;
        List<String> expectedLogs;

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(1, fastResult);
        expectedLogs.add("digraph {\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t}\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(2, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t}\n\tsubgraph cluster_1 {\n\t\t1 [label=\"2\"];\n\t}\n\t0 -> 1;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(3, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"3\"];\n\t}\n\t0 -> 2;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(4, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"3\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"3\"];\n\t}\n\tsubgraph cluster_3 {\n\t\t3 [label=\"4\"];\n\t}\n\t0 -> 2;\n\t2 -> 3;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(5, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t}\n\t0 -> 4;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(6, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"6\"];\n\t}\n\t0 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\t0 -> 4;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(7, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"7\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(8, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"7\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"7\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"8\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(9, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(10, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"10\"];\n\t}\n\t0 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(11, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"11\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(12, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"11\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"11\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"12\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(13, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(14, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t}\n\tsubgraph cluster_13 {\n\t\t13 [label=\"14\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 13;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(15, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"15\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(16, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"15\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"15\"];\n\t}\n\tsubgraph cluster_15 {\n\t\t15 [label=\"16\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 15;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(17, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t}\n\t0 -> 16;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(18, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t}\n\tsubgraph cluster_17 {\n\t\t17 [label=\"18\"];\n\t}\n\t0 -> 16;\n\t16 -> 17;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t\t16 -> 17;\n\t\t17 [label=\"18\"];\n\t}\n\t0 -> 16;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(19, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t\t16 -> 17;\n\t\t17 [label=\"18\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t\t16 -> 17;\n\t\t17 [label=\"18\"];\n\t}\n\tsubgraph cluster_18 {\n\t\t18 [label=\"19\"];\n\t}\n\t0 -> 16;\n\t16 -> 18;\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());
    }

    /**
     * <p>
     * Simple tests to see if numbers can be added in descending order in the range [-2. 16] or not <br>
     * Both positive and negative integers are added! <br>
     * </p>
     * Tests both {@link BinomialHeap#insert(int, Result)} and {@link BinomialHeap#min()} <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void simpleInsertReverse() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult;
        List<String> expectedLogs;

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(16, fastResult);
        expectedLogs.add("digraph {\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"16\"];\n\t}\n}");

        assertEquals(16, heap.min(), "You returned wrong value of min! Expected <16> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(15, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t}\n\tsubgraph cluster_1 {\n\t\t1 [label=\"16\"];\n\t}\n\t0 -> 1;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n}");

        assertEquals(15, heap.min(), "You returned wrong value of min! Expected <15> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(14, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"14\"];\n\t}\n\t0 -> 2;\n}");

        assertEquals(14, heap.min(), "You returned wrong value of min! Expected <14> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(13, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"14\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"13\"];\n\t}\n\tsubgraph cluster_3 {\n\t\t3 [label=\"14\"];\n\t}\n\t0 -> 2;\n\t2 -> 3;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n}");

        assertEquals(13, heap.min(), "You returned wrong value of min! Expected <13> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(12, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"12\"];\n\t}\n\t0 -> 4;\n}");

        assertEquals(12, heap.min(), "You returned wrong value of min! Expected <12> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(11, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"12\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"12\"];\n\t}\n\t0 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\t0 -> 4;\n}");

        assertEquals(11, heap.min(), "You returned wrong value of min! Expected <11> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(10, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"10\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        assertEquals(10, heap.min(), "You returned wrong value of min! Expected <10> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(9, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"10\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"9\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"10\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"9\"];\n\t\t4 -> 5;\n\t\t5 [label=\"10\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"11\"];\n\t\t6 -> 7;\n\t\t7 [label=\"12\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n}");

        assertEquals(9, heap.min(), "You returned wrong value of min! Expected <9> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(8, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(8, heap.min(), "You returned wrong value of min! Expected <8> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(7, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"8\"];\n\t}\n\t0 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(7, heap.min(), "You returned wrong value of min! Expected <7> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(6, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"6\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");

        assertEquals(6, heap.min(), "You returned wrong value of min! Expected <6> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(5, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"6\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"5\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"6\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(5, heap.min(), "You returned wrong value of min! Expected <5> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(4, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(4, heap.min(), "You returned wrong value of min! Expected <4> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(3, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t}\n\tsubgraph cluster_13 {\n\t\t13 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 13;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(3, heap.min(), "You returned wrong value of min! Expected <3> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(2, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");

        assertEquals(2, heap.min(), "You returned wrong value of min! Expected <2> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(1, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"1\"];\n\t}\n\tsubgraph cluster_15 {\n\t\t15 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 15;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"1\"];\n\t\t12 -> 13;\n\t\t13 [label=\"2\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"3\"];\n\t\t14 -> 15;\n\t\t15 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"1\"];\n\t\t8 -> 9;\n\t\t9 [label=\"2\"];\n\t\t8 -> 10;\n\t\t10 [label=\"3\"];\n\t\t10 -> 11;\n\t\t11 [label=\"4\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"5\"];\n\t\t12 -> 13;\n\t\t13 [label=\"6\"];\n\t\t12 -> 14;\n\t\t14 [label=\"7\"];\n\t\t14 -> 15;\n\t\t15 [label=\"8\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n}");

        assertEquals(1, heap.min(), "You returned wrong value of min! Expected <1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(0, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"0\"];\n\t}\n\t0 -> 16;\n}");

        assertEquals(0, heap.min(), "You returned wrong value of min! Expected <0> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-1, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"0\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-1\"];\n\t}\n\tsubgraph cluster_17 {\n\t\t17 [label=\"0\"];\n\t}\n\t0 -> 16;\n\t16 -> 17;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-1\"];\n\t\t16 -> 17;\n\t\t17 [label=\"0\"];\n\t}\n\t0 -> 16;\n}");

        assertEquals(-1, heap.min(), "You returned wrong value of min! Expected <-1> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-2, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-1\"];\n\t\t16 -> 17;\n\t\t17 [label=\"0\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-1\"];\n\t\t16 -> 17;\n\t\t17 [label=\"0\"];\n\t}\n\tsubgraph cluster_18 {\n\t\t18 [label=\"-2\"];\n\t}\n\t0 -> 16;\n\t16 -> 18;\n}");

        assertEquals(-2, heap.min(), "You returned wrong value of min! Expected <-2> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());
    }

    /**
     * <p>
     * Tests if randomly numbers added result in expected behaviour or not. <br>
     * Both positive and negative integers are added! <br>
     * Adds numbers in the range of [-1000, 1000] using the pseudo seed 696969 <br>
     * </p>
     * Tests both {@link BinomialHeap#insert(int, Result)} and {@link BinomialHeap#min()} <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void randomInsert() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult;
        List<String> expectedLogs;

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(248, fastResult);
        expectedLogs.add("digraph {\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"248\"];\n\t}\n}");

        assertEquals(248, heap.min(), "You returned wrong value of min! Expected <248> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-640, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"248\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t}\n\tsubgraph cluster_1 {\n\t\t1 [label=\"248\"];\n\t}\n\t0 -> 1;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-263, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"-263\"];\n\t}\n\t0 -> 2;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(335, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"-263\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"-263\"];\n\t}\n\tsubgraph cluster_3 {\n\t\t3 [label=\"335\"];\n\t}\n\t0 -> 2;\n\t2 -> 3;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(405, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"405\"];\n\t}\n\t0 -> 4;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-78, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"405\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"405\"];\n\t}\n\t0 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\t0 -> 4;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(837, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"837\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(987, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"837\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"837\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"987\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-344, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(997, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(591, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"591\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-305, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"591\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-305\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"591\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(246, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(492, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t}\n\tsubgraph cluster_13 {\n\t\t13 [label=\"492\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 13;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t\t12 -> 13;\n\t\t13 [label=\"492\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(955, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t\t12 -> 13;\n\t\t13 [label=\"492\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t\t12 -> 13;\n\t\t13 [label=\"492\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");

        assertEquals(-640, heap.min(), "You returned wrong value of min! Expected <-640> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-799, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t\t12 -> 13;\n\t\t13 [label=\"492\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"246\"];\n\t\t12 -> 13;\n\t\t13 [label=\"492\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"-799\"];\n\t}\n\tsubgraph cluster_15 {\n\t\t15 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 15;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-799\"];\n\t\t12 -> 13;\n\t\t13 [label=\"955\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"246\"];\n\t\t14 -> 15;\n\t\t15 [label=\"492\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-799\"];\n\t\t8 -> 9;\n\t\t9 [label=\"955\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-344\"];\n\t\t12 -> 13;\n\t\t13 [label=\"997\"];\n\t\t12 -> 14;\n\t\t14 [label=\"-305\"];\n\t\t14 -> 15;\n\t\t15 [label=\"591\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n}");

        assertEquals(-799, heap.min(), "You returned wrong value of min! Expected <-799> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(321, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"321\"];\n\t}\n\t0 -> 16;\n}");

        assertEquals(-799, heap.min(), "You returned wrong value of min! Expected <-799> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-241, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"321\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t}\n\tsubgraph cluster_17 {\n\t\t17 [label=\"321\"];\n\t}\n\t0 -> 16;\n\t16 -> 17;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\t0 -> 16;\n}");

        assertEquals(-799, heap.min(), "You returned wrong value of min! Expected <-799> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-650, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\tsubgraph cluster_18 {\n\t\t18 [label=\"-650\"];\n\t}\n\t0 -> 16;\n\t16 -> 18;\n}");

        assertEquals(-799, heap.min(), "You returned wrong value of min! Expected <-799> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());
    }

    /**
     * <p>
     * Simple tests to see if the code throws an exception when possible errors could occur.<br>
     * </p>
     * Tests {@link BinomialHeap#deleteMin(Result)} and {@link BinomialHeap#min()} <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void exceptions() {
        BinomialHeap binomilia = new BinomialHeap();

        assertThrows(NoSuchElementException.class, binomilia::min);

        binomilia.insert(69, new FastResult());
        binomilia.deleteMin(new FastResult());
        assertThrows(NoSuchElementException.class, () -> binomilia.deleteMin(new FastResult()));
        assertThrows(NoSuchElementException.class, binomilia::min);
    }

    /**
     * <p>
     * Simple tests to see if numbers can be remove after being added in ascending order in the range [1, 20) or not. <br>
     * Only positive integers are added! <br>
     * Deletes the minimum element until can't no more
     * </p>
     * This test works only when {@link BinomialHeap#insert(int, Result)} has been implemented and then tests based on that
     * both {@link BinomialHeap#deleteMin(Result)} and {@link BinomialHeap#min()} <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void simpleDelete() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult;
        List<String> expectedLogs;
        int deletedMin;

        for (int i = 1; i <= 19; i++) {
            heap.insert(i, new FastResult());
        }

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t\t16 -> 17;\n\t\t17 [label=\"18\"];\n\t}\n\tsubgraph cluster_18 {\n\t\t18 [label=\"19\"];\n\t}\n\t0 -> 16;\n\t16 -> 18;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"17\"];\n\t\t14 -> 15;\n\t\t15 [label=\"18\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"2\"];\n\t}\n\tsubgraph cluster_17 {\n\t\t17 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 16;\n\t16 -> 17;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"2\"];\n\t\t12 -> 13;\n\t\t13 [label=\"19\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"3\"];\n\t\t14 -> 15;\n\t\t15 [label=\"4\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t\t16 -> 17;\n\t\t17 [label=\"18\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"2\"];\n\t\t8 -> 9;\n\t\t9 [label=\"19\"];\n\t\t8 -> 10;\n\t\t10 [label=\"3\"];\n\t\t10 -> 11;\n\t\t11 [label=\"4\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"5\"];\n\t\t12 -> 13;\n\t\t13 [label=\"6\"];\n\t\t12 -> 14;\n\t\t14 [label=\"7\"];\n\t\t14 -> 15;\n\t\t15 [label=\"8\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t\t16 -> 17;\n\t\t17 [label=\"18\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"2\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t\t16 -> 17;\n\t\t17 [label=\"18\"];\n\t}\n\t0 -> 8;\n\t8 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"2\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t\t16 -> 17;\n\t\t17 [label=\"18\"];\n\t}\n\t0 -> 16;\n}");
        assertEquals(1, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<1> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"2\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"17\"];\n\t\t16 -> 17;\n\t\t17 [label=\"18\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"17\"];\n\t\t14 -> 15;\n\t\t15 [label=\"18\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"17\"];\n\t\t14 -> 15;\n\t\t15 [label=\"18\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"3\"];\n\t\t8 -> 9;\n\t\t9 [label=\"4\"];\n\t\t8 -> 10;\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"5\"];\n\t\t12 -> 13;\n\t\t13 [label=\"6\"];\n\t\t12 -> 14;\n\t\t14 [label=\"7\"];\n\t\t14 -> 15;\n\t\t15 [label=\"8\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"3\"];\n\t\t0 -> 1;\n\t\t1 [label=\"4\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"3\"];\n\t\t0 -> 1;\n\t\t1 [label=\"4\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"19\"];\n\t}\n\t0 -> 16;\n}");
        assertEquals(2, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<2> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"3\"];\n\t\t0 -> 1;\n\t\t1 [label=\"4\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"19\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"17\"];\n\t\t12 -> 13;\n\t\t13 [label=\"18\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"4\"];\n\t}\n\tsubgraph cluster_15 {\n\t\t15 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 15;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"4\"];\n\t\t12 -> 13;\n\t\t13 [label=\"19\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"17\"];\n\t\t14 -> 15;\n\t\t15 [label=\"18\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"4\"];\n\t\t8 -> 9;\n\t\t9 [label=\"19\"];\n\t\t8 -> 10;\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"5\"];\n\t\t12 -> 13;\n\t\t13 [label=\"6\"];\n\t\t12 -> 14;\n\t\t14 [label=\"7\"];\n\t\t14 -> 15;\n\t\t15 [label=\"8\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"4\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"4\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n}");
        assertEquals(3, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<3> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"4\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"17\"];\n\t\t12 -> 13;\n\t\t13 [label=\"18\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"17\"];\n\t\t12 -> 13;\n\t\t13 [label=\"18\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"17\"];\n\t\t12 -> 13;\n\t\t13 [label=\"18\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"17\"];\n\t\t12 -> 13;\n\t\t13 [label=\"18\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"17\"];\n\t\t12 -> 13;\n\t\t13 [label=\"18\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        assertEquals(4, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<4> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"17\"];\n\t\t12 -> 13;\n\t\t13 [label=\"18\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"6\"];\n\t}\n\tsubgraph cluster_13 {\n\t\t13 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 12;\n\t12 -> 13;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"6\"];\n\t\t8 -> 9;\n\t\t9 [label=\"19\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"17\"];\n\t\t12 -> 13;\n\t\t13 [label=\"18\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t\t8 -> 10;\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"6\"];\n\t\t12 -> 13;\n\t\t13 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        assertEquals(5, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<5> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t\t8 -> 10;\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"6\"];\n\t\t12 -> 13;\n\t\t13 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t\t8 -> 10;\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t\t8 -> 10;\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        assertEquals(6, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<6> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t\t8 -> 10;\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"17\"];\n\t\t8 -> 9;\n\t\t9 [label=\"18\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"8\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"8\"];\n\t\t8 -> 9;\n\t\t9 [label=\"19\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"8\"];\n\t\t8 -> 9;\n\t\t9 [label=\"19\"];\n\t\t8 -> 10;\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(7, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<7> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"8\"];\n\t\t8 -> 9;\n\t\t9 [label=\"19\"];\n\t\t8 -> 10;\n\t\t10 [label=\"17\"];\n\t\t10 -> 11;\n\t\t11 [label=\"18\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"17\"];\n\t\t8 -> 9;\n\t\t9 [label=\"18\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"17\"];\n\t\t8 -> 9;\n\t\t9 [label=\"18\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"17\"];\n\t\t8 -> 9;\n\t\t9 [label=\"18\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        assertEquals(8, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<8> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"17\"];\n\t\t8 -> 9;\n\t\t9 [label=\"18\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"19\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"17\"];\n\t\t6 -> 7;\n\t\t7 [label=\"18\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"10\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"19\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"19\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"11\"];\n\t\t6 -> 7;\n\t\t7 [label=\"12\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"17\"];\n\t\t8 -> 9;\n\t\t9 [label=\"18\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"11\"];\n\t\t0 -> 1;\n\t\t1 [label=\"12\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"10\"];\n\t\t8 -> 9;\n\t\t9 [label=\"19\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"11\"];\n\t\t0 -> 1;\n\t\t1 [label=\"12\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"10\"];\n\t\t8 -> 9;\n\t\t9 [label=\"19\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(9, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<9> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"11\"];\n\t\t0 -> 1;\n\t\t1 [label=\"12\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"10\"];\n\t\t8 -> 9;\n\t\t9 [label=\"19\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"11\"];\n\t\t0 -> 1;\n\t\t1 [label=\"12\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"19\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"11\"];\n\t\t0 -> 1;\n\t\t1 [label=\"12\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"19\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(10, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<10> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"11\"];\n\t\t0 -> 1;\n\t\t1 [label=\"12\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"19\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"17\"];\n\t\t4 -> 5;\n\t\t5 [label=\"18\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"12\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"19\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"12\"];\n\t\t4 -> 5;\n\t\t5 [label=\"19\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"17\"];\n\t\t6 -> 7;\n\t\t7 [label=\"18\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n}");
        assertEquals(11, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<11> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"12\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"17\"];\n\t\t4 -> 5;\n\t\t5 [label=\"18\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"19\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"17\"];\n\t\t4 -> 5;\n\t\t5 [label=\"18\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"19\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"17\"];\n\t\t4 -> 5;\n\t\t5 [label=\"18\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"19\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"17\"];\n\t\t4 -> 5;\n\t\t5 [label=\"18\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"19\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        assertEquals(12, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<12> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"17\"];\n\t\t4 -> 5;\n\t\t5 [label=\"18\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"19\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"14\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"19\"];\n\t}\n\t0 -> 2;\n\t2 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"14\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"17\"];\n\t\t4 -> 5;\n\t\t5 [label=\"18\"];\n\t}\n\t0 -> 2;\n\t2 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"14\"];\n\t\t4 -> 5;\n\t\t5 [label=\"19\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(13, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<13> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"14\"];\n\t\t4 -> 5;\n\t\t5 [label=\"19\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"19\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"19\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(14, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<14> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"19\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"17\"];\n\t\t0 -> 1;\n\t\t1 [label=\"18\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"16\"];\n\t}\n\tsubgraph cluster_3 {\n\t\t3 [label=\"19\"];\n\t}\n\t0 -> 2;\n\t2 -> 3;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"16\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"16\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n}");
        assertEquals(15, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<15> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"16\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t\t0 -> 2;\n\t\t2 [label=\"17\"];\n\t\t2 -> 3;\n\t\t3 [label=\"18\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"17\"];\n\t\t0 -> 1;\n\t\t1 [label=\"18\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"19\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"17\"];\n\t\t0 -> 1;\n\t\t1 [label=\"18\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"19\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"17\"];\n\t\t0 -> 1;\n\t\t1 [label=\"18\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"19\"];\n\t}\n\t0 -> 2;\n}");
        assertEquals(16, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<16> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"17\"];\n\t\t0 -> 1;\n\t\t1 [label=\"18\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"19\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"18\"];\n\t}\n\tsubgraph cluster_1 {\n\t\t1 [label=\"19\"];\n\t}\n\t0 -> 1;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"18\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t}\n}");
        assertEquals(17, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<17> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"18\"];\n\t\t0 -> 1;\n\t\t1 [label=\"19\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"19\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"19\"];\n\t}\n}");
        assertEquals(18, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<18> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"19\"];\n\t}\n}");
        expectedLogs.add("digraph {\n}");
        assertEquals(19, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<19> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        assertThrows(NoSuchElementException.class, () -> heap.deleteMin(new FastResult()));
    }

    /**
     * <p>
     * Simple tests to see if numbers can be remove after being added in ascending order in the range [-2, 16) or not. <br>
     * Both positive and negative integers are added! <br>
     * Deletes the minimum element until can't no more
     * </p>
     * This test works only when {@link BinomialHeap#insert(int, Result)} has been implemented and then tests based on that
     * both {@link BinomialHeap#deleteMin(Result)} and {@link BinomialHeap#min()} <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void simpleDeleteReverse() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult;
        List<String> expectedLogs;
        int deletedMin;

        for (int i = 16; i >= -2; i--) {
            heap.insert(i, new FastResult());
        }

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-1\"];\n\t\t16 -> 17;\n\t\t17 [label=\"0\"];\n\t}\n\tsubgraph cluster_18 {\n\t\t18 [label=\"-2\"];\n\t}\n\t0 -> 16;\n\t16 -> 18;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-1\"];\n\t\t16 -> 17;\n\t\t17 [label=\"0\"];\n\t}\n\t0 -> 16;\n}");
        assertEquals(-2, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-2> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-1\"];\n\t\t16 -> 17;\n\t\t17 [label=\"0\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"0\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"0\"];\n\t}\n\t0 -> 16;\n}");
        assertEquals(-1, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-1> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"0\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n}");
        assertEquals(0, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<0> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"1\"];\n\t\t0 -> 1;\n\t\t1 [label=\"2\"];\n\t\t0 -> 2;\n\t\t2 [label=\"3\"];\n\t\t2 -> 3;\n\t\t3 [label=\"4\"];\n\t\t0 -> 4;\n\t\t4 [label=\"5\"];\n\t\t4 -> 5;\n\t\t5 [label=\"6\"];\n\t\t4 -> 6;\n\t\t6 [label=\"7\"];\n\t\t6 -> 7;\n\t\t7 [label=\"8\"];\n\t\t0 -> 8;\n\t\t8 [label=\"9\"];\n\t\t8 -> 9;\n\t\t9 [label=\"10\"];\n\t\t8 -> 10;\n\t\t10 [label=\"11\"];\n\t\t10 -> 11;\n\t\t11 [label=\"12\"];\n\t\t8 -> 12;\n\t\t12 [label=\"13\"];\n\t\t12 -> 13;\n\t\t13 [label=\"14\"];\n\t\t12 -> 14;\n\t\t14 [label=\"15\"];\n\t\t14 -> 15;\n\t\t15 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        assertEquals(1, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<1> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"2\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        assertEquals(2, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<2> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"3\"];\n\t\t12 -> 13;\n\t\t13 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        assertEquals(3, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<3> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"4\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(4, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<4> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"5\"];\n\t\t8 -> 9;\n\t\t9 [label=\"6\"];\n\t\t8 -> 10;\n\t\t10 [label=\"7\"];\n\t\t10 -> 11;\n\t\t11 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"6\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"6\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"6\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        assertEquals(5, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<5> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"6\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(6, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<6> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"7\"];\n\t\t8 -> 9;\n\t\t9 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(7, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<7> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"8\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n}");
        assertEquals(8, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<8> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"9\"];\n\t\t0 -> 1;\n\t\t1 [label=\"10\"];\n\t\t0 -> 2;\n\t\t2 [label=\"11\"];\n\t\t2 -> 3;\n\t\t3 [label=\"12\"];\n\t\t0 -> 4;\n\t\t4 [label=\"13\"];\n\t\t4 -> 5;\n\t\t5 [label=\"14\"];\n\t\t4 -> 6;\n\t\t6 [label=\"15\"];\n\t\t6 -> 7;\n\t\t7 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"10\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"10\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"10\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"10\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        assertEquals(9, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<9> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"10\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(10, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<10> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"11\"];\n\t\t4 -> 5;\n\t\t5 [label=\"12\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"12\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"12\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(11, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<11> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"12\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n}");
        assertEquals(12, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<12> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"13\"];\n\t\t0 -> 1;\n\t\t1 [label=\"14\"];\n\t\t0 -> 2;\n\t\t2 [label=\"15\"];\n\t\t2 -> 3;\n\t\t3 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"14\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"14\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"14\"];\n\t}\n\t0 -> 2;\n}");
        assertEquals(13, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<13> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"14\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n}");
        assertEquals(14, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<14> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"15\"];\n\t\t0 -> 1;\n\t\t1 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"16\"];\n\t}\n}");
        assertEquals(15, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<15> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"16\"];\n\t}\n}");
        expectedLogs.add("digraph {\n}");
        assertEquals(16, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<16> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        assertThrows(NoSuchElementException.class, () -> heap.deleteMin(new FastResult()));
    }

    /**
     * <p>
     * Tests if deleting the randomly added numbers result in expected behaviour or not. <br>
     * Both positive and negative integers are added! <br>
     * Adds numbers in the range of [-1000, 1000] using the pseudo seed 696969 <br>
     * </p>
     * This test works only when {@link BinomialHeap#insert(int, Result)} has been implemented and then tests based on that
     * both {@link BinomialHeap#deleteMin(Result)} and {@link BinomialHeap#min()} <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void deleteFromRandomInsert() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult;
        List<String> expectedLogs;
        int deletedMin;

        heap.insert(248, new FastResult());
        heap.insert(-640, new FastResult());
        heap.insert(-263, new FastResult());
        heap.insert(335, new FastResult());
        heap.insert(405, new FastResult());
        heap.insert(-78, new FastResult());
        heap.insert(837, new FastResult());
        heap.insert(987, new FastResult());
        heap.insert(-344, new FastResult());
        heap.insert(997, new FastResult());
        heap.insert(591, new FastResult());
        heap.insert(-305, new FastResult());
        heap.insert(246, new FastResult());
        heap.insert(492, new FastResult());
        heap.insert(955, new FastResult());
        heap.insert(-799, new FastResult());
        heap.insert(321, new FastResult());
        heap.insert(-241, new FastResult());
        heap.insert(-650, new FastResult());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-799\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\tsubgraph cluster_18 {\n\t\t18 [label=\"-650\"];\n\t}\n\t0 -> 16;\n\t16 -> 18;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-241\"];\n\t\t12 -> 13;\n\t\t13 [label=\"321\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"246\"];\n\t\t14 -> 15;\n\t\t15 [label=\"492\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-650\"];\n\t}\n\tsubgraph cluster_17 {\n\t\t17 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 16;\n\t16 -> 17;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-650\"];\n\t\t12 -> 13;\n\t\t13 [label=\"955\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"-241\"];\n\t\t14 -> 15;\n\t\t15 [label=\"321\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"246\"];\n\t\t16 -> 17;\n\t\t17 [label=\"492\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-650\"];\n\t\t8 -> 9;\n\t\t9 [label=\"955\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-344\"];\n\t\t12 -> 13;\n\t\t13 [label=\"997\"];\n\t\t12 -> 14;\n\t\t14 [label=\"-305\"];\n\t\t14 -> 15;\n\t\t15 [label=\"591\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-650\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\t0 -> 8;\n\t8 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-650\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\t0 -> 16;\n}");
        assertEquals(-799, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-799> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-650\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-344\"];\n\t\t4 -> 5;\n\t\t5 [label=\"997\"];\n\t\t4 -> 6;\n\t\t6 [label=\"-305\"];\n\t\t6 -> 7;\n\t\t7 [label=\"591\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-640\"];\n\t\t8 -> 9;\n\t\t9 [label=\"248\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-263\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"-241\"];\n\t\t16 -> 17;\n\t\t17 [label=\"321\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-241\"];\n\t\t12 -> 13;\n\t\t13 [label=\"321\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"246\"];\n\t\t14 -> 15;\n\t\t15 [label=\"492\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-241\"];\n\t\t12 -> 13;\n\t\t13 [label=\"321\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"246\"];\n\t\t14 -> 15;\n\t\t15 [label=\"492\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-241\"];\n\t\t12 -> 13;\n\t\t13 [label=\"321\"];\n\t\t12 -> 14;\n\t\t14 [label=\"246\"];\n\t\t14 -> 15;\n\t\t15 [label=\"492\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-241\"];\n\t\t12 -> 13;\n\t\t13 [label=\"321\"];\n\t\t12 -> 14;\n\t\t14 [label=\"246\"];\n\t\t14 -> 15;\n\t\t15 [label=\"492\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-241\"];\n\t\t12 -> 13;\n\t\t13 [label=\"321\"];\n\t\t12 -> 14;\n\t\t14 [label=\"246\"];\n\t\t14 -> 15;\n\t\t15 [label=\"492\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"955\"];\n\t}\n\t0 -> 16;\n}");
        assertEquals(-650, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-650> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-640\"];\n\t\t0 -> 1;\n\t\t1 [label=\"248\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-263\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-344\"];\n\t\t8 -> 9;\n\t\t9 [label=\"997\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-305\"];\n\t\t10 -> 11;\n\t\t11 [label=\"591\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-241\"];\n\t\t12 -> 13;\n\t\t13 [label=\"321\"];\n\t\t12 -> 14;\n\t\t14 [label=\"246\"];\n\t\t14 -> 15;\n\t\t15 [label=\"492\"];\n\t}\n\tsubgraph cluster_16 {\n\t\t16 [label=\"955\"];\n\t}\n\t0 -> 16;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-344\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-305\"];\n\t\t2 -> 3;\n\t\t3 [label=\"591\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-241\"];\n\t\t4 -> 5;\n\t\t5 [label=\"321\"];\n\t\t4 -> 6;\n\t\t6 [label=\"246\"];\n\t\t6 -> 7;\n\t\t7 [label=\"492\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-78\"];\n\t\t8 -> 9;\n\t\t9 [label=\"405\"];\n\t\t8 -> 10;\n\t\t10 [label=\"837\"];\n\t\t10 -> 11;\n\t\t11 [label=\"987\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-263\"];\n\t\t12 -> 13;\n\t\t13 [label=\"335\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"248\"];\n\t}\n\tsubgraph cluster_15 {\n\t\t15 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n\t14 -> 15;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-344\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-305\"];\n\t\t2 -> 3;\n\t\t3 [label=\"591\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-241\"];\n\t\t4 -> 5;\n\t\t5 [label=\"321\"];\n\t\t4 -> 6;\n\t\t6 [label=\"246\"];\n\t\t6 -> 7;\n\t\t7 [label=\"492\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-78\"];\n\t\t8 -> 9;\n\t\t9 [label=\"405\"];\n\t\t8 -> 10;\n\t\t10 [label=\"837\"];\n\t\t10 -> 11;\n\t\t11 [label=\"987\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-263\"];\n\t\t12 -> 13;\n\t\t13 [label=\"335\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"248\"];\n\t\t14 -> 15;\n\t\t15 [label=\"955\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-344\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-305\"];\n\t\t2 -> 3;\n\t\t3 [label=\"591\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-241\"];\n\t\t4 -> 5;\n\t\t5 [label=\"321\"];\n\t\t4 -> 6;\n\t\t6 [label=\"246\"];\n\t\t6 -> 7;\n\t\t7 [label=\"492\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-263\"];\n\t\t8 -> 9;\n\t\t9 [label=\"335\"];\n\t\t8 -> 10;\n\t\t10 [label=\"248\"];\n\t\t10 -> 11;\n\t\t11 [label=\"955\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-344\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-305\"];\n\t\t2 -> 3;\n\t\t3 [label=\"591\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-241\"];\n\t\t4 -> 5;\n\t\t5 [label=\"321\"];\n\t\t4 -> 6;\n\t\t6 [label=\"246\"];\n\t\t6 -> 7;\n\t\t7 [label=\"492\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-263\"];\n\t\t8 -> 9;\n\t\t9 [label=\"335\"];\n\t\t8 -> 10;\n\t\t10 [label=\"248\"];\n\t\t10 -> 11;\n\t\t11 [label=\"955\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-344\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-305\"];\n\t\t2 -> 3;\n\t\t3 [label=\"591\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-241\"];\n\t\t4 -> 5;\n\t\t5 [label=\"321\"];\n\t\t4 -> 6;\n\t\t6 [label=\"246\"];\n\t\t6 -> 7;\n\t\t7 [label=\"492\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-263\"];\n\t\t8 -> 9;\n\t\t9 [label=\"335\"];\n\t\t8 -> 10;\n\t\t10 [label=\"248\"];\n\t\t10 -> 11;\n\t\t11 [label=\"955\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n}");
        assertEquals(-640, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-640> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-344\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"-305\"];\n\t\t2 -> 3;\n\t\t3 [label=\"591\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-241\"];\n\t\t4 -> 5;\n\t\t5 [label=\"321\"];\n\t\t4 -> 6;\n\t\t6 [label=\"246\"];\n\t\t6 -> 7;\n\t\t7 [label=\"492\"];\n\t\t0 -> 8;\n\t\t8 [label=\"-263\"];\n\t\t8 -> 9;\n\t\t9 [label=\"335\"];\n\t\t8 -> 10;\n\t\t10 [label=\"248\"];\n\t\t10 -> 11;\n\t\t11 [label=\"955\"];\n\t\t8 -> 12;\n\t\t12 [label=\"-78\"];\n\t\t12 -> 13;\n\t\t13 [label=\"405\"];\n\t\t12 -> 14;\n\t\t14 [label=\"837\"];\n\t\t14 -> 15;\n\t\t15 [label=\"987\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-263\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"248\"];\n\t\t2 -> 3;\n\t\t3 [label=\"955\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-305\"];\n\t\t12 -> 13;\n\t\t13 [label=\"591\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-263\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"248\"];\n\t\t2 -> 3;\n\t\t3 [label=\"955\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-305\"];\n\t\t12 -> 13;\n\t\t13 [label=\"591\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-263\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"248\"];\n\t\t2 -> 3;\n\t\t3 [label=\"955\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-305\"];\n\t\t12 -> 13;\n\t\t13 [label=\"591\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-263\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"248\"];\n\t\t2 -> 3;\n\t\t3 [label=\"955\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-305\"];\n\t\t12 -> 13;\n\t\t13 [label=\"591\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-263\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"248\"];\n\t\t2 -> 3;\n\t\t3 [label=\"955\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-305\"];\n\t\t12 -> 13;\n\t\t13 [label=\"591\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        assertEquals(-344, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-344> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-263\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"248\"];\n\t\t2 -> 3;\n\t\t3 [label=\"955\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-305\"];\n\t\t12 -> 13;\n\t\t13 [label=\"591\"];\n\t}\n\tsubgraph cluster_14 {\n\t\t14 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 14;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-263\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"248\"];\n\t\t2 -> 3;\n\t\t3 [label=\"955\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"591\"];\n\t}\n\tsubgraph cluster_13 {\n\t\t13 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 13;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-263\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"248\"];\n\t\t2 -> 3;\n\t\t3 [label=\"955\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"591\"];\n\t\t12 -> 13;\n\t\t13 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        assertEquals(-305, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-305> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-263\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"248\"];\n\t\t2 -> 3;\n\t\t3 [label=\"955\"];\n\t\t0 -> 4;\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"591\"];\n\t\t12 -> 13;\n\t\t13 [label=\"997\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-241\"];\n\t\t0 -> 1;\n\t\t1 [label=\"321\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"248\"];\n\t\t8 -> 9;\n\t\t9 [label=\"955\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"591\"];\n\t\t10 -> 11;\n\t\t11 [label=\"997\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"335\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n\t8 -> 10;\n\t10 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-241\"];\n\t\t0 -> 1;\n\t\t1 [label=\"321\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"248\"];\n\t\t8 -> 9;\n\t\t9 [label=\"955\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"591\"];\n\t\t10 -> 11;\n\t\t11 [label=\"997\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"335\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n\t8 -> 10;\n\t10 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-241\"];\n\t\t0 -> 1;\n\t\t1 [label=\"321\"];\n\t\t0 -> 2;\n\t\t2 [label=\"246\"];\n\t\t2 -> 3;\n\t\t3 [label=\"492\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-78\"];\n\t\t4 -> 5;\n\t\t5 [label=\"405\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"248\"];\n\t\t8 -> 9;\n\t\t9 [label=\"955\"];\n\t\t8 -> 10;\n\t\t10 [label=\"591\"];\n\t\t10 -> 11;\n\t\t11 [label=\"997\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"335\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-78\"];\n\t\t0 -> 1;\n\t\t1 [label=\"405\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"335\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        assertEquals(-263, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-263> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-78\"];\n\t\t0 -> 1;\n\t\t1 [label=\"405\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-241\"];\n\t\t8 -> 9;\n\t\t9 [label=\"321\"];\n\t\t8 -> 10;\n\t\t10 [label=\"246\"];\n\t\t10 -> 11;\n\t\t11 [label=\"492\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"335\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-78\"];\n\t\t0 -> 1;\n\t\t1 [label=\"405\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"246\"];\n\t\t8 -> 9;\n\t\t9 [label=\"492\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"321\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"335\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-78\"];\n\t\t0 -> 1;\n\t\t1 [label=\"405\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"246\"];\n\t\t8 -> 9;\n\t\t9 [label=\"492\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"321\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-78\"];\n\t\t0 -> 1;\n\t\t1 [label=\"405\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"246\"];\n\t\t8 -> 9;\n\t\t9 [label=\"492\"];\n\t\t8 -> 10;\n\t\t10 [label=\"321\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(-241, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-241> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-78\"];\n\t\t0 -> 1;\n\t\t1 [label=\"405\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"246\"];\n\t\t8 -> 9;\n\t\t9 [label=\"492\"];\n\t\t8 -> 10;\n\t\t10 [label=\"321\"];\n\t\t10 -> 11;\n\t\t11 [label=\"335\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"246\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"321\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"837\"];\n\t\t8 -> 9;\n\t\t9 [label=\"987\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"405\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"246\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"321\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"837\"];\n\t\t8 -> 9;\n\t\t9 [label=\"987\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"405\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"246\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"321\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"837\"];\n\t\t8 -> 9;\n\t\t9 [label=\"987\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"405\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"246\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"321\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"837\"];\n\t\t8 -> 9;\n\t\t9 [label=\"987\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"405\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        assertEquals(-78, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-78> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"246\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"321\"];\n\t\t2 -> 3;\n\t\t3 [label=\"335\"];\n\t\t0 -> 4;\n\t\t4 [label=\"248\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"837\"];\n\t\t8 -> 9;\n\t\t9 [label=\"987\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"405\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"248\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"321\"];\n\t\t4 -> 5;\n\t\t5 [label=\"335\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"405\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"492\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"248\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"321\"];\n\t\t4 -> 5;\n\t\t5 [label=\"335\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"405\"];\n\t\t6 -> 7;\n\t\t7 [label=\"492\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"837\"];\n\t\t8 -> 9;\n\t\t9 [label=\"987\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"248\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"321\"];\n\t\t4 -> 5;\n\t\t5 [label=\"335\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"405\"];\n\t\t8 -> 9;\n\t\t9 [label=\"492\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"248\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t\t0 -> 4;\n\t\t4 [label=\"321\"];\n\t\t4 -> 5;\n\t\t5 [label=\"335\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"405\"];\n\t\t8 -> 9;\n\t\t9 [label=\"492\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(246, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<246> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"248\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t\t0 -> 4;\n\t\t4 [label=\"321\"];\n\t\t4 -> 5;\n\t\t5 [label=\"335\"];\n\t\t4 -> 6;\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"405\"];\n\t\t8 -> 9;\n\t\t9 [label=\"492\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"321\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"405\"];\n\t\t4 -> 5;\n\t\t5 [label=\"492\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"955\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"321\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"405\"];\n\t\t4 -> 5;\n\t\t5 [label=\"492\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"955\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"321\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"405\"];\n\t\t4 -> 5;\n\t\t5 [label=\"492\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"955\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"321\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"405\"];\n\t\t4 -> 5;\n\t\t5 [label=\"492\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"955\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(248, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<248> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"321\"];\n\t\t0 -> 1;\n\t\t1 [label=\"335\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"405\"];\n\t\t4 -> 5;\n\t\t5 [label=\"492\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"955\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"405\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"837\"];\n\t\t4 -> 5;\n\t\t5 [label=\"987\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"335\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"955\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"405\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"335\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"837\"];\n\t\t6 -> 7;\n\t\t7 [label=\"987\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"335\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"405\"];\n\t\t4 -> 5;\n\t\t5 [label=\"492\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"335\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"405\"];\n\t\t4 -> 5;\n\t\t5 [label=\"492\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n}");
        assertEquals(321, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<321> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"335\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t\t0 -> 4;\n\t\t4 [label=\"405\"];\n\t\t4 -> 5;\n\t\t5 [label=\"492\"];\n\t\t4 -> 6;\n\t\t6 [label=\"591\"];\n\t\t6 -> 7;\n\t\t7 [label=\"997\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"405\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"837\"];\n\t\t4 -> 5;\n\t\t5 [label=\"987\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"955\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"405\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"837\"];\n\t\t4 -> 5;\n\t\t5 [label=\"987\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"955\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"405\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"837\"];\n\t\t4 -> 5;\n\t\t5 [label=\"987\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"955\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"405\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"837\"];\n\t\t4 -> 5;\n\t\t5 [label=\"987\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"955\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        assertEquals(335, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<335> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"405\"];\n\t\t0 -> 1;\n\t\t1 [label=\"492\"];\n\t\t0 -> 2;\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"837\"];\n\t\t4 -> 5;\n\t\t5 [label=\"987\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"955\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"591\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"492\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"955\"];\n\t}\n\t0 -> 2;\n\t2 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"492\"];\n\t\t0 -> 1;\n\t\t1 [label=\"955\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"591\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"837\"];\n\t\t4 -> 5;\n\t\t5 [label=\"987\"];\n\t}\n\t0 -> 2;\n\t2 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"591\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"492\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(405, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<405> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"591\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"492\"];\n\t\t4 -> 5;\n\t\t5 [label=\"955\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"591\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"955\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"591\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"955\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(492, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<492> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"591\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t\t0 -> 2;\n\t\t2 [label=\"837\"];\n\t\t2 -> 3;\n\t\t3 [label=\"987\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"955\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"837\"];\n\t\t0 -> 1;\n\t\t1 [label=\"987\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"955\"];\n\t}\n\tsubgraph cluster_3 {\n\t\t3 [label=\"997\"];\n\t}\n\t0 -> 2;\n\t2 -> 3;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"837\"];\n\t\t0 -> 1;\n\t\t1 [label=\"987\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"955\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"837\"];\n\t\t0 -> 1;\n\t\t1 [label=\"987\"];\n\t\t0 -> 2;\n\t\t2 [label=\"955\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n}");
        assertEquals(591, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<591> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"837\"];\n\t\t0 -> 1;\n\t\t1 [label=\"987\"];\n\t\t0 -> 2;\n\t\t2 [label=\"955\"];\n\t\t2 -> 3;\n\t\t3 [label=\"997\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"955\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"987\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"955\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"987\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"955\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"987\"];\n\t}\n\t0 -> 2;\n}");
        assertEquals(837, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<837> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"955\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"987\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"987\"];\n\t}\n\tsubgraph cluster_1 {\n\t\t1 [label=\"997\"];\n\t}\n\t0 -> 1;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"987\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t}\n}");
        assertEquals(955, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<955> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"987\"];\n\t\t0 -> 1;\n\t\t1 [label=\"997\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"997\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"997\"];\n\t}\n}");
        assertEquals(987, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<987> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"997\"];\n\t}\n}");
        expectedLogs.add("digraph {\n}");
        assertEquals(997, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<997> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        assertThrows(NoSuchElementException.class, () -> heap.deleteMin(new FastResult()));
    }

    /**
     * <p>
     * Brutal tests to see if numbers can be randomly added and removed in the range of [-1000, 1000] <br>
     * Tests till can't no more.
     * </p>
     * Tests both {@link BinomialHeap#insert(int, Result)} and {@link BinomialHeap#min()} <br>
     * <i> Note: Read the class docs </i>
     * @author Aamin
     */
    @Test
    public void randomInsertAndDelete() {
        BinomialHeap heap = new BinomialHeap();
        FastResult fastResult;
        List<String> expectedLogs;
        int deletedMin;

        heap.insert(-730, new FastResult());
        heap.insert(-865, new FastResult());
        heap.insert(62, new FastResult());
        heap.insert(836, new FastResult());
        heap.insert(397, new FastResult());
        heap.insert(10, new FastResult());
        heap.insert(797, new FastResult());
        heap.insert(342, new FastResult());
        heap.insert(288, new FastResult());
        heap.insert(-293, new FastResult());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-865\"];\n\t\t0 -> 1;\n\t\t1 [label=\"-730\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-293\"];\n\t\t8 -> 9;\n\t\t9 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-293\"];\n\t\t4 -> 5;\n\t\t5 [label=\"288\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"62\"];\n\t\t6 -> 7;\n\t\t7 [label=\"836\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-293\"];\n\t\t4 -> 5;\n\t\t5 [label=\"288\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"62\"];\n\t\t6 -> 7;\n\t\t7 [label=\"836\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(-865, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-865> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-50, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"-50\"];\n\t}\n\t0 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(-730, heap.min(), "You returned wrong value of min! Expected <-730> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-497, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-497\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");

        assertEquals(-730, heap.min(), "You returned wrong value of min! Expected <-730> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(695, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-497\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-497\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"695\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(-730, heap.min(), "You returned wrong value of min! Expected <-730> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-716, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-716\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(-730, heap.min(), "You returned wrong value of min! Expected <-730> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-730\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-716\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-716\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"-50\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-716\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-716\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(-730, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-730> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-367, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-716\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-716\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-367\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(-716, heap.min(), "You returned wrong value of min! Expected <-716> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-716\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-497\"];\n\t\t10 -> 11;\n\t\t11 [label=\"695\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-367\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-367\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"-50\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(-716, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-716> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(706, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"706\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(-497, heap.min(), "You returned wrong value of min! Expected <-497> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-434, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"706\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-434\"];\n\t}\n\tsubgraph cluster_13 {\n\t\t13 [label=\"706\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n\t12 -> 13;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-434\"];\n\t\t12 -> 13;\n\t\t13 [label=\"706\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(-497, heap.min(), "You returned wrong value of min! Expected <-497> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-497\"];\n\t\t8 -> 9;\n\t\t9 [label=\"695\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-434\"];\n\t\t12 -> 13;\n\t\t13 [label=\"706\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-434\"];\n\t\t8 -> 9;\n\t\t9 [label=\"706\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"695\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-434\"];\n\t\t8 -> 9;\n\t\t9 [label=\"706\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"695\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-434\"];\n\t\t8 -> 9;\n\t\t9 [label=\"706\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"695\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        assertEquals(-497, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-497> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-434\"];\n\t\t8 -> 9;\n\t\t9 [label=\"706\"];\n\t\t8 -> 10;\n\t\t10 [label=\"-367\"];\n\t\t10 -> 11;\n\t\t11 [label=\"-50\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"695\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-367\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"695\"];\n\t}\n\tsubgraph cluster_11 {\n\t\t11 [label=\"706\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n\t10 -> 11;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-367\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"695\"];\n\t\t10 -> 11;\n\t\t11 [label=\"706\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-367\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"695\"];\n\t\t10 -> 11;\n\t\t11 [label=\"706\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(-434, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-434> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-582, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-367\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"695\"];\n\t\t10 -> 11;\n\t\t11 [label=\"706\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-367\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"695\"];\n\t\t10 -> 11;\n\t\t11 [label=\"706\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-582\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");

        assertEquals(-582, heap.min(), "You returned wrong value of min! Expected <-582> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-367\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"695\"];\n\t\t10 -> 11;\n\t\t11 [label=\"706\"];\n\t}\n\tsubgraph cluster_12 {\n\t\t12 [label=\"-582\"];\n\t}\n\t0 -> 8;\n\t8 -> 12;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-367\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"695\"];\n\t\t10 -> 11;\n\t\t11 [label=\"706\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(-582, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-582> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-367\"];\n\t\t8 -> 9;\n\t\t9 [label=\"-50\"];\n\t\t8 -> 10;\n\t\t10 [label=\"695\"];\n\t\t10 -> 11;\n\t\t11 [label=\"706\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"695\"];\n\t\t8 -> 9;\n\t\t9 [label=\"706\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-50\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"695\"];\n\t\t8 -> 9;\n\t\t9 [label=\"706\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-50\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"695\"];\n\t\t8 -> 9;\n\t\t9 [label=\"706\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-50\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        assertEquals(-367, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-367> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-293\"];\n\t\t0 -> 1;\n\t\t1 [label=\"288\"];\n\t\t0 -> 2;\n\t\t2 [label=\"62\"];\n\t\t2 -> 3;\n\t\t3 [label=\"836\"];\n\t\t0 -> 4;\n\t\t4 [label=\"10\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"695\"];\n\t\t8 -> 9;\n\t\t9 [label=\"706\"];\n\t}\n\tsubgraph cluster_10 {\n\t\t10 [label=\"-50\"];\n\t}\n\t0 -> 8;\n\t8 -> 10;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-50\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"288\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-50\"];\n\t\t4 -> 5;\n\t\t5 [label=\"288\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"62\"];\n\t\t6 -> 7;\n\t\t7 [label=\"836\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"695\"];\n\t\t8 -> 9;\n\t\t9 [label=\"706\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-50\"];\n\t\t8 -> 9;\n\t\t9 [label=\"288\"];\n\t}\n\t0 -> 4;\n\t4 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-50\"];\n\t\t8 -> 9;\n\t\t9 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(-293, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-293> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-50\"];\n\t\t8 -> 9;\n\t\t9 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(-50, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-50> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-233, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-233\"];\n\t}\n\tsubgraph cluster_9 {\n\t\t9 [label=\"288\"];\n\t}\n\t0 -> 8;\n\t8 -> 9;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-233\"];\n\t\t8 -> 9;\n\t\t9 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(-233, heap.min(), "You returned wrong value of min! Expected <-233> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-233\"];\n\t\t8 -> 9;\n\t\t9 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        assertEquals(-233, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-233> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"10\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"62\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"288\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"62\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"342\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"288\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"397\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"62\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"288\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"62\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"288\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"62\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t\t0 -> 4;\n\t\t4 [label=\"288\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n}");
        assertEquals(10, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<10> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(382, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"62\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t\t0 -> 4;\n\t\t4 [label=\"288\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"62\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t\t0 -> 4;\n\t\t4 [label=\"288\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"382\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(62, heap.min(), "You returned wrong value of min! Expected <62> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"62\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t\t0 -> 4;\n\t\t4 [label=\"288\"];\n\t\t4 -> 5;\n\t\t5 [label=\"397\"];\n\t\t4 -> 6;\n\t\t6 [label=\"342\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"382\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"288\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"695\"];\n\t\t4 -> 5;\n\t\t5 [label=\"706\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"382\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"836\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"288\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"382\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"288\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"382\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"288\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"382\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n}");
        assertEquals(62, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<62> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-97, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"288\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"382\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"288\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"382\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-97\"];\n\t}\n\t0 -> 8;\n}");

        assertEquals(-97, heap.min(), "You returned wrong value of min! Expected <-97> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"288\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"382\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n\tsubgraph cluster_8 {\n\t\t8 [label=\"-97\"];\n\t}\n\t0 -> 8;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"288\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"382\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n}");
        assertEquals(-97, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-97> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"288\"];\n\t\t0 -> 1;\n\t\t1 [label=\"397\"];\n\t\t0 -> 2;\n\t\t2 [label=\"342\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"382\"];\n\t\t4 -> 5;\n\t\t5 [label=\"836\"];\n\t\t4 -> 6;\n\t\t6 [label=\"695\"];\n\t\t6 -> 7;\n\t\t7 [label=\"706\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"342\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"397\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"342\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"397\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"342\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"397\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"342\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"397\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        assertEquals(288, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<288> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"342\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"397\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(342, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<342> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(190, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"190\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        assertEquals(190, heap.min(), "You returned wrong value of min! Expected <190> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"190\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(190, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<190> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-920, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"-920\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        assertEquals(-920, heap.min(), "You returned wrong value of min! Expected <-920> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(852, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"-920\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"-920\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"852\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-920\"];\n\t\t4 -> 5;\n\t\t5 [label=\"852\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"397\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        assertEquals(-920, heap.min(), "You returned wrong value of min! Expected <-920> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-920\"];\n\t\t4 -> 5;\n\t\t5 [label=\"852\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"397\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"852\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"852\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        assertEquals(-920, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-920> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"382\"];\n\t\t0 -> 1;\n\t\t1 [label=\"836\"];\n\t\t0 -> 2;\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"852\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"397\"];\n\t\t0 -> 1;\n\t\t1 [label=\"797\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"836\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"852\"];\n\t}\n\t0 -> 2;\n\t2 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"397\"];\n\t\t0 -> 1;\n\t\t1 [label=\"797\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"695\"];\n\t\t2 -> 3;\n\t\t3 [label=\"706\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"836\"];\n\t\t4 -> 5;\n\t\t5 [label=\"852\"];\n\t}\n\t0 -> 2;\n\t2 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(382, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<382> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-509, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"-509\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");

        assertEquals(-509, heap.min(), "You returned wrong value of min! Expected <-509> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(410, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"-509\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"-509\"];\n\t}\n\tsubgraph cluster_7 {\n\t\t7 [label=\"410\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n\t6 -> 7;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"-509\"];\n\t\t4 -> 5;\n\t\t5 [label=\"410\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"397\"];\n\t\t6 -> 7;\n\t\t7 [label=\"797\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-509\"];\n\t\t0 -> 1;\n\t\t1 [label=\"410\"];\n\t\t0 -> 2;\n\t\t2 [label=\"397\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"695\"];\n\t\t4 -> 5;\n\t\t5 [label=\"706\"];\n\t\t4 -> 6;\n\t\t6 [label=\"836\"];\n\t\t6 -> 7;\n\t\t7 [label=\"852\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-509\"];\n\t\t0 -> 1;\n\t\t1 [label=\"410\"];\n\t\t0 -> 2;\n\t\t2 [label=\"397\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"695\"];\n\t\t4 -> 5;\n\t\t5 [label=\"706\"];\n\t\t4 -> 6;\n\t\t6 [label=\"836\"];\n\t\t6 -> 7;\n\t\t7 [label=\"852\"];\n\t}\n}");

        assertEquals(-509, heap.min(), "You returned wrong value of min! Expected <-509> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-509\"];\n\t\t0 -> 1;\n\t\t1 [label=\"410\"];\n\t\t0 -> 2;\n\t\t2 [label=\"397\"];\n\t\t2 -> 3;\n\t\t3 [label=\"797\"];\n\t\t0 -> 4;\n\t\t4 [label=\"695\"];\n\t\t4 -> 5;\n\t\t5 [label=\"706\"];\n\t\t4 -> 6;\n\t\t6 [label=\"836\"];\n\t\t6 -> 7;\n\t\t7 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"410\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"410\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"410\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"410\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        assertEquals(-509, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-509> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"397\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\tsubgraph cluster_6 {\n\t\t6 [label=\"410\"];\n\t}\n\t0 -> 4;\n\t4 -> 6;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"410\"];\n\t}\n\tsubgraph cluster_5 {\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n\t4 -> 5;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"410\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(397, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<397> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"410\"];\n\t\t4 -> 5;\n\t\t5 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        assertEquals(410, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<410> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"695\"];\n\t\t0 -> 1;\n\t\t1 [label=\"706\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\tsubgraph cluster_4 {\n\t\t4 [label=\"797\"];\n\t}\n\t0 -> 4;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"706\"];\n\t}\n\tsubgraph cluster_3 {\n\t\t3 [label=\"797\"];\n\t}\n\t0 -> 2;\n\t2 -> 3;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"706\"];\n\t\t0 -> 1;\n\t\t1 [label=\"797\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"706\"];\n\t\t0 -> 1;\n\t\t1 [label=\"797\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n}");
        assertEquals(695, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<695> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"706\"];\n\t\t0 -> 1;\n\t\t1 [label=\"797\"];\n\t\t0 -> 2;\n\t\t2 [label=\"836\"];\n\t\t2 -> 3;\n\t\t3 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"797\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"797\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"797\"];\n\t}\n\t0 -> 2;\n}");
        assertEquals(706, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<706> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"797\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n}");
        assertEquals(797, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<797> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-588, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"-588\"];\n\t}\n\t0 -> 2;\n}");

        assertEquals(-588, heap.min(), "You returned wrong value of min! Expected <-588> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"-588\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n}");
        assertEquals(-588, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-588> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(318, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"318\"];\n\t}\n\t0 -> 2;\n}");

        assertEquals(318, heap.min(), "You returned wrong value of min! Expected <318> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n\tsubgraph cluster_2 {\n\t\t2 [label=\"318\"];\n\t}\n\t0 -> 2;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n}");
        assertEquals(318, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<318> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"836\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"852\"];\n\t}\n}");
        assertEquals(836, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<836> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        heap.insert(-429, fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-429\"];\n\t}\n\tsubgraph cluster_1 {\n\t\t1 [label=\"852\"];\n\t}\n\t0 -> 1;\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-429\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n}");

        assertEquals(-429, heap.min(), "You returned wrong value of min! Expected <-429> but got <" + heap.min() + ">");
        if (expectedLogs.size() < fastResult.getLogs().size()) {
            fail("You logged too much! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        } else if (expectedLogs.size() > fastResult.getLogs().size()) {
            fail("You didn't log enough! Expected logs (size: " + expectedLogs.size() + ") <\n" + expectedLogs + "\n> but got (size: " + fastResult.getLogs() + ") <\n" + fastResult.getLogs().toString() + "\n>");
        }
        assertEquals(expectedLogs, fastResult.getLogs());


        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"-429\"];\n\t\t0 -> 1;\n\t\t1 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"852\"];\n\t}\n}");
        assertEquals(-429, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<-429> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        fastResult = new FastResult();
        expectedLogs = new ArrayList<>();
        deletedMin = heap.deleteMin(fastResult);
        expectedLogs.add("digraph {\n\tsubgraph cluster_0 {\n\t\t0 [label=\"852\"];\n\t}\n}");
        expectedLogs.add("digraph {\n}");
        assertEquals(852, deletedMin, "You deleted the wrong node/returned a wrong node as the deleted element! Expected<852> but got <" + deletedMin + ">");
        assertEquals(expectedLogs, fastResult.getLogs());

        assertThrows(NoSuchElementException.class, () -> heap.deleteMin(new FastResult()));
    }

}
