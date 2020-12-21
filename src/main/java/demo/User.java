package demo;

import elevator.Elevator;
import elevator.ElevatorStatus;
import elevator.ElevatorObserver;
import userInteraction.UserInteraction;
import userInteraction.UserPriority;


public class User implements ElevatorObserver {
	private boolean alive = true;
	private int pickUpFloor;
	private int destinationFloor;
	private UserPriority priority;
	static UserInteraction UI = UserInteraction.getInstance();
	
	public User(int pickUpFloor, int destinationFloor, UserPriority priority) {
		this.pickUpFloor = pickUpFloor;
		this.destinationFloor = destinationFloor;
		this.priority = priority;
	}
	
	public void requestPickUp() {
		UI.requestElevatorPickUp(pickUpFloor, priority);
	}
	
	public void requestDestination(Elevator elevator) {
		UI.requestElevatorToDestination(destinationFloor, priority, elevator);
	}

	@Override
	public void elevatorDidFinishTask(Elevator elevator) {
		if (elevator.getCurrentFloorId() == pickUpFloor && elevator.getCurrentDirection() == ElevatorStatus.HANDLING_REQUEST &&alive) {
			requestDestination(elevator);
			alive = false;
		}
		
	}
}
