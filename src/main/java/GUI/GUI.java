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

/**
 * This class defines the GUI used to generate the configuration file for the
 * Elevator System.
 */

public class GUI extends JFrame {
	
	/**
	 * ArrayList of Floors
	 */
	private ArrayList<Floor> floorArray;
	/**
	 * ArrayList of elevators
	 */
	private ArrayList<Elevator> elevatorArray;
	/**
	 * ArrayList of requests
	 */
	private ArrayList<Request> requestArray;
	/**
	 * ArrayList of user requests
	 */
	private ArrayList<userRequest> userRequestArray;
	
	/**
	 * JLabel used for text in the GUI
	 */
	private JLabel label;
	/**
	 * JScrollPane used to encapsulate JLists
	 */
	private JScrollPane sp;

	/**
	 * JComboBox for Floor priorities
	 */
	private JComboBox floorPriorityComboBox;
	/**
	 * JComboBox for user priorities
	 */
	private JComboBox UserPriorityComboBox;
	
	/**
	 * Button to add a Floor
	 */
	private JButton addFloorButton;
	/**
	 * Button to add an Elevator
	 */
	private JButton addElevatorButton;
	/**
	 * Button to add a User Request
	 */
	private JButton addUserRequest;
	/**
	 * Button to add an emergency request
	 */
	private JButton addEmergencyRequest;
	/**
	 * Button to add a stop emergency request
	 */
	private JButton addStopEmergencyRequest;
	/**
	 * Button to generate configuration file
	 */
	private JButton generateConfigurationFileButton;
	
	/**
	 * Button to increment Elevator Starting Floor
	 */
	private JButton incrementElevatorStartingFloor;
	/**
	 * Button to increment Elevator Max Capacity
	 */
	private JButton incrementElevatorMaxCapacity;
	/**
	 * Button to increment User Request Starting Floor
	 */
	private JButton incrementUserRequestStartingFloor;
	/**
	 * Button to increment User Request Destination Floor
	 */
	private JButton incrementUserRequestDestinationFloor;
	/**
	 * Button to increment User Request Time
	 */
	private JButton incrementUserRequestTime;
	/**
	 * Button to increment Emergency Request Time
	 */
	private JButton incrementEmergencyRequestTime;
	/**
	 * Button to increment Reset Request Time
	 */
	private JButton incrementResetRequestTime;
	
	/**
	 * Button to decrement Elevator Starting Floor
	 */
	private JButton decrementElevatorStartingFloor;
	/**
	 * Button to decrement Elevator Max Capacity
	 */
	private JButton decrementElevatorMaxCapacity;
	/**
	 * Button to decrement User Request Starting Floor
	 */
	private JButton decrementUserRequestStartingFloor;
	/**
	 * Button to decrement User Request Destination Floor
	 */
	private JButton decrementUserRequestDestinationFloor;
	/**
	 * Button to decrement User Request Time
	 */
	private JButton decrementUserRequestTime;
	/**
	 * Button to decrement Emergency Request Time
	 */
	private JButton decrementEmergencyRequestTime;
	/**
	 * Button to decrement Reset Request Time
	 */
	private JButton decrementResetRequestTime;
	
	/**
	 * Value field for elevator Starting Floor
	 */
	private JTextField elevatorStartingFloorTextField;
	/**
	 * Value field for elevator Max Capacity
	 */
	private JTextField elevatorMaxCapacityTextField;
	/**
	 * Value field for user Request Starting Floor
	 */
	private JTextField userRequestStartingFloorTextField;
	/**
	 * Value field for user Request Destination Floor
	 */
	private JTextField userRequestDestinationFloorTextField;
	/**
	 * Value field for user Request Time
	 */
	private JTextField userRequestTimeTextField;
	/**
	 * Value field for emergency Request Time
	 */
	private JTextField emergencyRequestTimeTextField;
	/**
	 * Value field for reset Request Time
	 */
	private JTextField resetRequestTimeTextField;
	
	/**
	 * JList of all floors
	 */
	private JList floorList;
	/**
	 * JList of all elevators
	 */
	private JList elevatorList;
	/**
	 * JList of all requests
	 */
	private JList requestList;
	
	/**
	 * JSeparator instance
	 */
	private JSeparator sep;

	/**
	 * Main contentPane
	 */
	private JPanel contentPane;
	
