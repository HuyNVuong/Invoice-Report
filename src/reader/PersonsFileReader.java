/* Read in reader file and format its printout */
package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import entities.Persons;
import entities.PersonsAddress;


public class PersonsFileReader {
	
	public ArrayList<Persons> readPersons() {
	Scanner sc = null;
		
		try {
			sc = new Scanner(new File("data/Persons.dat"));
			sc.nextLine(); // reads the number of records from the first line
			
			// This Person ArrayList stores the Person objects 
			ArrayList<Persons> personsList = new ArrayList<Persons>();
			
			while(sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				String data[] = line.split(";"); // tokenizes the line and stores in a string array 
								// Stores the 4 array elements of each line into strings
				String personCode = data[0];
				String name = data[1];
				String dataName [] = name.split(",");
				String firstName = dataName[0];
				String lastName = dataName[1];
				String dataAddress = data[2];
				String dataAddressSplit [] = dataAddress.split(",");
				String street = dataAddressSplit[0];
				String city =  dataAddressSplit[1];
				String state =  dataAddressSplit[2];
				String zip =  dataAddressSplit[3];
				String country = dataAddressSplit[4];
				// Creates an Address object
				PersonsAddress address = new PersonsAddress (street, city, state, zip, country);
				String [] emails  = null;
				if(data.length == 4) {
				emails =  data[3].split(",");
				}
				// Creates a Person object
				Persons persons = new Persons(personCode, firstName, lastName, address, emails);
				
				// Adds the Person object into Person ArrayList
				personsList.add(persons);
			}
			sc.close();
			return personsList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}	
	}
}