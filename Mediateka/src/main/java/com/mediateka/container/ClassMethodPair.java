package com.mediateka.container;

import java.lang.reflect.Method;

public class ClassMethodPair {
	private Class<?> clazz;
	private Method method;

	public ClassMethodPair(Class<?> clazz, Method method) {
		this.clazz = clazz;
		this.method = method;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Method getMethod() {
		return method;
	}

}
