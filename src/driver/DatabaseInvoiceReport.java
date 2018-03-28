package driver;

import com.sf.ext.InvoiceDataWriter;

public class DatabaseInvoiceReport {
	public static void main (String args[]) {
	
		InvoiceDataWriter invoiceWriter = new InvoiceDataWriter();
		invoiceWriter.InvoiceReportWriter(); 
	}
}
