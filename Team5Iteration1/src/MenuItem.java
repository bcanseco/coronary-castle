/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */


public class MenuItem {
	public ItemDetails details;
	public int quantity;
	
	public MenuItem(ItemDetails details, int quantity) {
		this.quantity = quantity;
		this.details = details;
	}
	
	public double getSubtotal() {
		return details.getPrice() * quantity;
	}
}
