package GUI;

import GUI.Request;
import userInteraction.UserPriority;

/**
 * This class is used to model pick-up requests from users. It inherits from the
 * 'Request' class
 */

public class userRequest extends Request{
	/**
	 * User ID associated with request
	 */
	private int id;
	/**
	 * Pickup floor in the request
	 */
	private int pickUpFloor;
	/**
	 * Destination floor in the request
	 */
	private int destFloor;
	/**
	 * User priority
	 */
	private UserPriority priority;
	
	
	/**
	 * Constructor for the Request class
	 * @param id ID associated with the user
	 * @param PF Pick-up floor associated with request
	 * @param DF Destination floor associated with request
	 * @param p User priority associated with the request
	 * @param type Character associated with the type of request
	 * @param time Time associated with request
	 */
	public userRequest(int id, int PF, int DF, UserPriority p, char type, int time) {
		super(type, time);
		this.id = id;
		this.pickUpFloor = PF;
		this.destFloor = DF;
		this.priority = p;
	}
	
	/**
	 * @return Returns ID associated with request
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @return returns Pickup floor associated with request
	 */
	public int getPickUpFloor() {
		return pickUpFloor;
	}
	
	/**
	 * @return Returns destination floor associated with request
	 */
	public int getDestinationFloor() {
		return destFloor;
	}
	
	/**
	 * @return Returns priority associated with request
	 */
	public UserPriority getPriority() {
		return priority;
	}
	
	/**
	 * 
	 * @param id  Sets Pickup floor associated with request
	 */
	public void setID(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @param PF Sets destination floor associated with request
	 */
	public void setPickUpFloor(int PF) {
		this.pickUpFloor = PF;
	}
	
	/**
	 * 
	 * @param DF  Sets destination floor associated with request
	 */
	public void setDestinationFloor(int DF) {
		this.destFloor = DF;
	}
	
	/**
	 * 
	 * @param P  Sets priority associated with request
	 */
	public void setPriority(UserPriority P) {
		this.priority = P;
	}
}

