/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

package edu.fit.coronary_castle;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Store store = new Store();
		Register register = store.getRegister();
		MenuCatalog catalog = store.getCatalog();
		
		ItemDetails burger = new ItemDetails(1, "Burger", "A delicious classic.");
		ItemDetails fries = new ItemDetails(2, "Fries", "A delicious side.");
		ItemDetails cola = new ItemDetails(3, "Cola", "A delicious drink.");
		
		catalog.items.put(1, burger);
		catalog.items.put(2, fries);
		catalog.items.put(3, cola);
	}
}
