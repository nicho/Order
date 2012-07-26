package com.omdasoft.orderonline.gwt.order.server.login;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.remote.login.LoginService;
import com.omdasoft.orderonline.gwt.order.client.support.UserSession;
import com.omdasoft.orderonline.gwt.order.model.ClientException;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.util.UserRoleTool;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserSessionVo;
import com.omdasoft.orderonline.model.user.UserStatus;
import com.omdasoft.orderonline.service.staff.IStaffService;
import com.omdasoft.orderonline.service.user.UserService;

/**
 * The server side implementation of the RPC service.
 */
@Singleton
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService userService;
	IStaffService staffService;
	/**
	 * 校验码 KEY
	 */
	protected static final String CODE_SESSION_KEY = com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

	@Inject
	public LoginServiceImpl(UserService userService,IStaffService staffService) {
		this.userService = userService;
		this.staffService=staffService;
	}
	public LoginServiceImpl() {
		
	}
	@Override
	public UserSession authLogin(String username, String password,
			String verifyCode) throws ClientException {
		// check verification code (kaptcha)
//		HttpSession session = getThreadLocalRequest().getSession();
//		String code = (String) session.getAttribute(CODE_SESSION_KEY);
//		if (!verifyCode.equalsIgnoreCase(code)) {
//			throw new ClientException("验证码错误");
//		}
		
	
		UserSession resp = new UserSession();
		UserSessionVo u = userService.authenticate(username,password);
		
		if (u != null) {
			if(u.getUserRoles().size()<=0)
			{
				throw new ClientException("用户无角色!");
			}
			if(u.getUserStatus()==UserStatus.Inactive)
			{
				throw new ClientException("您已离职,拒绝进入!");
			}
			resp.setCorporationId(u.getCorporationId());
			resp.setCorporationName(u.getCorporationName());
			resp.setPhoto(u.getPhoto());
			resp.setLoginName(u.getUsername());
			resp.setToken(u.getId());
			resp.setDepartmentId(u.getDepartmentId());
			resp.setDepartmentName(u.getDepartmentName());
			resp.setUserRoles(UserRoleTool.adaptToRoleVo(u.getUserRoles()));
			resp.setStaffId(u.getStaffId());
			resp.setStaffName(u.getStaffName());
			resp.setCid(u.getCid());
			if(u.getLastLoginRole()!=null)
			{
				resp.setLastLoginRole(UserRoleVo.valueOf(u.getLastLoginRole().toString()));
			}
			
			
			UserContext context=new UserContext();
			context.setCorporationId(u.getCorporationId());


		} else {
			throw new ClientException("用户名或密码错误!");
		}
		
		return resp;
	}
}
