package adt;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import entities.Invoice;

public class InvoiceList<T> implements Iterable<Invoice> {

	private Comparator<Invoice> comp;
	private InvoiceNode<Invoice> start;

	private int size;


	@Override
	public Iterator<Invoice> iterator() {
		return new IteratorInvoice();
	}
	
	class IteratorInvoice implements Iterator<Invoice> {
		int index = 0; // FIXME how/why did Hasan want us to use this?
		
		@Override 
		public boolean hasNext() {
			if (start != null) {
				return true;
			}
			return false;
		}
		
		@Override
		public Invoice next() {
			InvoiceNode<Invoice> nextNode = start.getNext();
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				Invoice result = nextNode.getItem();
				return result;
			}
		}
		
		@Override
		public void remove() {
			if (start == null) {
				throw new NoSuchElementException();
			} else {
				start = start.getNext();
			}
		}
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

	public void add(Invoice item) { // order is highest to lowest
		InvoiceNode<Invoice> newInvoiceNode = new InvoiceNode<Invoice>(item);

		if (start == null) { // if no nodes exist
			newInvoiceNode = this.start;
			size++;
		} else if (size == 1) { // if 1 node exists
			if (this.comp.compare(newInvoiceNode.getItem(), start.getItem()) == 1) { // if new node is larger than start
				newInvoiceNode.setNext(start);
				start = newInvoiceNode;
				size++;
			} else {
				start.setNext(newInvoiceNode); // if new node is smaller than start
				size++;
			}
		} else { // 2 or more nodes exist 
			if (this.comp.compare(newInvoiceNode.getItem(), start.getItem()) == 1) { // if new node is bigger than start
				newInvoiceNode.setNext(start);
				start = newInvoiceNode;
				size++;
			}	
			else { // if new node is less than or equal to nextNode
				InvoiceNode<Invoice> currentNode = start;
				boolean added = false;
				while (currentNode.getNext() != null) {
					if(this.comp.compare(newInvoiceNode.getItem(), currentNode.getNext().getItem()) >= 0) {
						newInvoiceNode.setNext(currentNode.getNext());
						currentNode.setNext(newInvoiceNode);
						added = true;
						size++;
					}
					currentNode = currentNode.getNext();
				}
					if (!added) {
						//add to end of list
						currentNode.setNext(newInvoiceNode);
						size++;
					}
			}
		}

		
	}

}
