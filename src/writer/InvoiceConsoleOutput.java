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
		System.out.println(String.format("%-20s %-40s %-15s", "Invoice", "Member", "Personal Trainer"));
		for(Invoice element : invoiceList) {
			System.out.println(String.format("%-20s %-40s %-15s", element.getInvoiceCode(), element.getMembersCode(), element.getPersonsCode()));
		}
		System.out.println("==================================");
	}
}
