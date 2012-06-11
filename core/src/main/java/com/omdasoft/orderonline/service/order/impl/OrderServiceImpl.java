package com.omdasoft.orderonline.service.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.omdasoft.orderonline.dao.dishes.OrdersDishesDao;
import com.omdasoft.orderonline.dao.org.CorporationDao;
import com.omdasoft.orderonline.dao.person.PersonDao;
import com.omdasoft.orderonline.dao.user.UserRoleDao;
import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.domain.order.OrdersDishes;
import com.omdasoft.orderonline.domain.person.Person;
import com.omdasoft.orderonline.domain.rest.InvokeHistory;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.domain.user.SysUserRole;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.order.CarteState;
import com.omdasoft.orderonline.model.order.DishesModel;
import com.omdasoft.orderonline.model.order.OrderAndDishesModel;
import com.omdasoft.orderonline.model.order.OrderListCriteria;
import com.omdasoft.orderonline.model.order.OrderModel;
import com.omdasoft.orderonline.model.order.OrderReturnModel;
import com.omdasoft.orderonline.model.order.OrderSource;
import com.omdasoft.orderonline.model.order.OrderStatus;
import com.omdasoft.orderonline.model.order.UpdateOrderReturnModel;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.user.UserRole;
import com.omdasoft.orderonline.model.vo.OrderDishesVo;
import com.omdasoft.orderonline.model.vo.OrderVo;
import com.omdasoft.orderonline.service.dishes.DishesLogic;
import com.omdasoft.orderonline.service.order.OrderLogic;
import com.omdasoft.orderonline.service.order.OrderService;
import com.omdasoft.orderonline.service.org.DepartmentLogic;
import com.omdasoft.orderonline.service.user.UserLogic;
import com.omdasoft.orderonline.util.DateUtil;
import com.omdasoft.orderonline.util.StringUtil;

@Transactional
public class OrderServiceImpl implements OrderService {
	private final OrderLogic orderLogic;
	private final UserLogic userLogic;
	private final PersonDao personDao;
	private final DepartmentLogic departmentLogic;
	private final DishesLogic dishesLogic;
	private final OrdersDishesDao ordersDishesDao;
	private UserRoleDao userRoleDao;
	private CorporationDao corporationDao;
	

	@Inject
	public OrderServiceImpl(OrderLogic orderLogic, UserLogic userLogic,
			PersonDao personDao, DepartmentLogic departmentLogic,
			DishesLogic dishesLogic, OrdersDishesDao ordersDishesDao,UserRoleDao userRoleDao,CorporationDao corporationDao) {
		this.userLogic = userLogic;
		this.orderLogic = orderLogic;
		this.personDao = personDao;
		this.departmentLogic = departmentLogic;
		this.dishesLogic = dishesLogic;
		this.ordersDishesDao = ordersDishesDao;
		this.userRoleDao=userRoleDao;
		this.corporationDao=corporationDao;
	}

