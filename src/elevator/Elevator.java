package elevator;

public class Elevator {
	private int id;
	private int maximumLoad;
	private int currentFloorId;
	private ElevatorDoorStatus doorStatus;
	private ElevatorDirection currentDirection;
	private int currentLoad;
	
	ElevatorDoorController doorController;
	
	//Constructor
	public Elevator(int id, int maximumLoad, int currentFloorId) {
		this.currentFloorId = id;
		this.maximumLoad = maximumLoad;
		this.currentFloorId = currentFloorId;
		
		this.doorStatus = ElevatorDoorStatus.CLOSED;
		this.currentDirection = ElevatorDirection.STATIONARY;
		this.currentLoad = 0;
	}
	
	// Methods
	public boolean isOverloaded() {
		return currentLoad > maximumLoad;
	}
	
	public boolean isMoving() {
		return currentDirection != ElevatorDirection.STATIONARY;
	}
	
	public void goToFloor(int floorId) {
		this.currentFloorId = floorId;
	}
	

	//Getters & Setters
	public int getMaximumLoad() {
		return maximumLoad;
	}

	public void setMaximumLoad(int maximumLoad) {
		this.maximumLoad = maximumLoad;
	}

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

	public int getCurrentLoad() {
		return currentLoad;
	}

	public void setCurrentLoad(int currentLoad) {
		this.currentLoad = currentLoad;
	}
	
	
}
