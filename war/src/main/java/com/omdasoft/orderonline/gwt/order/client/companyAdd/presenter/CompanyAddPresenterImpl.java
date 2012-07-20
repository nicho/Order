package com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
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
import com.omdasoft.orderonline.gwt.order.client.view.constant.CssStyleConstants;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;
import com.omdasoft.orderonline.gwt.order.util.XmlUtil_GWT;

public class CompanyAddPresenterImpl extends
		BasePresenter<CompanyAddPresenter.CompanyAddDisplay> implements
		CompanyAddPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	String companyId = null;
	private final BreadCrumbsPresenter breadCrumbs;
	String imageCss=display.getBusinessLicenseImageShow().getElement().getParentElement().getParentElement().getClassName();
	@Inject
	public CompanyAddPresenterImpl(EventBus eventBus, CompanyAddDisplay display,
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
		if(companyId==null){
			display.getBusinessLicenseImageShow().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
			display.getTaxRegistrationCertificateImageShow().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
			display.getOrganizationCodeImageShow().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
			display.getEnterpriseLogoImageShow().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
			display.getEnterpriseLoginLogoImageShow().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
			breadCrumbs.loadChildPage("添加企业信息");
			display.setTitleText("添加企业信息");
			display.setAddBtnValue("创建");
		}else{
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
						if(StringUtil.isEmpty(display.getEnterpriseName()))
						{
							win.alert("请填企业名称!");
							return;
						}
						if(StringUtil.isEmpty(display.getLinkman()))
						{
							win.alert("请填企业联系人!");
							return;
						}
						if(StringUtil.isEmpty(display.getTell()))
						{
							win.alert("请填联系电话!");
							return;
						}
						if(!StringUtil.isValidEmail(display.getEmail())){
							win.alert("邮箱格式不正确");
							return;
						}
						if(StringUtil.isEmpty(display.getCallphone()))
						{
							win.alert("请填手机号!");
							return;
						}
						if(StringUtil.isEmpty(display.getClientController()))
						{
							win.alert("请填客户管理员!");
							return;
						}
						if(StringUtil.isEmpty(display.getClientControllerEmail()))
						{
							win.alert("请填管理员Email!");
							return;
						}
						if(!StringUtil.isValidEmail(display.getClientControllerEmail())){
							win.alert("管理员Email格式不正确");
							return;
						}
						if(StringUtil.isEmpty(display.getEnterpriseRealmName()))
						{
							win.alert("请填企业子域名!");
							return;
						}
						if(StringUtil.isEmpty(display.getHrLoginAddress()))
						{
							win.alert("请填企业HR登录地址!");
							return;
						}
						if(StringUtil.isEmpty(display.getStaffLoginAddress()))
						{
							win.alert("请填企业员工登录地址!");
							return;
						}
						if(StringUtil.isEmpty(display.getBusinessOwner()))
						{
							win.alert("请填业务负责人员!");
							return;
						}
						if(StringUtil.isEmpty(display.getBusinessOwnerEmail()))
						{
							win.alert("请填业务负责人员Email!");
							return;
						}
						if(!StringUtil.isValidEmail(display.getBusinessOwnerEmail())){
							win.alert("业务负责人员Email格式不正确");
							return;
						}
						if(StringUtil.isEmpty(display.getBusinessOwnerTelNum()))
						{
							win.alert("请填联系电话!");
							return;
						}
						
						CompanyAddRequest request = new CompanyAddRequest();
						request.setSession(sessionManager.getSession());
						if(companyId!=null){
							request.setEnterpriseId(companyId);
						}
						request.setSession(sessionManager.getSession());
						request.setEnterpriseName(display.getEnterpriseName());
						request.setAddress(display.getAddress());
						request.setLinkman(display.getLinkman());
						request.setTell(display.getTell());
						request.setEmail(display.getEmail());
						request.setCallphone(display.getCallphone());
						request.setClientController(display.getClientController());
						request.setClientControllerEmail(display.getClientControllerEmail());
						request.setEnterpriseRealmName(display.getEnterpriseRealmName());
						request.setHrLoginAddress(display.getHrLoginAddress());
						request.setStaffLoginAddress(display.getStaffLoginAddress());
						request.setBusinessLicenseImage(display.getBusinessLicenseImage().getValue());
						request.setTaxRegistrationCertificateImage(display.getTaxRegistrationCertificateImage().getValue());
						request.setOrganizationCodeImage(display.getOrganizationCodeImage().getValue());
						request.setEnterpriseLogoImage(display.getEnterpriseLogoImage().getValue());
						request.setEnterpriseLoginLogoImage(display.getEnterpriseLoginLogoImage().getValue());
						request.setBusinessOwner(display.getBusinessOwner());
						request.setBusinessOwnerEmail(display.getBusinessOwnerEmail());
						request.setBusinessOwnerTelNum(display.getBusinessOwnerTelNum());
						request.setBusinessRemark(display.getBusinessRemark());
						
						dispatch.execute(request,
								new AsyncCallback<CompanyAddResponse>() {

									@Override
									public void onFailure(Throwable t) {
										win.alert(t.getMessage());
									}

									@Override
									public void onSuccess(CompanyAddResponse resp) {
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
		
				// 浏览即上传事件--企业营业执照
				registerHandler(display.getBusinessLicenseImageUpload().addChangeHandler(
						new ChangeHandler() {
							@Override
							public void onChange(ChangeEvent arg0) {
								System.out.println("==========="
										+ display.getBusinessLicenseImageUpload());
								display.getBusinessLicenseImageShow().getElement().getParentElement().getParentElement().setClassName(imageCss);
								display.getBusinessLicenseImageShow().setVisible(true);
								display.getBusinessLicenseImageForm().setAction("fileupload");
								display.getBusinessLicenseImageForm().submit();
							}
						}));

				// 上传图片按钮事件--企业营业执照
				registerHandler(display.getBusinessLicenseImageUploadBtn().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								display.getBusinessLicenseImageForm().setAction("fileupload");
								display.getBusinessLicenseImageForm().submit();
							}
						}));

				// 文件上传后回调--企业营业执照
				display.getBusinessLicenseImageForm().addSubmitCompleteHandler(
						new SubmitCompleteHandler() {
							@Override
							public void onSubmitComplete(SubmitCompleteEvent event) {
								String eventResults = event.getResults();
								System.out.println("submitComplete event.getResults:"
										+ eventResults);
								// win.alert(eventResults);

								if (eventResults != null) {
									eventResults = XmlUtil_GWT
											.replaceSpecialStr(eventResults);

									try {
										String result = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<result>", "</result>");
										String info = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<info>", "</info>");

										if ("SUCCESS".equals(result)) {
											display.getBusinessLicenseImage().setValue(info);
											String giftImageUrl = "imageshow?imageName="
													+ info;
											display.getBusinessLicenseImageShow()
													.setUrl(giftImageUrl);
										} else {
											win.alert("上传图片异常<br>" + info);
										}
									} catch (Exception e) {
										e.printStackTrace();
										win.alert("上传图片异常，请重试" + e.getMessage());
										return;
									}
								}
							}
						});
				
				// 浏览即上传事件--税务登记证
				registerHandler(display.getTaxRegistrationCertificateImageUpload().addChangeHandler(
						new ChangeHandler() {
							@Override
							public void onChange(ChangeEvent arg0) {
								System.out.println("==========="
										+ display.getTaxRegistrationCertificateImageUpload());
								display.getTaxRegistrationCertificateImageShow().getElement().getParentElement().getParentElement().setClassName(imageCss);
								display.getTaxRegistrationCertificateImageShow().setVisible(true);
								display.getTaxRegistrationCertificateImageForm().setAction("fileupload");
								display.getTaxRegistrationCertificateImageForm().submit();
							}
						}));

				// 上传图片按钮事件--税务登记证
				registerHandler(display.getTaxRegistrationCertificateImageUploadBtn().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								display.getTaxRegistrationCertificateImageForm().setAction("fileupload");
								display.getTaxRegistrationCertificateImageForm().submit();
							}
						}));

				// 文件上传后回调--税务登记证
				display.getTaxRegistrationCertificateImageForm().addSubmitCompleteHandler(
						new SubmitCompleteHandler() {
							@Override
							public void onSubmitComplete(SubmitCompleteEvent event) {
								String eventResults = event.getResults();
								System.out.println("submitComplete event.getResults:"
										+ eventResults);
								// win.alert(eventResults);

								if (eventResults != null) {
									eventResults = XmlUtil_GWT
											.replaceSpecialStr(eventResults);

									try {
										String result = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<result>", "</result>");
										String info = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<info>", "</info>");

										if ("SUCCESS".equals(result)) {
											display.getTaxRegistrationCertificateImage().setValue(info);
											String giftImageUrl = "imageshow?imageName="
													+ info;
											display.getTaxRegistrationCertificateImageShow()
													.setUrl(giftImageUrl);
										} else {
											win.alert("上传图片异常<br>" + info);
										}
									} catch (Exception e) {
										e.printStackTrace();
										win.alert("上传图片异常，请重试" + e.getMessage());
										return;
									}
								}
							}
						});
				
				// 浏览即上传事件--组织机构代码
				registerHandler(display.getOrganizationCodeImageUpload().addChangeHandler(
						new ChangeHandler() {
							@Override
							public void onChange(ChangeEvent arg0) {
								System.out.println("==========="
										+ display.getOrganizationCodeImageUpload());
								display.getOrganizationCodeImageShow().getElement().getParentElement().getParentElement().setClassName(imageCss);
								display.getOrganizationCodeImageShow().setVisible(true);
								display.getOrganizationCodeImageForm().setAction("fileupload");
								display.getOrganizationCodeImageForm().submit();
							}
						}));

				// 上传图片按钮事件--组织机构代码
				registerHandler(display.getOrganizationCodeImageUploadBtn().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								display.getOrganizationCodeImageForm().setAction("fileupload");
								display.getOrganizationCodeImageForm().submit();
							}
						}));

				// 文件上传后回调--组织机构代码
				display.getOrganizationCodeImageForm().addSubmitCompleteHandler(
						new SubmitCompleteHandler() {
							@Override
							public void onSubmitComplete(SubmitCompleteEvent event) {
								String eventResults = event.getResults();
								System.out.println("submitComplete event.getResults:"
										+ eventResults);
								// win.alert(eventResults);

								if (eventResults != null) {
									eventResults = XmlUtil_GWT
											.replaceSpecialStr(eventResults);

									try {
										String result = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<result>", "</result>");
										String info = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<info>", "</info>");

										if ("SUCCESS".equals(result)) {
											display.getOrganizationCodeImage().setValue(info);
											String giftImageUrl = "imageshow?imageName="
													+ info;
											display.getOrganizationCodeImageShow()
													.setUrl(giftImageUrl);
										} else {
											win.alert("上传图片异常<br>" + info);
										}
									} catch (Exception e) {
										e.printStackTrace();
										win.alert("上传图片异常，请重试" + e.getMessage());
										return;
									}
								}
							}
						});
				
				// 浏览即上传事件--企业Logo
				registerHandler(display.getEnterpriseLogoImageUpload().addChangeHandler(
						new ChangeHandler() {
							@Override
							public void onChange(ChangeEvent arg0) {
								System.out.println("==========="
										+ display.getEnterpriseLogoImageUpload());
								display.getEnterpriseLogoImageShow().getElement().getParentElement().getParentElement().setClassName(imageCss);
								display.getEnterpriseLogoImageShow().setVisible(true);
								display.getEnterpriseLogoImageForm().setAction("fileupload");
								display.getEnterpriseLogoImageForm().submit();
							}
						}));

				// 上传图片按钮事件--企业Logo
				registerHandler(display.getEnterpriseLogoImageUploadBtn().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								display.getEnterpriseLogoImageForm().setAction("fileupload");
								display.getEnterpriseLogoImageForm().submit();
							}
						}));

				// 文件上传后回调--企业Logo
				display.getEnterpriseLogoImageForm().addSubmitCompleteHandler(
						new SubmitCompleteHandler() {
							@Override
							public void onSubmitComplete(SubmitCompleteEvent event) {
								String eventResults = event.getResults();
								System.out.println("submitComplete event.getResults:"
										+ eventResults);
								// win.alert(eventResults);

								if (eventResults != null) {
									eventResults = XmlUtil_GWT
											.replaceSpecialStr(eventResults);

									try {
										String result = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<result>", "</result>");
										String info = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<info>", "</info>");

										if ("SUCCESS".equals(result)) {
											display.getEnterpriseLogoImage().setValue(info);
											String giftImageUrl = "imageshow?imageName="
													+ info;
											display.getEnterpriseLogoImageShow()
													.setUrl(giftImageUrl);
										} else {
											win.alert("上传图片异常<br>" + info);
										}
									} catch (Exception e) {
										e.printStackTrace();
										win.alert("上传图片异常，请重试" + e.getMessage());
										return;
									}
								}
							}
						});
				
				// 浏览即上传事件--企业登录logo图片
				registerHandler(display.getEnterpriseLoginLogoImageUpload().addChangeHandler(
						new ChangeHandler() {
							@Override
							public void onChange(ChangeEvent arg0) {
								System.out.println("==========="
										+ display.getEnterpriseLoginLogoImageUpload());
								display.getEnterpriseLoginLogoImageShow().getElement().getParentElement().getParentElement().setClassName(imageCss);
								display.getEnterpriseLoginLogoImageShow().setVisible(true);
								display.getEnterpriseLoginLogoImageForm().setAction("fileupload");
								display.getEnterpriseLoginLogoImageForm().submit();
							}
						}));

				// 上传图片按钮事件--企业登录logo图片
				registerHandler(display.getEnterpriseLoginLogoImageUploadBtn().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								display.getEnterpriseLoginLogoImageForm().setAction("fileupload");
								display.getEnterpriseLoginLogoImageForm().submit();
							}
						}));

				// 文件上传后回调--企业登录logo图片
				display.getEnterpriseLoginLogoImageForm().addSubmitCompleteHandler(
						new SubmitCompleteHandler() {
							@Override
							public void onSubmitComplete(SubmitCompleteEvent event) {
								String eventResults = event.getResults();
								System.out.println("submitComplete event.getResults:"
										+ eventResults);
								// win.alert(eventResults);

								if (eventResults != null) {
									eventResults = XmlUtil_GWT
											.replaceSpecialStr(eventResults);

									try {
										String result = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<result>", "</result>");
										String info = XmlUtil_GWT.getNormalNodeText(
												eventResults, "<info>", "</info>");

										if ("SUCCESS".equals(result)) {
											display.getEnterpriseLoginLogoImage().setValue(info);
											String giftImageUrl = "imageshow?imageName="
													+ info;
											display.getEnterpriseLoginLogoImageShow()
													.setUrl(giftImageUrl);
										} else {
											win.alert("上传图片异常<br>" + info);
										}
									} catch (Exception e) {
										e.printStackTrace();
										win.alert("上传图片异常，请重试" + e.getMessage());
										return;
									}
								}
							}
						});
		
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
					display.setClientController(enterpriseVo.getClientController());
					display.setClientControllerEmail(enterpriseVo.getClientControllerEmail());
					display.setEnterpriseRealmName(enterpriseVo.getEnterpriseRealmName());
					display.setHrLoginAddress(enterpriseVo.getHrLoginAddress());
					display.setStaffLoginAddress(enterpriseVo.getStaffLoginAddress());
					display.setBusinessLicenseImage(enterpriseVo.getBusinessLicenseImage());
					display.setBusinessLicenseImageShow(enterpriseVo.getBusinessLicenseImage());
					display.setTaxRegistrationCertificateImage(enterpriseVo.getTaxRegistrationCertificateImage());
					display.setTaxRegistrationCertificateImageShow(enterpriseVo.getTaxRegistrationCertificateImage());
					display.setOrganizationCodeImage(enterpriseVo.getOrganizationCodeImage());
					display.setOrganizationCodeImageShow(enterpriseVo.getOrganizationCodeImage());
					display.setEnterpriseLogoImage(enterpriseVo.getEnterpriseLogoImage());
					display.setEnterpriseLogoImageShow(enterpriseVo.getEnterpriseLogoImage());
					display.setEnterpriseLoginLogoImage(enterpriseVo.getEnterpriseLoginLogoImage());
					display.setEnterpriseLoginLogoImageShow(enterpriseVo.getEnterpriseLoginLogoImage());
					display.setBusinessOwner(enterpriseVo.getBusinessOwner());
					display.setBusinessOwnerEmail(enterpriseVo.getBusinessOwnerEmail());
					display.setBusinessOwnerTelNum(enterpriseVo.getBusinessOwnerTelNum());
					display.setBusinessRemark(enterpriseVo.getBusinessRemark());
				}
			}
		});
	}
	
	
	
	@Override
	public void initCompanyUpdate(String companyId) {
		this.companyId = companyId;
	}

}
