/**
 * 
 */
package com.ideasium.btcej.general;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author pepyakin
 *
 */
public class OrderInfoDeserializer extends JsonDeserializer<OrderInfo> {

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public OrderInfo deserialize(JsonParser jp, DeserializationContext context)
			throws IOException {
		
		JsonToken token;
		
		token = jp.getCurrentToken();
		
		if (token != JsonToken.START_ARRAY) {
			throw context.wrongTokenException(jp, JsonToken.START_ARRAY, null);
		}
		
		double price;
		double amount;
		
		token = jp.nextToken();
		if (token == JsonToken.VALUE_NUMBER_FLOAT || token == JsonToken.VALUE_NUMBER_INT) {
			price = jp.getDoubleValue();
		} else {
			throw context.wrongTokenException(jp, JsonToken.VALUE_NUMBER_FLOAT, null);
		}
		
		token = jp.nextToken();
		if (token == JsonToken.VALUE_NUMBER_FLOAT || token == JsonToken.VALUE_NUMBER_INT) {
			amount = jp.getDoubleValue();
		} else {
			throw context.wrongTokenException(jp, JsonToken.VALUE_NUMBER_FLOAT, null);
		}
		
		if (jp.nextToken() != JsonToken.END_ARRAY) {
			throw context.wrongTokenException(jp, JsonToken.END_ARRAY, null);
		}
		
		return new OrderInfo(price, amount); 
	}

}
