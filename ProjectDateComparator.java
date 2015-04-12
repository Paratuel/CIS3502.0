package CIS350;

import java.util.Comparator;

public class ProjectDateComparator implements Comparator<Project>{
	
	/**
	 * compares the dates
	 * @param p1 is the first date
	 * @param p2 is the second date
	 * @return the results
	 */
	@Override
	public int compare(Project p1, Project p2){
		if(p1.getName().equals(p2.getName()) && p1.getSubName() == null){
			return -1;
		}
		if(p1.getName().equals(p2.getName()) && p2.getSubName() == null){
			return 1;
		}
		return p1.getDueDate().compareTo(p2.getDueDate());
	}
}
