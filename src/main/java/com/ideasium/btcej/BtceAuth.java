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
 * 
 * @author knott
 */
public class BtceAuth {

	private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();

	private String key;
	private Mac hmacSha256;

	public BtceAuth(String key, String secretKey) throws BtceException {
		if (key == null) {
			throw new IllegalArgumentException("key");
		}

		if (secretKey == null) {
			throw new IllegalArgumentException("secretKey");
		}

		try {
			hmacSha256 = Mac.getInstance("HmacSHA256");

			SecretKeySpec secretKeySpec = new SecretKeySpec(
					secretKey.getBytes(DEFAULT_CHARSET), "HmacSHA256");
			hmacSha256.init(secretKeySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new BtceException("There are no HMAC SHA256 algorithm", e);
		} catch (InvalidKeyException e) {
			throw new BtceException("Provided key is invalid", e);
		}

		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public Mac getHmacSha256() {
		return hmacSha256;
	}
}
