/**
 * 
 */
package com.ideasium.btcej.general;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author knott
 * 
 */
public class Depth {

	@JsonProperty("asks")
	private List<OrderInfo> asks;

	@JsonProperty("bids")
	private List<OrderInfo> bids;
	
	public Depth() {
	}

	public Depth(List<OrderInfo> asks, List<OrderInfo> bids) {
		this.asks = asks;
		this.bids = bids;
	}
	
	

	public List<OrderInfo> getAsks() {
		return asks;
	}

	public void setAsks(List<OrderInfo> asks) {
		this.asks = asks;
	}

	public List<OrderInfo> getBids() {
		return bids;
	}

	public void setBids(List<OrderInfo> bids) {
		this.bids = bids;
	}

}
