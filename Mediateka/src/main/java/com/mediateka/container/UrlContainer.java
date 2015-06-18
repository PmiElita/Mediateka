package com.mediateka.container;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;

public class UrlContainer {

	private static Map<UrlMethodPair, ClassMethodPair> controllerMap;

	public static Map<UrlMethodPair, ClassMethodPair> getControllerMap() {
		if (controllerMap == null) {
			initializeControllerMap();
		}
		return controllerMap;
	}

	private static void initializeControllerMap() {
		controllerMap = new HashMap<UrlMethodPair, ClassMethodPair>();
		
		Reflections reflections = new Reflections( // set package with
													// controller classes
				"com.mediateka.controller");

		Set<Class<?>> annotated = reflections
				.getTypesAnnotatedWith(Controller.class); // get all classes
															// from package
															// com.mediateka.controller
															// which are
															// annotated with
															// Controller
															// annotation

		for (Class<?> c : annotated) {
			for (Method m : c.getDeclaredMethods()) {
				if (m.isAnnotationPresent(Request.class)) {
					UrlMethodPair urlMethodPair = new UrlMethodPair(m
							.getAnnotation(Request.class).url(), m
							.getAnnotation(Request.class).method());
					ClassMethodPair classMethodPair = new ClassMethodPair(c, m);
					controllerMap.put(urlMethodPair, classMethodPair);
				}
			}
		}
	}
}
