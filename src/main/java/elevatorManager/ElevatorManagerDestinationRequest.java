package elevatorManager;

import elevator.Elevator;
import userInteraction.UserPriority;

/*
 * STATUS: In Progress/Used in current implementation
 */

/**
 * This class represents the destination requests which can be handled by the ElevatorManager.
 * It extends the ElevatorManagerRequest class. 
 */
public class ElevatorManagerDestinationRequest extends ElevatorManagerRequest {
	Elevator elevator;
	
	/**
	 * Constructor of ElevatorManagerDestinationRequest
	 * @param floorId represents the floor id of interest
	 * @param userPriority represents the priority of the user that is requesting. (NORMAL, HIGH)
	 * @param elevator specifies the elevator specified for the desired destination floor
	 */
	public ElevatorManagerDestinationRequest(int floorId, UserPriority priority, Elevator elevator) {
		super(floorId, priority);
		this.elevator = elevator;
	}

}
