/* Parking passes that supports Products classes */
package entities;

public class ParkingPasses {
	private String productCode;
	private String prodcutType;
	private String parkingFee;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProdcutType() {
		return prodcutType;
	}
	public void setProdcutType(String prodcutType) {
		this.prodcutType = prodcutType;
	}
	public String getParkingFee() {
		return parkingFee;
	}
	public void setParkingFee(String parkingFee) {
		this.parkingFee = parkingFee;
	}
	public ParkingPasses(String productCode, String prodcutType, String parkingFee) {
		this.productCode = productCode;
		this.prodcutType = prodcutType;
		this.parkingFee = parkingFee;
	}
	
}
