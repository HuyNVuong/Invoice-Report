package driver;

import com.sf.ext.InvoiceDataReader;
import com.sf.ext.InvoiceDataWriter;

public class DatabaseInvoiceReport {
	public static void main (String args[]) {
		InvoiceDataReader invoiceList = new InvoiceDataReader();
		InvoiceDataWriter invoiceWriter = new InvoiceDataWriter();
		invoiceWriter.InvoiceReportWriter(); 
	}
}
