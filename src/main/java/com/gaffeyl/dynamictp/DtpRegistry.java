package com.gaffeyl.dynamictp;

import com.gaffeyl.dynamictp.entity.DtpProperties;
import com.gaffeyl.dynamictp.threadpool.DtpExecutor;
import com.gaffeyl.dynamictp.threadpool.ThreadPoolBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @CLass: DtpRegistry
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/3
 * @Version: 1.0
 * @Description:
 */
@Component
public class DtpRegistry implements InitializingBean {
	public DtpRegistry(){
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		DtpExecutor dtpExecutor = ThreadPoolBuilder.newBuilder()
				.corePoolSize(dtpProperties.getCorePoolSize())
				.maximumPoolSize(dtpProperties.getMaximumPoolSize())
				.keepAliveTime(dtpProperties.getKeepAliveTime())
				.timeUnit(dtpProperties.getTimeUnit())
				.wordQueue(dtpProperties.getQueueName(), dtpProperties.getCapacity(), dtpProperties.getFair())				.threadFactory(dtpProperties.getThreadPoolName())
				.rejectedExecutionHandler(dtpProperties.getRejectHandlerName())
				.build();
		registryMap.put(dtpProperties.getThreadPoolName(), dtpExecutor);
	}

	private static final Map<String,DtpExecutor> registryMap = new ConcurrentHashMap<>();
	private static DtpProperties dtpProperties;
	@Autowired
	public void setDtpProperties(DtpProperties dtpProperties){
		DtpRegistry.dtpProperties = dtpProperties;
	}
	public static void registerExecutor(DtpExecutor executor,String source){
		registryMap.put(executor.getThreadPoolName(),executor);
	}
	public static DtpExecutor getExecutor(String threadPoolName){
		 return registryMap.get(threadPoolName);
	}
}
