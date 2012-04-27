package com.omdasoft.orderonline.gwt.order.server.enterprise;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.gwt.order.client.enterprise.model.EnterpriseVo;
import com.omdasoft.orderonline.gwt.order.client.enterprise.request.EnterpriseInitRequest;
import com.omdasoft.orderonline.gwt.order.client.enterprise.request.EnterpriseInitResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.service.org.CorporationService;

public class EnterpriseInitActionHandler extends
		BaseActionHandler<EnterpriseInitRequest, EnterpriseInitResponse> {

	CorporationService corporationService;

	@Inject
	public EnterpriseInitActionHandler(CorporationService corporationService) {
		this.corporationService = corporationService;
	}

	@Override
	public Class<EnterpriseInitRequest> getActionType() {
		return EnterpriseInitRequest.class;
	}

	@Override
	public EnterpriseInitResponse execute(EnterpriseInitRequest request,
			ExecutionContext context) throws DispatchException {
		 String corporationId = request.getUserSession().getCorporationId();
		 Corporation corp =  corporationService.findCorporationById(corporationId);
		 if (corp != null) {
		 EnterpriseInitResponse resp = new EnterpriseInitResponse();
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
		 
		 vo.setIntegralPrice(corp.getIntegralPrice());
		 vo.setMoneyType(corp.getMoneyType());
		
		 vo.setPeriod(corp.getPeriod());
		 vo.setFirstTime(corp.getFirstTime());
		 
//		 System.out.println(EnterpriseInitActionHandler.class.getName()+"----"+corp.getPeriod()+"--"+corp.getFirstTime());
		 
		 resp.setEnterprise(vo);
		 return resp;
		 } else {
		 return null;
		 }
		
	}

	@Override
	public void rollback(EnterpriseInitRequest arg0,
			EnterpriseInitResponse arg1, ExecutionContext arg2)
			throws DispatchException {
		// TODO Auto-generated method stub

	}

}
