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
