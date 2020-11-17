package elevatorManager;

import userInteraction.UserPriority;

public class ElevatorManagerRequest {
	int floorId;
	UserPriority priority;
	
	public ElevatorManagerRequest(int floorId, UserPriority priority) {
		this.floorId = floorId;
		this.priority = priority;
	}
}
