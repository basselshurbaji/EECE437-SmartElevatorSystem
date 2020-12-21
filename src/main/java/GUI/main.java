package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import GUI.userRequest;
import userInteraction.UserPriority;
import elevator.Elevator;
import floor.*;

public class main {
	private static ArrayList<Floor> floorList = new ArrayList<Floor>();
	private static ArrayList<Elevator> elevatorArray = new ArrayList<Elevator>();
	private static ArrayList<Request> requestArray = new ArrayList<Request>();
	private static ArrayList<userRequest> userRequestArray = new ArrayList<userRequest>();
	
	
	public static void JsonParser(String fileName) {
		
		Scanner scanner = null;
		int counter =0;
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
		   String line = scanner.nextLine();
		   if (counter ==0) {
			   Floor[] f = new Gson().fromJson(line, Floor[].class);
//			   System.out.println("First Floor ID: " + f[0].getId());
			   counter++;
		   }
		   else if(counter ==1) {
			   Elevator[] e = new Gson().fromJson(line, Elevator[].class);
//			   System.out.println("First Elevator Current Floor: " + e[0].getCurrentFloorId());
			   counter++;
		   }
		   else if(counter ==2) {
			   Request[] r = new Gson().fromJson(line, Request[].class);
//			   System.out.println("Fist Request Type: " + r[0].getType());
			   counter++;
		   }
		   else {
			   userRequest[] ur = new Gson().fromJson(line, userRequest[].class);
//			   System.out.println("First userRequest Priority: " + ur[0].getPriority());
			   counter=0;
		   }
		}
	}
	
	public static void main(String[] args) {
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Gson gson = new Gson();
		
		//TESTS
//		floorList.add(new Floor(1, FloorPriority.HIGH));
//		floorList.add(new Floor(2, FloorPriority.LOW));
//		
//		elevatorArray.add(new Elevator(1, 100, 44));
//		elevatorArray.add(new Elevator(2, 200, 5));
//		
//		requestArray.add(new Request('E', 10));
//		requestArray.add(new Request('S', 20));
//		
//		userRequestArray.add(new userRequest(1, 2, 5, UserPriority.HIGH, 'U', 10));
//		userRequestArray.add(new userRequest(2, 5, 2, UserPriority.NORMAL, 'U', 10));
//		
		String FloorJsonStr = gson.toJson(floorList);
		String ElevatorJsonStr = gson.toJson(elevatorArray);
		String RequestJsonStr = gson.toJson(requestArray);
		String UserRequesJsonStr = gson.toJson(userRequestArray);
//		System.out.println(jsonStr); 
		
		//writing to a json file using Gson
		FileWriter writer = null;
		try {
			writer = new FileWriter("data.json");
			writer.write(FloorJsonStr + "\n");
			writer.write(ElevatorJsonStr + "\n");
			writer.write(RequestJsonStr + "\n");
			writer.write(UserRequesJsonStr + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		JsonParser("data.json");
	}
}
