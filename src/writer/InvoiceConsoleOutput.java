/* Java classes that takes all the information from the reader and entities classes and output them in a nice and understandable format */
package writer;


import java.io.File;
import java.util.List;

import entities.DayMembership;
import entities.EquipmentRentals;
import entities.Invoice;
import entities.Members;
import entities.ParkingPasses;
import entities.Persons;
import entities.Products;
import entities.ProductsAddress;
import entities.YearMembership;
import reader.InvoiceFileReader;
import reader.MembersFileReader;
import reader.PersonsFileReader;
import reader.ProductsFileReader;

public class InvoiceConsoleOutput {
	public void InvoiceReportWriter () {
		MembersFileReader members = new MembersFileReader();
		PersonsFileReader persons = new PersonsFileReader();
		ProductsFileReader products = new ProductsFileReader();
		InvoiceFileReader invoice = new InvoiceFileReader();
		List<Members> membersList = members.readMembers();
		List<Persons> personsList = persons.readPersons();
		List<Products> productsList = products.readProducts();
		List<Invoice> invoiceList = invoice.readInvoice(productsList, membersList, personsList);
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		System.out.println(String.format("%-20s %-50s %-32s %-15s %-15s %-15s %-15s %-15s", "Invoice", "Member",  "Personal Trainer", "Subtotal", "Fees", "Taxes", "Discount", "Total"));
		
		for(Invoice element : invoiceList) {
			System.out.println(String.format("%-20s %-50s %-32s %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f", element.getInvoiceCode(), element.getMembersCode().getName(), element.getPersonsCode(), 0.0, 0.0, 0.0, 0.0, 0.0));
		}
		System.out.println("==================================================================================================================================================================================");
		System.out.println("TOTALS");
		System.out.println("\nIndividual Invoice Detail Reports");
		System.out.println("=================================================");
		// First enhanced for loops that goes all over the invoice list
		for(Invoice element : invoiceList) {
			System.out.println("Invoice " + element.getInvoiceCode());
			System.out.println("========================================");
			System.out.println("Personal trainer: " + element.getPersonsCode());
			System.out.println("Members info: ");
			// 2nd enhanced for loops that goes all over the memebers list
			for(Members aMember : membersList) {
				if(aMember.getMemberCode().equals(element.getMembersCode().getMemberCode())) {
					System.out.println("  " + aMember.getName() + " (" + aMember.getMemberCode() + ")");
					if(aMember.getType().toLowerCase().equals("s")) {
						System.out.println("  [Student]");
					} else {
						System.out.println("  [General]");
					}
					// 3rd enhanced for loops thats goes all over Persons list, whose code included in memebers.dat
					for(Persons aPerson : personsList) {
						if(aMember.getPrimaryContact().equals(aPerson.getPersonCode())) {
							System.out.println("  " + aPerson.getFirstName() + ", " + aPerson.getLastName());
						}
					}
					System.out.println("  " + aMember.getAddress());
				}
			}
			System.out.println("--------------------------------------------------");
			System.out.printf("%-10s %-80s %-20s %-15s %-15s\n", "Code", "Item", "Subtotal", "Taxes", "Total" );
			//2nd.1 enhanced for loops that loops through all products list and downcast all its subclasses and print them out
			for(Products elementsProducts : element.getProducts()) {
				String productsNameType = null;
			
				if(elementsProducts.getProductsType().toLowerCase().equals("y")) {
					productsNameType = "Year-long membership";
					String getItem = productsNameType + " '" +  ((YearMembership) elementsProducts).getMembershipName() + "' @ " +
							((YearMembership) elementsProducts).getAddress();
					if(elementsProducts instanceof YearMembership) {
					System.out.println(String.format("%-10s %-76s %-5s %.2f\n%-10s (%s) - (%s) (%d units @ $%.2f) ",
							elementsProducts.getProductsCode(), getItem, "$", 0.0, " ",  ((YearMembership) elementsProducts).getStartDate(),
							((YearMembership) elementsProducts).getEndDate(), elementsProducts.getProductsQuantity(), 
							Double.parseDouble(((YearMembership) elementsProducts).getPricePerUnit())));
					}
				} else if (elementsProducts.getProductsType().toLowerCase().equals("d")) {
					productsNameType = "Day-long membership";
					String getItem = productsNameType + " @ "+ ((DayMembership) elementsProducts).getAddress();
					if(elementsProducts instanceof DayMembership) {
					System.out.println(String.format("%-10s %-76s %-5s %.2f\n%-10s (%s) (%d units @ $%.2f) ", elementsProducts.getProductsCode(), 
							getItem, "$",  0.0, " ", ((DayMembership) elementsProducts).getStartDate(),
							elementsProducts.getProductsQuantity(), Double.parseDouble(((DayMembership) elementsProducts).getCostPerUnit())));
					}
				} else if (elementsProducts.getProductsType().toLowerCase().equals("r")) {
					productsNameType = "Rental Equipment";
					String getItem = null;
					if(elementsProducts instanceof EquipmentRentals) {
						if(elementsProducts.getProductsCodeAttach() != null) {
							getItem = productsNameType + " - "  + elementsProducts.getProductsCodeAttach() + " - " +
									((EquipmentRentals) elementsProducts).getProductName();
						System.out.println(String.format("%-10s %-76s %-5s %.2f\n%-10s (%d units @ $%.2f/unit)", elementsProducts.getProductsCode(), 
								 getItem, "$", 0.0, " ", elementsProducts.getProductsQuantity(), Double.parseDouble(((EquipmentRentals) elementsProducts).getProductCost())));
						} else {
							getItem = productsNameType + " - " + ((EquipmentRentals) elementsProducts).getProductName();
						System.out.println(String.format("%-10s %-76s %-5s %.2f\n%-10s (%d units @ $%.2f/unit)", elementsProducts.getProductsCode(), 
								 getItem, "$", 0.0, " ", elementsProducts.getProductsQuantity(), Double.parseDouble(((EquipmentRentals) elementsProducts).getProductCost())));
						}
					}
				} else {
					productsNameType = "Parking Pass";
					if(elementsProducts instanceof ParkingPasses) {
						String getItem = null;
						if(elementsProducts.getProductsCodeAttach() != null) {
							getItem = productsNameType + " " + elementsProducts.getProductsCodeAttach() + " (" +  elementsProducts.getProductsQuantity() + 
									" units @ " + Double.parseDouble(((ParkingPasses) elementsProducts).getParkingFee()) + ")";
						System.out.println(String.format("%-10s %-76s %-5s %.2f", elementsProducts.getProductsCode(), getItem, "$", 0.0));
						} else {
							getItem = productsNameType + " "+ "(" +  elementsProducts.getProductsQuantity() + 
									" units @ " + Double.parseDouble(((ParkingPasses) elementsProducts).getParkingFee()) + ")";
							System.out.println(String.format("%-10s %-76s %-5s %.2f", elementsProducts.getProductsCode(), getItem, "$", 0.0));
						}
					}
				}
				
					
			}
			System.out.printf("\n\n				Thank you for your purchase!\n");
			System.out.println();
		}
	}
}
