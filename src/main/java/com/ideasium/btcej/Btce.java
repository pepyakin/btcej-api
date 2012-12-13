/**
 * 
 */
package com.ideasium.btcej;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideasium.btcej.common.BtceException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author pepyakin
 */
public class Btce {

	private long nonce;
	private BtceAuth auth;


    public static Btce getBtce(String key, String secret) throws BtceException {
        BtceAuth auth = new BtceAuth(key, secret);
        return new Btce(auth);
    }

	/**
	 * @param auth Информация необходимая для авторизации клиента. Не может быть <code>null</code>.
	 */
	public Btce(BtceAuth auth) {
		if (auth == null) {
			throw new IllegalArgumentException();
		}
		
		this.auth = auth;
        nonce = System.currentTimeMillis() / 1000;
	}


    private String signBytes(String dataString) {
        final char[] ALPHABET = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        byte[] dataBytes = dataString.getBytes();
        byte[] signedBytes = auth.getHmacSha512().doFinal(dataBytes);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < signedBytes.length; i++) {
            byte value = signedBytes[i];

            sb.append(ALPHABET[((value & 0xF0) >> 4)]);
            sb.append(ALPHABET[value & 0x0F]);
        }

        return sb.toString();
    }

    private String buildPostData(Map<String, String> args) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        boolean first = true;

        for (Map.Entry<String, String> entry : args.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (first) {
                first = false;
            } else {
                sb.append('&');
            }

            sb.append(URLEncoder.encode(key, "utf8"));
            sb.append('=');
            sb.append(URLEncoder.encode(value, "utf8"));
        }

        return sb.toString();
    }

    private String query(Map<String, String> args) throws IOException {
        final String tapi = "https://btc-e.com/tapi";

        String dataString = buildPostData(args);
        String signBytes = signBytes(dataString);

        HttpsURLConnection connection = (HttpsURLConnection) new URL(tapi).openConnection();

        connection.setDoOutput(true);

        connection.setRequestProperty("Key", auth.getKey());
        connection.setRequestProperty("Sign", signBytes);

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(dataString);
        writer.close();

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        BufferedReader in = new BufferedReader(reader);

        StringBuilder responseContent = new StringBuilder();

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseContent.append(inputLine);
        }

        return responseContent.toString();
    }
	
	public UserInfo getUserInfo() throws BtceException {


        Map<String, String> args = new TreeMap<String, String>();
        args.put("method", "getInfo");
        args.put("nonce", Long.toString(nonce++));

        try {
            String response = query(args);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            if (root.get("success").asInt() != 1) {
                throw new BtceException(root.get("error").asText());
            }

            return mapper.treeToValue(root.get("return"), UserInfo.class);
        } catch (IOException e) {
            throw new BtceException(e);
        }
    }
}
