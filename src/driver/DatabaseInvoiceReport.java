package driver;

import com.sf.ext.InvoiceDataWriter;

/**
 * 
 * Assignment 4, Database and Connectivity - Project Phase 3
 * @author HUY VUONG, Will Kitay
 * @version 3.0
 * @date 3/29/2017
 * File associating with this assignment
 * @file DatabaseInfo.java
 * @file InvoiceData.java
 * @file InvoiceDataReader.java
 * @file InvoiceDataWriter.java
 * @file DatabaseInvoiceReport.java
 *
 */
public class DatabaseInvoiceReport {
	/* Block of code got from TA Jordan Schimitz
	private static final Logger logger;
	static {
	ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
	builder.setStatusLevel(Level.ERROR);
	builder.setConfigurationName("BuilderTest");
	builder.add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL).addAttribute("level", Level.DEBUG));
	AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE").addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
	appenderBuilder.add(builder.newLayout("PatternLayout").addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
	appenderBuilder.add(builder.newFilter("MarkerFilter", Filter.Result.DENY, Filter.Result.NEUTRAL).addAttribute("marker", "FLOW"));
	builder.add(appenderBuilder);
	builder.add(builder.newLogger("org.apache.logging.log4j", Level.DEBUG).add(builder.newAppenderRef("Stdout")).addAttribute("additivity", false));
	builder.add(builder.newRootLogger(Level.ERROR).add(builder.newAppenderRef("Stdout")));
	Configurator.initialize(builder.build());
	logger = LogManager.getLogger(Demo.class);
	}
	*/
	public static void main (String args[]) {
	
		InvoiceDataWriter invoiceWriter = new InvoiceDataWriter();
		invoiceWriter.InvoiceReportWriter(); 
	}
}
