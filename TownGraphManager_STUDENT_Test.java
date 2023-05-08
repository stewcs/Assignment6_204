import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * student test for Town Graph Manager.
 * @author Daniel Xu
 *
 */
class TownGraphManager_STUDENT_Test {
	Graph graph;
	TownGraphManager graphManager;
	Town a, b, c, d, e, f, g;

	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph();
		graphManager =  new TownGraphManager();

		a = new Town("a");
		b = new Town("b");
		c = new Town("c");
		d = new Town("d");
		e = new Town("e");
		f = new Town("f");
		g = new Town("g");
		
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addVertex(f);
		graph.addVertex(g);

		graph.addEdge(a, b, 4, "AB");
		graph.addEdge(a, c, 3, "AC");
		graph.addEdge(b, c, 5, "BC");
		graph.addEdge(b, d, 2, "BD");
		graph.addEdge(b, e, 8, "BE");
		graph.addEdge(c, d, 6, "CD");
		graph.addEdge(c, e, 1, "CE");
		graph.addEdge(d, e, 3, "DE");
		graph.addEdge(d, f, 5, "DF");
		graph.addEdge(e, f, 6, "EF");
		graph.addEdge(e, g, 7, "EG");
		graph.addEdge(f, g, 2, "FG");
		graph.addEdge(f, d, 3, "FD");


		graphManager.addTown("a");
		graphManager.addTown("b");
		graphManager.addTown("c");
		graphManager.addTown("d");
		graphManager.addTown("e");
		graphManager.addTown("f");
		graphManager.addTown("g");

		graphManager.addRoad("a", "b", 2, "AB");
		graphManager.addRoad("a", "c", 3, "AC");
		graphManager.addRoad("b", "c", 1, "BC");
		graphManager.addRoad("b", "d", 5, "BD");
		graphManager.addRoad("b", "e", 4, "BE");
		graphManager.addRoad("c", "d", 1, "CD");
		graphManager.addRoad("c", "e", 1, "CE");
		graphManager.addRoad("d", "e", 4, "DE");
		graphManager.addRoad("d", "f", 2, "DF");
		graphManager.addRoad("e", "f", 7, "EF");
		graphManager.addRoad("e", "g", 4, "EG");
		graphManager.addRoad("f", "g", 2, "FG");
		graphManager.addRoad("f", "d", 1, "FD");
	}


	@AfterEach
	void tearDown() throws Exception {
		graph = null;
		a = null;
		b = null;
		c = null;
		d = null;
		e = null;
		f = null;
		g = null;
	}


	@Test
	void testGetRoad() {
		assertEquals(graph.getEdge(a, b).getName(), graphManager.getRoad("a", "b"));
		assertEquals(graph.getEdge(a, c).getName(), graphManager.getRoad("a", "c"));
		assertEquals(graph.getEdge(b, d).getName(), graphManager.getRoad("b", "d"));
		assertEquals(graph.getEdge(d, e).getName(), graphManager.getRoad("d", "e"));
	}

	@Test
	void testGetTown() {
		assertEquals(a, graphManager.getTown("a"));
		assertEquals(b, graphManager.getTown("b"));
		
	}

	@Test
	void testContainsTown() {
		assertTrue(graphManager.containsTown("a"));
		assertTrue(graphManager.containsTown("b"));
		assertFalse(graphManager.containsTown("h"));
	}

	@Test
	void testContainsRoadConnection() {
		assertTrue(graphManager.containsRoadConnection("a", "b"));
		assertFalse(graphManager.containsRoadConnection("c", "f"));
		assertTrue(graphManager.containsRoadConnection("b", "d"));
		assertFalse(graphManager.containsRoadConnection("e", "a"));
	}

	@Test
	void testDeleteRoadConnection() {
		graphManager.deleteRoadConnection("a", "b", "AB");
		assertFalse(graphManager.containsRoadConnection("a", "b"));
		
		graphManager.deleteRoadConnection("c", "c", "CC");
		assertFalse(graphManager.containsRoadConnection("c", "c"));
	}

	@Test
	void testDeleteTown() {
		graphManager.deleteTown("a");
		
		assertEquals(false, graphManager.containsTown("a"));
		
		graphManager.deleteTown("b");
		
		assertEquals(false, graphManager.containsTown("b"));
	}
}




