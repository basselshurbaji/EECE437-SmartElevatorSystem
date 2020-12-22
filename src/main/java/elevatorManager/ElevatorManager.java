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


/**
 * This component is responsible for handling all requests by the UserInteraction component. 
 * It has a queues for handling the requests by the UserInteraction component.
 * Requests are handled while taking into consideration the priorities specified.
 * This component is also responsible for handling emergencies.
 *
 */
public class ElevatorManager implements ElevatorObserver {
	private ArrayList<Floor> floors;
	private ArrayList<Elevator> elevators;
	private ElevatorManagerMode mode;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	private PriorityQueue<ElevatorManagerRequest> pickUpRequests = new PriorityQueue<ElevatorManagerRequest>(Collections.reverseOrder());
	private Queue<ElevatorManagerDestinationRequest> destinationRequests = new LinkedList<>();
	private static ElevatorManager sharedInstance;
	
	/**
	 * Private constructor of emergency manager.
	 * It initiates an empty array of floors, elevators, and sets mode to normal.
	 */
	private ElevatorManager() {
		this.floors = new ArrayList<>();
		this.elevators = new ArrayList<>();
		this.mode = ElevatorManagerMode.NORMAL;
		
	}
	
	/**
	 * @return returns ElevatorManager singleton shared instance.
	 */
	public static ElevatorManager getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new ElevatorManager();
		}
		return sharedInstance;
	}
	
	/**
	 * Adds the pick up request passed to the queue to be handled.
	 * @param request ElevatorManagerRequest passed to be handled.
	 */
	public void requestPickup(ElevatorManagerRequest request) {
		if(mode==ElevatorManagerMode.EMERGENCY) {
			return;
		}
		pickUpRequests.add(request);
		handlePickUpRequest();
	}
	
	/**
	 * Adds the destination request passed to the queue to be handled.
	 * @param request ElevatorManagerRequest passed to be handled.
	 */
	public void requestDestination(ElevatorManagerDestinationRequest request) {
		destinationRequests.add(request);
		handleDestinationRequest();
	}
	
	/**
	 * This method handles the top request in the priority queue.
	 * It checks if there are available elevators.
	 * If there is an elevator elevator, it is assigned to the task.
	 * @return true if an elevator is assigned to task, and false otherwise.
	 */
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
	
	/**
	 * This method handles the top destination request in the queue.
	 * @return true if an elevator is assigned to task, and false otherwise.
	 */
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
	
	/**
	 * This method handles the emergency properly.
	 * It stops all elevators at nearest floor, and returns them to the ground floor.
	 */
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
	
	/**
	 * This method stops the passed elevator at the nearest floor.
	 * @param elevator Elevator passed to be stopped.
	 */
	public void stopElevator(Elevator elevator) {
		elevator.stopElevator();
	}
	
	/**
	 * This method finds the nearest free elevator to the passed floor. 
	 * @param floorId id of floor of interest
	 * @return Elevator that is the available and nearest to floorId 
	 */
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
	
	/**
	 * Validates if there are elevators array is not empty.
	 * @return false if elevators array empty, true otherwise
	 */
	private boolean validateElevators() {
		if (elevators.size() == 0) {
			System.out.println("\nERROR: No Elevators\n");
			return false; 
		}
		return true;
	}
	
	/**
	 * Validates if floors are not empty and if the passed floorId is within the floors listed.
	 * @param floorId floorId to be checked
	 * @return true if floorId is within the listed floors, false otherwise.
	 */
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
	
	/**
	 * @return returns the ElevatorManagerMode (Normal, Emergency)
	 */
	public ElevatorManagerMode getMode() {
		return mode;
	}

	/**
	 * Resets the mode of the manager back to normal.
	 */
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
