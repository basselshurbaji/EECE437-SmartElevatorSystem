package userInteraction;

import elevator.Elevator;
import elevatorManager.ElevatorManager;
import elevatorManager.ElevatorManagerDestinationRequest;
import elevatorManager.ElevatorManagerRequest;

/**
 * This class is used to represent user interactions in our system. 
 * Currently, there are two types of user requests: pickup and destination.
 * It communicates with the ElevatorManager to handle the requests.
 */
public class UserInteraction {
	/**
	 * ElevatorManager instance
	 */
	ElevatorManager elevatorManager;
	/**
	 * Shared Instance of User Interaction
	 */
	private static UserInteraction sharedInstance;
	
	/**
	 * Private constructor of user interaction.
	 * It initiates an elevator manager.
	 * It serves as a singleton class.
	 */
	private UserInteraction() {
		this.elevatorManager = ElevatorManager.getInstance();
	}
	
	/**
	 * This method creates an instance of User Interaction if it doesn't exist already.
	 * @return a UserInteraction instance
	 */
	public static UserInteraction getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new UserInteraction();
		}
		return sharedInstance;
	}
	
	/**
	 * This method sends an elevator pickup request to the elevator manager to handle it.
	 * @param floorId id of floor of interest
	 * @param priority represents the priority of the user that is requesting. (NORMAL, HIGH)
	 */
	public void requestElevatorPickUp(int floorId, UserPriority priority) {
		elevatorManager.requestPickup(new ElevatorManagerRequest(floorId, priority));
	}
	
	/**
	 * This method sends an elevator destination request to the elevator manager to handle it.
	 * @param floorId id of floor of interest
	 * @param priority represents the priority of the user that is requesting. (NORMAL, HIGH)
	 * @param elevator specifies the elevator specified for the desired destination floor
	 */
	public void requestElevatorToDestination(int floorId, UserPriority priority, Elevator elevator) {
		elevatorManager.requestDestination(new ElevatorManagerDestinationRequest(floorId, priority, elevator));
	}
	
	/**
	 * This method sends an request to stop the elevator to the elevator manager to handle it.
	 * @param elevator specifies the elevator specified for the desired destination floor
	 */
	public void stopElevator(Elevator elevator) {
		elevatorManager.stopElevator(elevator);
	}
	
	/**
	 * This method sends an request to reset the elevator to the elevator manager to handle it.
	 */
	public void resetMode() {
		elevatorManager.resetMode();
	}
	
	/**
	 * This method sends an request to fire an emergency to the elevator manager to handle it.
	 */
	public void fireEmergencyRequest() {
		elevatorManager.handleEmergency();
	}
	
}
