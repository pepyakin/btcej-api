/**
 * 
 */
package com.ideasium.btcej.general;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideasium.btcej.common.BtceException;
import com.ideasium.btcej.common.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Методы доступа к публичным данным биржи BTC-E.
 * 
 * @author pepyakin
 */
public class GeneralBtce {

    private final ObjectMapper mapper;
    private final RequestHandler requestHandler;

    public GeneralBtce(ObjectMapper mapper, RequestHandler requestHandler) {
        this.mapper = mapper;
        this.requestHandler = requestHandler;
    }

    /**
     *
     * @param pair {@link Pair Валютная пара} относительно которой необходимо сделать запрос.
     * @return
     */
    public Ticker getTicker(Pair pair) throws BtceException {
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
    public Depth getDepth(Pair pair) throws BtceException {
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
    public List<TradeInfo> getTrades(Pair pair) throws BtceException {
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

    private JsonNode queryResultRoot(Pair pair, String methodName) throws BtceException {
        try {
            String respone = requestHandler.getResponse(pair, methodName);
            return mapper.readTree(respone);
        } catch (IOException e) {
            throw new BtceException(e);
        }
    }
}
