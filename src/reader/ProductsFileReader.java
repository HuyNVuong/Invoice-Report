/* Read in Products.dat and format ist print out */
package reader;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import entities.Products;
import entities.YearMembership;
import entities.ProductsAddress;
import entities.DayMembership;
import entities.ParkingPasses;
import entities.EquipmentRentals;

public class ProductsFileReader {
	
	public ArrayList<Products> readProducts() {
	Scanner sc = null;
		
		try {
			sc = new Scanner(new File("data/Products.dat"));
			sc.nextLine(); // reads the number of records from the first line
			
			// This Person ArrayList stores the Person objects 
			ArrayList <Products> productsList = new ArrayList<Products>();
			while(sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				String data[] = line.split(";"); // tokenizes the line and stores in a tring array 
				// Stores the array elements of each line into strings
				String productCode = data[0];
				String productType = data[1];
				if(productType.equals("Y")) {
					
					String startDate = data[2];
					String endDate = data[3];
					String productsData = data[4];
					String productsDataSplit [] = productsData.split(",");
					String street = productsDataSplit[0];
					String city = productsDataSplit[1];
					String state = productsDataSplit[2];
					String zip = productsDataSplit[3];
					String country = productsDataSplit[4];
					// Creates an Address object
					ProductsAddress address = new ProductsAddress(street, city, state, zip, country);
					String membershipName = data[5];
					String pricePerUnit = data[6];
					YearMembership YearMembership = new YearMembership (productCode, productType, startDate, endDate, address, membershipName, pricePerUnit);
					// Creates a Product object
					Products products = new Products (YearMembership);
					productsList.add(products);

				}
				if(productType.equals("D")) {
					String startDate = data[2];
					String productsData = data[3];
					String productsDataSplit [] = productsData.split(",");
					String street = productsDataSplit[0];
					String city = productsDataSplit[1];
					String state = productsDataSplit[2];
					String zip = productsDataSplit[3];
					String country = productsDataSplit[4];
					// Creates an Address object
					ProductsAddress address = new ProductsAddress(street, city, state, zip, country);
					String cost = data[4];
					DayMembership DayMembership = new DayMembership (productCode, productType, startDate, address, cost);
					// Creates a Product object
					Products products = new Products(DayMembership);
					productsList.add(products);
				}
				if(productType.equals("P")) {
					String parkingFee = data[2];
					ParkingPasses ParkingPasses = new ParkingPasses (productCode, productType, parkingFee);
					Products products = new Products(ParkingPasses);
					// Creates a Product object
					productsList.add(products);
				}
				if(productType.equals("R")) {
					String name = data[2];
					String cost = data [3];
					EquipmentRentals EquipmentRentals = new EquipmentRentals (productCode, productType, name, cost);
					Products products = new Products(EquipmentRentals);
					// Creates a Product object
					productsList.add(products);
				}
				
								
				// Adds the Product object into Person ArrayLis
			}
			sc.close();
			return productsList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}	
	}
}
