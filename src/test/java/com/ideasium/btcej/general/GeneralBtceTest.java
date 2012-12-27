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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideasium.btcej.common.Currency;
import com.ideasium.btcej.common.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author pepyakin
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneralBtceTest {

    private static final Pair PAIR = Pair.BTC_RUR;
    private static final double DELTA = 0.000001;


    @Mock
    ResponseFetcher handler;
    ObjectMapper mapper;

    GeneralBtce btce;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        btce = new GeneralBtce(mapper, handler);
    }

    @Test
    public void testGetTicker() throws Exception {
        final String JSON_RESPONSE = "{ \"ticker\":{\"high\":13.509,\"low\":13.309,\"avg\":13.409,\"vol\":22219.88385," +
                "\"vol_cur\":1655.07919,\"last\":13.367,\"buy\":13.4,\"sell\":13.367,\"server_time\":1355412389}}";

        when(handler.fetchResponse(PAIR, "ticker")).thenReturn(JSON_RESPONSE);

        Ticker ticker = btce.getTicker(PAIR);

        assertEquals(13.509, ticker.getHigh(), DELTA);
        assertEquals(13.309, ticker.getLow(), DELTA);
        assertEquals(13.409, ticker.getAverage(), DELTA);
        assertEquals(22219.88385, ticker.getVolume(), DELTA);
        assertEquals(1655.07919, ticker.getVolumeCurrent(), DELTA);
        assertEquals(13.367, ticker.getLast(), DELTA);
        assertEquals(13.4, ticker.getBuy(), DELTA);
        assertEquals(13.367, ticker.getSell(), DELTA);
        assertEquals(1355412389, ticker.getServerTime());
    }

    @Test
    public void testGetDepth() throws Exception {
        final String JSON_RESPONSE = "{\"asks\":[[426,0.46726227],[427,0.35]], " +
                "\"bids\" : [[420.5,0.9277161],[420.2,7.48484346]]}";

        when(handler.fetchResponse(PAIR, "depth")).thenReturn(JSON_RESPONSE);

        Depth depth = btce.getDepth(PAIR);

        List<OrderInfo> asks = depth.getAsks();
        assertNotNull(asks);

        OrderInfo askOrder1 = asks.get(0);
        assertEquals(426, askOrder1.getPrice(), DELTA);
        assertEquals(0.46726227, askOrder1.getAmount(), DELTA);

        OrderInfo askOrder2 = asks.get(1);
        assertEquals(427, askOrder2.getPrice(), DELTA);
        assertEquals(0.35, askOrder2.getAmount(), DELTA);

        List<OrderInfo> bids = depth.getBids();
        assertNotNull(bids);

        OrderInfo bidOrder1 = bids.get(0);
        assertEquals(420.5, bidOrder1.getPrice(), DELTA);
        assertEquals(0.9277161, bidOrder1.getAmount(), DELTA);

        OrderInfo bidOrder2 = bids.get(1);
        assertEquals(420.2, bidOrder2.getPrice(), DELTA);
        assertEquals(7.48484346, bidOrder2.getAmount(), DELTA);
    }

    @Test
    public void testGetTrades() throws Exception {
        final String JSON_RESPONSE = "[{\"date\":1355412657,\"price\":420.5,\"amount\":0.132284,\"tid\":403436," +
                "\"price_currency\":\"RUR\",\"item\":\"BTC\",\"trade_type\":\"ask\"}]";

        when(handler.fetchResponse(PAIR, "trades")).thenReturn(JSON_RESPONSE);

        List<TradeInfo> trades = btce.getTrades(PAIR);

        assertNotNull(trades);
        assertEquals(1, trades.size());

        TradeInfo tradeInfo1 = trades.get(0);

        assertEquals(1355412657, tradeInfo1.getDate());
        assertEquals(420.5, tradeInfo1.getPrice(), DELTA);
        assertEquals(0.132284, tradeInfo1.getAmount(), DELTA);
        assertEquals(403436, tradeInfo1.getTid());
        assertEquals(Currency.RUR, tradeInfo1.getPriceCurrency());
        assertEquals(Currency.BTC, tradeInfo1.getItem());
        assertEquals(TradeType.ASK, tradeInfo1.getType());
    }
}
