package GUI;

import java.util.ArrayList;

import elevator.Elevator;
import floor.Floor;

/**
 * STATUS: In-use in our system
 * This class is used carry data related to the Floors, Elevators, Requests, and User Requests
 */

public class DataObject {
	private ArrayList<Floor> floorArrayList;
	private ArrayList<Elevator> elevatorArrayList;
	private ArrayList<Request> requestArrayList;
	private ArrayList<userRequest> userRequestArrayList;
	
	/**
	 * Constructor for the DataObject Class
	 * @param fal ArrayList of Floors
	 * @param eal ArrayList of Elevators
	 * @param ral  ArrayList of Requests
	 * @param ural  ArrayList of User Requests
	 */
	public DataObject(ArrayList<Floor> fal, 
			ArrayList<Elevator> eal, 
			ArrayList<Request> ral, 
			ArrayList<userRequest> ural) {
		this.floorArrayList = fal; 
		this.elevatorArrayList = eal;
		this.requestArrayList = ral;
		this.userRequestArrayList = ural;
	}
	
	/**
	 * @return Returns ArrayList of Floors
	 */
	public ArrayList<Floor> getFloorArrayList(){
		return floorArrayList;
	}
	
	/**
	 * @return Returns ArrayList of Elevators
	 */
	public ArrayList<Elevator> getElevatorArrayList(){
		return elevatorArrayList;
	}
	
	/**
	 * @return Returns ArrayList of Requests
	 */
	public ArrayList<Request> getRequestArrayList(){
		return requestArrayList;
	}
	
	/**
	 * @return Returns ArrayList of userRequests
	 */
	public ArrayList<userRequest> getUserRequestArrayList(){
		return userRequestArrayList;
	}

}
