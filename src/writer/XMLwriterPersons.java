package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import entities.Persons;

import com.thoughtworks.xstream.XStream;

public class XMLwriterPersons {

	public void xmlConverterPersons(List<Persons> persons) {
		XStream  xstream = new XStream();
		
        File xmlOutputPersons = new File("data/Persons.xml");
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutputPersons);
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
		
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("persons", Persons.class); 
		for(Persons aPerson : persons) {
			// Use toXML method to convert Person object into a String
			String personOutput = xstream.toXML(aPerson);
			xmlPrintWriter.write(personOutput);
		}
	
		xmlPrintWriter.close();	
	}
	
}