package elevatorManager;

import floor.*;
import userInteraction.UserPriority;

/*
 * STATUS: In Progress/Used in current implementation
 * This class represents the requests which can be handled by the ElevatorManager.
 */
public class ElevatorManagerRequest implements Comparable<ElevatorManagerRequest>  {	
	int floorId;
	int priority;
	static ElevatorManager manager=ElevatorManager.getInstance();
	
	public ElevatorManagerRequest(int floorId, UserPriority userPriority) {
		this.floorId=floorId;
		Floor floor=manager.getFloors().get(floorId);
		if(userPriority==UserPriority.HIGH) {
			this.priority=3;
		}
		
		else if (floor.getPriority()==FloorPriority.HIGH) {
			this.priority=2;
		}
		else if(floor.getPriority()==FloorPriority.MEDIUM) {
			this.priority=1;
		}
		else if(floor.getPriority()==FloorPriority.LOW) {
			this.priority=0;
		}
	}
	
	@Override
	public int compareTo(ElevatorManagerRequest r2) {
		if (this.priority < r2.priority) {
			return -1;
		} else if (this.priority > r2.priority) {
			return 1;
		}			
		return 0;
	}
	
	
}
