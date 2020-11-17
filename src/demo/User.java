package demo;

import elevator.Elevator;
import elevator.ElevatorDirection;
import elevator.ElevatorObserver;
import userInteraction.UserInteraction;
import userInteraction.UserPriority;

public class User implements ElevatorObserver {
	private boolean alive = true;
	private int pickUpFloor;
	private int destinationFloor;
	static UserInteraction UI = UserInteraction.getInstance();
	
	public User(int pickUpFloor, int destinationFloor) {
		this.pickUpFloor = pickUpFloor;
		this.destinationFloor = destinationFloor;
	}
	
	public void requestPickUp() {
		UI.requestElevatorPickUp(pickUpFloor, UserPriority.NORMAL);
	}
	
	public void requestDestination(Elevator elevator) {
		UI.requestElevatorToDestination(destinationFloor, UserPriority.NORMAL, elevator);
	}

	@Override
	public synchronized void elevatorDidFinishTask(Elevator elevator) {
		if (elevator.getCurrentFloorId() == pickUpFloor && elevator.getCurrentDirection() == ElevatorDirection.HANDLING_REQUEST &&alive) {
			requestDestination(elevator);
			alive = false;
		}
		
	}
}
