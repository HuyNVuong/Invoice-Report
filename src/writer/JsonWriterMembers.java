package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import entities.Members; 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriterMembers {
	
	public void jsonConverterMembers(List<Members> members) {
		
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("data/Members.json");
		
		PrintWriter jsonPrintWriter = null;
		
		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		for(Members aMember : members) {
			// Use toJson method to convert Person object into a String
			String membersOutput = gson.toJson(aMember); 
			jsonPrintWriter.write(membersOutput + "\n");
		}
		 
		jsonPrintWriter.close();
	}
}
