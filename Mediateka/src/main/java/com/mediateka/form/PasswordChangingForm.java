package com.mediateka.form;

public class PasswordChangingForm {

	private String token;
	private String password;
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
