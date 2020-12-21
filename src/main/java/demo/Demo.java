package demo;

import java.util.ArrayList;
import elevatorManager.ElevatorManager;
import floor.Floor;
import floor.FloorPriority;
import userInteraction.UserInteraction;
import userInteraction.UserPriority;
import elevator.Elevator;

public class Demo {

	public static void main(String[] args) {
		UserInteraction UI=UserInteraction.getInstance();
		//Getting ElevatorManager Singleton
		ElevatorManager EM = ElevatorManager.getInstance();
		
		//Initializing  Elevators
		ArrayList<Elevator> elevators = new ArrayList<Elevator>();
		Elevator E0 = new Elevator(0,100,0);
//		Elevator E1 = new Elevator(1,100,0);
		elevators.add(E0);	
//		elevators.add(E1);
		
		//Initializing Floors
		ArrayList<Floor> floors = new ArrayList<Floor>();
		Floor F0 = new Floor(0,FloorPriority.MEDIUM);
		Floor F1 = new Floor(1,FloorPriority.MEDIUM);
		Floor F2 = new Floor(2,FloorPriority.MEDIUM);
		Floor F3 = new Floor(3,FloorPriority.MEDIUM);
		floors.add(F0);
		floors.add(F1);
		floors.add(F2);
		floors.add(F3);
		
		//Passing the elevators and floors to the elevator manager
		EM.setElevators(elevators);
		EM.setFloors(floors);
		
		User user1 = new User(0,2, UserPriority.NORMAL);
		User user2 = new User(0,2, UserPriority.NORMAL);
		User user3 = new User(3,1, UserPriority.HIGH);
		
		E0.addObserver(user1);
		E0.addObserver(user2);
		E0.addObserver(user3);
//		E1.addObserver(user1);
//		E1.addObserver(user2);
//		E1.addObserver(user3);
		
		user1.requestPickUp();
		delay(100);
		user2.requestPickUp();
		delay(100);
		user3.requestPickUp();
//		delay(50);
//		UI.fireEmergencyRequest();
	}
	
	private static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
