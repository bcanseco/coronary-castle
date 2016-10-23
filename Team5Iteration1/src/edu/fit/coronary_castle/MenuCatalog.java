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
	
	public HashMap<Integer, ItemDetails> items;
	
	private MenuCatalog() {
		// Enforce singleton pattern
	}
	
	public static MenuCatalog getInstance() {
		if (instance == null) {
			return new MenuCatalog();
		}
		return instance;
	}
	
	public ItemDetails getItemDetails(int id) {
		return items.get(id);
	}
}
