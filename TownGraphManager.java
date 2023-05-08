import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;


/**
 * This is a manager class for the Town, Road, and Graph class implementations.
 * @author Daniel Xu
 * Version: 05/08/2023
 */

public class TownGraphManager implements TownGraphManagerInterface{

	private Graph graph;

	public TownGraphManager() {
		graph = new Graph();
	}

	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		graph.addEdge(getTown(town1), getTown(town2), weight, roadName);
		return true;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	public String getRoad(String town1, String town2) {
		return graph.getEdge(getTown(town1), getTown(town2)).getName();
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v) {
		graph.addVertex(new Town(v));
		return true;
	}

	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name) {
		Set<Town> allTowns = graph.vertexSet();

		// Find the town in the set
		for(Town t: allTowns) {
			if(t.getName().equals(name))
				return t;
		}

		return null;
	}


	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v) {
		return graph.containsVertex(getTown(v));
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2) {

		return graph.containsEdge(getTown(town1), getTown(town2));
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads() {
		ArrayList<String> roadsArrayList = new ArrayList<>();

		// Loop through the set of the roads and add them to the ArrayList
		Set<Road> roads = graph.edgeSet();
		for(Road r: roads) {
			roadsArrayList.add(r.getName());
		}

		Collections.sort(roadsArrayList);

		return roadsArrayList;
	}
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		
		try {
			graph.removeEdge(getTown(town1), getTown(town2), 0, road);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v) {
		return graph.removeVertex(getTown(v));
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	public ArrayList<String> allTowns() {
		
		// Create a set of the towns and the ArrayList to return
		Set<Town> allTowns = graph.vertexSet();
		ArrayList<String> toReturn = new ArrayList<>();

		for(Town t: allTowns) {
			toReturn.add(t.getName());
		}
		// Sort the ArrayList
		Collections.sort(toReturn);

		return toReturn;
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
	
		ArrayList<String> path = new ArrayList<>();

		// Get the shortest path or return null if no path exist.
		try {
			path = graph.shortestPath(getTown(town1), getTown(town2));
		} catch(Exception e) {
			return null;
		}

		return path;
	}
}
