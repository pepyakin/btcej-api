package com.ideasium.btcej;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ideasium.btcej.common.Currency;

import java.io.IOException;

/**
 * @author pepyakin
 */
class FundsDeserializer extends JsonDeserializer<Funds> {

    private JsonParser jsonParser;
    private DeserializationContext context;

    /**
     * {@inheritDoc}
     */
    @Override
    public Funds deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {

        this.jsonParser = jsonParser;
        this.context = context;

        Funds.Builder builder = new Funds.Builder();

        /*
         * Разбирает JSON объект вида:
         * {
         *   <currency> : <amount>,
         *   <currency> : <amount>,
         *   ...
         * }
         */

        parseStartObject();

        while (this.jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currencyName = parseCurrencyName();
            Currency currency = Currency.findByName(currencyName);

            double amount = parseAmountValue();

            if (currency != null) {
                builder.setFunds(currency, amount);
            }
        }

        return builder.build();
    }

    private void parseStartObject() throws JsonMappingException {
        JsonToken token;
        token = jsonParser.getCurrentToken();

        if (token != JsonToken.START_OBJECT) {
            throw context.wrongTokenException(jsonParser, JsonToken.START_OBJECT, null);
        }
    }

    private String parseCurrencyName() throws IOException {
        if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
            throw context.wrongTokenException(jsonParser, JsonToken.FIELD_NAME, null);
        }

        return jsonParser.getText();
    }

    private double parseAmountValue() throws IOException {
        JsonToken token;
        token = jsonParser.nextToken();
        if (!token.isNumeric()) {
            throw context.wrongTokenException(jsonParser, JsonToken.VALUE_NUMBER_FLOAT, null);
        }

        return jsonParser.getValueAsDouble();
    }
}
