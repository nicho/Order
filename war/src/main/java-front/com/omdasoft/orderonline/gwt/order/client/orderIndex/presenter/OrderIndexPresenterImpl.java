package com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public class OrderIndexPresenterImpl extends
		BasePresenter<OrderIndexPresenter.OrderIndexDisplay> implements
		OrderIndexPresenter {

	final ErrorHandler errorHandler;

	final EltGinjector injector;

	String orderId = null;

	@Inject
	public OrderIndexPresenterImpl(EventBus eventBus,
			OrderIndexDisplay display, ErrorHandler errorHandler,

			EltGinjector injector) {
		super(eventBus, display);
		this.errorHandler = errorHandler;

		this.injector = injector;
	}

	@Override
	public void bind() {

		registerHandler(display.getAdminpage().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				injector.getMain().init(RootLayoutPanel.get());

			}
		}));
		registerHandler(display.getOrderListpage().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				injector.getOrderIndexPresenter().initPresenter(
						injector.getFrontOrderListPresenter());

			}
		}));
		registerHandler(display.getOrderNow().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				injector.getOrderIndexPresenter().initPresenter(
						injector.getOrderLoginPresenter());

			}
		}));
		registerHandler(display.getOrderIndex().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				injector.getOrderIndexPresenter().initPresenter(
						injector.getOrdersDishesPresenter());

			}
		}));
	}

	@Override
	public void initPresenter(Presenter<?> presenter) {
		display.getDock().clear();
		display.getDock().add(presenter.getDisplay().asWidget());
		presenter.unbind();
		presenter.bind();
	}

}
