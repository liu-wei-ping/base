package com.base.core.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.base.core.model.RestReturnObject;


/**
 * @author liu wp
 * @data 2017年8月28日
 */
public class ParamsCheckUtil {

	private final static String[] IGNORE_FIELD = { "remark" };


	/**
	 * @param cList
	 * @return
	 */
	public static <T> RestReturnObject checkParams(final List<T> cList) {
		if (CollectionUtils.isEmpty(cList)) {
			return RestReturnObject.generateFailedObject("参数为空！", null);
		}
		return checkParams(cList, getFieldArr(cList.get(0)));
	}

	/**
	 * @param cList
	 * @param checkField
	 * @return
	 */
	public static <T> RestReturnObject checkParams(final List<T> cList, final String... checkField) {
		try {
			if (CollectionUtils.isEmpty(cList)) {
				return RestReturnObject.generateFailedObject("参数为空！", null);
			}
			for (final T ct : cList) {
				for (Class<?> clazz = ct.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
					final Field[] fields = clazz.getDeclaredFields();
					for (final Field field : fields) {
						field.setAccessible(true);
						final Object object = field.get(ct);
						final String fieldName = field.getName();
						if (Arrays.asList(checkField).contains(fieldName)) {
							if (null == object || "".equals(object)) {
								return RestReturnObject.generateFailedObject("参数" + fieldName + "为空！", null);
							}
						} else {
							continue;
						}
					}
				}
			}
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
			return RestReturnObject.generateFailedObject("参数验证错误！", null);
		}
		return null;
	}


	/**
	 * @param obj
	 * @return
	 */
	public static <T> String[] getFieldArr(final T obj) {
		final Field[] fields = obj.getClass().getDeclaredFields();
		final Set<String> fieldSet = new HashSet<>();
		for (final Field field : fields) {
			field.setAccessible(true);
			final String fieldName = field.getName();
			if (!"serialVersionUID".equalsIgnoreCase(fieldName) && !Arrays.asList(IGNORE_FIELD).contains(fieldName)) {
				fieldSet.add(fieldName);
			}
		}
		final String[] fieldArr = fieldSet.toArray(new String[fieldSet.size()]);
		return fieldArr;
	}
}
