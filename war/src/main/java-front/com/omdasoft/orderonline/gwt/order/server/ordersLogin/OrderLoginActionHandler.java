package com.omdasoft.orderonline.gwt.order.server.ordersLogin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesClient;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.request.OrderLoginRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.request.OrderLoginResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;
import com.omdasoft.orderonline.model.order.OrderAndDishesModel;
import com.omdasoft.orderonline.service.order.OrderService;
import com.omdasoft.orderonline.service.org.DepartmentService;

public class OrderLoginActionHandler extends
		BaseActionHandler<OrderLoginRequest, OrderLoginResponse> {

	OrderService orderService;
	DepartmentService departmentService;
	
	@Inject
	public OrderLoginActionHandler(OrderService orderService,DepartmentService departmentService)
	{
		this.orderService=orderService;
		this.departmentService=departmentService;
	}
	public OrderLoginActionHandler() {

	}
	@Override
	public Class<OrderLoginRequest> getActionType() {
		return OrderLoginRequest.class;
	}

	@Override
	public OrderLoginResponse execute(OrderLoginRequest action, ExecutionContext context)
			throws DispatchException {
		OrderLoginResponse rep=new OrderLoginResponse();

		OrderAndDishesModel model=orderService.getOrderAndDishesModelByPhone(action.getPhone(),action.getDeptId());
		if(model!=null && model.getOrder()!=null)
		{
			rep.setOrderId(model.getOrder().getId());
			rep.setAmountOfClient(model.getOrder().getAmountOfClient());
			rep.setFavoriteRoom(model.getOrder().getFavoriteRoom());
			rep.setMemo(model.getOrder().getMemo());
			rep.setCity(model.getOrder().getCity());
			if(model.getOrder().getOrderPerson()!=null)
			{
				rep.setOrderPersonName(model.getOrder().getOrderPerson().getName());
				rep.setOrderPersonPhone(model.getOrder().getOrderPerson().getPhone());
			}
			if(model.getOrder().getDepartment()!=null)
			{
				rep.setRestaurantName(model.getOrder().getDepartment().getName());
				rep.setRestaurantId(model.getOrder().getDepartment().getId());
			}
			
			if(model.getOrdersDishesList()!=null && model.getOrdersDishesList().size()>0)
			{
				List<BookingDishesClient> dishesList=new ArrayList<BookingDishesClient>();
				
					for (OrdersDishes dishe:model.getOrdersDishesList()) {
						BookingDishesClient r=new BookingDishesClient();

						if(dishe.getDishes()!=null)
						{
							r.setId(dishe.getDishes().getId());
							r.setName(dishe.getDishes().getName());
							r.setUnitprice(dishe.getDishes().getPrice()+"");
							r.setPrice((dishe.getDishes().getPrice()*dishe.getNumber()) +"");
							
							
				    		  List<String> tasteLt=new ArrayList<String>();
				    		  if(!StringUtil.isEmpty((dishe.getDishes().getTaste())))
				    		  {
				    			  String [] arrtasts=dishe.getDishes().getTaste().split(",");
				    			  for (int i = 0; i < arrtasts.length; i++) {
				    				  tasteLt.add(arrtasts[i]);
								 }
				    		  }
							 r.setTasteList(tasteLt);
						}						
						
						r.setUnit(dishe.getUnit());
						r.setTaste(dishe.getTaste());
						r.setNumber(dishe.getNumber());
						
						dishesList.add(r);
					}
				rep.setBookingDishesList(dishesList);
			}
		}
		else
		{
			Department dept=departmentService.findDepartmentById(action.getDeptId());
			if(dept!=null)
			{
				rep.setCity(dept.getCity());
				rep.setRestaurantId(dept.getId());
				rep.setRestaurantName(dept.getName());
			}
		}
		return rep;
	}

	@Override
	public void rollback(OrderLoginRequest action, OrderLoginResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
