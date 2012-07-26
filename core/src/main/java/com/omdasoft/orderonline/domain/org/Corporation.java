package com.omdasoft.orderonline.domain.org;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * It defines a corporation.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "corporation")
public class Corporation extends Organization {

	private static final long serialVersionUID = -2314873131853974603L;

	/**
	 * Which industry this corporation belongs to.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Industry industry;

	/**
	 * account id of tx
	 */
	private String txAccountId;

	/**
	 * Which unit code this corporation use.
	 */
	private String defaultUnitCode;
	
	private String address;//企业地址
	private String cellphone;//手机

	private String corporationer;//法人
	
	private String emailAddress;//邮箱地址

	private String fax;//传真
	
	private String linkman;//联系人
	
	private String tell;//联系电话

	private String web;//企业网站
	
	private Double integralPrice;//积分价值 
	
	private Double period;//财年周期
	
	private Date   firstTime;//首个财年开始日期
	
	private  String moneyType;//企业所用货币类型
	
    private  String  mailpwd;//发企业邮件的发送密码
    private  String  smtp;  //发邮件所用的smtp协议
    private  String  cid;  //企业标识
    
    
    
    
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
    
    private Integer isCreateHrAccount;//是否生成公司hr管理员
    
    @ManyToOne
    private Staff staff; 

	
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
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

	public Integer getIsCreateHrAccount() {
		return isCreateHrAccount;
	}

	public void setIsCreateHrAccount(Integer isCreateHrAccount) {
		this.isCreateHrAccount = isCreateHrAccount;
	}

	public void setCorporationer(String corporationer) {
		this.corporationer = corporationer;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
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

	public Double getIntegralPrice() {
		if(integralPrice==null){
			return Double.valueOf(0);
		}
		return integralPrice;
	}

	public void setIntegralPrice(Double integralPrice) {
		this.integralPrice = integralPrice;
	}

	public Double getPeriod() {
		if(period==null){
			return Double.valueOf(0);
		}
		return period;
	}

	public void setPeriod(Double period) {
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

	public String getCorporationer() {
		return corporationer;
	}

	public void setCorporation(String corporationer) {
		this.corporationer = corporationer;
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

	
	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public String getTxAccountId() {
		return txAccountId;
	}

	public void setTxAccountId(String txAccountId) {
		this.txAccountId = txAccountId;
	}

	public String getDefaultUnitCode() {
		return defaultUnitCode;
	}

	public void setDefaultUnitCode(String defaultUnitCode) {
		this.defaultUnitCode = defaultUnitCode;
	}

}
