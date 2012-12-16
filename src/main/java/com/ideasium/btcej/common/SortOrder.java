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
	
	private final String name;

	private SortOrder(String name) {
		this.name = name;
	}

    public String getName() {
        return name;
    }
}
