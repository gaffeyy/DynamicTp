package com.gaffeyl.dynamictp.controller;
import java.util.concurrent.TimeUnit;

import com.gaffeyl.dynamictp.DtpRegistry;
import com.gaffeyl.dynamictp.entity.DtpPropsRequestBody;
import com.gaffeyl.dynamictp.entity.DtpPropsResponseBody;
import com.gaffeyl.dynamictp.refresh.MyRefresh;
import com.gaffeyl.dynamictp.threadpool.DtpExecutor;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @CLass: DtpController
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/4
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/update")
public class DtpController {
	@Resource
	MyRefresh myRefresh;
	@PostMapping("/updateDtp")
	public List<String> update(@RequestBody DtpPropsRequestBody dtpPropsRequest){
		myRefresh.refresh(dtpPropsRequest,dtpPropsRequest.getThreadPoolName());
		List<String> allDtp = DtpRegistry.getAllDtp();
		List<String> result = new ArrayList<>();
		for(String s:allDtp){
			DtpExecutor executor = DtpRegistry.getExecutor(s);
			result.add(executor.getInformation());
		}
		return result;
	}

	@PostMapping("/getThreadPool")
	public String getThreadPool(@RequestParam("threadPoolName") String threadPoolName){
		DtpExecutor executor = DtpRegistry.getExecutor(threadPoolName);
		return executor.getInformation();
	}

	@GetMapping("/getAllDtp")
	public List<DtpPropsResponseBody> getAllDtp(){
		List<String> allDtp = DtpRegistry.getAllDtp();
		List<DtpPropsResponseBody> result = new ArrayList<>();
		for(String name:allDtp){
			DtpExecutor executor = DtpRegistry.getExecutor(name);
			DtpPropsResponseBody dtpPropsResponseBody = new DtpPropsResponseBody();
			dtpPropsResponseBody.setCorePoolSize(executor.getCorePoolSize());
			dtpPropsResponseBody.setMaximumPoolSize(executor.getMaximumPoolSize());
			dtpPropsResponseBody.setKeepAliveTime(executor.getKeepAliveTime(TimeUnit.SECONDS));
			dtpPropsResponseBody.setTimeUnit(TimeUnit.NANOSECONDS);
			dtpPropsResponseBody.setQueueName(executor.getQueueName());
			dtpPropsResponseBody.setCapacity(executor.getQueueCapacity());
			dtpPropsResponseBody.setThreadPoolName(executor.getThreadPoolName());
			dtpPropsResponseBody.setRejectHandlerName(executor.getRejectedExecutionHandler().getClass().getSimpleName());
			result.add(dtpPropsResponseBody);
		}
		return result;
	}

}
