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
