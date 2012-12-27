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
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author pepyakin
 */
public class TradeAnswerTest {

    public static final double APPLICABLE_DELTA = 0.000001;

    ObjectMapper mapper;
    String jsonFixture;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();

        jsonFixture = IOUtils.toString(
                getClass().getResourceAsStream("/fixtures/tradeanswer")
        );
    }

    @Test
    public void testDeserialze() throws Exception {
        TradeAnswer tradeAnswer = mapper.readValue(jsonFixture, TradeAnswer.class);

        assertEquals(0.1, tradeAnswer.getReceived(), APPLICABLE_DELTA);
        assertEquals(0, tradeAnswer.getRemains(), APPLICABLE_DELTA);
        assertEquals(22842, tradeAnswer.getOrderId());
    }

    @Test
    public void testDeserializeFunds() throws Exception {
        TradeAnswer tradeAnswer = mapper.readValue(jsonFixture, TradeAnswer.class);

        Funds funds = tradeAnswer.getFunds();
        assertNotNull(funds);
    }
}
