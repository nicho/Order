package com.omdasoft.orderonline.service.org.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.omdasoft.orderonline.domain.org.OrgInit;
import com.omdasoft.orderonline.service.org.OrgInitLogic;
import com.omdasoft.orderonline.service.org.OrgInitService;
@Transactional
public class OrgInitServiceImpl implements OrgInitService {
	private final OrgInitLogic orgInitLogic;
	

	@Inject
	public OrgInitServiceImpl(OrgInitLogic orgInitLogic) {
		this.orgInitLogic = orgInitLogic;
		
	}


	@Override
	public OrgInit save(OrgInit init) {
		// TODO Auto-generated method stub
		return orgInitLogic.save(init);
	}


	@Override
	public OrgInit getOrgInit() {
		// TODO Auto-generated method stub
		return orgInitLogic.getOrgInit();
	}
	
	
	
	
	

}
