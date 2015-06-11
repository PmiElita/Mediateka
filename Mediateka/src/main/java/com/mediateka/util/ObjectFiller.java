package com.mediateka.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class ObjectFiller {
	public static <T> void fill(T object, HttpServletRequest request)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> classT = object.getClass();
		for (Field field : classT.getDeclaredFields()) {

			Method method = getSetMethodByField(field, classT);
			method.invoke(object, request.getParameter(field.getName()));
		}

	}

	private static Method getSetMethodByField(Field f, Class<?> c)
			throws NoSuchMethodException, SecurityException {

		String upperCaseFirstLetter = new Character(f.getName().charAt(0))
				.toString().toUpperCase();

		return c.getMethod("set" + upperCaseFirstLetter
				+ f.getName().substring(1), f.getType());
	}
}
