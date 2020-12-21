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
public class Demo {

	public static void main(String[] args) {
		UserInteraction UI=UserInteraction.getInstance();
		//Getting ElevatorManager Singleton
		ElevatorManager EM = ElevatorManager.getInstance();
		
		//Creating a DataObject
		DataObject DO = GsonHandler.JsonParser("data.json");
		
		//Initializing Elevators
		ArrayList<Elevator> elevators = DO.getElevatorArrayList();
		
//		//Initializing  Elevators
//		ArrayList<Elevator> elevators = new ArrayList<Elevator>();
//		Elevator E0 = new Elevator(0,100,0);
//		Elevator E1 = new Elevator(1,100,0);
//		elevators.add(E0);	
//		elevators.add(E1);
		
		//Initializing Floors
		ArrayList<Floor> floors = DO.getFloorArrayList();
		
//		//Initializing Floors
//		ArrayList<Floor> floors = new ArrayList<Floor>();
//		Floor F0 = new Floor(0,FloorPriority.MEDIUM);
//		Floor F1 = new Floor(1,FloorPriority.MEDIUM);
//		Floor F2 = new Floor(2,FloorPriority.MEDIUM);
//		Floor F3 = new Floor(3,FloorPriority.MEDIUM);
//		floors.add(F0);
//		floors.add(F1);
//		floors.add(F2);
//		floors.add(F3);
		
		//Passing the elevators and floors to the elevator manager
		EM.setElevators(elevators);
		EM.setFloors(floors);
		
		//Fetching userReqeustArrayList
		ArrayList<userRequest> userRequestArrayList = DO.getUserRequestArrayList();
		
		//Creating an array list of Users
		ArrayList<User> users = new ArrayList<User>();
		
		for(int i=0; i<userRequestArrayList.size(); i++) {
			users.add(new User(userRequestArrayList.get(i).getPickUpFloor(), userRequestArrayList.get(i).getDestinationFloor()));
		}
		
//		User user1 = new User(0,2);
//		User user2 = new User(0,2);
//		User user3 = new User(3,1);
		
		//Adding users as elevator observers
		for(int i=0; i<elevators.size(); i++) {
			for(int j=0; j<users.size(); j++) {
				elevators.get(i).addObserver(users.get(j));
			}
		}
		
//		E0.addObserver(user1);
//		E0.addObserver(user2);
//		E0.addObserver(user3);
//		E1.addObserver(user1);
//		E1.addObserver(user2);
//		E1.addObserver(user3);
		
		// Create a loop for 120 sec
		// Every sec : check userResquest, check requests
		ArrayList<Request> requests = DO.getRequestArrayList();
		for(int i=0; i<120; i++) {
			for(int j=0; j<userRequestArrayList.size(); j++) {
				if(userRequestArrayList.get(j).getTime()==i)
				{
					users.get(j).requestPickUp();
					delay(50);
				}
			}
			
			for(int j=0; j<requests.size(); j++) {
				if(requests.get(j).getTime()==i)
				{
					if(requests.get(j).getType()=='e')
					{
						UI.fireEmergencyRequest();
						System.out.println("EMERGENCY STOP!");
					}
					else if(requests.get(j).getType()=='s')
					{
						UI.resetMode();
						System.out.println("EMERGENCY SOlVED!");
					}
				}
			}
			delay(1000);
		}
		
		// We are going to have to iterate over the userRequest, check the time to see if there are any requests which must be handled
		// To handle request -> Request #1 is associated to User 1, we fetch that user from the user array we created
		
		// We also do a check on the request array (Where is the request array?)
		
//		
//		user1.requestPickUp();
//		delay(50);
//		user2.requestPickUp();
//		delay(50);
//		user3.requestPickUp();
//		delay(50);
		
	}
	
	private static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
