/* A subclass of Products classes that contains year and days passses */
package entities;

public class Membership extends Products {
	boolean isYearPass;

	public Membership(entities.YearMembership yearMembership, boolean isYearPass) {
		super(yearMembership);
		this.isYearPass = isYearPass;
	}

	public Membership(entities.DayMembership dayMembership, boolean isYearPass) {
		super(dayMembership);
		this.isYearPass  = isYearPass;
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
