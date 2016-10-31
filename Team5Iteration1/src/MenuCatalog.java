/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class MenuCatalog {
	private static MenuCatalog instance;
	
	private ArrayList<ItemDetails> items;
	
	private MenuCatalog() {
		items = new ArrayList<ItemDetails>();
	}
	
	public static MenuCatalog getInstance() {
		// Responsibility: KNOWING - the menu catalog information 
		if (instance == null) {
			instance = new MenuCatalog();
			instance.addItem(new ItemDetails(1, "Bypass\t", "Half-pound burger", EItemType.Entree, 3.50));
			instance.addItem(new ItemDetails(2, "Double Bypass", "1 pound burger.", EItemType.Entree, 6.50));
			instance.addItem(new ItemDetails(3, "Triple Bypass", "1.5 pound burger.", EItemType.Entree, 8.50));
			instance.addItem(new ItemDetails(4, "Quadruple Bypass", "2 pound burger.", EItemType.Entree, 9.75));
			instance.addItem(new ItemDetails(5, "Ham \t", "A ham slice topping.", EItemType.Topping, 1.00));
			instance.addItem(new ItemDetails(6, "Egg \t", "A fried egg topping.", EItemType.Topping, 0.75));
			instance.addItem(new ItemDetails(7, "Cheese", "A cheese slice topping.", EItemType.Topping, 0.50));
			instance.addItem(new ItemDetails(8, "Onion Rings", "Onion rings on the sandwich.", EItemType.Topping, 0.75));
			instance.addItem(new ItemDetails(9, "French Fries", "Fries on the sandwich.", EItemType.Topping, 0.75));
			instance.addItem(new ItemDetails(10, "Mustard", "Nothing goes better on a dog.", EItemType.Topping, 0.00));
			instance.addItem(new ItemDetails(11, "Mayonnaise", "Not an instrument.", EItemType.Topping, 0.00));
			instance.addItem(new ItemDetails(12, "Ketchup", "Can't go wrong with ketchup.", EItemType.Topping, 0.00));
			instance.addItem(new ItemDetails(13, "Lettuce", "You want a diet coke too?", EItemType.Topping, 0.00));
			instance.addItem(new ItemDetails(14, "Tomato", "Might as well get ketchup.", EItemType.Topping, 0.00));
			instance.addItem(new ItemDetails(15, "Onion", "America's finest news source.", EItemType.Topping, 0.00));
			instance.addItem(new ItemDetails(16, "Pickle", "Goes great with the bypass.", EItemType.Topping, 0.00));
			instance.addItem(new ItemDetails(17, "Jalapeños", "Don't beathe this.", EItemType.Topping, 0.00));
			instance.addItem(new ItemDetails(18, "32oz Soda", "Quench your thirst.", EItemType.Drink, 2.00));
			instance.addItem(new ItemDetails(19, "48oz Soda", "Never thirst again.", EItemType.Drink, 3.00));
			instance.addItem(new ItemDetails(20, "Bladder Buster", "A 64 ounce bad decision.", EItemType.Drink, 4.00));
		}
		return instance;
	}
	
	public ItemDetails getItemDetails(int id) {
		// Responsibility: KNOWING - the menu item's detailed information
		return items.get(id - 1); // menu indices start at 1
	}
	
	public void addItem(ItemDetails details) {
		// Responsibility: DOING - adding to the catalog
		items.add(details);
	}
	
	protected Iterator<ItemDetails> getIterator() {
		return items.iterator();
	}
	
	public List<ItemDetails> getEntrees() {
		// Responsibility: KNOWING the items in the menu that are entrees
		List<ItemDetails> entreeList = new ArrayList<ItemDetails>();
		Iterator<ItemDetails> eIt = getInstance().getIterator();
		while(eIt.hasNext()) {
			ItemDetails item = eIt.next();
			if(item.type == EItemType.Entree)
				entreeList.add(item);
		}
		return entreeList;
	}
	
	public List<ItemDetails> getToppingsNotFree() {
		// Responsibility: KNOWING the items in the menu that are toppings and aren't free
		List<ItemDetails> list = new ArrayList<ItemDetails>();
		Iterator<ItemDetails> eIt = getInstance().getIterator();
		while(eIt.hasNext()) {
			ItemDetails item = eIt.next();
			if(item.type == EItemType.Topping && item.getPrice() != 0.0)
				list.add(item);
		}
		return list;
	}
	
	public List<ItemDetails> getToppingsFree() {
		// Responsibility: KNOWING the items in the menu that are toppings and are free
		List<ItemDetails> list = new ArrayList<ItemDetails>();
		Iterator<ItemDetails> eIt = getInstance().getIterator();
		while(eIt.hasNext()) {
			ItemDetails item = eIt.next();
			if(item.type == EItemType.Topping && item.getPrice() == 0.0)
				list.add(item);
		}
		return list;
	}
	
	public List<ItemDetails> getDrinks() {
		// Responsibility: KNOWING the items in the menu that are drinks
		List<ItemDetails> list = new ArrayList<ItemDetails>();
		Iterator<ItemDetails> eIt = getInstance().getIterator();
		while(eIt.hasNext()) {
			ItemDetails item = eIt.next();
			if(item.type == EItemType.Drink)
				list.add(item);
		}
		return list;
	}
}
