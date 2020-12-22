package demo;

import java.util.ArrayList;


import GUI.DataObject;
import GUI.GsonHandler;
import GUI.userRequest;
import elevatorManager.ElevatorManager;
import floor.Floor;
import floor.FloorPriority;
import userInteraction.UserInteraction;
import elevator.Elevator;
import GUI.Request;
public class IntegratedDemo {
	
/**
 * STATUS: In-use in our system
 * This class is used for simulation of our Elevator system based on a configuration file
 */

	public static void main(String[] args) {
		
		//Fetching UserInteraction and ElevatorManager Singletons
		UserInteraction UI=UserInteraction.getInstance();
		ElevatorManager EM = ElevatorManager.getInstance();
		
		//Fetching configuration parameters from JSON file, and storing them in a DataObject instance
		DataObject DO = GsonHandler.JsonParser("data.json");
		
		//Initializing Elevators using DataObject instance
		ArrayList<Elevator> elevators = DO.getElevatorArrayList();
		
		//Initializing Floors using DataObject instance
		ArrayList<Floor> floors = DO.getFloorArrayList();
		
		//Initializing Reset/Emergency Requests using DataObject instance
		ArrayList<Request> requests = DO.getRequestArrayList(); // Emergency Request + Reset Request
		
		//Initializing User Requests and Reset/Emergency Requests using DataObject instance
		ArrayList<userRequest> userRequestArrayList = DO.getUserRequestArrayList(); // User Request (i.e. User 1 wants to go from Floor 1 to Floor 3)
		//Initializing set of Users based on fetched requests
		ArrayList<User> users = usersInitialization(userRequestArrayList);

		
		//Initializing ElevatorManager by passing set of Elevators and Floors
		EM.setElevators(elevators);
		EM.setFloors(floors);
	
		//Setting all users as elevator observers
		observerInitialization(elevators,users);
		
		//Demo Loop
		//Runs for 120 seconds
		//Executes User requests and Simulates Elevator Traffic
		for(int time=0; time<120; time++) {
			
			printTime(time);
			
			checkUserRequests(userRequestArrayList, users, time);
			
			checkSystemRequests(requests, UI, time);
			
			delay(1000);
			
		}
		
	}
	
	
	/**
	 * Adding users as elevator observers
	 * @param elevators ArrayList of elevators
	 * @param users ArrayList of users
	 */
	public static void observerInitialization(ArrayList<Elevator> elevators, ArrayList<User> users )
	{
		for(int i=0; i<elevators.size(); i++) {
			for(int j=0; j<users.size(); j++) {
				elevators.get(i).addObserver(users.get(j));
			}
		}
	}
	
	/**
	 * Initializing set of Users based on fetched requests
	 * @param userRequestArrayList ArrayList of user requests
	 */
	public static ArrayList<User> usersInitialization(ArrayList<userRequest> userRequestArrayList)
	{
		ArrayList<User> users = new ArrayList<User>();
		
		for(int i=0; i<userRequestArrayList.size(); i++) {
			users.add(new User(userRequestArrayList.get(i).getPickUpFloor(), userRequestArrayList.get(i).getDestinationFloor(), userRequestArrayList.get(i).getPriority()));
		}
		
		return users;
	}
	
	/**
	 * Formats & Prints the passed parameter 'time'
	 * @param time Time value
	 */
	private static void printTime(int time)
	{
		System.out.println("\n==============================");
		System.out.println("Time t="+String.valueOf(time));
		System.out.println("==============================\n");
	}
	
	/**
	 * Check if any UserRequest occurs at time t='time', and execute them if they do
	 * @param userRequestArrayList ArrayList of user requests
	 * @param users ArrayList of users
	 * @param time Time value
	 */
	private static void checkUserRequests(ArrayList<userRequest> userRequestArrayList, ArrayList<User> users, int time)
	{
		for(int iterator=0; iterator<userRequestArrayList.size(); iterator++) {
			
			if(userRequestArrayList.get(iterator).getTime()==time)
			{
				users.get(iterator).requestPickUp();
				delay(50);
			}
		}
	}
	
	/**
	 * Check if any SystemRequest occurs at time t='time', and execute them if they do
	 * @param requests ArrayList of system requests
	 * @param UI User Interaction singleton
	 * @param time Time value
	 */
	private static void checkSystemRequests(ArrayList<Request> requests, UserInteraction UI, int time)
	{
		for(int iterator=0; iterator<requests.size(); iterator++) {
			
			if(requests.get(iterator).getTime()==time)
			{
				if(requests.get(iterator).getType()=='e')
				{
					UI.fireEmergencyRequest();
					System.out.println("EMERGENCY STOP!");
				}
				else if(requests.get(iterator).getType()=='s')
				{
					UI.resetMode();
					System.out.println("RESET MODE!");
				}
			}
		}
	}
	
	
	/**
	 * Delay Function
	 * @param delay Time (in ms) to delay for
	 */
	private static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
