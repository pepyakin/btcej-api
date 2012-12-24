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

/**
 * @author pepyakin
 */
public class Btce {

	private BtceAuth auth;
    private ConnectionFactory connectionFactory;

    public static Btce getBtce(String key, String secret) {
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
	}
	
	public UserInfo getUserInfo() {
        RequestTemplate template = new RequestTemplate(connectionFactory);

        try {
            return template.makeRequest("getInfo", UserInfo.class);
        } catch (IOException e) {
            throw new BtceException(e);
        }
    }

    public TransactionHistoryRequestBuilder getTransactionHistory() {
        RequestTemplate template = new RequestTemplate(connectionFactory);
        return new TransactionHistoryRequestBuilder(template);
    }
}
