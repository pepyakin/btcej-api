package com.ideasium.btcej.general;

import com.ideasium.btcej.common.Pair;
import org.jetbrains.annotations.NonNls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author pepyakin
 */
class HttpV2ResponseFetcher implements ResponseFetcher {

    @NonNls
    public static final String API_BASE_URL = "https://btc-e.com/api/2/";

    public String fetchResponse(Pair pair, String method) throws IOException {
        String requestUrl = getRequestUrl(pair, method);
        return query(requestUrl);
    }

    public String getRequestUrl(Pair pair, String methodName) {
        final String API_URL_FORMAT = API_BASE_URL + "%s/%s";

        return String.format(API_URL_FORMAT, pair.getName(), methodName);
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
