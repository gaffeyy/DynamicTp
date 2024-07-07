package com.gaffeyl.dynamictp.exception;

/**
 * @CLass: DtpException
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/7
 * @Version: 1.0
 * @Description:
 */
public class DtpException extends RuntimeException{
	public DtpException(String description){
		super(description);
	}
}
