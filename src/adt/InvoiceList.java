package adt;

import java.util.Comparator;
import java.util.Iterator;

import entities.Invoice;

public class InvoiceList implements Iterable<Invoice>{

	private InvoiceNode<Invoice> start;
	private int size;
	private Comparator<Invoice> comp;
	private Invoice invoiceArray;
	
	@Override
	public Iterator<Invoice> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public InvoiceList(Comparator<Invoice> comp) {
		super();
		this.start = null;
		this.size = 0;
		this.comp = comp;
	}

	public Comparator<Invoice> getComp() {
		return comp;
	}

	public void add(Invoice item) {
		InvoiceNode<Invoice> newInvoiceNode = new InvoiceNode<Invoice>(item);
		
		if (start == null) {
			newInvoiceNode = this.start;
		}
		else if (size == 1) {
			if()
				newInvoiceNode.setNext(start);
				start = newInvoiceNode;
			
		} 
		
	}
	
	
	
}
