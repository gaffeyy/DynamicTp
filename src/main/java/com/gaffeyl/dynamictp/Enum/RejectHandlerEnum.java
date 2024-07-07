package com.gaffeyl.dynamictp.Enum;

import lombok.Getter;

/**
 * @Enum: RejectHandlerEnum
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/7
 * @Description:
 * @Version: 1.0
 */
@Getter
public enum RejectHandlerEnum {
		/**
		 * RejectedExecutionHandler type while triggering reject policy.
		 */
		ABORT_POLICY("AbortPolicy"),

		CALLER_RUNS_POLICY("CallerRunsPolicy"),

		DISCARD_OLDEST_POLICY("DiscardOldestPolicy"),

		DISCARD_POLICY("DiscardPolicy");

		private final String name;

		RejectHandlerEnum(String name) {
			this.name = name;
		}

}
