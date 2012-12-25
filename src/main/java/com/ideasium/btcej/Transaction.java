/*
 * ${NAME}
 * ${PROJECT_NAME}
 *
 * Created by ${USER} on ${DAY}.${MONTH}.${YEAR}.
 * Copyright (c) ${YEAR} ideasium. All rights reserved.
 */

package com.ideasium.btcej;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ideasium.btcej.common.Currency;

/**
* @author pepyakin
*/
public class Transaction {

    @JsonProperty("type")
    private int type;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("desc")
    private String description;

    @JsonProperty("status")
    private int status;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonIgnore
    private long id;

    public long getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
