package entities;

public abstract class Service extends Products {
	
	private int quantity;
	private String attach;
	
	public Service(String productsCode, String productsType) {
		super(productsCode, productsType);
		this.quantity = 0;
		this.attach = null;
	}
	
	public Service(Service service, int quantity) {
		super(service, quantity);
		quantity = service.getProductsQuantity();
	} 
	public Service(Service service, int quantity, String attach) {
		super(service, quantity, attach);
		quantity = service.getProductsQuantity();
		attach = service.getProductsCodeAttach();
	}


	public abstract double getCost();
	public abstract double getTax();
	public abstract double getTotal();
	
}