	@Override
	public Orders saveOrders(UserContext context, OrderVo orderVo) {
		SysUser ux = null;
		if (context != null && context.getUserId() != null)
			ux = userLogic.findUserById(context.getUserId());

		Orders order = null;
		if (!StringUtil.isEmptyString(orderVo.getId())) {
			order = orderLogic.findOrderById(orderVo.getId());
		} else {
			order = new Orders();
		}

		order.setAmountOfClient(orderVo.getAmountOfClient());
		order.setCity(orderVo.getCity());
		order.setFavoriteRoom(orderVo.getFavoriteRoom());
		order.setMemo(orderVo.getMemo());

		if (!StringUtil.isEmptyString(orderVo.getContactPersonName())
				&& !StringUtil.isEmptyString(orderVo.getContactPersonPhone())) {
			
			Person p1=personDao.findPersonByPhone(orderVo.getContactPersonPhone());
			if(p1==null)
			{
			    p1 = new Person();
				p1.setName(orderVo.getContactPersonName());
				p1.setPhone(orderVo.getContactPersonPhone());
				p1.setSex(orderVo.getContactPersonSex());
				p1 = personDao.save(p1);
			}
			else
			{
				p1.setName(orderVo.getContactPersonName());
				p1.setSex(orderVo.getContactPersonSex());
				p1=personDao.update(p1);
			}
			
			
			order.setContactPerson(p1);
		}

		if (!StringUtil.isEmptyString(orderVo.getOrderPersonName())
				&& !StringUtil.isEmptyString(orderVo.getOrderPersonPhone())) {
			
			Person p2=personDao.findPersonByPhone(orderVo.getOrderPersonPhone());
			if(p2==null)
			{
				p2 = new Person();
				p2.setName(orderVo.getOrderPersonName());
				p2.setPhone(orderVo.getOrderPersonPhone());
				p2.setSex(orderVo.getOrderPersonSex());
				p2 = personDao.save(p2);
			}
			else
			{
				p2.setName(orderVo.getOrderPersonName());
				p2.setSex(orderVo.getOrderPersonSex());
				p2=personDao.update(p2);
			}
			order.setOrderPerson(p2);
		}

		order.setPlaceOrderTime(new Date());
		order.setReserveTimeDate(orderVo.getReserveTimeDate());
		order.setReserveTimeDateH(orderVo.getReserveTimeDateH());
		order.setReserveTimeDateS(orderVo.getReserveTimeDateS());
		order.setOrderStatus(orderVo.getOrderStatus());

		if (!StringUtil.isEmptyString(orderVo.getRestaurantId())) {
			order.setDepartment(departmentLogic.findDepartmentById(orderVo.getRestaurantId()));
			order.setCorporation(order.getDepartment().getCorporation());
		}
		

	
		
		order = orderLogic.save(ux, order);

		if (orderVo.getOrdersDishesList() != null
				&& orderVo.getOrdersDishesList().size() > 0) {
			for (OrderDishesVo dishes : orderVo.getOrdersDishesList()) {
				OrdersDishes od = new OrdersDishes();
				od.setDishes(dishesLogic.findDishesById(dishes.getDishesId()));
				od.setNumber(dishes.getNumber());
				od.setOrders(order);
				od.setTaste(dishes.getTaste());
				od.setUnit(dishes.getUnit());
				od.setPrice(dishes.getPrice());
				od.setDeleted(false);
				ordersDishesDao.save(od);
			}
		}

		return order;
	}

	@Override
	public Orders findOrderById(String id) {
		return orderLogic.findOrderById(id);
	}

