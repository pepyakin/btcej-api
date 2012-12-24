/*
 * ${NAME}
 * ${PROJECT_NAME}
 *
 * Created by ${USER} on ${DAY}.${MONTH}.${YEAR}.
 * Copyright (c) ${YEAR} ideasium. All rights reserved.
 */

package com.ideasium.btcej;

import com.ideasium.btcej.common.BtceException;
import com.ideasium.btcej.common.SortOrder;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Sergey
 */
public class TransactionHistoryRequestBuilder {

    private static final String METHOD_NAME = "TransHistory";

    private RequestTemplate params;

    public TransactionHistoryRequestBuilder(@NotNull RequestTemplate params) {
        this.params = params;
    }

    public TransactionHistoryRequestBuilder from(long num) {
        params.setParam("from", Long.toString(num));
        return this;
    }

    public TransactionHistoryRequestBuilder count(long count) {
        params.setParam("count", Long.toString(count));
        return this;
    }

    public TransactionHistoryRequestBuilder fromId(long fromId) {
        params.setParam("from_id", Long.toString(fromId));
        return this;
    }

    public TransactionHistoryRequestBuilder endId(long endId) {
        params.setParam("end_id", Long.toString(endId));
        return this;
    }

    public TransactionHistoryRequestBuilder sortBy(@NotNull SortOrder sortOrder) {
        params.setParam("order", sortOrder.getName());
        return this;
    }

    public TransactionHistoryRequestBuilder since(long since) {
        params.setParam("since", Long.toString(since));
        return this;
    }

    public TransactionHistoryRequestBuilder end(long end) {
        params.setParam("end", Long.toString(end));
        return this;
    }

    public TransactionHistory execute() {
        try {
            return params.makeRequest(METHOD_NAME, TransactionHistory.class);
        } catch (IOException e) {
            throw new BtceException(e);
        }
    }
}
