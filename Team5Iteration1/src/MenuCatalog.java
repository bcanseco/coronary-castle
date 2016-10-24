/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class MenuCatalog {
	private static MenuCatalog instance;
	
	public HashMap<Integer, ItemDetails> items;
	
	private MenuCatalog() {
		items = new HashMap<Integer, ItemDetails>();
	}
	
	public static MenuCatalog getInstance() {
		// Responsibility: KNOWING - the menu catalog information 
		if (instance == null) {
			instance = new MenuCatalog();
		}
		return instance;
	}
	
	public ItemDetails getItemDetails(int id) {
		// Responsibility: KNOWING - the menu item's detailed information
		return items.get(id);
	}
	
}
