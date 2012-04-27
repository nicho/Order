package com.omdasoft.orderonline.service.org;

import com.omdasoft.orderonline.domain.org.OrgInit;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface OrgInitLogic {

	/**
	 * 保存或修改
	 * @param context
	 * @param team
	 * @return
	 */
	public OrgInit save( OrgInit init);
	/**
	/**
	 * 查找初始化数据
	 * @param 
	 * @return
	 */
	public OrgInit getOrgInit();
	
	

}
