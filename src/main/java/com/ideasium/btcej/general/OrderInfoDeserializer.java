/**
 * 
 */
package com.ideasium.btcej.general;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 *
 * @author pepyakin
 */
class OrderInfoDeserializer extends JsonDeserializer<OrderInfo> {

    private JsonParser jsonParser;
    private DeserializationContext deserializationContext;

    /**
     * {@inheritDoc}
     */
    @Override
	public OrderInfo deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException {
        this.jsonParser = jsonParser;
        this.deserializationContext = context;

        /*
         * Разбирает JSON последовательность такого вида:
         * [ <price>, <amount> ]
         */

        parseStartArray();
        double price = readNumericTokenAsDouble();
        double amount = readNumericTokenAsDouble();
        parseEndArray();

        return new OrderInfo(price, amount);
	}

    private double readNumericTokenAsDouble() throws IOException {
        JsonToken token = jsonParser.nextToken();

        if (!token.isNumeric()) {
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.VALUE_NUMBER_FLOAT, null);
        }

        return jsonParser.getValueAsDouble();
    }

    private void parseStartArray() throws IOException {
        // В самом начале текущий токен указывает на первый токен,
        // принадлежащий нам.
        JsonToken actualToken = jsonParser.getCurrentToken();

        if (actualToken != JsonToken.START_ARRAY) {
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, null);
        }
    }

    private void parseEndArray() throws IOException {
        JsonToken actualToken = jsonParser.nextToken();

        if (actualToken != JsonToken.END_ARRAY) {
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, null);
        }
    }
}
