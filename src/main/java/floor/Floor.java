package floor;

/*
 * STATUS: In Progress/Used in our system.
 * This class is used to represent the different floors in the elevator system. 
 * Every floor has a unique id and can be assigned a specific priority.
 */
public class Floor {
	private int id;
	private FloorPriority priority;
	
	//Constructor
	public Floor(int id, FloorPriority priority) {
		this.id = id;
		this.priority = priority;
	}
	
	//Getters & Setters
	public FloorPriority getPriority() {
		return priority;
	}

	public void setPriority(FloorPriority priority) {
		this.priority = priority;
	}

	public int getId() {
		return id;
	}
	
}
