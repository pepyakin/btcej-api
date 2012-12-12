/**
 * 
 */
package com.ideasium.btcej.general;

import com.ideasium.btcej.common.Pair;

/**
 * Методы доступа к публичным данным биржи BTC-E.
 * 
 * @author knott
 */
public class GeneralBtce {
	
	public static PairRequest forPair(Pair pair) {
		return new PairRequest(pair);
	}
}
