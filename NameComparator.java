package CIS350;

import java.util.Comparator;

public class NameComparator implements Comparator<Project> {
	/**
	 * compares the names
	 * @param s1 is a name
	 * @param s2 is a name
	 * @return returns results
	 */
	
	@Override
	public int compare(Project o1, Project o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
