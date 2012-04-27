package com.omdasoft.orderonline.gwt.order.client.server.order;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.gwt.order.client.orderSave.request.CityInitRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.CityInitResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.service.org.DepartmentService;

public class CityInitActionHandler extends
		BaseActionHandler<CityInitRequest, CityInitResponse> {

	DepartmentService departmentService;
	
	@Inject
	public CityInitActionHandler(DepartmentService departmentService)
	{
		this.departmentService=departmentService;
	}
	public CityInitActionHandler() {

	}
	@Override
	public Class<CityInitRequest> getActionType() {
		return CityInitRequest.class;
	}

	@Override
	public CityInitResponse execute(CityInitRequest action, ExecutionContext context)
			throws DispatchException {
		CityInitResponse rep=new CityInitResponse();

		rep.setDeptName(departmentService.getDepartmentByCityName(action.getCorpId(),action.getCityName()));
		
		return rep;
	}

	@Override
	public void rollback(CityInitRequest action, CityInitResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
