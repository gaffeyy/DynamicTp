package com.gaffeyl.dynamictp.entity;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @CLass: DtpPropsResponseBody
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/4
 * @Version: 1.0
 * @Description:
 */
@Data
public class DtpPropsResponseBody {
	private String threadPoolName;
	private Integer corePoolSize;
	private Integer maximumPoolSize;
	private Long keepAliveTime;
	private TimeUnit timeUnit;
	private String queueName;
	private Integer capacity;
	private Boolean fair;
	private String rejectHandlerName;
}
