package dataMonitor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class defines the structure of Emergency events logged by the DataMonitor,implements Loggable.
 * 
 */
public class EmergencyEvent implements Loggable {
	/**
	 * Shared instance of the GSON builder
	 */
	static 	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	/**
	 * Emergency type
	 */
	String type;
	/**
	 * Time associated with emergency
	 */
	String time;
	
	/**
	 * Public constructor for EmergencyEvent.
	 * @param type Type of emergency
	 * @param time Time associated with emergency
	 */
	public EmergencyEvent(String type, String time) {
		this.type = type;
		this.time = time;
	}
	@Override
	/**
	 * Converts EmergencyEvent to Json
	 * @return String Data 
	 */
	public String toLogData() {   
	    return gson.toJson(this);
	}
	
}
