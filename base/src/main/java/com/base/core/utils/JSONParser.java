package com.base.core.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JSON工具
 * 
 * @author
 */
public class JSONParser {
	/** 日志对象 **/
	private static final Logger logger = LoggerFactory.getLogger(JSONParser.class);

	private static SerializeConfig mapping = new SerializeConfig();

	static {
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
	}

	/**
	 * 解析所有json跟节点返回信息
	 *
	 * @param JSONObject
	 *            json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static void decodeJSONObject(JSONObject json, Map<String, List<Object>> result) {
		if (result == null) {
			return;
		}
		Iterator<String> keys = json.keys();
		JSONObject jo = null;
		JSONArray ja = null;
		Object o;
		String key;
		while (keys.hasNext()) {
			key = keys.next();
			o = json.get(key);
			if (o instanceof JSONObject) {
				jo = (JSONObject) o;
				if (jo.keySet().size() > 0) {
					decodeJSONObject(jo, result);
				} else {
					decodeJsonResult(String.valueOf(key), "", result);
				}
			} else if (o instanceof JSONArray) {
				ja = (JSONArray) o;
				for (int i = 0; i < ja.size(); i++) {
					try {
						decodeJSONObject(ja.getJSONObject(i), result);
					} catch (Exception e) {
						// 如果ja.getJSONObject转换对象异常，说明无法利用json键值对解析，则直接暴力当成数组解析
						json.put(key, String.valueOf(ja.get(i)).trim());
						decodeJsonResult(String.valueOf(key), String.valueOf(ja.get(i)).trim(), result);
					}
				}
			} else {
				json.put(key, o.toString().trim());
				decodeJsonResult(String.valueOf(key), o, result);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		System.out.println("---------- Test 1 ----------");
		long beginTime = System.currentTimeMillis();
		String test = "{\"adddd\":[{\"couponTemplateNo\":\"60188CNKQJH\",\"distExport\":\"CXJ_APP\",\"grantNum\":\"1\",\"start\":\"1\",\"end\":\"60\"},{\"couponTemplateNo\":\"60188JQDL\",\"distExport\":\"CXJ_APP\",\"grantNum\":\"1\",\"start\":\"61\",\"end\":\"100\"}]}";
		Map a = new HashMap();
		decodeJSONObject(JSONObject.fromObject(test), a);
		System.out.println("Test 1 finish time : " + (System.currentTimeMillis() - beginTime) + " ms");
		System.out.println("---------- Test 2 ----------");
		beginTime = System.currentTimeMillis();
		a = new HashMap();
		test = "{\"name\":\"a\",\"num\":12345678901234567890;\"couponid\":1203080008,\"aa\":null}";
		decodeJSONObject(JSONObject.fromObject(test), a);
		System.out.println("Test 2 finish time : " + (System.currentTimeMillis() - beginTime) + " ms");
	}

	/**
	 * 根据解析JSON字符
	 *
	 * @param object
	 */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}

	/***
	 * 将对象解析为JSON
	 *
	 * @param object
	 * @param datePattern
	 * @return String
	 */
	public static String toJSONString(Object object, String datePattern) {
		return JSON.toJSONString(object, config(datePattern));
	}

	/***
	 * 将对象解析为JSON
	 *
	 * @param object
	 *            JSON解析对象
	 * @param datePattern
	 *            日期格式
	 * @param properties
	 *            字段
	 * @param isIncludeProperties
	 *            true 包含字段，False 则排除字段
	 * @return String 返回JSON字符串
	 */
	public static String toJSONString(Object object, String datePattern, Set<String> properties,
			boolean isIncludeProperties) {
		// 设置
		SerializerFeature[] features = { SerializerFeature.QuoteFieldNames,
				SerializerFeature.DisableCircularReferenceDetect };
		SerializeWriter out = new SerializeWriter(features);
		JSONSerializer serializer = new JSONSerializer(out, config(datePattern));
		serializer.getPropertyPreFilters().add(configPropertyFilter(properties, isIncludeProperties));
		serializer.write(object);
		return out.toString();
	}

	/**
	 * 将字符串转为Map
	 *
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(String jsonStr) {
		try {
			return JSON.parseObject((String) JSON.parse(jsonStr), Map.class);
		} catch (Exception e) {
			logger.error("Json String Error : " + jsonStr);
			return null;
		}

	}

	/***
	 *
	 * 功能描述:将jsonString字符串转换为对象List <br>
	 * 〈功能详细描述〉
	 *
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static <T> List<T> toStringList(String jsonStr, Class<T> clazz) {
		return JSON.parseArray(jsonStr, clazz);
	}

	/***
	 *
	 * 功能描述:将jsonString字符串转换为对象 <br>
	 * 〈功能详细描述〉
	 *
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static <T> T toStringObject(String jsonStr, Class<T> clazz) {
		try {
			return JSON.parseObject(jsonStr, clazz);
		} catch (Exception e) {
			logger.info("Json String Error : " + jsonStr);
			return null;
		}
	}

	/***
	 *
	 * 功能描述:将jsonString字符串转换为对象 <br>
	 * 〈功能详细描述〉
	 *
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static <T> T toStringObject(String jsonStr, TypeReference<T> type) {
		try {
			return JSON.parseObject(jsonStr, type);
		} catch (Exception e) {
			logger.info("Json String Error : " + jsonStr);
			return null;
		}
	}

	/**
	 * 根据
	 *
	 * @param datePattern
	 *            日期格式
	 */
	private static SerializeConfig config(String datePattern) {
		SerializeConfig mapping = new SerializeConfig();
		mapping.put(Date.class, new SimpleDateFormatSerializer(datePattern));
		mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(datePattern));
		mapping.put(Timestamp.class, new SimpleDateFormatSerializer(datePattern));
		return mapping;
	}

	/***
	 *
	 * @param properties
	 * @param includeProperties
	 * @return PropertyFilter
	 */
	private static PropertyPreFilter configPropertyFilter(Set<String> properties, boolean includeProperties) {

		SimplePropertyPreFilter simplePropertyPreFilter = new SimplePropertyPreFilter();

		if (includeProperties) {
			simplePropertyPreFilter.getIncludes().addAll(properties);
		} else {
			simplePropertyPreFilter.getExcludes().addAll(properties);
		}
		return simplePropertyPreFilter;
	}

	/**
	 * 解析json判断节点存储对象
	 *
	 * @param Objecy
	 *            key
	 * @param Objecy
	 *            value
	 * @param Map<String,
	 *            List<Object>>
	 */
	private static void decodeJsonResult(String key, Object value, Map<String, List<Object>> result) {
		if (key == null) {
			logger.warn("decodeJsonResult key is null");
			return;
		}
		if (result.get(key) == null) {
			List<Object> o = new ArrayList<Object>();
			o.add(value);
			result.put(key, o);
		} else {
			result.get(key).add(value);
		}
	}
}
