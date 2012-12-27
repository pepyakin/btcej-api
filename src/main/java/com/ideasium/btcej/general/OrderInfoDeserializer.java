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

package com.ideasium.btcej.general;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
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
