package main;

import java.util.ArrayList;
import java.util.Scanner;

import elevatorManager.ElevatorManager;
import floor.Floor;
import floor.FloorPriority;
import elevator.Elevator;
import elevator.ElevatorDirection;
import userInteraction.UserInteraction;
import userInteraction.UserPriority;

public class Main {


	public static void main(String[] args) {
		//Getting UserInteraction Singleton
		UserInteraction UI = UserInteraction.getInstance();
		
		//Getting ElevatorManager Singleton
		ElevatorManager EM = ElevatorManager.getInstance();
		
		//Initializing  Elevators
		ArrayList<Elevator> elevators = new ArrayList<Elevator>();
		Elevator E0 = new Elevator(0,100,0);
		Elevator E1 = new Elevator(1,100,0);
		elevators.add(E0);	
		elevators.add(E1);
		
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
		
		
		//Infinite loop that allows user to call an elevator and go to a desired destination.
		while(true) {
			Scanner scan = new Scanner(System.in);
			
			//Specify floor where user is located.
			System.out.print("\nEnter Floor Number(to exit, enter -1): ");
			int floor = scan.nextInt();
			
			if (floor == -1) {
				//Termination condition
				break;
			}
			
			//Request elevator pickup from the UserInteraction and return the assigned elevator
			//The UserInteraction will communicate with the ElevatorManager to perform the task.
			Elevator elv = UI.requestElevatorPickUp(floor,  ElevatorDirection.STATIONARY, UserPriority.NORMAL);
			
			//In case the user location doesn't exist, the returned elevator will be null.
			if(elv == null) {
				continue;
			}
			
			///Specify destination floor where the user wants the elevator to take him.
			int destinationFloor;
			
			//If the user inputs an invalid destination floor, give an error and ask him to re-enter a valid floor. 
			do {
				System.out.print("\nEnter destination floor: ");
				destinationFloor = scan.nextInt();
			} while(!UI.requestElevatorToDestination(destinationFloor, UserPriority.NORMAL, elv));
			
			
			//Print statuses of all elevators
			System.out.print("\nSTATUS OF ELEVATORS:\n");
			for(Elevator e: EM.getElevators()) {
				System.out.println("Elevator " + e.getId() + " is at floor: " + e.getCurrentFloorId());
			}
		}
		
		System.out.print("\nEXITED");
	}
	
}
