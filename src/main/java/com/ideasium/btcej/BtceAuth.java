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

package com.ideasium.btcej;

import com.ideasium.btcej.common.BtceException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
