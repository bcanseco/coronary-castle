/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Store {
	public static String address;
	public static String name;
	public static List<Sale> completedSales;
	
	public Store() {
		completedSales = new ArrayList<Sale>();
	}
	
	public Register getRegister() {
		// Responsibility: KNOWING - the currently instantiated Register instance.
		return Register.getInstance();
	}
	
	public MenuCatalog getCatalog() {
		// Responsibility: KNOWING - the currently instantiated MenuCatalog instance
		return MenuCatalog.getInstance();
	}
	
	public static void addCompleteSale(Sale sale) {
		// Responsibility: DOING - recording the completed sale to the store sales records 
		completedSales.add(sale);
	}
	
	public void showMenu() {
		// Responsibility: DOING - showing the menu catalog to the user
		System.out.println(" WELCOME TO CORONARY CASTLE");
		System.out.println("----------------------------");
		Iterator<Entry<Integer, ItemDetails>> itemsIt = getCatalog().getIterator();
		while(itemsIt.hasNext()) {
			Entry<Integer, ItemDetails> item = itemsIt.next();
			System.out.println(item.getValue().id + "  " + item.getValue().name + "\t" + item.getValue().getPrice());
			System.out.println("   - " + item.getValue().description);
			System.out.println();
		}
		System.out.println("----------------------------");
	}
}
