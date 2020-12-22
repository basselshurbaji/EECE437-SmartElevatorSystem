package GUI;

/**
 * STATUS: In-use in our system
 * This class is used to model requests from the system/users which should be handled 
 * by the Elevator System
 */

public class Request {
	private char type;
	private int time;
	
	
	/**
	 * Constructor for the Request class
	 * @param type Character associated with the type of request
	 * @param time Time associated with request
	 */
	
	public Request(char type, int time) {
		this.time = time;
		this.type = type;
	}
	
	/**
	 * @return Returns time associated with request
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * @return Returns type associated with request
	 */
	public char getType() {
		return type;
	}
	
	/**
	 * @return Set time associated with request
	 */
	public void setTime(int t) {
		this.time = t;
	}
	
	/**
	 * @return Set type associated with request
	 */
	public void setType(char T) {
		this.type = T;
	}
}

