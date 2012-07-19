package com.omdasoft.orderonline.gwt.order.client.restaurantSave.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.core.ui.DialogCloseListener;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.ChooseLeaderEvent;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.ChooseLeaderHandler;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.ChooseLeaderWinDialog;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.StaffClient;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.model.OrganicationClient;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.plugin.RestaurantListConstants;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.FindRestaurantRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.FindRestaurantResponse;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.RestaurantSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.RestaurantSaveResponse;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class RestaurantSavePresenterImpl extends
		BasePresenter<RestaurantSavePresenter.RestaurantSaveDisplay> implements
		RestaurantSavePresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;

	private final BreadCrumbsPresenter breadCrumbs;
	String restaurantId=null;
	
	
	private final Provider<ChooseLeaderWinDialog> chooseLeaderDialogProvider;

	@Inject
	public RestaurantSavePresenterImpl(EventBus eventBus, RestaurantSaveDisplay display,
			DispatchAsync dispatch, SessionManager sessionManager, Win win,
			BreadCrumbsPresenter breadCrumbs, ErrorHandler errorHandler,Provider<ChooseLeaderWinDialog> chooseLeaderDialogProvider) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
		this.chooseLeaderDialogProvider=chooseLeaderDialogProvider;
	}

	@Override
	public void bind() {
		if(restaurantId==null)
			breadCrumbs.loadChildPage("添加");
		else 
		{
			breadCrumbs.loadChildPage("修改");
			FindRestaurantRequest findrequest=new FindRestaurantRequest();
			findrequest.setRestaurantId(restaurantId);
			dispatch.execute(findrequest,
					new AsyncCallback<FindRestaurantResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(
								FindRestaurantResponse response) {
							display.getCity().setValue(response.getCity());
							display.getRestaurant().setValue(response.getName());
							display.getAddress().setValue(response.getAddress());
							display.getPhone().setValue(response.getPhone());
							display.getDid().setValue(response.getDid());
							if(!StringUtil.isEmpty(response.getDeptAdminStaffId()))
							{
								OrganicationClient org=new OrganicationClient();
								org.setId(response.getDeptAdminStaffId());
								org.setName(response.getDeptAdminStaffName());
								display.getLeaderArea().addItem(org);
							}
						}

					});
		}
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		registerChooseLeader();
	}

	private void init() {
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RestaurantSaveRequest request = new RestaurantSaveRequest();
						if(!StringUtil.isEmpty(restaurantId))
						{
							request.setId(restaurantId);
						}
						request.setCity(display.getCity().getValue());
						request.setName(display.getRestaurant().getValue());
						request.setAddress(display.getAddress().getValue());
						request.setPhone(display.getPhone().getValue());
						request.setDid(display.getDid().getValue());
						request.setSession(sessionManager.getSession());
						List<OrganicationClient> org=display.getLeaderArea().getItemList();
						if(org!=null && org.size()>0)
						{
							request.setDeptAdminStaffId(org.get(0).getId());
						}
						
						dispatch.execute(request,
								new AsyncCallback<RestaurantSaveResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(
											RestaurantSaveResponse response) {
										win.alert("保存成功!");
										Platform.getInstance()
										.getEditorRegistry()
										.openEditor(
												RestaurantListConstants.EDITOR_RESTAURANTLIST_SEARCH,
												"EDITOR_RESTAURANTLIST_SEARCH_DO_ID", null);
									}

								});
					}
				}));
	}

	@Override
	public void initRestaurant(String restaurantId) {
		this.restaurantId=restaurantId;
	}
	//选择Leader
		private void registerChooseLeader(){	
			registerHandler(display.getChooseLeaderBtnClick().addClickHandler(
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent arg0) {
							final HandlerRegistration registration = eventBus.addHandler(ChooseLeaderEvent.getType(),new ChooseLeaderHandler() {
												@Override
												public void chosenLeader(List<StaffClient> list) {
													for (StaffClient item : list) {
														if(display.getLeaderArea()!=null&&item!=null){
															if (!display.getLeaderArea().containsItem(item)) {
																display.getLeaderArea().clear();														
																display.getLeaderArea().addItem(item);		
															}
														}
														
													}
												}
											});
								//
							final ChooseLeaderWinDialog dialog = chooseLeaderDialogProvider
									.get();
							dialog.setNominee(false, true, null);
							//    
							Platform.getInstance().getSiteManager()
									.openDialog(dialog, new DialogCloseListener() {
										public void onClose(String dialogId,String instanceId) {
											registration.removeHandler();
										}
									});
						}
					}));
		}

}
