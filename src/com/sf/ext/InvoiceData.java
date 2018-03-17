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

		Connection conn = DatabaseInfo.getConnection();

		String updateDayMembership = "TRUNCATE TABLE DayMembership;";
		try {
			PreparedStatement ps = conn.prepareStatement(updateDayMembership);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// String updateAddress = "TRUNCATE TABLE Address;";
		// try {
		// PreparedStatement ps = conn.prepareStatement(updateAddress);
		// ps.executeUpdate();
		// ps.close();
		//
		// } catch (SQLException e) {
		// System.out.println("SQLException: ");
		// e.printStackTrace();
		// throw new RuntimeException(e);
		// }

		String updateInvoiceProducts = "TRUNCATE TABLE InvoiceProducts;";
		try {
			PreparedStatement ps = conn.prepareStatement(updateInvoiceProducts);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String updateInvoice = "TRUNCATE TABLE Invoice;";
		try {
			PreparedStatement ps = conn.prepareStatement(updateInvoice);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String updateEmail = "TRUNCATE TABLE Email;";
		try {
			PreparedStatement ps = conn.prepareStatement(updateEmail);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String updatePersons = "TRUNCATE TABLE Persons;";
		try {
			PreparedStatement ps = conn.prepareStatement(updatePersons);
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
	 * COMPLETED 2. Method to add a person record to the database with the provided
	 * data.
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

		int foreignKeyID = -1;
		String checkAddressQuery = "SELECT * FROM Address WHERE (Street = ? AND City = ? AND State = ? AND Zip = ? AND Country = ?);";
		Connection conn1 = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn1.prepareStatement(checkAddressQuery);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				foreignKeyID = rs.getInt("AddressID");
			} else {
				String insertAddressQuery = "INSERT INTO Address (Street, City, State, Zip, Country) VALUES (?,?,?,?,?);";
				try {
					PreparedStatement ps2 = conn1.prepareStatement(insertAddressQuery);
					ps2.setString(1, street);
					ps2.setString(2, city);
					ps2.setString(3, state);
					ps2.setString(4, zip);
					ps2.setString(5, country);
					ps2.executeUpdate();
					ps2.close();

					ps = conn1.prepareStatement("SELECT LAST_INSERT_ID() AS LID;");
					ResultSet rs2 = ps.executeQuery();
					rs2.next();
					foreignKeyID = rs2.getInt("LID");

					rs2.close();
				} catch (SQLException e) {
					System.out.println("SQLException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			conn1.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String checkPersonQuery = "SELECT * FROM Persons WHERE (PersonCode = ? AND PersonFirstName = ? AND PersonLastName = ?);";
		Connection conn2 = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn2.prepareStatement(checkPersonQuery);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("error: person already exists");
			} else {
				String insertPersonQuery = "INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) VALUES (?,?,?,?);";
				try {
					PreparedStatement ps1 = conn2.prepareStatement(insertPersonQuery);
					ps1.setString(1, personCode);
					ps1.setString(2, firstName);
					ps1.setString(3, lastName);
					ps1.setInt(4, foreignKeyID);
					ps1.executeUpdate();

					ps1.close();
				} catch (SQLException e) {
					System.out.println("SQLException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			rs.close();
			ps.close();
			conn2.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * COMPLETED 3. Adds an email record corresponding person record corresponding
	 * to the provided <code>personCode</code>
	 * 
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {

		int foreignKeyID = -1;
		String checkPersonCodeQuery = "SELECT PersonID FROM Persons WHERE PersonCode = ?;";
		Connection conn = DatabaseInfo.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(checkPersonCodeQuery);
			ps.setString(1, personCode);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				foreignKeyID = rs.getInt("PersonID");
			} else {
				System.out.println("error: person doesn't exist");
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String checkEmailQuery = "SELECT * FROM Email WHERE Email = ?;";
		Connection conn1 = DatabaseInfo.getConnection();

		try {
			PreparedStatement ps = conn1.prepareStatement(checkEmailQuery);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("error: email already exists");
			} else {
				String insertEmailQuery = "INSERT INTO Email (Email, PersonID) VALUES (?,?);";
				try {
					PreparedStatement ps2 = conn1.prepareStatement(insertEmailQuery);
					ps2.setString(1, email);
					ps2.setInt(2, foreignKeyID);
					ps2.executeUpdate();

					ps2.close();
					rs.close();
					conn1.close();
				} catch (SQLException e) {
					System.out.println("SQLException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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
		// addPerson("33ds5", "Jaime", "Lannister", "1 Casterly Rock", "Lannisport", "n/a", "n/a", "Westeros"); // WORKS
		// addEmail("944c", "test@test.com"); // WORKS
		
	}

}
