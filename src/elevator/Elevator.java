package elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
	private int id;
	private int currentFloorId;
	private ElevatorDoorStatus doorStatus;
	private ElevatorDirection currentDirection;
	private List<ElevatorObserver> observers = new ArrayList<>();
	
	//Constructor
	public Elevator(int id, int maximumLoad, int currentFloorId) {
		this.id = id;
		this.currentFloorId = currentFloorId;
		
		this.doorStatus = ElevatorDoorStatus.CLOSED;
		this.currentDirection = ElevatorDirection.STATIONARY;
	}
	
	// Methods
	public boolean isBusy() {
		return (currentDirection == ElevatorDirection.DOWN) || (currentDirection == ElevatorDirection.UP) || (currentDirection == ElevatorDirection.HANDLING_REQUEST);
	}
	
	public synchronized void addObserver(ElevatorObserver observer) {
		observers.add(observer);
	}
	
	public synchronized void removeObserver(ElevatorObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for(ElevatorObserver obs: observers) {
			obs.elevatorDidFinishTask(this);
		}
	}
	
	public void goToFloor(int floorId, ElevatorRequestType type) {
		ElevatorTask task = new ElevatorTask(this, floorId, type);
		task.start();
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

	public ElevatorDirection getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(ElevatorDirection currentDirection) {
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
				elevator.setCurrentDirection(ElevatorDirection.UP);
				System.out.print("\nElevator " + elevator.getId() + " is going up from floor " + elevator.getCurrentFloorId() + " to floor " + floorId + "\n");
				while(floorId > elevator.getCurrentFloorId()) {
					Thread.sleep(1000);
					elevator.setCurrentFloorId(elevator.getCurrentFloorId() + 1);
				}
				
				if (type == ElevatorRequestType.PICKUP) {
					elevator.setCurrentDirection(ElevatorDirection.HANDLING_REQUEST);
				} else {
					elevator.setCurrentDirection(ElevatorDirection.STATIONARY);
				}
				elevator.notifyObservers();
			}
			else {
				elevator.setCurrentDirection(ElevatorDirection.DOWN);
				System.out.print("\nElevator " + elevator.getId() + " is going down from floor " + elevator.getCurrentFloorId() + " to floor " + floorId + "\n");
				while(floorId < elevator.getCurrentFloorId()) {
					Thread.sleep(1000);
					elevator.setCurrentFloorId(elevator.getCurrentFloorId() - 1);
				}
				if (type == ElevatorRequestType.PICKUP) {
					elevator.setCurrentDirection(ElevatorDirection.HANDLING_REQUEST);
				} else {
					elevator.setCurrentDirection(ElevatorDirection.STATIONARY);
				}
				elevator.notifyObservers();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
