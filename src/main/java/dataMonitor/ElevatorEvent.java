package dataMonitor;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import userInteraction.UserPriority;

/*
 * STATUS: Complete//Not used in current implementation
 * This class defines the structure of events logged by the DataMonitor.
 */
public class ElevatorEvent implements Loggable {
	static 	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private int elevatorId;
	private int sourceFloor;
	private int destiniationFloor;
	private String time;
	
	public ElevatorEvent(int elevatorId, int sourceFloor, int destiniationFloor, String time, UserPriority priority) {
		this.elevatorId = elevatorId;
		this.sourceFloor = sourceFloor;
		this.destiniationFloor = destiniationFloor;
		this.time = time;
	}
	
	public String toLogData() {   
	    return gson.toJson(this);
	}
}
