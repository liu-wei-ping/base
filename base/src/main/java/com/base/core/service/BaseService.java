package com.base.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.base.core.config.redis.RedisCache;

/**
 * @author liu wp
 * @data  2017年9月1日
 */
public class BaseService {
	@Autowired
	protected RedisCache redisCache;
	@Autowired
	protected RestTemplate restTemplate;
	/*** logger 日志 */
	Logger logger = LoggerFactory.getLogger(super.getClass());
}
