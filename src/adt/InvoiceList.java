package adt;

import java.util.Iterator;

import entities.Invoice;

public class InvoiceList implements Iterable<Invoice>{

	@Override
	public Iterator<Invoice> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	private InvoiceNode<Invoice> start;
	private int size;
	
}
