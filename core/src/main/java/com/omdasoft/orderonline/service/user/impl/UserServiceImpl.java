package com.omdasoft.orderonline.service.user.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.omdasoft.orderonline.domain.dictionary.Dictionary;
import com.omdasoft.orderonline.domain.org.Restaurant;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.order.LoginReturnModel;
import com.omdasoft.orderonline.model.user.DictionarySearchCriteria;
import com.omdasoft.orderonline.model.user.RestaurantSearchCriteria;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.model.user.UserSearchCriteria;
import com.omdasoft.orderonline.model.user.UserSessionVo;
import com.omdasoft.orderonline.service.user.UserLogic;
import com.omdasoft.orderonline.service.user.UserService;
@Transactional
public class UserServiceImpl implements UserService {
	private final UserLogic userLogic;

	@Inject
	public UserServiceImpl(UserLogic userLogic) {	
		this.userLogic = userLogic;
	}


	@Override
	public SysUser save(UserContext context, SysUser user) {
		return userLogic.save(context, user);
	}


	@Override
	public PageStore<SysUser> getUserList(UserContext context,UserSearchCriteria criteria) {
		return userLogic.getUserList(context,criteria);
	}


	@Override
	public SysUser findUserById(String id) {
		return userLogic.findUserById(id);
	}


	@Override
	public String deleteUser(UserContext context, String id) {
		SysUser ux=null;
		if(context!=null && context.getUserId()!=null)
			 ux = userLogic.findUserById(context.getUserId());
		return userLogic.deleteUser(ux, id);
	}


	@Override
	public PageStore<Restaurant> getRestaurantListList(UserContext context,
			RestaurantSearchCriteria criteria) {
		return userLogic.getRestaurantListList(context, criteria);
	}


	@Override
	public PageStore<Dictionary> getDictionaryListList(UserContext context,
			DictionarySearchCriteria criteria) {
		return userLogic.getDictionaryListList(context, criteria);
	}


	@Override
	public Dictionary saveDictionary(UserContext context, Dictionary dictionary) {
		return userLogic.saveDictionary(context, dictionary);
	}


	@Override
	public Restaurant saveRestaurant(UserContext context, Restaurant restaurant) {
		return userLogic.saveRestaurant(context, restaurant);
	}


	@Override
	public Dictionary findDictionaryById(String id) {
		return userLogic.findDictionaryById(id);
	}


	@Override
	public Restaurant findRestaurantById(String id) {
		return userLogic.findRestaurantById(id);
	}


	@Override
	public UserSessionVo authenticate(String userName, String pwd) {
		return userLogic.authenticate(userName, pwd);
	}


	@Override
	public UserSessionVo tokenVaild(String token) {
		return userLogic.tokenVaild(token);
	}


	@Override
	public LoginReturnModel loginRestful(String username, String password) {
		return userLogic.loginRestful(username, password);
	}


	@Override
	public SysUser findUserByStaffId(String staffid) {
		return userLogic.findUserByStaffId(staffid);
	}


	@Override
	public int deleteDictionary(UserContext context, String id) {
		return userLogic.deleteDictionary(context, id);
	}

	@Override
	public String updateStaffPwd(String userId,String oldpwd,String pwd,String byUserId) {
		return userLogic.updateStaffPwd(userId,oldpwd, pwd,byUserId);
	}


	@Override
	public String updateUserPwd(String staffId, String pwd, String byUserId) {
		return userLogic.updateUserPwd(staffId, pwd, byUserId);
	}
	@Override
	public String updateLastLoginRole(String userId, UserRole role) {
		return userLogic.updateLastLoginRole(userId, role);
	}

}
