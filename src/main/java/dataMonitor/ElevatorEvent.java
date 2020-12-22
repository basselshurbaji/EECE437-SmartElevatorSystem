package dataMonitor;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import userInteraction.UserPriority;

/**
 * This class defines the structure of events logged by the DataMonitor,implements Loggable.
 * 
 */
public class ElevatorEvent implements Loggable {
	static 	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private int elevatorId;
	private int sourceFloor;
	private int destiniationFloor;
	private String time;
	
	/**
	 * Public constructor for ElevatorEvent.
	 * @param int elevatorId, int sourceFloor, int destionationFloor, String time, UserPriority
	 * 
	 */
	public ElevatorEvent(int elevatorId, int sourceFloor, int destiniationFloor, String time, UserPriority priority) {
		this.elevatorId = elevatorId;
		this.sourceFloor = sourceFloor;
		this.destiniationFloor = destiniationFloor;
		this.time = time;
	}
	
	/**
	 * Converts ElevatorEvent to Json
	 * @return String Data 
	 */
	public String toLogData() {   
	    return gson.toJson(this);
	}
}
