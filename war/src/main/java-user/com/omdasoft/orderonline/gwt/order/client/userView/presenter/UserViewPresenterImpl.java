package com.omdasoft.orderonline.gwt.order.client.userView.presenter;

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
import com.omdasoft.orderonline.gwt.order.client.userAdd.dataprovider.UserWinAdapter;
import com.omdasoft.orderonline.gwt.order.client.userAdd.plugin.UserAddConstants;
import com.omdasoft.orderonline.gwt.order.client.userView.model.UserWinClient;
import com.omdasoft.orderonline.gwt.order.client.userView.request.UserViewRequest;
import com.omdasoft.orderonline.gwt.order.client.userView.request.UserViewResponse;
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class UserViewPresenterImpl extends
		BasePresenter<UserViewPresenter.UserViewDisplay> implements
		UserViewPresenter {

	private final DispatchAsync dispatch;
	//private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;

	private final BreadCrumbsPresenter breadCrumbs;
	String staffId;
	boolean colleague=false;
	EltNewPager pager;
	ListCellTable<UserWinClient> cellTable;
	UserWinAdapter listViewAdapter;
	@Inject
	public UserViewPresenterImpl(EventBus eventBus, UserViewDisplay display,
			DispatchAsync dispatch, Win win,
			BreadCrumbsPresenter breadCrumbs, ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
	//	this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("用户详细信息");
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		registerHandler(display.getupadateBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								UserAddConstants.EDITOR_STAFFADD_SEARCH,
								"EDITOR_STAFFADD_SEARCH_DO_ID", staffId);
					}
				}));

	}

	void init() {
		if(colleague==true)
		{
			display.displayUpdateBtn(colleague);
		}

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
							String roleString="";
							for (UserRoleVo role:resp.getUserRoleVos()) {
								if(role==UserRoleVo.CORP_ADMIN)
									roleString+="管理员;";
								if(role==UserRoleVo.DEPT_MGR)
									roleString+="分店管理员;";
							}
							if(!StringUtil.isEmpty(roleString))
								display.getStaffRoles().setText(roleString);
							else
								display.getStaffRoles().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
							
						}
						else
						{
							display.getStaffRoles().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
						}
					}
				});

	}

	@Override
	public void initUserView(String staffId) {
		this.staffId = staffId;

	}


	@Override
	public void initUserView_Colleague(String staffId,boolean colleague) {
		this.staffId = staffId;
		this.colleague=colleague;
	}
}
