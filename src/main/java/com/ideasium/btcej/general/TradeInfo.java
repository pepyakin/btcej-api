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
import com.ideasium.btcej.common.Currency;

/**
 * @author pepyakin
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TradeInfo{" +
                "amount=" + amount +
                ", price=" + price +
                ", date=" + date +
                ", item=" + item +
                ", priceCurrency=" + priceCurrency +
                ", tid=" + tid +
                ", type=" + type +
                '}';
    }
}
