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

	public String getQueueName(){
		return this.getQueue().getClass().getSimpleName();
	}
	public int getQueueCapacity(){
		return this.getQueue().size() + getQueue().remainingCapacity();
	}
	public String getInformation(){
		return new StringBuilder()
				.append("ThreadPoolName:"+threadPoolName+"\n")
				.append("CorePoolSize:"+getCorePoolSize()+"\n")
				.append("MaximumSize:"+getMaximumPoolSize()+"\n")
				.append("KeepAliveTime:"+getKeepAliveTime(TimeUnit.SECONDS)+"\n")
				.append("QueueName:"+getQueueName()+"--QueueType:"+getQueue().getClass().getSimpleName()+"--QueueCapacity:"+getQueueCapacity()+"\n")
				.append("ThreadFactory:"+getThreadFactory()+"\n")
				.append("RejectPolicy:"+getRejectedExecutionHandler()+"\n")
//				.append("ThreadPoolName:"+threadPoolName+"\n")
				.toString();

	}
}
