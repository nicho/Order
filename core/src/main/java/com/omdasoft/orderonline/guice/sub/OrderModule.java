/**
 * 
 */
package com.omdasoft.orderonline.guice.sub;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.dao.order.OrderDao;
import com.omdasoft.orderonline.dao.rest.InvokeHistoryDao;
import com.omdasoft.orderonline.service.order.OrderLogic;
import com.omdasoft.orderonline.service.order.OrderService;
import com.omdasoft.orderonline.service.order.impl.OrderLogicImpl;
import com.omdasoft.orderonline.service.order.impl.OrderServiceImpl;

/**
 * order module.
 * 
 * @author nicho
 * @since 2012年1月10日 16:00:44
 */
public class OrderModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		// order bind
		bind(OrderDao.class).in(Singleton.class);
		bind(InvokeHistoryDao.class).in(Singleton.class);

		bind(OrderLogic.class).to(OrderLogicImpl.class).in(Singleton.class);

		bind(OrderService.class).to(OrderServiceImpl.class).in(Singleton.class);
	}

}
