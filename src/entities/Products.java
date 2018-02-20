/* Product classes that provide neccesarry variable for products classes and call DayMembership, YearMembership, ParkingPasses and EquipmentRentals classes */
package entities;

public abstract class Products {
	private String productsCode;
	private String productsType;
	private int productsQuantity;
	
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
	public Products(String productsCode, String productsType) {
		this.productsCode = productsCode;
		this.productsType = productsType;
		this.productsQuantity = 0;
	}
	public Products(Products products) {
		this.productsCode = productsCode;
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
