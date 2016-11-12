/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		System.out.println("       ::ENTREES::          ");
		Iterator<ItemDetails> itemsIt = getCatalog().getEntrees().iterator();
		while(itemsIt.hasNext()) {
			ItemDetails item = itemsIt.next();
			System.out.println(item.id + "  " + item.name + "\t" + item.getPrice());
			System.out.println("   - " + item.description);
			//System.out.println();
		}
		System.out.println();
		
		System.out.println("    ::PAID TOPPINGS::       ");
		itemsIt = getCatalog().getToppingsNotFree().iterator();
		while(itemsIt.hasNext()) {
			ItemDetails item = itemsIt.next();
			System.out.println(item.id + "  " + item.name + "\t\t" + item.getPrice());
		}
		System.out.println();
		
		System.out.println("    ::FREE TOPPINGS::       ");
		itemsIt = getCatalog().getToppingsFree().iterator();
		while(itemsIt.hasNext()) {
			ItemDetails item1 = itemsIt.next();
			ItemDetails item2 = itemsIt.next();
			System.out.print(item1.id + " " + item1.name + "\t");
			if(item2 != null)
				System.out.println(item2.id +" " + item2.name);
			else System.out.println();
		}
		System.out.println();
		
		System.out.println("       ::DRINKS::           ");
		itemsIt = getCatalog().getDrinks().iterator();
		while(itemsIt.hasNext()) {
			ItemDetails item = itemsIt.next();
			System.out.println(item.id + "  " + item.name + "   \t" + item.getPrice());
		}
		System.out.println("----------------------------");
	}
}
