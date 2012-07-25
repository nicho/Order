package com.omdasoft.orderonline.service.order.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.omdasoft.orderonline.dao.dishes.OrdersDishesDao;
import com.omdasoft.orderonline.dao.order.OrderDao;
import com.omdasoft.orderonline.dao.rest.InvokeHistoryDao;
import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.domain.org.Department;
import com.omdasoft.orderonline.domain.rest.InvokeHistory;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.order.CarteState;
import com.omdasoft.orderonline.model.order.OrderAndDishesModel;
import com.omdasoft.orderonline.model.order.OrderListCriteria;
import com.omdasoft.orderonline.model.order.OrderStatus;
import com.omdasoft.orderonline.model.order.UpdateOrderReturnModel;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.service.order.OrderLogic;
import com.omdasoft.orderonline.service.org.DepartmentLogic;
import com.omdasoft.orderonline.service.user.UserLogic;
import com.omdasoft.orderonline.util.DateUtil;
import com.omdasoft.orderonline.util.StringUtil;

public class OrderLogicImpl implements OrderLogic{
	private OrderDao orderDao;
	private OrdersDishesDao ordersDishesDao;
	private InvokeHistoryDao invokeHistoryDao;
	private UserLogic userLogic;
	private DepartmentLogic departmentLogic;


	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Inject
	protected OrderLogicImpl(OrderDao orderDao,InvokeHistoryDao invokeHistoryDao,UserLogic userLogic,OrdersDishesDao ordersDishesDao,DepartmentLogic departmentLogic){
		this.orderDao = orderDao;
		this.invokeHistoryDao=invokeHistoryDao;
		this.userLogic=userLogic;
		this.ordersDishesDao=ordersDishesDao;
		this.departmentLogic=departmentLogic;
	}
	
	@Override
	public Orders save(SysUser caller, Orders order) {
		Date currTime = DateUtil.getTime();
		String millsTime=currTime.getTime()+"";
		String orderCode = DateUtil.formatData("yyyyMMddhhmmss", currTime)+millsTime.substring(millsTime.length()-3);
		if (StringUtil.isEmptyString(order.getId())) {
			// Create
			order.setDeleted(false);//正常状态，没有删除为0
			order.setCode(orderCode);//用当前时间作为订单编号
			orderDao.save(order);
		} else {

			orderDao.update(order);
		}

		return order;
	}
	

	@Override
	public Orders findOrderById(String id) {
		try {
			return  orderDao.findById(Orders.class, id);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public String deleteOrder(SysUser caller,String id) {
	
		Orders order = orderDao.findById(Orders.class, id);
		order.setDeleted(true);

		order= orderDao.update(order);
		return order.getId();
	}

	@Override
	public PageStore<Orders> getOrderList(UserContext context,
			OrderListCriteria criteria) {

		if(UserRole.CORP_ADMIN==context.getLastRole())
		{
			criteria.setCorpId(context.getCorporationId());
		}
		else if(UserRole.DEPT_MGR==context.getLastRole())
		{
			//查询管理的部门
			Department dept=departmentLogic.findDepartmentByAdminUserId(context.getUserId());
			if(dept!=null)
			{
				criteria.setDeptId(dept.getId());
			}
		}
		
		return orderDao.queryOrderPageAction(criteria);
	}
	@Override
	public PageStore<Orders> getOrderListToo(UserContext context,
			OrderListCriteria criteria) {

		if(!StringUtil.isEmptyString(criteria.getPhone()))
		{
			criteria.setCorpId(context.getCorporationId());
		}
		else
		{
			if(UserRole.CORP_ADMIN==context.getLastRole())
			{
				criteria.setCorpId(context.getCorporationId());
			}
			else if(UserRole.DEPT_MGR==context.getLastRole())
			{
				//查询管理的部门
				Department dept=departmentLogic.findDepartmentByAdminUserId(context.getUserId());
				if(dept!=null)
				{
					criteria.setDeptId(dept.getId());
				}
			}
		}
		return orderDao.queryOrderPageAction(criteria);
	}
	@Override
	public UpdateOrderReturnModel processingOrdersResult(String orderId, OrderStatus status,String result) {
		Orders order=orderDao.findByOrdersId(orderId);
		UpdateOrderReturnModel model=new UpdateOrderReturnModel();
		if(order!=null && status!=null)
		{
			order.setOrderStatus(status);
			order.setHandleTime(new Date());
			order.setModifyTime(new Date());
			if(!StringUtil.isEmptyString(order.getResult()))
				order.setResult(order.getResult()+DateUtil.formatData(null, new Date())+"  "+status.getDisplayName()+"  "+result+"|");
			else
				order.setResult("| "+DateUtil.formatData(null, new Date())+"  "+status.getDisplayName()+"  "+result+"|");
			orderDao.update(order);
			
			model.setFlag("0");
			return model;
		}
		else
		{
			model.setFlag("1");
			model.setException_code("12");
			model.setException_msg("找不到订单,或者状态为null");
			return model;
		}
		
	
		
	}

	@Override
	public InvokeHistory addInvokeHistory(String restApiName, Date invokeTime,
			String invokeResult,String tokenid) {
		InvokeHistory ih=new InvokeHistory();
		ih.setRestApiName(restApiName);
		ih.setInvokeTime(invokeTime);
		ih.setInvokeResult(invokeResult);
		if(!StringUtil.isEmptyString(tokenid))
		{
			SysUser user = userLogic.getSysUserByTokenIdNotCheckDate(tokenid);
			if(user!=null)
			{
				ih.setCorporation(user.getCorporation());
				if(user.getStaff()!=null)
				ih.setDepartment(user.getStaff().getDepartment());
			}
		}

		return invokeHistoryDao.save(ih);
	}

	@Override
	public OrderAndDishesModel getOrderAndDishesModelByPhone(String phone,String deptId) {
		Orders order=orderDao.findByOrdersPhone(phone,deptId);
		if(order!=null)
		{
			OrderAndDishesModel model=new OrderAndDishesModel();
			model.setOrder(order);
			
			if(order.getCarteState()!=CarteState.NOTPOINT)
			{
				List<OrdersDishes> dishesList=ordersDishesDao.findOrdersDishesByOrderId(order.getId());
				if(dishesList!=null && dishesList.size()>0)
				model.setOrdersDishesList(dishesList);
			}
			return model;
		}
		return null;
	}

	@Override
	public UpdateOrderReturnModel processingOrdersResultCarteState(
			String orderId, CarteState status) {
		Orders order=orderDao.findByOrdersId(orderId);
		UpdateOrderReturnModel model=new UpdateOrderReturnModel();
		if(order!=null && status!=null)
		{
			order.setCarteState(status);
			order.setHandleTime(new Date());
			order.setModifyTime(new Date());

			orderDao.update(order);
			
			model.setFlag("0");
			return model;
		}
		else
		{
			model.setFlag("1");
			model.setException_code("12");
			model.setException_msg("找不到订单,或者状态为null");
			return model;
		}
	}


}
