package GUI;

import java.util.ArrayList;

import elevator.Elevator;
import floor.Floor;

public class DataObject {
	private ArrayList<Floor> floorArrayList;
	private ArrayList<Elevator> elevatorArrayList;
	private ArrayList<Request> requestArrayList;
	private ArrayList<userRequest> userRequestArrayList;
	
	public DataObject(ArrayList<Floor> fal, 
			ArrayList<Elevator> eal, 
			ArrayList<Request> ral, 
			ArrayList<userRequest> ural) {
		this.floorArrayList = fal; 
		this.elevatorArrayList = eal;
		this.requestArrayList = ral;
		this.userRequestArrayList = ural;
	}
	
	//Getters
	public ArrayList<Floor> getFloorArrayList(){
		return floorArrayList;
	}
	public ArrayList<Elevator> getElevatorArrayList(){
		return elevatorArrayList;
	}
	public ArrayList<Request> getRequestArrayList(){
		return requestArrayList;
	}
	public ArrayList<userRequest> getUserRequestArrayList(){
		return userRequestArrayList;
	}

}
