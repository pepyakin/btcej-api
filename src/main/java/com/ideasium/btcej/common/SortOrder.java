/**
 * 
 */
package com.ideasium.btcej.common;

/**
 * @author pepyakin
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
