package com.sf.ext;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						String street = rs.getString("Street");
						String city = rs.getString("City");
						String state = rs.getString("State");
						String zip = rs.getString("Zip");
						String country = rs.getString("Country");
						address = new ProductsAddress(street, city, state, zip, country);
					}
					query = "SELECT * FROM YearMembership;";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						String startDate = rs.getString("StartDate");
						String endDate = rs.getString("EndDate");
						String membershipName = rs.getString("Name");
						String price = rs.getString("Price");
						YearMembership yearMembership = new YearMembership(productCode, productType, startDate, endDate,
								address, membershipName, price);
						productsList.add(yearMembership);
					}
					
					
					// Creates a Product object

					

				}
				if (productType.equals("D")) {
					query = "SELECT * FROM Address;";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					
						String street = rs.getString("Street");
						String city = rs.getString("City");
						String state = rs.getString("State");
						String zip = rs.getString("Zip");
						String country = rs.getString("Country");
						address = new ProductsAddress(street, city, state, zip, country);
				
					query = "SELECT * FROM DayMembership;";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					
						String startDate = rs.getString("StartDate");
						String cost = rs.getString("Cost");
						DayMembership dayMembership = new DayMembership(productCode, productType, startDate, address, cost);
						productsList.add(dayMembership);
				
				}
				if (productType.equals("P")) {
					query = "SElECT * FROM ParkingPasses;";
					ps = conn.prepareStatement(query);
					while (rs.next()) {
						rs = ps.executeQuery();
						String parkingFee = rs.getString("Fee");
						ParkingPasses ParkingPasses = new ParkingPasses(productCode, productType, parkingFee);
						// Creates a Product object
						productsList.add(ParkingPasses);
					}
				}
				if (productType.equals("R")) {
					query = "SELECT * EquipmentRental;";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						String name = rs.getString("Name");
						String cost = rs.getString("Cost");
						EquipmentRentals EquipmentRentals = new EquipmentRentals(productCode, productType, name, cost);
						// Creates a Product object
						productsList.add(EquipmentRentals);
					}
				}

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
			return productsList;
		} catch (SQLIntegrityConstraintViolationException le) {
			log.error(le);
			return null;
		} catch (SQLException e) {
			log.error(e);
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
				int memberCode = rs.getInt("InvoiceMemberID");
				int personCode = rs.getInt("InvoicePersonID");
				String invoiceDate = rs.getString("InvoiceDate");
				query = "SELECT * FROM Members;";
				psInner = conn.prepareStatement(query);
				rsInner = ps.executeQuery();
				while (rsInner.next()) {
					int getMemberID = rs.getInt("MemberID");
					if (memberCode == getMemberID) {
						getMemberCode = rs.getString("MemberCode");
					}
				}
				query = "SELECT * FROM Persons;";
				psInner = conn.prepareStatement(query);
				rsInner = ps.executeQuery();
				while(rsInner.next()) {
					int getPersonID = rs.getInt("PersonID");
					if (personCode == getPersonID) {
						getPersonCode = rs.getString("PersonCode");
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
				// creates array for list of products
				String productGetCodeString = data[4];
				String productCodeList[] = productGetCodeString.split(","); // Store the whole List of code of Product
																			// in a string array and concatinates it
				String productGetCodeList = null;
				String productCode = null;
				for (int i = 0; i < productCodeList.length; i++) {
					productGetCodeList = productCodeList[i];
					String productCodeDetails[] = productGetCodeList.split(":"); // Tokenize a Product to get its
																					// productCode
					int productCodeQuantity = 0;
					String productCodeAttach = null;
					if (productCodeDetails.length == 2) {
						productCode = productCodeDetails[0];
						productCodeQuantity = Integer.parseInt(productCodeDetails[1]);
						// foundProduct.setProductsQuantity(productCodeQuantity);

					}
					if (productCodeDetails.length == 3) {
						productCode = productCodeDetails[0];
						productCodeQuantity = Integer.parseInt(productCodeDetails[1]);
						productCodeAttach = productCodeDetails[2];
						// foundProduct.setProductsQuantity(productCodeQuantity);
						// foundProduct.setProductsCodeAttach(productCodeAttach);

					}
					// Products foundNewProduct = new Products(foundProduct);
					// For every products that matches the product Code, add it to the Product
					// ArrayList
					for (Products aProduct : productsList) {
						if (productCode.equals(aProduct.getProductsCode())) {
							if (aProduct instanceof YearMembership) {
								foundProduct = aProduct;
								foundProduct.setProductsQuantity(productCodeQuantity);
								YearMembership newProduct = new YearMembership((YearMembership) aProduct,
										foundProduct.getProductsQuantity());
								invoice.addItem(newProduct);
								/*
								 * Also add a membership if one is attached only applies to Rentals and Parking
								 * passes
								 */
							}
							if (aProduct instanceof DayMembership) {
								foundProduct = aProduct;
								foundProduct.setProductsQuantity(productCodeQuantity);
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
								foundProduct.setProductsQuantity(productCodeQuantity);
								if (productCodeAttach != null) {
									foundProduct.setProductsCodeAttach(productCodeAttach);
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
								foundProduct.setProductsQuantity(productCodeQuantity);
								if (productCodeAttach != null) {
									foundProduct.setProductsCodeAttach(productCodeAttach);
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
					}
				}
				invoiceList.add(invoice);
			}
			sc.close();
			return invoiceList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
