package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import entities.Members;

import com.thoughtworks.xstream.XStream;

public class XMLwriterMembers {

	public void xmlConverterMembers(List<Members> members) {
		XStream  xstream = new XStream();
		
        File xmlOutputMembers = new File("data/Members.xml");
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutputMembers);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("members", Members.class); 
		for(Members aMember : members) {
			// Use toXML method to convert Person object into a String
			String memberOutput = xstream.toXML(aMember);
			xmlPrintWriter.write(memberOutput);
		}
	
		xmlPrintWriter.close();	
	}
	
} 
