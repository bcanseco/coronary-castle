/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MenuCatalog {
	private static MenuCatalog instance;
	
	private HashMap<Integer, ItemDetails> items;
	
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
		return items.get(id - 1); // menu indices start at 1
	}
	
	public boolean contains(int id) {
		return items.containsKey(id);
	}
	
	public void addItem(ItemDetails details) {
		// Responsibility: DOING - adding to the catalog
		items.put(items.size(), details);
	}
	
	protected Iterator<Entry<Integer, ItemDetails>> getIterator() {
		return items.entrySet().iterator();
	}
}
