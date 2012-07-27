package com.omdasoft.orderonline.service.dishes.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.omdasoft.orderonline.domain.dishes.Dishes;
import com.omdasoft.orderonline.domain.dishes.DishesType;
import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.dishes.DishesSearchCriteria;
import com.omdasoft.orderonline.model.dishes.DishesTypeSearchCriteria;
import com.omdasoft.orderonline.model.dishes.OrderDishesSearchCriteria;
import com.omdasoft.orderonline.model.order.DishcategoryReturnModel;
import com.omdasoft.orderonline.model.order.DishesReturnModel;
import com.omdasoft.orderonline.model.order.DishesTypeModel;
import com.omdasoft.orderonline.model.order.MyDishesModel;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.model.vo.MenuVo;
import com.omdasoft.orderonline.service.dishes.DishesLogic;
import com.omdasoft.orderonline.service.dishes.DishesService;
import com.omdasoft.orderonline.service.user.UserLogic;
import com.omdasoft.orderonline.util.StringUtil;

@Transactional
public class DishesServiceImpl implements DishesService {
	private final DishesLogic dishesLogic;
	private final UserLogic userLogic;

	@Inject
	public DishesServiceImpl(DishesLogic dishesLogic, UserLogic userLogic) {
		this.dishesLogic = dishesLogic;
		this.userLogic = userLogic;
	}

	@Override
	public Dishes saveDishes(UserContext context, Dishes user) {
		return dishesLogic.saveDishes(context, user);
	}

	@Override
	public DishesType saveDishesType(UserContext context, DishesType user) {
		return dishesLogic.saveDishesType(context, user);
	}

	@Override
	public PageStore<Dishes> getDishesList(UserContext context,
			DishesSearchCriteria criteria) {
		return dishesLogic.getDishesList(context, criteria);
	}

	@Override
	public Dishes findDishesById(String id) {
		return dishesLogic.findDishesById(id);
	}

	@Override
	public int deleteDishes(UserContext context, String id) {
		SysUser ux = null;
		if (context != null && context.getUserId() != null)
			ux = userLogic.findUserById(context.getUserId());
		return dishesLogic.deleteDishesId(ux, id);
	}

	@Override
	public PageStore<DishesType> getDishesTypeList(UserContext context,
			DishesTypeSearchCriteria criteria) {
		return dishesLogic.getDishesTypeList(context, criteria);
	}

	@Override
	public Dishes saveMenu(UserContext context, MenuVo menuvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DishesType findDishesTypeById(String id) {
		return dishesLogic.findDishesTypeById(id);
	}

	@Override
	public PageStore<OrdersDishes> getOrderDishesList(UserContext context,
			OrderDishesSearchCriteria criteria) {
		return dishesLogic.getOrderDishesList(context, criteria);
	}


	@Override
	public DishcategoryReturnModel deleteDishcategory(String tokenId,
			String category_id) {
		DishcategoryReturnModel returnModel = new DishcategoryReturnModel();
		try {

			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			} else {
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {
					int flag = dishesLogic.deleteDishesType(user, category_id,user.getCorporation().getId());
					returnModel.setFlag(flag + "");

				} else {
					returnModel.setFlag("1");
					returnModel.setException_code("11");
					returnModel.setException_msg("登录失效");
				}
			}

		} catch (Exception e) {
			returnModel.setFlag("1");
			returnModel.setException_code("12");
			returnModel.setException_msg(e.getStackTrace().toString());
		}
		return returnModel;
	}

