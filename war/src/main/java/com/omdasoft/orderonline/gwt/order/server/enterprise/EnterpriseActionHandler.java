package com.omdasoft.orderonline.gwt.order.server.enterprise;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.gwt.order.client.enterprise.model.EnterpriseVo;
import com.omdasoft.orderonline.gwt.order.client.enterprise.request.EnterpriseRequest;
import com.omdasoft.orderonline.gwt.order.client.enterprise.request.EnterpriseResponse;
import com.omdasoft.orderonline.gwt.order.model.ClientException;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.org.CorporationVo;
import com.omdasoft.orderonline.service.org.CorporationService;
import com.omdasoft.orderonline.service.user.UserService;

public class EnterpriseActionHandler extends
		BaseActionHandler<EnterpriseRequest, EnterpriseResponse> {

	CorporationService corporationService;
	UserService userService;
	@Inject
	public EnterpriseActionHandler(CorporationService corporationService,UserService userService) {
		this.corporationService = corporationService;
		this.userService = userService;
	}

	@Override
	public Class<EnterpriseRequest> getActionType() {
		return EnterpriseRequest.class;
	}

	@Override
	public EnterpriseResponse execute(EnterpriseRequest request,
			ExecutionContext context) throws DispatchException {
		 CorporationVo model = new CorporationVo();
		 EnterpriseVo vo = request.getEnterprise();
		
		 model.setAddress(vo.getAddress());
		 model.setCellphone(vo.getCellphone());
		 model.setCorporation(vo.getCorporation());
		 model.setEmailAddress(vo.getEmailAddress());
		 model.setName(vo.getName());
		 model.setFax(vo.getFax());
		 model.setLinkman(vo.getLinkman());
		 model.setDescription(vo.getDescription());
		 model.setTell(vo.getTell());
		 model.setWeb(vo.getWeb());
		 model.setId(vo.getId());
		 SysUser caller = userService.findUserById(request.getUserSession().getToken());
		 Corporation coporation = corporationService.saveCorporation(caller, model);
		 if (coporation.getId() != null) {
		 EnterpriseResponse resp = new EnterpriseResponse();
		 resp.setToken("注册成功");
		 return resp;
		 } else {
		 throw new ClientException("注册失败!");
		 }
		
	}

	@Override
	public void rollback(EnterpriseRequest action, EnterpriseResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
