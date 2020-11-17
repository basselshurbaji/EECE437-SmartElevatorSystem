package elevatorManager;

import elevator.Elevator;
import userInteraction.UserPriority;

public class ElevatorManagerDestinationRequest extends ElevatorManagerRequest {
	Elevator elevator;
	
	public ElevatorManagerDestinationRequest(int floorId, UserPriority priority, Elevator elevator) {
		super(floorId, priority);
		this.elevator = elevator;
	}

}
