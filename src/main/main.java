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

public class main {


	public static void main(String[] args) {
		UserInteraction UI = UserInteraction.getInstance();
		ElevatorManager EM=ElevatorManager.getInstance();
		Elevator E1=new Elevator(0,100,2);
		Floor F0=new Floor(0,FloorPriority.MEDIUM);
		Floor F1=new Floor(1,FloorPriority.MEDIUM);
		Floor F2=new Floor(2,FloorPriority.MEDIUM);
		ArrayList<Floor> Floors=new ArrayList<Floor>();
		ArrayList<Elevator> Elevators=new ArrayList<Elevator>();
		Elevators.add(E1);		
		Floors.add(F0);Floors.add(F1);Floors.add(F2);
		EM.setElevators(Elevators);
		EM.setFloors(Floors);
		
		int GF=0;
		Scanner scan=new Scanner(System.in);
		System.out.print("Enter Floor Number: ");
		int Floor=scan.nextInt();
		
		UserPriority priority = UserPriority.NORMAL;
		ElevatorDirection direction = ElevatorDirection.STATIONARY;
		UI.requestElevatorPickUp(Floor, direction, priority, Elevators.get(0));
		
		
	}
	
}
