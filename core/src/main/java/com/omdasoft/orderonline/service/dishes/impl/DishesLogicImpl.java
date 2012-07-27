package com.omdasoft.orderonline.service.dishes.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.omdasoft.orderonline.dao.dishes.DishesDao;
import com.omdasoft.orderonline.dao.dishes.DishesTypeDao;
import com.omdasoft.orderonline.dao.dishes.OrdersDishesDao;
import com.omdasoft.orderonline.dao.org.CorporationDao;
import com.omdasoft.orderonline.domain.dishes.Dishes;
import com.omdasoft.orderonline.domain.dishes.DishesType;
import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.dishes.DishesSearchCriteria;
import com.omdasoft.orderonline.model.dishes.DishesTypeSearchCriteria;
import com.omdasoft.orderonline.model.dishes.OrderDishesSearchCriteria;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.service.dishes.DishesLogic;
import com.omdasoft.orderonline.service.org.CorporationLogic;
import com.omdasoft.orderonline.service.org.DepartmentLogic;
import com.omdasoft.orderonline.util.StringUtil;

public class DishesLogicImpl implements DishesLogic {

	private DishesDao dishesDao;
	private OrdersDishesDao ordersDishesDao;
	private DishesTypeDao dishesTypeDao;
	private CorporationLogic corporationLogic;
	private CorporationDao corporationDao;
	private DepartmentLogic departmentLogic;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	protected DishesLogicImpl(DepartmentLogic departmentLogic,CorporationDao corporationDao,DishesDao dishesDao,DishesTypeDao dishesTypeDao,OrdersDishesDao ordersDishesDao,CorporationLogic corporationLogic) {
		this.dishesTypeDao=dishesTypeDao;
		this.dishesDao=dishesDao;
		this.ordersDishesDao=ordersDishesDao;
		this.corporationLogic=corporationLogic;
		this.corporationDao=corporationDao;
		this.departmentLogic=departmentLogic;
	}

	@Override
	public Dishes saveDishes(UserContext context, Dishes dishes) {
		if(!StringUtil.isEmptyString(context.getCorporationId()) && !StringUtil.isEmptyString(context.getDeptId()))
		{
			dishes.setCorporation(corporationLogic.findCorporationById(context.getCorporationId()));
			dishes.setDepartment(departmentLogic.findDepartmentById(context.getDeptId()));
		}
		if(dishes.getId()!=null)
			return dishesDao.update(dishes);
		else
			return dishesDao.save(dishes);
	}

	@Override
	public PageStore<Dishes> getDishesList(UserContext context,
			DishesSearchCriteria criteria) {
		if(!StringUtil.isEmptyString(context.getCorporationId()))
		{
			criteria.setCorporationId(context.getCorporationId());
		}

		if(!StringUtil.isEmptyString(context.getDeptId()))
		{
			criteria.setDeptId(context.getDeptId());
		}
		
		if(UserRole.DEPT_MGR==context.getLastRole())
		{
			//查询管理的部门
			Department dept=departmentLogic.findDepartmentByAdminUserId(context.getUserId());
			if(dept!=null)
			{
				criteria.setDeptId(dept.getId());
			}
		}
		
		//没选分店。默认机构
		if(StringUtil.isEmptyString(criteria.getCorporationId()))
		{
			Corporation corp=corporationDao.getDeCorp();
			if(corp!=null)
				criteria.setCorporationId(corp.getId());
		}
		return dishesDao.queryDishesPageAction(criteria);
	}

	@Override
	public Dishes findDishesById(String dishesId) {
		return dishesDao.findDishesById(dishesId);
	}

	@Override
	public int deleteDishes(SysUser caller, String id,String cid) {
		Dishes dishes = dishesDao.findDishesByrId(id,cid);
		if (dishes != null) {
			dishes.setDeleted(1);
			dishesDao.update(dishes);
			return 0;
		} else
			return 1;
	}
	@Override
	public int deleteDishesType(SysUser caller, String id,String cid) {
 
		DishesType dishes = dishesTypeDao.findDishesTypeByrId(id,cid);
		if (dishes != null) {
			dishes.setDeleted(1);
			dishesTypeDao.update(dishes);
			return 0;
		} else
			return 1;
	}
	@Override
	public DishesType saveDishesType(UserContext context, DishesType dishesType) {
		if(!StringUtil.isEmptyString(context.getCorporationId()) && !StringUtil.isEmptyString(context.getDeptId()))
		{
			dishesType.setCorporation(corporationLogic.findCorporationById(context.getCorporationId()));
			dishesType.setDepartment(departmentLogic.findDepartmentById(context.getDeptId()));
		}
		if(dishesType.getId()!=null)
			return dishesTypeDao.update(dishesType);
		else
			return dishesTypeDao.save(dishesType);
	}

