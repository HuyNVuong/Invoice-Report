package com.sf.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Persons;
import entities.PersonsAddress;
/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 16 methods in total, add more if required.
 * Do not change any method signatures or the package name.
 * 
 */

//import com.sf.model.DayMembership;
//import com.sf.model.Invoice;
//import com.sf.model.Member;
//import com.sf.model.Membership;
//import com.sf.model.Parkingpass;
//import com.sf.model.Person;
//import com.sf.model.Product;
//import com.sf.model.RentalEquipment;
//import com.sf.model.YearLongMembership;

public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		String update = "TRUNCATE TABLE Persons";

		Connection conn = DatabaseInfo.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(update);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country) {

		// FIXME how to test?

		// FIXME does my address/person check actually work/make sense? Is there a better way to do it?
		int foreignKeyID;
		String checkAddressQuery = "SELECT ?, ?, ?, ?, ? FROM Address";
		Connection conn1 = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn1.prepareStatement(checkAddressQuery);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ResultSet rs = ps.executeQuery();
			ps.close();
			while (rs.next()) {
				if (rs.getString("Street").equalsIgnoreCase(street) && rs.getString("City").equalsIgnoreCase(city)
						&& rs.getString("State").equalsIgnoreCase(state) && rs.getString("Zip").equalsIgnoreCase(zip)
						&& rs.getString("Country").equalsIgnoreCase(country)) {
					// FIXME prevent address insertion
				} else {
					String insertAddressQuery = "INSERT INTO Address (Street, City, State, Zip, Country) VALUES (?,?,?,?,?);";
					Connection conn2 = DatabaseInfo.getConnection();
					try {
						PreparedStatement ps2 = conn2.prepareStatement(insertAddressQuery);
						ps2.setString(1, street);
						ps2.setString(2, city);
						ps2.setString(3, state);
						ps2.setString(4, zip);
						ps2.setString(5, country);
						ps2.executeUpdate();
						ps2.close();
						
						ps = conn2.prepareStatement("SELECT LAST_INSERT_ID()");
						ResultSet rs2 = ps.executeQuery();
						rs2.next();
						foreignKeyID = rs2.getInt("LAST_INSERT_ID()");
						conn2.close();
						rs2.close();

					} catch (SQLException e) {
						System.out.println("SQLException: ");
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
			rs.close(); // FIXME CAN I HAVE A CONN/PS/RS inside of another conn/ps/rs?
			conn1.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		String checkPersonQuery = "SELECT ?,?,? FROM Persons";
		Connection conn4 = DatabaseInfo.getConnection();
		try {
		PreparedStatement ps = conn4.prepareStatement(checkPersonQuery);
		ps.setInt(1, Integer.parseInt(personCode));
		ps.setString(2, firstName);
		ps.setString(3, lastName);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			if (rs.getInt("PersonCode") == Integer.parseInt(personCode) && rs.getString("PersonFirstName").equalsIgnoreCase(firstName) 
					&& rs.getString("PersonLastName").equalsIgnoreCase(lastName)) {
				// FIXME prevent Person insertion below
			}
			else {
				String insertPersonQuery = "INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName) VALUES (?,?,?) WHERE AddressID = ?;";
				Connection conn3 = DatabaseInfo.getConnection();
				try {
					// FIXME check if student already exists? --> SELECT statement to retrieve
					// AddressID from Address table?
					PreparedStatement ps1 = conn3.prepareStatement(insertPersonQuery);
					ps1.setInt(1, Integer.parseInt(personCode));
					ps1.setString(2, firstName);
					ps1.setString(3, lastName);
					//ps1.setInt(4, foreignKeyID); FIXME can't access foreignKeyID
					ps1.executeUpdate();
					ps1.close();
					conn3.close();

				} catch (SQLException e) {
					System.out.println("SQLException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		rs.close(); //FIXME conn inside conn
		ps.close();
		conn4.close();
		
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		/** TODO */
	}

	/**
	 * 4. Method that removes every member record from the database
	 */
	public static void removeAllMembers() {
		/** TODO */
	}

	/**
	 * 5. Method to add a member record to the database with the provided data
	 * 
	 * @param memberCode
	 * @param memberType
	 * @param primaryContactPersonCode
	 * @param name
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addMember(String memberCode, String memberType, String primaryContactPersonCode, String name,
			String street, String city, String state, String zip, String country) {
		/** TODO */
	}

	/**
	 * 6. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		/** TODO */
	}

	/**
	 * 7. Adds a day-pass record to the database with the provided data.
	 */
	public static void addDayPass(String productCode, String dateTime, String street, String city, String state,
			String zip, String country, double pricePerUnit) {
		/** TODO */
	}

	/**
	 * 8. Adds a year-long-pass record to the database with the provided data.
	 */
	public static void addYearPass(String productCode, String StartDate, String EndDate, String street, String city,
			String state, String zip, String country, String name, double pricePerUnit) {
		/** TODO */
	}

	/**
	 * 9. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
		/** TODO */
	}

	/**
	 * 10. Adds an equipment rental record to the database with the provided data.
	 */
	public static void addRental(String productCode, String name, double cost) {
		/** TODO */
	}

	/**
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		/** TODO */
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String memberCode, String personalTrainerCode,
			String invoiceDate) {
		/** TODO */
	}

	/**
	 * 13. Adds a particular day-pass (corresponding to <code>productCode</code> to
	 * an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given number of units
	 */

	public static void addDayPassToInvoice(String invoiceCode, String productCode, int quantity) {
		/** TODO */
	}

	/**
	 * 14. Adds a particular year-long-pass (corresponding to
	 * <code>productCode</code> to an invoice corresponding to the provided
	 * <code>invoiceCode</code> with the given begin/end dates
	 */
	public static void addYearPassToInvoice(String invoiceCode, String productCode, int quantity) {
		/** TODO */
	}

	/**
	 * 15. Adds a particular ParkingPass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given number of quantity. NOTE: membershipCode may be null
	 */
	public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity,
			String membershipCode) {
		/** TODO */
	}

	/**
	 * 16. Adds a particular equipment rental (corresponding to
	 * <code>productCode</code> to an invoice corresponding to the provided
	 * <code>invoiceCode</code> with the given number of quantity. NOTE:
	 * membershipCode may be null
	 */
	public static void addRentalToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
		/** TODO */
	}

	public static void main(String[] args) {

	}

}
