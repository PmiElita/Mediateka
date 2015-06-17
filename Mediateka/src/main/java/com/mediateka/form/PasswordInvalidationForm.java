package com.mediateka.form;

import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class PasswordInvalidationForm {

	@Validation(regexp=RegExps.EMAIL, maxLength=45, minLength=1)
	String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PasswordInvalidationForm [email=" + email + "]";
	}
	
	
	
}
