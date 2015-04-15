package CIS350;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
  private JButton[] projects = new JButton[20];
  private JLabel[][] labels = new JLabel[20][5];
  private JButton[] completed = new JButton[50];
  private JLabel[][] completedLabels = new JLabel[50][5];
  private String[][] temp = new String[1][5];
  private int number = 0;
  private int numberComplete = 0;
  private JLabel one, two, three, four, five;
  
  private JLabel categories;
  
  private GregorianCalendar dueDate = new GregorianCalendar();
  
  private String Spacing;


  /**
   * Instantiates the GUI.
   */
  public ProjectGUI() {
    load(new File("src/package1/file.ser"));
    setupFrame();
    model = new ProjectModel();
    //special.
    special.validate();
    special.repaint();
    
  }

  /**
   * Sets up the panel for the parent GUI.
   */
  public final void setupFrame() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.setTitle("Project Management");
    
    
    
    for(int i = 0; i < projects.length; i++){
      projects[i] = new JButton();
      projects[i].setActionCommand(String.valueOf(i));
      projects[i].addActionListener(this);
      projects[i].setLayout(new GridLayout(1,5));
    }

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
    
    
    categories = new JLabel();
    categories.setText("                Projects                     "
        + "Sub Projects                    "
        + "Due Dates                     "
        + "Reminders                       "
        + "Notes");
    categories.setFont(new Font(name, style, size));
    
    newItem = new JButton("+");
    newItem.setFont(new Font(name, style, size));
    newItem.setHorizontalAlignment(SwingConstants.CENTER);
    newItem.addActionListener(this);
    
    //for (int control = 0; control < projects.length; control++){
    //  special.add(projects[control]);
    //}
    special.add(categories);
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
      save(new File("src/package1/file.ser"));
      System.exit(0);
    }
    //    if (e.getSource() == aboutItem) {
    //      JOptionPane.showMessageDialog(null, "Hello!");
    //    }
    if (e.getSource() == newItem) {
      newProject = new CreateGUI(this);

      if (newProject.isOkPressed()) {
        //Project p = new Project(newProject.getName(), null, newProject.getDueDate(), 
        //    newProject.getReminder(), newProject.getNotes(),  false);
        //        Project p = newProject.whatProject();
        //model.add(p);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        dueDate = newProject.getDueDate();
        formatter.setCalendar(dueDate);
        String dd = formatter.format(dueDate.getTime());;        
        addingProject(newProject.getName(), null, dd, 
            newProject.getReminder(), newProject.getNotes());
        //model.sortByName();
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

    if (e.getSource() == week1Button) {
      
    }
    if (e.getSource() == week2Button) {
      model.sortByWeek(2);
    }
    if (e.getSource() == week4Button) {
      model.sortByWeek(4);
    }
    if (e.getSource() == allButton) {
      sortByName();
    }
    if (e.getSource() == dateButton) {
      sortByDate();
    }

  }
  public void addingProject(String n, String s, String d, 
      String r, String notes){
    if(number <= 20) {
//      projects[number] = new JButton();
//      projects[number].setActionCommand(String.valueOf(number));
//      projects[number].addActionListener(this);
//      projects[number].setLayout(new GridLayout(1,5));
      //now.setSize(5,950);
      //now.setText("");
      labels[number][0] = new JLabel(n);
      labels[number][0].setHorizontalAlignment(SwingConstants.CENTER);
      labels[number][1] = new JLabel(s);
      labels[number][1].setHorizontalAlignment(SwingConstants.CENTER);
      labels[number][2] = new JLabel(d);
      labels[number][2].setSize(labels[number][2].getPreferredSize());
      labels[number][2].setHorizontalAlignment(SwingConstants.CENTER);
      labels[number][3] = new JLabel(r);
      labels[number][3].setSize(labels[number][3].getPreferredSize());
      labels[number][3].setHorizontalAlignment(SwingConstants.CENTER);
      labels[number][4] = new JLabel(notes);
      labels[number][4].setHorizontalAlignment(SwingConstants.CENTER);

      projects[number].add(labels[number][0]);
      projects[number].add(labels[number][1]);
      projects[number].add(labels[number][2]);
      projects[number].add(labels[number][3]);
      projects[number].add(labels[number][4]);
      projects[number].setBackground(setColor(d));
      scroll.setVisible(false);
      scroll.setVisible(true);
      

      int temp = number;
      number++;
      //System.out.println(temp);
      special.add(projects[temp]);
      
    }else{
      JOptionPane.showMessageDialog(null, "Get a LIFE dude!");
    }
  }
  
  public void remove(int i){
    special.remove(projects[i]);
    special.validate();
    special.repaint();
    
    //for (int x = 0; i < 3; i++){
      //projects[i].remove(labels[i][x]);
    //  labels[i][x].setText(null);
    //}
    //number--;
    //updateProjects(i);
  }

  public void edit(int i) {
    if (labels[i][1].getText() != null) {
      newProject = new CreateGUI(this, 
          labels[i][0].getText(),
          labels[i][1].getText(),
          labels[i][2].getText(), 
          labels[i][3].getText(),
          labels[i][4].getText());
      //model.remove(model.get(index));
    } else {
      newProject = new CreateGUI(this, 
          labels[i][0].getText(),
          labels[i][1].getText(),
          labels[i][2].getText(), 
          labels[i][3].getText(),
          labels[i][4].getText());
    }

    if (newProject.isOkPressed()) {
      //if(newProject.getName() != model.get(i).getName()){
        labels[i][0].setText(newProject.getName());
        //model.upDate(model.get(i).getName(), 
            //newProject.getName());
      //}
      //if(newProject.getSub() != model.get(i).getSubName()){
        labels[i][1].setText(newProject.getSub());
        //model.get(i).setSubName(newProject.getName());
      //}
      //if(newProject.getDueDate() != model.get(i).getDueDate()){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        dueDate = newProject.getDueDate();
        formatter.setCalendar(dueDate);
        String dd = formatter.format(dueDate.getTime());; 
        labels[i][2].setText(dd);
        //model.get(i).setDueDate(newProject.getDueDate());
      //}
      //if(newProject.getReminder() != model.get(i).getReminder()){
        labels[i][3].setText(String.valueOf(newProject.getReminder()));
        //model.get(i).setReminder(newProject.getReminder());
      //}
      //if(newProject.getNotes() != model.get(i).getNotes()){
        labels[i][4].setText(newProject.getNotes());
        //model.get(i).setNotes(newProject.getNotes());
      //}
    }
    if(newProject.isDeletePressed()){
      remove(i);
    }
    if(newProject.isCompletePressed()){
      projects[i].setBackground(Color.GRAY);
//      completed[numberComplete] = projects[i];
//      completedLabels[numberComplete][0] = labels[i][0];
//      completedLabels[numberComplete][1] = labels[i][1];
//      completedLabels[numberComplete][2] = labels[i][2];
//      numberComplete++;
//      remove(i);
      
    }
    if(newProject.isSubPressed()){
      newProject = new CreateGUI(this, 
          labels[i][0].getText(),
          labels[i][1].getText(),
          labels[i][2].getText(), 
          labels[i][3].getText(),
          labels[i][4].getText());

      SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
      dueDate = newProject.getDueDate();
      formatter.setCalendar(dueDate);
      String dd = formatter.format(dueDate.getTime());; 
      addingProject(newProject.getName(), newProject.getSub(), dd, 
          newProject.getReminder(), newProject.getNotes());



    }
  }
//  public void updateProjects(int n){
//    //JButton temp;
//    for(int i = n; i < number+1; i++){
//      while(i < 19){
//        //projects[i] = projects[i+1];
//        for (int j = 0; j < 3; j++){
//          if(labels[i+1][j].getText() != null){
//            labels[i][j].setText(labels[i+1][j].getText());
//          }
//        }
//      }
//    }
//    //System.out.println(projects[0]);
//    return;
//  }
  public void sortByName(){
    //System.out.println("1");
    for(int j = 0; j < number; j++){
      for(int i = j+1; i < number; i++){
        if((labels[i][0].getText().toLowerCase().compareTo(
            labels[j][0].getText().toLowerCase()) < 0) || 
            (labels[i][0].getText().toLowerCase().compareTo(
                labels[j][0].getText().toLowerCase()) == 0) && 
                labels[i][0].getText().toLowerCase() == null){
          //System.out.println("i: " + i + " and j: " + j);

          String[][] b = new String[1][5];
          b[0][0] = labels[j][0].getText();
          b[0][1] = labels[j][1].getText();
          b[0][2] = labels[j][2].getText();
          b[0][3] = labels[j][3].getText();
          b[0][4] = labels[j][4].getText();
          
          labels[j][0].setText(labels[i][0].getText());
          labels[j][1].setText(labels[i][1].getText());
          labels[j][2].setText(labels[i][2].getText());
          labels[j][3].setText(labels[i][3].getText());
          labels[j][4].setText(labels[i][4].getText());
          
          labels[i][0].setText(b[0][0]);
          labels[i][1].setText(b[0][1]);
          labels[i][2].setText(b[0][2]);
          labels[i][3].setText(b[0][3]);
          labels[i][4].setText(b[0][4]);
          //i--;
        }
        
      }
    }
  }
  public void sortByDate(){
    for(int j = 0; j < number; j++){
      for(int i = j+1; i < number; i++){

        if (Utilities.beforeAfter(Utilities.strToGregCalendar(labels[j][2].getText()), 
            Utilities.strToGregCalendar(labels[i][2].getText())) == false) {
          //System.out.println("i: " + i + " and j: " + j);

          String[][] b = new String[1][5];
          b[0][0] = labels[j][0].getText();
          b[0][1] = labels[j][1].getText();
          b[0][2] = labels[j][2].getText();
          b[0][3] = labels[j][3].getText();
          b[0][4] = labels[j][4].getText();
          
          labels[j][0].setText(labels[i][0].getText());
          labels[j][1].setText(labels[i][1].getText());
          labels[j][2].setText(labels[i][2].getText());
          labels[j][3].setText(labels[i][3].getText());
          labels[j][4].setText(labels[i][4].getText());
          
          labels[i][0].setText(b[0][0]);
          labels[i][1].setText(b[0][1]);
          labels[i][2].setText(b[0][2]);
          labels[i][3].setText(b[0][3]);
          labels[i][4].setText(b[0][4]);
          //i--;
        }
      }
    }
  }
  private void save(File file){
    try {
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(projects);
        oos.writeInt(0);
        oos.writeObject(labels);
        oos.writeInt(0);
        oos.writeObject(completed);
        oos.writeInt(0);
        oos.writeObject(completedLabels);
        oos.writeInt(0);
        oos.writeObject(temp);
        oos.writeInt(0);
        oos.close();
        bos.close();
        fos.close();
      } catch (final Exception e) {
        e.printStackTrace();
      }
    }
  
  private void load(File file){
    try {
      FileInputStream fis = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(fis);
      ObjectInputStream ois = new ObjectInputStream(bis);
      projects = (JButton[]) ois.readObject();
      ois.readInt();
      labels = (JLabel[][]) ois.readObject();
      ois.readInt();
      completed = (JButton[]) ois.readObject();
      ois.readInt();
      completedLabels = (JLabel[][]) ois.readObject();
      ois.readInt();
      temp = (String[][]) ois.readObject();
      ois.close();
      bis.close();
      fis.close();
      //fireTableRowsInserted(myArray.size() - 1, myArray.size() - 1);
    } catch (FileNotFoundException f) {
      save(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private Color setColor(String d){
    Color color;
    GregorianCalendar DueDate = new GregorianCalendar();
    GregorianCalendar fiveDaysAgo = new GregorianCalendar();
    GregorianCalendar twoDaysAgo = new GregorianCalendar();
    DueDate = Utilities.strToGregCalendar(d);
    fiveDaysAgo.set(Calendar.DAY_OF_MONTH, fiveDaysAgo.get(Calendar.DAY_OF_MONTH) + 5);
    twoDaysAgo.set(Calendar.DAY_OF_MONTH, twoDaysAgo.get(Calendar.DAY_OF_MONTH) + 2);
    if (DueDate.compareTo(fiveDaysAgo) > 0){
      color = Color.GREEN;
    }
    else if (DueDate.compareTo(fiveDaysAgo) <= 0 && DueDate.compareTo(twoDaysAgo) > 0){
      color = Color.YELLOW;
    }
    else{
      color = Color.RED;
    }
    return color;
    
  }
}
