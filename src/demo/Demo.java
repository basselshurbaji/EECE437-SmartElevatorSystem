package demo;

import java.util.ArrayList;
import java.util.Scanner;

import elevatorManager.ElevatorManager;
import floor.Floor;
import floor.FloorPriority;
import elevator.Elevator;
import elevator.ElevatorDirection;
import elevator.ElevatorObserver;
import userInteraction.UserInteraction;
import userInteraction.UserPriority;

public class Demo {


	public static void main(String[] args) {
		//Getting UserInteraction Singleton
		UserInteraction UI = UserInteraction.getInstance();
		
		//Getting ElevatorManager Singleton
		ElevatorManager EM = ElevatorManager.getInstance();
		
		//Initializing  Elevators
		ArrayList<Elevator> elevators = new ArrayList<Elevator>();
		Elevator E0 = new Elevator(0,100,0);
		
		//Elevator E1 = new Elevator(1,100,0);
		elevators.add(E0);	
		//elevators.add(E1);
		
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
		
		UI.requestElevatorPickUp(3, UserPriority.NORMAL);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UI.requestElevatorPickUp(2, UserPriority.NORMAL);
		
	}
	
}
