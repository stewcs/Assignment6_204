import java.util.Comparator;
import java.util.Map.Entry;

public class ValueComparator implements Comparator<Entry<Town, Integer>>{

	@Override
	// Compares the values of two entries
	public int compare(Entry<Town, Integer> e1, Entry<Town, Integer> e2) {
		return e1.getValue().compareTo(e2.getValue());
	}

}
