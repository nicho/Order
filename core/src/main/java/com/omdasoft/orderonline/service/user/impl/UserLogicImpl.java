package com.omdasoft.orderonline.service.user.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.omdasoft.orderonline.dao.dictionary.DictionaryDao;
import com.omdasoft.orderonline.dao.org.CorporationDao;
import com.omdasoft.orderonline.dao.org.DepartmentDao;
import com.omdasoft.orderonline.dao.org.RestaurantDao;
import com.omdasoft.orderonline.dao.user.TokenDao;
import com.omdasoft.orderonline.dao.user.UserDao;
import com.omdasoft.orderonline.dao.user.UserRoleDao;
import com.omdasoft.orderonline.domain.dictionary.Dictionary;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.domain.org.Restaurant;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.domain.user.SysUserRole;
import com.omdasoft.orderonline.domain.user.Token;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.order.LoginModel;
import com.omdasoft.orderonline.model.order.LoginReturnModel;
import com.omdasoft.orderonline.model.user.DictionarySearchCriteria;
import com.omdasoft.orderonline.model.user.RestaurantSearchCriteria;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.model.user.UserSearchCriteria;
import com.omdasoft.orderonline.model.user.UserSessionVo;
import com.omdasoft.orderonline.service.org.DepartmentLogic;
import com.omdasoft.orderonline.service.user.UserLogic;
import com.omdasoft.orderonline.util.DateUtil;
import com.omdasoft.orderonline.util.MD5;
import com.omdasoft.orderonline.util.StringUtil;

public class UserLogicImpl implements UserLogic {
	private UserDao userDao;
	private RestaurantDao restaurantDao;
	private DictionaryDao dictionaryDao;
	private UserRoleDao userRoleDao;
	private TokenDao tokenDao;
	private CorporationDao corporationDao;
	private DepartmentDao departmentDao;
	private DepartmentLogic departmentLogic;

    MD5 md5 =new  MD5();
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	protected UserLogicImpl(DepartmentLogic departmentLogic,CorporationDao corporationDao,UserDao userDao,RestaurantDao restaurantDao,DictionaryDao dictionaryDao,UserRoleDao userRoleDao,TokenDao tokenDao,DepartmentDao departmentDao) {
		this.userDao = userDao;
		this.restaurantDao=restaurantDao;
		this.dictionaryDao=dictionaryDao;
		this.userRoleDao=userRoleDao;
		this.tokenDao=tokenDao;
		this.corporationDao=corporationDao;
		this.departmentDao=departmentDao;
		this.departmentLogic=departmentLogic;
	}

