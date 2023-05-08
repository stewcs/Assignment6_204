import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
/**
 * This is a class that implements a Graph data structure using maps and sets.
 * @author Daniel Xu
 * Version: 05/07/2023
 */

public class Graph implements GraphInterface<Town, Road>{

	private Map<Town, List<Town>> Graph;
	private Set<Road> edges;

	private Map<Town, Integer> edgeCost;
	private Map<Town, Town> prevNodes;

	// Constructor
	public Graph() {
		Graph = new HashMap<>();
		edges = new HashSet<>();
	}

	@Override
	/**
	 * Returns an edge connecting source vertex to target vertex if such
	 * vertices and such edge exist in this graph. Otherwise returns
	 * null. If any of the specified vertices is null
	 * returns null
	 *
	 * In undirected graphs, the returned edge may have its source and target
	 * vertices in the opposite order.
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 *
	 * @return an edge connecting source vertex to target vertex.
	 */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {

		// Find the road in the set
		for(Road r: edges) {
			if(r.contains(destinationVertex) && r.contains(sourceVertex))
				return r;
		}
		// Return null if vertices do not exist.
		return null;
	}

	@Override
	/**
	 * Creates a new edge in this graph, going from the source vertex to the
	 * target vertex, and returns the created edge.
	 *
	 * The source and target vertices must already be contained in this
	 * graph. If they are not found in graph IllegalArgumentException is
	 * thrown.
	 *
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 * @param weight weight of the edge
	 * @param description description for edge
	 *
	 * @return The newly created edge if added to the graph, otherwise null.
	 *
	 * @throws IllegalArgumentException if source or target vertices are not
	 * found in the graph.
	 * @throws NullPointerException if any of the specified vertices is null.
	 */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

		// If source or target vertexes are not found in the graph.
		if (Graph.get(sourceVertex).equals(null) || Graph.get(destinationVertex).equals(null)) {
			throw new IllegalArgumentException();
		}

		// If either specified vertex is null
		if(sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();

		Road r = new Road(sourceVertex, destinationVertex, weight, description);

		// Add vertexes to adjacency list
		Graph.get(sourceVertex).add(destinationVertex);
		Graph.get(destinationVertex).add(sourceVertex);
		sourceVertex.addAdjacentTown(destinationVertex);
		destinationVertex.addAdjacentTown(sourceVertex);

		edges.add(r);

		return r;

	}


	@Override
	/**
	 * Adds the specified vertex to this graph if not already present. More
	 * formally, adds the specified vertex, v, to this graph if
	 * this graph contains no vertex u such that
	 * u.equals(v). If this graph already contains such vertex, the call
	 * leaves this graph unchanged and returns false. In combination
	 * with the restriction on constructors, this ensures that graphs never
	 * contain duplicate vertices.
	 *
	 * @param v vertex to be added to this graph.
	 *
	 * @return true if this graph did not already contain the specified
	 * vertex.
	 *
	 * @throws NullPointerException if the specified vertex is null.
	 */
	public boolean addVertex(Town v) {

		// If the vertex is null
		if(v == null)
			throw new NullPointerException();

		// If the vertex already exists
		if(Graph.containsKey(v)) {
			return false;
		}else {
			// Add the vertex
			Graph.put(v, new LinkedList<Town>());
			return true;
		}
	}

	@Override
	/**
	 * Returns true if and only if this graph contains an edge going
	 * from the source vertex to the target vertex. In undirected graphs the
	 * same result is obtained when source and target are inverted. If any of
	 * the specified vertices does not exist in the graph, or if is
	 * null, returns false.
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 *
	 * @return true if this graph contains the specified edge.
	 */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {

		// If either vertex is null
		if(Graph.get(sourceVertex) == null || Graph.get(destinationVertex) == null)
			return false;

		// Locate the edge
		for(Road r: edges) {
			if(r.contains(destinationVertex) && r.contains(sourceVertex))
				return true;
		}

		return false;
	}

