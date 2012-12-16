package com.ideasium.btcej.general;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TradeType {
	/**
	 * Покупка.
	 */
	ASK("ask"),

	/**
	 * Продажа.
	 */
	BID("bid");
	
	
	private final String id;
	
	private TradeType(String id) {
		this.id = id;
	}

	@JsonValue
	public String getTradeTypeId() {
		return id;
	}
}