/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.time.ZonedDateTime;

public class Register {
	public Sale currentSale;
	public MenuCatalog catalog;
	public MenuItem currentItem;
	
	private static Register instance;
	
	private Register() {
		catalog = MenuCatalog.getInstance();
	}
	
	public static Register getInstance() {
		// Responsibility: KNOWING - the initialized instance of the Register class
		if (instance == null) {
			instance = new Register();
		}
		return instance;
	}
	
	public void newSale() {
		// Responsibility: DOING - the creation of a new Sale object
		currentSale = new Sale();
	}
	
	public void endSale() {
		// Responsibility: DOING - recording the completion of a Sale
		currentSale.becomeComplete();
	}
	
	public void enterItem(int id) {
		// Responsibility: DOING - the adding of ONE of the item to the order
		enterItem(id, 1);
	}
	
	public void enterItem(int id, int quantity) {
		// Responsibility: DOING - the addition of an item to the Sale order
		ItemDetails desc = catalog.getItemDetails(id);
		currentItem = new MenuItem(desc, quantity);
	}
	
	public void addCondiment(ItemDetails desc) {
		// Responsibility: DOING - applying the condiment decorators to the item being ordered
		CondimentDecorator condiment = new CondimentDecorator(desc, 1);
		condiment.decorate(currentItem);
		currentItem = condiment;
	}
	
	public void addCurrentItemToSale(int updatedQuantity) {
		// Responsibility: DOING - perform the act of adding the current item being ordered to the sale
		currentItem.setQuantity(updatedQuantity);
		currentSale.addMenuItem(currentItem);
		currentItem = null;
	}
	
	public void makePayment(boolean type, double amount) {
		// Responsibility: DOING - the recording of the payment that has occurred
		currentSale.makePayment(type, amount);
	}

	public void printReceipt() {
		// Responsibility: DOING - the printing of the receipt
		ZonedDateTime zdt = ZonedDateTime.now();
		String header = "===== CORONARY CASTLE =====\r\n" + 
						" 150 W. University Blvd.\r\n" +
						" Melbourne, FL 32901\r\n\r\n" +
						"Sale Date: " + zdt.toLocalDate() + "\r\n" +
						"Sale Time: " + zdt.toLocalTime() + "\r\n" +
						"Items Sold: " + currentSale.orderedItems.size() +
						"\r\n" + 
						"---------------------------";
		StringBuilder items = new StringBuilder();
		for(MenuItem mi : currentSale.orderedItems) {
			items.append(mi.getName(false) + "\r\n\t\t" + mi.getQuantity() + "  $" + mi.getSubtotal() + "\r\n");
		}
		String footer = "---------------------------\r\n" +
						"\t   Total: $" + currentSale.getTotal() + "\r\n" +
						"\t   Paid:  $" + currentSale.payment.getAmount() + "\r\n\r\n" +
						"   Have a CORONARY day! \r\n\r\n";
		System.out.println("\r\n");
		System.out.println(header);
		System.out.println(items);
		System.out.println(footer);
	}
}
