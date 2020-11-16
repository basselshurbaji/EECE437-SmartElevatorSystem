package floor;

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
