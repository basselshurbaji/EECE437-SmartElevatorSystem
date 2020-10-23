package dataMonitor;

import java.util.HashMap;

public class DataMonitor {
	private HashMap<Integer, ElevatorEvent> events;
	
	public DataMonitor() {
		this.events = new HashMap<>();
	}
	
	public void saveEvent(ElevatorEvent event) {
		events.put(event.getId(), event);
		System.out.println("Event ID: " + event.getId() + "\n" +
							"Elevator ID: " + event.getElevatorId() + "\n" + 
							"Source Floor: " + event.getSourceFloor() + "\n" + 
							"Destination Floor: " + event.getDestiniationFloor() + "\n" +
							"Start Time: " + event.getStartTime() + "\n" +
							"End Time: " + event.getEndTime() + "\n");
	}
	
	public void deleteEvent(int eventId) {
		events.remove(eventId);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<Integer, ElevatorEvent> getHistory() {
		 return (HashMap<Integer, ElevatorEvent>) events.clone();
	}
}