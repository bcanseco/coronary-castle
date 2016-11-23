/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Queue;

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

	public Queue<String> getReceiptData() {
		// Responsibility: KNOWING - the printing of the receipt
		Queue<String> receiptData = new LinkedList<String>();
		ZonedDateTime zdt = ZonedDateTime.now();
		int total = 0;
		
		receiptData.add("========= CORONARY CASTLE ========");
		receiptData.add("      150 W. University Blvd.");
		receiptData.add("        Melbourne, FL 32901");
		receiptData.add("         Date: " + zdt.toLocalDate());
		receiptData.add("        Time: " + zdt.toLocalTime());
		receiptData.add("----------------------------------");
		receiptData.add("ITEM                    QTY  PRICE");
		for (MenuItem item : currentSale.orderedItems) {
			total += item.getQuantity();
			receiptData.add(String.format("%-22s %3s %7s", 
					item.getName(false), item.getQuantity(), String.format("%.2f", item.getSubtotal())));
		}
		receiptData.add("\n" + String.format("%22s %3s %7s", "Total:", 
				total, String.format("%.2f", currentSale.getTotal())));
		receiptData.add(String.format("%22s %11s", "Paid:", String.format("%.2f", currentSale.payment.getAmount())));
		receiptData.add(String.format("%22s %11s", "Change:", String.format("%.2f", currentSale.getChange())));
		receiptData.add("----------------------------------");
		receiptData.add("       Have a CORONARY day!       ");
		
		return receiptData;
	}
}
