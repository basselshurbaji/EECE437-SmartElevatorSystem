package elevator;

public class Elevator {
	private int id;
	private int currentFloorId;
	private ElevatorDoorStatus doorStatus;
	private ElevatorDirection currentDirection;
	
	//Constructor
	public Elevator(int id, int maximumLoad, int currentFloorId) {
		this.id = id;
		this.currentFloorId = currentFloorId;
		
		this.doorStatus = ElevatorDoorStatus.CLOSED;
		this.currentDirection = ElevatorDirection.STATIONARY;
	}
	
	// Methods
	public boolean isMoving() {
		return currentDirection != ElevatorDirection.STATIONARY;
	}
	
	public void goToFloor(int floorId) {
		//Introduce a delay to mimic real life transition delay
		try {
			if (floorId >= currentFloorId) {
				while(floorId >  currentFloorId) {
					System.out.println("Elevator " + id +  " Going Up...");
					Thread.sleep(1000);
					currentFloorId++;
					System.out.println("Elevator " + id + " is at Floor: " + getCurrentFloorId());
				}
			}
			else {
				while(floorId < currentFloorId) {
					System.out.println("Elevator " + id +  " Going Down...");
					Thread.sleep(1000);
					currentFloorId--;
					System.out.println("Elevator " + id + " is at Floor: " + getCurrentFloorId());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
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
