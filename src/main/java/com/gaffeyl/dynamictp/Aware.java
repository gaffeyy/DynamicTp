package com.gaffeyl.dynamictp;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @CLass: Aware
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/4
 * @Version: 1.0
 * @Description:
 */
public class Aware implements EnvironmentAware {
	private Environment environment;
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	public Environment getEnvironment(){
		return this.environment;
	}
}
