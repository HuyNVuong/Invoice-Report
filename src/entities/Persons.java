/* Persons classes that provides necessary variable for Persons */

package entities;

import java.util.ArrayList;

public class Persons {
	private String personCode;
	private String firstName;
	private String lastName;
	private PersonsAddress address;
	private String [] email;
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public PersonsAddress getAddress() {
		return address;
	}
	public void setAddress(PersonsAddress address) {
		this.address = address; 
	}
	public String [] getEmail() {
		return email;
	}
	public void setEmail(String [] email) {
		this.email = email;
	}
	public Persons(String personCode, String firstName, String lastName, PersonsAddress address,
			String [] email) {
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
	}
	@Override
	public String toString() {
		return  firstName + ", "  + lastName;
	}
	

	
	
}