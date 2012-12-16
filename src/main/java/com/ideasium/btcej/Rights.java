package com.ideasium.btcej;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс представляет возможность узнать какие операции доступны данному {@link BtceAuth аккаунту}.
 *
 * @author pepyakin
 */
public class Rights {

    @JsonProperty("info")
    private boolean info;

    @JsonProperty("trade")
    private boolean trade;

    @JsonProperty("withdraw")
    private boolean withdraw;

    private Rights() {
    }

    /**
     * @return Разрешается ли запрашивать информацию о данном аккаунте.
     */
    public boolean isInfoAvailable() {
        return info;
    }

    /**
     * @return Разрешается ли проводить торговые транзакции на данном аккаунте.
     */
    public boolean isTradeAvailable() {
        return trade;
    }

    /**
     * @return Разрешается ли проводить вывод на данном аккаунте.
     */
    public boolean isWithdrawAvailable() {
        return withdraw;
    }

    @Override
    public String toString() {
        return "Rights{" +
                "info=" + info +
                ", trade=" + trade +
                ", withdraw=" + withdraw +
                '}';
    }
}
