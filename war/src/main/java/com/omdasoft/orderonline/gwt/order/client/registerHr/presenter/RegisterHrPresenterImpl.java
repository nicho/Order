package com.omdasoft.orderonline.gwt.order.client.registerHr.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.company.plugin.CompanyConstants;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.registerHr.model.HrVo;
import com.omdasoft.orderonline.gwt.order.client.registerHr.request.RegisterHrRequest;
import com.omdasoft.orderonline.gwt.order.client.registerHr.request.RegisterHrResponse;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class RegisterHrPresenterImpl extends
		BasePresenter<RegisterHrPresenter.RegisterHrDisplay> implements
		RegisterHrPresenter {

	private final DispatchAsync dispatcher;
	final SessionManager sessionManager;
	final Win win;
	String corpId="";
	@Inject
	public RegisterHrPresenterImpl(EventBus eventBus,SessionManager sessionManager,
			RegisterHrDisplay display, DispatchAsync dispatcher,Win win) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.sessionManager = sessionManager;
		this.win=win;
	}

	@Override
	public void bind() {
		registerHandler(display.getRegisterHrClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						if (StringUtil.isEmpty(display.getUsername().getValue())) {

							win.alert("用户名不能为空!");
							return;
						}
						if (StringUtil.isEmpty(display.getPassword().getValue())) {
							win.alert("密码不能为空!");
							return;
						}
 
						if (!display.getPassword().getValue().equals(display.getValidatePassword().getValue())) {
							win.alert("密码和确认密码不一致!");
							return;
						}
						if(!StringUtil.isValidEmail(display.getEmail().getValue()))
						{
							win.alert("Email格式不正确,请重新填写Email!");
							return;
						}
						doRegisterHr();
					}
				}));
	}

	protected void doRegisterHr() {

		HrVo vo = new HrVo();
		vo.setName(display.getUsername().getValue());
		vo.setEmail(display.getEmail().getValue());
		vo.setTell(display.getTell().getValue());
		vo.setPassword(display.getPassword().getValue());
		vo.setUsername(display.getUsername().getValue());
		vo.setCorpId(corpId);
		List<UserRoleVo> userRoleVos=new ArrayList<UserRoleVo>();
		
		userRoleVos.add(UserRoleVo.CORP_ADMIN);

		//userRoleVos.add(UserRoleVo.DEPT_MGR);
		
		vo.setUserRoleVos(userRoleVos);
		dispatcher.execute(new RegisterHrRequest(vo),
				new AsyncCallback<RegisterHrResponse>() {
					public void onFailure(Throwable t) {
						Window.alert(t.getMessage());
					}

					@Override
					public void onSuccess(RegisterHrResponse response) {
						win.alert("餐厅管理员创建成功!");
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								CompanyConstants.EDITOR_COMPANY_SEARCH,
								"EDITOR_CompanyList_SEARCH_DO_ID", null);
					}
				});
	}

	@Override
	public void initRegisterCorp(String corpId) {
		this.corpId=corpId;
		
	}

	 

}
