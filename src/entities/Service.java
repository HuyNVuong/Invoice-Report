package entities;

public abstract class Service extends Products {

	public Service(String productsCode, String productsType) {
		super(productsCode, productsType);
		// TODO Auto-generated constructor stub
	}
	public abstract double getCost();
	public abstract double getTax();
	public abstract double getTotal();
	
}
