package com.gaffeyl.dynamictp;

import com.gaffeyl.dynamictp.entity.DtpProperties;
import com.gaffeyl.dynamictp.threadpool.DtpExecutor;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DtpRegistryTest {

	@Resource
	DtpRegistry dtpRegistry;
	@Resource
	DtpProperties dtpProperties;
	@Test
	void registerExecutor() {
		System.out.println(dtpProperties.getThreadPoolName());
//		ApplicationRunner
//		Map<String, DtpExecutor> registryMap = DtpRegistry;
		DtpExecutor executor = DtpRegistry.getExecutor("testThreadPoolName");
		System.out.println("注册之前"+executor.getThreadPoolName());
//		dtpRegistry.registerExecutor();
		System.out.println("注册之后"+executor.getThreadPoolName());
	}
}