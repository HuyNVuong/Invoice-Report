/* EquipmentRetals classes that supports Porducts classes */
package entities;

public class EquipmentRentals {
	private String productCode;
	private String productType;
	private String productName;
	private String productCost;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCost() {
		return productCost;
	}
	public void setProductCost(String productCost) {
		this.productCost = productCost;
	}
	public EquipmentRentals(String productCode, String productType, String productName, String productCost) {
		this.productCode = productCode;
		this.productType = productType;
		this.productName = productName;
		this.productCost = productCost;
	}
	
}
