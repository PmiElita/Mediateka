package com.mediateka.dao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mediateka.model.User;
import com.mediateka.oauth2.FacebookOAuth2Details;

public class FacebookLoginDAO {
	private String accessToken;
	private String userId;

	public void getAccessToken(String code) {
		try {
			String accessTokenString = FacebookOAuth2Details.accessTokenUri
					+ "?client_id=" + FacebookOAuth2Details.clientId
					+ "&client_secret=" + FacebookOAuth2Details.clientSecret
					+ "&code=" + code + "&redirect_uri="
					+ FacebookOAuth2Details.redirectUri;

			URL myurl = new URL(accessTokenString);
			HttpsURLConnection con = (HttpsURLConnection) myurl
					.openConnection();
			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins, "UTF-8");

			JSONParser parser = new JSONParser();
			JSONObject jObject = (JSONObject) parser.parse(isr);
			accessToken = jObject.get("access_token").toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public User getUserFromSocNet() {
		try {

			String methodString = FacebookOAuth2Details.methodUri
					+ "me?access_token=" + accessToken;
			URL newUrl = new URL(methodString);

			HttpsURLConnection con = (HttpsURLConnection) newUrl
					.openConnection();
			InputStream ins = con.getInputStream();

			InputStreamReader newIsr = new InputStreamReader(ins, "UTF-8");
			JSONParser parser = new JSONParser();
			JSONObject jsonUser = (JSONObject) parser.parse(newIsr);
			userId = jsonUser.get("id").toString();

			User user = new User();
			user.setSocialId("fb" + userId);

			user.setFirstName(jsonUser.get("first_name").toString());

			user.setLastName(jsonUser.get("last_name").toString());
			if (jsonUser.get("email") != null) {

				user.setEmail(jsonUser.get("email").toString());
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserForLogIn() throws SQLException,
			ReflectiveOperationException {

		return UserDAO.getUserBySocialId("fb" + userId);
	}

	public boolean isRegistrated() throws SQLException,
			ReflectiveOperationException {

		if (UserDAO.getUserBySocialId("fb" + userId) != null)
			return true;
		return false;
	}
}
