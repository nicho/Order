/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.companyAdd.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

import com.omdasoft.orderonline.gwt.order.client.support.UserSession;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;

/**
 * An action which perform request to search company.
 * 
 * @author sunny
 */
public class CompanyAddRequest implements Action<CompanyAddResponse> {
	private List<UserRoleVo> userRoleVos;
	private UserSession session;
	
	private String cid;	
	private String enterpriseId;		//企业id
	private String enterpriseName;		//企业名称
	private String address;				//企业地址
	private String linkman;				//企业联系人
	private String tell;				//联系电话
	private String email;				//email
	private String callphone;			//手机号
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

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCallphone() {
		return callphone;
	}

	public void setCallphone(String callphone) {
		this.callphone = callphone;
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

	public CompanyAddRequest() {
	}

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	public List<UserRoleVo> getUserRoleVos() {
		return userRoleVos;
	}

	public void setUserRoleVos(List<UserRoleVo> userRoleVos) {
		this.userRoleVos = userRoleVos;
	}
		
}
