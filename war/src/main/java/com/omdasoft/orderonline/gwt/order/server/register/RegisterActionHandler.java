package com.omdasoft.orderonline.gwt.order.server.register;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.org.OrgInit;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.gwt.order.client.enterprise.model.EnterpriseVo;
import com.omdasoft.orderonline.gwt.order.client.register.request.RegisterRequest;
import com.omdasoft.orderonline.gwt.order.client.register.request.RegisterResponse;
import com.omdasoft.orderonline.gwt.order.model.ClientException;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.org.CorporationVo;
import com.omdasoft.orderonline.service.org.CorporationService;
import com.omdasoft.orderonline.service.org.OrgInitService;
import com.omdasoft.orderonline.service.user.UserService;

public class RegisterActionHandler extends	BaseActionHandler<RegisterRequest, RegisterResponse> {

	CorporationService corporationService;
	UserService userService;
	OrgInitService orgInitSerivce;
	@Inject
	public RegisterActionHandler(CorporationService corporationService,UserService userService,OrgInitService orgInitSerivce) {
		this.corporationService = corporationService;
		this.userService = userService;
		this.orgInitSerivce = orgInitSerivce;
	}

	@Override
	public Class<RegisterRequest> getActionType() {
		return RegisterRequest.class;
	}

	@Override
	public RegisterResponse execute(RegisterRequest request,
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
		 SysUser caller = null;
		 Corporation coporation = corporationService.saveCorporation(caller, model);
		 OrgInit init = new OrgInit();
		  init.setCorpInit(1);
		  init.setHrInit(0);
		  init.setCorpId(coporation.getId());
		  orgInitSerivce.save(init);
		 if (coporation.getId() != null) {
		 RegisterResponse resp = new RegisterResponse();
		 resp.setToken("注册成功");
		 resp.setCorpId(coporation.getId());
		 return resp;
		 } else {
		 throw new ClientException("注册失败!");
		 }
		
	}

	@Override
	public void rollback(RegisterRequest action, RegisterResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
