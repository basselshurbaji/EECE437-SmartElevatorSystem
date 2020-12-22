package demo;

import elevator.Elevator;
import elevator.ElevatorStatus;
import elevator.ElevatorObserver;
import userInteraction.UserInteraction;
import userInteraction.UserPriority;

/**
 * This class is used to model users in our Elevator System
 */ 

public class User implements ElevatorObserver {
	/**
	 * Boolean used to model if the Elevator Observer is alive
	 */ 
	private boolean alive = true;
	/**
	 * User required pickup floor
	 */ 
	private int pickUpFloor;
	/**
	 * User required destination floor
	 */ 
	private int destinationFloor;
	/**
	 * User Priority associated with user
	 */ 
	private UserPriority priority;
	/**
	 * Shared Instance of User Interaction
	 */ 
	static UserInteraction UI = UserInteraction.getInstance();
	
	/**
	 * Constructor for the User class
	 * @param pickUpFloor Pickup floor value
	 * @param destinationFloor Destination floor value
	 * @param priority Priority of user
	 */
	public User(int pickUpFloor, int destinationFloor, UserPriority priority) {
		this.pickUpFloor = pickUpFloor;
		this.destinationFloor = destinationFloor;
		this.priority = priority;
	}
	
	/**
	 * Request pickup for the user
	 */
	public void requestPickUp() {
		UI.requestElevatorPickUp(pickUpFloor, priority);
	}
	
	/**
	 * Request destination for the user
	 * @param elevator Observed elevator instance
	 */
	public void requestDestination(Elevator elevator) {
		UI.requestElevatorToDestination(destinationFloor, priority, elevator);
	}

	/**
	 * Check if an elevator observed by the user has finished its assigned tasks
	 * @param elevator Observed elevator instance
	 */
	@Override
	public void elevatorDidFinishTask(Elevator elevator) {
		if (elevator.getCurrentFloorId() == pickUpFloor && elevator.getCurrentDirection() == ElevatorStatus.HANDLING_REQUEST &&alive) {
			requestDestination(elevator);
			alive = false;
		}
		
	}
}
