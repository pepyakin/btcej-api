package com.ideasium.btcej;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideasium.btcej.common.Currency;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.*;

/**
 * @author pepyakin
 */
public class FundsDeserializerTest {

    private static final double ACCEPTED_DELTA = 0.00001;

    private static final String JSON = "{ \"usd\" : 2.3, \"rur\" : 3 }";

    FundsDeserializer deserializer;
    ObjectMapper mapper;


    @Before
    public void setUp() {
        deserializer = new FundsDeserializer();
        mapper = new ObjectMapper();
    }

    @Test
    public void testNnotNull() throws IOException {
        Funds funds = mapper.readValue(JSON, Funds.class);
        assertNotNull(funds);
    }

    @Test
    public void testFirstValue() throws IOException {
        Funds funds = mapper.readValue(JSON, Funds.class);
        assertEquals(2.3, funds.amountFor(Currency.USD), ACCEPTED_DELTA);
    }

    @Test
    public void testSecondValue() throws IOException {
        Funds funds = mapper.readValue(JSON, Funds.class);
        assertEquals(3.0, funds.amountFor(Currency.RUR), ACCEPTED_DELTA);
    }

    @Test
    public void testUnspecifiedCurrency() throws IOException {
        Funds funds = mapper.readValue(JSON, Funds.class);
        assertEquals(0.0, funds.amountFor(Currency.NMC));
    }

    @Test
    public void testUnknownCurrency() throws IOException {
        final String JSON_UNKNOWN_CURR = "{ \"usd\" : 2.3, \"rur\" : 3, \"unknown_cur\" : 2 }";

        mapper.readValue(JSON_UNKNOWN_CURR, Funds.class); // or die.
    }
}
