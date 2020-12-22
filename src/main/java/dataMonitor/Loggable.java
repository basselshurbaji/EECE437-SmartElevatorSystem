package dataMonitor;

/**
 * This interface defines the class Loggable for ElevatorEvent and EmergencyEvent.
 */
public interface Loggable {
	/**
	 * Converts Data to Json
	 * @return Returns string
	 */
	public String toLogData();
}
