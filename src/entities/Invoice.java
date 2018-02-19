package entities;

import java.util.List;

public class Invoice {
	private String invoiceCode;
	private Members membersCode;
	private Persons personsCode;
	private String invoiceDate;
	private List<Products> products;
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
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	public Invoice(String invoiceCode, Members membersCode, Persons personsCode, String invoiceDate,
			List<Products> products) {
		this.invoiceCode = invoiceCode;
		this.membersCode = membersCode;
		this.personsCode = personsCode;
		this.invoiceDate = invoiceDate;
		this.products = products;
	}
	

}
