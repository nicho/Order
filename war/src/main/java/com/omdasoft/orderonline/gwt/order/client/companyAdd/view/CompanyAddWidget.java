package com.omdasoft.orderonline.gwt.order.client.companyAdd.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter.CompanyAddPresenter.CompanyAddDisplay;

public class CompanyAddWidget extends Composite implements CompanyAddDisplay {
	@UiField
	InlineLabel titleText;
	@UiField
	TextBox enterpriseName;
	@UiField
	TextBox address;
	@UiField
	TextBox linkman;
	@UiField
	TextBox tell;
	@UiField
	TextBox email;
	@UiField
	TextBox callphone;
	@UiField
	TextBox clientController;
	@UiField
	TextBox clientControllerEmail;
	@UiField
	TextBox enterpriseRealmName;
	@UiField
	TextBox hrLoginAddress;
	@UiField
	TextBox staffLoginAddress;
	@UiField
	FormPanel businessLicenseImageForm;
	@UiField
	FileUpload businessLicenseImageUpload;
	@UiField
	TextBox businessLicenseImage;
	@UiField
	Button businessLicenseImageUploadBtn;
	@UiField
	FormPanel taxRegistrationCertificateImageForm;
	@UiField
	FileUpload taxRegistrationCertificateImageUpload;
	@UiField
	TextBox taxRegistrationCertificateImage;
	@UiField
	Button taxRegistrationCertificateImageUploadBtn;
	@UiField
	FormPanel organizationCodeImageForm;
	@UiField
	FileUpload organizationCodeImageUpload;
	@UiField
	TextBox organizationCodeImage;
	@UiField
	Button organizationCodeImageUploadBtn;
	@UiField
	FormPanel enterpriseLogoImageForm;
	@UiField
	FileUpload enterpriseLogoImageUpload;
	@UiField
	TextBox enterpriseLogoImage;
	@UiField
	Button enterpriseLogoImageUploadBtn;
	@UiField
	FormPanel enterpriseLoginLogoImageForm;
	@UiField
	FileUpload enterpriseLoginLogoImageUpload;
	@UiField
	TextBox enterpriseLoginLogoImage;
	@UiField
	Button enterpriseLoginLogoImageUploadBtn;
	@UiField
	TextBox businessOwner;
	@UiField
	TextBox businessOwnerEmail;
	@UiField
	TextBox businessOwnerTelNum;
	@UiField
	TextArea businessRemark;
	@UiField
	Image businessLicenseImageShow;
	@UiField
	Image taxRegistrationCertificateImageShow;
	@UiField
	Image organizationCodeImageShow;
	@UiField
	Image enterpriseLogoImageShow;
	@UiField
	Image enterpriseLoginLogoImageShow;
	@UiField
	Button addBtn;
	@UiField
	Panel breadCrumbs;
	
	private static CompanyAddWidgetUiBinder uiBinder = GWT
			.create(CompanyAddWidgetUiBinder.class);

	interface CompanyAddWidgetUiBinder extends UiBinder<Widget, CompanyAddWidget> {
	}

