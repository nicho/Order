package com.omdasoft.orderonline.guice;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.omdasoft.orderonline.common.BaseDao;

public class CommonModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("elt"));
//		 EntityManager em = Persistence.createEntityManagerFactory("elt")
//		 .createEntityManager();
//		 bind(EntityManager.class).toInstance(em);
		bind(BaseDao.class);
	}

}
