package com.omdasoft.orderonline.model.vo;

import java.util.Date;
import java.util.List;

import com.omdasoft.orderonline.model.order.OrderSource;
import com.omdasoft.orderonline.model.order.OrderStatus;

public class OrderVo {
	String id;
	String rid;
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
	 * 订单来源
	 */
	private OrderSource orderSource;
	
	public OrderSource getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(OrderSource orderSource) {
		this.orderSource = orderSource;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private List<OrderDishesVo> ordersDishesList;
	private OrderStatus orderStatus;
	
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderDishesVo> getOrdersDishesList() {
		return ordersDishesList;
	}

	public void setOrdersDishesList(List<OrderDishesVo> ordersDishesList) {
		this.ordersDishesList = ordersDishesList;
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

}
