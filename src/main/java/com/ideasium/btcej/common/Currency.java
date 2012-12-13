/**
 * 
 */
package com.ideasium.btcej.common;

/**
 * Перечисление, определяющее основные валюты используемые на бирже
 * BTC.
 *
 * @see Pair
 * @author pepyakin
 */
public enum Currency {
    /**
     * Bitoin.
     */
	BTC("BTC"),

    /**
     * Доллары США.
     */
	USD("USD"),

    /**
     * Litecoin.
     */
	LTC("LTC"),

    /**
     * Namecoin.
     */
	NMC("NMC"),

    /**
     * Российские Рубли.
     */
	RUR("RUR");
	
	private final String name;

    /**
     * @return Идентификатор валюты.
     */
	public String getName() {
		return name;
	}

	private Currency(String name) {
		this.name = name;
	}

    /**
     * @param name Идентификатор валюты.
     * @return Валюту по ее идентификатору.
     */
	public static Currency findByName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		
		for (Currency currency : Currency.values()) {
			if (currency.getName().equalsIgnoreCase(name)) {
				return currency;
			}
		}
		
		return null;
	}
}
