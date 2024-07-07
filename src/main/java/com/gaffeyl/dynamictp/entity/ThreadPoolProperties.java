package com.gaffeyl.dynamictp.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @CLass: ThreadPoolProperties
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/3
 * @Version: 1.0
 * @Description:
 */
@Data
public class ThreadPoolProperties {
	private Integer corePoolSize;
	private Integer maximumPoolSize;
	private Long keepAliveTime;
	private TimeUnit timeUnit;
	private String queueName;
	private Integer capacity;
	private Boolean fair;
	private String threadPoolName;
	private String rejectHandlerName;
	private Boolean allowCoreThreadTimeOut;
}
