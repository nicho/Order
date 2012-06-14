package com.omdasoft.orderonline.model.order;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class OrderModel   implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String rid;
	private String code;
	private String reserveTimeDate;
	private int amountOfClient;
	private int favoriteRoom;
	private String placeOrderTime;
	private int orderStatus;
	private List<DishesModel> dishesList;
	private String memo;
	private String orderPersonPhone;
	private String orderPersonName;
	private String orderPersonSex;
	private String contactPersonPhone;
	private String contactPersonName;
	private String contactPersonSex;
	

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getOrderPersonPhone() {
		return orderPersonPhone;
	}

	public void setOrderPersonPhone(String orderPersonPhone) {
		this.orderPersonPhone = orderPersonPhone;
	}

	public String getOrderPersonName() {
		return orderPersonName;
	}

	public void setOrderPersonName(String orderPersonName) {
		this.orderPersonName = orderPersonName;
	}

	public String getOrderPersonSex() {
		return orderPersonSex;
	}

	public void setOrderPersonSex(String orderPersonSex) {
		this.orderPersonSex = orderPersonSex;
	}

	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonSex() {
		return contactPersonSex;
	}

	public void setContactPersonSex(String contactPersonSex) {
		this.contactPersonSex = contactPersonSex;
	}

	public List<DishesModel> getDishesList() {
		return dishesList;
	}

	public void setDishesList(List<DishesModel> dishesList) {
		this.dishesList = dishesList;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReserveTimeDate() {
		return reserveTimeDate;
	}

	public void setReserveTimeDate(String reserveTimeDate) {
		this.reserveTimeDate = reserveTimeDate;
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



	public String getPlaceOrderTime() {
		return placeOrderTime;
	}

	public void setPlaceOrderTime(String placeOrderTime) {
		this.placeOrderTime = placeOrderTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
