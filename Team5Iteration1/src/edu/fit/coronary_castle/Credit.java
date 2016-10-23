/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

package edu.fit.coronary_castle;

public class Credit implements Payment {
	private double amount;
	
	public Credit(double amount) {
		this.amount = amount;
	}
	
	@Override
	public double getAmount() {
		return amount;
	}
}
