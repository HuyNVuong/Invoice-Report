package entities;

import java.util.List;

public class Invoice {
	private String invoiceCode;
	private Members membersCode;
	private Persons personsCode;
	private String invoiceDate;
	private Products products;
	private double subTotal = 0.0;
	private double fee = 0.0;
	private double taxes = 0.0;
	private double discount = 0.0;
	private double total = 0.0;
	
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
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
		this.subTotal = subTotal;
	}
	public String toString() {
		return "Invoice [ " + invoiceCode + " " + membersCode + " " + personsCode
				+" " + invoiceDate + " " + products + "]";
	}
	public double getSubTotal() {
		subTotal = products.getCost();
		return subTotal;
	}
	

}
