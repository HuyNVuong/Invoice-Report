package adt;

import entities.Invoice;
import adt.InvoiceList;

public class InvoiceReport {

	public static void main(String[] args) {
		// Read from database
			// create DB reader object from DB reader class
			// read data from DB using that object
			// it should create a list of invoices (e.g. InvList)

		// Order using ADT
		InvoiceList<Invoice> invoiceOrderedList = new InvoiceList<Invoice>(new TotalComparator());
		// add list of invoices (e.g. InvList) into the invoiceOrderedList

		// write to Stdout

	}

}
