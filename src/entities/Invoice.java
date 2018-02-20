package entities;

import java.util.List;

public class Invoice {
	private String invoiceCode;
	private String membersCode;
	private String personsCode;
	private String invoiceDate;
	private Products products;
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	
	public String getMembersCode() {
		return membersCode;
	}
	public void setMembersCode(String membersCode) {
		this.membersCode = membersCode;
	}
	public String getPersonsCode() {
		return personsCode;
	}
	public void setPersonsCode(String personsCode) {
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
	public Invoice(String invoiceCode, String membersCode, String personsCode, String invoiceDate,
			Products products) {
		this.invoiceCode = invoiceCode;
		this.membersCode = membersCode;
		this.personsCode = personsCode;
		this.invoiceDate = invoiceDate;
		this.products = products;
	}
	@Override
	public String toString() {
		return "Invoice [ " + invoiceCode + " " + membersCode + " " + personsCode
				+" " + invoiceDate + " " + products + "]";
	}
	
	

}
