package tests.binomilia;

import gad.binomilia.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinomialTreeNodeTester {

    /**
     * Simple tests to see if the smallest element of the nodes are returned or not. <br>
     * Tests solely {@link BinomialTreeNode#min()}
     * @author Aamin
     */
    @Test
    public void testMin() {
        BinomialTreeNode treeNode;

        treeNode = new BinomialTreeNode(15515);
        assertEquals(15515, treeNode.min());

        treeNode = new BinomialTreeNode(55202);
        assertEquals(55202, treeNode.min());

        treeNode = new BinomialTreeNode(55690);
        assertEquals(55690, treeNode.min());

        treeNode = new BinomialTreeNode(47549);
        assertEquals(47549, treeNode.min());

        treeNode = new BinomialTreeNode(96055);
        assertEquals(96055, treeNode.min());

        treeNode = new BinomialTreeNode(46879);
        assertEquals(46879, treeNode.min());

        treeNode = new BinomialTreeNode(98713);
        assertEquals(98713, treeNode.min());

        treeNode = new BinomialTreeNode(67420);
        assertEquals(67420, treeNode.min());

        treeNode = new BinomialTreeNode(80231);
        assertEquals(80231, treeNode.min());

        treeNode = new BinomialTreeNode(3738);
        assertEquals(3738, treeNode.min());

        treeNode = new BinomialTreeNode(57062);
        assertEquals(57062, treeNode.min());

        treeNode = new BinomialTreeNode(9560);
        assertEquals(9560, treeNode.min());

        treeNode = new BinomialTreeNode(56586);
        assertEquals(56586, treeNode.min());

        treeNode = new BinomialTreeNode(58590);
        assertEquals(58590, treeNode.min());

        treeNode = new BinomialTreeNode(96034);
        assertEquals(96034, treeNode.min());

        treeNode = new BinomialTreeNode(84061);
        assertEquals(84061, treeNode.min());

        treeNode = new BinomialTreeNode(54418);
        assertEquals(54418, treeNode.min());

        treeNode = new BinomialTreeNode(95256);
        assertEquals(95256, treeNode.min());

        treeNode = new BinomialTreeNode(51095);
        assertEquals(51095, treeNode.min());

        treeNode = new BinomialTreeNode(6125);
        assertEquals(6125, treeNode.min());
    }

    /**
     * Simple tests to see if the correct rank of the nodes are returned or not. <br>
     * Tests solely {@link BinomialTreeNode#rank()}
     * @author Aamin
     */
    @Test
    public void testRankSimple() {
        BinomialTreeNode treeNode;

        treeNode = new BinomialTreeNode(19723);
        assertEquals(0, treeNode.rank());

        treeNode = new BinomialTreeNode(47613);
        assertEquals(0, treeNode.rank());

        treeNode = new BinomialTreeNode(43494);
        assertEquals(0, treeNode.rank());

        treeNode = new BinomialTreeNode(80915);
        assertEquals(0, treeNode.rank());

        treeNode = new BinomialTreeNode(65384);
        assertEquals(0, treeNode.rank());

        treeNode = new BinomialTreeNode(40565);
        assertEquals(0, treeNode.rank());

        treeNode = new BinomialTreeNode(44523);
        assertEquals(0, treeNode.rank());

        treeNode = new BinomialTreeNode(72520);
        assertEquals(0, treeNode.rank());

        treeNode = new BinomialTreeNode(10295);
        assertEquals(0, treeNode.rank());

        treeNode = new BinomialTreeNode(7682);
        assertEquals(0, treeNode.rank());
    }

    /**
     * Relatively more complex tests to see if the correct min and rank of the nodes are returned or not. <br>
     * This test uses {@link BinomialTreeNode#merge(BinomialTreeNode, BinomialTreeNode)} since it is not possible to check for higher ranks without it.
     * @author Aamin
     */
    @Test
    public void testMinAndRank() {
        BinomialTreeNode treeNode;

        treeNode = new BinomialTreeNode(50);

        assertEquals(50, treeNode.min());

        BinomialTreeNode.merge(treeNode, new BinomialTreeNode(55));
        assertEquals(50, treeNode.min());
        assertEquals(1, treeNode.rank());

        BinomialTreeNode.merge(treeNode, BinomialTreeNode.merge(new BinomialTreeNode(60), new BinomialTreeNode(69)));
        assertEquals(50, treeNode.min());
        assertEquals(2, treeNode.rank());

        BinomialTreeNode.merge(treeNode, BinomialTreeNode.merge(
                BinomialTreeNode.merge(new BinomialTreeNode(700), new BinomialTreeNode(800)),
                BinomialTreeNode.merge(new BinomialTreeNode(6969), new BinomialTreeNode(420))
        ));
        assertEquals(50, treeNode.min());
        assertEquals(3, treeNode.rank());


        BinomialTreeNode smallerTree = new BinomialTreeNode(-10);

        assertEquals(-10, smallerTree.min());

        BinomialTreeNode.merge(smallerTree, new BinomialTreeNode(-6));
        assertEquals(-10, smallerTree.min());
        assertEquals(1, smallerTree.rank());

        BinomialTreeNode.merge(smallerTree, BinomialTreeNode.merge(new BinomialTreeNode(25), new BinomialTreeNode(10)));
        assertEquals(-10, smallerTree.min());
        assertEquals(2, smallerTree.rank());

        BinomialTreeNode.merge(smallerTree, BinomialTreeNode.merge(
                BinomialTreeNode.merge(new BinomialTreeNode(22), new BinomialTreeNode(0)),
                BinomialTreeNode.merge(new BinomialTreeNode(12), new BinomialTreeNode(100000))
        ));
        assertEquals(-10, smallerTree.min());
        assertEquals(3, smallerTree.rank());

        BinomialTreeNode.merge(smallerTree, treeNode);
        assertEquals(-10, smallerTree.min());
        assertEquals(50, treeNode.min());
        assertEquals(4, smallerTree.rank());
        assertEquals(3, treeNode.rank());

    }

    /**
     * Relatively complex tests to see if {@link BinomialTreeNode#merge(BinomialTreeNode, BinomialTreeNode)} returns
     * the correct node as well as if the ranks and mins of the returned nodes are being changed or not. <br>
     * This test uses all the methods of {@link BinomialTreeNode}
     * @author Aamin
     */
    @Test
    public void merge() {
        BinomialTreeNode treeNode1 = new BinomialTreeNode(50);
        BinomialTreeNode treeNode2 = new BinomialTreeNode(69);

        assertEquals(treeNode1, BinomialTreeNode.merge(treeNode1, treeNode2), "You returned the wrong node!");
        assertEquals(1, treeNode1.rank());
        assertEquals(50, treeNode1.min());

        BinomialTreeNode treeNode3 = new BinomialTreeNode(12);
        BinomialTreeNode treeNode4 = new BinomialTreeNode(420);
        assertEquals(treeNode3, BinomialTreeNode.merge(treeNode4, treeNode3), "You returned the wrong node!");
        assertEquals(1, treeNode3.rank());
        assertEquals(12, treeNode3.min());
        assertEquals(treeNode3, BinomialTreeNode.merge(treeNode1, treeNode3), "You returned the wrong node!");
        assertEquals(2, treeNode3.rank());
        assertEquals(12, treeNode3.min());

        BinomialTreeNode bigTreeNode = BinomialTreeNode.merge(
                BinomialTreeNode.merge(new BinomialTreeNode(911), new BinomialTreeNode(133)),
                BinomialTreeNode.merge(new BinomialTreeNode(18), new BinomialTreeNode(3))
        );
        assertEquals(3, bigTreeNode.min());
        assertEquals(2, bigTreeNode.rank());

        assertEquals(bigTreeNode, BinomialTreeNode.merge(bigTreeNode, treeNode3));
        assertEquals(3, bigTreeNode.min());
        assertEquals(3, bigTreeNode.rank());
    }

}
