/**
 * 
 */
package com.ideasium.btcej;

/**
 * @author knott
 */
public class Btce {

	private long nonce;
	private BtceAuth auth;
	
	/**
	 * @param auth Информация необходимая для авторизации клиента. Не может быть <code>null</code>.
	 */
	public Btce(BtceAuth auth) {
		if (auth == null) {
			throw new IllegalArgumentException();
		}
		
		this.auth = auth;
	}
	
	public UserInfo getUserInfo() {
		throw new IllegalArgumentException();
	}
}
