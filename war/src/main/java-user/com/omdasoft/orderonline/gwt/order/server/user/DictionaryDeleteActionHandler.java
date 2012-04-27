package com.omdasoft.orderonline.gwt.order.server.user;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.DictionaryDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.DictionaryDeleteResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.user.UserService;

public class DictionaryDeleteActionHandler extends
		BaseActionHandler<DictionaryDeleteRequest, DictionaryDeleteResponse> {

	UserService userService;
	
	@Inject
	public DictionaryDeleteActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	public DictionaryDeleteActionHandler() {

	}
	@Override
	public Class<DictionaryDeleteRequest> getActionType() {
		return DictionaryDeleteRequest.class;
	}

	@Override
	public DictionaryDeleteResponse execute(DictionaryDeleteRequest action, ExecutionContext context)
			throws DispatchException {
		DictionaryDeleteResponse rep=new DictionaryDeleteResponse();
		UserContext u=new UserContext();
		u.setUserId(action.getSession().getToken());
		
		rep.setTotal(userService.deleteDictionary(u, action.getId()));
		
		return rep;
	}

	@Override
	public void rollback(DictionaryDeleteRequest action, DictionaryDeleteResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
