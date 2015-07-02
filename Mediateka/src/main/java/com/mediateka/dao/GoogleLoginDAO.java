package com.mediateka.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mediateka.model.User;
import com.mediateka.oauth2.GoogleOAuth2Details;

public class GoogleLoginDAO {

	public User getUserFromSocialNetwork(String token) throws IOException,
			ParseException {

		User user = new User();
		String getUsetInfo = GoogleOAuth2Details.methodUri + "me"
				+ "?access_token=" + token;

		URL myurl = new URL(getUsetInfo);
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		InputStream ins = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins, "UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject jObject = (JSONObject) parser.parse(isr);
		user.setSocialId("g+" + jObject.get("id").toString());
		user.setEmail(((JSONObject) ((JSONArray) jObject.get("emails")).get(0))
				.get("value").toString());
		JSONObject jsonName = (JSONObject) jObject.get("name");
		user.setLastName(jsonName.get("familyName").toString());
		user.setFirstName(jsonName.get("givenName").toString());
		return user;
	}
}
