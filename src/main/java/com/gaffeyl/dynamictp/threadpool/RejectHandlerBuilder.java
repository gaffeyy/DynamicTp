package com.gaffeyl.dynamictp.threadpool;

import com.gaffeyl.dynamictp.Enum.RejectHandlerEnum;
import com.gaffeyl.dynamictp.exception.DtpException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @CLass: RejectHandlerBuilder
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/7
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class RejectHandlerBuilder {
	RejectHandlerBuilder(){}

	public static RejectedExecutionHandler buildRejectHandler(String name){
		if(Objects.equals(name, RejectHandlerEnum.ABORT_POLICY.getName())){
			return new ThreadPoolExecutor.AbortPolicy();
		} else if (Objects.equals(name,RejectHandlerEnum.CALLER_RUNS_POLICY.getName())) {
			return new ThreadPoolExecutor.CallerRunsPolicy();
		}else if(Objects.equals(name,RejectHandlerEnum.DISCARD_OLDEST_POLICY.getName())){
			return new ThreadPoolExecutor.DiscardOldestPolicy();
		}else if(Objects.equals(name,RejectHandlerEnum.DISCARD_POLICY.getName())){
			return new ThreadPoolExecutor.DiscardPolicy();
		}else {
			log.error("指定拒绝策略不存在");
			throw new DtpException("指定拒绝策略不存在");
		}
	}

}
