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


		System.out.println();
		// Write invoice report to console
		InvoiceConsoleOutput invoiceWriter = new InvoiceConsoleOutput();
		invoiceWriter.InvoiceReportWriter(); 

	}

}
