/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

public class MenuItem {
	public ItemDetails details;
	private int quantity;
	
	public MenuItem(ItemDetails details, int quantity) {
		this.quantity = quantity;
		this.details = details;
	}
	
	public double getSubtotal() {
		// Responsibility: KNOWING - the total price of the order without sales tax
		return details.getPrice() * quantity;
	}
	
	public String getName(boolean condimentsExist) {
		// Responsibility: KNOWING - the name of the item in the order
		return condimentsExist ? "Cust. " + details.name : details.name;
	}

	public int getQuantity() {
		// Responsibility: KNOWING - how many of the item are in the order line item
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		// Responsibility: DOING - updating its own quantity
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return getQuantity() + " " + getName(false);
	}
}
