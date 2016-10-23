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
		Store.addCompleteSale(this);
	}
	
	public void makeMenuItem(ItemDetails item, int quantity) {
		orderedItems.add(new MenuItem(item, quantity));
	}
	
	public void makePayment(boolean isCash, double amount) {
		payment = isCash ? new Cash(amount) : new Credit(amount);
	}
	
	public double getTotal() {
		double total = 0.0;
		for (MenuItem item : orderedItems) {
			total += item.getSubtotal();
		}
		return total;
	}
	
	public double getBalance() {
		return getTotal() - payment.getAmount();
	}
}
