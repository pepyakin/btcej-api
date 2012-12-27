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
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
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
