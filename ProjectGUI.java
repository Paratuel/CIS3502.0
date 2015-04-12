package CIS350;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;


/**
 * Creates the main GUI of the program.
 * @author Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 *
 */
public class ProjectGUI extends JFrame implements ActionListener {

	/**
	 * serialVersionUID. 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Main frame of the GUI.
	 */
	private JFrame frame;
	private JScrollPane scroll;
	private JPanel special;

	/**
	 * Button for the one week sort.
	 * (To be implemented later)
	 */
	private JButton week1Button;

	/**
	 * Button for the two week sort.
	 * (To be implemented later)
	 */
	private JButton week2Button; 

	/**
	 * Button for the four week sort.
	 * (To be implemented later)
	 */
	private JButton week4Button;

	/**
	 * Button for displaying all projects.
	 * (To be implemented later)
	 */
	private JButton allButton;

	/**
	 * Button for sorting projects by date.
	 */
	private JButton dateButton;

	/**
	 * Button to add a new project.
	 */
	private JButton newItem;

	/**
	 * Button to delete a project.
	 */
	private JButton deleteItem;

	/**
	 * Button to edit a project.
	 */
	private JButton editItem;

	/**
	 * Button to display sub-projects of a project.
	 */
	private JButton subItem;


	/**
	 * Button to declare a project complete or not complete.
	 */
	private JButton doneItem;

	/**
	 * Panel for buttons at the top of the GUI.
	 */
	private JPanel buttonPanel;

	/**
	 * Panel for buttons at the bottom of the GUI.
	 */
	private JPanel southPanel;
	private JPanel centralPanel;

	/**
	 * Menu for containing exit, save, and load.
	 */
	private JMenu fileMenu; 

	/**
	 * Menu for containing about.
	 */
	private JMenu helpMenu;

	/**
	 * Exit MenuItem. Closes program.
	 */
	private JMenuItem exitItem;

	/**
	 * Displays information about the program.
	 */
	private JMenuItem aboutItem;

	/**
	 * Allows user to save current data as a file.
	 */
	private JMenuItem saveItem;

	/**
	 * Creates the ProjectModel for the program.
	 */
	private ProjectModel model;

	/**
	 * Creates a newProject for the program.
	 */
	private CreateGUI newProject;
	private JButton[] projects = new JButton[100];
	private JLabel[][] labels = new JLabel[100][5];
	private JButton[] completed = new JButton[100];
	private ArrayList<Project> temp;
	private int number = 0;
	private JLabel one, two, three, four, five;

	/**
	 * Instantiates the GUI.
	 */
	public ProjectGUI() {
		setupFrame();
		model = new ProjectModel();
		model.load(new File("src/package1/file.ser"));
	}

	/**
	 * Sets up the panel for the parent GUI.
	 */
	public final void setupFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Project Management");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		
		fileMenu.add(exitItem);
		fileMenu.addActionListener(this);
		//helpMenu.add(aboutItem);
		menuBar.add(fileMenu);
				
		JPanel panel;
		
		special = new JPanel(new GridLayout(30,1));
		
		String name = "Serif"; //"Serif", "SansSerif", "Monospaced", or a font name
		
		int style = Font.BOLD; //Font.ITALIC, Font.BOLD, or Font.BOLD | Font.ITALIC
		
		int size = 18; //any number size
	
		newItem = new JButton("+");
		newItem.setFont(new Font(name, style, size));
		newItem.setHorizontalAlignment(SwingConstants.CENTER);
		newItem.addActionListener(this);
		special.add(newItem);
		
		scroll = new JScrollPane(special);
		scroll.setViewportView(special);
		special.setPreferredSize(new Dimension(950, 800));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		frame.add(scroll, BorderLayout.CENTER);
		
