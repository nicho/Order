package com.omdasoft.orderonline.domain.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.domain.person.Person;
import com.omdasoft.orderonline.model.order.CarteState;
import com.omdasoft.orderonline.model.order.OrderSource;
import com.omdasoft.orderonline.model.order.OrderStatus;
import com.omdasoft.orderonline.model.order.OrderType;
import com.omdasoft.orderonline.model.order.RoomState;

@Entity
@XmlRootElement
public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4076124377833291323L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
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
	 * 处理时间
	 */
	private Date handleTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 完成时间
	 */
	private Date finishTime;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 订餐人信息
	 */
	@ManyToOne
	private Person orderPerson;
	/**
	 * 就餐人
	 */
	@ManyToOne
	private Person contactPerson;

	private String city;

	/**
	 * 订单状态
	 */
	private OrderStatus orderStatus;

	/**
	 * 餐饮公司
	 */
	@OneToOne(fetch = FetchType.EAGER)
	private Corporation corporation;

	//分店
	@OneToOne(fetch = FetchType.EAGER)
	private Department department;
	private String  result;
	
	/**
	 * 点菜状态
	 */
	private CarteState carteState;
	/**
	 * 订房状态
	 */
	private RoomState roomState;
	 
	/**
	 * 订单来源
	 */
	private OrderSource orderSource;
	/**
	 * 订单类型
	 */
	private OrderType orderType;
	/**
	 * 订单房号
	 */
	private String orderRoom;
	/**
	 * 排队号
	 */
	private String queuingNumber;
	/**
	 * RID
	 */
	private String rid;
	

	public RoomState getRoomState() {
		return roomState;
	}

	public void setRoomState(RoomState roomState) {
		this.roomState = roomState;
	}

	public String getOrderRoom() {
		return orderRoom;
	}

	public void setOrderRoom(String orderRoom) {
		this.orderRoom = orderRoom;
	}

	public String getQueuingNumber() {
		return queuingNumber;
	}

	public void setQueuingNumber(String queuingNumber) {
		this.queuingNumber = queuingNumber;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public OrderSource getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(OrderSource orderSource) {
		this.orderSource = orderSource;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public CarteState getCarteState() {
		return carteState;
	}

	public void setCarteState(CarteState carteState) {
		this.carteState = carteState;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	private boolean deleted;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Person getOrderPerson() {
		return orderPerson;
	}

	public void setOrderPerson(Person orderPerson) {
		this.orderPerson = orderPerson;
	}

	public Person getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(Person contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

}
