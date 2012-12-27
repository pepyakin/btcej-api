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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideasium.btcej.common.BtceException;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author pepyakin
 */
class RequestTemplate {

    private RequestParams requestParams;
    private ObjectMapper mapper;

    private ConnectionFactory factory;

    RequestTemplate(@NotNull ConnectionFactory factory) {
        this.factory = factory;

        mapper = new ObjectMapper();
        requestParams = new RequestParams();
    }

    public void setParam(@NotNull String name, @NotNull String value) {
        requestParams.setParam(name, value);
    }

    @NotNull
    public <T> T makeRequest(
            @NotNull String methodName,
            @NotNull Class<T> beanClass) throws IOException {
        HttpsURLConnection connection = factory.createConnection(methodName, requestParams);

        // Получение ответа.
        InputStream responseInput = connection.getInputStream();
        String responseContent = fetchResponse(responseInput);

        JsonNode responseRoot = mapper.readTree(responseContent);
        JsonNode returnNode = getReturnNodeOrThrow(responseRoot);

        return mapper.treeToValue(returnNode, beanClass);
    }

    private String fetchResponse(InputStream is) throws IOException {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader in = new BufferedReader(reader);

        StringBuilder responseContent = new StringBuilder();

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseContent.append(inputLine);
        }

        return responseContent.toString();
    }

    private JsonNode getReturnNodeOrThrow(JsonNode responseRoot) {
        if (responseRoot.get("success").asInt() != 1) {
            throw new BtceException(responseRoot.get("error").asText());
        }

        return responseRoot.get("return");
    }
}
