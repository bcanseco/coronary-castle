import java.util.Scanner;

/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

public class Main {
	public static void main(String[] args) { 
		// Responsibility: DOING - all the program's interaction logic
		
		Store store = new Store();
		Register register = store.getRegister();
		MenuCatalog catalog = store.getCatalog();
		
		ItemDetails bypass = new ItemDetails(1, "Bypass\t", "Half-pound burger", 3.50);
		ItemDetails bypass2 = new ItemDetails(2, "Double Bypass", "1 pound burger.", 6.50);
		ItemDetails bypass3 = new ItemDetails(3, "Triple Bypass", "1.5 pound burger.", 8.50);
		ItemDetails bypass4 = new ItemDetails(4, "Quadruple Bypass", "2 pound burger.", 9.75);
		
		catalog.items.put(1, bypass);
		catalog.items.put(2, bypass2);
		catalog.items.put(3, bypass3);
		catalog.items.put(4, bypass4);
		
		store.showMenu();
		System.out.println("CMDs:  (N)ew Sale  |  E(X)it\r\n");
		char opt = getChoice();
		while(opt != 'x') {
			if(opt == 'n') {
				System.out.println("Created new sale.");
				System.out.println("Enter 'c' at any time to complete sale.");
				register.newSale();
				while(opt != 'c') {
					opt = getChoice("item");
					if(opt == 'c') break;
					while(!Character.isDigit(opt)) {
						System.out.println("Invalid menu item.");
						opt = getChoice("item");
					}
					char quantity = getChoice("quantity");
					while(!Character.isDigit(quantity)) {
						System.out.println("Invalid menu item.");
						opt = getChoice("quantity");
					}
					int itemid = Character.getNumericValue(opt);
					int qty = Character.getNumericValue(quantity);
					register.enterItem(itemid, qty);
					System.out.println("Added " + qty + " of item #" + itemid);
					System.out.println("Current total: " + register.currentSale.getTotal());
				}
				System.out.println("Completed sale.");
				System.out.println("CMDs:  Make (P)ayment  |  (C)ancel sale  |  E(X)it");
				opt = getChoice();
				while(opt != 'p' && opt != 'c' && opt != 'x') {
					System.out.println("Invalid option");
					opt = getChoice();
				}
				if(opt == 'c') { /*move on*/ }
				else if(opt == 'x') break;
				else { /*make payment*/
					double amountRemaining = register.currentSale.getTotal();
					while(amountRemaining>0) {
						System.out.println("Payment: C(a)sh  |  C(r)edit");
						opt = getChoice("payment");
						while(opt != 'a' && opt != 'r') {
							System.out.println("Invalid option.");
							opt = getChoice("payment");
						}
						boolean payType;
						String payTypeName;
						if(opt == 'a') {
							payType = true;
							payTypeName = "cash";
						}
						else {
							payType = false;
							payTypeName = "credit";
						}
						double amount = getMoneyAmount();
						register.makePayment(payType, amount);
						amountRemaining = register.currentSale.getBalance();
						System.out.println("Paid $" + amount + " with " + payTypeName + ".");
						System.out.print((amountRemaining<0) ? amountRemaining*-1.0 : amountRemaining);
						System.out.println(" is the change.");
					}
					register.printReceipt();
					register.endSale();
					System.out.println("Sale complete.");
				}
			}
			else {
				System.out.println("Invalid option.");
			}
			System.out.println("CMDs:  (N)ew Sale  |  E(X)it");
			opt = getChoice();
		}
		System.out.println("Exiting.");
	}
	
	// Responsibility: Utility methods to get user input, used only for Main procedure
	private static char getChoice(String word) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Select "+word+" => ");
		char opt = scn.next().toLowerCase().charAt(0);
		//scn.close();
		return opt;
	}
	private static char getChoice() {
		return getChoice("option");
	}
	private static double getMoneyAmount() {
		Scanner scn = new Scanner(System.in);
		double amount;
		while(true) { 
			try {
				System.out.print("Amount => ");
				String in = scn.next();
				amount = Double.parseDouble(in);
				break;
			} catch(NumberFormatException ex) {
				System.out.println("Invalid amount.");
				continue;
			}
		}
		return amount;
	}
}
