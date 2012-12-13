/**
 * 
 */
package com.ideasium.btcej.general;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author pepyakin
 * 
 */
@JsonDeserialize(using = OrderInfoDeserializer.class)
public class OrderInfo {

	private double price;
	private double amount;

	public OrderInfo(double price, double amount) {
		this.price = price;
		this.amount = amount;
	}
	
	public OrderInfo() {
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
