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

package com.ideasium.btcej.common;

import com.fasterxml.jackson.annotation.JsonValue;

import static com.ideasium.btcej.common.Currency.*;

/**
 * Перечисление, определяющее основные валютные пары, поддерживаемые
 * биржой BTC-E.
 *
 * @author pepyakin
 */
public enum Pair {
    /**
     * Пара {@link Currency#BTC Bitcoin} к {@link Currency#USD USD}.
     */
    BTC_USD(BTC, USD),

    /**
     * Пара {@link Currency#LTC Litecoin} к {@link Currency#BTC Bitcoin}.
     */
    LTC_BTC(LTC, BTC),
    LTC_USD(LTC, USD),
    NMC_BTC(NMC, BTC),
    BTC_RUR(BTC, RUR),
    USD_RUR(USD, RUR);

    private final Currency source;
    private final Currency destination;

    public Currency getSource() {
        return source;
    }

    public Currency getDestination() {
        return destination;
    }

    private Pair(Currency source, Currency destination) {
        this.source = source;
        this.destination = destination;
    }

    /**
     * @return Идентификатор пригодный для использования в запросах.
     */
    @JsonValue
    public String getName() {
        StringBuilder sb = new StringBuilder();

        // Идентификаторы пары должны быть в нижнем регистре.
        sb.append(source.getName().toLowerCase());
        sb.append('_');
        sb.append(destination.getName().toLowerCase());

        return sb.toString();
    }
}
