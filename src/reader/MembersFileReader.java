/* Read in Members file and design its print out format */
package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.General;
import entities.Members;
import entities.MembersAddress;

public class MembersFileReader {
	
	public ArrayList<Members> readMembers() {
	Scanner sc = null;
		
		try {
			sc = new Scanner(new File("data/Members.dat"));
			sc.nextLine(); // reads the number of records from the first line
			
			// This Members ArrayList stores the Members objects 
			ArrayList<Members> membersList = new ArrayList<Members>();
			while(sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				String data[] = line.split(";"); // tokenizes the line and stores in a string array 
				
				// Stores the  array elements of each line into strings
				String memberCode = data[0];
				String type = data[1];
				String primaryContact = data[2];
				String name = data[3];
				String dataAddress = data[4];
				String dataAddressSplit [] = dataAddress.split(",");
				String street = dataAddressSplit[0];
				String city = dataAddressSplit[1];
				String state = dataAddressSplit[2];
				String zip = dataAddressSplit[3];
				String country = dataAddressSplit[4];
				// Creates an Address object
				MembersAddress address = new MembersAddress(street, city, state, zip, country);
				
				// Creates a Members object  // updated: changes made for phase 2
				Members members = new Members(memberCode, type, primaryContact, name, address);
				
				// Adds the Members object into Person ArrayList
				membersList.add(members);
			}
			sc.close();
			return membersList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}	
	}
}