	@Override
	/**
	 * Returns true if this graph contains the specified vertex. More
	 * formally, returns true if and only if this graph contains a
	 * vertex u such that u.equals(v). If the
	 * specified vertex is null returns false.
	 *
	 * @param v vertex whose presence in this graph is to be tested.
	 *
	 * @return true if this graph contains the specified vertex.
	 */
	public boolean containsVertex(Town v) {

		// If vertex is null
		if(v == null)
			return false;

		return Graph.containsKey(v);
	}


	@Override
	/**
	 * Returns a set of the edges contained in this graph. The set is backed by
	 * the graph, so changes to the graph are reflected in the set. If the graph
	 * is modified while an iteration over the set is in progress, the results
	 * of the iteration are undefined.
	 *
	 *
	 * @return a set of the edges contained in this graph.
	 */
	public Set<Road> edgeSet() {
		return edges;
	}

	@Override
	/**
	 * Returns a set of all edges touching the specified vertex (also
	 * referred to as adjacent vertices). If no edges are
	 * touching the specified vertex returns an empty set.
	 *
	 * @param vertex the vertex for which a set of touching edges is to be
	 * returned.
	 *
	 * @return a set of all edges touching the specified vertex.
	 *
	 * @throws IllegalArgumentException if vertex is not found in the graph.
	 * @throws NullPointerException if vertex is null.
	 */
	public Set<Road> edgesOf(Town vertex) {

		// If vertex does not exist in the graph
		if(!containsVertex(vertex))
			throw new IllegalArgumentException();

		// If vertex is null
		if(vertex == null)
			throw new NullPointerException();

		// Create the set to return
		Set<Road> returnThis = new HashSet<>();

		// Add every edge of the vertex
		for(Road r: edges) {
			if(r.contains(vertex))
				returnThis.add(r);
		}
		return returnThis;
	}

	@Override
	/**
	 * Removes an edge going from source vertex to target vertex, if such
	 * vertices and such edge exist in this graph.
	 *
	 * If weight >- 1 it must be checked
	 * If description != null, it must be checked
	 *
	 * Returns the edge if removed
	 * or null otherwise.
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 * @param weight weight of the edge
	 * @param description description of the edge
	 *
	 * @return The removed edge, or null if no edge removed.
	 */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (description == null || weight < -1) {
			return null;
		}

