// This main-method generates an invoice report
package driver;

import java.util.List;

import entities.Invoice;
import entities.Members;
import entities.Persons;
import entities.Products;
import reader.InvoiceFileReader;
import reader.MembersFileReader;
import reader.PersonsFileReader;
import reader.ProductsFileReader;
import writer.InvoiceConsoleOutput;

public class InvoiceReport {

	public static void main(String[] args) {
		MembersFileReader members = new MembersFileReader();
		PersonsFileReader persons = new PersonsFileReader();
		ProductsFileReader products = new ProductsFileReader();
		InvoiceFileReader invoice = new InvoiceFileReader();

		/*
		 * fr Reads data from the flat file; Creates entities objects; and Stores
		 * entities objects in a entities ArrayList
		 */
		List<Members> membersList = members.readMembers();
		List<Persons> personsList = persons.readPersons();
		List<Products> productsList = products.readProducts();
		List<Invoice> invoiceList = invoice.readInvoice(productsList, membersList, personsList);

		System.out.println();
		// Write invoice report to console
		InvoiceConsoleOutput invoiceWriter = new InvoiceConsoleOutput();
		invoiceWriter.InvoiceReportWriter(); 

	}

}
