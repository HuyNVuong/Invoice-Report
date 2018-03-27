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
	
	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InvoiceData.class);

	public ArrayList<Members> readMembers() {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = DatabaseInfo.getConnection();
	String query = null;
		
		try {
			// This Members ArrayList stores the Members objects 
			ArrayList<Members> membersList = new ArrayList<Members>();
			MembersAddress address = null;
			query = "SELECT * FROM Address;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			// Adding stuff to Address
			while (rs.next()) {
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String zip = rs.getString("Zip");
				String country = rs.getString("Country");
				// Creates an Address object
				address = new MembersAddress(street, city, state, zip, country);
			}
			query = "SELECT * FROM Members;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				
				// Stores the  array elements of each line into strings
				String memberCode = rs.getString("MemberCode");
				String type = rs.getString("MemberType");
				String primaryContact = rs.getString("MemberContact");
				String name = rs.getString("MemberName");	
				Members members = new Members(memberCode, type, primaryContact, name, address);
				// Adds the Members object into Person ArrayList
				membersList.add(members);
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
			query = "SELECT * FROM Address;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			// Adding stuff to Address
			while (rs.next()) {
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String zip = rs.getString("Zip");
				String country = rs.getString("Country");
				// Creates an Address object
				address = new PersonsAddress(street, city, state, zip, country);
			}
			// Adding stuff to Email
			String email [] = null;
			String queryEmail = "SELECT * FROM Email;";
			ps = conn.prepareStatement(queryEmail);
			rs = ps.executeQuery();
		// 	int i = 0;
//			while (rs.next()) {
//				email[i] = rs.getString("Email");
//				i++;
//			}
			// Adding stuff to Persons
			query = "SELECT * FROM Persons;";
			
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				
				// Stores the  array elements of each line into strings
				String personCode = rs.getString("PersonCode");
				String firstName = rs.getString("PersonFirstName");
				String lastName = rs.getString("PersonLastName");
				Persons person = new Persons(personCode, firstName, lastName, address, email);
				// Adds the Members object into Person ArrayList
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
		PreparedStatement psInner = null;
		ResultSet rsInner = null;
		Connection conn = DatabaseInfo.getConnection();
		String query = null;
		
		try {
			// This Person ArrayList stores the Person objects
			ArrayList<Products> productsList = new ArrayList<Products>();
			query = "SELECT * FROM Products;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String productCode = rs.getString("ProductCode");
				String productType = rs.getString("ProductType");
				
				ProductsAddress address = null;
				if (productType.equals("Y")) {
					query = "SELECT * FROM Address;";
					psInner = conn.prepareStatement(query);
					rsInner = psInner.executeQuery();
					while (rsInner.next()) {
						String street = rsInner.getString("Street");
						String city = rsInner.getString("City");
						String state = rsInner.getString("State");
						String zip = rsInner.getString("Zip");
						String country = rsInner.getString("Country");
						address = new ProductsAddress(street, city, state, zip, country);
					}
					query = "SELECT * FROM YearMembership;";
					psInner = conn.prepareStatement(query);
					rsInner = psInner.executeQuery();
					while (rsInner.next()) {
						String startDate = rsInner.getString("StartDate");
						String endDate = rsInner.getString("EndDate");
						String membershipName = rsInner.getString("Name");
						String price = rsInner.getString("Price");
						YearMembership yearMembership = new YearMembership(productCode, productType, startDate, endDate,
								address, membershipName, price);
						productsList.add(yearMembership);
					}		
					
					// Creates a Product object
				}
				if (productType.equals("D")) {
					query = "SELECT * FROM Address;";
					psInner = conn.prepareStatement(query);
					rsInner = psInner.executeQuery();
					while (rsInner.next()) {
						String street = rsInner.getString("Street");
						String city = rsInner.getString("City");
						String state = rsInner.getString("State");
						String zip = rsInner.getString("Zip");
						String country = rsInner.getString("Country");
						address = new ProductsAddress(street, city, state, zip, country);
					}
					query = "SELECT * FROM DayMembership;";
					psInner = conn.prepareStatement(query);
					rsInner = psInner.executeQuery();
					while(rsInner.next()) {
						String startDate = rsInner.getString("StartDate");
						String cost = rsInner.getString("Cost");
						DayMembership dayMembership = new DayMembership(productCode, productType, startDate, address, cost);
						productsList.add(dayMembership);
					}
				}
				if (productType.equals("P")) {
					query = "SELECT * FROM ParkingPasses;";
					psInner = conn.prepareStatement(query);
					rsInner = psInner.executeQuery();
					while (rsInner.next()) {
						String parkingFee = rsInner.getString("Fee");
						ParkingPasses ParkingPasses = new ParkingPasses(productCode, productType, parkingFee);
						// Creates a Product object
						productsList.add(ParkingPasses);
					}
				}
				if (productType.equals("R")) {
					query = "SELECT * EquipmentRental;";
					psInner = conn.prepareStatement(query);
					rsInner = psInner.executeQuery();
					while (rs.next()) {
						String name = rsInner.getString("Name");
						String cost = rsInner.getString("Cost");
						EquipmentRentals EquipmentRentals = new EquipmentRentals(productCode, productType, name, cost);
						// Creates a Product object
						productsList.add(EquipmentRentals);
					}
				}
				try {
					if(rsInner != null && !rsInner.isClosed())
						rsInner.close();
					if(psInner != null && !psInner.isClosed())
						psInner.close();
				} catch (SQLException sqle) {
					System.out.println("Error");
					log.error(sqle);
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
		PreparedStatement psInner = null;
		ResultSet rsInner = null;
		try {

			// This Invoice ArrayList stores the Invoice objects
			ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
			query = "SELECT * FROM Invoice;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				Products foundProduct = null;
				Persons foundPerson = null;
				Members foundMember = null;
				Invoice invoice = null;
				
				String getMemberCode = null;
				String getPersonCode = null;
				// Stores the array elements of each line into strings
				String invoiceCode = rs.getString("InvoiceCode");
				int memberID = rs.getInt("InvoiceMemberID");
				int personID = rs.getInt("InvoicePersonID");
				String invoiceDate = rs.getString("InvoiceDate");
				query = "SELECT MemberID, MemberCode FROM Members;";
				psInner = conn.prepareStatement(query);
				rsInner = psInner.executeQuery();
				while (rsInner.next()) {
					int getMemberID = rsInner.getInt("MemberID");
					if (memberID == getMemberID) {
						getMemberCode = rsInner.getString("MemberCode");
					}
				}
				query = "SELECT * FROM Persons;";
				psInner = conn.prepareStatement(query);
				rsInner = psInner.executeQuery();
				while(rsInner.next()) {
					int getPersonID = rsInner.getInt("PersonID");
					if (personID == getPersonID) {
						getPersonCode = rsInner.getString("PersonCode");
					}
				}
				
				for (Members aMember : membersList) {
					if (getMemberCode.equals(aMember.getMemberCode())) {
						foundMember = aMember;
					}
				}
				for (Persons aPerson : personsList) {
					if (getPersonCode.equals(aPerson.getPersonCode())) {
						foundPerson = aPerson;
					}
				}
				invoice = new Invoice(invoiceCode, foundMember, foundPerson, invoiceDate);
				
					// For every products that matches the product Code, add it to the Product
					// ArrayList
					for (Products aProduct : productsList) {
						if (aProduct instanceof YearMembership) {
							foundProduct = aProduct;
							query = "SELECT * FROM Membership;";
							ps = conn.prepareStatement(query);
							rs = ps.executeQuery();
							foundProduct = aProduct;
							int quantity = 0;
							while (rs.next()) {
								quantity = rs.getInt("Quantity");
							}
							foundProduct.setProductsQuantity(quantity);
							YearMembership newProduct = new YearMembership((YearMembership) aProduct,
									foundProduct.getProductsQuantity());
							invoice.addItem(newProduct);
						}
							if (aProduct instanceof DayMembership) {
								foundProduct = aProduct;
								query = "SELECT * FROM Membership;";
								ps = conn.prepareStatement(query);
								rs = ps.executeQuery();
								foundProduct = aProduct;
								int quantity = 0;
								while (rs.next()) {
									quantity = rs.getInt("Quantity");
								}
								foundProduct.setProductsQuantity(quantity);
								DayMembership newProduct = new DayMembership((DayMembership) aProduct,
										foundProduct.getProductsQuantity());
								invoice.addItem(newProduct);
							}
							/*
							 * Also add a membership if one is attached only applies to Rentals and Parking
							 * passes
							 */
							if (aProduct instanceof EquipmentRentals) {
								foundProduct = aProduct;
								query = "SELECT * FROM Service;";
								ps = conn.prepareStatement(query);
								rs = ps.executeQuery();
								foundProduct = aProduct;
								int quantity = 0;
								String CodeAttach = null;
								while (rs.next()) {
									quantity = rs.getInt("Quantity");
									CodeAttach = rs.getString("ProductCodeAttach");
								}
								foundProduct.setProductsQuantity(quantity);
								if (CodeAttach != null) {
									foundProduct.setProductsCodeAttach(CodeAttach);
									EquipmentRentals newProduct = new EquipmentRentals((EquipmentRentals) aProduct,
											foundProduct.getProductsQuantity(), foundProduct.getProductsCodeAttach());
									invoice.addItem(newProduct);
								} else {
									EquipmentRentals newProduct = new EquipmentRentals((EquipmentRentals) aProduct,
											foundProduct.getProductsQuantity());
									invoice.addItem(newProduct);
								}
							}
							if (aProduct instanceof ParkingPasses) {
								foundProduct = aProduct;
								query = "SELECT * FROM Service;";
								ps = conn.prepareStatement(query);
								rs = ps.executeQuery();
								foundProduct = aProduct;
								int quantity = 0;
								String CodeAttach = null;
								while (rs.next()) {
									quantity = rs.getInt("Quantity");
									CodeAttach = rs.getString("ProductCodeAttach");
								}
								foundProduct.setProductsQuantity(quantity);
								if (CodeAttach != null) {
									foundProduct.setProductsCodeAttach(CodeAttach);
									ParkingPasses newProduct = new ParkingPasses((ParkingPasses) aProduct,
											foundProduct.getProductsQuantity(), foundProduct.getProductsCodeAttach());
									invoice.addItem(newProduct);
								} else {
									ParkingPasses newProduct = new ParkingPasses((ParkingPasses) aProduct,
											foundProduct.getProductsQuantity());
									invoice.addItem(newProduct);
								}
							}
						}
					try {
						if(rsInner != null && !rsInner.isClosed())
							rsInner.close();
						if(psInner != null && !psInner.isClosed())
							psInner.close();
					} catch (SQLException sqle) {
						
						log.error(sqle);
					}
					invoiceList.add(invoice);
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
			log.error(e);
			return null;
		}
	}
}
