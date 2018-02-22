package writer;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import entities.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriterProducts {
	
	public void jsonConverterProducts(List<Products> products) {
		
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("data/Products.json");
		
		PrintWriter jsonPrintWriter = null;
		
		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		for(Products aProduct : products) {
			// Use toJson method to convert Person object into a String
			String productsOutput = gson.toJson(aProduct); 
			jsonPrintWriter.write(productsOutput + "\n");
		}
		
		jsonPrintWriter.close();
	}
}
