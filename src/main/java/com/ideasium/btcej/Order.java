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

package com.ideasium.btcej;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ideasium.btcej.common.OrderType;
import com.ideasium.btcej.common.Pair;

/**
 * @author pepyakin
 */
public class Order {

    @JsonProperty("pair")
    private Pair pair;

    @JsonProperty("type")
    private OrderType orderType;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("rate")
    private double rate;

    @JsonProperty("timestamp_created")
    private long timestamp;

    @JsonProperty("status")
    private int status;

    @JsonIgnore
    private long id;

    void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Pair getPair() {
        return pair;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public double getAmount() {
        return amount;
    }

    public double getRate() {
        return rate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "pair=" + pair +
                ", orderType=" + orderType +
                ", amount=" + amount +
                ", rate=" + rate +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}
