package com.omdasoft.orderonline.model.order;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class DishesReturnModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String flag;
	private String exception_code;
	private String exception_msg;
	private List<MyDishesModel> data;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getException_code() {
		return exception_code;
	}
	public void setException_code(String exception_code) {
		this.exception_code = exception_code;
	}
	public String getException_msg() {
		return exception_msg;
	}
	public void setException_msg(String exception_msg) {
		this.exception_msg = exception_msg;
	}
	public List<MyDishesModel> getData() {
		return data;
	}
	public void setData(List<MyDishesModel> data) {
		this.data = data;
	}



}
