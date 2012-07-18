package com.omdasoft.orderonline.service.order;

import java.util.Date;

import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.domain.rest.InvokeHistory;
import com.omdasoft.orderonline.domain.user.SysUser;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.order.CarteState;
import com.omdasoft.orderonline.model.order.OrderAndDishesModel;
import com.omdasoft.orderonline.model.order.OrderListCriteria;
import com.omdasoft.orderonline.model.order.OrderStatus;
import com.omdasoft.orderonline.model.order.UpdateOrderReturnModel;
import com.omdasoft.orderonline.model.user.UserContext;

public interface OrderLogic {

	/**
	 * 保存(添加和修改)
	 * @param context
	 * @param order
	 * @return
	 */
	public Orders save(SysUser caller, Orders Order);

	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public Orders findOrderById(String id);
	
	
	/**
	 * 删除订单根据ID
	 * @param id
	 * @return
	 */
	public String deleteOrder(SysUser caller,String id);
	/**
	 * 订单列表
	 * @param context
	 * @param Orders
	 * @return
	 */
	public PageStore<Orders> getOrderList(UserContext context,OrderListCriteria criteria);
	/**
	 * 订单列表（修改版：为接口）
	 * @param context
	 * @param Orders
	 * @return
	 */
	public PageStore<Orders> getOrderListToo(UserContext context,OrderListCriteria criteria);
	/**
	 * 处理订单结果反馈
	 * @param context
	 * @param Orders
	 * @return
	 */
	public UpdateOrderReturnModel processingOrdersResult(String orderId,OrderStatus status,String result);
	/**
	 * 处理订单结果反馈
	 * @param context
	 * @param Orders
	 * @return
	 */
	public UpdateOrderReturnModel processingOrdersResultCarteState(String orderId,CarteState status);
	/**
	 * 保存调用历史
	 * @param restApiName
	 * @param invokeTime
	 * @param invokeResult
	 * @param corporation
	 * @param department
	 * @return
	 */
	public InvokeHistory addInvokeHistory(String restApiName,Date invokeTime,String invokeResult,String tokenid);
	/**
	 * 根据电话查询，订单。和菜单
	 * @param phone
	 * @return
	 */
	public OrderAndDishesModel getOrderAndDishesModelByPhone(String phone,String deptId);
}


