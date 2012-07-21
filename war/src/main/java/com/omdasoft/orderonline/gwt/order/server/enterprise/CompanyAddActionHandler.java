package com.omdasoft.orderonline.gwt.order.server.enterprise;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.request.CompanyAddRequest;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.request.CompanyAddResponse;
import com.omdasoft.orderonline.gwt.order.model.ClientException;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.server.logger.InjectLogger;
import com.omdasoft.orderonline.model.org.CorporationVo;
import com.omdasoft.orderonline.service.org.CorporationService;
import com.omdasoft.orderonline.service.user.UserService;

/**
 * The handler to correspond the CompanyAddRequest.
 * 
 * @author sunny
 * @since 2012年6月15日 
 */
public class CompanyAddActionHandler extends
		BaseActionHandler<CompanyAddRequest, CompanyAddResponse>{
	@InjectLogger
	Logger logger;

	CorporationService corporationService;
	UserService userService;

	@Inject
	public CompanyAddActionHandler(CorporationService corporationService,UserService userService) {
		this.corporationService = corporationService;
		this.userService=userService;
	}

	@Override
	public CompanyAddResponse execute(CompanyAddRequest request,
			ExecutionContext response) throws DispatchException {
		CorporationVo model = new CorporationVo();
		if(request.getEnterpriseId()!=null){
			model.setId(request.getEnterpriseId());
		}
		model.setName(request.getEnterpriseName());
		model.setAddress(request.getAddress());
		model.setLinkman(request.getLinkman());
		model.setTell(request.getTell());
		model.setEmailAddress(request.getEmail());
		model.setCellphone(request.getCallphone());
		model.setClientController(request.getClientController());
		model.setClientControllerEmail(request.getClientControllerEmail());
		model.setEnterpriseRealmName(request.getEnterpriseRealmName());
		model.setHrLoginAddress(request.getHrLoginAddress());
		model.setStaffLoginAddress(request.getStaffLoginAddress());
		model.setBusinessLicenseImage(request.getBusinessLicenseImage());
		model.setTaxRegistrationCertificateImage(request.getTaxRegistrationCertificateImage());
		model.setOrganizationCodeImage(request.getOrganizationCodeImage());
		model.setEnterpriseLogoImage(request.getEnterpriseLogoImage());
		model.setEnterpriseLoginLogoImage(request.getEnterpriseLoginLogoImage());
		model.setBusinessOwner(request.getBusinessOwner());
		model.setBusinessOwnerEmail(request.getBusinessOwnerEmail());
		model.setBusinessOwnerTelNum(request.getBusinessOwnerTelNum());
		model.setBusinessRemark(request.getBusinessRemark());
		model.setCid(request.getCid());
		SysUser caller = userService.findUserById(request.getSession().getToken());
		 Corporation coporation = corporationService.saveCorporation(caller, model);
		 if (coporation.getId() != null) {
			 CompanyAddResponse resp = new CompanyAddResponse();
		 if(request.getEnterpriseId()!=null) {
			 resp.setToken("修改成功");
		 }else{
			 resp.setToken("注册成功");
		 }
		 
		 return resp;
		 } else {
			 if(request.getEnterpriseId()!=null) {
				 throw new ClientException("修改失败!");
			 }else{
				 throw new ClientException("注册失败!");
			 }
		 }
	}

	@Override
	public Class<CompanyAddRequest> getActionType() {
		return CompanyAddRequest.class;
	}

	@Override
	public void rollback(CompanyAddRequest request,
			CompanyAddResponse response, ExecutionContext context)
			throws DispatchException {
	}
}
