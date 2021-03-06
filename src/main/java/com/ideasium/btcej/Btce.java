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

import java.io.IOException;

/**
 * @author pepyakin
 */
public class Btce {

    private BtceAuth auth;
    private ConnectionFactory connectionFactory;

    public static Btce getBtce(String key, String secret) {
        BtceAuth auth = new BtceAuth(key, secret);
        return new Btce(auth);
    }

    /**
     * @param auth Информация необходимая для авторизации клиента. Не может быть <code>null</code>.
     */
    public Btce(BtceAuth auth) {
        if (auth == null) {
            throw new IllegalArgumentException();
        }

        this.auth = auth;

        connectionFactory = new ConnectionFactory(
                "https://btc-e.com/tapi",
                new NonceHelper(),
                auth
        );
    }

    public UserInfo getUserInfo() {
        RequestTemplate template = getRequestTemplate();

        try {
            return template.makeRequest("getInfo", UserInfo.class);
        } catch (IOException e) {
            throw new BtceException(e);
        }
    }

    public TransactionHistoryRequestBuilder getTransactionHistory() {
        RequestTemplate template = getRequestTemplate();
        return new TransactionHistoryRequestBuilder(template);
    }

    public OrderHistoryRequestBuilder orderHistory() {
        RequestTemplate template = getRequestTemplate();
        return new OrderHistoryRequestBuilder(template);
    }

    public TradeRequestBuilder trade() {
        RequestTemplate template = getRequestTemplate();
        return new TradeRequestBuilder(template);
    }

    public CancelOrderAnswer cancelOrder(long orderId) {
        RequestTemplate template = getRequestTemplate();
        template.setParam("order_id", Long.toString(orderId));

        try {
            return template.makeRequest("CancelOrder", CancelOrderAnswer.class);
        } catch (IOException e) {
            throw new BtceException(e);
        }
    }

    private RequestTemplate getRequestTemplate() {
        return new RequestTemplate(connectionFactory);
    }
}
