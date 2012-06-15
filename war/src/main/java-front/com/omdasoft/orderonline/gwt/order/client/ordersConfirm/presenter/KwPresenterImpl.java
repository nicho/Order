package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.mvp.BaseDialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.ui.MyAnchor;

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
			
			final MyAnchor cb=new MyAnchor(kw);

			
			if(value.indexOf(kw)!=-1)
			{
				cb.getElement().getFirstChildElement().setClassName("cur");
				cb.getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName("cur");
				selectKw.add(kw);
			}
			cb.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {	
					if(!selectKw.contains(kw))
					{
						selectKw.add(kw);
						cb.getElement().getFirstChildElement().setClassName("cur");
						cb.getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName("cur");
					}
					else
					{
						selectKw.remove(kw);
						cb.getElement().getFirstChildElement().setClassName("");
						cb.getElement().getFirstChildElement().getFirstChildElement().getFirstChildElement().setClassName("");
					}
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
