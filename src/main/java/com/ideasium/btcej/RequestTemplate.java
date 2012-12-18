package com.ideasium.btcej;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideasium.btcej.common.BtceException;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;

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