		// Find the edge and remove it
		Road removeThis = getEdge(sourceVertex, destinationVertex);
		if (removeThis != null) {
			edges.remove(removeThis);

			// Check the vertexes exist in the graph and remove them
			if(Graph.get(sourceVertex) != null && Graph.get(destinationVertex) != null) {
				Graph.get(sourceVertex).remove(sourceVertex);
				Graph.get(destinationVertex).remove(destinationVertex);
				Graph.replace(sourceVertex, null);
				Graph.replace(destinationVertex, null);

				// Remove the towns from the adjacent towns list
				sourceVertex.removeAdjacentTown(destinationVertex);
				destinationVertex.removeAdjacentTown(sourceVertex);

				return removeThis;
			}
		}
		return null;
	}


	@Override
	/**
	 * Removes the specified vertex from this graph including all its touching
	 * edges if present. More formally, if the graph contains a vertex
	 * u such that u.equals(v), the call removes all edges
	 * that touch u and then removes u itself. If no
	 * such u is found, the call leaves the graph unchanged.
	 * Returns true if the graph contained the specified vertex. (The
	 * graph will not contain the specified vertex once the call returns).
	 *
	 * If the specified vertex is null returns false.
	 *
	 * @param v vertex to be removed from this graph, if present.
	 *
	 * @return true if the graph contained the specified vertex;
	 * false otherwise.
	 */
	public boolean removeVertex(Town v) {

		// If graph doesn't have the vertex
		if(!Graph.containsKey(v))
			return false;

		// Find adjacent edges and remove them.
		Set<Road> adjacentEdges;
		adjacentEdges = edgesOf(v);

		for(Road r: adjacentEdges) {
			removeEdge(r.getSource(), r.getDestination(), r.getWeight(), r.getName());
			edges.remove(r);
		}
		// Remove edge from graph
		Graph.remove(v);
		return true;
	}

	@Override
	/**
	 * Returns a set of the vertices contained in this graph. The set is backed
	 * by the graph, so changes to the graph are reflected in the set. If the
	 * graph is modified while an iteration over the set is in progress, the
	 * results of the iteration are undefined.
	 *
	 *
	 * @return a set view of the vertices contained in this graph.
	 */
	public Set<Town> vertexSet() {
		return Graph.keySet();
	}

	@Override
	/**
	 * Find the shortest path from the sourceVertex to the destinationVertex
	 * call the dijkstraShortestPath with the sourceVertex
	 * @param sourceVertex starting vertex
	 * @param destinationVertex ending vertex
	 * @return An ArrayList of Strings that describe the path from sourceVertex
	 * to destinationVertex
	 * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
	 */
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {

		// Call Dijkstra's algorithm with the source vertex
		dijkstraShortestPath(sourceVertex);

		// ArrayList of Strings to hold the results.
		ArrayList<String> result = new ArrayList<>();

		// Create a variable to hold the previous town
		Town prev = new Town();
		Town current = destinationVertex;

		// Loop through the vertexes in the shortest path
		while(current != sourceVertex) {
			// Get the current road
			prev = prevNodes.get(current);
			Road r = getEdge(current, prev);

			// Construct the string for the shortest path result
			result.add(prev + " via " + r.getName() + " to " + current + " " + r.getWeight() + " mi");
			current = prev;
		}

		// Flip the ArrayList for proper order.
		Collections.reverse(result);

		return result;
	}

	@Override
	/**
	 * Dijkstra's Shortest Path Method.  Internal structures are built which
	 * hold the ability to retrieve the path, shortest distance from the
	 * sourceVertex to all the other vertices in the graph, etc.
	 * @param sourceVertex the vertex to find shortest path from
	 *
	 */
	public void dijkstraShortestPath(Town sourceVertex) {

	    // Stores the total cost to each vertex from source
	    edgeCost = new HashMap<>();

	    // Stores shortest path structure
	    prevNodes = new HashMap<>();

	    // Stores the processed vertexes
	    Set<Town> visited = new HashSet<>();

	    ValueComparator sortByValues = new ValueComparator();

	    // Initialize cost of unvisited vertexes to infinity
	    for(Town t: Graph.keySet()) {
	        edgeCost.put(t, Integer.MAX_VALUE);
	        prevNodes.put(t, null);
	    }

	    // Set the cost of the origin vertex to 0
	    edgeCost.put(sourceVertex, 0);

	    // Create a list of unvisited towns
	    List<Entry<Town, Integer>> unvisitedTowns = new ArrayList<>(edgeCost.entrySet());

	    unvisitedTowns.sort(sortByValues);

	    // Initialize a queue to store the towns to be visited
	    Queue<Town> queue = new LinkedList<>();
	    queue.add(sourceVertex);

	    // Loop until queue is empty
	    while(!queue.isEmpty()) {

	        Town currentTown = queue.poll();
	        visited.add(currentTown);

	        // Check all adjacent towns
	        for(Town neighbor: currentTown.getAdjacentTown()) {

	            int currentCost = edgeCost.get(currentTown);
	            int adjWeight = getEdge(currentTown, neighbor).getWeight();

	            // If town has not been processed
	            if(!visited.contains(neighbor)) {

	                // If cost is infinity
	                if(currentCost == Integer.MAX_VALUE) {
	                    edgeCost.replace(neighbor, adjWeight);
	                    prevNodes.replace(neighbor, currentTown);
	                }

	                else { // If cost not infinity
	                    int altPath = currentCost + adjWeight;

	                    // If alternative path has lower cost than prev cost
	                    if(altPath < edgeCost.get(neighbor)) {
	                        edgeCost.replace(neighbor, altPath);
	                        prevNodes.replace(neighbor, currentTown);
	                    }
	                }

	                // Add adjacent vertex to queue
	                if (!queue.contains(neighbor)) {
	                    queue.add(neighbor);
	                }
	            }
	        }

	        // Sort the cost of unvisited towns
	        unvisitedTowns = new ArrayList<>(edgeCost.entrySet());
	        unvisitedTowns.sort(sortByValues);
	    }
	}

}