	@Override
	public String deleteOrder(UserContext context, String id) {
		SysUser ux = null;
		if (context != null && context.getUserId() != null)
			ux = userLogic.findUserById(context.getUserId());
		return orderLogic.deleteOrder(ux, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.omdasoft.orderonline.service.order.OrderService#getUnhandledOrderList
	 * (com.omdasoft.orderonline.model.user.UserContext)
	 */
	@Override
	public OrderReturnModel getUnhandledOrderList(String tokenId,String phonenumber) {
		OrderReturnModel returnModel = new OrderReturnModel();

		try {

			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setData(null);
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			} else {
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {
					OrderListCriteria criteria = new OrderListCriteria();
					criteria.setOrderStatus(OrderStatus.UNHANDLED);
					if(!StringUtil.isEmptyString(phonenumber))
					{
						criteria.setPhone(phonenumber);
					}
					// done...加入机构,分店过滤数据
					
					UserContext uc=new UserContext();
					if(user.getCorporation()!=null)
					uc.setCorporationId(user.getCorporation().getId());
					if(user.getStaff()!=null && user.getStaff().getDepartment()!=null)
					uc.setDeptId(user.getStaff().getDepartment().getId());
					
					List<SysUserRole> lt = userRoleDao.findUserRoleByUserId(user.getId());
					
					if (lt.size() > 0) {
						UserRole [] urole=new UserRole[lt.size()];
						for (int i = 0; i < lt.size(); i++) {
							urole[i]=lt.get(i).getRole().getName();
						}
						uc.setUserRoles(urole);
					}
				
					PageStore<Orders> orderList = orderLogic.getOrderList(uc,criteria);
					List<OrderModel> ordermodel = new ArrayList<OrderModel>();
					for (Orders order : orderList.getResultList()) {
						OrderModel model = new OrderModel();
						model.setId(order.getId());
						model.setCode(order.getCode());
						model.setAmountOfClient(order.getAmountOfClient());
						model.setFavoriteRoom(order.getFavoriteRoom());
						model.setMemo(order.getMemo());
						if(order.getPlaceOrderTime()!=null)
						model.setPlaceOrderTime(DateUtil.formatData(null, order.getPlaceOrderTime()));
						if(order.getReserveTimeDate()!=null && order.getReserveTimeDateH()!=null && order.getReserveTimeDateS()!=null)
						model.setReserveTimeDate(order.getReserveTimeDate()+" "+order.getReserveTimeDateH()+":"+order.getReserveTimeDateS());
						if(order.getOrderPerson()!=null)
						{
							model.setOrderPersonPhone(order.getOrderPerson().getPhone());
							model.setOrderPersonName(order.getOrderPerson().getName());
							model.setOrderPersonSex(order.getOrderPerson().getSex());
						}
						if(order.getContactPerson()!=null)
						{
							model.setContactPersonPhone(order.getContactPerson().getPhone());
							model.setContactPersonName(order.getContactPerson().getName());
							model.setContactPersonSex(order.getContactPerson().getSex());
						}
						List<OrdersDishes> dishesList = ordersDishesDao
								.findOrdersDishesByOrderId(order.getId());
						if (dishesList != null && dishesList.size() > 0) {
							List<DishesModel> dishesmodelList = new ArrayList<DishesModel>();
							for (OrdersDishes dishes : dishesList) {
								DishesModel dishesmodel = new DishesModel();
								dishesmodel.setDishesId(dishes.getDishes()
										.getId());
								dishesmodel.setDishesName(dishes.getDishes()
										.getName());
								if (dishes.getDishes().getDishesType() != null)
									dishesmodel.setDishesType(dishes
											.getDishes().getDishesType()
											.getName().toString());
								dishesmodel.setNumber(dishes.getNumber());
								dishesmodel.setPrice(dishes.getDishes()
										.getPrice());
								dishesmodel.setTaste(dishes.getTaste());
								dishesmodel.setUnit(dishes.getUnit());
								dishesmodelList.add(dishesmodel);
							}

							model.setDishesList(dishesmodelList);
						}

						ordermodel.add(model);
					}

					returnModel.setData(ordermodel);
					returnModel.setFlag("0");
					returnModel.setException_code(null);
					returnModel.setException_msg(null);
				} else {
					returnModel.setData(null);
					returnModel.setFlag("1");
					returnModel.setException_code("11");
					returnModel.setException_msg("登录失效");
				}
			}

		} catch (Exception e) {
			returnModel.setData(null);
			returnModel.setFlag("1");
			returnModel.setException_code("12");
			returnModel.setException_msg(e.getStackTrace().toString());
		}

		return returnModel;
	}

	@Override
	public PageStore<Orders> getOrderList(UserContext context,
			OrderListCriteria criteria) {
		return orderLogic.getOrderList(context, criteria);
	}

	@Override
	public UpdateOrderReturnModel updateOrderStatus(String tokenId,
			String orderId, String orderStatus,String result) {
		UpdateOrderReturnModel returnModel = new UpdateOrderReturnModel();
		try {
			OrderStatus status = null;


			if (StringUtil.isEmptyString(tokenId)) {
				returnModel.setFlag("1");
				returnModel.setException_code("10");
				returnModel.setException_msg("未登录");
			} else {
				int intstatus=0;
				if(!StringUtil.isEmptyString(orderStatus))
				{
					intstatus=Integer.parseInt(orderStatus);
					if (intstatus == 0)
						status = OrderStatus.UNHANDLED;
					else if (intstatus == 1)
						status = OrderStatus.SUCCESS;
					else if (intstatus == 2)
						status = OrderStatus.FAILURE;
					else if (intstatus == 3)
						status = OrderStatus.NOTCONSUMPR;
					else if (intstatus == 4)
						status = OrderStatus.HASCONSUMER;
				}
				else
				{
					returnModel.setFlag("1");
					returnModel.setException_code("12");
					returnModel.setException_msg("传入状态为空!");
				}
				
				SysUser user = userLogic.getSysUserByTokenId(tokenId);
				if (user != null) {
					
					return orderLogic.processingOrdersResult(orderId, status,result);
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
	public InvokeHistory addInvokeHistory(String restApiName, Date invokeTime,
			String invokeResult,String tokenid) {
		return orderLogic.addInvokeHistory(restApiName, invokeTime, invokeResult,tokenid);
	}

	@Override
	public OrderAndDishesModel getOrderAndDishesModelByPhone(String phone) {
		return orderLogic.getOrderAndDishesModelByPhone(phone);
	}

	@Override
	public Orders saveOrdersByPhoneDishes(UserContext context, OrderVo orderVo) {
		SysUser ux = null;
		if (context != null && context.getUserId() != null)
			ux = userLogic.findUserById(context.getUserId());

		Orders order = null;
		if (!StringUtil.isEmptyString(orderVo.getId())) {
			order = orderLogic.findOrderById(orderVo.getId());
			
			//删除以前点的菜
			List<OrdersDishes> orderdishesList=ordersDishesDao.findOrdersDishesByOrderId(orderVo.getId());
			if(orderdishesList!=null && orderdishesList.size()>0)
			{
				for (OrdersDishes od:orderdishesList) {
					ordersDishesDao.delete(od);
				}
			}
			
		} else {
			order = new Orders();
			
			if (!StringUtil.isEmptyString(orderVo.getOrderPersonPhone())) {
				
				Person p2=personDao.findPersonByPhone(orderVo.getOrderPersonPhone());
				if(p2==null)
				{
					p2 = new Person();
					p2.setName(orderVo.getOrderPersonName());
					p2.setPhone(orderVo.getOrderPersonPhone());
					p2.setSex(orderVo.getOrderPersonSex());
					p2 = personDao.save(p2);
				}
				else
				{
					p2.setName(orderVo.getOrderPersonName());
					p2.setSex(orderVo.getOrderPersonSex());
					p2=personDao.update(p2);
				}
				order.setOrderPerson(p2);
			}
			
			order.setCorporation(corporationDao.getDeCorp());
		}


		order.setOrderSource(OrderSource.ONLINE);
		
		if (orderVo.getOrdersDishesList() != null	&& orderVo.getOrdersDishesList().size() > 0) 
			order.setCarteState(CarteState.WAITREAD);
		else
			order.setCarteState(CarteState.NOTPOINT);
		
		
		order.setOrderStatus(OrderStatus.UNHANDLED);

		

		
		
		order = orderLogic.save(ux, order);

	
		
		if (orderVo.getOrdersDishesList() != null	&& orderVo.getOrdersDishesList().size() > 0) {
			for (OrderDishesVo dishes : orderVo.getOrdersDishesList()) {
				OrdersDishes od = new OrdersDishes();
				od.setDishes(dishesLogic.findDishesById(dishes.getDishesId()));
				od.setNumber(dishes.getNumber());
				od.setOrders(order);
				od.setTaste(dishes.getTaste());
				od.setUnit(dishes.getUnit());
				od.setPrice(dishes.getPrice());
				od.setDeleted(false);
				ordersDishesDao.save(od);
			}
		}

		return order;
	}

	@Override
	public Orders saveOrdersByRoom(UserContext context, OrderVo orderVo) {
		SysUser ux = null;
		if (context != null && context.getUserId() != null)
			ux = userLogic.findUserById(context.getUserId());

		Orders order = null;
		if (!StringUtil.isEmptyString(orderVo.getId())) {
			order = orderLogic.findOrderById(orderVo.getId());
		} else {
			order = new Orders();
		}

		order.setAmountOfClient(orderVo.getAmountOfClient());
		order.setCity(orderVo.getCity());
		order.setFavoriteRoom(orderVo.getFavoriteRoom());
		order.setMemo(orderVo.getMemo());

		if (!StringUtil.isEmptyString(orderVo.getContactPersonName())
				&& !StringUtil.isEmptyString(orderVo.getContactPersonPhone())) {
			
			Person p1=personDao.findPersonByPhone(orderVo.getContactPersonPhone());
			if(p1==null)
			{
			    p1 = new Person();
				p1.setName(orderVo.getContactPersonName());
				p1.setPhone(orderVo.getContactPersonPhone());
				p1.setSex(orderVo.getContactPersonSex());
				p1 = personDao.save(p1);
			}
			else
			{
				p1.setName(orderVo.getContactPersonName());
				p1.setSex(orderVo.getContactPersonSex());
				p1=personDao.update(p1);
			}
			
			
			order.setContactPerson(p1);
		}

		if (!StringUtil.isEmptyString(orderVo.getOrderPersonName())
				&& !StringUtil.isEmptyString(orderVo.getOrderPersonPhone())) {
			
			Person p2=personDao.findPersonByPhone(orderVo.getOrderPersonPhone());
			if(p2==null)
			{
				p2 = new Person();
				p2.setName(orderVo.getOrderPersonName());
				p2.setPhone(orderVo.getOrderPersonPhone());
				p2.setSex(orderVo.getOrderPersonSex());
				p2 = personDao.save(p2);
			}
			else
			{
				p2.setName(orderVo.getOrderPersonName());
				p2.setSex(orderVo.getOrderPersonSex());
				p2=personDao.update(p2);
			}
			order.setOrderPerson(p2);
		}

		order.setPlaceOrderTime(new Date());
		order.setReserveTimeDate(orderVo.getReserveTimeDate());
		order.setReserveTimeDateH(orderVo.getReserveTimeDateH());
		order.setReserveTimeDateS(orderVo.getReserveTimeDateS());
		
		if(orderVo.getOrderStatus()!=null)
		order.setOrderStatus(orderVo.getOrderStatus());

		if (!StringUtil.isEmptyString(orderVo.getRestaurantId())) {
			order.setDepartment(departmentLogic.findDepartmentById(orderVo.getRestaurantId()));
			order.setCorporation(order.getDepartment().getCorporation());
		}
		
		
		order = orderLogic.save(ux, order);


		return order;
	}

}