	/**
	 * Counter for elevator Starting Floor
	 */
	private int elevatorStartingFloorCounter = 1;
	/**
	 * Counter for elevator Max Capacity
	 */
	private int elevatorMaxCapacityCounter = 100;
	/**
	 * Counter for user Request Starting
	 */
	private int userRequestStartingFloorCounter = 1;
	/**
	 * Counter for user Request Destination
	 */
	private int userRequestDestinationFloorCounter = 1;
	/**
	 * Counter for user Request Time
	 */
	private int userRequestTimeCounter = 0;
	/**
	 * Counter for emergency Request Time
	 */
	private int emergencyRequestTimeCounter = 0;
	/**
	 * Counter for reset Request Time
	 */
	private int resetRequestTimeCounter = 0;
	
	/**
	 * ListModel for floors
	 */
	DefaultListModel<String> floorModel;
	/**
	 * ListModel for elevators
	 */
	DefaultListModel<String> elevatorModel;
	/**
	 * ListModel for requests
	 */
	DefaultListModel<String> requestModel;
	
	/**
	 * Counter for floors
	 */
	private int floorCounter = 0;
	/**
	 * Counter for elevators
	 */
	private int elevatorCounter = 0;
	/**
	 * Counter for requests
	 */
	private int requestCounter = 0;
	/**
	 * Counter for user requests
	 */
	private int userRequestCounter = 0;

	/**
	 * Launch the application.
	 * @param args Arguments passed to the main function
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
	 * Create the GUI frame and all associated components.
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
		

		sep = new JSeparator(JSeparator.HORIZONTAL);
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
		
		elevatorStartingFloorTextField = new JTextField("1");
		elevatorStartingFloorTextField.setEditable(false);
		elevatorStartingFloorTextField.setColumns(4);
		elevatorStartingFloorTextField.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 2;
		c.gridy = 5;
		contentPane.add(elevatorStartingFloorTextField, c);
		
		decrementElevatorStartingFloor = new JButton("-");
		decrementElevatorStartingFloor.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 3;
		c.gridy = 5;
		contentPane.add(decrementElevatorStartingFloor, c);
		
		incrementElevatorStartingFloor = new JButton("+");
		incrementElevatorStartingFloor.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 4;
		c.gridy = 5;
		contentPane.add(incrementElevatorStartingFloor, c);
		
		
		label = new JLabel("Maximum Capacity (kg)");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 6;
		contentPane.add(label, c);
		
		elevatorMaxCapacityTextField = new JTextField("100");
		elevatorMaxCapacityTextField.setEditable(false);
		elevatorMaxCapacityTextField.setColumns(4);
		elevatorMaxCapacityTextField.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 2;
		c.gridy = 6;
		contentPane.add(elevatorMaxCapacityTextField, c);
		
		decrementElevatorMaxCapacity = new JButton("-");
		decrementElevatorMaxCapacity.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 3;
		c.gridy = 6;
		contentPane.add(decrementElevatorMaxCapacity, c);
		
		incrementElevatorMaxCapacity = new JButton("+");
		incrementElevatorMaxCapacity.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 4;
		c.gridy = 6;
		contentPane.add(incrementElevatorMaxCapacity, c);
		
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
		
		userRequestStartingFloorTextField = new JTextField("1");
		userRequestStartingFloorTextField.setEditable(false);
		userRequestStartingFloorTextField.setColumns(4);
		userRequestStartingFloorTextField.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 2;
		c.gridy = 10;
		contentPane.add(userRequestStartingFloorTextField, c);
		
		decrementUserRequestStartingFloor = new JButton("-");
		decrementUserRequestStartingFloor.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 3;
		c.gridy = 10;
		contentPane.add(decrementUserRequestStartingFloor, c);
		
		incrementUserRequestStartingFloor = new JButton("+");
		incrementUserRequestStartingFloor.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 4;
		c.gridy = 10;
		contentPane.add(incrementUserRequestStartingFloor, c);
		
		label = new JLabel("Destination Floor");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 11;
		contentPane.add(label, c);
		
		userRequestDestinationFloorTextField = new JTextField("1");
		userRequestDestinationFloorTextField.setEditable(false);
		userRequestDestinationFloorTextField.setColumns(4);
		userRequestDestinationFloorTextField.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 2;
		c.gridy = 11;
		contentPane.add(userRequestDestinationFloorTextField, c);
		
		decrementUserRequestDestinationFloor = new JButton("-");
		decrementUserRequestDestinationFloor.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 3;
		c.gridy = 11;
		contentPane.add(decrementUserRequestDestinationFloor, c);
		
		incrementUserRequestDestinationFloor = new JButton("+");
		incrementUserRequestDestinationFloor.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,5,0);
		c.gridx = 4;
		c.gridy = 11;
		contentPane.add(incrementUserRequestDestinationFloor, c);
		
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
		
		userRequestTimeTextField = new JTextField("0");
		userRequestTimeTextField.setEditable(false);
		userRequestTimeTextField.setColumns(4);
		userRequestTimeTextField.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		c.gridx = 2;
		c.gridy = 13;
		contentPane.add(userRequestTimeTextField, c);
		
		decrementUserRequestTime = new JButton("-");
		decrementUserRequestTime.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		c.gridx = 3;
		c.gridy = 13;
		contentPane.add(decrementUserRequestTime, c);
		
		incrementUserRequestTime = new JButton("+");
		incrementUserRequestTime.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		c.gridx = 4;
		c.gridy = 13;
		contentPane.add(incrementUserRequestTime, c);
		
		
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
		
		emergencyRequestTimeTextField = new JTextField("0");
		emergencyRequestTimeTextField.setEditable(false);
		emergencyRequestTimeTextField.setColumns(4);
		emergencyRequestTimeTextField.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 2;
		c.gridy = 14;
		contentPane.add(emergencyRequestTimeTextField, c);
		
		decrementEmergencyRequestTime = new JButton("-");
		decrementEmergencyRequestTime.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 3;
		c.gridy = 14;
		contentPane.add(decrementEmergencyRequestTime, c);
		
		incrementEmergencyRequestTime = new JButton("+");
		incrementEmergencyRequestTime.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 4;
		c.gridy = 14;
		contentPane.add(incrementEmergencyRequestTime, c);
		
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
		
		resetRequestTimeTextField = new JTextField("0");
		resetRequestTimeTextField.setEditable(false);
		resetRequestTimeTextField.setColumns(4);
		resetRequestTimeTextField.setMinimumSize(new Dimension(50,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 2;
		c.gridy = 15;
		contentPane.add(resetRequestTimeTextField, c);
		
		decrementResetRequestTime = new JButton("-");
		decrementResetRequestTime.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 3;
		c.gridy = 15;
		contentPane.add(decrementResetRequestTime, c);
		
		incrementResetRequestTime = new JButton("+");
		incrementResetRequestTime.addActionListener(actionHandler);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,0);
		c.gridx = 4;
		c.gridy = 15;
		contentPane.add(incrementResetRequestTime, c);
		
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
	
	/**
	 * This class implements the actionListener for the GUI components
	 */
	
