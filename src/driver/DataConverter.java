/* This program takes all entities packages and writer packages and runs them */
package driver;

import java.util.List;
import entities.Members;
import entities.Persons;
import entities.Products;
import reader.MembersFileReader;
import reader.PersonsFileReader;
import reader.ProductsFileReader;
import writer.JsonWriterMembers;
import writer.JsonWriterPersons;
import writer.JsonWriterProducts;
import writer.XMLwriterMembers;
import writer.XMLwriterPersons;
import writer.XMLwriterProducts;

public class DataConverter {

	public static void main(String[] args) {
		
		// Create a FlatFileReader object
		MembersFileReader members = new MembersFileReader();
		PersonsFileReader persons = new PersonsFileReader();
		ProductsFileReader products = new ProductsFileReader();
		
		/* 
		 * fr Reads data from the flat file;
		 * Creates entities objects; and
		 * Stores entities objects in a entities ArrayList
		 */
		List<Members> membersList = members.readMembers();
		List<Persons> personsList = persons.readPersons();
		List<Products> productsList = products.readProducts();
		
		// Write entities from ArrayList into a Json file 
		JsonWriterMembers jWriterMembers = new JsonWriterMembers();
		JsonWriterPersons jWriterPersons = new JsonWriterPersons();
		JsonWriterProducts jWriterProducts = new JsonWriterProducts();
		jWriterMembers.jsonConverterMembers(membersList);
		jWriterPersons.jsonConverterPersons(personsList);
		jWriterProducts.jsonConverterProducts(productsList);
		
		// Write entities from ArrayList into an XML file
		 XMLwriterMembers xmlWriterMembers = new XMLwriterMembers();
		 XMLwriterPersons xmlWriterPersons = new XMLwriterPersons();
		 XMLwriterProducts xmlWriterProducts = new XMLwriterProducts();
	     xmlWriterMembers.xmlConverterMembers(membersList);
	     xmlWriterPersons.xmlConverterPersons(personsList);
	     xmlWriterProducts.xmlConverterProducts(productsList);
	}
}
