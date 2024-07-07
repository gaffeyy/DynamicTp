package com.gaffeyl.dynamictp.Enum;

import com.gaffeyl.dynamictp.exception.DtpException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @Enum: QueueTypeEnum
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/7
 * @Description:
 * @Version: 1.0
 */
@Getter
@Slf4j
public enum QueueTypeEnum {
	/**
	 * BLocking Queue Enum
	 */
	ARRAY_BLOCKING_QUEUE(1,"ArrayBlockingQueue"),

	LINKED_BLOCKING_QUEUE(2,"LinkedBlockingQueue"),

	PRIORITY_BLOCKING_QUEUE(3,"PriorityBlockingQueue"),

	DELAY_QUEUE(4,"DelayQueue"),

	SYNCHRONOUS_QUEUE(5,"SynchronousQueue"),

	LINKED_TRANSFER_QUEUE(6,"LinkedTransferQueue"),

	LINKED_BLOCKING_DEQUE(7,"LinkedBlockingDeque");

	private final Integer code;
	private final String name;
	QueueTypeEnum(Integer code,String name){
		this.code = code;
		this.name = name;
	}
	public static BlockingQueue<Runnable> buildBlockingQueue(String name,int capacity,boolean fair){
		BlockingQueue<Runnable> queue = null;
		if(Objects.equals(name,ARRAY_BLOCKING_QUEUE.getName())){
			queue = new ArrayBlockingQueue<>(capacity);
		}else if(Objects.equals(name,LINKED_BLOCKING_QUEUE.getName())){
			queue = new LinkedBlockingQueue<>(capacity);
		}else if(Objects.equals(name,PRIORITY_BLOCKING_QUEUE.getName())){
			queue = new PriorityBlockingQueue<>(capacity);
		}else if(Objects.equals(name,DELAY_QUEUE.getName())){
			queue = new DelayQueue();
		}else if(Objects.equals(name,SYNCHRONOUS_QUEUE.getName())){
			queue = new SynchronousQueue<>(fair);
		}else if(Objects.equals(name,LINKED_TRANSFER_QUEUE.getName())){
			queue = new LinkedTransferQueue<>();
		}else if(Objects.equals(name,LINKED_BLOCKING_DEQUE.getName())){
			queue = new LinkedBlockingDeque<>(capacity);
		}
		if(queue != null){
			return queue;
		}
		log.error("队列创建失败");
		throw new DtpException("创建队列失败");
	}
}
