package elevator;
/**
 * This is an interface for all components interested in observing elevator events.
 */
public interface ElevatorObserver {
	/**
	 * interface function that notify when an elevator finishes a task
	 * @param elevator Elevator class instance
	 */
	public void elevatorDidFinishTask(Elevator elevator);
}
