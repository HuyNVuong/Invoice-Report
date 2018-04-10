package com.sf.ext;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import adt.InvoiceList;
import adt.TotalComparator;
import entities.DayMembership;
import entities.EquipmentRentals;
import entities.Invoice;
import entities.Members;
import entities.ParkingPasses;
import entities.Persons;
import entities.Products;
import entities.YearMembership;

public class InvoiceDataWriter {
	public void InvoiceReportWriter () {
		// for parsing string got from products file to pattern
		InvoiceDataReader invoice = new InvoiceDataReader();
		List<Members> membersList = invoice.readMembers();
		List<Persons> personsList = invoice.readPersons();
		List<Products> productsList = invoice.readProducts();
		InvoiceList<Invoice> invoiceOrderedList = new InvoiceList<Invoice>(new TotalComparator());
		
		List<Invoice> invoiceList = invoice.readInvoice(productsList, membersList, personsList);
		for (Invoice i : invoiceList) {
			invoiceOrderedList.add(i);
		}
		
		// Create neccessarily variable to stores all subtotal, taxes, discount of summarize of all Invoice
		System.out.println("\n\nThe above error is from log4j error because it is set up to be used locally, webgrader is not supporting it!\n\n");
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		System.out.println(String.format("%-20s %-50s %-32s %-15s %-15s %-15s %-15s %-15s", "Invoice", "Member",  ""
				+ "Personal Trainer", "Subtotal", "Fees", "Taxes", "Discount", "Total"));
		double[] totals = printExecReport(invoiceOrderedList, membersList, personsList, productsList);
		System.out.println("==================================================================================================================================================================================");
		System.out.println(String.format("TOTALS %96s %9.2f  %s %9.2f  %s %12.2f  %s %14.2f  %s %10.2f",  "$", totals[0], "$", totals[1], "$", totals[2], "$", totals[3], "$", totals[4]));
		System.out.println("\nIndividual Invoice Detail Reports");
		System.out.println("=================================================");
		
		printIndividualReport(invoiceOrderedList, membersList, personsList, productsList);
	}
		// First enhanced for loops that goes all over the invoice list
	public void printIndividualReport(InvoiceList <Invoice> invoiceOrdered, List<Members> membersList, List<Persons> personsList, List<Products> productsLists) {
		DateTimeFormatter m = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTimeFormatter s = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
		for(Invoice element : invoiceOrdered) {
			// Create neccessarily variable to store subtotal, taxes, discount,etc
			double allTotalTotal = 0.0;
			double InvoiceSubtotalTotal = 0.0;
			double InvoiceTaxTotal = 0.0;
			double totalTotal = 0.0;
			double studentDiscount = 0.0;
			double storeYearLongSubTotal = 0.0, storeYearLongTaxTotal = 0.0;
			double storeDayLongSubTotal = 0.0, storeDayLongTaxTotal = 0.0;
			double storeEquipmentSubTotal = 0.0 , storeEquipmentTaxTotal = 0.0;
			double storeFeeSubTotal = 0.0 , storeFeeTaxTotal = 0.0;
			System.out.println("Invoice " + element.getInvoiceCode());
			System.out.println("========================================");
			System.out.println("Personal trainer: " + element.getPersonsCode());
			System.out.println("Members info: ");
			// 2nd enhanced for loops that goes all over the memebers list
			for(Members aMember : membersList) {
				if(aMember.getMemberCode().equals(element.getMembersCode().getMemberCode())) {
					System.out.println("  " + aMember.getName() + " (" + aMember.getMemberCode() + ")");
					if(aMember.getType().toLowerCase().equals("student")) {
						System.out.println("  [Student]");
					} else {
						
						System.out.println("  [General]");
					}
					// 3rd enhanced for loops thats goes all over Persons list, whose code included in memebers.dat
					for(Persons aPerson : personsList) {
						if(aMember.getPrimaryContact().equals(aPerson.getPersonCode())) {
							System.out.println("  " + aPerson.getFirstName() + ", " + aPerson.getLastName());
						}
					}
					System.out.println("  " + aMember.getAddress());
				}
			}
			System.out.println("--------------------------------------------------");
			System.out.printf("%-10s %-80s %-15s %-11s %-15s\n", "Code", "Item", "Subtotal", "Taxes", "Total" );
			// Since this class only take the whole arraylist of an entities, therefore it should not store any products type
			// so that use flag and break points to compare and get products informations
			int flagYear = 0;
			int flagDay = 0;
			String codeYear = "";
			String codeDay = "";
			//2nd.1 enhanced for loops that loops through all products list and downcast all its subclasses and print them out
			for(Products elementsProducts : element.getProducts()) {
				
				String yearLongNameType = null;
				String dayLongNameType = null;
				String serviceNameType = null;
				if(elementsProducts.getProductsType().toLowerCase().equals("y")) {
					yearLongNameType = "Year-long membership";
					flagYear = 1;
					codeYear = elementsProducts.getProductsCode();
					double subTotal = elementsProducts.getCost();
					double tax = elementsProducts.getTax();
					double total = elementsProducts.getTotal();
					String getItem = yearLongNameType + " '" +  ((YearMembership) elementsProducts).getMembershipName() + "' @ " +
							((YearMembership) elementsProducts).getAddress();
					if(elementsProducts instanceof YearMembership) {
						DateTime yearTimeStart = m.parseDateTime(((YearMembership) elementsProducts).getStartDate());
						DateTime yearTimeEnd = m.parseDateTime(((YearMembership) elementsProducts).getEndDate());
						int month = Integer.parseInt(yearTimeStart.toString("MM"));
						if(month == 1) {
							subTotal = elementsProducts.getCost() * 85.0 / 100.0;
							total = elementsProducts.getTotal() * 85.0 / 100.0;
							tax = elementsProducts.getTax() * 85.0 / 100.0;
							System.out.println(String.format("%-10s %-76s %s %9.2f  %s %9.2f  %s %9.2f\n%-10s %s - %s (%d units @ $%.2f "
									+ "with %%15 off) ", elementsProducts.getProductsCode(), getItem,
									"$", subTotal, "$",	tax, "$", total," ",  yearTimeStart.toString("yyyy/MM/dd"),
									yearTimeEnd.toString("yyyy/MM/dd"), elementsProducts.getProductsQuantity(), 
									Double.parseDouble(((YearMembership) elementsProducts).getPricePerUnit())));
						} else {
						System.out.println(String.format("%-10s %-76s %s %9.2f  %s %9.2f  %s %9.2f\n%-10s %s - %s (%d units @ $%.2f) ",
								elementsProducts.getProductsCode(), getItem,
								"$",subTotal, "$",	tax, "$", total,
								" ",  yearTimeStart.toString("yyyy/MM/dd"),
								yearTimeEnd.toString("yyyy/MM/dd"), elementsProducts.getProductsQuantity(), 
								Double.parseDouble(((YearMembership) elementsProducts).getPricePerUnit())));
						}
					}
					storeYearLongSubTotal = subTotal;
					storeYearLongTaxTotal = tax;				
				} 
				if (elementsProducts.getProductsType().toLowerCase().equals("d")) {
					dayLongNameType = "Day-long membership";
					flagDay = 1;
					codeDay = elementsProducts.getProductsCode();
					double subTotal = elementsProducts.getCost();
					double tax = elementsProducts.getTax();
					double total = elementsProducts.getTotal();
					String getItem = dayLongNameType + " @ "+ ((DayMembership) elementsProducts).getAddress();
					if(elementsProducts instanceof DayMembership) {
						DateTime getDayTime = s.parseDateTime(((DayMembership) elementsProducts).getStartDate());
						int month = Integer.parseInt(getDayTime.toString("MM"));
						if(month == 1) {
							subTotal = elementsProducts.getCost() / 2.0;
							total = elementsProducts.getTotal() / 2.0;
							tax = elementsProducts.getTax() / 2.0;
							System.out.println(String.format("%-10s %-76s %s %9.2f  %s %9.2f  %s %9.2f\n%-10s %s (%d units @ $%.2f with %%50 off) ", elementsProducts.getProductsCode(), 
									getItem, "$", subTotal, "$",	tax, "$", total, " ", getDayTime.toString("yyyy/MM/dd"),
									elementsProducts.getProductsQuantity(), Double.parseDouble(((DayMembership) elementsProducts).getCostPerUnit())));
						} else {
						System.out.println(String.format("%-10s %-76s %s %9.2f  %s %9.2f  %s %9.2f\n%-10s %s (%d units @ $%.2f) ", elementsProducts.getProductsCode(), 
								getItem, "$",  subTotal, "$",	tax, "$", total,
								" ", getDayTime.toString("yyyy/MM/dd"),
								elementsProducts.getProductsQuantity(), Double.parseDouble(((DayMembership) elementsProducts).getCostPerUnit())));
						}
					}
					storeDayLongSubTotal = subTotal;
					storeDayLongTaxTotal = tax;
				} 
				if (elementsProducts.getProductsType().toLowerCase().equals("r")) {
					serviceNameType = "Rental Equipment";
					String getItem = null;
					String discountAnnounce = new String("");
					double subTotal = elementsProducts.getCost();
					double taxes = elementsProducts.getTax();
					double total = elementsProducts.getTotal();
					
					if(elementsProducts instanceof EquipmentRentals) {
					
						if(elementsProducts.getProductsCodeAttach() != null) {
								if(flagYear == 1) {
									if(codeYear.equals(elementsProducts.getProductsCodeAttach())) {
										discountAnnounce = " @ %5 off";
										subTotal = elementsProducts.getCost() * 95.0 / 100.0;
										taxes = elementsProducts.getTax() * 95.0 / 100.0;
										total = subTotal + taxes;
									}
								}
							getItem = serviceNameType + " - "  + elementsProducts.getProductsCodeAttach() + " - " +
									((EquipmentRentals) elementsProducts).getProductName();
						System.out.println(String.format("%-10s %-76s %s %9.2f  %s %9.2f  %s %9.2f\n%-10s (%d units @ $%.2f/unit%s)", elementsProducts.getProductsCode(), 
								 getItem, "$", subTotal, "$", taxes, "$", total,
								 " ", elementsProducts.getProductsQuantity(),
								 Double.parseDouble(((EquipmentRentals) elementsProducts).getProductCost()), discountAnnounce));
						} else {
							getItem = serviceNameType + " - " + ((EquipmentRentals) elementsProducts).getProductName();
						System.out.println(String.format("%-10s %-76s %s %9.2f  %s %9.2f  %s %9.2f\n%-10s (%d units @ $%.2f/unit%s)", elementsProducts.getProductsCode(), 
								 getItem, "$", subTotal, "$", taxes, "$", total,
								 " ", elementsProducts.getProductsQuantity(), 
								 Double.parseDouble(((EquipmentRentals) elementsProducts).getProductCost()), discountAnnounce));
						}
					}
					storeEquipmentSubTotal = subTotal;
					storeEquipmentTaxTotal = taxes;
				} 
				if (elementsProducts.getProductsType().toLowerCase().equals("p")) {
					serviceNameType = "Parking Pass";
					String freePasses = "";
					double subTotal = elementsProducts.getCost();
					double tax = elementsProducts.getTax();
					double total = elementsProducts.getTotal();
					if(elementsProducts instanceof ParkingPasses) {
						String getItem = null;
						if(elementsProducts.getProductsCodeAttach() != null) {
							if(elementsProducts.getProductsCodeAttach() != null) {
								if(flagYear == 1) {
									if(codeYear.equals(elementsProducts.getProductsCodeAttach())) {
										freePasses = " with 30 free";
										subTotal = elementsProducts.getCost() - Double.parseDouble(((ParkingPasses) elementsProducts).getParkingFee()) * 30.0;
										tax = 0.04 * subTotal;
										total = subTotal + tax;
									}
								} 
								if(flagDay == 1) {
									if(codeDay.equals(elementsProducts.getProductsCodeAttach())) {
										freePasses = " with 1 free";
										subTotal = elementsProducts.getCost() - Double.parseDouble(((ParkingPasses) elementsProducts).getParkingFee());
										tax = 0.04 * subTotal;
										total = subTotal + tax;
									}
								}
							} 
							getItem = serviceNameType + " " + elementsProducts.getProductsCodeAttach() + " (" +  
									elementsProducts.getProductsQuantity() + 
									" units @ " + Double.parseDouble(((ParkingPasses) elementsProducts).getParkingFee()) + freePasses + ")";
						System.out.println(String.format("%-10s %-76s %s %9.2f  %s %9.2f  %s %9.2f", elementsProducts.getProductsCode(), getItem,
								"$", subTotal, "$", tax, "$", total));
						} else {
							getItem = serviceNameType + " "+ "(" +  elementsProducts.getProductsQuantity() + 
									" units @ " + Double.parseDouble(((ParkingPasses) elementsProducts).getParkingFee()) + ")";
							System.out.println(String.format("%-10s %-76s %s %9.2f  %s %9.2f  %s %9.2f", elementsProducts.getProductsCode(), getItem, 
									"$", subTotal, "$", tax	, "$", total));
						}
					}
					storeFeeSubTotal = subTotal;
					storeFeeTaxTotal = tax;
				}
				
					
			}
			System.out.printf("%126s\n", "======================================");
			InvoiceSubtotalTotal = storeYearLongSubTotal + storeDayLongSubTotal + storeEquipmentSubTotal + storeFeeSubTotal; 
			InvoiceTaxTotal = storeYearLongTaxTotal + storeDayLongTaxTotal + storeEquipmentTaxTotal + storeFeeTaxTotal;
			totalTotal = InvoiceSubtotalTotal + InvoiceTaxTotal;
			studentDiscount = -1.0*(InvoiceSubtotalTotal * 8.0 / 100.0 + InvoiceTaxTotal);
			allTotalTotal = totalTotal + studentDiscount;
			if(element.getMembersCode().getType().toLowerCase().equals("student")) {
				System.out.println(String.format("%s %78s %9.2f  %s %9.2f  %s %9.2f", "SUB-TOTALS", "$", InvoiceSubtotalTotal, "$", InvoiceTaxTotal, "$", totalTotal));
				System.out.println(String.format("%s %84s %9.2f", "DISCOUNT (8% STUDENT & NO TAX)", "$", studentDiscount));
				System.out.println(String.format("%s %90s %9.2f", "ADDITIONAL FEE (STUDENT)", "$", 10.50));
				System.out.println(String.format("%s %108s %9.2f", "TOTALS", "$", allTotalTotal + 10.50));
			} else {
				System.out.println(String.format("%s %78s %9.2f  %s %9.2f  %s %9.2f", "SUB-TOTALS", "$", InvoiceSubtotalTotal, "$", InvoiceTaxTotal, "$", totalTotal));
				System.out.println(String.format("%s %108s %9.2f", "TOTALS", "$", totalTotal));
			}
			System.out.printf("\n\n				Thank you for your purchase!\n");
			System.out.println();
			// summaryReportArray = new double [] {InvoiceSubtotalTotal, 10.50, InvoiceTaxTotal, studentDiscount, totalTotal};  
		}
	}
	public double[] printExecReport(InvoiceList<Invoice> invoiceOrdered, List<Members> membersList, List<Persons> personsList, List<Products> productsLists) {
		DateTimeFormatter m = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTimeFormatter s = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
		double[] totals = new double[5];
		for(Invoice element : invoiceOrdered) {
			// Create neccessarily variable to store subtotal, taxes, discount,etc
			double additionalFee = 0.0;
			double InvoiceSubtotalTotal = 0.0;
			double InvoiceTaxTotal = 0.0;
			double totalTotal = 0.0;
			double studentDiscount = 0.0;
			double storeYearLongSubTotal = 0.0, storeYearLongTaxTotal = 0.0;
			double storeDayLongSubTotal = 0.0, storeDayLongTaxTotal = 0.0;
			double storeEquipmentSubTotal = 0.0 , storeEquipmentTaxTotal = 0.0;
			double storeFeeSubTotal = 0.0 , storeFeeTaxTotal = 0.0;
			double allTotalTotal = 0.0;
			// 2nd enhanced for loops that goes all over the memebers list
			
			// Since this class only take the whole arraylist of an entities, therefore it should not store any products type
			// so that use flag and break points to compare and get products informations
			int flagYear = 0;
			int flagDay = 0;
			String codeYear = "";
			String codeDay = "";
			//2nd.1 enhanced for loops that loops through all products list and downcast all its subclasses and print them out
			for(Products elementsProducts : element.getProducts()) {
				String yearLongNameType = null;
				String dayLongNameType = null;
				
				if(elementsProducts.getProductsType().toLowerCase().equals("y")) {
					yearLongNameType = "Year-long membership";
					flagYear = 1;
					codeYear = elementsProducts.getProductsCode();
					double subTotal = elementsProducts.getCost();
					double tax = elementsProducts.getTax();
					double total = elementsProducts.getTotal();
					String getItem = yearLongNameType + " '" +  ((YearMembership) elementsProducts).getMembershipName() + "' @ " +
							((YearMembership) elementsProducts).getAddress();
					if(elementsProducts instanceof YearMembership) {
						DateTime yearTimeStart = m.parseDateTime(((YearMembership) elementsProducts).getStartDate());
						DateTime yearTimeEnd = m.parseDateTime(((YearMembership) elementsProducts).getEndDate());
						int month = Integer.parseInt(yearTimeStart.toString("MM"));
						if(month == 1) {
							subTotal = elementsProducts.getCost() * 85.0 / 100.0;
							total = elementsProducts.getTotal() * 85.0 / 100.0;
							tax = elementsProducts.getTax() * 85.0 / 100.0;
						}
					}
					storeYearLongSubTotal = subTotal;
					storeYearLongTaxTotal = tax;				
				} 
				if (elementsProducts.getProductsType().toLowerCase().equals("d")) {
					dayLongNameType = "Day-long membership";
					flagDay = 1;
					codeDay = elementsProducts.getProductsCode();
					double subTotal = elementsProducts.getCost();
					double tax = elementsProducts.getTax();
					double total = elementsProducts.getTotal();
					String getItem = dayLongNameType + " @ "+ ((DayMembership) elementsProducts).getAddress();
					if(elementsProducts instanceof DayMembership) {
						DateTime getDayTime = s.parseDateTime(((DayMembership) elementsProducts).getStartDate());
						int month = Integer.parseInt(getDayTime.toString("MM"));
						if(month == 1) {
							subTotal = elementsProducts.getCost() / 2.0;
							total = elementsProducts.getTotal() / 2.0;
							tax = elementsProducts.getTax() / 2.0;
						} 
			
					}
					storeDayLongSubTotal = subTotal;
					storeDayLongTaxTotal = tax;
				} 
				if (elementsProducts.getProductsType().toLowerCase().equals("r")) {
					String getItem = null;
					String discountAnnounce = new String("");
					double subTotal = elementsProducts.getCost();
					double taxes = elementsProducts.getTax();
					double total = elementsProducts.getTotal();
					if(elementsProducts instanceof EquipmentRentals) {
						if(elementsProducts.getProductsCodeAttach() != null) {
								if(flagYear == 1) {
									if(codeYear.equals(elementsProducts.getProductsCodeAttach())) {
										discountAnnounce = " @ %5 off";
										subTotal = elementsProducts.getCost() * 95.0 / 100.0;
										taxes = elementsProducts.getTax() * 95.0 / 100.0;
										total = subTotal + taxes;
									}
								}
						}
					}
					storeEquipmentSubTotal = subTotal;
					storeEquipmentTaxTotal = taxes;
				} 
				if (elementsProducts.getProductsType().toLowerCase().equals("p")) {
					String freePasses = "";
					double subTotal = elementsProducts.getCost();
					double tax = elementsProducts.getTax();
					double total = elementsProducts.getTotal();
					if(elementsProducts instanceof ParkingPasses) {
						String getItem = null;
						if(elementsProducts.getProductsCodeAttach() != null) {
							if(elementsProducts.getProductsCodeAttach() != null) {
								if(flagYear == 1) {
									if(codeYear.equals(elementsProducts.getProductsCodeAttach())) {
										freePasses = " with 30 free";
										subTotal = elementsProducts.getCost() - Double.parseDouble(((ParkingPasses) elementsProducts).getParkingFee()) * 30.0;
										tax = 0.04 * subTotal;
										total = subTotal + tax;
									}
								} 
								if(flagDay == 1) {
									if(codeDay.equals(elementsProducts.getProductsCodeAttach())) {
										freePasses = " with 1 free";
										subTotal = elementsProducts.getCost() - Double.parseDouble(((ParkingPasses) elementsProducts).getParkingFee());
										tax = 0.04 * subTotal;
										total = subTotal + tax;
									}
								}
							} 
						}
					}
					storeFeeSubTotal = subTotal;
					storeFeeTaxTotal = tax;
				}
				
					
			}
			InvoiceSubtotalTotal = storeYearLongSubTotal + storeDayLongSubTotal + storeEquipmentSubTotal + storeFeeSubTotal; 
			InvoiceTaxTotal = storeYearLongTaxTotal + storeDayLongTaxTotal + storeEquipmentTaxTotal + storeFeeTaxTotal;
			totalTotal = InvoiceSubtotalTotal + InvoiceTaxTotal;
			studentDiscount = -1.0*(InvoiceSubtotalTotal * 8.0 / 100.0 + InvoiceTaxTotal);
			allTotalTotal = totalTotal + studentDiscount;
			if(element.getMembersCode().getType().toLowerCase().equals("student")) {
				additionalFee = 10.50;
			} 
		
			System.out.println(String.format("%-20s %-50s %-29s %s %9.2f  %s %9.2f  %s %12.2f  %s %14.2f  %s %10.2f", element.getInvoiceCode(),
					element.getMembersCode().getName(), element.getPersonsCode(), "$", InvoiceSubtotalTotal, "$", additionalFee, "$", InvoiceTaxTotal, "$", studentDiscount, "$", totalTotal));
			totals[0] += InvoiceSubtotalTotal;
			totals[1] += additionalFee;
			totals[2] += InvoiceTaxTotal;
			totals[3] += studentDiscount;
			totals[4] += allTotalTotal;
			
			// summaryReportArray = new double [] {InvoiceSubtotalTotal, 10.50, InvoiceTaxTotal, studentDiscount, totalTotal};  
		}
		return totals;
	}
}
