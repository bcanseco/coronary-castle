/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

package edu.fit.coronary_castle;

public class ItemDetails {
	public int id;
	public String name;
	public String description;
	
	private double price;
	
	public ItemDetails(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}
}
