package com.base.core.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author liu wp
 * @data  2017年9月1日
 */
@Component
@SuppressWarnings("deprecation")
public class RedisDataSource {
	private static final Logger logger = LoggerFactory.getLogger(RedisDataSource.class);
	private int index = 0;
	/*** */
	// @Autowired
	// private RedisConfig redisConfig;

	/*** */
	@Autowired
	private JedisPool redisPool;

	/**
	 * 取得Jedis的客户端
	 *
	 * @return
	 */
	public Jedis getRedisClient() {
        try {
			Jedis jedis = redisPool.getResource();
			jedis.select(index);
			return jedis;
        } catch (Exception e) {
			logger.error("Jedis error", e);
        }
        return null;
    }

	public JedisPool getRedisPool() {
		return redisPool;
	}


	// @PostConstruct
	// public void initRedis() {
	// JedisPoolConfig poolConfig = new JedisPoolConfig();
	// poolConfig.setMaxTotal(redisConfig.getMaxActive());
	// poolConfig.setMaxIdle(redisConfig.getMaxIdle());
	// // poolConfig.setMinIdle(redisConfig.get);
	// poolConfig.setMaxWaitMillis(redisConfig.getMaxWait());
	// // poolConfig.setTestOnBorrow(redisConfig.getTestOnBorrow());
	// // poolConfig.setTestOnReturn(redisConfig.getTestOnReturn());
	// poolConfig.setTestWhileIdle(redisConfig.isTestOnBorrow());
	//
	// redisPool = new JedisPool(poolConfig, redisConfig.getAddr(),
	// redisConfig.getPort(), redisConfig.getTimeOut(),
	// redisConfig.getAuth());
	// }

	/**
	 * 将资源返还给pool
	 *
	 * @param jedis
	 */
	public void returnResource(Jedis jedis) {
		redisPool.returnResource(jedis);
	}

	/**
	 * 出现异常后，将资源返还给pool
	 *
	 * @param jedis
	 * @param broken
	 */
	public void returnResource(Jedis jedis, boolean broken) {
		if (broken) {
			redisPool.returnBrokenResource(jedis);
		} else {
			redisPool.returnResource(jedis);
		}
	}


	public void setRedisPool(JedisPool redisPool) {
		this.redisPool = redisPool;
	}
}
