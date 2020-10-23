package floor;

public class Floor {
	private int id;
	private FloorPriority priority;
	private boolean restricted;
	
	//Constructor
	public Floor(int id, FloorPriority priority) {
		this.id = id;
		this.priority = priority;
		this.restricted = false;
	}
	
	//Getters & Setters
	public FloorPriority getPriority() {
		return priority;
	}

	public void setPriority(FloorPriority priority) {
		this.priority = priority;
	}

	public boolean isRestricted() {
		return restricted;
	}

	public void setRestricted(boolean restricted) {
		this.restricted = restricted;
	}

	public int getId() {
		return id;
	}
	
}
