package elevator;

import java.util.ArrayList;
import java.util.List;

/**
 * This component defines the behavior and properties of elevators in our system.
 * It is responsible for notifying all ElevatorObservers (User,ElevatorManager,...) when it becomes available or ready for pickup.
 * Elevator instances can spawn threads to allow for concurrency purposes when multiple elevators are active simultaneously in the system.
 * 
 */
public class Elevator {
	/**
	 * ID associated with the Elevator
	 */
	private int id;
	/**
	 * ID associated with the floor the Elevator is currently at
	 */
	private int currentFloorId;
	/**
	 * Status of the elevator doors (Open/Closed)
	 */
	private ElevatorDoorStatus doorStatus;
	/**
	 * Current direction the elevator is going in (Up/Down)
	 */
	private ElevatorStatus currentDirection;
	/**
	 * Boolean representing whether an elevator should be stopped or not
	 */
	public boolean shouldBeStopped;
	/**
	 * ArrayList containing all observers associated with the elevator
	 */
	private List<ElevatorObserver> observers = new ArrayList<>();
	/**
	 * Boolean indicating whether the elevator is busy or not
	 */
	public boolean isBusy = false;
	
	//Constructor
	/**
	 * Public constructor of elevator.
	 * @param id ID associated to elevator
	 * @param maximumLoad Maximum Load associated to elevator
	 * @param currentFloorId ID of the current floor the elevator is at
	 */
	public Elevator(int id, int maximumLoad, int currentFloorId) {
		this.id = id;
		this.currentFloorId = currentFloorId;
		this.doorStatus = ElevatorDoorStatus.CLOSED;
		this.currentDirection = ElevatorStatus.STATIONARY;
	}
	
	// Methods
	/**
	 * Function return true if elevator is busy
	 * @return boolean
	 */
	public boolean isBusy() {
		return isBusy;
	}
	
	/**
	 * Adds the elevator to the list of observers.
	 * @param observer Elevator instance encapsulated in an ElevatorObserver instance
	 */
	public void addObserver(ElevatorObserver observer) {
		observers.add(observer);
	}
	
	/**
	 * Removes the elevator to the list of observers.
	 * @param observer Elevator instance encapsulated in an ElevatorObserver instance
	 */	
	public void removeObserver(ElevatorObserver observer) {
		observers.remove(observer);
	}
	
	/**
	 * Notify the list of observers.
	 * 
	 */	
	public void notifyObservers() {
		for(ElevatorObserver obs: observers) {
			obs.elevatorDidFinishTask(this);
		}
	}
	
	/**
	 * Function that takes care of elevator movement, if not on the same floor it creates a task thread and run it
	 * @param floorId ID associated with the floor we'd like the elevator to go to
	 * @param type Elevator Request type
	 */
	public void goToFloor(int floorId, ElevatorRequestType type) {
		isBusy = true;
		if(currentFloorId == floorId) {
			System.out.print("\nElevator " + id + " is already at floor " + floorId + "\n");
			if (type == ElevatorRequestType.PICKUP) {
				setCurrentDirection(ElevatorStatus.HANDLING_REQUEST);
			} else {
				setCurrentDirection(ElevatorStatus.STATIONARY);
			}
			isBusy = false;
			notifyObservers();
			return;
		}
		ElevatorTask task = new ElevatorTask(this, floorId, type);
		task.start();
	}
	

	/**
	 * Stops Elevator
	 */
	public void stopElevator() {
		if(this.currentDirection!=ElevatorStatus.STATIONARY) {
			this.shouldBeStopped=true;
		}
	}
	
	//Getters & Setters
	
	/**
	 * Current Floor ID getter.
	 * @return int currentFloorId
	 */
	public int getCurrentFloorId() {
		return currentFloorId;
	}

	/**
	 * Current Floor ID setter.
	 * @param currentFloorId ID of the floor to set
	 */
	public void setCurrentFloorId(int currentFloorId) {
		this.currentFloorId = currentFloorId;
	}

	/**
	 * Elevator Door Status Getter.
	 * @return ElevatorDoorStatus doorStatus
	 */
	public ElevatorDoorStatus getDoorStatus() {
		return doorStatus;
	}

	/**
	 * Elevator Door Status Setter.
	 * @param doorStatus Elevator Door status to set
	 */
	public void setDoorStatus(ElevatorDoorStatus doorStatus) {
		this.doorStatus = doorStatus;
	}

	/**
	 * Elevator Direction Status Getter.
	 * @return ElevatorStatus currentDirection 
	 */
	public ElevatorStatus getCurrentDirection() {
		return currentDirection;
	}

	/**
	 * Elevator Direction Status Setter.
	 * @param currentDirection Direction to orient the elevator towards when moving
	 */
	public void setCurrentDirection(ElevatorStatus currentDirection) {
		this.currentDirection = currentDirection;
	}

	/**
	 * Elevator Id Getter.
	 * @return int id 
	 */
	public int getId() {
		return id;
	}
	
}


/**
 * ElevatorTask0threads allow the use of concurrency when multiple elevators are active simultaneously in the system.
 * 
 */
class ElevatorTask extends Thread {
	/**
	 * Elevator Class instance
	 */
	Elevator elevator;
	/**
	 * Value used to represent a floor's ID
	 */
	int floorId;
	/**
	 * Elevator Request Type
	 */
	ElevatorRequestType type;
	
	/**
	 * Public constructor for ElevatorTask
	 * @param elevator  Elevator Class instance
	 * @param floorId Value used to represent a floor's ID
	 * @param type  Elevator Request Type
	 */
	public ElevatorTask(Elevator elevator, int floorId, ElevatorRequestType type) {
		this.elevator = elevator;
		this.floorId = floorId;
		this.type = type;
	}
	
	/**
	 * ElevatorTask thread run function
	 * Responsible for handling elevator movement
	 */
    public void run() {
		try {
			if (floorId >= elevator.getCurrentFloorId()) {
				elevator.setCurrentDirection(ElevatorStatus.UP);
				System.out.print("\nElevator " + elevator.getId() + " is going up from floor " + elevator.getCurrentFloorId() + " to floor " + floorId + "\n");
				while(floorId > elevator.getCurrentFloorId() && !elevator.shouldBeStopped) {
					Thread.sleep(500);
					elevator.setCurrentFloorId(elevator.getCurrentFloorId() + 1);
				}
			}
			else {
				elevator.setCurrentDirection(ElevatorStatus.DOWN);
				System.out.print("\nElevator " + elevator.getId() + " is going down from floor " + elevator.getCurrentFloorId() + " to floor " + floorId + "\n");
				while(floorId < elevator.getCurrentFloorId() && !elevator.shouldBeStopped) {
					Thread.sleep(500);
					elevator.setCurrentFloorId(elevator.getCurrentFloorId() - 1);
				}
			}
			
			if (type == ElevatorRequestType.PICKUP) {
				elevator.setCurrentDirection(ElevatorStatus.HANDLING_REQUEST);
			} else {
				elevator.setCurrentDirection(ElevatorStatus.STATIONARY);
				elevator.isBusy = false;
			}
			if(elevator.shouldBeStopped) {
				elevator.shouldBeStopped=false;
				System.out.println("Elevator is stopped");
			} else {
				elevator.notifyObservers();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
