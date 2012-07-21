package com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.company.plugin.CompanyConstants;
import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyInitRequest;
import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyInitResponse;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.request.CompanyAddRequest;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.request.CompanyAddResponse;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.enterprise.model.EnterpriseVo;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class CompanyAddPresenterImpl extends
		BasePresenter<CompanyAddPresenter.CompanyAddDisplay> implements
		CompanyAddPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	String companyId = null;
	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public CompanyAddPresenterImpl(EventBus eventBus,
			CompanyAddDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager, Win win,
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
		if (companyId == null) {
			breadCrumbs.loadChildPage("添加企业信息");
			display.setTitleText("添加企业信息");
			display.setAddBtnValue("创建");
		} else {
			display.setAddBtnValue("修改");
			breadCrumbs.loadChildPage("修改企业信息");
			display.setTitleText("修改企业信息");
			initCompany();
		}

		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());

		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						if (StringUtil.isEmpty(display.getEnterpriseName())) {
							win.alert("请填企业名称!");
							return;
						}
						if (StringUtil.isEmpty(display.getLinkman())) {
							win.alert("请填企业联系人!");
							return;
						}
						if (StringUtil.isEmpty(display.getTell())) {
							win.alert("请填联系电话!");
							return;
						}
						if (!StringUtil.isValidEmail(display.getEmail())) {
							win.alert("邮箱格式不正确");
							return;
						}
						if (StringUtil.isEmpty(display.getCallphone())) {
							win.alert("请填手机号!");
							return;
						}

						CompanyAddRequest request = new CompanyAddRequest();
						request.setSession(sessionManager.getSession());
						if (companyId != null) {
							request.setEnterpriseId(companyId);
						}
						request.setSession(sessionManager.getSession());
						request.setEnterpriseName(display.getEnterpriseName());
						request.setAddress(display.getAddress());
						request.setLinkman(display.getLinkman());
						request.setTell(display.getTell());
						request.setEmail(display.getEmail());
						request.setCallphone(display.getCallphone());
						request.setCid(display.getCid().getValue());
						dispatch.execute(request,
								new AsyncCallback<CompanyAddResponse>() {

									@Override
									public void onFailure(Throwable t) {
										win.alert(t.getMessage());
									}

									@Override
									public void onSuccess(
											CompanyAddResponse resp) {
										win.alert("保存成功");
										Platform.getInstance()
												.getEditorRegistry()
												.openEditor(
														CompanyConstants.EDITOR_COMPANY_SEARCH,
														"EDITOR_COMPANYLIST_SEARCH_DO_ID",
														null);
									}
								});
					}
				}));

	}

	private void initCompany() {
		CompanyInitRequest req = new CompanyInitRequest(companyId);
		dispatch.execute(req, new AsyncCallback<CompanyInitResponse>() {
			public void onFailure(Throwable caught) {
				win.alert("初始化失败");
			}

			@Override
			public void onSuccess(CompanyInitResponse response) {
				if (response != null) {
					EnterpriseVo enterpriseVo = response.getEnterprise();
					display.setEnterpriseName(enterpriseVo.getName());
					display.setAddress(enterpriseVo.getAddress());
					display.setLinkman(enterpriseVo.getLinkman());
					display.setCallphone(enterpriseVo.getCellphone());
					display.setEmail(enterpriseVo.getEmailAddress());
					display.setTell(enterpriseVo.getTell());
					display.getCid().setValue(enterpriseVo.getCid());
				}
			}
		});
	}

	@Override
	public void initCompanyUpdate(String companyId) {
		this.companyId = companyId;
	}

}
