package elevatorManager;

import userInteraction.UserPriority;

/*
 * STATUS: In Progress/Used in current implementation
 * This class represents the requests which can be handled by the ElevatorManager.
 */
public class ElevatorManagerRequest {
	int floorId;
	UserPriority priority;
	
	public ElevatorManagerRequest(int floorId, UserPriority priority) {
		this.floorId = floorId;
		this.priority = priority;
	}
}
