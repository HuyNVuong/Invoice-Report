/* Supports Products classes */
package entities;

public class YearMembership extends Membership {
	private String productCode;
	private String productType;
	private String startDate;
	private String endDate;
	private ProductsAddress address;
	private String membershipName;
	private String pricePerUnit;
	private boolean isStudent;
	
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
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public ProductsAddress getAddress() {
		return address;
	}
	public void setAddress(ProductsAddress address) {
		this.address = address;
	}
	public String getMembershipName() {
		return membershipName;
	}
	public void setMembershipName(String membershipName) {
		this.membershipName = membershipName;
	}
	public String getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public YearMembership(YearMembership yearMembership, String productCode, String productType,
			String startDate, String endDate, ProductsAddress address, String membershipName, String pricePerUnit) {
		super(yearMembership);
		this.productCode = productCode;
		this.productType = productType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.membershipName = membershipName;
		this.pricePerUnit = pricePerUnit;
	}
	@Override
	public double getCost() {
		double cost = Double.parseDouble(this.pricePerUnit);
		return cost;
	}
	@Override
	public double getTax() {
		
		return 0;
	}
	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
