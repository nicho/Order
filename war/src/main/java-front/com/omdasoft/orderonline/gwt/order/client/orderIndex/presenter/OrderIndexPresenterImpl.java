package com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter;

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
//	final FrontOrderListPresenter frontorderIndexPresenter;
//	final OrderIndexPresenter orderIndexPresenter;
//	final OrdersLoginPresenter orderLoginPresenter;
//	final OrdersDishesPresenter ordersDishesPresenter;
	final EltGinjector injector;

	String orderId = null;

	@Inject
	public OrderIndexPresenterImpl(EventBus eventBus,
			OrderIndexDisplay display, ErrorHandler errorHandler,
//			FrontOrderListPresenter frontorderIndexPresenter,
//			OrdersLoginPresenter orderLoginPresenter,OrderIndexPresenter orderIndexPresenter,
//			OrdersDishesPresenter ordersDishesPresenter,
			EltGinjector injector) {
		super(eventBus, display);
		this.errorHandler = errorHandler;
//		this.frontorderIndexPresenter=frontorderIndexPresenter;
//		this.orderLoginPresenter=orderLoginPresenter;
//		this.orderIndexPresenter=orderIndexPresenter;
//		this.ordersDishesPresenter=ordersDishesPresenter;
		this.injector=injector;
	}

	@Override
	public void bind() {

		display.getAdminpage().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				injector.getMain().init(RootLayoutPanel.get());

			}
		});
//		display.getOrderListpage().addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent arg0) {
//				orderIndexPresenter.initPresenter(frontorderIndexPresenter.getDisplay().asWidget());
//				frontorderIndexPresenter.bind();
//			}
//		});
//		display.getOrderNow().addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent arg0) {
//	
//				
//				orderIndexPresenter.initPresenter(orderLoginPresenter.getDisplay().asWidget());
//				orderLoginPresenter.bind();
//
//			}
//		});
		display.getOrderIndex().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				injector.getOrderIndexPresenter().initPresenter(injector.getOrdersDishesPresenter().getDisplay().asWidget());
				injector.getOrdersDishesPresenter().bind();

			}
		});
	}

	@Override
	public void initPresenter(Widget widget) {
		display.getDock().clear();
		display.getDock().add(widget);
	}

}
