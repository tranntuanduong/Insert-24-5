package com.laptrinhjavaweb.repositoty.impl;

import java.lang.reflect.Field;

public class RunGetter {
	public static <T> Object runGetter(Object object) {
		int countFieldParent = 0;
		try {
			//current class
			Field[] fields = object.getClass().getDeclaredFields();
			Class parentClass1 = object.getClass().getSuperclass();
			while(parentClass1 != null) {
				Field[] fieldParents = parentClass1.getDeclaredFields();
				countFieldParent += fieldParents.length; 
				parentClass1 = parentClass1.getSuperclass();
			}
			Object[] parameters = new Object[fields.length + countFieldParent - 1];

			for(int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				Object value = fields[i].get(object);
				if(value!=null) {
					parameters[i] = value;
				}
			}
			//parent class
			Class parentClass = object.getClass().getSuperclass();
			while(parentClass != null) {
				Field[] fieldParents = parentClass.getDeclaredFields();
				for(int i = 0; i < fieldParents.length; i++) {					
					fieldParents[i].setAccessible(true);
					int index = fields.length + i;
					Object value = fieldParents[i].get(object);
					if(value!=null) {
						parameters[index] = value;
					}
				}
				parentClass = parentClass.getSuperclass();
			}
			return parameters;
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
