package com.omdasoft.orderonline.service.dishes;

import java.util.List;

import com.omdasoft.orderonline.domain.dishes.Dishes;
import com.omdasoft.orderonline.domain.dishes.DishesType;
import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.dishes.DishesSearchCriteria;
import com.omdasoft.orderonline.model.dishes.DishesTypeSearchCriteria;
import com.omdasoft.orderonline.model.dishes.OrderDishesSearchCriteria;
import com.omdasoft.orderonline.model.order.DishcategoryReturnModel;
import com.omdasoft.orderonline.model.order.DishesReturnModel;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.vo.MenuVo;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface DishesService {

	/**
	 * 保存
	 * @param context
	 * @param order
	 * @return
	 */
	public Dishes saveDishes(UserContext context, Dishes user);

	/**
	 * 保存类型
	 * @param context
	 * @param order
	 * @return
	 */
	public DishesType saveDishesType(UserContext context, DishesType dishesType);
	
	/**
	 *Dishes 列表
	 * @param context
	 * @param Dishes
	 * @return
	 */
	public PageStore<Dishes> getDishesList(UserContext context,DishesSearchCriteria criteria);
	/**
	 * DishesType列表
	 * @param context
	 * @param Dishes
	 * @return
	 */
	public PageStore<DishesType> getDishesTypeList(UserContext context,DishesTypeSearchCriteria criteria);
	/**
	 *Order Dishes 列表
	 * @param context
	 * @param Dishes
	 * @return
	 */
	public PageStore<OrdersDishes> getOrderDishesList(UserContext context,OrderDishesSearchCriteria criteria);
	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public Dishes findDishesById(String id);
	
	
	/**
	 * 删除菜品根据ID
	 * @param id
	 * @return
	 */
	public int deleteDishes(UserContext context,String id);
	/**
	 * 删除菜品类别根据ID
	 * @param id
	 * @return
	 */
	public int deleteDishesType(UserContext context,String id);
	/**
	 * 保存Menu
	 * @param context
	 * @param order
	 * @return
	 */
	public Dishes saveMenu(UserContext context, MenuVo menuvo);


	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public DishesType findDishesTypeById(String id);
	
	/**
	 * 添加菜品类别
	 * @param tokenId
	 * @param category_id
	 * @param category_name
	 * @return
	 */
	public DishcategoryReturnModel addDishcategory(String tokenId,String category_id,String category_name);
	
	/**
	 * 修改菜品类别
	 * @param tokenId
	 * @param category_id
	 * @param category_name
	 * @return
	 */
	public DishcategoryReturnModel updateDishcategory(String tokenId,String category_id,String category_name);
	
	
	/**
	 * 删除菜品类别
	 * @param tokenId
	 * @param category_id
	 * @return
	 */
	public DishcategoryReturnModel deleteDishcategory(String tokenId,String category_id);
	/**
	 * 查询菜品类别
	 * @param tokenId
	 * @return
	 */
	public DishcategoryReturnModel findDishcategory(String tokenId);
	
	
	/**
	 * 添加菜品
	 * @param tokenId
	 * @param category_id
	 * @param category_name
	 * @return
	 */
	public DishesReturnModel addDishes(String tokenId,String dishes_id,String dishes_name);
	/**
	 * 修改菜品
	 * @param tokenId
	 * @param category_id
	 * @param category_name
	 * @return
	 */
	public DishesReturnModel updateDishes(String tokenId,String dishes_id,String dishes_name);
	/**
	 * 删除菜品
	 * @param tokenId
	 * @param category_id
	 * @return
	 */
	public DishesReturnModel deleteDishes(String tokenId,String dishes_id);
	/**
	 * 查询菜品
	 * @param tokenId
	 * @return
	 */
	public DishesReturnModel findDishes(String tokenId);

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
