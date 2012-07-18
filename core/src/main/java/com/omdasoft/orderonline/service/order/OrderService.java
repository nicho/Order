package com.omdasoft.orderonline.service.order;

import java.util.Date;

import com.omdasoft.orderonline.domain.order.Orders;
import com.omdasoft.orderonline.domain.rest.InvokeHistory;
import com.omdasoft.orderonline.model.common.PageStore;
import com.omdasoft.orderonline.model.order.OrderAndDishesModel;
import com.omdasoft.orderonline.model.order.OrderListCriteria;
import com.omdasoft.orderonline.model.order.OrderModel;
import com.omdasoft.orderonline.model.order.OrderReturnModel;
import com.omdasoft.orderonline.model.order.UpdateOrderReturnModel;
import com.omdasoft.orderonline.model.order.UploadOrderReturnModel;
import com.omdasoft.orderonline.model.user.UserContext;
import com.omdasoft.orderonline.model.vo.OrderVo;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface OrderService {

	/**
	 * 保存(添加和修改)
	 * 
	 * @param context
	 * @param order
	 * @return
	 */
	public Orders saveOrders(UserContext context, OrderVo orderVo);

	/**
	 * 查找根据ID
	 * 
	 * @param id
	 * @return
	 */
	public Orders findOrderById(String id);

	/**
	 * 删除订单根据ID
	 * 
	 * @param id
	 * @return
	 */
	public String deleteOrder(UserContext context, String id);

	/**
	 * 未处理订单查询
	 * 
	 * @param context
	 * @param Orders
	 * @return
	 */
	public OrderReturnModel getUnhandledOrderList(String tokenId,
			String phonenumber);

	/**
	 * 订单列表
	 * 
	 * @param context
	 * @param Orders
	 * @return
	 */
	public PageStore<Orders> getOrderList(UserContext context,
			OrderListCriteria criteria);

	/**
	 * 修改订单状态
	 * 
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	public UpdateOrderReturnModel updateOrderStatus(String tokenId,
			String orderId, String orderStatus, String result);

	/**
	 * 保存调用历史
	 * 
	 * @param restApiName
	 * @param invokeTime
	 * @param invokeResult
	 * @param corporation
	 * @param department
	 * @return
	 */
	public InvokeHistory addInvokeHistory(String restApiName, Date invokeTime,
			String invokeResult, String tokenid);

	/**
	 * 根据电话查询，订单。和菜单
	 * 
	 * @param phone
	 * @return
	 */
	public OrderAndDishesModel getOrderAndDishesModelByPhone(String phone,String deptId);

	/**
	 * 保存订单（电话+点菜信息）
	 * 
	 * @param context
	 * @param order
	 * @return
	 */
	public Orders saveOrdersByPhoneDishes(UserContext context, OrderVo orderVo);

	/**
	 * 保存订单（订房信息）
	 * 
	 * @param context
	 * @param order
	 * @return
	 */
	public Orders saveOrdersByRoom(UserContext context, OrderVo orderVo);

	/**
	 * 读取点菜状态为已点，待读订单
	 * 
	 * @param context
	 * @param Orders
	 * @return
	 */
	public OrderReturnModel findUnreadorderwithdish(String tokenId);

	/**
	 * 修改订单点菜状态
	 * 
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	public UpdateOrderReturnModel updatehandleorderdishstatus(String tokenId,
			String orderId, String carteState);

	/**
	 * 上传订单
	 * 
	 * @param tokenid
	 * @param orderrid
	 * @return
	 */
	public UploadOrderReturnModel uploadorder(String tokenid,OrderModel ordermodel);

	/**
	 * 读取已取消，待读订单
	 * 
	 * @param tokenid
	 * @return
	 */
	public OrderReturnModel cancelunreadorder(String tokenid);
	/**
	 * 取消订单
	 * @param tokenid
	 * @param id
	 * @return
	 */
	public UpdateOrderReturnModel cancelorder(String tokenid, String id);
}
