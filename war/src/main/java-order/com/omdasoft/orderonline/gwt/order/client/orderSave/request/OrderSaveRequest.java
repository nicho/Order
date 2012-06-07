/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderSave.request;

import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesClient;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesList;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class OrderSaveRequest implements Action<OrderSaveResponse> {

	private String id;
	private String dishesOrRoomFal;

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
	private String restaurantName;
	private OrderStatus orderStatus;

	public String getDishesOrRoomFal() {
		return dishesOrRoomFal;
	}

	public void setDishesOrRoomFal(String dishesOrRoomFal) {
		this.dishesOrRoomFal = dishesOrRoomFal;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	private List<BookingDishesList> bookingDishesList;
	private List<BookingDishesClient> bookingDishesListClient ;

	public List<BookingDishesClient> getBookingDishesListClient() {
		return bookingDishesListClient;
	}

	public void setBookingDishesListClient(
			List<BookingDishesClient> bookingDishesListClient) {
		this.bookingDishesListClient = bookingDishesListClient;
	}

	public List<BookingDishesList> getBookingDishesList() {
		return bookingDishesList;
	}

	public void setBookingDishesList(List<BookingDishesList> bookingDishesList) {
		this.bookingDishesList = bookingDishesList;
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

	public OrderSaveRequest() {
	}

}
