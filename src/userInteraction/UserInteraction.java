package userInteraction;

import elevator.ElevatorDirection;
import elevator.Elevator;
import elevatorManager.ElevatorManager;

public class UserInteraction {
	ElevatorManager elevatorManager;
	
	private static UserInteraction sharedInstance;
	
	// Private Constructor (Singleton)
	private UserInteraction() {
		this.elevatorManager = ElevatorManager.getInstance();
	}
	
	public static UserInteraction getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new UserInteraction();
		}
		return sharedInstance;
	}
	
	//These functions should be sending these requests to the elevator manager to handle them properly
	public Elevator requestElevatorPickUp(int floorId, ElevatorDirection direction, UserPriority priority) {
		return elevatorManager.handlePickUpRequest(floorId, direction, priority);
	}
	
	public boolean requestElevatorToDestination(int floorId, UserPriority priority, Elevator elevator) {
		return elevatorManager.handleDestinationRequest(floorId, priority, elevator);
	}
	
	public void stopElevator(int elevatorId) {
		//TODO
	}
	
	public void setAlarmAlert(int elevatorId) {
		//TODO
	}
	
	
	public void closeDoor(int elevatorId) {
		//TODO
	}
	
	public void openDoor(int elevatorId) {
		//TODO
	}
	
}
