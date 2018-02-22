/* EquipmentRetals classes that supports Porducts classes */
package entities;

public class EquipmentRentals extends Service {

	private String productName;
	private String productCost;
	private boolean isStudent;
<<<<<<< HEAD
	private int quantity;
	private String attach;
	
=======

>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
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
<<<<<<< HEAD
	
	
	public boolean isStudent() {
		this.isStudent = membersType.toLowerCase().equals("s");
		return isStudent;
	}
	public void setStudent(boolean isStudent) {
		this.isStudent = membersType.toLowerCase().equals("s");
		this.isStudent = isStudent;
	}
=======

>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
	public EquipmentRentals(String productsCode, String productsType, String productName, String productCost) {
		super(productsCode, productsType);
		this.productName = productName;
		this.productCost = productCost;
		this.quantity = 0;
		this.attach = null;
		
	}
<<<<<<< HEAD
	
	public EquipmentRentals(EquipmentRentals service, int quantity) {
		super(service, quantity);
		this.productName = service.productName;
		this.productCost = service.productCost;
		this.quantity = service.getProductsQuantity();
		// TODO Auto-generated constructor stub
	}
	public EquipmentRentals(EquipmentRentals service, int quantity, String attach) {
		super(service, quantity, attach);
		this.productName = service.productName;
		this.productCost = service.productCost;
		this.quantity = service.getProductsQuantity();
		this.attach = service.getProductsCodeAttach();
	}
=======
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git

	private String membersType;

	public String getMembersType(Members members) {
		membersType = members.getType();
		return membersType;
	}

	@Override
	public double getCost() {
		double cost = Double.parseDouble(this.productCost);
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
			tax = this.getCost() * 0.04;
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
		}
		return this.getCost() - (this.getCost() * 0.08) + this.getTax();
	}

	@Override
	public String toString() {
		return "EquipmentRentals [" + productName + " " + productCost + " " + membersType + "]";
	}

}
