package com.mediateka.util;

import java.lang.reflect.Field;
import java.text.ParseException;

import com.mediateka.annotation.DateField;
import com.mediateka.annotation.EnumField;
import com.mediateka.annotation.Validation;
import com.mediateka.exception.WrongInputException;

public class FormValidator {

	@SuppressWarnings("unchecked")
	public static <T> void validate(T object) throws IllegalArgumentException,
			IllegalAccessException, WrongInputException {
		Class<?> classT = object.getClass();

		for (Field field : classT.getDeclaredFields()) {
			if (field.isAnnotationPresent(Validation.class)) {
				field.setAccessible(true);
				if (field.get(object) == null) {
					throw new WrongInputException("Wrong value of "
							+ field.getName());
				}
				String fieldValue = (String) field.get(object);

				if (fieldValue.trim().length()<field.getAnnotation(Validation.class)
						.minLength()||fieldValue.length() > field.getAnnotation(Validation.class)
						.maxLength()
						|| !fieldValue.matches(field.getAnnotation(
								Validation.class).regexp())) {
					throw new WrongInputException("Wrong value of "
							+ field.getName());
				}

			}
			if (field.isAnnotationPresent(EnumField.class)){
				field.setAccessible(true);
				if (field.get(object) == null) {
					throw new WrongInputException("Wrong value of "
							+ field.getName());
				}
				String fieldValue = ((String) field.get(object)).toUpperCase();
			 try{
				Enum.valueOf((Class<Enum>) field.getAnnotation(EnumField.class).enumClass(), fieldValue);
			 } catch (IllegalArgumentException e){
				 throw new WrongInputException("Wrong type of event");
			 }
			}
			
			if (field.isAnnotationPresent(DateField.class)){
				field.setAccessible(true);
				if (field.get(object) == null) {
					throw new WrongInputException("Wrong value of "
							+ field.getName());
				}
				String fieldValue = (String) field.get(object);
				 try {
				DateConverter.convertIntoTimestamp(fieldValue, field.getAnnotation(DateField.class).format());
				 } catch (ParseException e){
					 throw new WrongInputException("Wrong date format");
				 }
			}
		}

	}
}
