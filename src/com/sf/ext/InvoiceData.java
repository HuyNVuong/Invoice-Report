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
import writer.InvoiceConsoleOutput;
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
		// Delete from Invoice
		String InvoiceQuery = "DELETE FROM Invoice";
		try {
			ps = conn.prepareStatement(InvoiceQuery);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
		// Delete from email
		String emailQuery = "DELETE FROM Email";
		try {
			ps = conn.prepareStatement(emailQuery);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
		// Delete all Person after get rid of Email
		String personQuery = "DELETE FROM Persons";
		try {
			ps = conn.prepareStatement(personQuery);
			int delete = ps.executeUpdate();
			if (delete > 0) {
				System.out.println("Removes Every Person Record From the Person Succeed");
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
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// Select PersonID  from Person
			String personQuery = "SELECT PersonID FROM Persons WHERE PersonCode = ?";
			ps = conn.prepareStatement(personQuery);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				int personID = rs.getInt("PersonID");
				// Insert into Email 
				String emailQuery = "INSERT INTO Email (Email, PersonID) value (?,?);";
				ps = conn.prepareStatement(emailQuery);
				ps.setString(1, email);
				ps.setInt(2, personID);
				ps.executeUpdate();
			}
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
		// Close connnection
		DatabaseInfo.closeConnection(conn);
	}
	/**
	 * 4. Method that removes every member record from the database
	 */
	public static void removeAllMembers() {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		// Remove all Invoice of a Member
		String InvoiceQuery = "DELETE FROM Invoice";
		try {
			ps = conn.prepareStatement(InvoiceQuery);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
		// Remove all Member
		String memberQuery = "DELETE FROM Members";
		try {
			ps = conn.prepareStatement(memberQuery);
			int delete = ps.executeUpdate();
			if (delete > 0) {
				System.out.println("Removes Every Member Record From the Members Succeed");
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
			String memberQuery = "INSERT INTO Members (MemberCode, MemberType, MemberContact, MemberName, MemberAddressID) values (?,?,?,?,?)";
			ps = conn.prepareStatement(memberQuery);
			ps.setString(1, memberCode);
			ps.setString(2, memberType);
			ps.setString(3, primaryContactPersonCode);
			ps.setString(4, name);
			ps.setInt(5, addressID);
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
	 * 6. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		String query = null;
		// Remove all YearMembership that contains in Products
		query = "DELETE FROM YearMembership;";
		try {
			ps = conn.prepareStatement(query);
			int delete = ps.executeUpdate();
			if (delete > 0) {
				System.out.println("Removes Every YearMembership Record From Products successfully");
			} else {
				System.out.println("YearMembership table Empty.");
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
		// Remove all DayMembership that contains in Products
		query = "DELETE FROM DayMembership;";
		try {
			ps = conn.prepareStatement(query);
			int delete = ps.executeUpdate();
			if (delete > 0) {
				System.out.println("Removes Every DayMembeship Record From Products successfully");
			} else {
				System.out.println("DayMembership Table Empty.");
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
		// Remove all EquipmentRental that contains in Products
		query = "DELETE FROM EquipmentRental;";
		try {
			ps = conn.prepareStatement(query);
			int delete = ps.executeUpdate();
			if (delete > 0) {
				System.out.println("Removes Every EquipmentRental Record From Products successfully");
			} else {
				System.out.println("EquipmentRental table Empty.");
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
		// Remove all ParkingPasses that contains in Products
		query = "DELETE FROM ParkingPasses;";
		try {
			ps = conn.prepareStatement(query);
			int delete = ps.executeUpdate();
			if (delete > 0) {
				System.out.println("Removes Every ParkingPasses Record From Products successfully");
			} else {
				System.out.println("ParkingPasses table Empty.");
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
		// Delete all Products
		query = "DELETE FROM Products";
		try {
			ps = conn.prepareStatement(query);
			int delete = ps.executeUpdate();
			if (delete > 0) {
				System.out.println("Removes Every Product Record From Products successfully");
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
	 * 7. Adds a day-pass record to the database with the provided data.
	 */
	public static void addDayPass(String productCode, String dateTime, String street, String city, String state, String zip, String country, double pricePerUnit) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int addressID = 0;
		int ProductID = 0;
		try {
			// Insert into Product
			query = "INSERT INTO Products (ProductCode, ProductType) VALUES (?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "D");
			ps.executeUpdate();
			ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			rs = ps.executeQuery();
			rs.next();
			ProductID = rs.getInt("LAST_INSERT_ID()");
			// Check that address is there
			query = "SELECT AddressID FROM Address;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			// if the address is not exist, then insert a new one in
			if (!rs.next()) {
				query  = "INSERT INTO Address (Street, City, State, Zip, Country) "
						+ "VALUES (?,?,?,?,?)";
				ps = conn.prepareStatement(query);
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
			query = "INSERT INTO DayMembership (StartDate, Cost, DayAddressID, DayProductID) values (?,?,?,?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, dateTime);
			ps.setDouble(2, pricePerUnit);
			ps.setInt(3, addressID);
			ps.setInt(4, ProductID);
			
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
	 * 8. Adds a year-long-pass record to the database with the provided data.
	 */
	public static void addYearPass(String productCode, String StartDate, String EndDate,String street, String city, String state, String zip, String country, String name, double pricePerUnit) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int addressID = 0;
		int ProductID = 0;
		try {
			// Insert into Product
			query = "INSERT INTO Products (ProductCode, ProductType) VALUES (?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "Y");
			ps.executeUpdate();
			ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			rs = ps.executeQuery();
			rs.next();
			ProductID = rs.getInt("LAST_INSERT_ID()");
			// Check that address is there
			query = "SELECT AddressID FROM Address;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			// if the address is not exist, then insert a new one in
			if (!rs.next()) {
				query  = "INSERT INTO Address (Street, City, State, Zip, Country) "
						+ "VALUES (?,?,?,?,?)";
				ps = conn.prepareStatement(query);
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
			query = "INSERT INTO YearMembership (StartDate, EndDate, Name, Price, YearProductID, YearAddressID) values (?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, StartDate);
			ps.setString(2, EndDate);
			ps.setString(3, productCode);
			ps.setDouble(4, pricePerUnit);
			ps.setInt(5, ProductID);
			ps.setInt(6, addressID);
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
	 * 9. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int ProductID = 0;
		try {
			// Insert into Product
			query = "INSERT INTO Products (ProductCode, ProductType) VALUES (?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "P");
			ps.executeUpdate();
			ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			rs = ps.executeQuery();
			rs.next();
			ProductID = rs.getInt("LAST_INSERT_ID()");
			query = "INSERT INTO ParkingPasses(Fee, ParkingProductID) VALUES (?,?);";
			ps = conn.prepareStatement(query);
			ps.setDouble(1, parkingFee);
			ps.setInt(2, ProductID);
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
	 * 10. Adds an equipment rental record to the database with the provided data.
	 */
	public static void addRental(String productCode, String name, double cost) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int ProductID = 0;
		try {
			// Insert into Product
			query = "INSERT INTO Products (ProductCode, ProductType) VALUES (?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "R");
			ps.executeUpdate();
			ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			rs = ps.executeQuery();
			rs.next();
			ProductID = rs.getInt("LAST_INSERT_ID()");
			query = "INSERT INTO EquipmentRental(Name, Cost, RentalProductID) VALUES (?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setDouble(2, cost);
			ps.setInt(3, ProductID);
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
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		String query = null;
		// Remove Membership from Invoice
		query = "DELETE FROM Membership;";
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
		// Remove Service from Invoice
		query = "DELETE FROM Service;";
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
		// Remove all Invoice
		query = "DELETE FROM Invoice;";
		try {
			ps = conn.prepareStatement(query);
			int delete = ps.executeUpdate();
			if (delete > 0) {
				System.out.println("Remove all Invoice success");
			} else {
				System.out.println("Invoice table Empty");
			}
		} catch (SQLException e) {
			log.error(e);
		}
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String memberCode, String personalTrainerCode, String invoiceDate) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int MemberID = 0;
		int PersonID = 0;
		try {
		// Check that Member is there
		query = "SELECT MemberID, MemberCode FROM Members;";
		ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			String getMemberCode = rs.getString("MemberCode");
			if (memberCode.equals(getMemberCode)) {
				MemberID = rs.getInt("MemberID");
			}
		}
		// Check for Person is there
		query = "SELECT PersonID, PersonCode FROM Persons;";
		ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			String getPersonCode = rs.getString("PersonCode");
			if (personalTrainerCode.equals(getPersonCode)) {
				PersonID = rs.getInt("PersonID");
			}
		}
		// Insert found values into Invoice
		query = "INSERT INTO Invoice (invoiceCode, InvoiceMemberID, InvoicePersonID, InvoiceDate) VALUES (?,?,?,?);";
		ps = conn.prepareStatement(query);
		ps.setString(1, invoiceCode);
		ps.setInt(2, MemberID);
		ps.setInt(3, PersonID);
		ps.setString(4, invoiceDate);
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
	 * 13. Adds a particular day-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addDayPassToInvoice(String invoiceCode, String productCode, int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int ProductID = 0;
		int InvoiceID = 0;
		try {
		// Check that Product is there
		query = "SELECT ProductID, ProductCode FROM Products;";
		ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			String getProductCode = rs.getString("ProductCode");
			if (productCode.equals(getProductCode)) {
				ProductID = rs.getInt("ProductID");
			}
		}
		query = "SELECT InvoiceID, InvoiceCode FROM Invoice;";
		ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			String getInvoiceCode = rs.getString("InvoiceCode");
			if (invoiceCode.equals(getInvoiceCode)) {
				InvoiceID = rs.getInt("Invoice");
			}
		}
		query = "INSERT TO Membership (MembershipProductID, MembershipInvoiceID, quantity) VALUES(?,?,?);";
		ps.setInt(1, ProductID);
		ps.setInt(2, InvoiceID);
		ps.setInt(3, quantity);
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
	 * 14. Adds a particular year-long-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addYearPassToInvoice(String invoiceCode, String productCode, int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int ProductID = 0;
		int InvoiceID = 0;
		try {
		// Check that Product is there
		query = "SELECT ProductID, ProductCode FROM Products;";
		ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			String getProductCode = rs.getString("ProductCode");
			if (productCode.equals(getProductCode)) {
				ProductID = rs.getInt("ProductID");
			}
		}
		query = "SELECT InvoiceID, InvoiceCode FROM Invoice;";
		ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			String getInvoiceCode = rs.getString("InvoiceCode");
			if (invoiceCode.equals(getInvoiceCode)) {
				InvoiceID = rs.getInt("Invoice");
			}
		}
		query = "INSERT TO Membership (MembershipProductID, MembershipInvoiceID, quantity) VALUES(?,?,?);";
		ps.setInt(1, ProductID);
		ps.setInt(2, InvoiceID);
		ps.setInt(3, quantity);
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
     * 15. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: membershipCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
    	Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int ProductID = 0;
		int InvoiceID = 0;
		try {
			if(membershipCode != null) {
			// Check that Product is there
			query = "SELECT ProductID, ProductCode FROM Products;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String getProductCode = rs.getString("ProductCode");
				if (productCode.equals(getProductCode)) {
					ProductID = rs.getInt("ProductID");
				}
			}
			query = "SELECT InvoiceID, InvoiceCode FROM Invoice;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String getInvoiceCode = rs.getString("InvoiceCode");
				if (invoiceCode.equals(getInvoiceCode)) {
					InvoiceID = rs.getInt("Invoice");
				}	
			}
			query = "INSERT TO Service (MembershipProductID, MembershipInvoiceID, quantity, ProductCodeAttach) VALUES(?,?,?);";
			ps.setInt(1, ProductID);
			ps.setInt(2, InvoiceID);
			ps.setInt(3, quantity);
			ps.setString(4, membershipCode);
			ps.executeUpdate();
			} else {
				// Check that Product is there
				query = "SELECT ProductID, ProductCode FROM Products;";
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					String getProductCode = rs.getString("ProductCode");
					if (productCode.equals(getProductCode)) {
						ProductID = rs.getInt("ProductID");
					}
				}
				query = "SELECT InvoiceID, InvoiceCode FROM Invoice;";
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					String getInvoiceCode = rs.getString("InvoiceCode");
					if (invoiceCode.equals(getInvoiceCode)) {
						InvoiceID = rs.getInt("Invoice");
					}	
				}
				query = "INSERT TO Service (MembershipProductID, MembershipInvoiceID, quantity, ProductCodeAttach) VALUES(?,?,?);";
				ps.setInt(1, ProductID);
				ps.setInt(2, InvoiceID);
				ps.setInt(3, quantity);
				ps.setString(4, null);
				ps.executeUpdate();	
			}
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
     * 16. Adds a particular equipment rental (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     * NOTE: membershipCode may be null
     */
    public static void addRentalToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
    	Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int ProductID = 0;
		int InvoiceID = 0;
		try {
			if(membershipCode != null) {
			// Check that Product is there
			query = "SELECT ProductID, ProductCode FROM Products;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String getProductCode = rs.getString("ProductCode");
				if (productCode.equals(getProductCode)) {
					ProductID = rs.getInt("ProductID");
				}
			}
			query = "SELECT InvoiceID, InvoiceCode FROM Invoice;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String getInvoiceCode = rs.getString("InvoiceCode");
				if (invoiceCode.equals(getInvoiceCode)) {
					InvoiceID = rs.getInt("Invoice");
				}	
			}
			query = "INSERT TO Service (MembershipProductID, MembershipInvoiceID, quantity, ProductCodeAttach) VALUES(?,?,?);";
			ps.setInt(1, ProductID);
			ps.setInt(2, InvoiceID);
			ps.setInt(3, quantity);
			ps.setString(4, membershipCode);
			ps.executeUpdate();
			} else {
				// Check that Product is there
				query = "SELECT ProductID, ProductCode FROM Products;";
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					String getProductCode = rs.getString("ProductCode");
					if (productCode.equals(getProductCode)) {
						ProductID = rs.getInt("ProductID");
					}
				}
				query = "SELECT InvoiceID, InvoiceCode FROM Invoice;";
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					String getInvoiceCode = rs.getString("InvoiceCode");
					if (invoiceCode.equals(getInvoiceCode)) {
						InvoiceID = rs.getInt("Invoice");
					}	
				}
				query = "INSERT TO Service (MembershipProductID, MembershipInvoiceID, quantity, ProductCodeAttach) VALUES(?,?,?);";
				ps.setInt(1, ProductID);
				ps.setInt(2, InvoiceID);
				ps.setInt(3, quantity);
				ps.setString(4, null);
				ps.executeUpdate();	
			}
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
    public static void main (String args[]) {
    	BasicConfigurator.configure();
    	InvoiceData.addMember("M001", "G", "abc123", "Consultant", "234 street", "Lincoln", "NE", "68503", "USA");
    	InvoiceData.addPerson("abc123", "Guy", "Name", "23 street", "City", "Vietnam", "10000", "USA");
    	InvoiceData.addEmail("abc123", "avb123@yahoo.com");
    	InvoiceData.addDayPass("fp12", "12/22/2013", "24 street", "lincoln", "ne", "43555", "USA", 24.00);
    	InvoiceDataWriter invoiceWriter = new InvoiceDataWriter();
		invoiceWriter.InvoiceReportWriter(); 
    	
    	
    }
   
}
