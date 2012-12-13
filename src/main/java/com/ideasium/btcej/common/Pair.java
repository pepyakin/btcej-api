/**
 * 
 */
package com.ideasium.btcej.common;

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
	public String getName() {
		StringBuilder sb = new StringBuilder();

        // Идентификаторы пары должны быть в нижнем регистре.
		sb.append(source.getName().toLowerCase());
		sb.append('_');
		sb.append(destination.getName().toLowerCase());

		return sb.toString();
	}
}
