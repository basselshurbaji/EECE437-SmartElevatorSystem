package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import floor.Floor;
import elevator.Elevator;
import GUI.Request;
import userInteraction.UserPriority;
import GUI.userRequest;
import floor.FloorPriority;


public class GUI extends JFrame {
	
	private ArrayList<Floor> floorArray;
	private ArrayList<Elevator> elevatorArray;
	private ArrayList<Request> requestArray;
	private ArrayList<userRequest> userRequestArray;
	
	private JLabel label;
	private JScrollPane sp;

	
	private JComboBox floorPriorityComboBox;
	private JComboBox UserPriorityComboBox;
	
	private JButton addFloorButton;
	private JButton addElevatorButton;
	private JButton addUserRequest;
	private JButton addEmergencyRequest;
	private JButton addStopEmergencyRequest;
	private JButton generateConfigurationFileButton;
	
	private JButton incrementButton01;
	private JButton incrementButton02;
	private JButton incrementButton03;
	private JButton incrementButton04;
	private JButton incrementButton05;
	private JButton incrementButton06;
	private JButton incrementButton07;
	
	private JButton decrementButton01;
	private JButton decrementButton02;
	private JButton decrementButton03;
	private JButton decrementButton04;
	private JButton decrementButton05;
	private JButton decrementButton06;
	private JButton decrementButton07;
	
	private JTextField valueField01;
	private JTextField valueField02;
	private JTextField valueField03;
	private JTextField valueField04;
	private JTextField valueField05;
	private JTextField valueField06;
	private JTextField valueField07;
	
	private JList floorList;
	private JList elevatorList;
	private JList requestList;
	
	private JSeparator sep;

	private JPanel contentPane;
	
	private int counter01 = 1;
	private int counter02 = 100;
	private int counter03 = 1;
	private int counter04 = 1;
	private int counter05 = 0;
	private int counter06 = 0;
	private int counter07 = 0;
	
	DefaultListModel<String> floorModel;
	DefaultListModel<String> elevatorModel;
	DefaultListModel<String> requestModel;
	
