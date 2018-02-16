package entities;

public class Invoice {
	private String invoiceCode;
	private Members membersCode;
	private Persons personsCode;
	private String invoiceDate;
	private Products productsCode;
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
	public Products getProductsCode() {
		return productsCode;
	}
	public void setProductsCode(Products productsCode) {
		this.productsCode = productsCode;
	}
	public Invoice(String invoiceCode, Members membersCode, Persons personsCode, String invoiceDate,
			Products productsCode) {
		this.invoiceCode = invoiceCode;
		this.membersCode = membersCode;
		this.personsCode = personsCode;
		this.invoiceDate = invoiceDate;
		this.productsCode = productsCode;
	}
	
}
