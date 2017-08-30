package com.base.core.service.demo;

import java.util.List;

import com.base.core.model.CarInfo;

/**
 * @author liu wp
 * @data  2017年8月29日
 */
public interface IDemoService {

	/**
	 * @param record
	 * @return
	 */
	int insertCarInfo(CarInfo record);

	/**
	 * @return
	 */
	List<CarInfo> selectAllCarInfo();
}
