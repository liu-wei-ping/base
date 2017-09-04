package com.base.core.controller.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.controller.BaseController;
import com.base.core.model.CarInfo;
import com.base.core.service.demo.IDemoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author liu wp
 * @data 2017年8月29日
 */
@RestController
public class DemoController extends BaseController {

	@Autowired
	private IDemoService service;

	@PostMapping("/{version}/demo/addCarInfo")
	@ApiOperation(value = "Demo新增汽车信息", notes = "Demo新增汽车信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "version", value = "版本信息", required = true, dataType = "String", paramType = "path"),
			@ApiImplicitParam(name = "record", value = "汽车信息", required = true, dataType = "CarInfo") })
	public Map<String, Object> addCarInfo(@PathVariable final String version, @RequestBody final CarInfo record) {
		final Map<String, Object> map = new HashMap<>();
		final int count = service.insertCarInfo(record);
		map.put("count", count);
		map.put("result", count > 0);
		logger.info("新增汽车数量【{}】", count);
		return map;
	}

	@PostMapping("/getCarInfoList")
	@ApiOperation(value = "Demo查询汽车信息", notes = "Demo查询汽车信息")
	public Map<String, Object> getCarInfoList() {
		final Map<String, Object> map = service.selectAllCarInfo();
		return map;
	}

}
