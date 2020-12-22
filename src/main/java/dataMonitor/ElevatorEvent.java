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
	/**
	 * Shared instance of the GSON builder
	 */
	static 	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	/**
	 * Elevator ID
	 */
	private int elevatorId;
	/**
	 * Source floor associated with the elevator
	 */
	private int sourceFloor;
	/**
	 * Destination floor associated with the elevator
	 */
	private int destiniationFloor;
	/**
	 * Time associated with an elevator event
	 */
	private String time;
	
	/**
	 * Public constructor for ElevatorEvent.
	 * @param elevatorId ID associated with elevator instance
	 * @param sourceFloor Source Floor associated with elevator instance
	 * @param destiniationFloor Destionation Floor associated with elevator instance
	 * @param time Time associated with elevator event
	 * @param priority User priority associated with elevator event
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
