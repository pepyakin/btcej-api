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
	
	
	private String value;
	
	private TradeType(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}