package floor;

/**
 * This class is used to represent the different floors in the elevator system. 
 * Every floor has a unique id and can be assigned a specific priority.
 */
public class Floor {
	private int id;
	private FloorPriority priority;
	
	/**
	 * Public constructor of floor.
	 * It initiates the floor's id and priority.
	 */
	public Floor(int id, FloorPriority priority) {
		this.id = id;
		this.priority = priority;
	}
	
	/**
	 * @return returns the floor's priority.
	 */
	public FloorPriority getPriority() {
		return priority;
	}

	/**
	 * Sets the floor's priority.
	 */
	public void setPriority(FloorPriority priority) {
		this.priority = priority;
	}

	/**
	 * @return returns the floor's id.
	 */
	public int getId() {
		return id;
	}
	
}
