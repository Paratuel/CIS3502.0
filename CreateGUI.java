package CIS350;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class CreateGUI extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private String projectName = null;
	private String subName = null;
	private String dueDate;
	private String reminder;
	private String notes;
	private String notThis = "USED FOR SPLITTING PROJECT";
	private JPanel northPanel, centralPanel, southPanel, verySouthPanel;
	
	private GridLayout layout;

	private JButton okButton; 
	private JButton cancelButton;
	private JButton complete, delete, sub;

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
	private boolean isSubOk, subUsed, isCompleteOk, isDeleteOk;
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
		setupDialog();
	}
	
	//adding a sub and editing main project
	public CreateGUI(ProjectGUI parent, String projName, String date, String rem, String n){
		super(parent, true);
		subUsed = false;
		this.projectName = projName;
		this.dueDate = date;
		this.reminder = rem;
		this.notes = n;
		setTitle("Editing");
		editDialog(projectName, dueDate, reminder, notes);
	}
	
	//editing with or without sub
	public CreateGUI(ProjectGUI parent, String n, String s, String d, String r, 
			String notes){
		super(parent, true);
		this.projectName = n;
		this.subName = s;
		this.dueDate = d;
		this.reminder = r;
		this.notes = notes;
		setTitle("Splitting Project");
		subDialog(projectName, subName, dueDate, reminder, notes);
	}
	
	public void setupDialog(){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		northPanel = new JPanel(new GridLayout(7,2));
		southPanel = new JPanel(new GridLayout(1,1));
		nameField = new JTextField(20);
		dateField = new JTextField(10);
		noteField = new JTextField(70);
		reminderField = new JTextField(20);
		
		dateField.setText("10/10/2012");
		noteField.setText("Take action NOW!");
		reminderField.setText("0");
		
		//isOk = false;
		//isSubOk = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		aProject = new Project();
	
		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		complete = new JButton("Complete");
		complete.addActionListener(this);
		sub = new JButton("Split Project");
		sub.addActionListener(this);
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");

		nameField.setText("Math Homework");
		northPanel.add(nameLabel);
		northPanel.add(nameField);
		northPanel.add(dateLabel);
		northPanel.add(dateField);
		northPanel.add(remLabel);
		northPanel.add(reminderField);
		northPanel.add(noteLabel);
		northPanel.add(noteField);
		northPanel.add(cancelButton);
		northPanel.add(okButton);
		add(northPanel, BorderLayout.NORTH);
		setSize(400, 200);

		setLocationRelativeTo(null);
		setVisible(true); 
	}
	
	public void editDialog(String name, String date, String rem, String notes){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		northPanel = new JPanel(new GridLayout(7,2));
		southPanel = new JPanel(new GridLayout(1,1));
		nameField = new JTextField(20);
		dateField = new JTextField(10);
		noteField = new JTextField(70);
		reminderField = new JTextField(20);
		
		dateField.setText(date);
		noteField.setText(notes);
		reminderField.setText(rem);
		
		//isOk = false;
		//isSubOk = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		aProject = new Project();
	
		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		complete = new JButton("Complete");
		complete.addActionListener(this);
		sub = new JButton("Split Project");
		sub.addActionListener(this);
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");

		nameField.setText(projectName);
		subField = new JTextField(3);
		subField.setText(" ");
		subLabel = new JLabel("Split Project");
		northPanel.add(nameLabel);
		northPanel.add(nameField);
		northPanel.add(dateLabel);
		northPanel.add(dateField);
		northPanel.add(remLabel);
		northPanel.add(reminderField);
		northPanel.add(noteLabel);
		northPanel.add(noteField);
		//northPanel.add(subLabel);
		//northPanel.add(subField);
		northPanel.add(cancelButton);
		northPanel.add(okButton);
		northPanel.add(delete);
		northPanel.add(complete);
		southPanel.add(sub);
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		setSize(400, 255);
		//subUsed = true;

		setLocationRelativeTo(null);
		setVisible(true); 
	}
	
	public void subDialog(String a, String b, String c, String d, String e){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		northPanel = new JPanel(new GridLayout(7,2));
		nameField = new JTextField(20);
		dateField = new JTextField(10);
		noteField = new JTextField(70);
		reminderField = new JTextField(20);
		
		dateField.setText(c);
		noteField.setText(e);
		reminderField.setText(d);
		
		//isOk = false;
		//isSubOk = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		aProject = new Project();
		
		WIDTH = 400;
		HEIGHT = 255;
		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		complete = new JButton("Complete");
		complete.addActionListener(this);
		sub = new JButton("Split Project");
		sub.addActionListener(this);
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		
		remLabel = new JLabel("Reminder (Days Needed):");

		nameField.setText(projectName);
		subField = new JTextField(3);
		if (b != null){
			subField.setText(b);
			subLabel = new JLabel("Split Project");
			northPanel.add(nameLabel);
			northPanel.add(nameField);
			northPanel.add(subLabel);
			northPanel.add(subField);
			northPanel.add(dateLabel);
			northPanel.add(dateField);
			northPanel.add(remLabel);
			northPanel.add(reminderField);
			northPanel.add(noteLabel);
			northPanel.add(noteField);
		
			southPanel = new JPanel(new GridLayout(1,1));
			northPanel.add(cancelButton);
			northPanel.add(okButton);
			northPanel.add(delete);
			northPanel.add(complete);
			//southPanel.add(sub);

			add(northPanel, BorderLayout.NORTH);
			//add(southPanel, BorderLayout.SOUTH);
			subUsed = true;
		}else{
			subField.setText("");
			subLabel = new JLabel("Split Project");
			northPanel.add(nameLabel);
			northPanel.add(nameField);
			northPanel.add(subLabel);
			northPanel.add(subField);
			northPanel.add(dateLabel);
			northPanel.add(dateField);
			northPanel.add(remLabel);
			northPanel.add(reminderField);
			northPanel.add(noteLabel);
			northPanel.add(noteField);
		
			southPanel = new JPanel(new GridLayout(1,1));
			northPanel.add(cancelButton);
			northPanel.add(okButton);
			northPanel.add(delete);
			northPanel.add(complete);
			//southPanel.add(sub);

			add(northPanel, BorderLayout.NORTH);
			//add(southPanel, BorderLayout.SOUTH);
			subUsed = true;
		}
		
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

		}
		if(e.getSource() == delete){
			if(isValid()){
				isDeleteOk = true;
				cancel = false;
				setVisible(false);
			}
		}
		if(e.getSource() == complete){
			if (isValidField()){
				isCompleteOk = true;
				cancel = false;
				setVisible(false);
			}
		}
		if(e.getSource() == sub){
			if (isValidField()){
				isSubOk = true;
				cancel = false;
				setVisible(false);
			}
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
	
	public boolean isCompletePressed(){
		return isCompleteOk;
	}
	
	public boolean isDeletePressed(){
		return isDeleteOk;
	}
	
	public boolean isSubPressed(){
		return isSubOk;
	}
	
	public String getName(){
		return nameField.getText();
	}
	public String getDueDate(){
		return dateField.getText();
	}
	public String getNotes(){
		return noteField.getText();
	}
	public String getReminder(){
		return reminderField.getText();
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
