/* Java classes that takes all the information from the reader and entities classes and output them in a nice and understandable format */
package writer;


import java.io.File;
import java.util.List;

import entities.Invoice;
import entities.Members;
import entities.Persons;
import entities.Products;
import reader.InvoiceFileReader;
import reader.MembersFileReader;
import reader.PersonsFileReader;
import reader.ProductsFileReader;

public class InvoiceConsoleOutput {
	public void InvoiceReportWriter () {
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		MembersFileReader members = new MembersFileReader();
		PersonsFileReader persons = new PersonsFileReader();
		ProductsFileReader products = new ProductsFileReader();
		InvoiceFileReader invoice = new InvoiceFileReader();
		List<Members> membersList = members.readMembers();
		List<Persons> personsList = persons.readPersons();
		List<Products> productsList = products.readProducts();
		List<Invoice> invoiceList = invoice.readInvoice(productsList, membersList, personsList);
		System.out.println(String.format("%-20s %-50s %-32s %-15s %-15s %-15s %-15s %-15s", "Invoice", "Member",  "Personal Trainer", "Subtotal", "Fees", "Taxes", "Discount", "Total"));
		
		for(Invoice element : invoiceList) {
			System.out.println(String.format("%-20s %-50s %-32s %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f", element.getInvoiceCode(), element.getMembersCode().getName(), element.getPersonsCode(), 0.0, 0.0, 0.0, 0.0, 0.0));
		}
		System.out.println("==================================================================================================================================================================================");
		System.out.println("TOTALS");
		System.out.println("\nIndividual Invoice Detail Reports");
		System.out.println("=================================================");
		for(Invoice element : invoiceList) {
			System.out.println("Invoice " + element.getInvoiceCode());
			System.out.println("========================================");
			System.out.println("Personal trainer: " + element.getPersonsCode());
			System.out.println("Members info: ");
			for(Members aMember : membersList) {
				if(aMember.getMemberCode().equals(element.getMembersCode().getMemberCode())) {
					System.out.println(aMember.getName() + " (" + aMember.getMemberCode() + ")");
					if(aMember.getType().toLowerCase().equals("s")) {
						System.out.println("[Student]");
					} else {
						System.out.println("[General]");
					}
					for(Persons aPerson : personsList) {
						if(aMember.getPrimaryContact().equals(aPerson.getPersonCode())) {
							System.out.println(aPerson.getFirstName() + ", " + aPerson.getLastName());
						}
					}
					System.out.println(aMember.getAddress());
				}
			}
			System.out.println("-----------------------------");
			
			System.out.println();
		}
	}
}
