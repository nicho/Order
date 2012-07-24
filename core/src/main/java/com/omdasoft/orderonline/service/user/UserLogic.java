package com.omdasoft.orderonline.service.user;

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

public interface UserLogic {
	public UserSessionVo authenticate(String userName,String pwd);
	public UserSessionVo tokenVaild(String token);
	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public Dictionary findDictionaryById(String id);
	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public Restaurant findRestaurantById(String id);
	/**
	 * 保存
	 * @param context
	 * @param order
	 * @return
	 */
	public Dictionary saveDictionary(UserContext context, Dictionary dictionary);
	/**
	 * 保存
	 * @param context
	 * @param order
	 * @return
	 */
	public Restaurant saveRestaurant(UserContext context, Restaurant restaurant);
	/**
	 * 保存
	 * @param context
	 * @param order
	 * @return
	 */
	public SysUser save(UserContext context, SysUser user);

	/**
	 * 用户列表
	 * @param context
	 * @param SysUser
	 * @return
	 */
	public PageStore<SysUser> getUserList(UserContext context,UserSearchCriteria criteria);
	
	
	/**
	 * 根据ID查找
	 * @param context
	 * @param order
	 * @return
	 */
	public SysUser findUserById(String userId);
	
	/**
	 * 删除员工根据ID
	 * @param id
	 * @return
	 */
	public String deleteUser(SysUser caller,String id);
	/**
	 * 分店列表
	 * @param context
	 * @param SysUser
	 * @return
	 */
	public PageStore<Restaurant> getRestaurantListList(UserContext context,RestaurantSearchCriteria criteria);
	/**
	 * 字典列表
	 * @param context
	 * @param SysUser
	 * @return
	 */
	public PageStore<Dictionary> getDictionaryListList(UserContext context,DictionarySearchCriteria criteria);
	
	/**
	 * 登录接口
	 * @param username
	 * @param password
	 * @return
	 */
	public LoginReturnModel loginRestful(String username,String password);
	
	/**
	 * 根据tokid取用户
	 * @param tokenId
	 * @return
	 */
	public SysUser getSysUserByTokenId(String tokenId);
	public SysUser getSysUserByTokenIdNotCheckDate(String tokenId);
	/**
	 * 查找根据员工Id
	 * @param id
	 * @return
	 */
	public SysUser findUserByStaffId(String staffid);
	/**
	 * 删除Dictionary根据ID
	 * @param id
	 * @return
	 */
	public int deleteDictionary(UserContext context,String id);
	/**
	 *	修改密码
	 * @param staffId
	 * @param pwd
	 * @return
	 */
	public String updateStaffPwd(String userId,String oldpwd,String pwd,String byUserId);
	
	/**
	 *	重置密码
	 * @param staffId
	 * @param pwd
	 * @return
	 */
	public String updateUserPwd(String staffId,String pwd,String byUserId);
	public String updateLastLoginRole(String userId, UserRole role);
}


