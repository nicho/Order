package com.omdasoft.orderonline.gwt.order.client.registerHr.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.AlertErrorWidget;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.registerHr.model.HrVo;
import com.omdasoft.orderonline.gwt.order.client.registerHr.request.RegisterHrRequest;
import com.omdasoft.orderonline.gwt.order.client.registerHr.request.RegisterHrResponse;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.ui.DialogBox;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class RegisterHrPresenterImpl extends
		BasePresenter<RegisterHrPresenter.RegisterHrDisplay> implements
		RegisterHrPresenter {

	private final DispatchAsync dispatcher;
	private final EltGinjector injector = GWT.create(EltGinjector.class);
	final SessionManager sessionManager;
	@Inject
	public RegisterHrPresenterImpl(EventBus eventBus,SessionManager sessionManager,
			RegisterHrDisplay display, DispatchAsync dispatcher	) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.sessionManager = sessionManager;
	}

	@Override
	public void bind() {
		registerHandler(display.getRegisterHrClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						
						if (StringUtil.isEmpty(display.getName().getValue())) {
							final AlertErrorWidget ae = new AlertErrorWidget();
							final DialogBox dialogBoxae = new DialogBox();
							ae.getOkBtn().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent arg0) {
									dialogBoxae.hide();
								}
							});
							ae.setMessage("名字不能为空!");
							dialogBoxae.setWidget(ae);
							dialogBoxae.setGlassEnabled(true);
							dialogBoxae.setAnimationEnabled(true);
							dialogBoxae.setWidth("350px");
							dialogBoxae.setText("提示");
							dialogBoxae.center();
							dialogBoxae.show();
							
							return;
						}
						if (StringUtil.isEmpty(display.getPassword().getValue())) {
							final AlertErrorWidget ae = new AlertErrorWidget();
							final DialogBox dialogBoxae = new DialogBox();
							ae.getOkBtn().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent arg0) {
									dialogBoxae.hide();
								}
							});
							ae.setMessage("密码不能为空!");
							dialogBoxae.setWidget(ae);
							dialogBoxae.setGlassEnabled(true);
							dialogBoxae.setAnimationEnabled(true);
							dialogBoxae.setWidth("350px");
							dialogBoxae.setText("提示");
							dialogBoxae.center();
							dialogBoxae.show();
							
							return;
						}
						if (StringUtil.isEmpty(display.getUsername().getValue())) {
							final AlertErrorWidget ae = new AlertErrorWidget();
							final DialogBox dialogBoxae = new DialogBox();
							ae.getOkBtn().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent arg0) {
									dialogBoxae.hide();
								}
							});
							ae.setMessage("用户名不能为空!");
							dialogBoxae.setWidget(ae);
							dialogBoxae.setGlassEnabled(true);
							dialogBoxae.setAnimationEnabled(true);
							dialogBoxae.setWidth("350px");
							dialogBoxae.setText("提示");
							dialogBoxae.center();
							dialogBoxae.show();
							
							return;
						}
						if (!display.getPassword().getValue().equals(display.getValidatePassword().getValue())) {
							final AlertErrorWidget ae = new AlertErrorWidget();
							final DialogBox dialogBoxae = new DialogBox();
							ae.getOkBtn().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent arg0) {
									dialogBoxae.hide();
								}
							});
							ae.setMessage("密码和确认密码不一致!");
							dialogBoxae.setWidget(ae);
							dialogBoxae.setGlassEnabled(true);
							dialogBoxae.setAnimationEnabled(true);
							dialogBoxae.setWidth("350px");
							dialogBoxae.setText("提示");
							dialogBoxae.center();
							dialogBoxae.show();
							
							return;
						}
						if (StringUtil.isEmpty(display.getEmail().getValue())) {
							final AlertErrorWidget ae = new AlertErrorWidget();
							final DialogBox dialogBoxae = new DialogBox();
							ae.getOkBtn().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent arg0) {
									dialogBoxae.hide();
								}
							});
							ae.setMessage("电子邮件不能为空!");
							dialogBoxae.setWidget(ae);
							dialogBoxae.setGlassEnabled(true);
							dialogBoxae.setAnimationEnabled(true);
							dialogBoxae.setWidth("350px");
							dialogBoxae.setText("提示");
							dialogBoxae.center();
							dialogBoxae.show();
							return;
						}
						else if(!StringUtil.isValidEmail(display.getEmail().getValue()))
						{
							final AlertErrorWidget ae = new AlertErrorWidget();
							final DialogBox dialogBoxae = new DialogBox();
							ae.getOkBtn().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent arg0) {
									dialogBoxae.hide();
								}
							});
							ae.setMessage("Email格式不正确,请重新填写Email!!");
							dialogBoxae.setWidget(ae);
							dialogBoxae.setGlassEnabled(true);
							dialogBoxae.setAnimationEnabled(true);
							dialogBoxae.setWidth("350px");
							dialogBoxae.setText("提示");
							dialogBoxae.center();
							dialogBoxae.show();
							
							return;
						}
						doRegisterHr();
					}
				}));
	}

	protected void doRegisterHr() {

		HrVo vo = new HrVo();
		vo.setName(display.getName().getValue());
		vo.setEmail(display.getEmail().getValue());
		vo.setTell(display.getTell().getValue());
		vo.setPassword(display.getPassword().getValue());
		vo.setUsername(display.getUsername().getValue());
		
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
						final AlertErrorWidget ae = new AlertErrorWidget();
						final DialogBox dialogBoxae = new DialogBox();
						ae.getOkBtn().addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								dialogBoxae.hide();
							}
						});
						ae.setMessage("管理员注册成功!");
						dialogBoxae.setWidget(ae);
						dialogBoxae.setGlassEnabled(true);
						dialogBoxae.setAnimationEnabled(true);
						dialogBoxae.setWidth("350px");
						dialogBoxae.setText("提示");
						dialogBoxae.center();
						dialogBoxae.show();
						sessionManager.initialize();
					}
				});
	}

	@Override
	public void initRegister(String instanceId) {
	//	this.instanceId=instanceId;
		
	}

}
