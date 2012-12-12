/**
 * 
 */
package com.ideasium.btcej.general;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author knott
 * 
 */
public class Ticker {

	@JsonProperty("avg")
	private double average;

	@JsonProperty("buy")
	private double buy;

	@JsonProperty("high")
	private double high;

	@JsonProperty("last")
	private double last;

	@JsonProperty("low")
	private double low;

	@JsonProperty("sell")
	private double sell;

	@JsonProperty("vol")
	private double volume;

	@JsonProperty("vol_cur")
	private double volumeCurrent;

	@JsonProperty("server_time")
	private long serverTime;

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getBuy() {
		return buy;
	}

	public void setBuy(double buy) {
		this.buy = buy;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getSell() {
		return sell;
	}

	public void setSell(double sell) {
		this.sell = sell;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getVolumeCurrent() {
		return volumeCurrent;
	}

	public void setVolumeCurrent(double volumeCurrent) {
		this.volumeCurrent = volumeCurrent;
	}

	public long getServerTime() {
		return serverTime;
	}

	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}

}
