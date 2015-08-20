/**
 * 
 */
package com.spring.controller;

import java.io.Serializable;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author MIsmail1
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Size(min = 3, max = 50)
	private String userName;
	@NotNull
	@Size(min = 3, max = 50)
	private String password;
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	
	
}
