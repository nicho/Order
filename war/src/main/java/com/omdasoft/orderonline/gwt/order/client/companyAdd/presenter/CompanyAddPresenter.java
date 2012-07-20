package com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface CompanyAddPresenter extends
		Presenter<CompanyAddPresenter.CompanyAddDisplay> {

	public void initCompanyUpdate(String companyId);

	public static interface CompanyAddDisplay extends Display {
		HasClickHandlers getAddBtnClickHandlers();
		String getEnterpriseName();
    	void setEnterpriseName(String name); 
    	String getAddress();
		void setAddress(String address);
        String  getLinkman();
        void  setLinkman(String man);
        String getTell();
		void setTell(String tell);
		String getEmail();
		void setEmail(String email);
		String getCallphone();
		void setCallphone(String callphone);
		String getClientController();
		void setClientController(String clientController);
		String getClientControllerEmail();
		void setClientControllerEmail(String clientControllerEmail);
		String getEnterpriseRealmName();
		void setEnterpriseRealmName(String enterpriseRealmName);
		String getHrLoginAddress();
		void setHrLoginAddress(String hrLoginAddress);
		String getStaffLoginAddress();
		void setStaffLoginAddress(String staffLoginAddress);
		
		FormPanel getBusinessLicenseImageForm();
		FileUpload getBusinessLicenseImageUpload();
		TextBox getBusinessLicenseImage();
		void setBusinessLicenseImage(String businessLicenseImage);
		Image getBusinessLicenseImageShow();
		void setBusinessLicenseImageShow(String text);
		HasClickHandlers getBusinessLicenseImageUploadBtn();
		
		FormPanel getTaxRegistrationCertificateImageForm();
		FileUpload getTaxRegistrationCertificateImageUpload();
		TextBox getTaxRegistrationCertificateImage();
		void setTaxRegistrationCertificateImage(String taxRegistrationCertificateImage);
		Image getTaxRegistrationCertificateImageShow();
		void setTaxRegistrationCertificateImageShow(String text);
		HasClickHandlers getTaxRegistrationCertificateImageUploadBtn();
		
		FormPanel getOrganizationCodeImageForm();
		FileUpload getOrganizationCodeImageUpload();
		TextBox getOrganizationCodeImage();
		void setOrganizationCodeImage(String organizationCodeImage);
		Image getOrganizationCodeImageShow();
		void setOrganizationCodeImageShow(String text);
		HasClickHandlers getOrganizationCodeImageUploadBtn();
		
		FormPanel getEnterpriseLogoImageForm();
		FileUpload getEnterpriseLogoImageUpload();
		TextBox getEnterpriseLogoImage();
		void setEnterpriseLogoImage(String enterpriseLogoImage);
		Image getEnterpriseLogoImageShow();
		void setEnterpriseLogoImageShow(String text);
		HasClickHandlers getEnterpriseLogoImageUploadBtn();
		
		FormPanel getEnterpriseLoginLogoImageForm();
		FileUpload getEnterpriseLoginLogoImageUpload();
		TextBox getEnterpriseLoginLogoImage();
		void setEnterpriseLoginLogoImage(String enterpriseLoginLogoImage);
		Image getEnterpriseLoginLogoImageShow();
		void setEnterpriseLoginLogoImageShow(String text);
		HasClickHandlers getEnterpriseLoginLogoImageUploadBtn();
		
		String getBusinessOwner();
		void setBusinessOwner(String businessOwner);
		String getBusinessOwnerEmail();
		void setBusinessOwnerEmail(String businessOwnerEmail);
		String getBusinessOwnerTelNum();
		void setBusinessOwnerTelNum(String businessOwnerTelNum);
		String getBusinessRemark();
		void setBusinessRemark(String businessRemark);
		
		void setAddBtnValue(String text);
		
		void setBreadCrumbs(Widget breadCrumbs);
		void setTitleText(String text);
	}
}
