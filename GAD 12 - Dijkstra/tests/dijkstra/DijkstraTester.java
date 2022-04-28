package tests.dijkstra;

import gad.dijkstra.Dijkstra;
import gad.dijkstra.Graph;
import gad.dijkstra.StudentResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static tests.dijkstra.TestResult.*;
import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTester {

	private Graph g;

	@BeforeEach
	void setup() {
		g = new Graph();
	}

	@Test
	void artemisExample() {

		for (int i = 0; i < 5; i++) {
			g.addNode();
		}

		g.addEdge(0, 1, 2);
		g.addEdge(0, 2, 3);
		g.addEdge(1, 2, 2);
		g.addEdge(1, 3, 1);
		g.addEdge(2, 4, 1);

		Dijkstra d = new Dijkstra();

		d.findRoute(g, g.getNode(0), g.getNode(4), new StudentResult());

		System.out.printf("Length: %d%n", d.getShortestPathLength());
		System.out.printf("Path: %s%n", d.getShortestPath().toString());


	}

	@Test
	void noPath() {

		for (int i = 0; i < 5; i++) {
			g.addNode();
		}

		g.addEdge(0, 1, 2);
		g.addEdge(0, 2, 3);
		g.addEdge(1, 2, 2);
		g.addEdge(1, 3, 1);

		Dijkstra d = new Dijkstra();

		assertThrows(RuntimeException.class, () -> d.findRoute(g, g.getNode(0), g.getNode(4), new StudentResult()));
		System.out.printf("Length: %d%n", d.getShortestPathLength());
		System.out.printf("Path: %s%n", d.getShortestPath().toString());
	}

	@Test
	void multiplePaths() {

		for (int i = 0; i < 5; i++) {
			g.addNode();
		}

		g.addEdge(0, 1, 2);
		g.addEdge(0, 2, 3);
		g.addEdge(0, 2, 2);
		g.addEdge(1, 2, 2);
		g.addEdge(1, 3, 1);
		g.addEdge(2, 4, 1);

		Dijkstra d = new Dijkstra();

		d.findRoute(g, g.getNode(0), g.getNode(4), new StudentResult());

		System.out.printf("Length: %d%n", d.getShortestPathLength());
		System.out.printf("Path: %s%n", d.getShortestPath().toString());


	}

	@Test
	void test() {

		for (int i = 0; i < 9; i++) {
			g.addNode();
		}

		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 3);
		g.addEdge(2, 4, 2);
		g.addEdge(2, 5, 1);
		g.addEdge(3, 1, 2);
		g.addEdge(3, 2, 2);
		g.addEdge(3, 4, 2);
		g.addEdge(4, 5, 1);
		g.addEdge(4, 6, 2);
		g.addEdge(4, 7, 1);
		g.addEdge(5, 6, 3);
		g.addEdge(5, 7, 2);
		g.addEdge(6, 7, 2);
		g.addEdge(7, 8, 1);
		g.addEdge(8, 3, 4);


		Dijkstra d = new Dijkstra();

		d.findRoute(g, g.getNode(4), g.getNode(1), new StudentResult());

		System.out.printf("Length: %d%n", d.getShortestPathLength());
		System.out.printf("Path: %s%n", d.getShortestPath().toString());
	}

	@Test
	void test2() {

		for (int i = 0; i < 26; i++) {
			g.addNode();
		}

		g.addBiEdge(0, 1, 2);
		g.addBiEdge(0, 2, 4);
		g.addBiEdge(0, 3, 1);
		g.addBiEdge(1, 2, 3);
		g.addBiEdge(1, 4, 1);
		g.addBiEdge(2, 4, 2);
		g.addBiEdge(2, 5, 2);
		g.addBiEdge(3, 5, 5);
		g.addBiEdge(3, 6, 4);
		g.addBiEdge(4, 7, 3);
		g.addBiEdge(5, 7, 3);
		g.addBiEdge(5, 8, 2);
		g.addBiEdge(5, 9, 4);
		g.addBiEdge(5, 6, 3);
		g.addBiEdge(6, 10, 2);
		g.addBiEdge(7, 11, 1);
		g.addBiEdge(7, 14, 8);
		g.addBiEdge(8, 9, 3);
		g.addBiEdge(8, 11, 3);
		g.addBiEdge(8, 12, 2);
		g.addBiEdge(9, 10, 6);
		g.addBiEdge(9, 12, 6);
		g.addBiEdge(9, 13, 3);
		g.addBiEdge(10, 13, 4);
		g.addBiEdge(10, 17, 2);
		g.addBiEdge(11, 12, 3);
		g.addBiEdge(11, 14, 6);
		g.addBiEdge(12, 13, 5);
		g.addBiEdge(12, 14, 4);
		g.addBiEdge(12, 15, 2);
		g.addBiEdge(13, 16, 2);
		g.addBiEdge(13, 17, 1);
		g.addBiEdge(14, 15, 2);
		g.addBiEdge(14, 18, 6);
		g.addBiEdge(15, 16, 1);
		g.addBiEdge(15, 18, 2);
		g.addBiEdge(15, 19, 1);
		g.addBiEdge(16, 17, 8);
		g.addBiEdge(16, 19, 3);
		g.addBiEdge(17, 19, 5);
		g.addBiEdge(18, 20, 2);
		g.addBiEdge(19, 20, 8);


		Dijkstra d = new Dijkstra();

		d.findRoute(g, g.getNode(0), g.getNode(20), new StudentResult());

		System.out.printf("Length: %d%n", d.getShortestPathLength());
		System.out.printf("Path: %s%n", d.getShortestPath().toString());
	}

	@Test
	void random() {

		int limit = 100000;
		
		Random random = new Random();

		for (int i = 0; i < limit; i++) {
			g.addNode();
		}

		for (int i = 0; i < limit; i++) {
			g.addEdge(random.nextInt(limit), random.nextInt(limit), random.nextInt(100));
		}

		long dur = 0;


		for (int i = 0; i < 5; i++) {

			for (Graph.Node node : g.getAllNodes()) {
				node.reset();
			}

			Dijkstra dijkstra = new Dijkstra();

			long start = System.nanoTime();

			try {
				dijkstra.findRoute(g, g.getNode(random.nextInt(limit)), g.getNode(random.nextInt(limit)), new TestResult());
			} catch (Exception ignored) {
			}
			dur += System.nanoTime() - start;
		}

		System.out.printf("average duration: %fs", dur * 2*1e-10);
	}
}
