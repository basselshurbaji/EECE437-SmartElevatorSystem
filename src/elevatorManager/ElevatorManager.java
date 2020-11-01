package elevatorManager;

import java.util.ArrayList;

import dataMonitor.DataMonitor;
import elevator.Elevator;
import elevator.ElevatorDirection;
import floor.Floor;
import userInteraction.UserPriority;
import java.lang.Math;

public class ElevatorManager {
	private ArrayList<Floor> floors;
	private ArrayList<Elevator> elevators;
	private ElevatorManagerMode mode;
	private DataMonitor dataMonitor;
	private ElevatorManagerStatus status;
	private AvailabilityController availabilityController;
	private FloorPriorityController floorPriorityController;
	private EmergencyController emergencyController;
	
	private static ElevatorManager sharedInstance;
	
	// Private Constructor (Singleton)
	private ElevatorManager() {
		this.floors = new ArrayList<>();
		this.elevators = new ArrayList<>();
		this.dataMonitor = new DataMonitor();
		this.mode = ElevatorManagerMode.NORMAL;
		this.status = ElevatorManagerStatus.OFF;
		
		this.availabilityController = new AvailabilityController();
		this.floorPriorityController = new FloorPriorityController();
		this.emergencyController = new EmergencyController();
	}
	
	// Get Shared Singleton Instance
	public static ElevatorManager getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new ElevatorManager();
		}
		return sharedInstance;
	}
	
	public Elevator handlePickUpRequest(int floorId, ElevatorDirection direction, UserPriority priority) {
		if (elevators.size() == 0) {
			System.out.println("\nERROR: No Elevators\n");
			return null; 
		}
		
		if (floors.size() == 0) {
			System.out.println("\nERROR: No Floors\n");
			return null; 
		}
		
		if((floorId < floors.get(0).getId()) || floorId > floors.get(floors.size() - 1).getId()) {
			System.out.println("\nERROR: Floor ID is not valid\n");
			return null;
		}
		
		Elevator nearestElevator = elevators.get(0);
		int minimumDistance = Math.abs(floorId - elevators.get(0).getCurrentFloorId());
		for(Elevator e: elevators) {
			if ( Math.abs(floorId - e.getCurrentFloorId()) < minimumDistance ) {
				minimumDistance = Math.abs(floorId - e.getCurrentFloorId());
				nearestElevator = e;
			}
		}
		
		System.out.println("Elevator " + nearestElevator.getId()+  " in Current Floor: " + nearestElevator.getCurrentFloorId() + " is assigned to task.");
		
		//checks if the user is below or above the elevator
		if (floorId >= nearestElevator.getCurrentFloorId()) {
			while(floorId >  nearestElevator.getCurrentFloorId()) {
				System.out.println("Elevator " + nearestElevator.getId() +  " Going Up...");
				nearestElevator.goToFloor(nearestElevator.getCurrentFloorId()+1);
			}
		}
		else {
			while(floorId <  nearestElevator.getCurrentFloorId()) {
				System.out.println("Elevator " + nearestElevator.getId() +  " Going Down...");
				nearestElevator.goToFloor(nearestElevator.getCurrentFloorId()-1);
			}
		}
		
		return nearestElevator;
	}
	
	public boolean handleDestinationRequest(int floorId, UserPriority priority, Elevator elevator) {
		if (elevators.size() == 0) {
			System.out.println("\nERROR: No Elevators\n");
			return false; 
		}
		
		if (floors.size() == 0) {
			System.out.println("\nERROR: No Floors\n");
			return false; 
		}
		
		if((floorId < floors.get(0).getId()) || floorId > floors.get(floors.size() - 1).getId()) {
			System.out.println("\nERROR: Floor ID is not valid\n");
			return false;
		}
		
		//checks if the user is below or above the elevator
		if (floorId >= elevator.getCurrentFloorId()) {
			while(floorId >  elevator.getCurrentFloorId()) {
				System.out.println("Elevator " + elevator.getId() +  " Going Up...");
				elevator.goToFloor(elevator.getCurrentFloorId()+1);
			}
		}
		else {
			while(floorId <  elevator.getCurrentFloorId()) {
				System.out.println("Elevator " + elevator.getId() +  " Going Down...");
				elevator.goToFloor(elevator.getCurrentFloorId()-1);
			}
		}
		
		return true;
	}

	//Getters & Setters
	public ArrayList<Floor> getFloors() {
		return floors;
	}

	public void setFloors(ArrayList<Floor> floors) {
		this.floors = floors;
	}

	public ArrayList<Elevator> getElevators() {
		return elevators;
	}

	public void setElevators(ArrayList<Elevator> elevators) {
		this.elevators = elevators;
	}

	public ElevatorManagerMode getMode() {
		return mode;
	}

	public void setMode(ElevatorManagerMode mode) {
		this.mode = mode;
	}

	public ElevatorManagerStatus getStatus() {
		return status;
	}

	public void setStatus(ElevatorManagerStatus status) {
		this.status = status;
	}	
	
}
