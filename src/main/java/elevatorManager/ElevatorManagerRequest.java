package elevatorManager;

import floor.*;
import userInteraction.UserPriority;


/**
 * This class represents the requests requested by users which can be handled by the ElevatorManager.
 */
public class ElevatorManagerRequest implements Comparable<ElevatorManagerRequest>  {	
	/**
	 * Represents the floor id of interest
	 */
	int floorId;
	/**
	 * Represents the priority of the user that is requesting (Integer value)
	 */
	int priority;
	/**
	 * Represents the priority of the user that is requesting. (NORMAL, HIGH)
	 */
	UserPriority userPriority;
	/**
	 * Elevator Manager shared Instance
	 */
	static ElevatorManager manager=ElevatorManager.getInstance();
	
	/**
	 * Constructor of ElevatorManagerRequest
	 * @param floorId represents the floor id of interest
	 * @param userPriority represents the priority of the user that is requesting. (NORMAL, HIGH)
	 */
	public ElevatorManagerRequest(int floorId, UserPriority userPriority) {
		this.floorId=floorId;
		this.userPriority = userPriority;
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
	
	/**
	 * Function used to compare the priority of two elevator manager requests
	 * @param r2 Represents the ElevatorManagerRequest priority
	 */
	
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
