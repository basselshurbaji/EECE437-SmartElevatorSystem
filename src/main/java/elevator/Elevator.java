package elevator;

import java.util.ArrayList;
import java.util.List;

/*
 * STATUS: In Progress/Used in current implementation
 * This component defines the behavior and properties of elevators in our system.
 * It is responsible for notifying all ElevatorObservers (User,ElevatorManager,...) when it becomes available or ready for pickup.
 * Elevator instances can spawn threads to allow for concurrency purposes when multiple elevators are active simultaneously in the system.
 */
public class Elevator {
	private int id;
	private int currentFloorId;
	private ElevatorDoorStatus doorStatus;
	private ElevatorStatus currentDirection;
	public boolean shouldBeStopped;
	private boolean isOutOfService;
	private List<ElevatorObserver> observers = new ArrayList<>();
	
	//Constructor
	public Elevator(int id, int maximumLoad, int currentFloorId) {
		this.id = id;
		this.currentFloorId = currentFloorId;
		this.isOutOfService=false;
		this.doorStatus = ElevatorDoorStatus.CLOSED;
		this.currentDirection = ElevatorStatus.STATIONARY;
	}
	
	// Methods
	public boolean isBusy() {
		return (currentDirection == ElevatorStatus.DOWN) || (currentDirection == ElevatorStatus.UP) || (currentDirection == ElevatorStatus.HANDLING_REQUEST);
	}
	
	public void setOutService(boolean isOutOfService) {
		this.isOutOfService=isOutOfService;
	}
	
	public void addObserver(ElevatorObserver observer) {
		observers.add(observer);
	}
	
	public void removeObserver(ElevatorObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for(ElevatorObserver obs: observers) {
			obs.elevatorDidFinishTask(this);
		}
	}
	
	public void goToFloor(int floorId, ElevatorRequestType type) {
		
		if(this.isOutOfService) {
			System.out.print("\nElevator " + id + " is out of service \n");
			return;
		}
		
		if(currentFloorId == floorId) {
			System.out.print("\nElevator " + id + " is already at floor " + floorId + "\n");
			if (type == ElevatorRequestType.PICKUP) {
				setCurrentDirection(ElevatorStatus.HANDLING_REQUEST);
			} else {
				setCurrentDirection(ElevatorStatus.STATIONARY);
			}
			notifyObservers();
			return;
		}
		ElevatorTask task = new ElevatorTask(this, floorId, type);
		task.start();
	}
	

	public void stopElevator() {
		if(this.currentDirection!=ElevatorStatus.STATIONARY) {
			this.shouldBeStopped=true;
		}
	}
	
	//Getters & Setters
	public int getCurrentFloorId() {
		return currentFloorId;
	}

	public void setCurrentFloorId(int currentFloorId) {
		this.currentFloorId = currentFloorId;
	}

	public ElevatorDoorStatus getDoorStatus() {
		return doorStatus;
	}

	public void setDoorStatus(ElevatorDoorStatus doorStatus) {
		this.doorStatus = doorStatus;
	}

	public ElevatorStatus getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(ElevatorStatus currentDirection) {
		this.currentDirection = currentDirection;
	}

	public int getId() {
		return id;
	}
	
}

class ElevatorTask extends Thread {
	Elevator elevator;
	int floorId;
	ElevatorRequestType type;
	
	public ElevatorTask(Elevator elevator, int floorId, ElevatorRequestType type) {
		this.elevator = elevator;
		this.floorId = floorId;
		this.type = type;
	}
    public void run() {
		try {
			if (floorId >= elevator.getCurrentFloorId()) {
				elevator.setCurrentDirection(ElevatorStatus.UP);
				System.out.print("\nElevator " + elevator.getId() + " is going up from floor " + elevator.getCurrentFloorId() + " to floor " + floorId + "\n");
				while(floorId > elevator.getCurrentFloorId() && !elevator.shouldBeStopped) {
					Thread.sleep(1000);
					elevator.setCurrentFloorId(elevator.getCurrentFloorId() + 1);
				}
				
				elevator.shouldBeStopped=false;
				
				if (type == ElevatorRequestType.PICKUP) {
					elevator.setCurrentDirection(ElevatorStatus.HANDLING_REQUEST);
				} else {
					elevator.setCurrentDirection(ElevatorStatus.STATIONARY);
				}
				elevator.notifyObservers();
			}
			else {
				elevator.setCurrentDirection(ElevatorStatus.DOWN);
				System.out.print("\nElevator " + elevator.getId() + " is going down from floor " + elevator.getCurrentFloorId() + " to floor " + floorId + "\n");
				while(floorId < elevator.getCurrentFloorId() && !elevator.shouldBeStopped) {
					Thread.sleep(1000);
					elevator.setCurrentFloorId(elevator.getCurrentFloorId() - 1);
				}
				
				elevator.shouldBeStopped=false;
				
				if (type == ElevatorRequestType.PICKUP) {
					elevator.setCurrentDirection(ElevatorStatus.HANDLING_REQUEST);
				} else {
					elevator.setCurrentDirection(ElevatorStatus.STATIONARY);
				}
				elevator.notifyObservers();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
