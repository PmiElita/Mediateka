package com.mediateka.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.mediateka.annotation.Roles;
import com.mediateka.container.ClassMethodPair;
import com.mediateka.container.UrlContainer;
import com.mediateka.container.UrlMethodPair;
import com.mediateka.model.enums.Role;

/**
 * Servlet Filter implementation class MainFilter
 */
public class MainFilter implements Filter {
	private static Logger logger = Logger.getLogger(MainFilter.class);

	/**
	 * Default constructor.
	 */
	public MainFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();

		httpRequest.setCharacterEncoding("UTF-8");
		httpResponse.setCharacterEncoding("UTF-8");

		if (uri.matches(".*\\..*")) {
			chain.doFilter(request, response);
		} else {
			String method = httpRequest.getMethod().toLowerCase();
			try {
				executeAction(httpRequest, httpResponse, findPathUri(uri),
						method);
			} catch (Exception e) {
				logger.warn(e);
				e.printStackTrace();
				httpResponse.sendError(404);
			}
		}

	}

	private String findPathUri(String uri) {

		String urlString;

		urlString = uri.substring(uri.indexOf('/', 1));

		urlString = urlString.substring(1);

		if (urlString.equals("")) {
			return "index";
		}
		return urlString;

	}

	private void executeAction(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, String url, String method)
			throws Exception {
		Map<UrlMethodPair, ClassMethodPair> controllerMap = UrlContainer
				.getControllerMap();
		UrlMethodPair urlMethodPair = new UrlMethodPair(url, method);
		ClassMethodPair classMethodPair = controllerMap.get(urlMethodPair);

		if (classMethodPair != null) {
			if (classMethodPair.getMethod().isAnnotationPresent(Roles.class)) {
				Role role = (Role) httpRequest.getSession().getAttribute(
						"userRole");
				if (role != null) {
					for (Role r : classMethodPair.getMethod()
							.getAnnotation(Roles.class).value()) {
						if (r.equals(role)) {
							classMethodPair.getMethod().invoke(
									classMethodPair.getClazz(), httpRequest,
									httpResponse);
							return;
						}
					}
					throw new Exception(
							"user don't have permission to invoke method "
									+ classMethodPair.getMethod().getName());
				}
			} else {
				classMethodPair.getMethod().invoke(classMethodPair.getClazz(),
						httpRequest, httpResponse); // invoke found method
			}
		} else {

			throw new Exception("can't find appropriate controller. url: "
					+ httpRequest.getRequestURI());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		String pathToLog4jConfigurator = fConfig.getServletContext()
				.getRealPath("/configurations/log4jConfiguration.xml");
		DOMConfigurator.configure(pathToLog4jConfigurator);

		Logger.getLogger(this.getClass()).debug("initiate main filter");

	}

}
