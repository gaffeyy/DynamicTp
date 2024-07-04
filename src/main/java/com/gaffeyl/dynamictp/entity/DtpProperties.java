package com.gaffeyl.dynamictp.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @CLass: DtpProperties
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/3
 * @Version: 1.0
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "spring.dynamictp")
@Data
public class DtpProperties {
	List<ThreadPoolProperties> executors;
}
