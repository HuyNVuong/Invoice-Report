/* DayMembership classes that supports Products classes */
package entities;

public class DayMembership extends Membership {

	private String startDate;
	private ProductsAddress address;
	private String costPerUnit;
	private boolean isStudent;
<<<<<<< HEAD
	private int quantity;
=======

>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
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
<<<<<<< HEAD
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isStudent() {
		this.isStudent = membersType.toLowerCase().equals("s");
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = membersType.toLowerCase().equals("s");
		this.isStudent = isStudent;
	}

	public DayMembership(String productsCode, String productsType,
			String startDate, ProductsAddress address, String costPerUnit) {
=======

	public DayMembership(String productsCode, String productsType, String startDate, ProductsAddress address,
			String costPerUnit) {
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
		super(productsCode, productsType);
		this.startDate = startDate;
		this.address = address;
		this.costPerUnit = costPerUnit;
		this.quantity = 0;
	}
<<<<<<< HEAD
	//Copy constructor
	public DayMembership(DayMembership dayMembership, int quantity) {
		super(dayMembership, quantity);
		this.startDate = dayMembership.startDate;
		this.address = dayMembership.address;
		this.costPerUnit = dayMembership.costPerUnit;
		this.quantity = dayMembership.getProductsQuantity();
	}
=======
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git

	@Override
	public double getCost() {
		double cost = Double.parseDouble(this.costPerUnit);
		return cost * quantity;
	}

	@Override
	public double getTax() {
<<<<<<< HEAD
		double tax = 0.0; 
		// this.isStudent = membersType.toLowerCase().equals("s");
		if(!isStudent) {
=======
		double tax = 10.50;
		this.isStudent = membersType.toLowerCase().equals("s");
		System.out.println(this.isStudent);
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
		if(!isStudent) {
=======
		this.isStudent = membersType.toLowerCase().equals("s");
		if (!isStudent) {
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
			return this.getCost() + this.getTax();
<<<<<<< HEAD
		} 
		return this.getCost() + this.getTax();
=======
		}
		return this.getCost() - (this.getCost() * 0.08) + this.getTax();
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
	}

}
