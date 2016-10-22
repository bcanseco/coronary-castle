/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

package edu.fit.coronary_castle;

public class MenuItem {
	public int id;
	public int quantity;
	public ItemDetails details;
	
	public double getSubtotal() {
		return details.price * quantity;
	}
}
