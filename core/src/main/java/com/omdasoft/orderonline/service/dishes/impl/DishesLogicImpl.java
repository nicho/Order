package com.omdasoft.orderonline.service.dishes.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.omdasoft.orderonline.dao.dishes.DishesDao;
import com.omdasoft.orderonline.dao.dishes.DishesTypeDao;
import com.omdasoft.orderonline.dao.dishes.OrdersDishesDao;
import com.omdasoft.orderonline.dao.org.CorporationDao;
import com.omdasoft.orderonline.dao.org.DepartmentDao;
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
import com.omdasoft.orderonline.service.dishes.DishesLogic;
import com.omdasoft.orderonline.service.org.CorporationLogic;
import com.omdasoft.orderonline.util.StringUtil;

public class DishesLogicImpl implements DishesLogic {

	private DishesDao dishesDao;
	private OrdersDishesDao ordersDishesDao;
	private DishesTypeDao dishesTypeDao;
	private CorporationLogic corporationLogic;
	private DepartmentDao departmentDao;
	private CorporationDao corporationDao;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	protected DishesLogicImpl(CorporationDao corporationDao,DishesDao dishesDao,DishesTypeDao dishesTypeDao,OrdersDishesDao ordersDishesDao,CorporationLogic corporationLogic,DepartmentDao departmentDao) {
		this.dishesTypeDao=dishesTypeDao;
		this.dishesDao=dishesDao;
		this.ordersDishesDao=ordersDishesDao;
		this.corporationLogic=corporationLogic;
		this.departmentDao=departmentDao;
		this.corporationDao=corporationDao;
	}

	@Override
	public Dishes saveDishes(UserContext context, Dishes dishes) {
		if(!StringUtil.isEmptyString(context.getCorporationId()))
		{
			dishes.setCorporation(corporationLogic.findCorporationById(context.getCorporationId()));
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

		if(!StringUtil.isEmptyString(criteria.getDeptId()))
		{
			Department dept=departmentDao.findById(Department.class, criteria.getDeptId());
			if(dept!=null && dept.getCorporation()!=null)
			criteria.setCorporationId(dept.getCorporation().getId());
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
	public int deleteDishes(SysUser caller, String id) {
		Dishes dishes = dishesDao.findDishesByrId(id);
		if (dishes != null) {
			dishes.setDeleted(1);
			dishesDao.update(dishes);
			return 0;
		} else
			return 1;
	}
	@Override
	public int deleteDishesType(SysUser caller, String id) {
		DishesType dishes = dishesTypeDao.findDishesTypeByrId(id);
		if (dishes != null) {
			dishes.setDeleted(1);
			dishesTypeDao.update(dishes);
			return 0;
		} else
			return 1;
	}
	@Override
	public DishesType saveDishesType(UserContext context, DishesType dishesType) {
		if(!StringUtil.isEmptyString(context.getCorporationId()))
		{
			dishesType.setCorporation(corporationLogic.findCorporationById(context.getCorporationId()));
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

		if(!StringUtil.isEmptyString(criteria.getDeptId()))
		{
			Department dept=departmentDao.findById(Department.class, criteria.getDeptId());
			if(dept!=null && dept.getCorporation()!=null)
			criteria.setCorporationId(dept.getCorporation().getId());
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
	public Dishes findDishesByrId(String rid) {
		return dishesDao.findDishesByrId(rid);
	}

	@Override
	public DishesType findDishesTypeByrId(String rid) {
		return dishesTypeDao.findDishesTypeByrId(rid);
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
}
