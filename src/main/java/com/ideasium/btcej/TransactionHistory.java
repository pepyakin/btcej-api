/*
 * ${NAME}
 * ${PROJECT_NAME}
 *
 * Created by ${USER} on ${DAY}.${MONTH}.${YEAR}.
 * Copyright (c) ${YEAR} ideasium. All rights reserved.
 */

package com.ideasium.btcej;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ideasium.btcej.util.TroveIteratorWrapper;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.map.TLongObjectMap;

import java.util.Iterator;

/**
 * @author pepyakin
 */

@JsonDeserialize(using = TransactionHistoryDeserializer.class)
public class TransactionHistory implements Iterable<Transaction> {

    private final TLongObjectMap<Transaction> transactions;

    public TransactionHistory(TLongObjectMap<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Transaction getTransactionById(long id) {
        return transactions.get(id);
    }

    public Iterator<Transaction> iterator() {
        TLongObjectIterator<Transaction> transactionsIterator = transactions.iterator();
        return new TroveIteratorWrapper<Transaction>(transactionsIterator);
    }
}
