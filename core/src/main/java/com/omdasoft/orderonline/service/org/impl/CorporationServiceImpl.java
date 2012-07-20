package com.omdasoft.orderonline.service.org.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.omdasoft.orderonline.dao.org.CorporationDao.QueryCompanyPageActionResult;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.company.CompanySearchCriteria;
import com.omdasoft.orderonline.model.org.CorporationVo;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.org.CorporationLogic;
import com.omdasoft.orderonline.service.org.CorporationService;

@Transactional
public class CorporationServiceImpl implements CorporationService {

	private final CorporationLogic corporationLogic;

	@Inject
	public CorporationServiceImpl(CorporationLogic corporationLogic) {
		this.corporationLogic = corporationLogic;
	}

	public Corporation saveCorporation(SysUser caller,
			CorporationVo corporationVo) {
		
		Corporation corporation = corporationLogic.saveCorporation(caller,corporationVo);
		
		return corporation;
	}

	@Override
	public Corporation findCorporationById(String id) {
		return corporationLogic.findCorporationById(id);
	}



	@Override
	public Corporation updateIntegralPrice(UserContext context,
			Corporation corporation) {
		return corporationLogic.updateIntegralPrice(context, corporation);
	}

	@Override
	public Corporation updatePeriod(UserContext context, Corporation corporation) {
		return corporationLogic.updatePeriod(context, corporation);
	}

	@Override
	public int getCorp() {
		return corporationLogic.getCorp();
		
	}

	@Override
	public String findCorporationBycId(String cid) {
		return corporationLogic.findCorporationBycId(cid);
	}

	@Override
	public QueryCompanyPageActionResult findCorporationByCompanyListCriteria(
			CompanySearchCriteria criteria) {
		return corporationLogic.findCorporationByCompanyListCriteria(criteria);
	}
   
}
