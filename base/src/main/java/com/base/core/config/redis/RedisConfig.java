package com.base.core.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author liu wp
 * @data  2017年9月1日
 */
@Configuration
public class RedisConfig {
	/*** Redis服务器IP */
	@Value("${jedis.addr}")
	private String addr;

	/*** 访问密码 */
	@Value("${jedis.auth}")
	private String auth;

	/*** 非切片额客户端连接 */
	private Jedis jedis;

	/*** 非切片连接池 */
	private JedisPool jedisPool;

	/***
	 * 可用连接实例的最大数目，默认值为8；<br>
	 * 如果赋值为-1，则表示不限制；<br>
	 * 如果pool已经分配了maxActive个jedis实例， 则此时pool的状态为exhausted(耗尽)
	 */
	@Value("${jedis.pool.maxActive}")
	private int maxActive;

	/*** 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是 */
	@Value("${jedis.pool.maxIdle}")
	private int maxIdle;

	/***
	 * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。<br>
	 * 如果超过等待时间，则直接抛出JedisConnectionException
	 */
	@Value("${jedis.pool.maxWait}")
	private int maxWait;

	/*** Redis的端口号 */
	@Value("${jedis.port}")
	private int port;

	/*** 切片额客户端连接 */
	private ShardedJedis shardedJedis;

	/*** 切片连接池 */
	private ShardedJedisPool shardedJedisPool;

	/***
	 * 在borrow一个jedis实例时，是否提前进行validate操作<br>
	 * 如果为true，则得到的jedis实例均是可用的
	 */
	@Value("${jedis.pool.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${jedis.pool.timeOut}")
	private int timeOut;

	public String getAddr() {
		return addr;
	}

	public String getAuth() {
		return auth;
	}
	public Jedis getJedis() {
		return jedis;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public int getPort() {
		return port;
	}

	public ShardedJedis getShardedJedis() {
		return shardedJedis;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public int getTimeOut() {
		return timeOut;
	}

	@Bean
	public JedisPool init(JedisPoolConfig jedisPoolConfig) {

		return new JedisPool(jedisPoolConfig, addr,port, timeOut,
				auth);
	}

	@Bean
	public JedisPoolConfig initJedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxActive);
		poolConfig.setMaxIdle(maxIdle);
		// poolConfig.setMinIdle(redisConfig.get);
		poolConfig.setMaxWaitMillis(maxWait);
		// poolConfig.setTestOnBorrow(redisConfig.getTestOnBorrow());
		// poolConfig.setTestOnReturn(redisConfig.getTestOnReturn());
		poolConfig.setTestWhileIdle(testOnBorrow);
		return poolConfig;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setShardedJedis(ShardedJedis shardedJedis) {
		this.shardedJedis = shardedJedis;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

}
