package com.ideasium.btcej;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ideasium.btcej.common.Currency;
import gnu.trove.map.hash.TIntDoubleHashMap;
import gnu.trove.procedure.TIntDoubleProcedure;

import java.util.HashMap;

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

        funds.forEachEntry(new TIntDoubleProcedure() {

            private boolean first = true;

            public boolean execute(int a, double b) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }

                Currency currency = Currency.values()[a];

                sb.append(currency.getName());
                sb.append('=');
                sb.append(b);

                return true;
            }
        });

        sb.append('}');
        return sb.toString();
    }

    public static class Builder {

        private final TIntDoubleHashMap funds = new TIntDoubleHashMap();

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
}
