package GUI;

import GUI.Request;
import userInteraction.UserPriority;

public class userRequest extends Request{
	private int id;
	private int pickUpFloor;
	private int destFloor;
	private UserPriority priority;
	
	
	//Constructor
	public userRequest(int id, int PF, int DF, UserPriority p, char type, int time) {
		super(type, time);
		this.id = id;
		this.pickUpFloor = PF;
		this.destFloor = DF;
		this.priority = p;
	}
	
	//Getters
	public int getID() {
		return id;
	}
	public int getPickUpFloor() {
		return pickUpFloor;
	}
	public int getDestinationFloor() {
		return destFloor;
	}
	public UserPriority getPriority() {
		return priority;
	}
	
	//Setters
	public void setID(int id) {
		this.id = id;
	}
	public void setPickUpFloor(int PF) {
		this.pickUpFloor = PF;
	}
	public void setDestinationFloor(int DF) {
		this.destFloor = DF;
	}
	public void setPriority(UserPriority P) {
		this.priority = P;
	}
}

