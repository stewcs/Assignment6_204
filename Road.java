
/**
 * This is a class that implements a Road object. Provides utility methods like getters/setters, override equals/compare method.
 * @author Daniel Xu
 * Version: 05/07/2023
 */

public class Road implements Comparable<Road> {

	private Town T1;
	private Town T2;
	private int weight;
	private String name;


	public Road(Town source, Town destination, int degrees, String name) {
		this.T1 = source;
		this.T2 = destination;
		this.weight = degrees;
		this.name = name;

	}

	// Constructor with weight preset at 1
	public Road(Town source, Town destination, String name) {
		this.T1 = source;
		this.T2 = destination;
		this.weight = 1;
		this.name = name;
	}

	// Returns true if the edge contains the given town
	public boolean contains(Town town) {
		if(town.getName().equals(T1.getName()) || town.getName().equals(T2.getName()))
			return true;
		else
			return false;
	}


	// Returns true if each of the ends of the road r is same as the ends of this road
	public boolean equals(Object r) {

		Road r1 = (Road) r;

		if ((r1.getDestination().getName().equals(this.getDestination().getName()) 
				&& r1.getSource().getName().equals(this.getSource().getName()))

				|| 

				(r1.getDestination().getName().equals(this.getSource().getName()) 
						&& r1.getSource().getName().equals(this.getDestination().getName()))
				)
			return true;
		else
			return false;
	}



	// Returns the road name
	public String getName() {
		return this.name;
	}

	// Returns the first town on the road
	public Town getSource() {
		return T1;
	}

	// Returns the destination town on the road
	public Town getDestination() {
		return T2;
	}

	// Returns the distance of the road
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int w) {
		weight = w;
	}

	// Returns the toString of the Road
	public String toString() {
		return (this.name + " road links town " + T1.getName() + " to town " + T2.getName()+". Weight: "+ weight);
	}

	@Override
	public int compareTo(Road o) {
		return weight - o.getWeight();
	}


}
