/*
 * ideasium (c) 2012.
 *
 * This software is provided 'as-is', without any express or implied
 * warranty.  In no event will the authors be held liable for any damages
 * arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source distribution.
 */

package com.ideasium.btcej.general;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pepyakin
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Ticker{" +
                "average=" + average +
                ", buy=" + buy +
                ", high=" + high +
                ", last=" + last +
                ", low=" + low +
                ", sell=" + sell +
                ", volume=" + volume +
                ", volumeCurrent=" + volumeCurrent +
                ", serverTime=" + serverTime +
                '}';
    }
}
