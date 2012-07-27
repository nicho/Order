package com.omdasoft.orderonline.service.dishes;

import java.util.List;

import com.omdasoft.orderonline.domain.dishes.Dishes;
import com.omdasoft.orderonline.domain.dishes.DishesType;
import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.dishes.DishesSearchCriteria;
import com.omdasoft.orderonline.model.dishes.DishesTypeSearchCriteria;
import com.omdasoft.orderonline.model.dishes.OrderDishesSearchCriteria;
import com.omdasoft.orderonline.model.user.UserContext;

public interface DishesLogic {
	/**
	 * 保存
	 * @param context
	 * @param order
	 * @return
	 */
	public Dishes saveDishes(UserContext context, Dishes dish);
	/**
	 * 保存类型
	 * @param context
	 * @param order
	 * @return
	 */
	public DishesType saveDishesType(UserContext context, DishesType dishesType);
	
	/**
	 * 列表
	 * @param context
	 * @param Dishes
	 * @return
	 */
	public PageStore<Dishes> getDishesList(UserContext context,DishesSearchCriteria criteria);
	
	
	/**
	 * 根据ID查找
	 * @param context
	 * @param order
	 * @return
	 */
	public Dishes findDishesById(String dishesId);
	/**
	 * 根据ID查找
	 * @param context
	 * @param order
	 * @return
	 */
	public Dishes findDishesByrId(String rid,String cid);
	
	/**
	 * 删除菜品根据rID
	 * @param id
	 * @return
	 */
	public int deleteDishes(SysUser caller,String id,String cid);
	/**
	 * 删除菜品类别根据rID
	 * @param id
	 * @return
	 */
	public int deleteDishesType(SysUser caller,String id,String cid);
	
	/**
	 * 删除菜品根据rID
	 * @param id
	 * @return
	 */
	public int deleteDishesId(SysUser caller,String id);
	/**
	 * 删除菜品类别根据rID
	 * @param id
	 * @return
	 */
	public int deleteDishesTypeId(SysUser caller,String id);
	/**
	 * DishesType列表
	 * @param context
	 * @param Dishes
	 * @return
	 */
	public PageStore<DishesType> getDishesTypeList(UserContext context,DishesTypeSearchCriteria criteria);

	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public DishesType findDishesTypeById(String id);
	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public DishesType findDishesTypeByrId(String rid,String cid);
	/**
	 *Order Dishes 列表
	 * @param context
	 * @param Dishes
	 * @return
	 */
	public PageStore<OrdersDishes> getOrderDishesList(UserContext context,OrderDishesSearchCriteria criteria);
	public List<String> findDishesTypePanel();
	/**
	 * 复制菜品类别
	 * @param deptId
	 * @return
	 */
	public String copyDishesType(String deptId);
	/**
	 * 复制菜品
	 * @param deptId
	 * @return
	 */
	public String copyDishes(String userId);
}


