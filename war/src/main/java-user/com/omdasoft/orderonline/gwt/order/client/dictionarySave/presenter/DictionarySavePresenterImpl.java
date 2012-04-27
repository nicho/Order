package com.omdasoft.orderonline.gwt.order.client.dictionarySave.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.plugin.DictionaryListConstants;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.DictionarySaveRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.DictionarySaveResponse;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.FindDictionaryRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.FindDictionaryResponse;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class DictionarySavePresenterImpl extends
		BasePresenter<DictionarySavePresenter.DictionarySaveDisplay> implements
		DictionarySavePresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;

	private final BreadCrumbsPresenter breadCrumbs;
	String dictionaryId=null;
	int dictionaryType=0;
	@Inject
	public DictionarySavePresenterImpl(EventBus eventBus, DictionarySaveDisplay display,
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
		if(dictionaryId==null)
			breadCrumbs.loadChildPage("添加");
		else 
		{
			breadCrumbs.loadChildPage("修改");
			FindDictionaryRequest findrequest=new FindDictionaryRequest();
			
			dispatch.execute(findrequest,
					new AsyncCallback<FindDictionaryResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(
								FindDictionaryResponse response) {
							display.getName().setValue(response.getName());
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
						DictionarySaveRequest request = new DictionarySaveRequest();
						if(!StringUtil.isEmpty(dictionaryId))
						{
							request.setId(dictionaryId);
						}
						request.setDictionaryType(dictionaryType);
						request.setName(display.getName().getValue());
						request.setSession(sessionManager.getSession());
						
						dispatch.execute(request,
								new AsyncCallback<DictionarySaveResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(
											DictionarySaveResponse response) {
										win.alert("保存成功!");
										Platform.getInstance()
										.getEditorRegistry()
										.openEditor(
												DictionaryListConstants.EDITOR_DICTIONARYLIST_SEARCH,
												"EDITOR_DICTIONARYLIST_SEARCH_DO_ID", dictionaryType);
									}

								});
					}
				}));
	}

	@Override
	public void initDictionary(String dictionaryId) {
		this.dictionaryId=dictionaryId;
	}

	@Override
	public void initDictionaryType(int dictionaryType) {
		this.dictionaryType=dictionaryType;
		
	}

}