	public CompanyAddWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return addBtn;
	}
	
	public String getEnterpriseName() {
		return enterpriseName.getValue();
	}

	public void setEnterpriseName(String name) {
		this.enterpriseName.setValue(name);
	}
	
	public String getAddress() {
		return address.getValue();
	}

	public void setAddress(String address) {
		this.address.setValue(address);
	}

	public String getLinkman() {
		return linkman.getValue();
	}

	public void setLinkman(String linkman) {
		this.linkman.setValue(linkman);
	}

	public String getTell() {
		return tell.getValue();
	}

	public void setTell(String tell) {
		this.tell.setValue(tell);
	}

	public String getEmail() {
		return email.getValue();
	}

	public void setEmail(String email) {
		this.email.setValue(email);
	}

	public String getCallphone() {
		return callphone.getValue();
	}

	public void setCallphone(String callphone) {
		this.callphone.setValue(callphone);
	}

	public String getClientController() {
		return clientController.getValue();
	}

	public void setClientController(String clientController) {
		this.clientController.setValue(clientController);
	}

	public String getClientControllerEmail() {
		return clientControllerEmail.getValue();
	}

	public void setClientControllerEmail(String clientControllerEmail) {
		this.clientControllerEmail.setValue(clientControllerEmail);
	}

	public String getEnterpriseRealmName() {
		return enterpriseRealmName.getValue();
	}

	public void setEnterpriseRealmName(String enterpriseRealmName) {
		this.enterpriseRealmName.setValue(enterpriseRealmName);
	}

	public String getHrLoginAddress() {
		return hrLoginAddress.getValue();
	}

	public void setHrLoginAddress(String hrLoginAddress) {
		this.hrLoginAddress.setValue(hrLoginAddress);
	}

	public String getStaffLoginAddress() {
		return staffLoginAddress.getValue();
	}

	public void setStaffLoginAddress(String staffLoginAddress) {
		this.staffLoginAddress.setValue(staffLoginAddress);
	}

	public String getBusinessOwner() {
		return businessOwner.getValue();
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner.setValue(businessOwner);
	}

	public String getBusinessOwnerEmail() {
		return businessOwnerEmail.getValue();
	}

	public void setBusinessOwnerEmail(String businessOwnerEmail) {
		this.businessOwnerEmail.setValue(businessOwnerEmail);
	}

	public String getBusinessOwnerTelNum() {
		return businessOwnerTelNum.getValue();
	}

	public void setBusinessOwnerTelNum(String businessOwnerTelNum) {
		this.businessOwnerTelNum.setValue(businessOwnerTelNum);
	}

	public String getBusinessRemark() {
		return businessRemark.getValue();
	}

	public void setBusinessRemark(String businessRemark) {
		this.businessRemark.setValue(businessRemark);
	}
	
	@Override
	public FormPanel getBusinessLicenseImageForm() {
		return this.businessLicenseImageForm;
	}

	@Override
	public FileUpload getBusinessLicenseImageUpload() {
		return this.businessLicenseImageUpload;
	}
	
	@Override
	public TextBox getBusinessLicenseImage() {
		return this.businessLicenseImage;
	}
	
	@Override
	public void setBusinessLicenseImage(String businessLicenseImage) {
		this.businessLicenseImage.setValue(businessLicenseImage);
	}
	
	@Override
	public FormPanel getTaxRegistrationCertificateImageForm() {
		return this.taxRegistrationCertificateImageForm;
	}

	@Override
	public FileUpload getTaxRegistrationCertificateImageUpload() {
		return this.taxRegistrationCertificateImageUpload;
	}
	
	@Override
	public TextBox getTaxRegistrationCertificateImage() {
		return this.taxRegistrationCertificateImage;
	}
	
	@Override
	public void setTaxRegistrationCertificateImage(String taxRegistrationCertificateImage) {
		this.taxRegistrationCertificateImage.setValue(taxRegistrationCertificateImage);
	}
	
	@Override
	public FormPanel getOrganizationCodeImageForm() {
		return this.organizationCodeImageForm;
	}

	@Override
	public FileUpload getOrganizationCodeImageUpload() {
		return this.organizationCodeImageUpload;
	}
	
	@Override
	public TextBox getOrganizationCodeImage() {
		return this.organizationCodeImage;
	}
	
	@Override
	public void setOrganizationCodeImage(String organizationCodeImage) {
		this.organizationCodeImage.setValue(organizationCodeImage);
	}
	
	@Override
	public FormPanel getEnterpriseLogoImageForm() {
		return this.enterpriseLogoImageForm;
	}

	@Override
	public FileUpload getEnterpriseLogoImageUpload() {
		return this.enterpriseLogoImageUpload;
	}
	
	@Override
	public TextBox getEnterpriseLogoImage() {
		return this.enterpriseLogoImage;
	}
	
	@Override
	public void setEnterpriseLogoImage(String enterpriseLogoImage) {
		this.enterpriseLogoImage.setValue(enterpriseLogoImage);
	}
	
	@Override
	public FormPanel getEnterpriseLoginLogoImageForm() {
		return this.enterpriseLoginLogoImageForm;
	}

	@Override
	public FileUpload getEnterpriseLoginLogoImageUpload() {
		return this.enterpriseLoginLogoImageUpload;
	}
	
	@Override
	public TextBox getEnterpriseLoginLogoImage() {
		return this.enterpriseLoginLogoImage;
	}
	
	@Override
	public void setEnterpriseLoginLogoImage(String enterpriseLoginLogoImage) {
		this.enterpriseLoginLogoImage.setValue(enterpriseLoginLogoImage);
	}
	
	public Image getBusinessLicenseImageShow(){
		return this.businessLicenseImageShow;
	}
	
	public void setBusinessLicenseImageShow(String text){
		this.businessLicenseImageShow.setUrl("imageshow?imageName=" + text);
		businessLicenseImageShow.setVisible(true);
	}
	
	public Image getTaxRegistrationCertificateImageShow(){
		return this.taxRegistrationCertificateImageShow;
	}
	
	public void setTaxRegistrationCertificateImageShow(String text){
		this.taxRegistrationCertificateImageShow.setUrl("imageshow?imageName=" + text);
		taxRegistrationCertificateImageShow.setVisible(true);
	}
	public Image getOrganizationCodeImageShow(){
		return this.organizationCodeImageShow;
	}
	
	public void setOrganizationCodeImageShow(String text){
		this.organizationCodeImageShow.setUrl("imageshow?imageName=" + text);
		organizationCodeImageShow.setVisible(true);
	}
	public Image getEnterpriseLogoImageShow(){
		return this.enterpriseLogoImageShow;
	}
	
	public void setEnterpriseLogoImageShow(String text){
		this.enterpriseLogoImageShow.setUrl("imageshow?imageName=" + text);
		enterpriseLogoImageShow.setVisible(true);
	}
	public Image getEnterpriseLoginLogoImageShow(){
		return this.enterpriseLoginLogoImageShow;
	}
	
	public void setEnterpriseLoginLogoImageShow(String text){
		this.enterpriseLoginLogoImageShow.setUrl("imageshow?imageName=" + text);
		enterpriseLoginLogoImageShow.setVisible(true);
	}
	
	@Override
	public void setTitleText(String text) {
		titleText.setText(text);
	}

	@Override
	public HasClickHandlers getBusinessLicenseImageUploadBtn() {
		return this.businessLicenseImageUploadBtn;
	}

	@Override
	public HasClickHandlers getTaxRegistrationCertificateImageUploadBtn() {
		return this.taxRegistrationCertificateImageUploadBtn;
	}

	@Override
	public HasClickHandlers getOrganizationCodeImageUploadBtn() {
		return this.organizationCodeImageUploadBtn;
	}

	@Override
	public HasClickHandlers getEnterpriseLogoImageUploadBtn() {
		return this.enterpriseLogoImageUploadBtn;
	}

	@Override
	public HasClickHandlers getEnterpriseLoginLogoImageUploadBtn() {
		return this.enterpriseLoginLogoImageUploadBtn;
	}

	@Override
	public void setAddBtnValue(String text) {
		this.addBtn.setText(text);
	}
}
