package com.mediateka.dao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mediateka.model.User;
import com.mediateka.oauth2.VKOAuth2Details;

public class VKLoginDAO {
	private String accessToken;
	private String userId;
	private String email;

	public void getAccessToken(String code) {
		try {
			String accessTokenString = VKOAuth2Details.accessTokenUri
					+ "?client_id=" + VKOAuth2Details.clientId
					+ "&client_secret=" + VKOAuth2Details.clientSecret
					+ "&code=" + code + "&redirect_uri="
					+ VKOAuth2Details.redirectUri;
			URL myurl = new URL(accessTokenString);
			HttpsURLConnection con = (HttpsURLConnection) myurl
					.openConnection();
			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins, "UTF-8");
			JSONParser parser = new JSONParser();
			JSONObject jObject = (JSONObject) parser.parse(isr);
			accessToken = jObject.get("access_token").toString();
			userId = jObject.get("user_id").toString();
			email = jObject.get("email").toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isRegistrated() throws SQLException,
			ReflectiveOperationException {
		if (UserDAO.getUserBySocialId("vk" + userId) != null)
			return true;
		return false;
	}

	public User getUserFromSocNet() {
		try {

			String methodString = VKOAuth2Details.methodUri
					+ "users.get?user_ids=" + userId
					+ "&fields=city,sex,bdate&v=5.28&access_token="
					+ accessToken + "&lang=ua";
			URL newUrl = new URL(methodString);
			HttpsURLConnection con = (HttpsURLConnection) newUrl
					.openConnection();
			InputStream ins = con.getInputStream();

			InputStreamReader newIsr = new InputStreamReader(ins, "UTF-8");
			JSONParser parser = new JSONParser();
			JSONObject response = (JSONObject) parser.parse(newIsr);
			JSONArray jsonUsers = (JSONArray) response.get("response");
			JSONObject jsonUser = (JSONObject) jsonUsers.get(0);

			User user = new User();
			user.setSocialId("vk" + userId);
			user.setEmail(email);
			user.setFirstName(jsonUser.get("first_name").toString());

			user.setLastName(jsonUser.get("last_name").toString());
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserForLogIn() throws SQLException, ReflectiveOperationException {
		
		return UserDAO.getUserBySocialId("vk"+userId);
	}
}
