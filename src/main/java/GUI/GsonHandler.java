package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;

import elevator.Elevator;
import floor.Floor;

/**
 * STATUS: In-use in our system
 * This class is used to read from/setup the Elevator System configuration file.
 */

public class GsonHandler {
	
	/**
	 * Generates a JSON configuration file based on a DataObject instance
	 * @param DO DataObject instance
	 * @return Returns boolean indicating whether the JSON file has been generated successfully
	 */
	public static boolean GenerateJson(DataObject DO) {
		
		Gson gson = new Gson();
		
		String FloorJsonStr = gson.toJson(DO.getFloorArrayList());
		String ElevatorJsonStr = gson.toJson(DO.getElevatorArrayList());
		String RequestJsonStr = gson.toJson(DO.getRequestArrayList());
		String UserRequesJsonStr = gson.toJson(DO.getUserRequestArrayList());
		
		FileWriter writer = null;
		try {
			writer = new FileWriter("data.json");
			writer.write(FloorJsonStr + "\n");
			writer.write(ElevatorJsonStr + "\n");
			writer.write(RequestJsonStr + "\n");
			writer.write(UserRequesJsonStr + "\n");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(writer != null) {
				try {
					writer.close();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Reading from a JSON configuration file, and returning a DataObject instance
	 * @param fileName Takes filename associated with the JSON file we'd like to read from
	 * @return Returns DataObject instance containing the configuration data from the JSON file
	 */
	
	public static DataObject JsonParser(String fileName) {
		
		Scanner scanner = null;
		int counter =0;
		ArrayList<Floor> floorArrayList = new ArrayList<Floor>();
		ArrayList<Elevator> elevatorArrayList = new ArrayList<Elevator>();
		ArrayList<Request> requestArrayList= new ArrayList<Request>();
		ArrayList<userRequest> userRequestArrayList = new ArrayList<userRequest>();
		
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Configuration File Not Found!");
		}
		while (scanner.hasNextLine()) {
		   String line = scanner.nextLine();
		   if (counter ==0) {
//			   Floor[] f = new Gson().fromJson(line, Floor[].class);
			   floorArrayList = new ArrayList<Floor>(Arrays.asList(new Gson().fromJson(line, Floor[].class)));
//			   System.out.println("First Floor ID: " + f[0].getId());
			   counter++;
		   }
		   else if(counter ==1) {
//			   Elevator[] e = new Gson().fromJson(line, Elevator[].class);
			   elevatorArrayList = new ArrayList<Elevator>(Arrays.asList(new Gson().fromJson(line, Elevator[].class)));
//			   System.out.println("First Elevator Current Floor: " + e[0].getCurrentFloorId());
			   counter++;
		   }
		   else if(counter ==2) {
//			   Request[] r = new Gson().fromJson(line, Request[].class);
			   requestArrayList = new ArrayList<Request>(Arrays.asList(new Gson().fromJson(line, Request[].class)));
//			   System.out.println("Fist Request Type: " + r[0].getType());
			   counter++;
		   }
		   else {
//			   userRequest[] ur = new Gson().fromJson(line, userRequest[].class);
			   userRequestArrayList = new ArrayList<userRequest>(Arrays.asList(new Gson().fromJson(line, userRequest[].class)));
//			   System.out.println("First userRequest Priority: " + ur[0].getPriority());
			   counter=0;
		   }
		}
		
		return(new DataObject(floorArrayList, elevatorArrayList, requestArrayList, userRequestArrayList));
	}
}
