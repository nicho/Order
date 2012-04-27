package com.omdasoft.orderonline.gwt.order.client.server.dictionary;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.dictionary.Dictionary;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.DictionarySaveRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.DictionarySaveResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.service.user.UserService;
import com.omdasoft.orderonline.util.StringUtil;

public class DictionarySaveActionHandler extends
		BaseActionHandler<DictionarySaveRequest, DictionarySaveResponse> {

	UserService userService;
	
	@Inject
	public DictionarySaveActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	public DictionarySaveActionHandler() {

	}
	@Override
	public Class<DictionarySaveRequest> getActionType() {
		return DictionarySaveRequest.class;
	}

	@Override
	public DictionarySaveResponse execute(DictionarySaveRequest action, ExecutionContext context)
			throws DispatchException {
		DictionarySaveResponse rep=new DictionarySaveResponse();
		UserContext u=new UserContext();
		u.setCorporationId(action.getSession().getCorporationId());
		u.setUserId(action.getSession().getToken());

		Dictionary vo=new Dictionary();
		if(!StringUtil.isEmptyString(action.getId()))
			vo.setId(action.getId());
		vo.setName(action.getName());
		vo.setDictionaryType(action.getDictionaryType());
		userService.saveDictionary(u, vo);

		return rep;
	}

	@Override
	public void rollback(DictionarySaveRequest action, DictionarySaveResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
