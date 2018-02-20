package reader;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 
import entities.Invoice;
import entities.Members;
import entities.Products;
import entities.Persons;

public class InvoiceFileReader {
	
	public ArrayList<Invoice> readInvoice(List<Products> productsList, List<Members> membersList, List<Persons> personsList) {
		Scanner sc = null;

		try {
			sc = new Scanner(new File("data/Invoices.dat"));
			sc.nextLine(); // skips the number of records from the first line --> doesnt need to be read

			// This Invoice ArrayList stores the Invoice objects
			ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
			
			// Get the Products List from the Products ArrayList
		
			
			while (sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				String data[] = line.split(";"); // tokenizes the line and stores in a string array
				
				Products foundProduct = null;
				Persons foundPerson = null;
				Members foundMember = null;

				// Stores the array elements of each line into strings
				String invoiceCode = data[0];
				String membersCode = data[1];
				String trainerCode = data[2];
				String invoiceDate = data[3];

				// creates array for list of products
				String productGetCodeString = data[4];
				String productCodeList[] = productGetCodeString.split(","); // Store the whole List of code of Product in a string array and concatinate it
				String productGetCodeList = null;
				for (int i = 0; i < productCodeList.length; i++) { 
					productGetCodeList = productCodeList[i]; 
				}
				String productCodeDetails [] = productGetCodeList.split(":"); // Tokenize a Product to get its productCode
				String productCode = null;
				int productCodeQuantity = 0;
				String productCodeAttach = null;
				if(productCodeDetails.length == 1) {
					productCode = productCodeDetails[0];
				}
				else if(productCodeDetails.length == 2) {
					productCode = productCodeDetails[0];
					productCodeQuantity = Integer.parseInt(productCodeDetails[1]);
				}
				else if(productCodeDetails.length == 3) {
					productCode = productCodeDetails[0];
					productCodeQuantity = Integer.parseInt(productCodeDetails[1]);
					productCodeAttach = productCodeDetails[2];
				}
				
				for (Products aProduct : productsList ) {
					if(productCode.equals(aProduct.getProductsCode())) {
						foundProduct = aProduct;
					}
				}
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
				Invoice invoice = new Invoice(invoiceCode, foundMember, foundPerson, invoiceDate, foundProduct);
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
