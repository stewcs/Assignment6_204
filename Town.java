import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that implements a Town object. Provides utility methods like getters/setters, override equals/compare method.
 * @author Daniel Xu
 * Version: 05/07/2023
 */
public class Town implements Comparable<Town>{
	private String name;
	private List<Town> adjacentTowns;
	
	// Default Constructor no name
	public Town() {
		name = "";
		adjacentTowns = new ArrayList<Town>();
	}
	
	// Copy Constructor
	public Town(Town newTown) {
	    name = newTown.getName();
	    adjacentTowns = new ArrayList<>(newTown.adjacentTowns);
	}

	
	// Constructor with name
	public Town(String name) {
		this.name = name;
		adjacentTowns = new ArrayList<Town>();
	}

	@Override
	// Returns true if the town names are equal, false if not
	public boolean equals(Object obj) {
		return obj.hashCode() == name.hashCode();
	}
	
	/**
	 * @return Name of the town
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the town name.
	 * @param newName
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	
	/**
	 * Returns a list of the adjacent towns
	 * @return adjacentTown
	 */
	public List<Town> getAdjacentTown() {
		return adjacentTowns;
	}

	@Override
	// Returns 0 if names are equal, a positive or negative number if the names are not equal
	public int compareTo(Town o) {
		return name.compareTo(o.name);
	}
	
	// Returns the hash-code for the name of the town
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 *  Returns the town name
	 *  @return name
	 */
	public String toString() {
		return name; 
	}
	
	/**
	 * Adds a new town to adjacentTowns
	 * @param toAdd town to add
	 */
	public void addAdjacentTown(Town toAdd) {
		adjacentTowns.add(toAdd);
	}
	
	/**
	 * Removes a town from adjacentTowns
	 * @param toRemove town to remove
	 */
	public void removeAdjacentTown(Town toRemove) {
		adjacentTowns.remove(toRemove);
	}
}
