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

	public static void main(String[] args) {
		
		//Getting our Singletons
		UserInteraction UI=UserInteraction.getInstance();
		ElevatorManager EM = ElevatorManager.getInstance();
		
		//Creating a DataObject
		DataObject DO = GsonHandler.JsonParser("data.json");
		
		//Initializing Elevators
		ArrayList<Elevator> elevators = DO.getElevatorArrayList();
		
		
		//Initializing Floors
		ArrayList<Floor> floors = DO.getFloorArrayList();
		

		
		//Passing the elevators and floors to the elevator manager
		EM.setElevators(elevators);
		EM.setFloors(floors);
		
		//Fetching userRequestArrayList
		ArrayList<userRequest> userRequestArrayList = DO.getUserRequestArrayList(); // User Request (i.e. User 1 wants to go from Floor 1 to Floor 3)
		ArrayList<Request> requests = DO.getRequestArrayList(); // Emergency Request + Reset Request
		
		//Creating an array list of Users
		ArrayList<User> users = new ArrayList<User>();
		
		
		for(int i=0; i<userRequestArrayList.size(); i++) {
			users.add(new User(userRequestArrayList.get(i).getPickUpFloor(), userRequestArrayList.get(i).getDestinationFloor()));
		}
		

		
		//Adding users as elevator observers
		for(int i=0; i<elevators.size(); i++) {
			for(int j=0; j<users.size(); j++) {
				elevators.get(i).addObserver(users.get(j));
			}
		}

		
		
		
		for(int i=0; i<120; i++) {
			
			System.out.println("==============================");
			System.out.println("Time t="+String.valueOf(i));
			System.out.println("==============================\n");
			
			// We first check if a user request happens at time t=i
			
			for(int j=0; j<userRequestArrayList.size(); j++) {
				
				if(userRequestArrayList.get(j).getTime()==i)
				{
					users.get(j).requestPickUp();
					delay(50);
				}
			}
			
			// We check if an emergency/reset request happens at time t=i
			
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
						System.out.println("RESET MODE!");
					}
				}
			}
			
			delay(1000);
			
			System.out.println("\n");
		}
		

		
	}
	
	private static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
