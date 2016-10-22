/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

package edu.fit.coronary_castle;

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
	
	public void makeMenuItem(MenuItem item) {
		orderedItems.add(item);
	}
	
	public void makePayment(boolean isCash) {
		payment = isCash ? new Cash() : new Credit();
	}
	
	public double getTotal() {
		double total = 0.0;
		for (MenuItem item : orderedItems) {
			total += item.getSubtotal();
		}
		return total;
	}
}
