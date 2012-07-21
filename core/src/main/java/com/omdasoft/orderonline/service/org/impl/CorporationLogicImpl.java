package com.omdasoft.orderonline.service.org.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.omdasoft.orderonline.dao.org.CorporationDao;
import com.omdasoft.orderonline.dao.org.CorporationDao.QueryCompanyPageActionResult;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.common.Amount;
import com.omdasoft.orderonline.model.company.CompanySearchCriteria;
import com.omdasoft.orderonline.model.org.CorporationVo;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.vo.CompanySearchVo;
import com.omdasoft.orderonline.service.exception.GetMaxConsumeErrorException;
import com.omdasoft.orderonline.service.org.CorporationLogic;
import com.omdasoft.orderonline.util.DateUtil;
import com.omdasoft.orderonline.util.StringUtil;

/**
 * The implementation of {@link CorporationLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class CorporationLogicImpl implements CorporationLogic {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	CorporationDao corporationDao;

	@Inject
	public CorporationLogicImpl(
			CorporationDao corporationDao) {
		this.corporationDao = corporationDao;
	}

	@Override
	public Corporation saveCorporation(SysUser caller, CorporationVo corporation) {
		logger.debug(" Process in saveCorporation method, corporation.toString:"
				+ corporation.toString());
		Corporation corp;
		if (StringUtil.isEmptyString(corporation.getId())) {
			// Create a new corporation
			corp = new Corporation();

			
			// Create the default unit code.


			Date now = DateUtil.getTime();
			corp.setName(corporation.getName());
			corp.setDescription(corporation.getDescription());
			corp.setDefaultUnitCode(corporation.getUnitCode());
			corp.setAddress(corporation.getAddress());
			corp.setCellphone(corporation.getCellphone());
			corp.setCorporation(corporation.getCorporation());
			corp.setEmailAddress(corporation.getEmailAddress());
			corp.setFax(corporation.getFax());
			corp.setLinkman(corporation.getLinkman());
			corp.setTell(corporation.getTell());
			corp.setWeb(corporation.getWeb());
			corp.setCreatedAt(now);
			corp.setCreatedBy(caller);
			corp.setLastModifiedAt(now);
			corp.setLastModifiedBy(caller);
			corp.setCid(corporation.getCid());
			corporationDao.save(corp);
		} else {
			// Edit a existed corporation
			corp = corporationDao.findById(Corporation.class,
					corporation.getId());
	
			Date now = DateUtil.getTime();
			corp.setName(corporation.getName());
			corp.setDescription(corporation.getDescription());
			corp.setTxAccountId(corporation.getTxAccountId());
			corp.setDefaultUnitCode(corporation.getUnitCode());
			corp.setAddress(corporation.getAddress());
			corp.setCellphone(corporation.getCellphone());
			corp.setCorporation(corporation.getCorporation());
			corp.setEmailAddress(corporation.getEmailAddress());
			corp.setFax(corporation.getFax());
			corp.setLinkman(corporation.getLinkman());
			corp.setTell(corporation.getTell());
			corp.setWeb(corporation.getWeb());
			corp.setCid(corporation.getCid());
			corp.setCreatedAt(now);
			corp.setCreatedBy(caller);
			corp.setLastModifiedAt(now);
			corp.setLastModifiedBy(caller);
			corporationDao.update(corp);
		}

		return corp;
	}

	@Override
	public Corporation updateIntegralPrice(UserContext context,
			Corporation corporation) {
		logger.debug(" Process in updateIntegralPrice method, corporation.toString:"
				+ corporation.toString());
		corporation = corporationDao.update(corporation);
		return corporation;
	}
	
	@Override
	public Corporation updatePeriod(UserContext context, Corporation corporation) {
		logger.debug(" Process in updatePeriod method, corporation.toString:"
				+ corporation.toString());
		corporation = corporationDao.update(corporation);
		return corporation;
	}

	@Override
	public Corporation findCorporationById(String id) {
		logger.debug(" Process in findCorporationById method, parameter id:"
				+ id);
		Corporation res = corporationDao.findById(Corporation.class, id);
		if (res == null || StringUtil.isEmptyString(res.getId())) {
			throw new IllegalArgumentException(
					"has not found Corporation by Id,Corporation.id:" + id);
		}
		return res;
	}




	@Override
	public Amount getMaxConsume(String corporationId)
			throws GetMaxConsumeErrorException {
		return null;
	}

	@Override
	public int getCorp() {
		return corporationDao.getCorp();
	}

	@Override
	public String findCorporationBycId(String cid) {
		return corporationDao.getCorporationBycId(cid);
	}


	@Override
	public QueryCompanyPageActionResult findCorporationByCompanyListCriteria(CompanySearchCriteria criteria) {
		CompanySearchVo searchVo = new CompanySearchVo();
		searchVo.setName(criteria.getName());
		
		return corporationDao.queryCompanyPageAction(searchVo);
	}

}
