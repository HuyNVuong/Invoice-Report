/* This class is a sub-classes from abstract class Members */
package entities;

public class General extends Members {
	private boolean isStudent; // if is true, then its a student, if false, then it is a general customer

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public General(String memberCode, String type, String primaryContact, String name, MembersAddress address) {
		super(memberCode, type, primaryContact, name, address);
		//this.isStudent = isStudent;
		this.isStudent = type.toLowerCase().equals("s");
	}

	@Override
	public double getDiscount() {
		double discount = 0.0;
		if(isStudent) {
			discount = 0.08;
		} else {
			discount = 0.0;
		}
		return discount;
	}

	@Override
	public double getExtraFees() {
		double fees = 0.0;
		if(isStudent) {
			fees = 10.50;
		} else {
			fees = 0.0;
		}
		return fees;
	}
	
	
	
}

