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
		
		store.showMenu();
		System.out.println("CMDs:  (N)ew Sale  |  E(X)it");
		char opt = getCommand();
		while(opt != 'x') {
			if(opt == 'n') {
				System.out.println("Created new sale.");
				System.out.println("Enter 'c' at any time to complete sale.");
				register.newSale();
				while(opt != 'c') {
					int itemid = getIdOrQty("item");
					if(itemid == Integer.MIN_VALUE) break;
					if (itemid <= 0) {
						System.out.println("We don't have that item number!");
						continue;
					}
					if (catalog.getItemDetails(itemid).type == EItemType.Topping) {
						System.out.println("Please pick an entree first.");
						continue;
					}
					
					int quantity = getIdOrQty("quantity");
					if(quantity == Integer.MIN_VALUE) break;
					if(quantity > 0) {
						register.enterItem(itemid, quantity);
						if (register.currentItem.details.type != EItemType.Drink) {
							System.out.println("Any condiments for that " + register.currentItem.details.name + "?");
							int condimentid;
							while ((condimentid = getIdOrQty("condiment")) != 0) {
								if (condimentid < 5 || condimentid > 17) {
									System.out.println("We don't have that condiment number! Try again or enter 0 to leave as is.");
									continue;
								}
								register.addCondiment(catalog.getItemDetails(condimentid));
								System.out.println("Successfully added " + register.currentItem.details.name + "."
										+ "\nAny others? Enter 0 to leave as is.");
							}
						}
						register.addCurrentItemToSale();
						System.out.println("Added " + quantity + " of item #" + itemid);
					}
					System.out.println("Current total: " + register.currentSale.getTotal());
				}
				System.out.println("Completed sale.");
				System.out.println("CMDs:  Make (P)ayment  |  (C)ancel sale  |  E(X)it");
				opt = getCommand();
				while(opt != 'p' && opt != 'c' && opt != 'x') {
					System.out.println("Invalid option");
					opt = getCommand();
				}
				if(opt == 'c') { /*move on*/ }
				else if(opt == 'x') break;
				else { /*make payment*/
					double amountRemaining = register.currentSale.getTotal();
					while(amountRemaining>0) {
						System.out.println("Payment: C(a)sh  |  C(r)edit");
						opt = getCommand("payment");
						while(opt != 'a' && opt != 'r') {
							System.out.println("Invalid option.");
							opt = getCommand("payment");
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
			opt = getCommand();
		}
		System.out.println("Exiting.");
	}
	
	// Responsibility: Utility methods to get user input, used only for Main procedure
	private static char getCommand(String word) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Select "+word+" => ");
		char opt = scn.next().toLowerCase().charAt(0);
		//scn.close();
		return opt;
	}
	private static char getCommand() {
		return getCommand("command");
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
	private static int getIdOrQty(String word) {
		Scanner scn = new Scanner(System.in);
		while(true) {
			System.out.print("Enter " + word + " => ");
			String in = scn.next().toLowerCase();
			if(isNumeric(in)) return Integer.parseInt(in);
			else if(in.charAt(0)=='c') return Integer.MIN_VALUE;
			else {
				System.out.println("Invalid number.");
				continue;
			}
		}
	}
	private static boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  
}
