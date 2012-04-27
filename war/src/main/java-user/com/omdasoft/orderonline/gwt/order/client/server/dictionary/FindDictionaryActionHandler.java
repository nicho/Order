package com.omdasoft.orderonline.gwt.order.client.server.dictionary;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.dictionary.Dictionary;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.FindDictionaryRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.FindDictionaryResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.service.user.UserService;

public class FindDictionaryActionHandler extends
		BaseActionHandler<FindDictionaryRequest, FindDictionaryResponse> {

	UserService userService;
	
	@Inject
	public FindDictionaryActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	public FindDictionaryActionHandler() {

	}
	@Override
	public Class<FindDictionaryRequest> getActionType() {
		return FindDictionaryRequest.class;
	}

	@Override
	public FindDictionaryResponse execute(FindDictionaryRequest action, ExecutionContext context)
			throws DispatchException {
		FindDictionaryResponse rep=new FindDictionaryResponse();

		Dictionary dictionary= userService.findDictionaryById(action.getDictionaryId());
		rep.setName(dictionary.getName());

		return rep;
	}

	@Override
	public void rollback(FindDictionaryRequest action, FindDictionaryResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
