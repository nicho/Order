package com.omdasoft.orderonline.service.org;

import com.omdasoft.orderonline.dao.org.CorporationDao.QueryCompanyPageActionResult;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.company.CompanySearchCriteria;
import com.omdasoft.orderonline.model.org.CorporationVo;
import com.omdasoft.orderonline.model.user.UserContext;

/**
 * Service of corporation.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface CorporationService {

	/**
	 * Save or update a corporation.
	 * 
	 * @param caller
	 * @param corporation
	 * @return
	 */
	public Corporation saveCorporation(SysUser caller, CorporationVo corporation);
	
	public Corporation updateIntegralPrice(UserContext context,	Corporation corporation);
	
	public Corporation updatePeriod(UserContext context,
			Corporation corporation);

	/**
	 * Find a corporation by the specified id.
	 * 
	 * @param id
	 * @return
	 */
	public Corporation findCorporationById(String id);


	/**
	 * 得到企业的数量用于注册
	 * @return
	 */
	
	public int getCorp();
	/**
	 * 根据CID查找机构Id
	 * @param id
	 * @return
	 */
	public String findCorporationBycId(String cid);
	/**
	 * Find corporation collection by Company List Criteria
	 * 
	 * @param name
	 * @return
	 */
	public QueryCompanyPageActionResult findCorporationByCompanyListCriteria(CompanySearchCriteria criteria);
}
