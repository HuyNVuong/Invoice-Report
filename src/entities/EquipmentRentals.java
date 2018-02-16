/* EquipmentRetals classes that supports Porducts classes */
package entities;

public class EquipmentRentals extends Service {
	
	private String productName;
	private String productCost;
	
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
	@Override
	public double getCost() {
		// TODO Auto-generated method stub
		return 0;
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
