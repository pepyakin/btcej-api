/**
 * 
 */
package com.ideasium.btcej.general;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ideasium.btcej.common.Currency;

/**
 * @author knott
 * 
 */
public class TradeInfo {

	@JsonProperty("amount")
	private double amount;
	
	@JsonProperty("price")
	private double price;
	
	@JsonProperty("date")
	private long date;
	
	@JsonProperty("item")
	private Currency item;
	
	@JsonProperty("price_currency")
	private Currency priceCurrency;
	
	@JsonProperty("tid")
	private long tid;
	
	@JsonProperty("trade_type")
	private TradeType type;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public Currency getItem() {
		return item;
	}

	public void setItem(Currency item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Currency getPriceCurrency() {
		return priceCurrency;
	}

	public void setPriceCurrency(Currency priceCurrency) {
		this.priceCurrency = priceCurrency;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public TradeType getType() {
		return type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}
}
