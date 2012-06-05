package com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;

public class OrderIndexPresenterImpl extends
		BasePresenter<OrderIndexPresenter.OrderIndexDisplay> implements
		OrderIndexPresenter {

	final ErrorHandler errorHandler;
	private final EltGinjector injector = GWT.create(EltGinjector.class);

	String orderId = null;

	@Inject
	public OrderIndexPresenterImpl(EventBus eventBus,
			OrderIndexDisplay display, ErrorHandler errorHandler) {
		super(eventBus, display);
		this.errorHandler = errorHandler;

	}

	@Override
	public void bind() {

		display.getAdminpage().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				injector.getMain().init(RootLayoutPanel.get());

			}
		});
		display.getOrderListpage().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				RootLayoutPanel.get().clear();
				RootLayoutPanel.get().add(injector.getOrderIndexPresenter().getDisplay().asWidget());
				
				injector.getOrderIndexPresenter().initPresenter(injector.getFrontOrderListPresenter().getDisplay().asWidget());
				injector.getOrderIndexPresenter().bind();
				injector.getFrontOrderListPresenter().bind();

			}
		});
		display.getOrderNow().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				RootLayoutPanel.get().clear();
				RootLayoutPanel.get().add(injector.getOrderIndexPresenter().getDisplay().asWidget());
				
				injector.getOrderIndexPresenter().initPresenter(injector.getOrderLoginPresenter().getDisplay().asWidget());
				injector.getOrderIndexPresenter().bind();
				injector.getOrderLoginPresenter().bind();

			}
		});
	}

	@Override
	public void initPresenter(Widget widget) {
		display.getDock().clear();
		display.getDock().add(widget);
	}

}
