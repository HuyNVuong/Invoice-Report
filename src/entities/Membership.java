/* A subclass of Products classes that contains year and days passses */
package entities;

public abstract class Membership extends Products { 
	
	private int quantity;
	
	public Membership(String productsCode, String productsType) {
		super(productsCode, productsType);
		this.quantity = 0;
	}
	public Membership(Membership membership, int quantity) {
		super(membership, quantity);
		quantity = membership.getProductsQuantity();
	}
	public abstract double getCost();

	public abstract double getTax();

	public abstract double getTotal();

}
