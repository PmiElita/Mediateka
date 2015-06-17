package com.mediateka.form;

import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class LogInForm {

	@Validation(regexp=RegExps.EMAIL, maxLength=45, minLength=1)
	private String email;
	
	@Validation(regexp=RegExps.ANY_CHARACTERS, maxLength=64, minLength=1)
	private String password;
	
	
	@Override
	public String toString() {
		return "LogInForm [email=" + email + ", password=" + password + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
