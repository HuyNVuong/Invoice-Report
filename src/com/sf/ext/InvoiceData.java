package com.sf.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 16 methods in total, add more if required.
 * Do not change any method signatures or the package name.
 * 
 */

import org.apache.log4j.BasicConfigurator;

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
	// bonus
	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InvoiceData.class);
	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		/** TODO*/
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		// Delete from email
		String emailQuery = "DELETE FROM Email";
		try {
			ps = conn.prepareStatement(emailQuery);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
		// Delete all Person after get rid of Email
		String personQuery = "DELETE FROM Person";
		try {
			ps = conn.prepareStatement(personQuery);
			int delete = ps.executeUpdate();
			if (delete > 0) {
				System.out.println("Removes Every Peron Record From the Person Succeed");
			} else {
				System.out.println("Empty Table.");
			}
		} catch (SQLException e) {
			log.error(e);
		}
		try {
			if(ps != null && !ps.isClosed())
				ps.close();
		} catch (SQLException e) {
			log.error(e);
		}
		DatabaseInfo.closeConnection(conn);
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
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int addressID = 0;
		try {
			// Check that address is there
			String addressQuery = "SELECT AddressID FROM Address;";
			ps = conn.prepareStatement(addressQuery);
			rs = ps.executeQuery();
			// if the address is not exist, then insert a new one in
			if (!rs.next()) {
				addressQuery  = "INSERT INTO Address (Street, City, State, Zip, Country) "
						+ "VALUES (?,?,?,?,?)";
				ps = conn.prepareStatement(addressQuery);
				ps.setString(1, street);
				ps.setString(2, city);
				ps.setString(3, state);
				ps.setString(4, zip);
				ps.setString(5, country);
				ps.executeUpdate();
				ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
				rs = ps.executeQuery();
				rs.next();
				addressID = rs.getInt("LAST_INSERT_ID()");
			} else {
				addressID = rs.getInt("addressID");
			}
			// Insert into the person
			String personQuery = "INSERT INTO Persons (personCode, personFirstName, personLastName, PersonAddressID) values (?,?,?,?)";
			ps = conn.prepareStatement(personQuery);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt(4, addressID);
			ps.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException le) {
			log.error(le);
		} catch (SQLException e) {
			log.error(e);
		}
		try {
			if(rs != null && !rs.isClosed())
				rs.close();
			if(ps != null && !ps.isClosed())
				ps.close();
		} catch (SQLException sqle) {
			
			log.error(sqle);
			
		}

        // Close connection
		DatabaseInfo.closeConnection(conn);	
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		/** TODO*/
	}

	/**
	 * 4. Method that removes every member record from the database
	 */
	public static void removeAllMembers() {
		/** TODO*/
	}
	/**
	 * 5. Method to add a member record to the database with the provided data
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
	public static void addMember(String memberCode, String memberType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
		/** TODO*/
	}
	
	/**
	 * 6. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		/** TODO*/
	}

	/**
	 * 7. Adds a day-pass record to the database with the provided data.
	 */
	public static void addDayPass(String productCode, String dateTime, String street, String city, String state, String zip, String country, double pricePerUnit) {
		/** TODO*/
	}

	/**
	 * 8. Adds a year-long-pass record to the database with the provided data.
	 */
	public static void addYearPass(String productCode, String StartDate, String EndDate,String street, String city, String state, String zip, String country, String name, double pricePerUnit) {
		/** TODO*/
	}

	/**
	 * 9. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
        /** TODO*/
	}

	/**
	 * 10. Adds an equipment rental record to the database with the provided data.
	 */
	public static void addRental(String productCode, String name, double cost) {
        /** TODO*/
	}

	/**
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
        /** TODO*/
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String memberCode, String personalTrainerCode, String invoiceDate) {
		/** TODO*/
	}

	/**
	 * 13. Adds a particular day-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addDayPassToInvoice(String invoiceCode, String productCode, int quantity) {
		/** TODO*/
	}

	/**
	 * 14. Adds a particular year-long-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addYearPassToInvoice(String invoiceCode, String productCode, int quantity) {
		/** TODO*/
	}

     /**
     * 15. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: membershipCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
    	/** TODO*/
    }
	
    /**
     * 16. Adds a particular equipment rental (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     * NOTE: membershipCode may be null
     */
    public static void addRentalToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
    	/** TODO*/
    }
    public static void main (String args[]) {
    	BasicConfigurator.configure();
    	InvoiceData i = new InvoiceData();
    	InvoiceData.addPerson("nwdoc5", "Huy", "Vuong", "246 street", "Lincoln", "Ne", "68503", "USA");
    }
   
}
