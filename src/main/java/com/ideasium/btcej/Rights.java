package com.ideasium.btcej;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pepyakin
 */
public class Rights {

    private boolean info;
    private boolean trade;

    @JsonCreator
    public Rights(
            @JsonProperty("info")
            boolean info,

            @JsonProperty("trade")
            boolean trade) {
        this.info = info;
        this.trade = trade;
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

    @Override
    public String toString() {
        return "Rights{" +
                "info=" + info +
                ", trade=" + trade +
                '}';
    }
}
