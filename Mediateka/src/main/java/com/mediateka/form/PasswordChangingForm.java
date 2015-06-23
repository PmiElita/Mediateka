package com.mediateka.form;

import com.mediateka.annotation.Validation;
import com.mediateka.util.RegExps;

public class PasswordChangingForm {

	@Validation(regexp = RegExps.ANY_CHARACTERS, maxLength = 64, minLength=1)
	private String oldPassword;
	@Validation(regexp = RegExps.PASSWORD, maxLength = 64, minLength=1)
	private String newPassword;
	@Validation(regexp = RegExps.PASSWORD, maxLength = 64, minLength=1)
	private String confirmNewPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	@Override
	public String toString() {
		return "PasswordChangingForm [oldPassword=" + oldPassword
				+ ", newPassword=" + newPassword + ", confirmNewPassword="
				+ confirmNewPassword + "]";
	}

}
