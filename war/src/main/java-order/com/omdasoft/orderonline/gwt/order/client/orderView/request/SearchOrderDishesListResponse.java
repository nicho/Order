package com.omdasoft.orderonline.gwt.order.client.orderView.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.omdasoft.orderonline.gwt.order.client.orderView.model.OrderDishesListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchOrderDishesListResponse implements Result {

	private List<OrderDishesListClient> result;
	private int total;



	public List<OrderDishesListClient> getResult() {
		return result;
	}



	public void setResult(List<OrderDishesListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchOrderDishesListResponse() {

	}
	public SearchOrderDishesListResponse(List<OrderDishesListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
