/*
 * ${NAME}
 * ${PROJECT_NAME}
 *
 * Created by ${USER} on ${DAY}.${MONTH}.${YEAR}.
 * Copyright (c) ${YEAR} ideasium. All rights reserved.
 */

package com.ideasium.btcej.util;

import gnu.trove.iterator.TLongObjectIterator;

import java.util.Iterator;

/**
 * @author Sergey
 */
public class TroveIteratorWrapper<T> implements Iterator<T> {

    private TLongObjectIterator<T> iterator;

    public TroveIteratorWrapper(TLongObjectIterator<T> iterator) {
        this.iterator = iterator;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public T next() {
        iterator.advance();
        return iterator.value();
    }

    public void remove() {
        iterator.remove();
    }
}
