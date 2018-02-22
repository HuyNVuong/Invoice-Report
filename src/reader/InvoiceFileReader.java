package reader;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.DayMembership;
import entities.EquipmentRentals;
import entities.Invoice;
import entities.Members;
import entities.ParkingPasses;
import entities.Products;
import entities.YearMembership;
import entities.Persons;

public class InvoiceFileReader {
	
	public ArrayList<Invoice> readInvoice(List<Products> productsList, List<Members> membersList, List<Persons> personsList) {
		Scanner sc = null;

		try {
			sc = new Scanner(new File("data/Invoices.dat"));
			sc.nextLine(); // skips the number of records from the first line --> doesnt need to be read

			// This Invoice ArrayList stores the Invoice objects
			ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
//			List<Products> getInvoiceProducts = new List<Products>();
			// Get the Products List from the Products ArrayList
		
			
			while (sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				String data[] = line.split(";"); // tokenizes the line and stores in a string array
				
				Products foundProduct = null;
				Persons foundPerson = null;
				Members foundMember = null;
				Invoice invoice = null;

				// Stores the array elements of each line into strings
				String invoiceCode = data[0];
				String membersCode = data[1];
				String trainerCode = data[2];
				String invoiceDate = data[3];
				for (Persons aPerson : personsList ) {
					if(trainerCode.equals(aPerson.getPersonCode())) {
						foundPerson = aPerson;
					}
				}
				for (Members aMember : membersList ) {
					if(membersCode.equals(aMember.getMemberCode())) {
						foundMember = aMember;
					}
				}
				invoice = new Invoice(invoiceCode, foundMember, foundPerson, invoiceDate);
				// creates array for list of products
				String productGetCodeString = data[4];
				String productCodeList[] = productGetCodeString.split(","); // Store the whole List of code of Product in a string array and concatinates it
				String productGetCodeList = null;
				String productCode = null;
				for (int i = 0; i < productCodeList.length; i++) { 
					productGetCodeList = productCodeList[i]; 
					String productCodeDetails [] = productGetCodeList.split(":"); // Tokenize a Product to get its productCode
					int productCodeQuantity = 0;
					String productCodeAttach = null;
					if(productCodeDetails.length == 2) {
						productCode = productCodeDetails[0];
						productCodeQuantity = Integer.parseInt(productCodeDetails[1]);
						//foundProduct.setProductsQuantity(productCodeQuantity);
						
					}
					if(productCodeDetails.length == 3) {
						productCode = productCodeDetails[0];
						productCodeQuantity = Integer.parseInt(productCodeDetails[1]);
						productCodeAttach = productCodeDetails[2];
//						foundProduct.setProductsQuantity(productCodeQuantity);
//						foundProduct.setProductsCodeAttach(productCodeAttach);
						  
					}
					//Products foundNewProduct = new Products(foundProduct);
					// For every products that matches the product Code, add it to the Product ArrayList
					for (Products aProduct : productsList ) {
						if(productCode.equals(aProduct.getProductsCode())) {
							if(aProduct instanceof YearMembership) {
								foundProduct = aProduct;
								foundProduct.setProductsQuantity(productCodeQuantity);
								YearMembership newProduct = new YearMembership((YearMembership)aProduct, foundProduct.getProductsQuantity());
								invoice.addItem(newProduct);
										/*Also add a membership if one is attached only applies to Rentals and Parking passes*/
							}
							if(aProduct instanceof DayMembership) {
								foundProduct = aProduct;
								foundProduct.setProductsQuantity(productCodeQuantity);
								DayMembership newProduct = new DayMembership((DayMembership)aProduct, foundProduct.getProductsQuantity());
								invoice.addItem(newProduct);
							}
							/*Also add a membership if one is attached only applies to Rentals and Parking passes*/
							if(aProduct instanceof EquipmentRentals) {
								foundProduct = aProduct;
								foundProduct.setProductsQuantity(productCodeQuantity);
								if(productCodeAttach != null) {
									foundProduct.setProductsCodeAttach(productCodeAttach);
									EquipmentRentals newProduct = new EquipmentRentals((EquipmentRentals)aProduct, 
											foundProduct.getProductsQuantity(), foundProduct.getProductsCodeAttach());
									invoice.addItem(newProduct);
								} else {
									EquipmentRentals newProduct = new EquipmentRentals((EquipmentRentals)aProduct, 
											foundProduct.getProductsQuantity());
									invoice.addItem(newProduct);
								}
							}
							if(aProduct instanceof ParkingPasses) {
								foundProduct = aProduct;
								foundProduct.setProductsQuantity(productCodeQuantity);
								if(productCodeAttach != null) {
									foundProduct.setProductsCodeAttach(productCodeAttach);
									ParkingPasses newProduct = new ParkingPasses((ParkingPasses)aProduct, 
											foundProduct.getProductsQuantity(), foundProduct.getProductsCodeAttach());
									invoice.addItem(newProduct);
								} else {
									ParkingPasses newProduct = new ParkingPasses((ParkingPasses)aProduct, 
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
