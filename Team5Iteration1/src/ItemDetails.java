/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

public class ItemDetails {
	public int id;
	public String name;
	public String description;
	public EItemType type;
	
	private double price;
	
	public ItemDetails(int id, String name, String description, EItemType type, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.price = price;
	}
	
	public double getPrice() {
		// Responsibility: KNOWING - the price of the item in the menu 
		return price;
	}
}

enum EItemType {
	Entree, Topping, Drink
}
