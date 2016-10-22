/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

package edu.fit.coronary_castle;

public class Register {
	public Sale currentSale;
	
	public void newSale() {
		currentSale = new Sale();
	}
	
	public void endSale() {
		currentSale.becomeComplete();
	}
	
	public void enterItem(MenuItem item) {
		currentSale.makeMenuItem(item);
	}
	
	public void makePayment(boolean type) {
		currentSale.makePayment(type);
	}
}
