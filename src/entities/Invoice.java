package entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Invoice {
	private String invoiceCode;
	private Members membersCode;
	private Persons personsCode;  
	private String invoiceDate;
	private List<Products> products;
	private double subtotal;
	
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

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

	public Invoice(String invoiceCode, Members membersCode, Persons personsCode, String invoiceDate) {
		super();
		this.invoiceCode = invoiceCode;
		this.membersCode = membersCode;
		this.personsCode = personsCode;
		this.invoiceDate = invoiceDate;
		this.products = new ArrayList<Products>();
	}

	// Method for adding products to the products list
	public void addItem(Products invoiceProduct) {
		this.products.add(invoiceProduct);
		double total = 0;
		for (Products p : this.getProducts()) {
			total += p.getTotal();
		}
		this.subtotal = total;

	}
	@Override
	public String toString() {
		return "Invoice [ " + invoiceCode + " " + membersCode + " " + personsCode + " " + invoiceDate + " " + products
				+ "]";
	}


}
