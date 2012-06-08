package com.omdasoft.orderonline.gwt.order.client.ordersLogin.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class OrderLoginResponse implements Result {
	/**
	 * 订单ID
	 */
	private String orderId;

	/**
	 * 菜单列表
	 */
	private List<BookingDishesClient> bookingDishesList;

	/**
	 * 就餐人数
	 */
	private int amountOfClient;
	/**
	 * 包间要求
	 */
	private int favoriteRoom;

	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 订餐人信息
	 */
	private String orderPersonName;

	private String orderPersonPhone;

	/**
	 * 餐厅名称
	 */
	private String restaurantName;
	private String restaurantId;
	private String city;
	

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAmountOfClient() {
		return amountOfClient;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setAmountOfClient(int amountOfClient) {
		this.amountOfClient = amountOfClient;
	}

	public int getFavoriteRoom() {
		return favoriteRoom;
	}

	public void setFavoriteRoom(int favoriteRoom) {
		this.favoriteRoom = favoriteRoom;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOrderPersonName() {
		return orderPersonName;
	}

	public void setOrderPersonName(String orderPersonName) {
		this.orderPersonName = orderPersonName;
	}

	public String getOrderPersonPhone() {
		return orderPersonPhone;
	}

	public void setOrderPersonPhone(String orderPersonPhone) {
		this.orderPersonPhone = orderPersonPhone;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<BookingDishesClient> getBookingDishesList() {
		return bookingDishesList;
	}

	public void setBookingDishesList(List<BookingDishesClient> bookingDishesList) {
		this.bookingDishesList = bookingDishesList;
	}

	public OrderLoginResponse() {

	}

}
