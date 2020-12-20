package elevatorManager;

import elevator.Elevator;
import userInteraction.UserPriority;

/*
 * STATUS: In Progress/Used in current implementation
 * This class represents the destination requests which can be handled by the ElevatorManager.
 * It extends the ElevatorManagerRequest class. 
 */

public class ElevatorManagerDestinationRequest extends ElevatorManagerRequest {
	Elevator elevator;
	
	public ElevatorManagerDestinationRequest(int floorId, UserPriority priority, Elevator elevator) {
		super(floorId, priority);
		this.elevator = elevator;
	}

}
