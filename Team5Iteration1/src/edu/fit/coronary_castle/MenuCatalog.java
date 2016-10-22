/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

package edu.fit.coronary_castle;

import java.util.HashMap;

public class MenuCatalog {
	private static MenuCatalog instance;
	
	public HashMap<MenuItem, ItemDetails> items;
	
	private MenuCatalog() {
		// Enforce singleton pattern
	}
	
	public static MenuCatalog getInstance() {
		if (instance == null) {
			return new MenuCatalog();
		}
		return instance;
	}
	
	public ItemDetails getItemDetails(MenuItem item) {
		return items.get(item);
	}
}
