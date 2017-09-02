package com.base.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author liu wp
 * @data  2017年9月1日
 */
@Configuration
public class RestTemplateConfig {
	@Value("${restTemplate.connectTimeout}")
	private int connectTimeout;
	@Value("${restTemplate.readTimeout}")
	private int readTimeout;

	@Bean(name = "restTemplateHttp")
	public RestTemplate restTemplate(final ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		final SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(readTimeout);
		factory.setConnectTimeout(connectTimeout);
		return factory;
	}
}
