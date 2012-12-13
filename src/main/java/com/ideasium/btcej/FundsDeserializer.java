package com.ideasium.btcej;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ideasium.btcej.common.Currency;

import java.io.IOException;

/**
 * @author pepyakin
 */
class FundsDeserializer extends JsonDeserializer<Funds> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Funds deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        JsonToken token;

        token = jp.getCurrentToken();

        if (token != JsonToken.START_OBJECT) {
            throw context.wrongTokenException(jp, JsonToken.START_OBJECT, null);
        }

        Funds.Builder builder = new Funds.Builder();

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String currencyName = jp.getText();
            Currency currency = Currency.findByName(currencyName);

            token = jp.nextToken();
            if (token != JsonToken.VALUE_NUMBER_FLOAT && token != JsonToken.VALUE_NUMBER_INT) {
                throw context.wrongTokenException(jp, JsonToken.VALUE_NUMBER_FLOAT, null);
            }

            double amount = jp.getValueAsDouble();

            if (currency != null) {
                builder.setFunds(currency, amount);
            }
        }

        return builder.build();
    }
}
