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
public class TransactionHistoryDeserializerTest {

    private TransactionHistoryDeserializer deserializer;
    private ObjectMapper mapper;


    private String jsonFixture;

    @Before
    public void setUp() throws Exception {
        deserializer = new TransactionHistoryDeserializer();
        mapper = new ObjectMapper();

        jsonFixture = IOUtils.toString(getClass().getResourceAsStream("/transaction_history.fixture"));
    }

    @Test
    public void testDeserialize() throws Exception {
        TransactionHistory history = mapper.readValue(jsonFixture, TransactionHistory.class);

        Transaction transaction = history.getTransactionById(1081672);
        assertNotNull(transaction);

        assertEquals(1, transaction.getType());
        assertEquals(1.0, transaction.getAmount(), 0.000001);
        assertEquals(Currency.BTC, transaction.getCurrency());
        assertEquals("BTC Payment", transaction.getDescription());
        assertEquals(2, transaction.getStatus());
        assertEquals(1342448420, transaction.getTimestamp());
    }
}
