package dataMonitor;

import java.util.Date;

public class ElevatorEvent {
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

	// Getters
	public int getId() {
		return id;
	}

	public int getElevatorId() {
		return elevatorId;
	}

	public int getSourceFloor() {
		return sourceFloor;
	}

	public int getDestiniationFloor() {
		return destiniationFloor;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}	
}
