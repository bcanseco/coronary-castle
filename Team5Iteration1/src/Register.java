import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */


public class Register {
	public Sale currentSale;
	public MenuCatalog catalog;
	
	private static Register instance;
	
	private Register() {
		catalog = MenuCatalog.getInstance();
	}
	
	public static Register getInstance() {
		if (instance == null) {
			instance = new Register();
		}
		return instance;
	}
	
	public void newSale() {
		currentSale = new Sale();
	}
	
	public void endSale() {
		currentSale.becomeComplete();
	}
	
	public void enterItem(int id, int quantity) {
		ItemDetails desc = catalog.getItemDetails(id);
		currentSale.makeMenuItem(desc, quantity);
	}
	
	public void makePayment(boolean type, double amount) {
		currentSale.makePayment(type, amount);
	}

	public void printReceipt() {
		ZonedDateTime zdt = ZonedDateTime.now();
		String header = "=== CORONARY CASTLE ===\r\n" + 
						" 150 W. University Blvd.\r\n" +
						" Melbourne, FL 32901\r\n\r\n" +
						"Sale Date: " + zdt.toLocalDate() + "\r\n" +
						"Sale Time: " + zdt.toLocalTime() + "\r\n" +
						"Items Sold: " + currentSale.orderedItems.size() +
						"\r\n" + 
						"-----------------------";
		StringBuilder items = new StringBuilder();
		for(MenuItem mi : currentSale.orderedItems) {
			items.append(mi.details.name + "\t\t" + mi.quantity + "  $" + mi.quantity*mi.details.getPrice() + "\r\n");
		}
		String footer = "-----------------------\r\n" +
						"\t   Total: $" + currentSale.getTotal() + "\r\n" +
						"\t   Paid:  $" + currentSale.payment.getAmount() + "\r\n\r\n" +
						"   Have a CORONARY day! \r\n\r\n";
		System.out.println("\r\n");
		System.out.println(header);
		System.out.println(items);
		System.out.println(footer);
	}
}
