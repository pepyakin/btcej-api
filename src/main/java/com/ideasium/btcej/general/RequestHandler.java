package com.ideasium.btcej.general;

import com.ideasium.btcej.common.Pair;

import java.io.IOException;

/**
 *
 * @author pepyakin
 */
interface RequestHandler {

    /**
     * Выполнить запрос к общему API.
     * @param pair
     * @param method
     * @return
     * @throws IOException
     */
    String getResponse(Pair pair, String method) throws IOException;
}
