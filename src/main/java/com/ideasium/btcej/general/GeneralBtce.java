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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideasium.btcej.common.BtceException;
import com.ideasium.btcej.common.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Методы доступа к публичным данным биржи BTC-E.
 *
 * @author pepyakin
 */
public class GeneralBtce {

    private final ObjectMapper mapper;
    private final ResponseFetcher responseFetcher;

    public GeneralBtce(ObjectMapper mapper, ResponseFetcher responseFetcher) {
        this.mapper = mapper;
        this.responseFetcher = responseFetcher;
    }

    /**
     * @param pair {@link Pair Валютная пара} относительно которой необходимо сделать запрос.
     * @return
     */
    public Ticker getTicker(Pair pair) {
        JsonNode response = queryResultRoot(pair, "ticker");

        try {
            return mapper.treeToValue(response.get("ticker"), Ticker.class);
        } catch (JsonProcessingException e) {
            throw new BtceException(e);
        }
    }

    /**
     * @param pair {@link Pair Валютная пара} относительно которой необходимо сделать запрос.
     * @return Глубина биржи. Список текущих ордеров на покупку и продажу.
     */
    public Depth getDepth(Pair pair) {
        JsonNode response = queryResultRoot(pair, "depth");

        try {
            return mapper.treeToValue(response, Depth.class);
        } catch (JsonProcessingException e) {
            throw new BtceException(e);
        }
    }

    /**
     * @param pair {@link Pair Валютная пара} относительно которой необходимо сделать запрос.
     * @return Список последних транзакций.
     */
    public List<TradeInfo> getTrades(Pair pair) {
        JsonNode response = queryResultRoot(pair, "trades");

        List<TradeInfo> infos = new ArrayList<TradeInfo>();

        try {
            for (JsonNode node : response) {
                TradeInfo tradeInfo;

                tradeInfo = mapper.treeToValue(node, TradeInfo.class);
                infos.add(tradeInfo);

            }
        } catch (JsonProcessingException e) {
            throw new BtceException(e);
        }

        return infos;
    }

    public double getFee(Pair pair) {
        throw new RuntimeException();
    }

    private JsonNode queryResultRoot(Pair pair, String methodName) {
        try {
            String respone = responseFetcher.fetchResponse(pair, methodName);
            return mapper.readTree(respone);
        } catch (IOException e) {
            throw new BtceException(e);
        }
    }
}
