/* DayMembership classes that supports Products classes */
package entities;

public class DayMembership extends Membership {

	private String startDate;
	private ProductsAddress address;
	private String costPerUnit;
	private boolean isStudent;

	private String membersType;

	public String getMembersType(Members members) {
		membersType = members.getType();
		return membersType;
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

	public String getCostPerUnit() {
		return costPerUnit;
	}

	public void setCostPerUnit(String costPerUnit) {
		this.costPerUnit = costPerUnit;
	}

	public DayMembership(String productsCode, String productsType, String startDate, ProductsAddress address,
			String costPerUnit) {
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
		double tax = 10.50;
		this.isStudent = membersType.toLowerCase().equals("s");
		System.out.println(this.isStudent);
		if (!isStudent) {
			tax = this.getCost() * 0.06;
		}
		return tax;
	}

	@Override
	public double getTotal() {
		this.isStudent = membersType.toLowerCase().equals("s");
		if (!isStudent) {
			return this.getCost() + this.getTax();
		}
		return this.getCost() - (this.getCost() * 0.08) + this.getTax();
	}

}
