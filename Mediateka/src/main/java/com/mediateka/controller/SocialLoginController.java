package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.model.User;
import com.mediateka.model.UserCard;
import com.mediateka.model.enums.State;
import com.mediateka.oauth2.FacebookOAuth2Details;
import com.mediateka.oauth2.GoogleOAuth2Details;
import com.mediateka.oauth2.VKOAuth2Details;
import com.mediateka.service.FacebookLoginService;
import com.mediateka.service.GoogleLoginService;
import com.mediateka.service.ProfessionService;
import com.mediateka.service.UserCardService;
import com.mediateka.service.UserService;
import com.mediateka.service.VKLoginService;

@Controller
public class SocialLoginController {

	@Request(url = "vkLogin", method = "get")
	public static void vkLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String autorizationString = VKOAuth2Details.userAuthorizationUri
				+ "?client_id=" + VKOAuth2Details.clientId + "&redirect_uri="
				+ VKOAuth2Details.redirectUri + "&scope="
				+ VKOAuth2Details.scope + "&display=" + VKOAuth2Details.display;
		response.sendRedirect(autorizationString);
	}

	@Request(url = "vkLoginCode", method = "get")
	public static void getVKLoginCode(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, ServletException, IOException {
		String code = request.getParameter("code");

		if (code != null) {
			VKLoginService vKLoginService = new VKLoginService();
			vKLoginService.getAccessToken(code);
             
			if (!vKLoginService.isRegistrated()) {
				User user = vKLoginService.getUserFromSocNet();
				request.setAttribute("professions",
						ProfessionService.getProfessionAll());
				request.setAttribute("user", user);
				request.getRequestDispatcher(
						"pages/form/social_registration_form.jsp").forward(
						request, response);
			} else {

				User user = vKLoginService.getUserForLogIn();
				if (!user.getState().equals(State.ACTIVE)) {
					response.sendRedirect("index");
					return;
				}
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("userRole", user.getRole());
				UserCard userCard = UserCardService.getUserCardByUserId(user
						.getId());

				request.getSession().setAttribute("userCard", userCard);

				response.sendRedirect("cabinet");
			}
		} else {
			response.sendError(404);
		}
	}

	@Request(url = "googleLogin", method = "get")
	public static void googleLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String autorizationString = GoogleOAuth2Details.userAuthorizationUri
				+ "?client_id=" + GoogleOAuth2Details.clientId
				+ "&redirect_uri=" + GoogleOAuth2Details.redirectUri
				+ "&scope=" + GoogleOAuth2Details.scope
				+ "&response_type=token";
		response.sendRedirect(autorizationString);
	}

	@Request(url = "googleLoginCode", method = "get")
	public static void googleVKLoginCode(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, ServletException, IOException {
		request.getRequestDispatcher("pages/additional/googleLogin.jsp")
				.forward(request, response);
	}

	@Request(url = "googleLogin", method = "post")
	public static void googleLoginPost(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ReflectiveOperationException, ServletException, IOException,
			ParseException {
		String token = request.getParameter("token");
		if (token != null) {
			GoogleLoginService googleLoginService = new GoogleLoginService();
			User socialUser = googleLoginService
					.getUserFromSocialNetwork(token);
			User user = UserService.getUserBySocialId(socialUser.getSocialId());
			if (user == null) {

				request.setAttribute("professions",
						ProfessionService.getProfessionAll());
				request.setAttribute("user", socialUser);
				request.getRequestDispatcher(
						"pages/form/social_registration_form.jsp").forward(
						request, response);
			} else {
				if (!user.getState().equals(State.ACTIVE)) {
					response.sendRedirect("index");
					return;
				}

				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("userRole", user.getRole());

				UserCard userCard = UserCardService.getUserCardByUserId(user
						.getId());

				request.getSession().setAttribute("userCard", userCard);

				response.sendRedirect("cabinet");
			}
		} else {
			response.sendError(404);
		}
	}

	@Request(url = "facebookLogin", method = "get")
	public static void getFacebookLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String autorizationString = FacebookOAuth2Details.userAuthorizationUri
				+ "?client_id=" + FacebookOAuth2Details.clientId
				+ "&redirect_uri=" + FacebookOAuth2Details.redirectUri
				+ "&scope=" + FacebookOAuth2Details.scope + "&display="
				+ FacebookOAuth2Details.display;
		response.sendRedirect(autorizationString);
	}
	
	@Request(url = "facebookLoginCode", method = "get")
	public static void getFacebookLoginCode(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ReflectiveOperationException, ServletException, IOException {
		String code = request.getParameter("code");

		if (code != null) {
			FacebookLoginService facebookLoginService = new FacebookLoginService();
			facebookLoginService.getAccessToken(code);
            facebookLoginService.getUserFromSocNet();
			if (!facebookLoginService.isRegistrated()) {
				User user = facebookLoginService.getUserFromSocNet();
            
				request.setAttribute("professions",
						ProfessionService.getProfessionAll());
				request.setAttribute("user", user);
				request.getRequestDispatcher(
						"pages/form/social_registration_form.jsp").forward(
						request, response);
			} else {
			User user = facebookLoginService.getUserForLogIn();
			if (!user.getState().equals(State.ACTIVE)) {
				response.sendRedirect("index");
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userRole", user.getRole());
			UserCard userCard = UserCardService.getUserCardByUserId(user
					.getId());

			request.getSession().setAttribute("userCard", userCard);

			response.sendRedirect("cabinet");
			}
		} else {
			response.sendError(404);
		}
	}
}
