package com.omdasoft.orderonline.service.org.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.omdasoft.orderonline.dao.org.OrgInitDao;
import com.omdasoft.orderonline.domain.org.OrgInit;
import com.omdasoft.orderonline.service.org.OrgInitLogic;
import com.omdasoft.orderonline.util.StringUtil;

/**
 * 
 * 
 * @author lw
 * @since 1.0
 */
public class OrgInitLogicImpl implements OrgInitLogic {

	Logger logger = LoggerFactory.getLogger(getClass());

	OrgInitDao orgInitDao;
	

	@Inject
	public OrgInitLogicImpl(OrgInitDao orgInitDao) {
			this.orgInitDao = orgInitDao;
			
	}


	@Override
	public OrgInit save( OrgInit gift) {
		if (StringUtil.isEmptyString(gift.getId())) {
			
			orgInitDao.save(gift);
		} else {
			
			orgInitDao.update(gift);
		}

		return gift;
	}


	@Override
	public OrgInit getOrgInit() {
		// TODO Auto-generated method stub
		return orgInitDao.getOrgInit();
	}

	
}
