/**
 *
 */
package com.ideasium.btcej;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pepyakin
 */
public class UserInfo {

    @JsonProperty("funds")
    private Funds funds;

    @JsonProperty("rights")
    private Rights rights;

    @JsonProperty("transaction_count")
    private int transactionCount;

    @JsonProperty("open_orders")
    private int openOrdersCount;

    @JsonProperty("server_time")
    private long serverTime;

    public Funds getFunds() {
        return funds;
    }

    /**
     * @return Объект {@link Rights}, с помощью которого можно
     * узнать о доступных операциях данному аккаунту.
     */
    public Rights getRights() {
        return rights;
    }

    /**
     * @return Количество транзакций выполненых данным {@link BtceAuth аккаунтом}.
     */
    public int getTransactionCount() {
        return transactionCount;
    }

    /**
     * @return Количество открытых ордеров на данный момент.
     */
    public int getOpenOrdersCount() {
        return openOrdersCount;
    }

    /**
     * @return Время сервера в формате UNIX-time.
     */
    public long getServerTime() {
        return serverTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "funds=" + funds +
                ", rights=" + rights +
                ", transactionCount=" + transactionCount +
                ", openOrdersCount=" + openOrdersCount +
                ", serverTime=" + serverTime +
                '}';
    }
}
