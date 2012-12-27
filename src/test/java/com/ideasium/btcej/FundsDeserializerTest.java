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
