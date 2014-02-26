package com.desandroid.adaframework.howto.validators;

import java.lang.reflect.Field;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.validators.Validator;

public class EmailValidator extends Validator {
	
	@Override
	public Boolean Validate(Entity pEntity, Field pField, Object pAnnotation,  Object pValue) {
		Boolean returnedValue = true;
		
		if (pValue != null) {
			if (pValue instanceof String) {
				String value = (String)pValue;
				
				if (!value.trim().equals("")) {
					if (!value.contains("@")) {
						returnedValue = false;
					}
				}
			}
		}
		
		return returnedValue;
	}
}
