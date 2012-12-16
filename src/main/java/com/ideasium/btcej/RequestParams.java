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

    public String buildPostData() {
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
