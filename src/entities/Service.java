package entities;

public class Service extends Products {
	boolean isParkingPasses;

	public boolean isParkingPasses() {
		return isParkingPasses;
	}

	public void setParkingPasses(boolean isParkingPasses) {
		this.isParkingPasses = isParkingPasses;
	}

	public Service(entities.ParkingPasses parkingPasses, boolean isParkingPasses) {
		super(parkingPasses);
		this.isParkingPasses = isParkingPasses;
	}
	public Service(entities.EquipmentRentals equipmentRentals, boolean isParkingPasses) {
		super(equipmentRentals);
		this.isParkingPasses = isParkingPasses;
	}

	@Override
	public double getDateCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPassesCost() {
		// TODO Auto-generated method stub
		
		return 0;
	}

	
}
