/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.util.ArrayList;
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
}
