package userInteraction;

import elevator.Elevator;
import elevatorManager.ElevatorManager;
import elevatorManager.ElevatorManagerDestinationRequest;
import elevatorManager.ElevatorManagerRequest;

/*
 * STATUS: In Progress/Used in our system
 * This class is used to represent user interactions in our system. 
 * Currently, there are two types of user requests: pickup and destination.
 * It communicates with the ElevatorManager to handle the requests.
 */
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
	public void requestElevatorPickUp(int floorId, UserPriority priority) {
		elevatorManager.requestPickup(new ElevatorManagerRequest(floorId, priority));
	}
	
	public void requestElevatorToDestination(int floorId, UserPriority priority, Elevator elevator) {
		elevatorManager.requestDestination(new ElevatorManagerDestinationRequest(floorId, priority, elevator));
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
