package adt;

import java.util.Comparator;
import entities.Invoice;

public class TotalComparator implements Comparator<Invoice> {

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		if (inv1.getSubtotal() == inv2.getSubtotal()) {
			return 0;
		} else if (inv1.getSubtotal() < inv2.getSubtotal()) {
			return -1;
		} else {
			return 1;
		}
	}

}
