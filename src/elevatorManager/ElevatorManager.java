package elevatorManager;

import java.util.ArrayList;

import dataMonitor.DataMonitor;
import elevator.Elevator;
import floor.Floor;

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
	
	public void  handleRequestMagically() {
		//TODO
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
