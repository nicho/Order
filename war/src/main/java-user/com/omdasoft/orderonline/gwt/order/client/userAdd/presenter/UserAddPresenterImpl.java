package com.omdasoft.orderonline.gwt.order.client.userAdd.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.userAdd.request.UserAddRequest;
import com.omdasoft.orderonline.gwt.order.client.userAdd.request.UserAddResponse;
import com.omdasoft.orderonline.gwt.order.client.userList.plugin.UserListConstants;
import com.omdasoft.orderonline.gwt.order.client.userView.request.UserViewRequest;
import com.omdasoft.orderonline.gwt.order.client.userView.request.UserViewResponse;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;




public class UserAddPresenterImpl extends
		BasePresenter<UserAddPresenter.UserAddDisplay> implements
		UserAddPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	String staffId = null;
	private final BreadCrumbsPresenter breadCrumbs;
	
	@Inject
	public UserAddPresenterImpl(EventBus eventBus, UserAddDisplay display,
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

		if(staffId!=null)
		{
			breadCrumbs.loadChildPage("修改用户");
			display.setTitleText("修改用户");
		}
		else
		{
			breadCrumbs.loadChildPage("添加用户");
			display.setTitleText("添加用户");
		}
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						if(StringUtil.isEmpty(display.getStaffName()))
						{
							win.alert("请填写用户名!");
							return;
						}
						if(staffId==null)
						{
							if(StringUtil.isEmpty(display.getPwd().getValue()) || StringUtil.isEmpty(display.getPwd2().getValue()))
							{
								win.alert("密码不能为空!");
								return;
							}
							if(!display.getPwd2().getValue().equals(display.getPwd().getValue()))
							{
								win.alert("两次密码不一致!");
								return;
							}
						}
						
						UserAddRequest request = new UserAddRequest();
						if (staffId != null)
							request.setStaffId(staffId);
						request.setSession(sessionManager.getSession());
						request.setStaffName(display.getStaffName());
						request.setStaffNo(display.getStaffNo());
						if(staffId==null)
						request.setPwd(display.getPwd().getValue());
						request.setPhone(display.getPhone());
				
						
						List<UserRoleVo> UserRoleVos=new ArrayList<UserRoleVo>();
						if(display.getAdmin().getValue())
						{
							UserRoleVos.add(UserRoleVo.CORP_ADMIN);		
						}
						if(display.getGift().getValue())
						{
							UserRoleVos.add(UserRoleVo.DEPT_MGR);		
						}
						request.setUserRoleVos(UserRoleVos);
						dispatch.execute(request,
								new AsyncCallback<UserAddResponse>() {

									@Override
									public void onFailure(Throwable t) {
										win.alert(t.getMessage());
									}

									@Override
									public void onSuccess(UserAddResponse resp) {
										win.alert("保存成功");
										Platform.getInstance()
												.getEditorRegistry()
												.openEditor(
														UserListConstants.EDITOR_USERLIST_SEARCH,
														"EDITOR_STAFFLIST_SEARCH_DO_ID",
														null);
									}
								});
					}
				}));

	}
	


	private void init() {
		
		if (staffId != null) {
			// 修改加载数据
			display.enableStaffName();
			dispatch.execute(new UserViewRequest(staffId),
					new AsyncCallback<UserViewResponse>() {

						@Override
						public void onFailure(Throwable t) {
							win.alert(t.getMessage());
						}

						@Override
						public void onSuccess(UserViewResponse resp) {

							display.setStaffNo(resp.getStaffNo());
							display.setStaffName(resp.getStaffName());
						
							display.setPhone(resp.getPhone());

							if(resp.getUserRoleVos()!=null && resp.getUserRoleVos().size()>0)
							{
								for (UserRoleVo role:resp.getUserRoleVos()) {
									if(role==UserRoleVo.CORP_ADMIN)
										display.getAdmin().setValue(true);
									if(role==UserRoleVo.DEPT_MGR)
										display.getGift().setValue(true);

								}
							}
							else
							{
								display.getAdmin().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
								
							}
							display.getPwd().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
							display.getPwd2().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
						}
					});			
			

		}else{
			
			display.getAdmin().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
		}
		
	
	}
	
	

	@Override
	public void initStaffUpdate(String staffId) {
		this.staffId = staffId;
	}

}
