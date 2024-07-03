package com.gaffeyl.dynamictp.spring;

import com.gaffeyl.dynamictp.DtpRegistry;
import com.gaffeyl.dynamictp.entity.DtpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @CLass: BaseBeanAutoConfiguration
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/3
 * @Version: 1.0
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(DtpProperties.class)
public class BaseBeanAutoConfiguration {

	@Bean
	public DtpPostProcessor dtpPostProcessor(){
		return new DtpPostProcessor();
	}
	@Bean
	public DtpRegistry dtpRegistry(){
		return new DtpRegistry();
	}
}
