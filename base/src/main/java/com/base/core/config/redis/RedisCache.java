package com.base.core.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.core.utils.JSONParser;
import com.base.core.utils.SerializeUtil;

import redis.clients.jedis.Jedis;

/**
 * @author liu wp
 * @data  2017年9月1日
 */
@Repository
public class RedisCache {
	private static final Logger logger = LoggerFactory.getLogger(RedisDataSource.class);
	@Autowired
	private RedisDataSource redisDataSource;

	/**
	 * 根据命名空间.关键字 删除redis中对应的键值对
	 *
	 * @param key
	 *            关键字
	 * @param nameSpace
	 *            命名空间
	 */
	public void delete(String key, String nameSpace) {
		Jedis jedis = null;
		try {
			jedis = redisDataSource.getRedisClient();
			jedis.del(getNamespaceKeyByte(nameSpace, key));
		} catch (Exception e) {
			if (null != jedis) {
				redisDataSource.returnResource(jedis, true);
				jedis = null;
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (null != jedis) {
				redisDataSource.returnResource(jedis);
			}
		}
	}

	/**
	 * 根据传入的class类类型，返回对应的class对象
	 *
	 * @param key
	 *            关键字
	 * @param type
	 *            class类型
	 * @param nameSpace
	 *            命名空间
	 * @return
	 */
	public <T> T get(String key, Class<T> type, String nameSpace) {
		Jedis jedis = null;
		try {
			jedis = redisDataSource.getRedisClient();
			Object returnObj = SerializeUtil.unserialize(jedis.get(getNamespaceKeyByte(nameSpace, key)));
			return JSONParser.toStringObject(JSONParser.toJSONString(returnObj), type);
		} catch (Exception e) {
			if (null != jedis) {
				redisDataSource.returnResource(jedis, true);
				jedis = null;
			}
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (null != jedis) {
				redisDataSource.returnResource(jedis);
			}
		}
	}

	/**
	 * 根据关键字以及命名空间返回对应的值
	 *
	 * @param key
	 *            关键字
	 * @param nameSpace
	 *            命名空间
	 * @return
	 */
	public Object get(String key, String nameSpace) {
		Jedis jedis = null;
		try {
			jedis = redisDataSource.getRedisClient();
			Object returnObj = SerializeUtil.unserialize(jedis.get(getNamespaceKeyByte(nameSpace, key)));
			return returnObj;
		} catch (Exception e) {
			if (null != jedis) {
				redisDataSource.returnResource(jedis, true);
				jedis = null;
			}
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (null != jedis) {
				redisDataSource.returnResource(jedis);
			}
		}
	}

	/**
	 * 根据命名空间.关键字传入对象到redis中，失效时间为expireTime秒
	 *
	 * @param key
	 *            关键字
	 * @param singleBean
	 *            对象
	 * @param expireTime
	 *            过期时间
	 * @param nameSpace
	 *            命名空间
	 */
	public void put(String key, Object singleBean, int expireTime, String nameSpace) {
		Jedis jedis = null;
		try {
			jedis = redisDataSource.getRedisClient();
			jedis.set(getNamespaceKeyByte(nameSpace, key), SerializeUtil.serialize(singleBean));
			if (expireTime > 0) {
				jedis.expire(key.getBytes(), expireTime);
			}
		} catch (Exception e) {
			if (null != jedis) {
				redisDataSource.returnResource(jedis, true);
				jedis = null;
			}
			logger.error(e.getMessage(), e);
		} finally {
			if (null != jedis) {
				redisDataSource.returnResource(jedis);
			}
		}
	}
	/**
	 * 根据命名空间.关键字传入对象到redis中，失效时间为永不过去
	 *
	 * @param key
	 *            关键字
	 * @param singleBean
	 *            对象
	 * @param nameSpace
	 *            命名空间
	 */
	public void put(String key, Object singleBean, String nameSpace) {
		put(key, singleBean, 0, nameSpace);
	}


	/**
	 * 拼接格式 namespace.key
	 *
	 * @param nameSpace
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private byte[] getNamespaceKeyByte(String nameSpace, String key) throws Exception {
		try {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(nameSpace);
			strBuilder.append(".");
			strBuilder.append(key);
			return strBuilder.toString().getBytes();
		} catch (Exception e) {
			throw new Exception("redis namespace and key erros!");
		}
	}
}
