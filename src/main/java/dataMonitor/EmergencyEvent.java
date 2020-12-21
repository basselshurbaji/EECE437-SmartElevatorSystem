package dataMonitor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmergencyEvent implements Loggable {
	static 	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String type;
	String time;
	
	public EmergencyEvent(String type, String time) {
		this.type = type;
		this.time = time;
	}
	@Override
	public String toLogData() {   
	    return gson.toJson(this);
	}
	
}
