/* A subclass of Products classes that contains year and days passses */
package entities;

public abstract class Membership extends Products {

	public Membership(entities.YearMembership yearMembership) {
		super(yearMembership);
		// TODO Auto-generated constructor stub
	}
	public Membership(entities.DayMembership dayMembership) {
		super(dayMembership);
		// TODO Auto-generated constructor stub
	}
	public abstract double getCost();
	public abstract double getTax();
	public abstract double getTotal();
	
}
