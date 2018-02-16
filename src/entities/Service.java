package entities;

public class Service extends Products {
boolean isStudent;

	

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}


	public Service(entities.ParkingPasses parkingPasses, boolean isStudent) {
		super(parkingPasses);
		this.isStudent = isStudent;
	}
	public Service(entities.EquipmentRentals equipmentRentals, boolean isStudent) {
		super(equipmentRentals);
		this.isStudent = isStudent;
	}

	@Override
	public double getCost() {
		double cost = 0.0;
		return cost;
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
