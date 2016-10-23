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
	
	private double price;
	
	public ItemDetails(int id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
}
