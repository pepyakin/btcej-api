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
