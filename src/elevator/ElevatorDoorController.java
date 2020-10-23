package elevator;

public class ElevatorDoorController {
	Elevator elevator;
	
	public ElevatorDoorController(Elevator elevator) {
		this.elevator = elevator;
	}
	
	public void openDoor() {
		elevator.setDoorStatus(ElevatorDoorStatus.OPENED);
	}
	
	public void closeDoor() {
		elevator.setDoorStatus(ElevatorDoorStatus.CLOSED);
	}
}
