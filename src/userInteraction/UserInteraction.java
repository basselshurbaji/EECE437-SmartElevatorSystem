package userInteraction;

import elevator.ElevatorDirection;
import elevatorManager.ElevatorManager;

public class UserInteraction {
	ElevatorManager elevatorManager;
	
	private static UserInteraction sharedInstance;
	
	// Private Constructor (Singleton)
	private UserInteraction() {
		this.elevatorManager = ElevatorManager.getInstance();
	}
	
	public UserInteraction getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new UserInteraction();
		}
		return sharedInstance;
	}
	
	//These functions should be sending these requests to the elevator manager to handle them properly
	public void requestElevatorPickUp(int floorId, ElevatorDirection direction, UserPriority priority) {
		//TODO
		elevatorManager.handleRequestMagically();
	}
	
	public void requestElevatorToDesitination(int floorId, UserPriority priority) {
		//TODO
		elevatorManager.handleRequestMagically();
	}
	
	public void stopElevator(int elevatorId) {
		//TODO
		elevatorManager.handleRequestMagically();
	}
	
	public void setAlarmAlert(int elevatorId) {
		//TODO
		elevatorManager.handleRequestMagically();
	}
	
	
	public void closeDoor(int elevatorId) {
		//TODO
		elevatorManager.handleRequestMagically();
	}
	
	public void openDoor(int elevatorId) {
		//TODO
		elevatorManager.handleRequestMagically();
	}
	
}
