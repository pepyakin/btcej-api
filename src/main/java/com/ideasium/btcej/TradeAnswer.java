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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pepyakin
 */
public class TradeAnswer {

    @JsonProperty("received")
    private double received;

    @JsonProperty("remains")
    private double remains;

    @JsonProperty("funds")
    private Funds funds;

    @JsonProperty("order_id")
    private long orderId;

    public double getReceived() {
        return received;
    }

    public double getRemains() {
        return remains;
    }

    public Funds getFunds() {
        return funds;
    }

    public long getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "TradeAnswer{" +
                "received=" + received +
                ", remains=" + remains +
                ", funds=" + funds +
                ", orderId=" + orderId +
                '}';
    }
}
