/**
 * 
 */
package com.omdasoft.orderonline.guice.sub;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.dao.org.StaffDao;
import com.omdasoft.orderonline.service.staff.IStaffService;
import com.omdasoft.orderonline.service.staff.StaffLogic;
import com.omdasoft.orderonline.service.staff.impl.StaffLogicImpl;
import com.omdasoft.orderonline.service.staff.impl.StaffServiceImpl;

/**
 * Staff module.
 * 
 * @author yanxin
 * @since 1.0
 */
public class StaffModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		// staff bind
		bind(StaffDao.class).in(Singleton.class);

		bind(StaffLogic.class).to(StaffLogicImpl.class).in(Singleton.class);

		bind(IStaffService.class).to(StaffServiceImpl.class)
				.in(Singleton.class);
	}

}