	@Override
	public DishcategoryReturnModel findDishcategory(String tokenId) {
		DishcategoryReturnModel returnModel = new DishcategoryReturnModel();

		try {

			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			} else {
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {
					// 加入用户机构过滤..wating
					UserContext uc=new UserContext();
					uc.setCorporationId(user.getCorporation().getId());
					DishesTypeSearchCriteria cr=new DishesTypeSearchCriteria();
					PageStore<DishesType> list = dishesLogic.getDishesTypeList(uc, cr);
					
					if (list != null && list.getResultList() != null
							&& list.getResultList().size() > 0) {
						List<DishesTypeModel> typeModel = new ArrayList<DishesTypeModel>();
						for (DishesType type : list.getResultList()) {
							DishesTypeModel mo = new DishesTypeModel();
							mo.setId(type.getId());
							mo.setName(type.getName());
							mo.setRid(type.getRid());
							typeModel.add(mo);
						}
						returnModel.setFlag("0");
						returnModel.setData(typeModel);

					} else {
						returnModel.setFlag("1");
						returnModel.setException_code("12");
						returnModel.setException_msg("无数据");
					}

				} else {
					returnModel.setFlag("1");
					returnModel.setException_code("11");
					returnModel.setException_msg("登录失效");
				}
			}

		} catch (Exception e) {
			returnModel.setFlag("1");
			returnModel.setException_code("12");
			returnModel.setException_msg(e.getStackTrace().toString());
		}
		return returnModel;
	}

	
	@Override
	public DishesReturnModel deleteDishes(String tokenId, String dishes_id) {
		DishesReturnModel returnModel = new DishesReturnModel();

		try {

			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			} else {
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {

					dishesLogic.deleteDishes(user, dishes_id,user.getCorporation().getId());
					returnModel.setFlag("0");

				} else {
					returnModel.setFlag("1");
					returnModel.setException_code("11");
					returnModel.setException_msg("登录失效");
				}
			}

		} catch (Exception e) {
			returnModel.setFlag("1");
			returnModel.setException_code("12");
			returnModel.setException_msg(e.getStackTrace().toString());
		}
		return returnModel;
	}

	@Override
	public DishesReturnModel findDishes(String tokenId) {
		DishesReturnModel returnModel = new DishesReturnModel();

		try {

			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			} else {
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {
					// 加入机构.等信息查询
					UserContext uc=new UserContext();
					uc.setCorporationId(user.getCorporation().getId());
					uc.setUserId(user.getId());
					uc.setLastRole(UserRole.DEPT_MGR);
					DishesSearchCriteria cr=new DishesSearchCriteria();
					PageStore<Dishes> list = dishesLogic.getDishesList(uc,
							cr);
					if (list != null && list.getResultList() != null
							&& list.getResultList().size() > 0) {
						List<MyDishesModel> typeModel = new ArrayList<MyDishesModel>();
						for (Dishes type : list.getResultList()) {
							MyDishesModel mo = new MyDishesModel();
							mo.setId(type.getId());
							mo.setName(type.getName());
							mo.setRid(type.getRid());
							typeModel.add(mo);
						}
						returnModel.setFlag("0");
						returnModel.setData(typeModel);

					} else {
						returnModel.setFlag("1");
						returnModel.setException_code("12");
						returnModel.setException_msg("无数据");
					}

				} else {
					returnModel.setFlag("1");
					returnModel.setException_code("11");
					returnModel.setException_msg("登录失效");
				}
			}

		} catch (Exception e) {
			returnModel.setFlag("1");
			returnModel.setException_code("12");
			returnModel.setException_msg(e.getStackTrace().toString());
		}
		return returnModel;

	}

	@Override
	public DishcategoryReturnModel addDishcategory(String tokenId,
			String category_id, String category_name) {
		DishcategoryReturnModel returnModel = new DishcategoryReturnModel();

		try {

			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			}
			else if(StringUtil.isEmptyString(category_id)){
				returnModel.setFlag("1");
				returnModel.setException_code("12");
				returnModel.setException_msg("rid为空");
			}
			else if(StringUtil.isEmptyString(category_name)){
				returnModel.setFlag("1");
				returnModel.setException_code("12");
				returnModel.setException_msg("category_name为空");
			}
			else {
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {

					DishesType type=new DishesType();
					
					type.setRid(category_id);
					type.setName(category_name);
					type.setCorporation(user.getCorporation());
					UserContext uc=new UserContext();
					
					dishesLogic.saveDishesType(uc, type);
					returnModel.setFlag("0");

				} else {
					returnModel.setFlag("1");
					returnModel.setException_code("11");
					returnModel.setException_msg("登录失效");
				}
			}

		} catch (Exception e) {
			returnModel.setFlag("1");
			returnModel.setException_code("12");
			returnModel.setException_msg(e.getStackTrace().toString());
		}
		return returnModel;
	}

	@Override
	public DishcategoryReturnModel updateDishcategory(String tokenId,
			String category_id, String category_name) {
		DishcategoryReturnModel returnModel = new DishcategoryReturnModel();

		try {

			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			}
			else if(StringUtil.isEmptyString(category_id)){
				returnModel.setFlag("1");
				returnModel.setException_code("12");
				returnModel.setException_msg("rid为空");
			}
			else if(StringUtil.isEmptyString(category_name)){
				returnModel.setFlag("1");
				returnModel.setException_code("12");
				returnModel.setException_msg("category_name为空");
			}
			else {
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {
					DishesType type = dishesLogic.findDishesTypeByrId(category_id,user.getCorporation().getId());
					if(type==null)
					{
						returnModel.setFlag("1");
						returnModel.setException_code("12");
						returnModel.setException_msg("找不到菜品类别");
						return returnModel;
					}
					else
					{
						type.setRid(category_id);
						type.setName(category_name);
						type.setCorporation(user.getCorporation());
						UserContext uc=new UserContext();
						
						dishesLogic.saveDishesType(uc, type);
						returnModel.setFlag("0");
					}
				} else {
					returnModel.setFlag("1");
					returnModel.setException_code("11");
					returnModel.setException_msg("登录失效");
				}
			}

		} catch (Exception e) {
			returnModel.setFlag("1");
			returnModel.setException_code("12");
			returnModel.setException_msg(e.getStackTrace().toString());
		}
		return returnModel;
	}

	@Override
	public DishesReturnModel addDishes(String tokenId, String dishes_id,
			String dishes_name) {
		DishesReturnModel returnModel = new DishesReturnModel();

		try {

			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			} else {
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {
					
					Dishes dishes=new Dishes();
					
					dishes.setRid(dishes_id);
					dishes.setName(dishes_name);
					dishes.setCorporation(user.getCorporation());
					UserContext uc=new UserContext();
					dishesLogic.saveDishes(uc, dishes);
					returnModel.setFlag("0");

				} else {
					returnModel.setFlag("1");
					returnModel.setException_code("11");
					returnModel.setException_msg("登录失效");
				}
			}

		} catch (Exception e) {
			returnModel.setFlag("1");
			returnModel.setException_code("12");
			returnModel.setException_msg(e.getStackTrace().toString());
		}
		return returnModel;
	}

	@Override
	public DishesReturnModel updateDishes(String tokenId, String dishes_id,
			String dishes_name) {
		DishesReturnModel returnModel = new DishesReturnModel();

		try {

			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			} else {
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {
					
					Dishes dishes = dishesLogic.findDishesByrId(dishes_id,user.getCorporation().getId());
					if(dishes==null)
					{
						returnModel.setFlag("1");
						returnModel.setException_code("12");
						returnModel.setException_msg("找不到菜品");
						return returnModel;
					}
					else
					{
						dishes.setRid(dishes_id);
						dishes.setName(dishes_name);
						dishes.setCorporation(user.getCorporation());
						UserContext uc=new UserContext();
						dishesLogic.saveDishes(uc, dishes);
						returnModel.setFlag("0");
					}

				} else {
					returnModel.setFlag("1");
					returnModel.setException_code("11");
					returnModel.setException_msg("登录失效");
				}
			}

		} catch (Exception e) {
			returnModel.setFlag("1");
			returnModel.setException_code("12");
			returnModel.setException_msg(e.getStackTrace().toString());
		}
		return returnModel;
	}

	@Override
	public int deleteDishesType(UserContext context, String id) {
		SysUser ux = null;
		if (context != null && context.getUserId() != null)
			ux = userLogic.findUserById(context.getUserId());
		return dishesLogic.deleteDishesTypeId(ux, id);
	}

	@Override
	public List<String> findDishesTypePanel() {
		return dishesLogic.findDishesTypePanel();
	}

	@Override
	public String copyDishesType(String deptId) {
		return dishesLogic.copyDishesType(deptId);
	}

	@Override
	public String copyDishes(String userId) {
		return dishesLogic.copyDishes(userId);
	}

}
