package com.omdasoft.orderonline.gwt.order.server.enterprise;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyInitRequest;
import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyInitResponse;
import com.omdasoft.orderonline.gwt.order.client.enterprise.model.EnterpriseVo;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.service.org.CorporationService;

public class CompanyInitActionHandler extends
	BaseActionHandler<CompanyInitRequest, CompanyInitResponse>{
	
	CorporationService corporationService;

	@Inject
	public CompanyInitActionHandler(CorporationService corporationService) {
		this.corporationService = corporationService;
	}

	@Override
	public Class<CompanyInitRequest> getActionType() {
		return CompanyInitRequest.class;
	}

	@Override
	public CompanyInitResponse execute(CompanyInitRequest request,
			ExecutionContext context) throws DispatchException {
		 String corporationId = request.getCompanyId();
		 Corporation corp =  corporationService.findCorporationById(corporationId);
		 if (corp != null) {
			 CompanyInitResponse resp = new CompanyInitResponse();
			 EnterpriseVo vo = new EnterpriseVo();
			 vo.setAddress(corp.getAddress());
			 vo.setCellphone(corp.getCellphone());
			 vo.setCorporation(corp.getCorporationer());//法人
			 vo.setEmailAddress(corp.getEmailAddress());
			 vo.setName(corp.getName());
			 vo.setFax(corp.getFax());
			 vo.setLinkman(corp.getLinkman());
			 vo.setDescription(corp.getDescription());
			 vo.setTell(corp.getTell());
			 vo.setWeb(corp.getWeb());
			 vo.setId(corp.getId());
			 vo.setCid(corp.getCid());
			 vo.setIntegralPrice(corp.getIntegralPrice());
			 vo.setMoneyType(corp.getMoneyType());
			 vo.setMailpwd(corp.getMailpwd());
			 vo.setSmtp(corp.getSmtp());
			 vo.setPeriod(corp.getPeriod());
			 vo.setFirstTime(corp.getFirstTime());
			 
			 vo.setClientController(corp.getClientController());
			 vo.setClientControllerEmail(corp.getClientControllerEmail());
			 vo.setEnterpriseRealmName(corp.getEnterpriseRealmName());
			 vo.setHrLoginAddress(corp.getHrLoginAddress());
			 vo.setStaffLoginAddress(corp.getStaffLoginAddress());
			 vo.setBusinessLicenseImage(corp.getBusinessLicenseImage());
			 vo.setTaxRegistrationCertificateImage(corp.getTaxRegistrationCertificateImage());
			 vo.setOrganizationCodeImage(corp.getOrganizationCodeImage());
			 vo.setEnterpriseLogoImage(corp.getEnterpriseLogoImage());
			 vo.setEnterpriseLoginLogoImage(corp.getEnterpriseLoginLogoImage());
			 vo.setBusinessOwner(corp.getBusinessOwner());
			 vo.setBusinessOwnerEmail(corp.getBusinessOwnerEmail());
			 vo.setBusinessOwnerTelNum(corp.getBusinessOwnerTelNum());
			 vo.setBusinessRemark(corp.getBusinessRemark());
			 
			 resp.setEnterprise(vo);
			 return resp;
		 } else {
		 return null;
		 }
		
	}

	@Override
	public void rollback(CompanyInitRequest arg0,
			CompanyInitResponse arg1, ExecutionContext arg2)
			throws DispatchException {

	}
}
