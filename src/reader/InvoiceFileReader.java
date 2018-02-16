package reader;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 
import entities.Invoice;

public class InvoiceFileReader {

	public ArrayList<Invoice> readInvoice(List productsList) {
		Scanner sc = null;

		try {
			sc = new Scanner(new File("data/Invoices.dat"));
			sc.nextLine(); // skips the number of records from the first line --> doesnt need to be read

			// This Members ArrayList stores the Members objects
			ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
			
			while (sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				String data[] = line.split(";"); // tokenizes the line and stores in a tring array

				// Stores the array elements of each line into strings
				String invoiceCode = data[0];
				String membersCode = data[1];
				String trainerCode = data[2];
				String invoiceDate = data[3];

				// creates array for list of products
				String productsCode = data[4];
				String productList[] = productsCode.split(",");
				String product = null;
				for (int i = 0; i < productList.length; i++) { 
					product = productList[i]; // FIXME this is gonna give a bug somehow, just wait and see
				}
				
				
				
			}
			return invoiceList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
