package com.omdasoft.orderonline.domain.rest;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.org.Department;

@Entity
public class InvokeHistory implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -2017666688884913104L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String restApiName;
	private Date invokeTime;
	private String invokeResult;
	@ManyToOne
	private Corporation corporation;
	@ManyToOne
	private Department department;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRestApiName() {
		return restApiName;
	}
	public void setRestApiName(String restApiName) {
		this.restApiName = restApiName;
	}
	public Date getInvokeTime() {
		return invokeTime;
	}
	public void setInvokeTime(Date invokeTime) {
		this.invokeTime = invokeTime;
	}
	public String getInvokeResult() {
		return invokeResult;
	}
	public void setInvokeResult(String invokeResult) {
		this.invokeResult = invokeResult;
	}
	public Corporation getCorporation() {
		return corporation;
	}
	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
