/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

package edu.fit.coronary_castle;

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
		return Register.getInstance();
	}
	
	public MenuCatalog getCatalog() {
		return MenuCatalog.getInstance();
	}
	
	public static void addCompleteSale(Sale sale) {
		completedSales.add(sale);
	}
}
