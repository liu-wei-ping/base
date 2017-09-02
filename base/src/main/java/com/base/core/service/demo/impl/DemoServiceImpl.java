package com.base.core.service.demo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.core.constant.SysConstant;
import com.base.core.mapper.CarInfoMapper;
import com.base.core.model.CarInfo;
import com.base.core.service.BaseService;
import com.base.core.service.demo.IDemoService;

/**
 * @author liu wp
 * @data  2017年8月29日
 */
@Service
public class DemoServiceImpl extends BaseService implements IDemoService {

	 @Autowired
	 private CarInfoMapper mapper;

	@Override
	public int insertCarInfo(final CarInfo record) {
		final int count = mapper.insertSelective(record);
		if (count > 0) {
			redisCache.put(record.getCarCode(), record, SysConstant.CACHE_NAMESPACH);
		}
		return count;
	}

	@Override
	public Map<String, Object> selectAllCarInfo() {
		Map<String, Object> map = new HashMap<>();
		final List<CarInfo> list = mapper.selectAll();
		map.put("list", list);
		List<Object> cacheList = new ArrayList<>();
		for (CarInfo carInfo : list) {
			Object obj = redisCache.get(carInfo.getCarCode(), SysConstant.CACHE_NAMESPACH);
			cacheList.add(obj);
		}
		map.put("cache", cacheList);
		return map;
	}

}
