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
