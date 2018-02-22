/* Supports Products classes */
package entities;

public class YearMembership extends Membership {

	private String startDate;
	private String endDate;
	private ProductsAddress address;
	private String membershipName;
	private String pricePerUnit;
	private boolean isStudent;

	private int quantity;
	
	

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

	private String membersType;

	public String getMembersType(Members members) {
		membersType = members.getType();
		return membersType;
	}

	public boolean isStudent() {
		this.isStudent = membersType.toLowerCase().equals("s");
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		isStudent = membersType.toLowerCase().equals("s");
		this.isStudent = isStudent;
	}

	public YearMembership(String productCode, String productType, String startDate, String endDate,
			ProductsAddress address, String membershipName, String pricePerUnit) {
		super(productCode, productType);

		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.membershipName = membershipName;
		this.pricePerUnit = pricePerUnit;
		// this.isStudent = membersType.toLowerCase().equals("s");
		this.quantity = 0;
	}

	
	// Copy constructor
	public YearMembership(YearMembership yearMembership, int quantity) {
		super(yearMembership, quantity);
		this.startDate = yearMembership.startDate;
		this.endDate = yearMembership.endDate;
		this.address = yearMembership.address;
		this.membershipName = yearMembership.membershipName;
		this.pricePerUnit = yearMembership.pricePerUnit;
		this.quantity = yearMembership.getProductsQuantity();
	}


	@Override

	public double getCost() {
		double cost = Double.parseDouble(this.pricePerUnit);
		return cost * quantity;
	}

	@Override

	public double getTax() {
		double tax = 0.0;
		//this.isStudent = membersType.toLowerCase().equals("s");
		if(!isStudent) {
			tax = this.getCost() * 0.06;
		}
		return tax;
	}

	@Override
	public double getTotal() {

		// this.isStudent = membersType.toLowerCase().equals("s");
		return this.getCost() + this.getTax();

	}
}
