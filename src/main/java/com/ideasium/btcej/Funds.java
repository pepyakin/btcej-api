package com.ideasium.btcej;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ideasium.btcej.common.Currency;
import gnu.trove.map.hash.TIntDoubleHashMap;

import java.util.HashMap;

/**
 * @author pepyakin
 */
@JsonDeserialize(using = FundsDeserializer.class)
public class Funds {

    private TIntDoubleHashMap funds;

    private Funds(TIntDoubleHashMap funds) {
        this.funds = funds;
    }

    public double amountFor(Currency currency) {
        return funds.get(currency.ordinal());
    }

    @Override
    public String toString() {
        return "Funds{" + funds +
                '}';
    }

    public static class Builder {

        private TIntDoubleHashMap funds = new TIntDoubleHashMap();

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
