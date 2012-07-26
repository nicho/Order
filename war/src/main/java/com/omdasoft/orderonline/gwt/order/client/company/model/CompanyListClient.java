package com.omdasoft.orderonline.gwt.order.client.company.model;

import java.io.Serializable;
import java.util.Date;

public class CompanyListClient implements Serializable, Comparable<CompanyListClient>{
	
	private static final long serialVersionUID = 4934837755724323679L;
	private String id;
	private String cid;
	private String companyNo;			//序号
	private String name;       	  		//企业名称
	private String companyAccountAddress;//企业帐号地址
	private String address;       	//企业地址
	private String linkman;       	//联系人
	private double balance;			//可用积分
	private double rechargeTotal;	//充值总积分
	private Date crearteAt;			//创建时间
	private Integer isCreateHrAccount; //是否生成公司hr管理员
	private String staffName; 
	private String staffId; 
	
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Integer getIsCreateHrAccount() {
		return isCreateHrAccount;
	}

	public void setIsCreateHrAccount(Integer isCreateHrAccount) {
		this.isCreateHrAccount = isCreateHrAccount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyNo() {
		return companyNo;
	}


	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCompanyAccountAddress() {
		return companyAccountAddress;
	}


	public void setCompanyAccountAddress(String companyAccountAddress) {
		this.companyAccountAddress = companyAccountAddress;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getLinkman() {
		return linkman;
	}


	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public double getRechargeTotal() {
		return rechargeTotal;
	}


	public void setRechargeTotal(double rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}


	public Date getCrearteAt() {
		return crearteAt;
	}


	public void setCrearteAt(Date crearteAt) {
		this.crearteAt = crearteAt;
	}


	@Override
	public int compareTo(CompanyListClient o) {
		return 0;
	}
	
	
}
