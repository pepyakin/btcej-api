/**
 * 
 */
package com.ideasium.btcej;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.ideasium.btcej.common.BtceException;

/**
 * Класс необходимый для авторизации клиента.
 * Хранит ключ API и секретный ключ для подписания сообщения.
 * 
 * @author pepyakin
 */
public class BtceAuth {

	private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();

	private String key;
	private Mac hmacSha512;
    private HexStringMaker hexMaker = new HexStringMaker();

	public BtceAuth(String key, String secretKey) {
		if (key == null) {
			throw new IllegalArgumentException("key");
		}

		if (secretKey == null) {
			throw new IllegalArgumentException("secretKey");
		}

		try {
			hmacSha512 = Mac.getInstance("HmacSHA512");

			SecretKeySpec secretKeySpec = new SecretKeySpec(
					secretKey.getBytes(DEFAULT_CHARSET), "HmacSHA512");
			hmacSha512.init(secretKeySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new BtceException("There are no HMAC SHA512 algorithm", e);
		} catch (InvalidKeyException e) {
			throw new BtceException("Provided key is invalid", e);
		}

		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public Mac getHmacSha512() {
		return hmacSha512;
	}

    public String signMessage(String message) {
        byte[] d = hmacSha512.doFinal(message.getBytes());
        return hexMaker.getHexString(d);
    }
}
