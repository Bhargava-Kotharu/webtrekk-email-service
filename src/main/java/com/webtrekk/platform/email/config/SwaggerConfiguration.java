package com.webtrekk.platform.email.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.webtrekk.platform.email.WebtrekkEmailApplication;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class for Swagger resources
 * 
 * @author bkotharu
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(WebtrekkEmailApplication.class.getPackage().getName()))
				.paths(PathSelectors.any()).build().apiInfo(generateApiInfo());
	}

	private ApiInfo generateApiInfo() {
		return new ApiInfoBuilder().title("Webtrekk Email Service").description("Coding Challenge").version("1.0.0")
				.build();
	}

}
