package com.omdasoft.orderonline.model.order;

import java.io.Serializable;
import java.util.List;

import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.domain.order.OrdersDishes;

public class OrderAndDishesModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Orders order;
	private List<OrdersDishes> ordersDishesList;

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public List<OrdersDishes> getOrdersDishesList() {
		return ordersDishesList;
	}

	public void setOrdersDishesList(List<OrdersDishes> ordersDishesList) {
		this.ordersDishesList = ordersDishesList;
	}

}
