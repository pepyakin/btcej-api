/**
 * 
 */
package com.ideasium.btcej.common;

/**
 * @author pepyakin
 *
 */
public class BtceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7930546232987642967L;

	public BtceException() {
		super();
	}

	public BtceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public BtceException(String message) {
		super(message);
	}

	public BtceException(Throwable throwable) {
		super(throwable);
	}
}
