package com.gaffeyl.dynamictp.entity;

import com.gaffeyl.dynamictp.DtpRegistry;
import com.gaffeyl.dynamictp.threadpool.DtpExecutor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@SpringBootTest
@Slf4j
class DtpPropertiesTest {
	@Resource
	DtpProperties dtpProperties;



	@Test
	void getExecutors() {
		List<ThreadPoolProperties> executors = dtpProperties.getExecutors();
		for(ThreadPoolProperties p:executors){
			System.out.println(p);
		}
//		DtpRegistry.getExecutor()
		List<String> allDtp = DtpRegistry.getAllDtp();
		List<DtpExecutor> executorList = new ArrayList<>();
//		System.out.println(allDtp.size());
		for(String name:allDtp){
			DtpExecutor executor = DtpRegistry.getExecutor(name);
			executorList.add(executor);
//			System.out.println(name);
		}
//		System.out.println(executorList.size());
//		for(DtpExecutor executor:executorList){
//			System.out.println(executor.getInformation());
//		}
		DtpExecutor executor = executorList.get(0);
		// 创建一个固定大小的线程池
		ExecutorService threadPool = new ThreadPoolExecutor(
				2, // 核心线程数
				4, // 最大线程数
				60, // 空闲线程存活时间
				TimeUnit.SECONDS, // 时间单位
				new LinkedBlockingQueue<>(10), // 任务队列
				new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
		);
		System.out.println(executor.getClass());
		for(int i = 0;i < 6;i++){
			final int x = i;
			executor.submit(() ->{
				try {
//					System.out.println(x);
					System.out.println("Task:"+x+" is Running By "+Thread.currentThread().getName());
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				} catch (RejectedExecutionException re){
					log.error(re.toString());
				}
			});
		}
		DtpExecutor executor1 = executorList.get(1);
		for(int i = 0;i < 6;i++){
			final int x = i;
			executor1.submit(() ->{
				try {
//					System.out.println(x);
					System.out.println("Task:"+x+" is Running By "+Thread.currentThread().getName());
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				} catch (RejectedExecutionException re){
					log.error(re.toString());
				}
			});
		}


	}
}