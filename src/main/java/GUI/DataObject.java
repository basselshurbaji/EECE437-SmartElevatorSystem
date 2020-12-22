package GUI;

import java.util.ArrayList;

import elevator.Elevator;
import floor.Floor;

/**
 * This class is used carry data related to the Floors, Elevators, Requests, and User Requests
 */

public class DataObject {
	/**
	 * ArrayList of floors
	 */
	private ArrayList<Floor> floorArrayList;
	/**
	 *  ArrayList of elevators
	 */
	private ArrayList<Elevator> elevatorArrayList;
	/**
	 *  ArrayList of requests
	 */
	private ArrayList<Request> requestArrayList;
	/**
	 *  ArrayList of user requests
	 */
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
	 * Function used to get all Floors stored in the DataObject
	 * @return Returns ArrayList of Floors
	 */
	public ArrayList<Floor> getFloorArrayList(){
		return floorArrayList;
	}
	
	/**
	 * Function used to get all Elevators stored in the DataObject
	 * @return Returns ArrayList of Elevators
	 */
	public ArrayList<Elevator> getElevatorArrayList(){
		return elevatorArrayList;
	}
	
	/**
	 * Function used to get all Requests stored in the DataObject
	 * @return Returns ArrayList of Requests
	 */
	public ArrayList<Request> getRequestArrayList(){
		return requestArrayList;
	}
	
	/**
	 * Function used to get all User Requests stored in the DataObject
	 * @return Returns ArrayList of userRequests
	 */
	public ArrayList<userRequest> getUserRequestArrayList(){
		return userRequestArrayList;
	}

}
