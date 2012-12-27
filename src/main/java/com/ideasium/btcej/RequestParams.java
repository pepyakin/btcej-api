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
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс
 *
 * @author pepyakin
 */
class RequestParams {

    private static final String PARAM_METHOD = "method";
    private static final String PARAM_NONCE = "nonce";

    private final Map<String, String> requestParams;

    public RequestParams() {
        requestParams = new HashMap<String, String>();
    }

    public void setMethod(@NotNull String method) {
        requestParams.put(PARAM_METHOD, method);
    }

    public void setNonce(long nonce) {
        requestParams.put(PARAM_NONCE, Long.toString(nonce));
    }

    public void setParam(@NotNull String name, @NotNull String value) {
        validateParamName(name);
        requestParams.put(name, value);
    }

    private void validateParamName(String name) {
        if (PARAM_NONCE.equalsIgnoreCase(name)) {
            throw new IllegalArgumentException();
        }

        if (PARAM_METHOD.equalsIgnoreCase(name)) {
            throw new IllegalArgumentException();
        }
    }

    public String buildQuery() {
        StringBuilder sb = new StringBuilder();

        boolean firstIteration = true;

        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if (firstIteration) {
                firstIteration = false;
            } else {
                // Перед вторым и последующими аргументами добавить &.
                sb.append('&');
            }

            try {
                String key = URLEncoder.encode(entry.getKey(), "utf8");
                String value = URLEncoder.encode(entry.getValue(), "utf8");

                sb.append(key);
                sb.append('=');
                sb.append(value);
            } catch (UnsupportedEncodingException e) {
                throw new BtceException(e);
            }
        }

        return sb.toString();
    }
}
