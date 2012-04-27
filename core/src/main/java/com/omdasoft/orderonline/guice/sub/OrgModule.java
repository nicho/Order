/**
 * 
 */
package com.omdasoft.orderonline.guice.sub;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.dao.org.CorporationDao;
import com.omdasoft.orderonline.dao.org.DepartmentDao;
import com.omdasoft.orderonline.dao.org.DepartmentManagerDao;
import com.omdasoft.orderonline.dao.org.OrganizationDao;
import com.omdasoft.orderonline.service.org.CorporationLogic;
import com.omdasoft.orderonline.service.org.CorporationService;
import com.omdasoft.orderonline.service.org.DepartmentLogic;
import com.omdasoft.orderonline.service.org.DepartmentManagerLogic;
import com.omdasoft.orderonline.service.org.DepartmentService;
import com.omdasoft.orderonline.service.org.OrgInitLogic;
import com.omdasoft.orderonline.service.org.OrgInitService;
import com.omdasoft.orderonline.service.org.impl.CorporationLogicImpl;
import com.omdasoft.orderonline.service.org.impl.CorporationServiceImpl;
import com.omdasoft.orderonline.service.org.impl.DepartmentLogicImpl;
import com.omdasoft.orderonline.service.org.impl.DepartmentManagerLogicImpl;
import com.omdasoft.orderonline.service.org.impl.DepartmentServiceImpl;
import com.omdasoft.orderonline.service.org.impl.OrgInitLogicImpl;
import com.omdasoft.orderonline.service.org.impl.OrgInitServiceImpl;

/**
 * Configure of organization module.
 * 
 * @author yanxin
 * @since 1.0
 */
public class OrgModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DepartmentDao.class).in(Singleton.class);

		bind(OrganizationDao.class).in(Singleton.class);
	
		bind(CorporationDao.class).in(Singleton.class);
		bind(DepartmentManagerDao.class).in(Singleton.class);
		bind(CorporationLogic.class).to(CorporationLogicImpl.class).in(
				Singleton.class);
		bind(CorporationService.class).to(CorporationServiceImpl.class).in(
				Singleton.class);

		bind(DepartmentLogic.class).to(DepartmentLogicImpl.class);
		bind(DepartmentService.class).to(DepartmentServiceImpl.class);
		
		bind(OrgInitLogic.class).to(OrgInitLogicImpl.class);
		bind(OrgInitService.class).to(OrgInitServiceImpl.class);
		

		bind(DepartmentManagerLogic.class).to(DepartmentManagerLogicImpl.class).in(Singleton.class);
		
	
	}

}
