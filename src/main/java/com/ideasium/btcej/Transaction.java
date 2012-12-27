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

    void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
