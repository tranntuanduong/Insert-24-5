package com.laptrinhjavaweb.repositoty.impl;

import java.lang.reflect.Field;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.entity.BuildingEntity;

public class Query {
	//insert
	public static <T> String queryInsert(Class<T> zClass) {
		String tableName = zClass.getAnnotation(Table.class).name();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("insert into "+tableName+"(");

		Field[] fields = zClass.getDeclaredFields();
		//current class
		for(Field field : fields) {
			if(field.isAnnotationPresent(Column.class)) {
				String columnName = field.getAnnotation(Column.class).name();
				stringBuilder.append(columnName+",");
			}
		}
		//parent class
		Class parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			Field[] fieldParents = parentClass.getDeclaredFields();	
			for(Field field : fieldParents) {
				if(field.isAnnotationPresent(Column.class)) {
					String columnName = field.getAnnotation(Column.class).name();
					if(!columnName.equals("id")) {
						stringBuilder.append(columnName+",");
					}
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
		stringBuilder.append(") values(");
		//current class
		for(Field field : fields) {
			if(field.isAnnotationPresent(Column.class)) {
				stringBuilder.append("?,");
			}
		}
		//parrent class
		Class parentClass1 = zClass.getSuperclass();
		while(parentClass1 != null) {
			Field[] fieldParents = parentClass1.getDeclaredFields();	
			for(Field field : fieldParents) {
				if(field.isAnnotationPresent(Column.class)) {
					String columnName = field.getAnnotation(Column.class).name();
					if(!columnName.equals("id")) {
						stringBuilder.append("?,");
					}
				}
			}
			parentClass1 = parentClass1.getSuperclass();
		}
		stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ")");
		return stringBuilder.toString();
	}
	//select ??? 
	public static <T> String querySelect(Class<T> zClass) {
		String tableName = zClass.getAnnotation(Table.class).name();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from "+tableName);

		Field[] fields = zClass.getDeclaredFields();
		for(Field field : fields) {
			if(field.isAnnotationPresent(Column.class)) {
				String columnName = field.getAnnotation(Column.class).name();
				stringBuilder.append(columnName+",");
			}
		}
		stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
		stringBuilder.append(") values(");
		for(Field field : fields) {
			if(field.isAnnotationPresent(Column.class)) {
				stringBuilder.append("?,");
			}
		}
		stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ")");
		
		return stringBuilder.toString();
	}
}
