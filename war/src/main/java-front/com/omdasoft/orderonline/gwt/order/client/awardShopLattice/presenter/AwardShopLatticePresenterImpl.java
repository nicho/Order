package com.omdasoft.orderonline.gwt.order.client.awardShopLattice.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.awardShopLattice.presenter.AwardShopLatticePresenter.AwardShopLatticeDisplay;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;

public class AwardShopLatticePresenterImpl extends BasePresenter<AwardShopLatticeDisplay>
		implements AwardShopLatticePresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;


	@Inject
	public AwardShopLatticePresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			AwardShopLatticeDisplay display, Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;


	}

	@Override
	public void bind() {

	}


	


}
