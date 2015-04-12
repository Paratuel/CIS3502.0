package CIS350;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Project Management is a program that helps with the organization 
 * of Projects needed to be done with the added benefit of subgroups.
 * Created by Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 */
public class Project implements Serializable {

	/**
	 * serialVersionUID. 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Stores the name of the project.
	 */
	private String name;
	
	private String subName;
	
	/**
	 * Stores the dueDate of the project.
	 */
	private GregorianCalendar dueDate;
	
	private GregorianCalendar subDueDate;
	
	/**
	 * Stores the number of days for the reminder.
	 */
	private int reminder;
	
	/**
	 * Stores the notes of the project.
	 */
	private String notes;
	
	private String subNotes;
	
	/**
	 * Stores the sub-projects of a project. 
	 */
	private ArrayList<Project> subTasks;
	
	/**
	 * Stores whether the project is complete as
	 * true or flase.
	 */
	private boolean done;
	
	private boolean sub;

	//ArrayList<Project> Projects = new ArrayList<Project>();

	/**
	 * Initializing needed variables.
	 */
	public Project() {
		name = "Untitled";
		dueDate = new GregorianCalendar(2015,1,1);
		reminder = 0;
		notes = "";
		done = false;
		subTasks = new ArrayList<Project>();
	}

	/**
	 * Initializing needed variables that are passed into the construct.
	 * @param n is the name of the Project
	 * @param dd is the due date of the Project
	 * @param rr is the reminder for the Project
	 * @param nt notes for the Project
	 * @param sub is the array of subprojects 
	 */
	public Project(final String n, final GregorianCalendar dd, final int rr, 
			final String nt, final ArrayList<Project> sub) {
		this.name = n;
	//	this.subName = s;
		this.dueDate = dd;
		this.reminder = rr;
		this.notes = nt;
		this.subTasks = sub;
		//done = false;
	}

	/**
	 * Initializing the variables that are passed into the construct.
	 * @param n is the project name
	 * @param s is the sub name
	 * @param dd is the due date
	 * @param nt is the note
	 * @param rr is the reminder
	 * @param b is the project state
	 */
	public Project(final String n, String s, final GregorianCalendar dd, 
			final int rr, final String nt, boolean b) {
		this.name = n;
		this.subName = s;
		this.dueDate = dd;
		this.reminder = rr;
		this.notes = nt;
		this.done = b;
		if(subName == null) {
			sub = false;
		} else {
			sub = true;
		}
		//subTasks = new ArrayList<Project>();
	}



	/**
	 * Setting up the string that will print.
	 * @return toString
	 */  
	public final String toString() {
		return "Subtask: " + name + "\nDue Date: " 
				+ dueDate.get(Calendar.MONTH) + "/" 
				+ dueDate.get(Calendar.DAY_OF_MONTH) + "/" 
				+ dueDate.get(Calendar.YEAR) + "\nNotes: " + notes;
	}

	/**
	 * Returns the name of the Project.
	 * @return name of the Project
	 */
	protected final String getName() {
		return name;
	}

	/**
	 * Sets the name of the Project object with the name being passed into it.
	 * @param n is the Project name.
	 */
	protected final void setName(final String n) {
		this.name = n;
	}
	
	/**
	 * Returns the subName of the subGroup.
	 * @return subName of the subGroup
	 */
	protected String getSubName() {
		return subName; 
	}
	
	/**
	 * sets the name of the subGroup with the name being passed into it.
	 * @param n is the name for the subName
	 */
	protected void setSubName(String n) {
		this.subName = n;
	}

	/**
	 * Returns the due date of the Project.
	 * @return dueDate of the Project
	 */
	protected final GregorianCalendar getDueDate() {
		return dueDate;
	}

	/**
	 * Sets the dueDate of the Project object with the dueDate passing into it.
	 * @param dd is the due date of the Project
	 */
	protected final void setDueDate(final GregorianCalendar dd) {
		this.dueDate = dd;
	}

	/**
	 * Returns the reminder for the Project.
	 * @return reminder of the Project
	 */
	protected final int getReminder() {
		return reminder;
	}

	/**
	 * Sets the reminder of the Project object with the 
	 * reminder passing into it.
	 * @param rr is the reminder for the Project
	 */
	protected final void setReminder(final int rr) {
		this.reminder = rr;
	}

	/**
	 * Returns the notes of of the Project.
	 * @return notes is the notes taken for the Project
	 */
	protected final String getNotes() {
		return notes;
	}

	/**
	 * Sets up the notes of the Project object with the notes passing into it.
	 * @param n is the notes of the Project
	 */
	protected final void setNotes(final String n) {
		this.notes = n;
	}

	/**
	 * Returns the boolean done.
	 * @return done true if the project is complete,
	 * false if not 
	 */
	protected final boolean getDone() {
		return done;
	}


	protected void setDone(boolean a) {
		this.done = a;		
	}
		
	/**
	 * Checking if this is a subGroup or not.
	 * @return sub = true if it is a subGroup
	 */
	protected boolean getSub() {
		return sub;
	}
	
	/**
	 * Setting whether this is a subGroup or not.
	 * @param a is the boolean for sub
	 */
	protected boolean setSub(boolean a) {
		return sub;
	}

	/**
	 * Adds subgroups into the subTasks vector 
	 * with the subgroup being passed into it.
	 * @param p is the subgroup of the Project
	 */
	protected final void addItems(final Project p) {
		subTasks.add(p);
	}

	/**
	 * Returns the Subtasks of the Project.
	 * @return subTasks are the subgroups of the Project
	 */
	protected final ArrayList<Project> getSubtasks() {
		return subTasks;
	}
}
