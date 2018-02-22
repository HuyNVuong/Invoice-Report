/* A subclass of Products classes that contains year and days passses */
package entities;

<<<<<<< HEAD
public abstract class Membership extends Products { 
	
	private int quantity;
=======
public abstract class Membership extends Products {

>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
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
