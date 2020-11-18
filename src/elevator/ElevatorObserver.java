package elevator;
/*
 * STATUS: In Progress/Used in current implementation
 * 
 * This is an interface for all components interested in observing elevator events.
 * More functions might be added.
 */
public interface ElevatorObserver {
	public void elevatorDidFinishTask(Elevator elevator);
}
