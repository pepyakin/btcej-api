/**
 * 
 */
package com.ideasium.btcej;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pepyakin
 */
public class UserInfo {

    @JsonProperty("funds")
    private Funds funds;

    @JsonProperty("rights")
    private Rights rights;

    @JsonProperty("transaction_count")
    private int transactionCount;

    @JsonProperty("open_orders")
    private int openOrdersCount;

    @JsonProperty("server_time")
    private long serverTime;

    public Funds getFunds() {
        return funds;
    }

    public Rights getRights() {
        return rights;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public int getOpenOrdersCount() {
        return openOrdersCount;
    }

    public long getServerTime() {
        return serverTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "funds=" + funds +
                ", rights=" + rights +
                ", transactionCount=" + transactionCount +
                ", openOrdersCount=" + openOrdersCount +
                ", serverTime=" + serverTime +
                '}';
    }
}
