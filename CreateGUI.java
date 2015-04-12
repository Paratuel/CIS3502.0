package CIS350;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class CreateGUI extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private String projectName = null;
	private String subName = null;
	private GregorianCalendar dueDate;
	private int reminder;
	private String notes;
	private String notThis = "USED FOR SPLITTING PROJECT";
	
	private GridLayout layout;

	private JButton okButton; 
	private JButton cancelButton;
	private JButton complete, delete;

	private JLabel nameLabel;
	private JLabel dateLabel;
	//private JLabel catLabel; 
	private JLabel noteLabel;
	private JLabel remLabel;
	private JLabel subLabel;

	private JTextField nameField;
	private JTextField dateField;
	//private JTextField catField;
	private JTextField noteField;
	private JTextField subField;
	private JTextField reminderField;

	private DateFormat format;

	private int WIDTH;
	private int HEIGHT;

	private boolean isOk, ok, cancel;
	//private boolean cancel;
	private boolean isSubOk, subUsed;
	private ProjectGUI parent;

	private Project aProject;

	/**
	 * Sets up the panel for adding a Project
	 * @param ProjectGUI is the parent GUI
	 * @param Project Proj is passing in the array from ProjectGUI
	 */
	public CreateGUI(ProjectGUI parent){
		super(parent, true);
		String a = null;
		setTitle("New Project");
		setupDialog(a);
	}
	public CreateGUI(ProjectGUI parent, String projName){
		super(parent, true);
		this.projectName = projName;
		setTitle("Splitting a Project");
		setupDialog(projName);
	}
	public CreateGUI(ProjectGUI parent, String n, String s, GregorianCalendar d, int r, 
			String notes){
		super(parent, true);
		this.projectName = n;
		this.subName = s;
		this.dueDate = d;
		this.reminder = r;
		this.notes = notes;
		setTitle("Edit a Project");
		editDialog(projectName, subName, dueDate, reminder, notes);
	}
	
	public void setupDialog(String a){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		nameField = new JTextField(20);
		dateField = new JTextField(10);
		noteField = new JTextField(70);
		reminderField = new JTextField(20);
		
		dateField.setText("10/10/2012");
		noteField.setText("Take action NOW!");
		reminderField.setText("0");
		//subField.setText(notThis);
		
		isOk = false;
		isSubOk = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		aProject = new Project();
		
		WIDTH = 400;
		HEIGHT = 400;
		layout = new GridLayout(7,2);
		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");
		
		if(a != null){
			nameField.setText(projectName);
			subField = new JTextField(3);
			subField.setText(" ");
			subLabel = new JLabel("Split Project");
			add(nameLabel);
			add(nameField);
			add(subLabel);
			add(subField);
			subUsed = true;
		}else{
			nameField.setText("Math Homework");
			//subField.setText(notThis);
			add(nameLabel);
			add(nameField);
		}
		
		add(dateLabel);
		add(dateField);
		add(remLabel);
		add(reminderField);
		add(noteLabel);
		add(noteField);
		
		add(cancelButton);
		add(okButton);

		setLayout(layout);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true); 
	}
	
	public void editDialog(String a, String b, GregorianCalendar c, int d, String e){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		nameField = new JTextField(20);
		dateField = new JTextField(10);
		noteField = new JTextField(70);
		reminderField = new JTextField(20);
		
		dateField.setText(Utilities.gToString(c));
		noteField.setText(e);
		reminderField.setText(String.valueOf(d));

		
		isOk = false;
		isSubOk = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		//aProject = new Project();
		
		WIDTH = 400;
		HEIGHT = 400;
		layout = new GridLayout(7,2);
		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		complete = new JButton("Complete");
		complete.addActionListener(this);
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");
		
		if(b != null){
			nameField.setText(a);
			subField = new JTextField(3);
			subField.setText(b);
			subLabel = new JLabel("Split Project");
			add(nameLabel);
			add(nameField);
			add(subLabel);
			add(subField);
			subUsed = true;
		}else{
			nameField.setText(a);
			//subField.setText(notThis);
			add(nameLabel);
			add(nameField);
		}
		
		add(dateLabel);
		add(dateField);
		add(remLabel);
		add(reminderField);
		add(noteLabel);
		add(noteField);
		
		add(delete);
		add(complete);
		add(cancelButton);
		add(okButton);

		setLayout(layout);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true); 
	}

	/*
	 * Assigns actions to buttons and JMenuItems
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			isOk = false;
			cancel = true;
			setVisible(false);
			clear();
			return;
		}
		if (e.getSource() == okButton) {
			if(isValidField()){
				isOk = true;
				cancel = false;
				setVisible(false);
			}else{
				JOptionPane.showMessageDialog(null, "Some fields are not entered correctly "
						+ "or missing information.", "Input Validation", JOptionPane.ERROR_MESSAGE);
			}
			return;
//			try {
//				String newName = nameField.getText();
//				String newDateString = dateField.getText();
//				int newReminder = getReminder();
//				String newNote = noteField.getText();
//				Date newDate = format.parse(newDateString);
//				GregorianCalendar newDate2 = new GregorianCalendar();
//				int reminder = Integer.parseInt(reminderField.getText());
//				newDate2.setTime(newDate);
//				aProject.setName(newName);
//				aProject.setDueDate(newDate2);
//				aProject.setNotes(newNote);
//				aProject.setReminder(reminder);
//				aProject.setReminder(newReminder);
//				isOk = true;
//				dispose();
//				int num = Integer.parseInt(subField.getText());
//				while (num > 0) {
//					SubCreateGUI subCreate = new SubCreateGUI(this);
//					if (subCreate.isOkPressed()) {
//						aProject.addItems(subCreate.whatProject());
//					}
//					num--;
//				}
//			} catch (Exception x) {
//				x.printStackTrace();
//			}
		}
	}
	
	public boolean isCancel(){
		return cancel;
	}

	/*
	 * Checks status
	 * @return isOk if true
	 */
	public boolean isOkPressed() {
		return isOk;
	}
	
	public String getName(){
		return nameField.getText();
	}
	public GregorianCalendar getDueDate(){
		return Utilities.strToGregCalendar(dateField.getText());
	}
	public String getNotes(){
		return noteField.getText();
	}
	public int getReminder(){
		return Integer.parseInt(reminderField.getText());
	}
	public String getSub(){
		if(subUsed == true){
			return subField.getText();
		}
		return null;
	}
	
	/**
	 * Checking inputs from user
	 * @return state
	 */
	public boolean isValidField(){
		if(nameField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Name Wasn't Entered.", "Input Validation",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(subUsed == true){
			if(subField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Split Name Wasn't Entered.", "Input Validation",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return true;
	}

	/**
	 * Checks which Project
	 * @return aProject is the Project
	 */
	public Project whatProject() {
		return aProject;
	}
	
	public void clear(){
		nameField.setText(null);
		dateField.setText(null);
		noteField.setText(null);
		reminderField.setText(null);
		/*
		 * if a subgroup is being used.
		 */
		if (subUsed == true){
			subField.setText(null);
		}
	}
}
