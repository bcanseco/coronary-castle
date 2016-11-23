/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

// Responsibility: Documented in the Payment interface
public class Cash implements Payment {
	private double amount;
	
	public Cash(double amount) {
		this.amount = amount;
	}
	
	@Override
	public double getAmount() {
		return amount;
	}
}
