/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale {
	public boolean isComplete;
	public Date time;
	public Payment payment;
	public List<MenuItem> orderedItems;
	
	public Sale() {
		orderedItems = new ArrayList<MenuItem>();
	}
	
	public void becomeComplete() {
		// Responsibility: DOING - recording Sale completed status
		isComplete = true;
		Store.addCompleteSale(this);
	}
	
	public void addMenuItem(MenuItem item) {
		// Responsibility: DOING - adding the item and quantity to the order
		orderedItems.add(item);
	}
	
	public void makePayment(boolean isCash, double amount) {
		// Responsibility: DOING - the creation of the Payment object based on payment type
		payment = isCash ? new Cash(amount) : new Credit(amount);
	}
	
	public double getTotal() {
		// Responsibility: KNOWING - the total price that needs to be paid by the customer
		double total = 0.0;
		for (MenuItem item : orderedItems) {
			total += item.getSubtotal();
		}
		return total;
	}
	
	public double getChange() {
		// Responsibility: KNOWING - the amount to be handed over as change after the payment
		return payment.getAmount() - getTotal();
	}
}
