/* Product classes that provide neccesarry variable for products classes and call DayMembership, YearMembership, ParkingPasses and EquipmentRentals classes */
package entities;

public abstract class Products {
	private String productsCode;
	private String productsType;
	private int productsQuantity;
	private String productsCodeAttach;
	
	public String getProductsCode() {
		return productsCode;
	}
	public void setProductsCode(String productsCode) {
		this.productsCode = productsCode;
	}
	public String getProductsType() {
		return productsType;
	}
	public void setProductsType(String productsType) {
		this.productsType = productsType;
	}
	
	
	public int getProductsQuantity() {
		return productsQuantity;
	}
	public void setProductsQuantity(int productsQuantity) {
		this.productsQuantity = productsQuantity;
	}
	
	public String getProductsCodeAttach() {
		return productsCodeAttach;
	}
	public void setProductsCodeAttach(String productsCodeAttach) {
		this.productsCodeAttach = productsCodeAttach;
	}
	public Products(String productsCode, String productsType) {
		super();
		this.productsCode = productsCode;
		this.productsType = productsType;
		this.productsCodeAttach = productsCodeAttach;
		this.productsQuantity = 0;
	}
	// Copy constructor for service
	public Products(Products products, int quantity, String attach) {
		this.productsCode = products.productsCode;
		this.productsType = products.productsType;
		this.productsCodeAttach = products.productsCodeAttach;
		this.productsQuantity = products.productsQuantity;
	}
	// Copy constructor for Membership
	public Products(Products products, int quantity) {
		this.productsCode = products.productsCode;
		this.productsType = products.productsType;
		this.productsQuantity = products.productsQuantity;
	}
	public abstract double getCost();
	public abstract double getTax();
	public abstract double getTotal();
	@Override
	public String toString() {
		return "Products ["+ productsCode + " " + productsType + "]";
	}
	
}
