package demo;

import java.util.ArrayList;

import elevator.Elevator;
import elevatorManager.ElevatorManager;
import floor.Floor;
import floor.FloorPriority;
import userInteraction.UserInteraction;

public class SimpleDemo {
	public static void main(String[] args) {
		UserInteraction UI=UserInteraction.getInstance();
		//Getting ElevatorManager Singleton
		ElevatorManager EM = ElevatorManager.getInstance();
		
		//Initializing  Elevators
		ArrayList<Elevator> elevators = new ArrayList<Elevator>();
		Elevator E0 = new Elevator(0,100,0);
		elevators.add(E0);	
		
		//Initializing Floors
		ArrayList<Floor> floors = new ArrayList<Floor>();
		Floor F0 = new Floor(0,FloorPriority.MEDIUM);
		Floor F1 = new Floor(1,FloorPriority.MEDIUM);
		floors.add(F0);
		floors.add(F1);
		
		//Passing the elevators and floors to the elevator manager
		EM.setElevators(elevators);
		EM.setFloors(floors);
		
		User user1 = new User(0,1);
		
		E0.addObserver(user1);
		user1.requestPickUp();
		delay(3000);
		UI.fireEmergencyRequest();
	}
	
	private static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
