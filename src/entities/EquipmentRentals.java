/* EquipmentRetals classes that supports Porducts classes */
package entities;

public class EquipmentRentals extends Service {
	
	private String productName;
	private String productCost;
	private boolean isStudent;
	
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
	public EquipmentRentals(String productsCode, String productsType, String productName, String productCost) {
		super(productsCode, productsType);
		this.productName = productName;
		this.productCost = productCost;
	}
	private String membersType;
	
	public String getMembersType(Members members) {
		membersType = members.getType();
		return membersType;
	}
	
	@Override
	public double getCost() {
		double cost = Double.parseDouble(this.productCost);
		return cost;
	}
	@Override
	public double getTax() {
		double tax = 10.50;
		this.isStudent = membersType.toLowerCase().equals("s");
		System.out.println(this.isStudent);
		if(!isStudent) {
			tax = this.getCost() * 0.06;
		}
		return tax;
	}
	@Override
	public double getTotal() {
		this.isStudent = membersType.toLowerCase().equals("s");
		if(!isStudent) {
			return this.getCost() + this.getTax();
		} 
		return this.getCost() - (this.getCost() * 0.08) + this.getTax();
	}
	
	
	
}
