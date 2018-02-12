/* DayMembership classes that supports Products classes */
package entities;

public class DayMembership {
	private String productCode;
	private String productType;
	private String startDate;
	private ProductsAddress address;
	private String cost;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public ProductsAddress getAddress() {
		return address;
	}
	public void setAddress(ProductsAddress address) {
		this.address = address;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public DayMembership(String productCode, String productType, String startDate, ProductsAddress address,
			String cost) {
		this.productCode = productCode;
		this.productType = productType;
		this.startDate = startDate;
		this.address = address;
		this.cost = cost;
	}
	
}