	private class handler implements ActionListener {

		/**
		 * Handles events triggered on the GUI by checking the source and performing appropriate logic operations
		 * @param event Event that has happened on the GUI, on a specific component
		 */
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
					String statement = "Elevator #"+String.valueOf(elevatorCounter)+" - Starting Floor "+String.valueOf(elevatorStartingFloorCounter)+ " - Max. Load "+String.valueOf(elevatorMaxCapacityCounter);
					elevatorArray.add(new Elevator(elevatorCounter- 1,elevatorMaxCapacityCounter, elevatorStartingFloorCounter- 1));
					elevatorModel.addElement(statement);
				}
			}
			
			if(event.getSource()== addUserRequest )
			{
				if(userRequestCounter<10)
				{
					int comboBoxIndex = UserPriorityComboBox.getSelectedIndex();
					userRequestCounter = userRequestCounter + 1;
					String statement = "User #"+String.valueOf(userRequestCounter)+" - From Floor "+String.valueOf(userRequestStartingFloorCounter)+ " to Floor "+String.valueOf(userRequestDestinationFloorCounter)+ " at Time " + String.valueOf(userRequestTimeCounter) + ", Priority ";
					if (comboBoxIndex == 0)
					{
						userRequestArray.add(new userRequest(userRequestCounter- 1, userRequestStartingFloorCounter- 1,userRequestDestinationFloorCounter- 1, UserPriority.NORMAL, 'u', userRequestTimeCounter));
						statement = statement + "MEDIUM";
					}
					
					else if (comboBoxIndex == 1)
					{
						userRequestArray.add(new userRequest(userRequestCounter- 1, userRequestStartingFloorCounter- 1,userRequestDestinationFloorCounter- 1, UserPriority.HIGH, 'u', userRequestTimeCounter));
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
					String statement = "Emergency Request #"+String.valueOf(requestCounter)+" - At Time " + String.valueOf(emergencyRequestTimeCounter);
					requestArray.add(new Request('e', emergencyRequestTimeCounter));
					requestModel.addElement(statement);
				}
			}
			
			if(event.getSource()== addStopEmergencyRequest )
			{
				if(requestCounter<10)
				{
					requestCounter = requestCounter + 1;
					String statement = "Stop Emergency Request #"+String.valueOf(requestCounter)+" - At Time " + String.valueOf(resetRequestTimeCounter);
					requestArray.add(new Request('s', resetRequestTimeCounter));
					requestModel.addElement(statement);
				}
			}
			
			
			
			if(event.getSource()== incrementElevatorStartingFloor )
			{
				if(elevatorStartingFloorCounter<10)
				{
					elevatorStartingFloorCounter = elevatorStartingFloorCounter + 1;
					elevatorStartingFloorTextField.setText(String.valueOf(elevatorStartingFloorCounter));
				}
				
			}
			
			if(event.getSource()== incrementElevatorMaxCapacity )
			{
				if(elevatorMaxCapacityCounter<1000)
				{
				elevatorMaxCapacityCounter = elevatorMaxCapacityCounter + 100;
				elevatorMaxCapacityTextField.setText(String.valueOf(elevatorMaxCapacityCounter));
				}
			}
			
			if(event.getSource()== incrementUserRequestStartingFloor )
			{
				if(userRequestStartingFloorCounter<10)
				{
				userRequestStartingFloorCounter = userRequestStartingFloorCounter + 1;
				userRequestStartingFloorTextField.setText(String.valueOf(userRequestStartingFloorCounter));
				}
			}
			
			if(event.getSource()== incrementUserRequestDestinationFloor)
			{
				if(userRequestDestinationFloorCounter<10)
				{
				userRequestDestinationFloorCounter = userRequestDestinationFloorCounter + 1;
				userRequestDestinationFloorTextField.setText(String.valueOf(userRequestDestinationFloorCounter));
				}
			}
			
			if(event.getSource()== incrementUserRequestTime )
			{
				if(userRequestTimeCounter<120)
				{
				userRequestTimeCounter = userRequestTimeCounter + 1;
				userRequestTimeTextField.setText(String.valueOf(userRequestTimeCounter));
				}
			}
			
			if(event.getSource()== incrementEmergencyRequestTime )
			{
				if(emergencyRequestTimeCounter<120)
				{
				emergencyRequestTimeCounter = emergencyRequestTimeCounter + 1;
				emergencyRequestTimeTextField.setText(String.valueOf(emergencyRequestTimeCounter));
				}
			}
			
			if(event.getSource()== incrementResetRequestTime )
			{
				if(resetRequestTimeCounter<120)
				{
				resetRequestTimeCounter = resetRequestTimeCounter + 1;
				resetRequestTimeTextField.setText(String.valueOf(resetRequestTimeCounter));
				}
			}
			
			if(event.getSource()== decrementElevatorStartingFloor)
			{
				if(elevatorStartingFloorCounter>1)
				{
					elevatorStartingFloorCounter = elevatorStartingFloorCounter - 1;
					elevatorStartingFloorTextField.setText(String.valueOf(elevatorStartingFloorCounter));
				}
			}
			
			if(event.getSource()== decrementElevatorMaxCapacity)
			{
				if(elevatorMaxCapacityCounter>100)
				{
					elevatorMaxCapacityCounter = elevatorMaxCapacityCounter - 100;
					elevatorMaxCapacityTextField.setText(String.valueOf(elevatorMaxCapacityCounter));
				}
			}
			
			if(event.getSource()==  decrementUserRequestStartingFloor)
			{
				if(userRequestStartingFloorCounter>1)
				{
					userRequestStartingFloorCounter = userRequestStartingFloorCounter - 1;
					userRequestStartingFloorTextField.setText(String.valueOf(userRequestStartingFloorCounter));
				}
			}
			
			if(event.getSource()== decrementUserRequestDestinationFloor)
			{
				if(userRequestDestinationFloorCounter>1)
				{
					userRequestDestinationFloorCounter = userRequestDestinationFloorCounter - 1;
					userRequestDestinationFloorTextField.setText(String.valueOf(userRequestDestinationFloorCounter));
				}
			}
			
			if(event.getSource()== decrementUserRequestTime)
			{
				if(userRequestTimeCounter>0)
				{
					userRequestTimeCounter = userRequestTimeCounter - 1;
					userRequestTimeTextField.setText(String.valueOf(userRequestTimeCounter));
				}
			}
			
			if(event.getSource()== decrementEmergencyRequestTime)
			{
				if(emergencyRequestTimeCounter>0)
				{
					emergencyRequestTimeCounter = emergencyRequestTimeCounter - 1;
					emergencyRequestTimeTextField.setText(String.valueOf(emergencyRequestTimeCounter));
				}
			}
			
			if(event.getSource()== decrementResetRequestTime)
			{
				if(resetRequestTimeCounter>0)
				{
					resetRequestTimeCounter = resetRequestTimeCounter - 1;
					resetRequestTimeTextField.setText(String.valueOf(resetRequestTimeCounter));
				}
			}
			

		}
	}
	

}

