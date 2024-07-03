package com.gaffeyl.dynamictp.threadpool;

import java.util.concurrent.*;

/**
 * @CLass: DtpExecutor
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/3
 * @Version: 1.0
 * @Description:
 */
public class DtpExecutor extends ThreadPoolExecutor {

	private String threadPoolName;
	DtpExecutor(
			int corePoolSize,
			int maximumPoolSize,
			long keepAliveTime,
			TimeUnit timeUnit,
			BlockingQueue<Runnable> workQueue,
			ThreadFactory threadFactory,
			RejectedExecutionHandler handler
	){
		super(corePoolSize,maximumPoolSize,keepAliveTime,timeUnit,workQueue,threadFactory);
	}
	public void setThreadPoolName(String name){
		this.threadPoolName = name;
	}
	public String getThreadPoolName(){
		return this.threadPoolName;
	}
}
