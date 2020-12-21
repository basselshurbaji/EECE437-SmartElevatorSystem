package dataMonitor;

import java.util.HashMap;

/*
 * STATUS: Incomplete/Not used in current implementation
 * This component is responsible for logging all events related to elevator traffic.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DataMonitor {
	static DataMonitor sharedInstance;
	FileWriter fw;
	BufferedWriter bw;
	PrintWriter pw;
	
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
	
	public static DataMonitor getInstance() {
		if(sharedInstance == null) {
			sharedInstance = new DataMonitor("log.txt");
		}
		return sharedInstance;
	}
	
	public <T extends Loggable> void log(T data) {
		pw.println(data.toLogData());
		pw.flush();
		System.out.println("Data Successfully appended into file");
	}
	
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






