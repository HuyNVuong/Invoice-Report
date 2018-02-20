package driver;

import java.util.List;

import entities.Invoice;
import entities.Members;
import entities.MembersAddress;
import entities.Products;
import entities.ProductsAddress;
import entities.Membership;
import entities.ParkingPasses;
import entities.Persons;
import entities.YearMembership;
import reader.MembersFileReader;
import reader.PersonsFileReader;
import reader.ProductsFileReader;
import reader.InvoiceFileReader;

public class Test {
	public static void main (String args[]) {
		MembersAddress address = new MembersAddress("204", "244", "Street", "City", "Country");
		Members members1 = new Members("M001", "S", "230 street", "earth", address );
		Members members2 = new Members("0202", "G", "230 street", "earth", address );
		ProductsAddress address2 = new ProductsAddress("204", "244", "Street", "City", "Country");
		YearMembership products1 = new YearMembership("50", "sd", "10-24", "12-40", address2, "acar", "10");
		ParkingPasses parkingpasses = new ParkingPasses("fp12", "sdg", "60.00");
		products1.getMembersType(members1);
		System.out.println(products1.getMembersType(members1));
		System.out.println(products1.getTotal());
		System.out.println(parkingpasses.getMembersType(members2));
		System.out.println(parkingpasses.getTotal());
		
		MembersFileReader members = new MembersFileReader();
		PersonsFileReader persons = new PersonsFileReader();
		ProductsFileReader products = new ProductsFileReader();
		InvoiceFileReader invoice = new InvoiceFileReader();
		List<Members> membersList = members.readMembers();
		List<Persons> personsList = persons.readPersons();
		List<Products> productsList = products.readProducts();
		List<Invoice> invoiceList = invoice.readInvoice(productsList, membersList, personsList);
		for(Invoice element : invoiceList) {
			System.out.println(element);
		}
		
	}
}
