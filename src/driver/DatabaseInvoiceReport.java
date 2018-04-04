package driver;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sf.ext.InvoiceDataWriter;

/**
 * 
 * Assignment 4, Database and Connectivity - Project Phase 3
 * 
 * @author HUY VUONG, Will Kitay
 * @version 3.0
 * @date 3/29/2017 File associating with this assignment
 * @file DatabaseInfo.java
 * @file InvoiceData.java
 * @file InvoiceDataReader.java
 * @file InvoiceDataWriter.java
 * @file DatabaseInvoiceReport.java BONUS: log4j
 * @file log4j.properties
 * @file javalog.out
 */
public class DatabaseInvoiceReport {
	static Logger log = Logger.getLogger(DatabaseInvoiceReport.class.getName());

	public static void main(String[] args) throws IOException, SQLException {

		/*
		 * Create a new directory (e.g., lib) and store the log4j.properties file in
		 * that directory.
		 */

		/*
		 * Convert the relative path of the properties file into the absolute path using
		 * the getAbsolutePath() method of the File class
		 */

		File file = new File("log4j.properties");
		String propFilePath = file.getAbsolutePath();

		/*
		 * PropertyConfigurator class initializes log4j with the configuration
		 * properties specified in log4j.properties.
		 */
		PropertyConfigurator.configure(propFilePath);

		try {
			InvoiceDataWriter invoiceWriter = new InvoiceDataWriter();
			invoiceWriter.InvoiceReportWriter();
		} catch (Exception e) {
			System.out.println("Exception/Error information is written on the log file.");
			log.fatal("This is a fatal message: ", e);
			log.error("This is an error message: ", e);
			log.warn("This is a warning message.");
			log.info("This is an info message.");
			log.debug("This is a debug message.");
			log.trace("This is a trace message.");
		}

	}

}
