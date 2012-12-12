/**
 * 
 */
package com.ideasium.btcej.common;

/**
 * @author knott
 *
 */
public enum SortOrder {
	ASCENDING("ASC"),
	DESCENDING("DESC");
	
	private String name;
	
	public String getName() {
		return name;
	}

	private SortOrder(String name) {
		this.name = name;
	}
}
