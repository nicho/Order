package com.omdasoft.orderonline.gwt.order.client.orderSave.request;

import java.util.Date;

import net.customware.gwt.dispatch.shared.Result;

import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class FindOrderResponse implements Result {

	/**
	 * 就餐时间
	 */
	private String reserveTimeDate;
	private String reserveTimeDateH;
	private String reserveTimeDateS;
	/**
	 * 就餐人数
	 */
	private int amountOfClient;
	/**
	 * 包间要求
	 */
	private int favoriteRoom;
	/**
	 * 下单时间
	 */
	private Date placeOrderTime;

	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 订餐人信息
	 */
	private String orderPersonName;
	private String orderPersonPhone;
	private String orderPersonSex;
	/**
	 * 就餐人
	 */
	private String contactPersonName;
	private String contactPersonPhone;
	private String contactPersonSex;
	private String city;

	/**
	 * 公司
	 */
	private String corporationId;
	/**
	 * 餐厅
	 */
	private String restaurantId;
	
	/**
	 * 餐厅名称
	 */
	private String restaurantName;
	private String result;
	private OrderStatus status;
	
	public OrderStatus getStatus() {
		return status;
	}


	public void setStatus(OrderStatus status) {
		this.status = status;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getRestaurantName() {
		return restaurantName;
	}


	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}


	public String getOrderPersonSex() {
		return orderPersonSex;
	}


	public void setOrderPersonSex(String orderPersonSex) {
		this.orderPersonSex = orderPersonSex;
	}


	public String getContactPersonSex() {
		return contactPersonSex;
	}


	public void setContactPersonSex(String contactPersonSex) {
		this.contactPersonSex = contactPersonSex;
	}


	public String getReserveTimeDate() {
		return reserveTimeDate;
	}


	public void setReserveTimeDate(String reserveTimeDate) {
		this.reserveTimeDate = reserveTimeDate;
	}


	public String getReserveTimeDateH() {
		return reserveTimeDateH;
	}


	public void setReserveTimeDateH(String reserveTimeDateH) {
		this.reserveTimeDateH = reserveTimeDateH;
	}


	public String getReserveTimeDateS() {
		return reserveTimeDateS;
	}


	public void setReserveTimeDateS(String reserveTimeDateS) {
		this.reserveTimeDateS = reserveTimeDateS;
	}


	public int getAmountOfClient() {
		return amountOfClient;
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


	public Date getPlaceOrderTime() {
		return placeOrderTime;
	}


	public void setPlaceOrderTime(Date placeOrderTime) {
		this.placeOrderTime = placeOrderTime;
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


	public String getContactPersonName() {
		return contactPersonName;
	}


	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}


	public String getContactPersonPhone() {
		return contactPersonPhone;
	}


	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCorporationId() {
		return corporationId;
	}


	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}


	public String getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}


	public FindOrderResponse() {

	}

	

}
