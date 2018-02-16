/* DayMembership classes that supports Products classes */
package entities;

public class DayMembership extends Membership{
	private String startDate;
	private ProductsAddress address;
	private String costPerUnit;

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
	public String getCostPerUnit() {
		return costPerUnit;
	}
	public void setCostPerUnit(String costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	
	
	public DayMembership(String productsCode, String productsType,
			String startDate, ProductsAddress address, String costPerUnit) {
		super(productsCode, productsType);
		this.startDate = startDate;
		this.address = address;
		this.costPerUnit = costPerUnit;
	}
	@Override
	public double getCost() {
		double cost = Double.parseDouble(this.costPerUnit);
		return cost;
	}
	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
