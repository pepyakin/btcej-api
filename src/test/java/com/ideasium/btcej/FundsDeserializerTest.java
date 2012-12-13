package com.ideasium.btcej;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideasium.btcej.common.Currency;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.*;

/**
 * @author pepyakin
 */
public class FundsDeserializerTest {

    FundsDeserializer deserializer;

    @Before
    public void setUp() {
        deserializer = new FundsDeserializer();
    }

    @Test
    public void testDeserialize() throws IOException {
        final String JSON = "{ \"usd\" : 2.3, \"rur\" : 3 }";

        ObjectMapper mapper = new ObjectMapper();
        Funds funds = mapper.readValue(JSON, Funds.class);


        assertNotNull(funds);
        assertEquals(2.3, funds.amountFor(Currency.USD), 0.00001);
        assertEquals(3, funds.amountFor(Currency.RUR), 0.00001);
    }

    @Test
    public void testDeserializeUnknown() throws IOException {
        final String JSON = "{ \"usd\" : 2.3, \"rur\" : 3, \"sc\" : 2 }";

        ObjectMapper mapper = new ObjectMapper();
        Funds funds = mapper.readValue(JSON, Funds.class);


        assertNotNull(funds);
        assertEquals(2.3, funds.amountFor(Currency.USD), 0.00001);
        assertEquals(3, funds.amountFor(Currency.RUR), 0.00001);
    }
}
