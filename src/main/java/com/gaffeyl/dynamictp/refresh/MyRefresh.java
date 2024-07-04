package com.gaffeyl.dynamictp.refresh;

import com.gaffeyl.dynamictp.DtpRegistry;
import com.gaffeyl.dynamictp.entity.DtpProperties;
import com.gaffeyl.dynamictp.entity.DtpPropsRequestBody;
import com.gaffeyl.dynamictp.entity.ThreadPoolProperties;
import com.gaffeyl.dynamictp.threadpool.DtpExecutor;
import com.gaffeyl.dynamictp.threadpool.ThreadPoolBuilder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @CLass: MyRefresh
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/4
 * @Version: 1.0
 * @Description:
 */
@Component
@Slf4j
public class MyRefresh implements Refresh, InitializingBean {
	@Resource
	DtpProperties dtpProperties;
	@Resource
	ApplicationEventMulticaster applicationEventMulticaster;
	public void refresh(DtpPropsRequestBody dtpPropsRequest, String threadPoolToUpdate){
		if(dtpPropsRequest!=null){
//			log.info("更新前配置:[{}]",dtpProperties);
			doRefresh(dtpPropsRequest,threadPoolToUpdate);
		}
	}

	private void doRefresh(DtpPropsRequestBody dtpPropsRequest, String updateThreadPool){
		DtpPropsRequestBody drpProp = dtpPropsRequest;
		Integer corePoolSize = drpProp.getCorePoolSize();
		Integer maximumPoolSize = drpProp.getMaximumPoolSize();
		Long keepAliveTime = drpProp.getKeepAliveTime();
		TimeUnit timeUnit = drpProp.getTimeUnit();
		String queueName = drpProp.getQueueName();
		Integer capacity = drpProp.getCapacity();
		Boolean fair = drpProp.getFair();
		String threadPoolName = drpProp.getThreadPoolName();
		String rejectHandlerName = drpProp.getRejectHandlerName();
		if(!DtpRegistry.containsExecutor(threadPoolName)){
			// 不存在,执行注册
			DtpExecutor dtpExecutor = ThreadPoolBuilder.newBuilder()
					.corePoolSize(corePoolSize)
					.maximumPoolSize(maximumPoolSize)
					.keepAliveTime(keepAliveTime)
					.timeUnit(TimeUnit.SECONDS)
					.wordQueue(queueName, capacity, true)
					.threadFactory(threadPoolName)
					.rejectedExecutionHandler(rejectHandlerName)
					.threadPoolName(threadPoolName)
					.build();
			DtpRegistry.registerExecutor(dtpExecutor,null);
			publishCreateEvent();
			return;
		}
		List<ThreadPoolProperties> executors = dtpProperties.getExecutors();
		for(ThreadPoolProperties prop:executors){
			if(prop.getThreadPoolName().equals(threadPoolName)){
				prop.setCorePoolSize(corePoolSize);
				prop.setMaximumPoolSize(maximumPoolSize);
				prop.setKeepAliveTime(keepAliveTime);
				prop.setQueueName(queueName);
				prop.setCapacity(capacity);
				publishUpdateEvent(updateThreadPool);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}
	private void publishUpdateEvent(String updateThreadPool){
		RefreshEvent event = new RefreshEvent(this,dtpProperties,updateThreadPool);
		applicationEventMulticaster.multicastEvent(event);
	}
	private void publishCreateEvent(){
		RefreshEvent event = new RefreshEvent(this,dtpProperties,"Build");
		applicationEventMulticaster.multicastEvent(event);
	}
}