	@Override
	public UserSessionVo authenticate(String userName, String pwd) {
		 String password="";
		 try {
			password = md5.MD5(pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser user = userDao.findUserByNameAndPwd(userName, password);
		if (user != null)
			return findUserRolebySysUser(user);
		else
			return null;
	}

	@Override
	public UserSessionVo tokenVaild(String token) {
		SysUser user = userDao.findById(SysUser.class, token);
		if (user != null)
			return findUserRolebySysUser(user);
		else
			return null;
	}

	private UserSessionVo findUserRolebySysUser(SysUser user) {
		UserSessionVo vo = new UserSessionVo();
		List<SysUserRole> listRole = userRoleDao.findUserRoleByUserId(user
				.getId());
		List<UserRole> userRoles = new ArrayList<UserRole>();
		if (listRole != null && listRole.size() > 0) {
			for (SysUserRole role : listRole) {
				userRoles.add(role.getRole().getName());
			}
		}

		vo.setId(user.getId());
		vo.setCorporationId(user.getCorporation().getId());
		vo.setCid(user.getCorporation().getCid());
		vo.setCorporationName(user.getCorporation().getName());
		vo.setPhoto(user.getStaff().getPhoto());
		vo.setUsername(user.getUserName());
		vo.setUserRoles(userRoles);
		if(user.getStaff().getDepartment()!=null)
		{
			vo.setDepartmentId(user.getStaff().getDepartment().getId());
			
			Department dept=departmentLogic.findDepartmentByAdminUserId(user.getId());
			if(dept!=null)
				vo.setDepartmentName(dept.getName());
		}
		vo.setStaffId(user.getStaff().getId());
		vo.setLastLoginRole(user.getLastLoginRole());
		vo.setUserStatus(user.getStatus());
		
		return vo;
	}

	@Override
	public SysUser save(UserContext context, SysUser user) {
		return userDao.save(user);
	}

	@Override
	public PageStore<SysUser> getUserList(UserContext context,
			UserSearchCriteria criteria) {
		if(!StringUtil.isEmptyString(context.getCorporationId()))
			criteria.setCorporationId(context.getCorporationId());
		return userDao.queryUserPageAction(criteria);
	}

	@Override
	public SysUser findUserById(String userId) {
		return userDao.findUserById(userId);
	}

	@Override
	public String deleteUser(SysUser caller, String id) {
		SysUser user = userDao.findUserById(id);
		if (user != null) {
			userDao.delete(user);
			return "success";
		} else
			return "fail";
	}

	@Override
	public PageStore<Restaurant> getRestaurantListList(UserContext context,
			RestaurantSearchCriteria criteria) {
		return restaurantDao.queryRestaurantPageAction(criteria);
	}

	@Override
	public PageStore<Dictionary> getDictionaryListList(UserContext context,
			DictionarySearchCriteria criteria) {
		if(!StringUtil.isEmptyString(context.getCorporationId()))
		{
			criteria.setCorpId(context.getCorporationId());
		}

		if(!StringUtil.isEmptyString(criteria.getDeptId()))
		{
			Department dept=departmentDao.findById(Department.class, criteria.getDeptId());
			if(dept!=null && dept.getCorporation()!=null)
			criteria.setCorpId(dept.getCorporation().getId());
		}
		
		//没选分店。默认机构
		if(StringUtil.isEmptyString(criteria.getCorpId()))
		{
			Corporation corp=corporationDao.getDeCorp();
			if(corp!=null)
				criteria.setCorpId(corp.getId());
		}
		
		return dictionaryDao.queryDictionaryPageAction(criteria);
	}

	@Override
	public Dictionary saveDictionary(UserContext context, Dictionary dictionary) {
		if(!StringUtil.isEmptyString(context.getCorporationId()))
		{
			dictionary.setCorporation(corporationDao.findById(Corporation.class, context.getCorporationId()));
		}

		return dictionaryDao.save(dictionary);
	}

	@Override
	public Restaurant saveRestaurant(UserContext context, Restaurant restaurant) {
		return restaurantDao.save(restaurant);
	}

	@Override
	public Dictionary findDictionaryById(String id) {
		return dictionaryDao.findDictionaryById(id);
	}

	@Override
	public Restaurant findRestaurantById(String id) {
		return restaurantDao.findRestaurantById(id);
	}

	@Override
	public LoginReturnModel loginRestful(String username, String password) {
		LoginReturnModel model=new LoginReturnModel();

		 try {
			password = md5.MD5(password.trim());
			SysUser user = userDao.findUserByNameAndPwd(username.trim(), password);
			if(user!=null)
			{
				Token tok=new Token();
				tok.setUser(user);
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+30);
				tok.setExpirationTime(calendar.getTime());
				tok=tokenDao.save(tok);
				
				LoginModel mo=new LoginModel();
				mo.setTokenid(tok.getId());
				model.setFlag("0");
				model.setData(mo);
			}
			else
			{
				model.setFlag("1");
				model.setException_code("12");
				model.setException_msg("用户名或密码错误!");
			}
			
		} catch (Exception e) {
			model.setFlag("1");
			model.setException_code("12");
			model.setException_msg(e.getStackTrace().toString());
		}
	
		return model;
	}

	@Override
	public SysUser getSysUserByTokenId(String tokenId) {
		Token tok=tokenDao.findById(Token.class, tokenId);
		if(DateUtil.getTime().getTime()<=tok.getExpirationTime().getTime())
		{
			//更新
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+30);
			tok.setExpirationTime(calendar.getTime());
			tok=tokenDao.update(tok);
			return tok.getUser();
		}
		return null;
	}
	@Override
	public SysUser getSysUserByTokenIdNotCheckDate(String tokenId) {
		try {
			Token tok=tokenDao.findById(Token.class, tokenId);
			return tok.getUser();
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	public SysUser findUserByStaffId(String staffid) {
		return userDao.findUserByStaffId(staffid);
	}

	@Override
	public int deleteDictionary(UserContext context, String id) {

		Dictionary dy=dictionaryDao.findById(Dictionary.class, id);
		dy.setDeleted(1);
		dictionaryDao.update(dy);
		return 1;
	}
	@Override
	public String updateStaffPwd(String userId,String oldpwd, String pwd,String byUserId) {
		String newpassword ="";
		String oldpassword ="";
		try {
			newpassword = md5.MD5(pwd);
			oldpassword = md5.MD5(oldpwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser user=userDao.findUserById(userId);
		
		SysUser nowUser=userDao.findUserById(byUserId);
		user.setLastModifiedAt(DateUtil.getTime());
		user.setLastModifiedBy(nowUser);
		if(user.getPassword().trim().equals(oldpassword.trim())){
			user.setPassword(newpassword);
		    userDao.update(user);
		    return "success";
		}else{
			return "faile";
		}
			
	}

	@Override
	public String updateUserPwd(String staffId, String pwd, String byUserId) {
		String newpassword ="";

		try {
			newpassword = md5.MD5(pwd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser user=userDao.findUserByStaffId(staffId);
		if(user!=null)
		{
			SysUser nowUser=userDao.findUserById(byUserId);
			user.setLastModifiedAt(DateUtil.getTime());
			user.setLastModifiedBy(nowUser);
			user.setPassword(newpassword);
		    userDao.update(user);
		    return "success";
			
		}
		else{
			return "faile";
		}
			
	}
	@Override
	public String updateLastLoginRole(String userId, UserRole role) {
		SysUser user=userDao.findById(SysUser.class, userId);
		user.setLastLoginRole(role);
		userDao.update(user);
		return "success";
	}

}
