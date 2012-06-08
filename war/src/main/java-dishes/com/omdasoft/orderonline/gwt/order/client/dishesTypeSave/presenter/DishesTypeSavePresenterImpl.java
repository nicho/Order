package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.plugin.DishesTypeListConstants;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypePanelRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypePanelResponse;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypeSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypeSaveResponse;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.FindDishesTypeRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.FindDishesTypeResponse;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class DishesTypeSavePresenterImpl extends
		BasePresenter<DishesTypeSavePresenter.DishesTypeSaveDisplay> implements
		DishesTypeSavePresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;

	private final BreadCrumbsPresenter breadCrumbs;
	String dishesTypeId=null;
	@Inject
	public DishesTypeSavePresenterImpl(EventBus eventBus, DishesTypeSaveDisplay display,
			DispatchAsync dispatch, SessionManager sessionManager, Win win,
			BreadCrumbsPresenter breadCrumbs, ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		if(dishesTypeId==null)
		{
			breadCrumbs.loadChildPage("添加");
			display.hiddenRid();
		}
		else 
		{
			breadCrumbs.loadChildPage("修改");
			FindDishesTypeRequest findrequest=new FindDishesTypeRequest();
			findrequest.setDishesTypeId(dishesTypeId);
			dispatch.execute(findrequest,
					new AsyncCallback<FindDishesTypeResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(
								FindDishesTypeResponse response) {
							display.getName().setValue(response.getName());
							display.getDishestype().setValue(response.getDishesType());
							display.setRid(response.getRid());
	
						}

					});
		}
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
	}

	private void init() {
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						DishesTypeSaveRequest request = new DishesTypeSaveRequest();
						if(!StringUtil.isEmpty(dishesTypeId))
							request.setId(dishesTypeId);
						request.setName(display.getName().getValue());
						request.setDishesType(display.getDishestype().getValue());
						request.setSession(sessionManager.getSession());
						dispatch.execute(request,
								new AsyncCallback<DishesTypeSaveResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(
											DishesTypeSaveResponse response) {
										win.alert("保存成功!");
										Platform.getInstance()
										.getEditorRegistry()
										.openEditor(
												DishesTypeListConstants.EDITOR_DISHESTYPELIST_SEARCH,
												"EDITOR_DISHESTYPELIST_SEARCH_DO_ID", null);
									}

								});
					}
				}));
		
		dispatch.execute(new DishesTypePanelRequest(),
				new AsyncCallback<DishesTypePanelResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(
							DishesTypePanelResponse response) {
						display.getDishestypePanel().clear();
						if(response.getTypeNameList()!=null && response.getTypeNameList().size()>0)
						{
							for (final String name:response.getTypeNameList()) {
								Anchor ar=new Anchor(name);
								ar.setStyleName("paddingleft5");
								ar.addClickHandler(new ClickHandler() {
									
									@Override
									public void onClick(ClickEvent event) {
										display.getDishestype().setText(name);
										
									}
								});
								display.getDishestypePanel().add(ar);
							}
						}
						
					}

				});
	}

	@Override
	public void initDishesType(String dishesTypeId) {
		this.dishesTypeId=dishesTypeId;
	}

}