		panel = setupNorthPanel();
		frame.add(panel, BorderLayout.NORTH);

		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1000, 500);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Setting up top panel.
	 * @return buttonPanel
	 */
	public final JPanel setupNorthPanel() {
		buttonPanel = new JPanel(new FlowLayout());
		//add(buttonPanel, BorderLayout.NORTH);

		week1Button = new JButton("1 Week");
		week1Button.addActionListener(this);

		week2Button = new JButton("2 Week");
		week2Button.addActionListener(this);

		week4Button = new JButton("4 Week");
		week4Button.addActionListener(this);

		allButton = new JButton("All Projects");
		allButton.addActionListener(this);

		dateButton = new JButton("Sort by Date");
		dateButton.addActionListener(this);

		buttonPanel.add(allButton);
		buttonPanel.add(week1Button);
		buttonPanel.add(week2Button);
		buttonPanel.add(week4Button);
		buttonPanel.add(dateButton);

		return buttonPanel;
	}

	/**
	 * Assigns actions to buttons and JMenuItems.
	 * @param e determines what is clicked and what to do
	 */
	public final void actionPerformed(final ActionEvent e) {
		if (e.getSource() == exitItem) {
			model.save(new File("src/package1/file.ser"));
			System.exit(0);
		}
		//		if (e.getSource() == aboutItem) {
		//			JOptionPane.showMessageDialog(null, "Hello!");
		//		}
		if (e.getSource() == newItem) {
			newProject = new CreateGUI(this);

			if (newProject.isOkPressed()) {
				Project p = new Project(newProject.getName(), null, newProject.getDueDate(), 
						newProject.getReminder(), newProject.getNotes(),  false);
				//				Project p = newProject.whatProject();
				model.add(p);
				addingProject(newProject.getName(), null, newProject.getDueDate(), 
						newProject.getReminder(), newProject.getNotes());
				model.sortByName();
			}
		}
		
		if(e.getActionCommand().equals(String.valueOf(0))){
			edit(0);
		}else if(e.getActionCommand().equals(String.valueOf(1))){
			edit(1);
		}else if(e.getActionCommand().equals(String.valueOf(2))){
			edit(2);
		}else if(e.getActionCommand().equals(String.valueOf(3))){
			edit(3);
		}else if(e.getActionCommand().equals(String.valueOf(4))){
			edit(4);
		}else if(e.getActionCommand().equals(String.valueOf(5))){
			edit(5);
		}else if(e.getActionCommand().equals(String.valueOf(6))){
			edit(6);
		}else if(e.getActionCommand().equals(String.valueOf(7))){
			edit(7);
		}else if(e.getActionCommand().equals(String.valueOf(8))){
			edit(8);
		}else if(e.getActionCommand().equals(String.valueOf(9))){
			edit(9);
		}else if(e.getActionCommand().equals(String.valueOf(10))){
			edit(10);
		}else if(e.getActionCommand().equals(String.valueOf(11))){
			edit(11);
		}else if(e.getActionCommand().equals(String.valueOf(12))){
			edit(12);
		}else if(e.getActionCommand().equals(String.valueOf(13))){
			edit(13);
		}else if(e.getActionCommand().equals(String.valueOf(14))){
			edit(14);
		}else if(e.getActionCommand().equals(String.valueOf(15))){
			edit(15);
		}else if(e.getActionCommand().equals(String.valueOf(16))){
			edit(16);
		}else if(e.getActionCommand().equals(String.valueOf(17))){
			edit(17);
		}else if(e.getActionCommand().equals(String.valueOf(18))){
			edit(18);
		}else if(e.getActionCommand().equals(String.valueOf(19))){
			edit(19);
		}else if(e.getActionCommand().equals(String.valueOf(20))){
			edit(20);
		}
		
		if (e.getSource() == deleteItem) {
//			int index = table.getSelectedRow();
//			if (index != -1) {
//				if (JOptionPane.showConfirmDialog(null, 
//						"Are you sure you would like to delete this project?"
//						, null, JOptionPane.OK_CANCEL_OPTION) != 0) {
//					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//				} else {
//					model.remove(model.get(index));
//				}
//			}
		}
		
			//if (e.getSource()) {
				//if()

//				newProject = new CreateGUI(this, 
//						model.get(i).getName(), 
//						model.get(i).getSubName(),
//						model.get(i).getDueDate(), 
//						model.get(i).getReminder(),
//						model.get(i).getNotes());
				//model.remove(model.get(index));

//				if (newProject.isOkPressed()) {
//					if(newProject.getName() != model.get(i).getName()){
//						one.setText(newProject.getName());
//						model.upDate(model.get(i).getName(), 
//								newProject.getName());
//					}
//					if(newProject.getSub() != model.get(i).getSubName()){
//						two.setText(newProject.getSub());
//						model.get(i).setSubName(newProject.getName());
//					}
//					if(newProject.getDueDate() != model.get(i).getDueDate()){
//						three.setText(Utilities.gToString(newProject.getDueDate()));
//						model.get(i).setDueDate(newProject.getDueDate());
//					}
//					if(newProject.getReminder() != model.get(i).getReminder()){
//						four.setText(String.valueOf(newProject.getReminder()));
//						model.get(i).setReminder(newProject.getReminder());
//					}
//					if(newProject.getNotes() != model.get(i).getNotes()){
//						five.setText(newProject.getNotes());
//						model.get(i).setNotes(newProject.getNotes());
//					}
					
//					Project s = new Project(newProject.getName(), 
//							newProject.getSub(), newProject.getDueDate(), 
//							newProject.getReminder(), newProject.getNotes(), 
//							false);

					//model.remove(model.get(i));
					//remove(i);
					//model.add(s);
//					addingProject(newProject.getName(), null, newProject.getDueDate(), 
//							newProject.getReminder(), newProject.getNotes());
					//model.sortByName();
//				}
//
//			}

		

		if (e.getSource() == doneItem) {
			//			if (model.getSize() == 0) {
			//				JOptionPane.showMessageDialog(null, "No project selected.");
//			} else {
//				int index = table.getSelectedRow();
//				if (index != -1) {
//					if (model.get(index).getDone() == false) {
//						if (JOptionPane.showConfirmDialog(null, 
//								"Have you completed the project?"
//								, null, JOptionPane.OK_CANCEL_OPTION) != 0) {
//							setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//
//
//						} else {
//							model.get(index).setDone(true);
//						}
//					} else {
//						model.get(index).setDone(false);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "No project selected.");
//				}
//				//model.refresh();
//			}
		}
		
		if (e.getSource() == subItem) {
//			int index = table.getSelectedRow();
//			if (index != -1) {
//				String name = model.get(index).getName();
//				newProject = new CreateGUI(this, name);
//				//subGroup = new SubCreateGUI(this);
//
//				if (newProject.isOkPressed()) {
//					Project p = new Project(newProject.getName(), newProject.getSub(), newProject.getDueDate(), 
//							newProject.getReminder(), newProject.getNotes(), false);
//					//Project p = newProject.whatProject();
//					model.add(p);
//					model.sortByName();
//
//				}
//			} else {
//				JOptionPane.showMessageDialog(this, "Please pick a project.");
//			}
		}
		if (e.getSource() == week1Button) {
			model.sortByWeek(1);
		}
		if (e.getSource() == week2Button) {
			model.sortByWeek(2);
		}
		if (e.getSource() == week4Button) {
			model.sortByWeek(4);
		}
		if (e.getSource() == allButton) {
			model.sortByName();
		}
		if (e.getSource() == dateButton) {
			model.sortByDate();
		}

	}
	public void addingProject(String n, String s, GregorianCalendar d, 
			int r, String notes){
		
		projects[number] = new JButton();
		projects[number].setActionCommand(String.valueOf(number));
		projects[number].addActionListener(this);
		projects[number].setLayout(new GridLayout(1,5));
		//now.setSize(5,950);
		//now.setText("");
		labels[number][0] = new JLabel(n);
		labels[number][0].setHorizontalAlignment(SwingConstants.CENTER);
		labels[number][1] = new JLabel(s);
		labels[number][1].setHorizontalAlignment(SwingConstants.CENTER);
		labels[number][2] = new JLabel(Utilities.gToString(d));
		labels[number][2].setSize(labels[number][2].getPreferredSize());
		labels[number][2].setHorizontalAlignment(SwingConstants.CENTER);
		labels[number][3] = new JLabel(String.valueOf(r));
		labels[number][3].setSize(labels[number][3].getPreferredSize());
		labels[number][3].setHorizontalAlignment(SwingConstants.CENTER);
		labels[number][4] = new JLabel(notes);
		labels[number][4].setHorizontalAlignment(SwingConstants.CENTER);
		
		projects[number].add(labels[number][0]);
		projects[number].add(labels[number][1]);
		projects[number].add(labels[number][2]);
		projects[number].add(labels[number][3]);
		projects[number].add(labels[number][4]);
		
		int temp = number;
		number++;
		//System.out.println(temp);
		special.add(projects[temp]);
	}
	
	public void remove(int i){
		projects[i] = null;
		special.remove(projects[i]);
		//special.invalidate();
	}

	public void edit(int i){
		newProject = new CreateGUI(this, 
				labels[i][0].getText(), 
				labels[i][1].getText(),
				Utilities.strToGregCalendar(labels[i][2].getText()), 
				Integer.parseInt(labels[i][3].getText()),
				labels[i][4].getText());
		//model.remove(model.get(index));

		if (newProject.isOkPressed()) {
			if(newProject.getName() != model.get(i).getName()){
				labels[i][0].setText(newProject.getName());
				model.upDate(model.get(i).getName(), 
						newProject.getName());
			}
			if(newProject.getSub() != model.get(i).getSubName()){
				labels[i][1].setText(newProject.getSub());
				model.get(i).setSubName(newProject.getName());
			}
			if(newProject.getDueDate() != model.get(i).getDueDate()){
				labels[i][2].setText(Utilities.gToString(newProject.getDueDate()));
				model.get(i).setDueDate(newProject.getDueDate());
			}
			if(newProject.getReminder() != model.get(i).getReminder()){
				labels[i][3].setText(String.valueOf(newProject.getReminder()));
				model.get(i).setReminder(newProject.getReminder());
			}
			if(newProject.getNotes() != model.get(i).getNotes()){
				labels[i][4].setText(newProject.getNotes());
				model.get(i).setNotes(newProject.getNotes());
			}

		}
	}
	
}
