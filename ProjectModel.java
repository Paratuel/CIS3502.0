package CIS350;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;

import java.io.FileNotFoundException;

/**
 * Class for the entire storage of our Project. 
 * @author  Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 *
 */
public class ProjectModel  implements Serializable {

	/**
	 * serialVersionUID. 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Array for storing each Project and its information.
	 */
	private ArrayList<Project> myArray;
	private int number;

	/**
	 * Project to store in the array.
	 */
	private Project project;

	/**
	 * String array for storing the JTable columnNames.
	 */
	private String[] columnNames = 
		{"Project Name", "Sub-Projects", "Due Date", "Reminders", "Notes", "Complete"};

	/**
	 * Initializing the Project object myArray.
	 */
	public ProjectModel() {
		myArray = new ArrayList<Project>();
	}
	
	public ArrayList<Project> getArray(){
		return myArray;
	}
	
	public void setArray(ArrayList<Project> n){
		Collections.copy(myArray, n);
	}
	/**
	 * returns the columnNames length.
	 * @return columnNames length
	 */
	public final int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * retrieves the index of myArray.
	 * @param index is the position in myArray that is needed
	 * @return the myArray at the index
	 */
	public final Project get(final int index) {
		return myArray.get(index);
	}

	/**
	 * Finds the index of a particular Project.
	 * @param a is the Project it is asking for
	 * @return the index of myArray at a
	 */
	public final int indexOf(final Project a) {
		return myArray.indexOf(a);
	}

	/**
	 * Returns the myArray size.
	 * @return myArray.size() of the project
	 * */
	public final int getSize() {
		return myArray.size();
	}

	/**
	 * Returns an element from myArray.
	 * @param index is the spot in the array needed.
	 * @return myArray.get(index) is the element needed 
	 */
	public final Object getElementAt(final int index) {
		return myArray.get(index);
	}

	/**
	 * Adding an element to myArray with p being passed into it.
	 * @param p is the Project object being passed into it. 
	 */
	public final void add(final Project p) {
		myArray.add(p);
		//fireTableRowsInserted(0, myArray.size());
	}

	/**
	 * Deleting a certain element in the myArray.
	 * @param index is the position being passed in
	 */
	public final void delete(final int index) {
		Object obj = myArray.remove(index);
		//fireTableRowsDeleted(0, myArray.size());
		return;
	}

	/**
	 * retrieves the index for a particular Project.
	 * @param a is the project.
	 */
	public final void remove(final Project a) {
		delete(indexOf(a));
	}
	
	public void upDate(String a, String b) {
		for (int i = 0; i < myArray.size(); i++) {
			if (myArray.get(i).getName().equals(a)) {
				myArray.get(i).setName(b);
			}
		}
		
		return;
	}
	
//	public void refresh() {
//		fireTableRowsUpdated(0, myArray.size());
//	}
	

	/**
	 * returns the columnNames that will fill the table in the GUI.
	 * @param col sets the position for the columnNames on the table
	 * @return columnNames and positions
	 */
	public final String getColumnName(final int col) {
		return columnNames[col];
	}

	/**
	 * Sorts array by the due date.
	 * (Still to be implemented)
	 */
	public final void sortByDate() {
		if (myArray.size() > 1) {
			Collections.sort(myArray, new ProjectDateComparator());
			//this.fireTableRowsUpdated(0, myArray.size());
		}
	}
	
	/**
	 * Sorts the listSites by Name and updates the GUI table.
	 */
	public final void sortByName() {
		if (myArray.size() > 1) {
			Collections.sort(myArray, new NameComparator());
			//this.fireTableRowsUpdated(0, myArray.size() - 1);
		}
	}
	
	public final void sortByWeek(int n) {
		for (int i = 0; i < myArray.size(); i++) {
				int x = Utilities.CurrentDateComp(myArray.get(i).getDueDate());
				if (x <= (7 * n)) {
					//this.fireTableRowsUpdated(0, myArray.size() - 1);
				}
			
		}
	}

	/**
	 * Saving the file of the program.
	 * @param file being passed into it.
	 */
	public final void save(final File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(myArray);
			oos.close();
			bos.close();
			fos.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param file being passed into it.
	 */
	public final void load(final File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			myArray = (ArrayList<Project>) ois.readObject();
			ois.close();
			bis.close();
			fis.close();
			//fireTableRowsInserted(myArray.size() - 1, myArray.size() - 1);
		} catch (FileNotFoundException f) {
			save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < myArray.size(); i++) {
			if (myArray.get(i).getReminder() != 0) {
				int x = Utilities.CurrentDateComp(myArray.get(i).getDueDate());
				if (x <= myArray.get(i).getReminder()) {
					JOptionPane.showMessageDialog(null, "Reminder for Project: "
							+ myArray.get(i).getName());

				}
			}
		}
	}
}
