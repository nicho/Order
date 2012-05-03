/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderList.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;
import com.omdasoft.orderonline.gwt.order.model.SortingDetailClient;

/**
 * @author nicho
 * @since 2012年2月14日 11:29:59
 */
public class OrderListCriteria implements IsSerializable {


	public static enum OrderStatus {

		UNHANDLED("待处理"),

		SUCCESS("成功"), FAILURE("失败"), NOTCONSUMPR("未消费"), HASCONSUMER("已消费"),HASCANCEL("已取消");

		private final String displayName;

		OrderStatus(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}
	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;
	private String phone;
	private String phoneorName;
	private OrderStatus status;
	private UserSession session;
	



	public String getPhoneorName() {
		return phoneorName;
	}

	public void setPhoneorName(String phoneorName) {
		this.phoneorName = phoneorName;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public PaginationDetailClient getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDetailClient pagination) {
		this.pagination = pagination;
	}

	public SortingDetailClient getSorting() {
		return sorting;
	}

	public void setSorting(SortingDetailClient sorting) {
		this.sorting = sorting;
	}


}
