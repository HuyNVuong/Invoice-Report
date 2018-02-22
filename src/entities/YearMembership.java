/* Supports Products classes */
package entities;

public class YearMembership extends Membership {

	private String startDate;
	private String endDate;
	private ProductsAddress address;
	private String membershipName;
	private String pricePerUnit;
	private boolean isStudent;
<<<<<<< HEAD
	private int quantity;
	
	
=======

>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
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
<<<<<<< HEAD
	
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
=======

	@Override
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
	public double getCost() {
		double cost = Double.parseDouble(this.pricePerUnit);
		return cost * quantity;
	}

	@Override
<<<<<<< HEAD
	public double getTax() {
		double tax = 0.0;
		//this.isStudent = membersType.toLowerCase().equals("s");
		if(!isStudent) {
=======
	public double getTax() { //FIXME 15% discount if purchased in first month of the year
		double tax = 0;
		this.isStudent = membersType.toLowerCase().equals("s"); // student tax is $0, one time fee of $10.50 to register as student
		if (!isStudent) {
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
			tax = this.getCost() * 0.06;
		}
		return tax;
	}

	@Override
	public double getTotal() {
<<<<<<< HEAD
		// this.isStudent = membersType.toLowerCase().equals("s");
		return this.getCost() + this.getTax();
=======
		this.isStudent = membersType.toLowerCase().equals("s");
		if (!isStudent) {
			return this.getCost() + this.getTax();
		}
		return this.getCost() - (this.getCost() * 0.08) + this.getTax();
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
	}
}
