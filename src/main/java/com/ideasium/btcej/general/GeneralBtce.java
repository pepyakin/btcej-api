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
 * @author knott
 */
public class GeneralBtce {

    private final ObjectMapper mapper = new ObjectMapper();


    /**
     *
     * @param pair {@link Pair Валютная пара} относительно которой необходимо сделать запрос.
     * @return
     */
    public Ticker getTicker(Pair pair) throws BtceException {
        String requestUrl = getRequestUrl(pair, "ticker");
        JsonNode response = queryResultRoot(requestUrl);

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
        String requestUrl = getRequestUrl(pair, "depth");
        JsonNode response = queryResultRoot(requestUrl);

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
        String requestUrl = getRequestUrl(pair, "trades");
        JsonNode response = queryResultRoot(requestUrl);

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

    private static String getRequestUrl(Pair pair, String methodName) {
        return String.format("https://btc-e.com/api/2/%s/%s", pair.getName(), methodName);
    }

    private JsonNode queryResultRoot(String urlSpec) throws BtceException {
        try {
            String respone = query(urlSpec);
            return mapper.readTree(respone);
        } catch (IOException e) {
            throw new BtceException(e);
        }
    }

    private static String query(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openStream()));

        StringBuilder responseContent = new StringBuilder();

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseContent.append(inputLine);
        }

        return responseContent.toString();
    }
}
