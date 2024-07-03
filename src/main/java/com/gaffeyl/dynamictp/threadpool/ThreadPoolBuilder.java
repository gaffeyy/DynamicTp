package com.gaffeyl.dynamictp.threadpool;

import com.gaffeyl.dynamictp.entity.DtpProperties;
import io.micrometer.common.util.StringUtils;

import java.util.concurrent.*;

/**
 * @CLass: ThreadPoolBuilder
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/3
 * @Version: 1.0
 * @Description:
 */
public class ThreadPoolBuilder {
	private String threadPoolName = "DynamicTp";
	private int corePoolSize = 1;
	private int maximumPoolSize = 1;
	private long keepAliveTime = 30;
	private TimeUnit timeUnit =TimeUnit.SECONDS;
	private BlockingQueue<Runnable> workQueue;
	private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
	private ThreadFactory threadFactory = new NamedThreadFactory("dynamicTp");

	private ThreadPoolBuilder(){}

	public static ThreadPoolBuilder newBuilder(){return new ThreadPoolBuilder();}

	public ThreadPoolBuilder threadPoolName(String poolName) {
		this.threadPoolName = poolName;
		return this;
	}

	public ThreadPoolBuilder corePoolSize(int corePoolSize) {
		if (corePoolSize >= 0) {
			this.corePoolSize = corePoolSize;
		}
		return this;
	}

	public ThreadPoolBuilder maximumPoolSize(int maximumPoolSize) {
		if (maximumPoolSize > 0) {
			this.maximumPoolSize = maximumPoolSize;
		}
		return this;
	}

	public ThreadPoolBuilder keepAliveTime(long keepAliveTime) {
		if (keepAliveTime > 0) {
			this.keepAliveTime = keepAliveTime;
		}
		return this;
	}

	public ThreadPoolBuilder timeUnit(TimeUnit timeUnit) {
		if (timeUnit != null) {
			this.timeUnit = timeUnit;
		}
		return this;
	}

	public ThreadPoolBuilder wordQueue(String queueName,Integer capacity,boolean fair){
		if(StringUtils.isNotBlank(queueName)){
			workQueue = new LinkedBlockingQueue<>(capacity);
		}
		return this;
	}
	public ThreadPoolBuilder rejectedExecutionHandler(String rejectedName) {
		if (StringUtils.isNotBlank(rejectedName)) {
			rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
		}
		return this;
	}

	public ThreadPoolBuilder threadFactory(String prefix) {
		if (StringUtils.isNotBlank(prefix)) {
			threadFactory = new NamedThreadFactory(prefix);
		}
		return this;
	}
	private DtpExecutor buildDtpExecutor(ThreadPoolBuilder builder){
		DtpExecutor dtpExecutor = new DtpExecutor(
				builder.corePoolSize,
				builder.maximumPoolSize,
				builder.keepAliveTime,
				builder.timeUnit,
				builder.workQueue,
				builder.threadFactory,
				builder.rejectedExecutionHandler
		);
		dtpExecutor.setThreadPoolName(builder.threadPoolName);
		return dtpExecutor;
	}

	public DtpExecutor build(){
		return buildDtpExecutor(this);
	}


}
