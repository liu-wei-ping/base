package com.base.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 有效时间设置
 *
 * @author
 *
 */
public enum TimeLifecycle implements CodeDesc {

	/** 1天 */
	DAY(24 * 60 * 60, "1天"),
	/** 5分钟 */
	DEFAULT(5 * 60, "5分钟"),
	/** 30分钟 */
	HALFHOUR(30 * 60, "30分钟"),
	/** 30秒 */
	HALFMINUTE(30, "30秒"),
	/** 15天 */
	HALFMONTH(15 * 24 * 60 * 60, "15天"),
	/** 1小时 */
	HOUR(60 * 60, "1小时"),
	/** 30天 */
	MONTH(30 * 24 * 60 * 60, "30天"),
	/** 永久 */
	PERSISTENCE(-1, "永久"),
	/** 15分钟 */
	QUARTER(15 * 60, "15分钟"),
	/** 2小时 */
	THOUR(2 * 60 * 60, "2小时"),
	/** 3秒 */
	THREESECOUND(3, "3秒"),
	/** 2天 */
	TWO_DAY(48 * 60 * 60, "2天");

	private static Map<String, TimeLifecycle> stringToEnum = new HashMap<String, TimeLifecycle>();

	static {
		// Initialize map from constant name to enum constant
		for (TimeLifecycle value : values()) {
			stringToEnum.put(String.valueOf(value.code()), value);
		}
	}

	public static TimeLifecycle fromCode(int code) {
		return stringToEnum.get(String.valueOf(code));
	}

	/** 枚举代码. */
	private Integer code;

	/** 枚举显示. */
	private String desc;

	/**
	 * 构造函数.
	 *
	 * @param code
	 *            枚举代码
	 * @param display
	 *            枚举显示
	 */
	private TimeLifecycle(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	@Override
	public Integer code() {
		return this.code;
	}

	@Override
	public String desc() {
		return this.desc;
	}
}
