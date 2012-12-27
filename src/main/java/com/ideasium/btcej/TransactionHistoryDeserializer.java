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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;

import java.io.IOException;

/**
 * @author pepyakin
 */
class TransactionHistoryDeserializer
        extends JsonDeserializer<TransactionHistory> {

    private JsonParser jsonParser;
    private DeserializationContext context;

    @Override
    public TransactionHistory deserialize(JsonParser jp,
                                          DeserializationContext ctxt)
            throws IOException {

        this.jsonParser = jp;
        this.context = ctxt;

        TLongObjectMap<Transaction> transactions;
        transactions = new TLongObjectHashMap<Transaction>();

        parseStartObject();

        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            Transaction transaction = parseTransaction();

            transactions.put(transaction.getId(), transaction);
        }

        return new TransactionHistory(transactions);
    }

    private void parseStartObject() throws JsonMappingException {
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw context.wrongTokenException(jsonParser, JsonToken.START_OBJECT, null);
        }
    }

    private Transaction parseTransaction() throws IOException {
        long transactionId = parseTransactionId();

        Transaction transaction = parseTransactionValue();
        transaction.setId(transactionId);

        return transaction;
    }

    private long parseTransactionId() throws IOException {
        if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
            throw context.wrongTokenException(jsonParser, JsonToken.FIELD_NAME, null);
        }

        String transactionIdField = jsonParser.getText();
        return Long.parseLong(transactionIdField);
    }

    private Transaction parseTransactionValue() throws IOException {
        if (jsonParser.nextToken() != JsonToken.START_OBJECT) {
            throw context.wrongTokenException(jsonParser, JsonToken.START_OBJECT, null);
        }

        return jsonParser.readValueAs(Transaction.class);
    }
}
