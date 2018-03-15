/* Parking passes that supports Products classes */
package entities;

public class ParkingPasses extends Service {

	private String parkingFee;
	private boolean isStudent;

	private String membersType;
	private int quantity;
	private String attach;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(String parkingFee) {
		this.parkingFee = parkingFee;
	}

	public String getMembersType(Members members) {
		membersType = members.getType();
		return membersType;
	}

	public boolean isStudent() {
		this.isStudent = membersType.toLowerCase().equals("s");
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = membersType.toLowerCase().equals("s");
		this.isStudent = isStudent;
	}

	public ParkingPasses(String productsCode, String productsType, String parkingFee) {
		super(productsCode, productsType);
		this.parkingFee = parkingFee;
		this.attach = null;
		this.quantity = 0;
	}

	public ParkingPasses(ParkingPasses service, int quantity, String attach) {
		super(service, quantity, attach);
		this.parkingFee = service.parkingFee;
		this.quantity = service.getProductsQuantity();
		this.attach = service.getProductsCodeAttach();

	}

	public ParkingPasses(ParkingPasses service, int quantity) {
		super(service, quantity);
		this.parkingFee = service.parkingFee;
		this.quantity = service.getProductsQuantity();
	}

	@Override
	public double getCost() {
		double cost = Double.parseDouble(this.parkingFee);
		return cost * quantity;
	}

	@Override
	public double getTax() {

		double tax = 0.0;

		if (!isStudent) {
			tax = this.getCost() * 0.04;
		}
		return tax;
	}

	@Override
	public double getTotal() {
		return this.getCost() + this.getTax();
	}

}
