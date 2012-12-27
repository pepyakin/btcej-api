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

import com.ideasium.btcej.common.Pair;
import org.jetbrains.annotations.NotNull;

/**
 * @author pepyakin
 */
public class TradeRequestBuilder extends RequestBuilder {

    private boolean tradeTypeSet;
    private boolean pairSet;
    private boolean amountSet;
    private boolean rateSet;

    public TradeRequestBuilder(@NotNull RequestTemplate params) {
        super(params, "TradeHistory", TradeAnswer.class);
    }

    /**
     * Указать с какой {@link Pair парой} будет произведена операция.
     */
    public TradeRequestBuilder pair(@NotNull Pair pair) {
        if (pair == null) {
            throw new IllegalStateException("pair can't be null");
        }

        pairSet = true;

        setParam("pair", pair.getName());
        return this;
    }

    /**
     * Указать операцию покупки валюты.
     */
    public TradeRequestBuilder buy() {
        tradeTypeSet = true;

        setParam("type", "buy");
        return this;
    }

    /**
     * Указать операцию продажи валюты.
     */
    public TradeRequestBuilder sell() {
        tradeTypeSet = true;

        setParam("type", "sell");
        return this;
    }

    /**
     * Указать курс по которому необходимо купить или продать.
     */
    public TradeRequestBuilder rate(double rate) {
        validateNumber(rate);
        rateSet = true;

        setParam("rate", Double.toString(rate));
        return this;
    }

    /**
     * Указать количество покупаемой или продаваемой валюты.
     */
    public TradeRequestBuilder amount(double amount) {
        validateNumber(amount);
        amountSet = true;

        setParam("amount", Double.toString(amount));
        return this;
    }

    private void validateNumber(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("number can't be equal or below zero");
        }

        if (Double.isNaN(number)) {
            throw new IllegalArgumentException("number can't be NaN");
        }

        if (Double.isInfinite(number)) {
            throw new IllegalArgumentException("number can't be infinity");
        }
    }

    /**
     * Данный метод перед выполнением проверяет все ли параметры заданы.
     */
    @Override
    protected void preExecute() {
        if (!tradeTypeSet) {
            throw new IllegalStateException("trade type is not set");
        }

        if (!amountSet) {
            throw new IllegalStateException("amount is not set");
        }

        if (!pairSet) {
            throw new IllegalStateException("pair is not set");
        }

        if (!rateSet) {
            throw new IllegalStateException("rate is not set");
        }
    }
}
