package com.omdasoft.orderonline.gwt.order.client.awardShopLattice.module;


import com.google.gwt.inject.client.AbstractGinModule;
import com.omdasoft.orderonline.gwt.order.client.awardShopLattice.presenter.AwardShopLatticePresenter;
import com.omdasoft.orderonline.gwt.order.client.awardShopLattice.presenter.AwardShopLatticePresenter.AwardShopLatticeDisplay;
import com.omdasoft.orderonline.gwt.order.client.awardShopLattice.presenter.AwardShopLatticePresenterImpl;
import com.omdasoft.orderonline.gwt.order.client.awardShopLattice.view.AwardShopLatticeWidget;

public class AwardShopLatticeModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AwardShopLatticePresenter.class).to(AwardShopLatticePresenterImpl.class);
		bind(AwardShopLatticeDisplay.class).to(AwardShopLatticeWidget.class);
	}

}
