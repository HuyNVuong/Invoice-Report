package adt;

import entities.Invoice;

public class InvoiceNode<T> {

	private InvoiceNode<T> next;
	private Invoice item;

	public InvoiceNode(Invoice item) {
		super();
		this.item = item;
		this.next = null;
	}

	public InvoiceNode<T> getNext() {
		return next;
	}

	public void setNext(InvoiceNode<T> next) {
		this.next = next;
	}

	public Invoice getItem() {
		return item;
	}

	public void setItem(Invoice item) {
		this.item = item;
	}

}
