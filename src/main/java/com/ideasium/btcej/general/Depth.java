/**
 * 
 */
package com.ideasium.btcej.general;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**

 * @author knott
 */
public class Depth {

	private final List<OrderInfo> asks;
	private final List<OrderInfo> bids;

	public Depth(
            @JsonProperty("asks") @NotNull List<OrderInfo> asks,
            @JsonProperty("bids") @NotNull List<OrderInfo> bids) {
		this.asks = asks;
		this.bids = bids;
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
