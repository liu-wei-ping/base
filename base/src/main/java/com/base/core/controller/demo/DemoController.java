package com.base.core.controller.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.controller.BaseController;
import com.base.core.model.CarInfo;
import com.base.core.service.demo.IDemoService;

import io.swagger.annotations.ApiOperation;

/**
 * @author liu wp
 * @data 2017年8月29日
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

	@Autowired
	private IDemoService service;

	@PostMapping("/addCarInfo")
	@ApiOperation(value = "Demo新增汽车信息", notes = "Demo新增汽车信息")
	public Map<String, Object> addCarInfo(@RequestBody final CarInfo record) {
		final Map<String, Object> map = new HashMap<>();
		final int count = service.insertCarInfo(record);
		map.put("count", count);
		map.put("result", count > 0);
		return map;
	}

	@PostMapping("/getCarInfoList")
	@ApiOperation(value = "Demo查询汽车信息", notes = "Demo查询汽车信息")
	public Map<String, Object> getCarInfoList() {
		final Map<String, Object> map = new HashMap<>();
		final List<CarInfo> list = service.selectAllCarInfo();
		map.put("list", list);
		map.put("result", !CollectionUtils.isEmpty(list));
		return map;
	}

}
