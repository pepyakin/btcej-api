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

	private final double price;
	private final double amount;

	public OrderInfo(double price, double amount) {
		this.price = price;
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public double getAmount() {
		return amount;
	}

}
