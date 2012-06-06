package com.omdasoft.orderonline.gwt.order.server.ordersLogin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesClient;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.request.OrderLoginRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.request.OrderLoginResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.model.order.OrderAndDishesModel;
import com.omdasoft.orderonline.service.order.OrderService;

public class OrderLoginActionHandler extends
		BaseActionHandler<OrderLoginRequest, OrderLoginResponse> {

	OrderService orderService;
	
	@Inject
	public OrderLoginActionHandler(OrderService orderService)
	{
		this.orderService=orderService;
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

		OrderAndDishesModel model=orderService.getOrderAndDishesModelByPhone(action.getPhone());
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
				rep.setRestaurantName(model.getOrder().getDepartment().getName());
			
			if(model.getOrdersDishesList()!=null && model.getOrdersDishesList().size()>0)
			{
				List<BookingDishesClient> dishesList=new ArrayList<BookingDishesClient>();
				
					for (OrdersDishes dishe:model.getOrdersDishesList()) {
						BookingDishesClient r=new BookingDishesClient();
						r.setId(dishe.getId());
						r.setNumber(dishe.getNumber());
						if(dishe.getDishes()!=null)
						{

							r.setName(dishe.getDishes().getName());
							r.setUnitprice(dishe.getDishes().getPrice()+"");
							r.setPrice((dishe.getDishes().getPrice()*dishe.getNumber()) +"");
						}						
						
						r.setUnit(dishe.getUnit());
						r.setTaste(dishe.getTaste());
					
						dishesList.add(r);
					}
				rep.setBookingDishesList(dishesList);
			}
		}
		return rep;
	}

	@Override
	public void rollback(OrderLoginRequest action, OrderLoginResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
