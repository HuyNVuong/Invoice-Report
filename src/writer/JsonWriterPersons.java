package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import entities.Persons;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriterPersons {
	
	public void jsonConverterPersons(List<Persons> persons) {
		
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("data/Persons.json");
		
		PrintWriter jsonPrintWriter = null;
		
		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		for(Persons aPerson : persons) {
			// Use toJson method to convert Person object into a String
			String personsOutput = gson.toJson(aPerson); 
			jsonPrintWriter.write(personsOutput + "\n");
		}
		
		jsonPrintWriter.close();
	}
}
