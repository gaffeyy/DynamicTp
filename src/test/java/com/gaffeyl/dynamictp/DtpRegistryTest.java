package com.gaffeyl.dynamictp;

import com.gaffeyl.dynamictp.entity.ThreadPoolProperties;
import com.gaffeyl.dynamictp.threadpool.DtpExecutor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class DtpRegistryTest {

	@Resource
	DtpRegistry dtpRegistry;
	@Resource
	ThreadPoolProperties threadPoolProperties;
	@Test
	void registerExecutor() {
		System.out.println(threadPoolProperties.getThreadPoolName());
//		ApplicationRunner
//		Map<String, DtpExecutor> registryMap = DtpRegistry;
		DtpExecutor executor = DtpRegistry.getExecutor("testThreadPoolName");
//		System.out.println("注册之前"+"队列名称:" + executor.getQueueName()+);
//		dtpRegistry.registerExecutor();
//		System.out.println("注册之后"+executor.toString());
		log.info("==之前== queueName:[{}],queueCapacity:[{}],threadPoolName:[{}],corePoolSize:[{}],maximumPoolSize:[{}]" +
				",keepAliveTime:[{}]",executor.getQueueName(),executor.getQueueCapacity(),executor.getThreadPoolName()
				,executor.getCorePoolSize(),executor.getMaximumPoolSize(),executor.getKeepAliveTime(TimeUnit.SECONDS));
	}
}