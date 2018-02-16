/* Parking passes that supports Products classes */
package entities;

public class ParkingPasses extends Service {

	private String parkingFee;
	private boolean isStudent;
	
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

	private String membersType;
	
	public String getMembersType(Members members) {
		membersType = members.getType();
		return membersType;
	}
	
	@Override
	public double getCost() {
		double cost = Double.parseDouble(this.parkingFee);
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
