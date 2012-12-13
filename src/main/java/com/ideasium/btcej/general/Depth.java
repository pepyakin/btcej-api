/**
 * 
 */
package com.ideasium.btcej.general;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**

 * @author pepyakin
 */
public class Depth {

    @JsonProperty("asks")
	private List<OrderInfo> asks;

    @JsonProperty("bids")
	private List<OrderInfo> bids;


	public Depth(
             @NotNull List<OrderInfo> asks,
             @NotNull List<OrderInfo> bids) {
		this.asks = asks;
		this.bids = bids;
	}

    public Depth() {

    }

    @NotNull
	public List<OrderInfo> getAsks() {
		return asks;
	}

    @NotNull
	public List<OrderInfo> getBids() {
		return bids;
	}
}
