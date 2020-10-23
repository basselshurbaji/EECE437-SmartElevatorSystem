package elevatorManager;

public class SystemController {
	private ElevatorManager elevatorManager;
	
	public SystemController() {
		this.elevatorManager = ElevatorManager.getInstance();
	}
	
	public void shutDown() {
		elevatorManager.setStatus(ElevatorManagerStatus.OFF);
	}
	
	public void startSystem() {
		elevatorManager.setStatus(ElevatorManagerStatus.ON);
	}
	
	public void restartSystem() {
		elevatorManager.setStatus(ElevatorManagerStatus.OFF);
		elevatorManager.setStatus(ElevatorManagerStatus.ON);
	}
}
