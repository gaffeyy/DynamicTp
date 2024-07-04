package com.gaffeyl.dynamictp.refresh;

import com.gaffeyl.dynamictp.entity.DtpProperties;
import org.springframework.context.ApplicationEvent;

/**
 * @CLass: RefreshEvent
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/4
 * @Version: 1.0
 * @Description:
 */
public class RefreshEvent extends ApplicationEvent {
	private final DtpProperties dtpProperties;
	private final String updateThreadPool;
	RefreshEvent(Object source, DtpProperties dtpProperties,String updateThreadPool){
		super(source);
		this.dtpProperties = dtpProperties;
		this.updateThreadPool = updateThreadPool;
	}

	public DtpProperties getDtpProperties(){
		return this.dtpProperties;
	}
	public String getUpdateThreadPoolName(){
		return this.updateThreadPool;
	}
}
