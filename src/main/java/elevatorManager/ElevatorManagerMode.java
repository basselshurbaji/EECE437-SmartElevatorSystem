package elevatorManager;

/**
 * Enum ElevatorManagerMode is used to indicate the mode of the elevator manager.
 * Different elevator modes affects the priority system and behavior of the elevator manager.  
 *
 */
public enum ElevatorManagerMode {
	/**
	 * Specifies that the Elevator operates normally
	 */
	NORMAL,
	/**
	 * Specifies that the Elevator operates in emergency mode
	 */
	EMERGENCY
}
