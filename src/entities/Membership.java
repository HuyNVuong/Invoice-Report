/* A subclass of Products classes that contains year and days passses */
package entities;

public abstract class Membership extends Products {
	
	public Membership(String productsCode, String productsType) {
		super(productsCode, productsType);
	}

	public abstract double getCost();
	public abstract double getTax();
	public abstract double getTotal();
	
}
