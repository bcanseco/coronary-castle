/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

public class CondimentDecorator extends MenuItem {
	private MenuItem menuItem;
	
	public CondimentDecorator(ItemDetails details, int quantity) {
		super(details, quantity);
	}
	
	public void decorate(MenuItem menuItem) {
		// Responsibility: DOING - wrapping the base item as a topping
		this.menuItem = menuItem;
	}
	
	@Override
	public double getSubtotal() {
		// Responsibility: KNOWING - the total price of the base item + decorators
		return details.getPrice() + menuItem.getSubtotal();
	}
	
	public String getName(boolean condimentsExist) {
		// Responsibility: KNOWING - the name of the condiment
		return menuItem.getName(true);
	}
	
	public int getQuantity() {
		// Responsibility: KNOWING - the quantity of the item being modified in the order line item.
		return menuItem.getQuantity();
	}
	
	public void setQuantity(int quantity) {
		// Responsibility: DOING - updating the base item's quantity
		menuItem.setQuantity(quantity);
	}
	
	@Override
	public String toString() {
		return getQuantity() + " " + getName(true);
	}
}
