/**
 * 
 */
package com.omdasoft.orderonline.guice.sub;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.dao.dishes.DishesDao;
import com.omdasoft.orderonline.dao.dishes.OrdersDishesDao;
import com.omdasoft.orderonline.service.dishes.DishesLogic;
import com.omdasoft.orderonline.service.dishes.DishesService;
import com.omdasoft.orderonline.service.dishes.impl.DishesLogicImpl;
import com.omdasoft.orderonline.service.dishes.impl.DishesServiceImpl;

/**
 * order module.
 * 
 * @author nicho
 * @since 2012年1月10日 16:00:44
 */
public class DishesModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		// order bind
		bind(DishesDao.class).in(Singleton.class);
		bind(OrdersDishesDao.class).in(Singleton.class);

		bind(DishesLogic.class).to(DishesLogicImpl.class).in(Singleton.class);

		bind(DishesService.class).to(DishesServiceImpl.class).in(Singleton.class);
	}

}
