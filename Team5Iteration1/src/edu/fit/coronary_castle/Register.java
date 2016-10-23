/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

package edu.fit.coronary_castle;

public class Register {
	public Sale currentSale;
	public MenuCatalog catalog;
	
	private static Register instance;
	
	private Register() {
		catalog = MenuCatalog.getInstance();
	}
	
	public static Register getInstance() {
		if (instance == null) {
			instance = new Register();
		}
		return instance;
	}
	
	public void newSale() {
		currentSale = new Sale();
	}
	
	public void endSale() {
		currentSale.becomeComplete();
	}
	
	public void enterItem(int id, int quantity) {
		ItemDetails desc = catalog.getItemDetails(id);
		currentSale.makeMenuItem(desc, quantity);
	}
	
	public void makePayment(boolean type, double amount) {
		currentSale.makePayment(type, amount);
	}
}
