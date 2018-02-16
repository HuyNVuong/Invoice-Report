/* A subclass of Products classes that contains year and days passses */
package entities;

public class Membership extends Products {
	boolean isStudent;

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	
	public Membership(entities.YearMembership yearMembership, boolean isStudent) {
		super(yearMembership);
		this.isStudent = isStudent;
	}

	@Override
	public double getCost() {
		double cost = 0.0;
		if(!isStudent) {
			
		}
		return cost;
	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
