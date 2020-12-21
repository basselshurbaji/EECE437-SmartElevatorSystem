package elevatorManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import dataMonitor.DataMonitor;
import elevator.Elevator;
import elevator.ElevatorStatus;
import elevator.ElevatorObserver;
import elevator.ElevatorRequestType;
import floor.Floor;
import userInteraction.UserPriority;
import java.text.SimpleDateFormat;  
import java.util.Date;

import java.lang.Math;
import dataMonitor.*;
/*
 * STATUS: In Progress/Used in current implementation
 * This component is responsible for handling all requests by the UserInteraction component. 
 * Currently, there are two types of requests: pickUprequest, destination request.
 * Each type of request has a queue. The requests are currently handled FIFO.
 */
public class ElevatorManager implements ElevatorObserver {
	private ArrayList<Floor> floors;
	private ArrayList<Elevator> elevators;
	private ElevatorManagerMode mode;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	private PriorityQueue<ElevatorManagerRequest> pickUpRequests = new PriorityQueue<ElevatorManagerRequest>(Collections.reverseOrder());
	private Queue<ElevatorManagerDestinationRequest> destinationRequests = new LinkedList<>();
	private static ElevatorManager sharedInstance;
	
	// Private Constructor (Singleton)
	private ElevatorManager() {
		this.floors = new ArrayList<>();
		this.elevators = new ArrayList<>();
		this.mode = ElevatorManagerMode.NORMAL;
		
	}
	
	// Get Shared Singleton Instance
	public static ElevatorManager getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new ElevatorManager();
		}
		return sharedInstance;
	}
	
	public void requestPickup(ElevatorManagerRequest request) {
		if(mode==ElevatorManagerMode.EMERGENCY) {
			return;
		}
		pickUpRequests.add(request);
		handlePickUpRequest();
	}
	
	public void requestDestination(ElevatorManagerDestinationRequest request) {
		destinationRequests.add(request);
		handleDestinationRequest();
	}
	
	private boolean handlePickUpRequest() {
		if (pickUpRequests.isEmpty())
			return false;
		
		ElevatorManagerRequest request = pickUpRequests.element();
		int floorId = request.floorId;
		
		if(!(validateElevators() && validateFloors(floorId)))
			return false;
		
		Elevator nearestElevator = getNearestAvailableElevatorToFloor(floorId);
		if(nearestElevator == null) {
			System.out.print("\ncurrently there are no available elevators\n");
			return false;
		}
		
		System.out.println("\nElevator " + nearestElevator.getId()+  " at floor " + nearestElevator.getCurrentFloorId() + " is assigned to task.");
		pickUpRequests.remove();
		nearestElevator.goToFloor(floorId, ElevatorRequestType.PICKUP);
		return true;
	}
	
	private boolean handleDestinationRequest() {
		ElevatorManagerDestinationRequest request = destinationRequests.element();
		int floorId = request.floorId;
		Elevator elevator = request.elevator;
		if(!(validateElevators() && validateFloors(floorId)))
			return false;
		
		destinationRequests.remove();
		DataMonitor.getInstance().log(new ElevatorEvent(elevator.getId(), elevator.getCurrentFloorId(), floorId, formatter.format(new Date()),  request.userPriority));
		elevator.goToFloor(floorId, ElevatorRequestType.DESTINATION);
		//int elevatorId, int sourceFloor, int destiniationFloor, String time, UserPriority priority
		return true;
	}
	
	public void handleEmergency() {
		System.out.println("EMERGENCY MODE INITIATED");
		DataMonitor.getInstance().log(new EmergencyEvent("EMERGENCY MODE INITIATED",formatter.format(new Date())));
		this.pickUpRequests.clear();
		this.destinationRequests.clear();
		this.mode=ElevatorManagerMode.EMERGENCY;
		for (Elevator elevator: elevators) {
			elevator.stopElevator();
		}
		delay(1000);
		for(Elevator elevator: elevators) {
			elevator.goToFloor(0, ElevatorRequestType.DESTINATION);
		}
	}
	
	public void stopElevator(Elevator elevator) {
		elevator.stopElevator();
	}
	
	private Elevator getNearestAvailableElevatorToFloor(int floorId) {
		Elevator nearestElevator = null;
		int minimumDistance = Math.abs(floorId - elevators.get(0).getCurrentFloorId());
		for(Elevator e: elevators) {
			if ( (Math.abs(floorId - e.getCurrentFloorId()) <= minimumDistance) && !e.isBusy()) {
				minimumDistance = Math.abs(floorId - e.getCurrentFloorId());
				nearestElevator = e;
			}
		}
		return nearestElevator;
	}
	
	private boolean validateElevators() {
		if (elevators.size() == 0) {
			System.out.println("\nERROR: No Elevators\n");
			return false; 
		}
		return true;
	}
	
	private boolean validateFloors(int floorId) {
		if (floors.size() == 0) {
			System.out.println("\nERROR: No Floors\n");
			return false; 
		}
		
		if((floorId < floors.get(0).getId()) || floorId > floors.get(floors.size() - 1).getId()) {
			System.out.println("\nERROR: Floor ID is not valid\n");
			return false;
		}
		
		return true;
	}
	
	@Override
	public void elevatorDidFinishTask(Elevator elevator) {
		if (elevator.getCurrentDirection() == ElevatorStatus.HANDLING_REQUEST) {
			System.out.print("\nElevator " + elevator.getId() + " is now ready for pickup at floor " + elevator.getCurrentFloorId() + "\n");
		} else {
			System.out.print("\nElevator " + elevator.getId() + " is now free at floor " + elevator.getCurrentFloorId() +"\n");
		}
		handlePickUpRequest();
	}	

	//Getters & Setters
	public ArrayList<Floor> getFloors() {
		return floors;
	}

	public void setFloors(ArrayList<Floor> floors) {
		this.floors = floors;
	}

	public ArrayList<Elevator> getElevators() {
		return elevators;
	}

	public void setElevators(ArrayList<Elevator> elevators) {
		this.elevators = elevators;
		for(Elevator elev: elevators) {
			elev.addObserver(this);
		}
	}

	public ElevatorManagerMode getMode() {
		return mode;
	}

	public void resetMode() {
		DataMonitor.getInstance().log(new EmergencyEvent("NORMAL MODE INITIATED",formatter.format(new Date())));
		this.mode = ElevatorManagerMode.NORMAL;
	}
	
	private static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
