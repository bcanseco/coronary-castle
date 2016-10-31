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
		return menuItem.getName(true);
	}
}
