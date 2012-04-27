package com.omdasoft.orderonline.guice;

import com.google.inject.AbstractModule;
import com.omdasoft.orderonline.guice.sub.DishesModule;
import com.omdasoft.orderonline.guice.sub.OrderModule;
import com.omdasoft.orderonline.guice.sub.OrgModule;
import com.omdasoft.orderonline.guice.sub.StaffModule;
import com.omdasoft.orderonline.guice.sub.UserModule;

public class EltModule extends AbstractModule {

	@Override
	protected void configure() {

		install(new CommonModule());
		install(new OrderModule());
		install(new UserModule());
		install(new DishesModule());
		install(new OrgModule());
		install(new StaffModule());



	}

}
