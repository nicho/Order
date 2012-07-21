package com.omdasoft.orderonline.gwt.order.client.enterprise.model;

import java.io.Serializable;
import java.util.Date;

public class EnterpriseVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String cid;
	private String name;
	private String description;//备注说明
	private String txAccountId;
	private String unitCode;
	private String address;//企业地址
	private String cellphone;//手机

	private String corporation;//法人
	
	private String emailAddress;//邮箱地址

	private String fax;//传真
	
	private String linkman;//联系人
	
	private String tell;//联系电话

	private String web;//企业网站
	
    private double integralPrice;//积分价值 
    private Double period;//财年周期
	private Date   firstTime;//首个财年开始日期
	private  String moneyType;//企业所用货币类型
	private  String  mailpwd;//发企业邮件的发送密码
    private  String  smtp;  //发邮件所用的smtp协议
    
    private Double balance;//可用积分
    private Double rechargeTotal;//充值总积分
    
    private String clientController;//客户管理员
    private String clientControllerEmail;//客户管理员email
    
    private String enterpriseRealmName;//企业子域名
    private String hrLoginAddress;//企业HR登录地址
    private String staffLoginAddress;//企业员工登录地址
    
    private String businessLicenseImage;//企业营业执照
    private String taxRegistrationCertificateImage;//税务登记证
    private String organizationCodeImage;//组织机构代码
    private String enterpriseLogoImage;//企业Logo
    private String enterpriseLoginLogoImage;//企业登录logo图片
    
    private String businessOwner;//业务负责人员
    private String businessOwnerEmail;//业务负责人员email
    private String businessOwnerTelNum;//业务负责人员联系电话
    private String businessRemark;//业务备注
    
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getRechargeTotal() {
		return rechargeTotal;
	}

	public void setRechargeTotal(Double rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}

	public String getClientController() {
		return clientController;
	}

	public void setClientController(String clientController) {
		this.clientController = clientController;
	}

	public String getClientControllerEmail() {
		return clientControllerEmail;
	}

	public void setClientControllerEmail(String clientControllerEmail) {
		this.clientControllerEmail = clientControllerEmail;
	}

	public String getEnterpriseRealmName() {
		return enterpriseRealmName;
	}

	public void setEnterpriseRealmName(String enterpriseRealmName) {
		this.enterpriseRealmName = enterpriseRealmName;
	}

	public String getHrLoginAddress() {
		return hrLoginAddress;
	}

	public void setHrLoginAddress(String hrLoginAddress) {
		this.hrLoginAddress = hrLoginAddress;
	}

	public String getStaffLoginAddress() {
		return staffLoginAddress;
	}

	public void setStaffLoginAddress(String staffLoginAddress) {
		this.staffLoginAddress = staffLoginAddress;
	}

	public String getBusinessLicenseImage() {
		return businessLicenseImage;
	}

	public void setBusinessLicenseImage(String businessLicenseImage) {
		this.businessLicenseImage = businessLicenseImage;
	}

	public String getTaxRegistrationCertificateImage() {
		return taxRegistrationCertificateImage;
	}

	public void setTaxRegistrationCertificateImage(
			String taxRegistrationCertificateImage) {
		this.taxRegistrationCertificateImage = taxRegistrationCertificateImage;
	}

	public String getOrganizationCodeImage() {
		return organizationCodeImage;
	}

	public void setOrganizationCodeImage(String organizationCodeImage) {
		this.organizationCodeImage = organizationCodeImage;
	}

	public String getEnterpriseLogoImage() {
		return enterpriseLogoImage;
	}

	public void setEnterpriseLogoImage(String enterpriseLogoImage) {
		this.enterpriseLogoImage = enterpriseLogoImage;
	}

	public String getEnterpriseLoginLogoImage() {
		return enterpriseLoginLogoImage;
	}

	public void setEnterpriseLoginLogoImage(String enterpriseLoginLogoImage) {
		this.enterpriseLoginLogoImage = enterpriseLoginLogoImage;
	}

	public String getBusinessOwner() {
		return businessOwner;
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}

	public String getBusinessOwnerEmail() {
		return businessOwnerEmail;
	}

	public void setBusinessOwnerEmail(String businessOwnerEmail) {
		this.businessOwnerEmail = businessOwnerEmail;
	}

	public String getBusinessOwnerTelNum() {
		return businessOwnerTelNum;
	}

	public void setBusinessOwnerTelNum(String businessOwnerTelNum) {
		this.businessOwnerTelNum = businessOwnerTelNum;
	}

	public String getBusinessRemark() {
		return businessRemark;
	}

	public void setBusinessRemark(String businessRemark) {
		this.businessRemark = businessRemark;
	}

	public String getMailpwd() {
		return mailpwd;
	}

	public void setMailpwd(String mailpwd) {
		this.mailpwd = mailpwd;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	
	public double getIntegralPrice() {
		return integralPrice;
	}

	public void setIntegralPrice(double integralPrice) {
		this.integralPrice = integralPrice;
	}

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTxAccountId() {
		return txAccountId;
	}

	public void setTxAccountId(String txAccountId) {
		this.txAccountId = txAccountId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

}