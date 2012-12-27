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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ideasium.btcej.common.Currency;
import gnu.trove.map.hash.TIntDoubleHashMap;
import gnu.trove.procedure.TIntDoubleProcedure;

/**
 * @author pepyakin
 */
@JsonDeserialize(using = FundsDeserializer.class)
public class Funds {

    private final TIntDoubleHashMap funds;

    private Funds(TIntDoubleHashMap funds) {
        this.funds = funds;
    }

    public double amountFor(Currency currency) {
        return funds.get(currency.ordinal());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Funds {");

        funds.forEachEntry(new CurrencyValueVisitor(sb));

        sb.append('}');
        return sb.toString();
    }

    public static class Builder {

        private final TIntDoubleHashMap funds = new TIntDoubleHashMap();

        @SuppressWarnings("UnusedReturnValue")
        public Builder setFunds(Currency currency, double amount) {
            funds.put(currency.ordinal(), amount);

            return this;
        }

        /**
         * @return Созданный по настройкам данного билдера объект {@link Funds}.
         */
        public Funds build() {
            return new Funds(funds);
        }
    }

    private static class CurrencyValueVisitor implements TIntDoubleProcedure {

        private final StringBuilder sb;
        private boolean firstIteration;

        public CurrencyValueVisitor(StringBuilder sb) {
            this.sb = sb;
            firstIteration = true;
        }

        public boolean execute(int currencyId, double amount) {
            if (firstIteration) {
                firstIteration = false;
            } else {
                sb.append(", ");
            }

            Currency currency = Currency.values()[currencyId];

            sb.append(currency.getName());
            sb.append('=');
            sb.append(amount);

            return true;
        }
    }
}
