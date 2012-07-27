package com.omdasoft.orderonline.gwt.order.client.orderList.model;

import java.io.Serializable;
import java.util.Date;

import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;

public class OrderListClient implements Serializable, Comparable<OrderListClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;
	private String indexNo;
	private String id;
	private OrderStatus orderStatus;
	private Date processingTime;
	private Date lastUpdateTime;
	private Date completeTime;
	private String result;
	private String roomState;

	public String getRoomState() {
		return roomState;
	}
	public void setRoomState(String roomState) {
		this.roomState = roomState;
	}
	public String getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * 编号
	 */
	private String code;
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

	/**
	 * 就餐人
	 */
	private String contactPersonName;
	private String contactPersonPhone;

	private String city;

	/**
	 * 公司
	 */
	private String corporationId;
	/**
	 * 餐厅
	 */
	private String restaurantId;
	
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
	public OrderListClient() {

	}
	@Override
	public int compareTo(OrderListClient o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(Date processingTime) {
		this.processingTime = processingTime;
	}



}
