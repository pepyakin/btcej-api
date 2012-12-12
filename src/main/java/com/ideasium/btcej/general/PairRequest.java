/**
 * 
 */
package com.ideasium.btcej.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideasium.btcej.common.BtceException;
import com.ideasium.btcej.common.Pair;

/**
 * @author knott
 * 
 */
public class PairRequest {

	private static final String API_URL = "https://btc-e.com/api/2/";

	private String pairApiUrl;
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * @param pair
	 *            Необходимая {@link com.ideasium.btcej.common.Pair пара} относительно которой необходимо
	 *            сделать запросы.
	 */
	public PairRequest(Pair pair) {
		if (pair == null) {
			throw new IllegalArgumentException();
		}

		pairApiUrl = API_URL + pair.getName() + '/';
	}
	
	public Ticker getTicker() throws BtceException {
		String requestUrl = pairApiUrl + "ticker";
		JsonNode response = queryResultRoot(requestUrl);

		try {
			return mapper.treeToValue(response.get("ticker"), Ticker.class);
		} catch (JsonProcessingException e) {
			throw new BtceException(e);
		}		
	}
	
	/**
	 * @return Глубина биржи. Список текущих ордеров на покупку и продажу.
	 */
	public Depth getDepth() throws BtceException {
		String requestUrl = pairApiUrl + "depth";
		JsonNode response = queryResultRoot(requestUrl);

		try {
			return mapper.treeToValue(response, Depth.class);
		} catch (JsonProcessingException e) {
			throw new BtceException(e);
		}
	}

	/**
	 * @return Список последних транзакций.
	 */
	public List<TradeInfo> getTrades() throws BtceException {
		String requestUrl = pairApiUrl + "trades";
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

	private JsonNode queryResultRoot(String urlSpec) throws BtceException {
		try {
			String respone = query(urlSpec);
			JsonNode root = mapper.readTree(respone);

			// if (root.get("success").asInt() != 1) {
			// String errorMsg = root.get("error").asText();
			//
			// throw new BtceException(errorMsg);
			// }
			//
			// return root.get("result");

			return root;
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
