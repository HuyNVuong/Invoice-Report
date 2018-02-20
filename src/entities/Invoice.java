package entities;

import java.util.List;

public class Invoice {
	private String invoiceCode;
	private Members membersCode;
	private Persons personsCode;
	private String invoiceDate;
	private Products products;
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public Members getMembersCode() {
		return membersCode;
	}
	public void setMembersCode(Members membersCode) {
		this.membersCode = membersCode;
	}
	public Persons getPersonsCode() {
		return personsCode;
	}
	public void setPersonsCode(Persons personsCode) {
		this.personsCode = personsCode;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	
	public Invoice(String invoiceCode, Members membersCode, Persons personsCode, String invoiceDate,
			Products products) {
		this.invoiceCode = invoiceCode;
		this.membersCode = membersCode;
		this.personsCode = personsCode;
		this.invoiceDate = invoiceDate;
		this.products = products;
	}
	public String toString() {
		return "Invoice [ " + invoiceCode + " " + membersCode + " " + personsCode
				+" " + invoiceDate + " " + products + "]";
	}
	
	

}
