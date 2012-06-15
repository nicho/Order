package com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.mvp.BaseDialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class DishesDetailedPresenterImpl extends	BaseDialogPresenter<DishesDetailedPresenter.DishesDetailedDisplay> implements	DishesDetailedPresenter {

	final DispatchAsync dispatcher;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;


	@Inject
	public DishesDetailedPresenterImpl(EventBus eventBus,ErrorHandler errorHandler, SessionManager sessionManager,
			DishesDetailedDisplay display, DispatchAsync dispatcher,Win win	) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
	}

	@Override
	public void bind() {

	}

	@Override
	public void initDishesDetail(String name, String prices,
			String description, String photo) {
		display.setName(name);
		display.setPrice(prices);
		display.setDescription(description);
		if(!StringUtil.isEmpty(photo))
		display.setPhoto(photo);
		
	}

	
	
}
