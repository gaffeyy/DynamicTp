package com.gaffeyl.dynamictp.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @CLass: NamedThreadFactory
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/3
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class NamedThreadFactory implements ThreadFactory {
	private final boolean daemon;

	private final ThreadGroup group;
	private final String namePrefix;
	private final Integer priority;
	private final AtomicInteger seq = new AtomicInteger(0);
	private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
	public NamedThreadFactory(String namePrefix,boolean daemon,int priority){
		this.namePrefix = namePrefix;
		this.daemon = daemon;
		this.priority = priority;
		group = Thread.currentThread().getThreadGroup();
		this.uncaughtExceptionHandler = new DtpUncaughtExceptionHandler();
	}
	public NamedThreadFactory(String namePrefix){
		this(namePrefix,false,Thread.NORM_PRIORITY);
	}
	public NamedThreadFactory(String namePrefix,boolean daemon){
		this(namePrefix,daemon, Thread.NORM_PRIORITY);
	}


	@Override
	public Thread newThread(Runnable r) {
//		String.format("{}{}",namePrefix,seq.getAndIncrement())
		Thread t = new Thread(group,r,new StringBuilder()
				.append("--").append(namePrefix).append("--").append(seq.getAndIncrement()).toString());
		t.setDaemon(daemon);
		t.setPriority(priority);
		t.setUncaughtExceptionHandler(uncaughtExceptionHandler);
		return t;
	}

	public static class DtpUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			log.error("thread {} throw exception {}",t,e);
		}
	}
}
