package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import com.revature.dao.implementations.ApartmentDAOGenericImpl;
import com.revature.dao.implementations.CarsDAOGenericImpl;
import com.revature.dao.implementations.PetsDAOGenericImpl;
import com.revature.dao.implementations.ResidentDAOGenericImpl;
import com.revature.dao.implementations.ResidentDAOImpl;
import com.revature.dao.interfaces.ResidentDAO;
import com.revature.models.Resident;
import com.revature.services.ConnectionService;

public class App {
	
	static ResidentDAOGenericImpl residentDao = new ResidentDAOGenericImpl();
	static PetsDAOGenericImpl petDao = new PetsDAOGenericImpl();
	static CarsDAOGenericImpl carDao = new CarsDAOGenericImpl();
	static ApartmentDAOGenericImpl apartmentDao = new ApartmentDAOGenericImpl();
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Apartment Management Database!");
		System.out.println("Type 1 for Apartment Information or 2 for Administrative Services");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		if(userInput == "1") {
			System.out.println("");
		}
		else if(userInput == "2") {
			System.out.println("Type 1 to move in a resident or 2 to move out a resident");
			userInput = scanner.nextLine();
			if(userInput == "1") {
				//System.out.println("Type the id of the apartment that people are moving into");
				//Set id
				List<Resident> residentList;
				System.out.println("Type the id number, first name, and last name of each resident. Type DONE when you're done.");
				while (userInput != "DONE") {
					//Split string to add id first and last name
					//residentList.add();
					userInput = scanner.nextLine();
				}
				//Resident resident = residentDao.set(apartment, residents, pets);
			}
		}
	}
}
