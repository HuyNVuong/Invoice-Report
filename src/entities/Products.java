/* Product classes that provide neccesarry variable for products classes and call DayMembership, YearMembership, ParkingPasses and EquipmentRentals classes */
package entities;

public abstract class Products {
	private String productsCode;
	private String productsType;
	private YearMembership YearMembership;
	private DayMembership DayMembership;
	private ParkingPasses ParkingPasses;
	private EquipmentRentals EquipmentRentals;
	
	public String getProductsCode() {
		return productsCode;
	}
	public void setProductsCode(String productsCode) {
		this.productsCode = productsCode;
	}
	public String getProductsType() {
		return productsType;
	}
	public void setProductsType(String productsType) {
		this.productsType = productsType;
	}
	public YearMembership getYearMembership() {
		return YearMembership;
	}
	public void setYearMembership(YearMembership yearMembership) {
		YearMembership = yearMembership;
	}
	public DayMembership getDayMembership() {
		return DayMembership;
	}
	public void setDayMembership(DayMembership dayMembership) {
		DayMembership = dayMembership;
	}
	public ParkingPasses getParkingPasses() {
		return ParkingPasses;
	}
	public void setParkingPasses(ParkingPasses parkingPasses) {
		ParkingPasses = parkingPasses;
	}
	public EquipmentRentals getEquipmentRentals() {
		return EquipmentRentals;
	}
	public void setEquipmentRentals(EquipmentRentals equipmentRentals) {
		EquipmentRentals = equipmentRentals;
	}
	public Products(entities.YearMembership yearMembership) {
		YearMembership = yearMembership;
	}
	public Products(entities.EquipmentRentals equipmentRentals) {
		EquipmentRentals = equipmentRentals;
	}
	public Products(entities.DayMembership dayMembership) {
		DayMembership = dayMembership;
	}
	public Products(entities.ParkingPasses parkingPasses) {
		ParkingPasses = parkingPasses;
	}
	public abstract double getCost();
	public abstract double getTax();
	public abstract double getTotal();
	
}
