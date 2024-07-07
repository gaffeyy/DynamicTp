package com.gaffeyl.dynamictp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @CLass: WebConfig
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/6
 * @Version: 1.0
 * @Description:
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		WebMvcConfigurer.super.addCorsMappings(registry);
		/**
		 * 所有请求都允许跨域，使用这种配置就不需要
		 * 在interceptor中配置header了
		 */
		registry.addMapping("/**")
				.allowCredentials(true)
				.allowedOrigins("http://1.14.1.82")
				.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				.allowedHeaders("*")
				.maxAge(3600);
	}
}
