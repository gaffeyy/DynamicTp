package com.gaffeyl.dynamictp.refresh;

import com.gaffeyl.dynamictp.entity.DtpPropsRequestBody;

/**
 * @Interface: Refresh
 * @BelongProlect:DynamicTp
 * @Author: gaf_Song
 * @Date:2024/7/4
 * @Description:
 * @Version: 1.0
 */
public interface Refresh {
	void refresh(DtpPropsRequestBody dtpPropsRequest, String ConfigureFileType);
}
