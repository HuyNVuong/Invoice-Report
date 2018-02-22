/* Parking passes that supports Products classes */
package entities;

public class ParkingPasses extends Service {

	private String parkingFee;
	private boolean isStudent;
<<<<<<< HEAD
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
=======

>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
	public String getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(String parkingFee) {
		this.parkingFee = parkingFee;
	}
<<<<<<< HEAD
=======

	public ParkingPasses(String productsCode, String productsType, String parkingFee) {
		super(productsCode, productsType);
		this.parkingFee = parkingFee;
	}

	private String membersType;

>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
	public String getMembersType(Members members) {
		membersType = members.getType();
		return membersType;
	}
<<<<<<< HEAD
	
	
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
=======

>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
	@Override
	public double getCost() {
		double cost = Double.parseDouble(this.parkingFee);
		return cost * quantity;
	}

	@Override
	public double getTax() {
<<<<<<< HEAD
		double tax = 0.0;
		
		if(!isStudent) {
=======
		double tax = 10.50;
		this.isStudent = membersType.toLowerCase().equals("s");
		System.out.println(this.isStudent);
		if (!isStudent) {
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
			tax = this.getCost() * 0.04;
		}
		return tax;
	}

	@Override
	public double getTotal() {
<<<<<<< HEAD
		
		if(!isStudent) {
=======
		this.isStudent = membersType.toLowerCase().equals("s");
		if (!isStudent) {
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
			return this.getCost() + this.getTax();
<<<<<<< HEAD
		} 
		return this.getCost() + this.getTax();
=======
		}
		return this.getCost() - (this.getCost() * 0.08) + this.getTax();
>>>>>>> branch 'master' of git@git.unl.edu:vuongnguyenhuy/156-Project.git
	}

}
