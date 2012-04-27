/**
 * 
 */
package com.omdasoft.orderonline.guice.sub;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.dao.user.TokenDao;
import com.omdasoft.orderonline.dao.user.UserDao;
import com.omdasoft.orderonline.service.user.UserLogic;
import com.omdasoft.orderonline.service.user.UserService;
import com.omdasoft.orderonline.service.user.impl.UserLogicImpl;
import com.omdasoft.orderonline.service.user.impl.UserServiceImpl;

/**
 * User module.
 * 
 * @author nicho
 * @since 2012年1月10日 16:00:44
 */
public class UserModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		// User bind
		bind(UserDao.class).in(Singleton.class);
		bind(TokenDao.class).in(Singleton.class);

		bind(UserLogic.class).to(UserLogicImpl.class).in(Singleton.class);

		bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
	}

}
