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
	public static MenuCatalog catalog;
	public static List<Register> registers;
	public static List<Sale> completedSales;
	
	public Store() {
		catalog = MenuCatalog.getInstance();
		registers = new ArrayList<Register>();
		completedSales = new ArrayList<Sale>();
	}
	
	public static void addCompleteSale(Sale sale) {
		completedSales.add(sale);
	}
	
	public static void main(String[] args) {
		// TODO: Auto-generated method stub
	}
}
