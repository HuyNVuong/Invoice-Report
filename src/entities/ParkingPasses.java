/* Parking passes that supports Products classes */
package entities;

public class ParkingPasses extends Service {

	private String parkingFee;
	
	public String getParkingFee() {
		return parkingFee;
	}
	public void setParkingFee(String parkingFee) {
		this.parkingFee = parkingFee;
	}
	
	public ParkingPasses(String productsCode, String productsType, String parkingFee) {
		super(productsCode, productsType);
		this.parkingFee = parkingFee;
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
