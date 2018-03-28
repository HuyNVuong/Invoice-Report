package com.sf.ext;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


import entities.DayMembership;
import entities.EquipmentRentals;
import entities.Invoice;
import entities.Members;
import entities.MembersAddress;
import entities.ParkingPasses;
import entities.Persons;
import entities.PersonsAddress;
import entities.Products;
import entities.ProductsAddress;
import entities.YearMembership;

public class InvoiceDataReader {
	
	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InvoiceDataReader.class);

	public ArrayList<Members> readMembers() {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = DatabaseInfo.getConnection();
	String query = null;
		
		try {
			// This Members ArrayList stores the Members objects 
			ArrayList<Members> membersList = new ArrayList<Members>();
			MembersAddress address = null;
			query = "SELECT * FROM Members AS m JOIN Address AS a ON m.AddressID = a.AddressID;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				address = new MembersAddress (rs.getString("Street"), rs.getString("City"), rs.getString("State")
						, rs.getString("Zip"), rs.getString("Country"));
				String MemberCode = rs.getString("MemberCode");
				String MemberType = rs.getString("MemberType");
				String MemberContact = rs.getString("MemberContact");
				String MemberName = rs.getString("Membername");
				Members member = new Members(MemberCode, MemberType, MemberContact, MemberName, address);
				membersList.add(member);
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
			return membersList;
		} catch (SQLIntegrityConstraintViolationException le) {
			log.error(le);
			return null;
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
		
	}
	public ArrayList<Persons> readPersons() {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = DatabaseInfo.getConnection();
	String query = null;
		
		try {
			
			// This Person ArrayList stores the Person objects 
			ArrayList<Persons> personsList = new ArrayList<Persons>();
			PersonsAddress address = null;
			query = "SELECT * FROM Persons as p JOIN Address as a on p.PersonsID = a.AddressID;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			// Adding stuff to Address
			while (rs.next()) {
				address = new PersonsAddress (rs.getString("Street"), rs.getString("City"), rs.getString("State")
						, rs.getString("Zip"), rs.getString("Country"));
				String [] email = null;
				String PersonCode = rs.getString("PersonCode");
				String PersonFirstName = rs.getString("PersonFirstname");
				String PersonLastName = rs.getString("PersonLastname");
				Persons person = new Persons (PersonCode, PersonFirstName, PersonLastName, address, email);
				personsList.add(person);
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
			return personsList;
		} catch (SQLIntegrityConstraintViolationException le) {
			log.error(le);
			return null;
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
	}
	public ArrayList<Products> readProducts() {
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		Connection conn = DatabaseInfo.getConnection();
		String query = null;
		
		try {
			
			ArrayList<Products> productsList = new ArrayList<Products>();
			// Query to retrieve all Year Membership
			query = "SELECT * FROM Products AS p JOIN YearMembership AS y on p.ProductID = y.YearProductID"
					+ " JOIN Address AS a ON y.YearAddressID = a.AddressID;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("ProductType").equals("Y")) {
					
					ProductsAddress address = new ProductsAddress (rs.getString("Street"), 
							rs.getString("City"), rs.getString("State")
							, rs.getString("Zip"), rs.getString("Country"));
					YearMembership y = new YearMembership (rs.getString("ProductCode"),	"Y",
							rs.getString("StartDate"),
							rs.getString("EndDate"),
							address,
							rs.getString("Name"),
							rs.getString("Price"));
					productsList.add(y);
				}
			}
			
			// Query to retrieve all Day Membership
			query = "SELECT * FROM Products AS p JOIN DayMembership AS d on p.ProductID = d.DayProductID"
					+ " JOIN Address AS a ON d.DayAddressID = a.AddressID;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("ProductType").equals("D")) {
					
					ProductsAddress address = new ProductsAddress (rs.getString("Street"), 
							rs.getString("City"), rs.getString("State")
							, rs.getString("Zip"), rs.getString("Country"));
					DayMembership d = new DayMembership (rs.getString("ProductCode"), "D",
							rs.getString("StartDate"),
							address,
							rs.getString("Cost"));
					productsList.add(d);
				}
			}
			
			// Query to retrieve all EquipmentRental
			query = "SELECT * FROM Products AS p JOIN EquipmentRental AS r on p.ProductID = r.RentalProductID;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("ProductType").equals("R")) {
					EquipmentRentals r = new EquipmentRentals (rs.getString("ProductCode"), "R",
							rs.getString("Name"),
							rs.getString("Cost"));
					productsList.add(r);
				}
			}
			query = "SELECT * FROM Products AS p JOIN ParkingPasses AS s ON p.ProductID = s.ParkingProductID;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("ProductType").equals("P")) {
					ParkingPasses p = new ParkingPasses (rs.getString("ProductCode"), "P",
							rs.getString("Fee"));
					productsList.add(p);
				}
			}
			try {
				if(rs != null && !rs.isClosed())
					rs.close();
				if(ps != null && !ps.isClosed())
					ps.close();
			} catch (SQLException sqle) {
				System.out.println("Error 0: ");
				log.error(sqle);
			}
			
	        // Close connection

			DatabaseInfo.closeConnection(conn);	
			return productsList;
		} catch (SQLIntegrityConstraintViolationException le) {
		
			log.error(le);
			return null;
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<Invoice> readInvoice(List<Products> productsList, List<Members> membersList,
			List<Persons> personsList) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = DatabaseInfo.getConnection();
		String query = null;
	
		try {

			// This Invoice ArrayList stores the Invoice objects
			ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
			// Invoice object for storing initial Invoice information
			Invoice invoice = null;
			Members member = null;
			Persons person = null;
			query = "SELECT * FROM Invoice as i JOIN Members AS m ON i.InvoiceMemberID = m.MemberID "
					+ "JOIN Persons AS p ON i.InvoicePersonID = p.PersonID;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String PersonCode = rs.getString("PersonCode");
				String MemberCode = rs.getString("MemberCode");
				for (Members aMember : membersList) {
					if (MemberCode.equals(member.getMemberCode())) {
						member = aMember;
					}
				}
				for (Persons aPerson : personsList) {
					if (PersonCode.equals(aPerson.getPersonCode())) {
						person = aPerson;
					}
				}
				invoice = new Invoice(rs.getString("InvoiceCode"), member, person, rs.getString("InvoiceDate"));
				
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
			return invoiceList;
		} catch (SQLIntegrityConstraintViolationException le) {
			log.error(le);
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}
	}
}
