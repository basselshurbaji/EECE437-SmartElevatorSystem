package GUI;

public class Request {
	private char type;
	private int time;
	
	
	//Constructor
	public Request(char type, int time) {
		this.time = time;
		this.type = type;
	}
	
	//Getters
	public int getTime() {
		return time;
	}
	public char getType() {
		return type;
	}
	
	//Setters
	public void setTime(int t) {
		this.time = t;
	}
	public void setType(char T) {
		this.type = T;
	}
}

