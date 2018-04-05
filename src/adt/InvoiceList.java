package adt;

import java.util.Comparator;
import java.util.Iterator;

import entities.Invoice;

public class InvoiceList implements Iterable<Invoice> {

	private InvoiceNode<Invoice> start;
	private InvoiceNode<Invoice> nextNode;
	private InvoiceNode<Invoice> currentNode;
	private int size;
	private Comparator<Invoice> comp;

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

	public void add(Invoice item) { /// HIGHEST TO LOWEST --> in the notes, Hasan sets it as lowest to highest: be careful
		InvoiceNode<Invoice> newInvoiceNode = new InvoiceNode<Invoice>(item);

		if (start == null) {
			newInvoiceNode = this.start;
			size++;
		} else if (size == 1) {
			if (this.comp.compare(newInvoiceNode.getItem(), start.getItem()) == 1) { // if new node is larger than start
				newInvoiceNode.setNext(start);
				start = newInvoiceNode;
				size++;
			} else {
				start.setNext(newInvoiceNode); // if new node is smaller than start
				size++;
			}
		} else {
			if (this.comp.compare(newInvoiceNode.getItem(), start.getItem()) == 1) { // if new node is bigger than start
				newInvoiceNode.setNext(start);
				start = newInvoiceNode;
				size++;
			}
			else if (this.comp.compare(newInvoiceNode.getItem(), start.getNext().getItem()) == 1) { // if new node is bigger than nextNode
				
				start.setNext(newInvoiceNode);
				newInvoiceNode.setNext(nextNode);
				size++;
			}	
			else { // if new node is less than or equal to nextNode
				currentNode.setNext(newInvoiceNode);
				currentNode = newInvoiceNode;
				size++;
			}
		}

	}

}
