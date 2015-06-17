package com.mediateka.form;

import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class PasswordResettingForm {

	@Validation(regexp=RegExps.ANY_CHARACTERS, maxLength=64, minLength=1)
	private String token;
	
	@Validation(regexp=RegExps.PASSWORD, maxLength=64, minLength=1)
	private String password;
	
	@Validation(regexp=RegExps.PASSWORD, maxLength=64, minLength=1)
	private String confirmPassword;

	
	@Override
	public String toString() {
		return "PasswordChangingForm [token=" + token + ", password="
				+ password + ", confirmPassword=" + confirmPassword + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
