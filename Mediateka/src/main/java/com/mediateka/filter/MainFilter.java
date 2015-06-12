package com.mediateka.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.reflections.Reflections;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;

/**
 * Servlet Filter implementation class MainFilter
 */
public class MainFilter implements Filter {

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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
	
		if (uri.matches(".*\\..*")) {
			chain.doFilter(request, response);
		}
		else {
			String method = httpRequest.getMethod().toLowerCase();
			try {
				executeAction(httpRequest, httpResponse, findPathUri(uri), method);
			} catch (Exception e){
				e.printStackTrace();
				httpResponse.sendError(404);
			}
		}

	}
	
	private String findPathUri(String uri){
		
		String urlString;
		
		urlString = uri.substring(uri.indexOf('/',1));

		urlString = urlString.substring(1);

		if(urlString.equals("")){
			return "index";
		}
		return urlString;
		
	}

	
	private void executeAction(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, String url, String method)
			throws Exception {		
		Reflections reflections = new Reflections(  // set package with controller classes
				"com.mediateka.controller");

		  Set<Class<?>> annotated = 
	               reflections.getTypesAnnotatedWith(Controller.class);  // get all classes from package com.mediateka.controller
		Method action = null;                                            // which are annotated with Controller annotation
		Class<?> actionClass = Object.class;
		outer: for (Class<?> c : annotated) {
			for (Method m : c.getDeclaredMethods()) {
				if (m.isAnnotationPresent(Request.class)) {
					if (m.getAnnotation(Request.class).url().equals(url)       // search method annotated URL annotation 
							&& (m.getAnnotation(Request.class).method()         // whith value of parameters url and method
									.equals(method))) {
						action = m;
						actionClass = c;
						break outer;
					}
				}
			}
		}
       if (action!=null){
		action.invoke( actionClass, httpRequest, httpResponse);  // invoke found method
       }
       else {
    	  throw new Exception();
       }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
