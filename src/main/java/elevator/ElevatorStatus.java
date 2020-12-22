package elevator;

/**
 * Enum ElevatorStatus is used to indicate the elevator status.
 * either handling request, stationary, up or down
 */
public enum ElevatorStatus {
	/**
	 * Specifies that the Elevator is currently handling a request
	 */
	HANDLING_REQUEST,
	/**
	 * Specifies that the Elevator is currently stationary
	 */
	STATIONARY,
	/**
	 * Specifies that the Elevator is currently going up
	 */
	UP,
	/**
	 * Specifies that the Elevator is currently going down
	 */
	DOWN
}
