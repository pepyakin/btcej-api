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
