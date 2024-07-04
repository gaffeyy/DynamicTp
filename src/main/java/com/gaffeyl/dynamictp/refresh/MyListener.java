package com.gaffeyl.dynamictp.refresh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @CLass: MyListener
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/4
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Component
public class MyListener implements ApplicationListener<RefreshEvent> {
	@Override
	public void onApplicationEvent(RefreshEvent event) {
//		log.info("更新了配置:[{}]",event.getDtpProperties());
//		System.out.println(event.getDtpProperties());
	}
}
