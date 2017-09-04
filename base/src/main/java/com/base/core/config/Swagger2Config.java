package com.base.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring Boot Auto API Document
 *
 * 使用Swagger2插件
 *
 * 用途，自动生成所有Api文档及测试类
 *
 * @author liu wp
 * @data 2017年8月29日
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket swagger2Docket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.base.core.controller")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful APIs")
				.description("更多Spring Boot相关文章请关注：http://www.cnblogs.com/liu-king/")
				.termsOfServiceUrl("https://github.com/liu-wei-ping/base")
				.contact(new Contact("Spring Boot Auto API Document Creater", "", "")).version("1.0").build();
	}
}
