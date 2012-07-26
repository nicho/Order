package com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
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
				String href=Window.Location.getHref();
				href=href.replace("#", "");

				if(href.indexOf("?")!=-1)
					href+="&page=ADMIN";
				else
					href+="?page=ADMIN";

					
				Window.open(href, "后台管理", null);
				
			//	injector.getMain().init(RootLayoutPanel.get());

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
				injector.getOrderLoginPresenter().rsDoLogin();
				injector.getOrderIndexPresenter().initPresenter(
						injector.getOrdersDishesPresenter());

			}
		}));
		registerHandler(display.getQueryOrder().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				injector.getOrderIndexPresenter().initPresenter(
						injector.getFrontOrderListPresenter());

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
