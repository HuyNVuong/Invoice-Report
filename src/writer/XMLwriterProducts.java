package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import entities.Products;

import com.thoughtworks.xstream.XStream;

public class XMLwriterProducts {

	public void xmlConverterProducts(List<Products> products) {
		XStream  xstream = new XStream();
		
        File xmlOutputProducts = new File("data/Products.xml");
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutputProducts);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("members", Products.class); 
		for(Products aProduct : products) {
			// Use toXML method to convert Person object into a String
			String productOutput = xstream.toXML(aProduct);
			xmlPrintWriter.write(productOutput);
		}
	
		xmlPrintWriter.close();	
	}
	
} 