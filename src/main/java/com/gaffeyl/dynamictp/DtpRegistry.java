package com.gaffeyl.dynamictp;

import com.gaffeyl.dynamictp.entity.DtpProperties;
import com.gaffeyl.dynamictp.entity.ThreadPoolProperties;
import com.gaffeyl.dynamictp.refresh.RefreshEvent;
import com.gaffeyl.dynamictp.threadpool.DtpExecutor;
import com.gaffeyl.dynamictp.threadpool.ThreadPoolBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @CLass: DtpRegistry
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/3
 * @Version: 1.0
 * @Description:
 */
@Component
@Slf4j
public class DtpRegistry implements InitializingBean, ApplicationListener<RefreshEvent> {
	public DtpRegistry(){
	}
	private static final Map<String,DtpExecutor> registryMap = new ConcurrentHashMap<>();
	private static DtpProperties dtpProperties;

	@Override
	public void afterPropertiesSet() throws Exception {
		dtpProperties.getExecutors().forEach(x -> {
			DtpExecutor dtpExecutor = ThreadPoolBuilder.newBuilder()
					.corePoolSize(x.getCorePoolSize())
					.maximumPoolSize(x.getMaximumPoolSize())
					.keepAliveTime(x.getKeepAliveTime())
					.timeUnit(x.getTimeUnit())
					.wordQueue(x.getQueueName(), x.getCapacity(), x.getFair())
					.threadFactory(x.getThreadPoolName())
					.rejectedExecutionHandler(x.getRejectHandlerName())
					.threadPoolName(x.getThreadPoolName())
					.build();
			if(!registryMap.containsKey(x.getThreadPoolName())){
				registryMap.put(x.getThreadPoolName(), dtpExecutor);
			}else {
				log.error("名为:[{}]的线程池已存在",x.getThreadPoolName());
			}
		});

	}

	public static boolean containsExecutor(String name){
		return registryMap.containsKey(name);
	}

	@Override
	public void onApplicationEvent(RefreshEvent event) {
		DtpProperties properties = event.getDtpProperties();
		String updateThreadPoolName = event.getUpdateThreadPoolName();
		refresh(properties,updateThreadPoolName);
	}
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
	public static List<String> getAllDtp(){
		return new ArrayList<>(registryMap.keySet());
	}

	private void refresh(DtpProperties properties,String updateThreadPool){
		 List<ThreadPoolProperties> newExecutors = properties.getExecutors();
		 for(ThreadPoolProperties prop:newExecutors){
			 if(prop.getThreadPoolName().equals(updateThreadPool)){
				 DtpExecutor executor = doRefresh(prop.getThreadPoolName(), prop);
				 registryMap.put(prop.getThreadPoolName(),executor);
			 }
		 }
	}
	private DtpExecutor doRefresh(String threadPoolName,ThreadPoolProperties props){
		DtpExecutor executor = registryMap.get(threadPoolName);
		executor.setMaximumPoolSize(props.getMaximumPoolSize());
		executor.setCorePoolSize(props.getCorePoolSize());
		executor.setKeepAliveTime(props.getKeepAliveTime(), TimeUnit.SECONDS);
		return executor;
	}
}
