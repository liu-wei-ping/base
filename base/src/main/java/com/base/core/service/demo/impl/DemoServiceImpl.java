package com.base.core.service.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.core.mapper.CarInfoMapper;
import com.base.core.model.CarInfo;
import com.base.core.service.demo.IDemoService;

/**
 * @author liu wp
 * @data  2017年8月29日
 */
@Service
public class DemoServiceImpl implements IDemoService {

	 @Autowired
	 private CarInfoMapper mapper;

	@Override
	public int insertCarInfo(final CarInfo record) {
		final int count = mapper.insertSelective(record);
		return count;
	}

	@Override
	public List<CarInfo> selectAllCarInfo() {
		final List<CarInfo> list = mapper.selectAll();
		return list;
	}

}
