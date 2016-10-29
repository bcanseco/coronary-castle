/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.util.Scanner;

public class Main {
	public static void main(String[] args) { 
		// Responsibility: DOING - all the program's interaction logic
		
		Store store = new Store();
		Register register = store.getRegister();
		MenuCatalog catalog = store.getCatalog();
		
		catalog.addItem(new ItemDetails(1, "Bypass", "Half-pound burger", EItemType.Entree, 3.50));
		catalog.addItem(new ItemDetails(2, "Double Bypass", "1 pound burger.", EItemType.Entree, 6.50));
		catalog.addItem(new ItemDetails(3, "Triple Bypass", "1.5 pound burger.", EItemType.Entree, 8.50));
		catalog.addItem(new ItemDetails(4, "Quadruple Bypass", "2 pound burger.", EItemType.Entree, 9.75));
		catalog.addItem(new ItemDetails(5, "Ham", "A ham slice topping.", EItemType.Topping, 1.00));
		catalog.addItem(new ItemDetails(6, "Egg", "A fried egg topping.", EItemType.Topping, 0.75));
		catalog.addItem(new ItemDetails(7, "Cheese", "A cheese slice topping.", EItemType.Topping, 0.50));
		catalog.addItem(new ItemDetails(8, "Onion Rings", "Onion rings on the sandwich.", EItemType.Topping, 0.75));
		catalog.addItem(new ItemDetails(9, "French Fries", "Fries on the sandwich.", EItemType.Topping, 0.75));
		catalog.addItem(new ItemDetails(10, "Mustard", "Nothing goes better on a dog.", EItemType.Topping, 0.00));
		catalog.addItem(new ItemDetails(11, "Mayonnaise", "Not an instrument.", EItemType.Topping, 0.00));
		catalog.addItem(new ItemDetails(12, "Ketchup", "Can't go wrong with ketchup.", EItemType.Topping, 0.00));
		catalog.addItem(new ItemDetails(13, "Lettuce", "You want a diet coke too?", EItemType.Topping, 0.00));
		catalog.addItem(new ItemDetails(14, "Tomato", "Might as well get ketchup.", EItemType.Topping, 0.00));
		catalog.addItem(new ItemDetails(15, "Onion", "America's finest news source.", EItemType.Topping, 0.00));
		catalog.addItem(new ItemDetails(16, "Pickle", "Goes great with the bypass.", EItemType.Topping, 0.00));
		catalog.addItem(new ItemDetails(17, "Jalapeno Peppers", "Don't beathe this.", EItemType.Topping, 0.00));
		catalog.addItem(new ItemDetails(18, "32oz Soda", "Quench your thirst.", EItemType.Drink, 2.00));
		catalog.addItem(new ItemDetails(19, "48oz Soda", "Never thirst again.", EItemType.Drink, 3.00));
		catalog.addItem(new ItemDetails(20, "Bladder Buster", "A 64 ounce bad decision.", EItemType.Drink, 4.00));
		
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