	private int floorCounter = 0;
	private int elevatorCounter = 0;
	private int requestCounter = 0;
	private int userRequestCounter = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 40, 620, 870);
		setResizable(true);
		setTitle("Elevator Management System Configuration Panel");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setContentPane(contentPane);
		handler actionHandler = new handler();
		floorArray = new ArrayList<Floor>();
		elevatorArray= new ArrayList<Elevator>();
		requestArray= new ArrayList<Request>();
		userRequestArray= new ArrayList<userRequest>();
		floorModel = new DefaultListModel<>();
		elevatorModel = new DefaultListModel<>();
		requestModel = new DefaultListModel<>();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		

		sep = new JSparator(JSeparator.HORIZONTAL);
		sep.setSize(new Dimension(1,5));

		label = new JLabel("Building Initialization", SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(15,0,15,0);
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 0;
		contentPane.add(label, c);
		
		label = new JLabel("Floor Priority");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		contentPane.add(label, c);
		
		String[] floorPriority = { "Low","Medium","High" };
		
		floorPriorityComboBox = new JComboBox(floorPriority);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 3;
		c.gridx = 2;
		c.gridy = 1;
		contentPane.add(floorPriorityComboBox, c);
		
		addFloorButton = new JButton("Add Floor");
		addFloorButton.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 1;
		c.gridx = 5;
		c.gridy = 1;
		contentPane.add(addFloorButton, c);
		
		
		floorList = new JList(floorModel);
		sp = new JScrollPane(floorList);
		c.ipady = 80;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,15,0);
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(sp, c);
		
		sep = new JSeparator(JSeparator.HORIZONTAL);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,0,5,0);
		c.ipady = 0;
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 3;
		contentPane.add(sep, c);
		
		label = new JLabel("Elevator Initialization", SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,15,0);
		c.ipady = 0;
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 4;
		contentPane.add(label, c);
		
		label = new JLabel("Starting Floor");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 5;
		contentPane.add(label, c);
		
		valueField01 = new JTextField("1");
		valueField01.setEditable(false);
		valueField01.setColumns(4);
		valueField01.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 2;
		c.gridy = 5;
		contentPane.add(valueField01, c);
		
		decrementButton01 = new JButton("-");
		decrementButton01.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 3;
		c.gridy = 5;
		contentPane.add(decrementButton01, c);
		
		incrementButton01 = new JButton("+");
		incrementButton01.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 4;
		c.gridy = 5;
		contentPane.add(incrementButton01, c);
		
		
		label = new JLabel("Maximum Capacity (kg)");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 6;
		contentPane.add(label, c);
		
		valueField02 = new JTextField("100");
		valueField02.setEditable(false);
		valueField02.setColumns(4);
		valueField02.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 2;
		c.gridy = 6;
		contentPane.add(valueField02, c);
		
		decrementButton02 = new JButton("-");
		decrementButton02.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 3;
		c.gridy = 6;
		contentPane.add(decrementButton02, c);
		
		incrementButton02 = new JButton("+");
		incrementButton02.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 4;
		c.gridy = 6;
		contentPane.add(incrementButton02, c);
		
		addElevatorButton = new JButton("Add Elevator");
		addElevatorButton.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 5;
		c.gridy = 6;
		contentPane.add(addElevatorButton, c);
		
		elevatorList = new JList(elevatorModel);
		sp = new JScrollPane(elevatorList);
		c.ipady = 80;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,15,0);
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 7;
		contentPane.add(sp, c);
		
		sep = new JSeparator(JSeparator.HORIZONTAL);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,0,5,0);
		c.ipady = 0;
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 8;
		contentPane.add(sep, c);
		
		label = new JLabel("Sequence Initialization", SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,15,0);
		c.ipady = 0;
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 9;
		contentPane.add(label, c);
		

		
		label = new JLabel("User Request");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 11;
		contentPane.add(label, c);
		
		label = new JLabel("Starting Floor");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 10;
		contentPane.add(label, c);
		
		valueField03 = new JTextField("1");
		valueField03.setEditable(false);
		valueField03.setColumns(4);
		valueField03.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 2;
		c.gridy = 10;
		contentPane.add(valueField03, c);
		
		decrementButton03 = new JButton("-");
		decrementButton03.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 3;
		c.gridy = 10;
		contentPane.add(decrementButton03, c);
		
		incrementButton03 = new JButton("+");
		incrementButton03.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 4;
		c.gridy = 10;
		contentPane.add(incrementButton03, c);
		
		label = new JLabel("Destination Floor");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 11;
		contentPane.add(label, c);
		
		valueField04 = new JTextField("1");
		valueField04.setEditable(false);
		valueField04.setColumns(4);
		valueField04.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 2;
		c.gridy = 11;
		contentPane.add(valueField04, c);
		
		decrementButton04 = new JButton("-");
		decrementButton04.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 3;
		c.gridy = 11;
		contentPane.add(decrementButton04, c);
		
		incrementButton04 = new JButton("+");
		incrementButton04.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 4;
		c.gridy = 11;
		contentPane.add(incrementButton04, c);
		
		label = new JLabel("User Priority");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 12;
		contentPane.add(label, c);
		
		String[] UserPriority = { "Medium","High" };
		
		UserPriorityComboBox = new JComboBox(UserPriority);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 3;
		c.gridx = 2;
		c.gridy = 12;
		contentPane.add(UserPriorityComboBox, c);
		
		label = new JLabel("Request Time (sec)");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 13;
		contentPane.add(label, c);
		
		valueField05 = new JTextField("0");
		valueField05.setEditable(false);
		valueField05.setColumns(4);
		valueField05.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		c.gridx = 2;
		c.gridy = 13;
		contentPane.add(valueField05, c);
		
		decrementButton05 = new JButton("-");
		decrementButton05.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		c.gridx = 3;
		c.gridy = 13;
		contentPane.add(decrementButton05, c);
		
		incrementButton05 = new JButton("+");
		incrementButton05.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		c.gridx = 4;
		c.gridy = 13;
		contentPane.add(incrementButton05, c);
		
		
		addUserRequest = new JButton("Add User Request");
		addUserRequest.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		c.gridx = 5;
		c.gridy = 13;
		contentPane.add(addUserRequest, c);
		
		
		label = new JLabel("Emergency Request");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 14;
		contentPane.add(label, c);
		
		label = new JLabel("Request Time (sec)");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 14;
		contentPane.add(label, c);
		
		valueField06 = new JTextField("0");
		valueField06.setEditable(false);
		valueField06.setColumns(4);
		valueField06.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 2;
		c.gridy = 14;
		contentPane.add(valueField06, c);
		
		decrementButton06 = new JButton("-");
		decrementButton06.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 3;
		c.gridy = 14;
		contentPane.add(decrementButton06, c);
		
		incrementButton06 = new JButton("+");
		incrementButton06.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 4;
		c.gridy = 14;
		contentPane.add(incrementButton06, c);
		
		addEmergencyRequest = new JButton("Add Emergency Request");
		addEmergencyRequest.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 5;
		c.gridy = 14;
		contentPane.add(addEmergencyRequest, c);
		
		label = new JLabel("Stop Emergency Request");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 15;
		contentPane.add(label, c);

		label = new JLabel("Request Time (sec)");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 15;
		contentPane.add(label, c);
		
		valueField07 = new JTextField("0");
		valueField07.setEditable(false);
		valueField07.setColumns(4);
		valueField07.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 2;
		c.gridy = 15;
		contentPane.add(valueField07, c);
		
		decrementButton07 = new JButton("-");
		decrementButton07.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 3;
		c.gridy = 15;
		contentPane.add(decrementButton07, c);
		
		incrementButton07 = new JButton("+");
		incrementButton07.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 4;
		c.gridy = 15;
		contentPane.add(incrementButton07, c);
		
		addStopEmergencyRequest = new JButton("Add Stop Emergency Request");
		addStopEmergencyRequest.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 5;
		c.gridy = 15;
		contentPane.add(addStopEmergencyRequest, c);
		
		
		requestList = new JList(requestModel);
		sp = new JScrollPane(requestList);
		c.ipady = 80;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,15,0);
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 16;
		contentPane.add(sp, c);
		
		sep = new JSeparator(JSeparator.HORIZONTAL);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.ipady = 0;
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 17;
		contentPane.add(sep, c);
		
		generateConfigurationFileButton = new JButton("Generate Configuration File");
		generateConfigurationFileButton.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,0,0);
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 18;
		contentPane.add(generateConfigurationFileButton, c);
	}
	
	
	private class handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource()== generateConfigurationFileButton )
			{
				// Error-Checking
				int numberOfFloors = floorArray.size();
				int numberOfElevators = elevatorArray.size();
				int numberOfRequests = userRequestArray.size();
				
				
				boolean errorCondition = false;
				
				if(numberOfFloors == 0 || numberOfFloors == 1 )
				{
					errorCondition = true;
					JOptionPane.showMessageDialog(new JFrame(), "There should be at least Two Floors!");
				}
				
				if(numberOfElevators == 0 )
				{
					errorCondition = true;
					JOptionPane.showMessageDialog(new JFrame(), "There should be at least One Elevator!");
				}
				
				if(numberOfRequests == 0 )
				{
					errorCondition = true;
					JOptionPane.showMessageDialog(new JFrame(), "There should be at least One User Request!");
				}
				
				for (int i=0; i<numberOfElevators;i++)
				{
					if(elevatorArray.get(i).getCurrentFloorId()>numberOfFloors)
					{
						errorCondition = true;
						JOptionPane.showMessageDialog(new JFrame(), "Some Elevators are initialized at non-existent Floors!");
					}
				}
				
				for (int i=0; i<numberOfRequests;i++)
				{
					if(userRequestArray.get(i).getDestinationFloor()>numberOfFloors || userRequestArray.get(i).getPickUpFloor()>numberOfFloors)
					{
						errorCondition = true;
						JOptionPane.showMessageDialog(new JFrame(), "Some User requests are directed at non-existent Floors!");
					}
					
					if(userRequestArray.get(i).getDestinationFloor()== userRequestArray.get(i).getPickUpFloor())
					{
						errorCondition = true;
						JOptionPane.showMessageDialog(new JFrame(), "Some User requests are have the same Pick-up and Destination Floor!");
					}
				}
				
				//Creating JSON configuration file
				
				if(!errorCondition)
				{
					DataObject DO = new DataObject(floorArray, elevatorArray, requestArray, userRequestArray);
					if(GsonHandler.GenerateJson(DO)) {
						JOptionPane.showMessageDialog(new JFrame(), "Json File Generated Successfully!");
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "Generation of Json File Failed!");
					}
				}
			}
			
			if(event.getSource()== addFloorButton)
			{
				if(floorCounter<10)
				{
					int comboBoxIndex = floorPriorityComboBox.getSelectedIndex();
					floorCounter= floorCounter+1;
					String statement = "Floor #"+String.valueOf(floorCounter)+" - Priority ";
					
					if (comboBoxIndex == 0)
					{
						floorArray.add(new Floor(floorCounter - 1, FloorPriority.LOW));
						statement = statement + "LOW";
					}
					else if (comboBoxIndex == 1)
					{
						floorArray.add(new Floor(floorCounter- 1, FloorPriority.MEDIUM));
						statement = statement + "MEDIUM";
					}
					
					else if (comboBoxIndex == 2)
					{
						floorArray.add(new Floor(floorCounter- 1, FloorPriority.HIGH));
						statement = statement + "HIGH";
					}
					
					floorModel.addElement(statement);
				}
				
				
			}
			
			if(event.getSource()== addElevatorButton )
			{
				if(elevatorCounter<10)
				{
					elevatorCounter = elevatorCounter + 1;
					String statement = "Elevator #"+String.valueOf(elevatorCounter)+" - Starting Floor "+String.valueOf(counter01)+ " - Max. Load "+String.valueOf(counter02);
					elevatorArray.add(new Elevator(elevatorCounter- 1,counter02, counter01- 1));
					elevatorModel.addElement(statement);
				}
			}
			
			if(event.getSource()== addUserRequest )
			{
				if(userRequestCounter<10)
				{
					int comboBoxIndex = UserPriorityComboBox.getSelectedIndex();
					userRequestCounter = userRequestCounter + 1;
					String statement = "User #"+String.valueOf(userRequestCounter)+" - From Floor "+String.valueOf(counter03)+ " to Floor "+String.valueOf(counter04)+ " at Time " + String.valueOf(counter05) + ", Priority ";
					if (comboBoxIndex == 0)
					{
						userRequestArray.add(new userRequest(userRequestCounter- 1, counter03- 1,counter04- 1, UserPriority.NORMAL, 'u', counter05));
						statement = statement + "MEDIUM";
					}
					
					else if (comboBoxIndex == 1)
					{
						userRequestArray.add(new userRequest(userRequestCounter- 1, counter03- 1,counter04- 1, UserPriority.HIGH, 'u', counter05));
						statement = statement + "HIGH";
					}
					
					requestModel.addElement(statement);
					
				}
			}
			
			if(event.getSource()== addEmergencyRequest )
			{
				if(requestCounter<10)
				{
					requestCounter = requestCounter + 1;
					String statement = "Emergency Request #"+String.valueOf(requestCounter)+" - At Time " + String.valueOf(counter06);
					requestArray.add(new Request('e', counter06));
					requestModel.addElement(statement);
				}
			}
			
			if(event.getSource()== addStopEmergencyRequest )
			{
				if(requestCounter<10)
				{
					requestCounter = requestCounter + 1;
					String statement = "Stop Emergency Request #"+String.valueOf(requestCounter)+" - At Time " + String.valueOf(counter07);
					requestArray.add(new Request('s', counter07));
					requestModel.addElement(statement);
				}
			}
			
			
			
			if(event.getSource()== incrementButton01 )
			{
				if(counter01<10)
				{
					counter01 = counter01 + 1;
					valueField01.setText(String.valueOf(counter01));
				}
				
			}
			
			if(event.getSource()== incrementButton02 )
			{
				if(counter02<1000)
				{
				counter02 = counter02 + 100;
				valueField02.setText(String.valueOf(counter02));
				}
			}
			
			if(event.getSource()== incrementButton03 )
			{
				if(counter03<10)
				{
				counter03 = counter03 + 1;
				valueField03.setText(String.valueOf(counter03));
				}
			}
			
			if(event.getSource()== incrementButton04)
			{
				if(counter04<10)
				{
				counter04 = counter04 + 1;
				valueField04.setText(String.valueOf(counter04));
				}
			}
			
			if(event.getSource()== incrementButton05 )
			{
				if(counter05<120)
				{
				counter05 = counter05 + 1;
				valueField05.setText(String.valueOf(counter05));
				}
			}
			
			if(event.getSource()== incrementButton06 )
			{
				if(counter06<120)
				{
				counter06 = counter06 + 1;
				valueField06.setText(String.valueOf(counter06));
				}
			}
			
			if(event.getSource()== incrementButton07 )
			{
				if(counter07<120)
				{
				counter07 = counter07 + 1;
				valueField07.setText(String.valueOf(counter07));
				}
			}
			
			if(event.getSource()== decrementButton01)
			{
				if(counter01>1)
				{
					counter01 = counter01 - 1;
					valueField01.setText(String.valueOf(counter01));
				}
			}
			
			if(event.getSource()== decrementButton02)
			{
				if(counter02>100)
				{
					counter02 = counter02 - 100;
					valueField02.setText(String.valueOf(counter02));
				}
			}
			
			if(event.getSource()==  decrementButton03)
			{
				if(counter03>1)
				{
					counter03 = counter03 - 1;
					valueField03.setText(String.valueOf(counter03));
				}
			}
			
			if(event.getSource()== decrementButton04)
			{
				if(counter04>1)
				{
					counter04 = counter04 - 1;
					valueField04.setText(String.valueOf(counter04));
				}
			}
			
			if(event.getSource()== decrementButton05)
			{
				if(counter05>0)
				{
					counter05 = counter05 - 1;
					valueField05.setText(String.valueOf(counter05));
				}
			}
			
			if(event.getSource()== decrementButton06)
			{
				if(counter06>0)
				{
					counter06 = counter06 - 1;
					valueField06.setText(String.valueOf(counter06));
				}
			}
			
			if(event.getSource()== decrementButton07)
			{
				if(counter07>0)
				{
					counter07 = counter07 - 1;
					valueField07.setText(String.valueOf(counter07));
				}
			}
			

		}
	}
	

}

