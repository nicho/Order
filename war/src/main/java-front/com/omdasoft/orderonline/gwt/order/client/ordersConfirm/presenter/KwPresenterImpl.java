package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.mvp.BaseDialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;

public class KwPresenterImpl extends	BaseDialogPresenter<KwPresenter.KwDisplay> implements	KwPresenter {

	final DispatchAsync dispatcher;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	List<String> selectKw=new ArrayList<String>();


	@Inject
	public KwPresenterImpl(EventBus eventBus,ErrorHandler errorHandler, SessionManager sessionManager,
			KwDisplay display, DispatchAsync dispatcher) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
	
	}

	@Override
	public void bind() {

	}

	@Override
	public void initKw(List<String> kwlt,String value) {
		display.getCheckBoxPanel().clear();
		selectKw.clear();
		
		for (final String kw:kwlt) {
			final CheckBox cb=new CheckBox(kw);
			if(value.indexOf(kw)!=-1)
			{
				cb.setValue(true);
				selectKw.add(kw);
			}
			cb.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if(cb.getValue())
						selectKw.add(kw);
					else
						selectKw.remove(kw);
					
				}
			});
			display.getCheckBoxPanel().add(cb);
		}
		
	}

	@Override
	public List<String> getkwlt() {
		return selectKw;
	}

	
	
}