	@Override
	public PageStore<DishesType> getDishesTypeList(UserContext context,
			DishesTypeSearchCriteria criteria) {
		if(!StringUtil.isEmptyString(context.getCorporationId()))
		{
			criteria.setCorporationId(context.getCorporationId());
		}



		//没选分店。默认机构
		if(StringUtil.isEmptyString(criteria.getCorporationId()))
		{
			Corporation corp=corporationDao.getDeCorp();
			if(corp!=null)
				criteria.setCorporationId(corp.getId());
		}
		
		return dishesTypeDao.queryDishesTypePageAction(criteria);
	}

	@Override
	public DishesType findDishesTypeById(String id) {
		return dishesTypeDao.findById(DishesType.class, id);
	}

	@Override
	public PageStore<OrdersDishes> getOrderDishesList(UserContext context,
			OrderDishesSearchCriteria criteria) {
		return ordersDishesDao.queryOrdersDishesPageAction(criteria);
	}

	@Override
	public Dishes findDishesByrId(String rid,String cid) {
		return dishesDao.findDishesByrId(rid,cid);
	}

	@Override
	public DishesType findDishesTypeByrId(String rid,String cid) {
		return dishesTypeDao.findDishesTypeByrId(rid,cid);
	}

	@Override
	public int deleteDishesId(SysUser caller, String id) {
		Dishes dishes = dishesDao.findDishesById(id);
		if (dishes != null) {
			dishes.setDeleted(1);
			dishesDao.update(dishes);
			return 0;
		} else
			return 1;
	}
	@Override
	public int deleteDishesTypeId(SysUser caller, String id) {
		DishesType dishes = dishesTypeDao.findById(DishesType.class, id);
		if (dishes != null) {
			dishes.setDeleted(1);
			dishesTypeDao.update(dishes);
			return 0;
		} else
			return 1;
	}

	@Override
	public List<String> findDishesTypePanel() {
		return dishesTypeDao.findDishesTypePanel();
	}

	@Override
	public String copyDishesType(String deptId) {
		int deleteint=dishesTypeDao.deleteDishesTypeBydeptId(deptId);
		logger.debug("删除分店菜单"+deleteint);
		Department nowdept=departmentLogic.findDepartmentById(deptId);
		
		List<DishesType> typeList=dishesTypeDao.findDishesTypeBydeptId(nowdept.getParent().getId());
		if(typeList!=null && typeList.size()>0)
		{
			for (DishesType oldType:typeList) {
				DishesType newdt=new DishesType();
				newdt.setCorporation(oldType.getCorporation());
				newdt.setDeleted(0);
				newdt.setDepartment(nowdept);
				newdt.setDishesType(oldType.getDishesType());
				newdt.setName(oldType.getName());
				newdt.setRid(oldType.getRid());
				dishesTypeDao.save(newdt);
			}
		
		}
		return "SUCCESS";
	}

	@Override
	public String copyDishes(String userId) {
		
		
		//查询管理的部门
		Department dept=departmentLogic.findDepartmentByAdminUserId(userId);
		
		
		int deleteint=dishesDao.deleteDishesBydeptId(dept.getId());
		logger.debug("删除分店菜品"+deleteint);
		Department nowdept=departmentLogic.findDepartmentById(dept.getId());
		
		List<Dishes> typeList=dishesDao.findDishesBydeptId(nowdept.getParent().getId());
		if(typeList!=null && typeList.size()>0)
		{
			for (Dishes oldType:typeList) {
				Dishes newdt=new Dishes();
				newdt.setCorporation(oldType.getCorporation());
				newdt.setDeleted(0);
				newdt.setDepartment(nowdept);
				newdt.setName(oldType.getName());
				newdt.setRid(oldType.getRid());
				newdt.setDescription(oldType.getDescription());
				newdt.setDishesType(oldType.getDishesType());
				newdt.setPhoto(oldType.getPhoto());
				newdt.setPrice(oldType.getPrice());
				newdt.setStatus(oldType.getStatus());
				newdt.setTaste(oldType.getTaste());
				dishesDao.save(newdt);
			}
		
		}
		return "SUCCESS";
	}
}
