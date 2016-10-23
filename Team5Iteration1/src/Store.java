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
		return Register.getInstance();
	}
	
	public MenuCatalog getCatalog() {
		return MenuCatalog.getInstance();
	}
	
	public static void addCompleteSale(Sale sale) {
		completedSales.add(sale);
	}
	
	public void showMenu() {
		System.out.println(" WELCOME TO CORONARY CASTLE");
		System.out.println("----------------------------");
		Iterator<Entry<Integer, ItemDetails>> itemsIt = getCatalog().items.entrySet().iterator();
		while(itemsIt.hasNext()) {
			Entry<Integer, ItemDetails> item = itemsIt.next();
			System.out.println(item.getValue().id + "  " + item.getValue().name + "\t\t" + item.getValue().getPrice());
			System.out.println("   - " + item.getValue().description);
			System.out.println();
		}
		System.out.println("----------------------------");
	}
}
