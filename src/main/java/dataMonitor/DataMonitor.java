package dataMonitor;

import java.util.HashMap;

/*
 * STATUS: Incomplete/Not used in current implementation
 * This component is responsible for logging all events related to elevator traffic.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * System Data Monitor
 * This component monitor system events and append the data to log.txt
 */
public class DataMonitor {
	/**
	 * Shared Instance for our dataMonitor class
	 */
	static DataMonitor sharedInstance;
	/**
	 * file Writer instance
	 */
	FileWriter fw;
	/**
	 * Buffered Writer instance
	 */
	BufferedWriter bw;
	/**
	 * Print Writer instance
	 */
	PrintWriter pw;
	
	/**
	 * Private constructor for DataMonitor.
	 * @param  path Path used to create our data monitor files
	 */
	private DataMonitor(String path) {
		try {
			this.fw = new FileWriter(path, true);
			this.bw = new BufferedWriter(fw);
			this.pw = new PrintWriter(bw);
			
			System.out.println("DataMonitor Initialized Successfully");
		} catch (Exception e) {
			System.out.println("Error Initializing DataMonitor: " + e.getMessage());
		}
	}
	
	/**
	 * Public static DataMonitor singleton Instance Getter.
	 * @return DataMonitor Instance
	 */
	public static DataMonitor getInstance() {
		if(sharedInstance == null) {
			sharedInstance = new DataMonitor("log.txt");
		}
		return sharedInstance;
	}
	
	/**
	 * function that handles data logging into file
	 * Parameter of type T extends Loggable
	 * @param data Class of Type T used to represent data
	 * @param <T> Parametrization of data type
	 */
	public <T extends Loggable> void log(T data) {
		pw.println(data.toLogData());
		pw.flush();
		System.out.println("Data Successfully appended into file");
	}
	
	/**
	 * Function that handles the closure of the Data fil
	 * 
	 */
	public void close() {
		try {
			pw.close();
			bw.close();
			fw.close();
			System.out.println("Data Monitor Closed Successfully");
		} catch(Exception e) {
			System.out.println("Error Closing Monitor: " + e.getMessage());
		}
	}
}






