package adt;

import java.util.Comparator;
import entities.Invoice;

public class TotalComparator implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		if (inv1.getInvoiceCode().equals(inv2.getInvoiceCode())){
			
		}
		return 0;
	}
	
}
