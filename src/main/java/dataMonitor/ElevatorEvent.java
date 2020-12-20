package dataMonitor;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * STATUS: Complete//Not used in current implementation
 * This class defines the structure of events logged by the DataMonitor.
 */
public class ElevatorEvent implements Loggable {
	static 	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private int id;
	private int elevatorId;
	private int sourceFloor;
	private int destiniationFloor;
	private Date startTime;
	private Date endTime;
	
	//Constructor
	public ElevatorEvent(int id, int elevatorId, int sourceFloor, int destiniationFloor, Date startTime, Date endTime) {
		this.id = id;
		this.elevatorId = elevatorId;
		this.sourceFloor = sourceFloor;
		this.destiniationFloor = destiniationFloor;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String toLogData() {   
	    return gson.toJson(this);
	}
}
