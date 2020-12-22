package GUI;

/**
 * This class is used to model requests from the system/users which should be handled 
 * by the Elevator System
 */

public class Request {
	/**
	 * Type of requests
	 */
	private char type;
	/**
	 * Time associated with request
	 */
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
	 * set time associated with request
	 * @param t Time passed
	 */
	public void setTime(int t) {
		this.time = t;
	}
	
	/**
	 * Set type associated with request
	 * @param T Type associated with request
	 */
	public void setType(char T) {
		this.type = T;
	}
}

