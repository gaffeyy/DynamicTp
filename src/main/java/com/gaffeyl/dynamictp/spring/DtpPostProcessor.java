package com.gaffeyl.dynamictp.spring;

import com.gaffeyl.dynamictp.DtpRegistry;
import com.gaffeyl.dynamictp.threadpool.DtpExecutor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @CLass: DtpPostProcessor
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/3
 * @Version: 1.0
 * @Description:
 */
public class DtpPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof DtpExecutor){
			DtpExecutor executor = (DtpExecutor) bean;
			DtpRegistry.registerExecutor(executor,"beanPostProcessor");
			return executor;
		}
		return bean;
	}
}
