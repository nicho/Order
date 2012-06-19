package com.omdasoft.orderonline.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.omdasoft.orderonline.gwt.order.server.login.ImageUrlActionHandler;
import com.omdasoft.orderonline.model.order.DishcategoryReturnModel;
import com.omdasoft.orderonline.model.order.DishesReturnModel;
import com.omdasoft.orderonline.model.order.LoginReturnModel;
import com.omdasoft.orderonline.model.order.OrderModel;
import com.omdasoft.orderonline.model.order.OrderReturnModel;
import com.omdasoft.orderonline.model.order.UpdateOrderReturnModel;
import com.omdasoft.orderonline.model.order.UploadOrderReturnModel;
import com.omdasoft.orderonline.service.dishes.DishesService;
import com.omdasoft.orderonline.service.order.OrderService;
import com.omdasoft.orderonline.service.user.UserService;
 
@Path("/service")
public class CommonController {
	OrderService orderService;
	DishesService dishesService;
	UserService userService;
	@Inject
	public  CommonController(OrderService orderService,DishesService dishesService,UserService userService){
		this.orderService=orderService;
		this.dishesService=dishesService;
		this.userService=userService;
	}

    @POST
    @Path("/login")
    @Produces("application/json")
    public LoginReturnModel loginUser(@HeaderParam("userid") String userid, @HeaderParam("password") String password){
    	LoginReturnModel model=userService.loginRestful(userid, password);
    	String tokenid=null;
    	if(model!=null && model.getData()!=null)
    	{
    		Properties properties = new Properties();
    		try {
    			properties.load(ImageUrlActionHandler.class	.getResourceAsStream("configuration.properties"));
    			model.getData().setUrl(properties.getProperty("restUrl"));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	tokenid=model.getData().getTokenid();
    	}
    	orderService.addInvokeHistory("loginUser", new Date(), model.getFlag(),tokenid);
    	return model;
    }
    /**
     * 获取未处理订单查询
     * @param tokenid
     * @return
     */
    @GET
    @Path("/unorder")
    @Produces("application/json")
    public OrderReturnModel getUnhandledOrderAll(@HeaderParam("tokenid") String tokenid){
    	OrderReturnModel model=orderService.getUnhandledOrderList(tokenid,null);
    	if(model.getData()!=null)
    		orderService.addInvokeHistory("getUnhandledOrder", new Date(), "共获取"+model.getData().size()+"条数据",tokenid);
    	else
    		orderService.addInvokeHistory("getUnhandledOrder", new Date(), model.getFlag(),tokenid);
    	return model;
    }
    /**
     * 获取未处理订单查询（通过手机号）
     * @param tokenid
     * @return
     */
    @GET
    @Path("/unorder/by/{phonenumber}")
    @Produces("application/json")
    public OrderReturnModel getUnhandledOrderByPhone(@HeaderParam("tokenid") String tokenid,@PathParam("phonenumber") String phonenumber){
    	OrderReturnModel model=orderService.getUnhandledOrderList(tokenid,phonenumber);
    	if(model.getData()!=null)
    		orderService.addInvokeHistory("getUnhandledOrder", new Date(), "共获取"+model.getData().size()+"条数据",tokenid);
    	else
    		orderService.addInvokeHistory("getUnhandledOrder", new Date(), model.getFlag(),tokenid);
    	return model;
    }
	/**
	 * 修改订单状态
	 * @param tokenid
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
    @POST
    @Path("/handleorder/{id}")
    @Produces("application/json")
    public UpdateOrderReturnModel updateOrderStatus(@HeaderParam("tokenid") String tokenid,@PathParam("id") String id, @HeaderParam("status") String status,@HeaderParam("result") String result){
    		try {
    			UpdateOrderReturnModel model= orderService.updateOrderStatus(tokenid,id, status,new String(result.getBytes("iso-8859-1"),"utf8"));
    			orderService.addInvokeHistory("updateOrderStatus", new Date(), model.getFlag(),tokenid);
    			return model;
    		} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				UpdateOrderReturnModel returnModel=new UpdateOrderReturnModel();
				returnModel.setFlag("1");
				returnModel.setException_code("12");
				returnModel.setException_msg("文字转换错误!");
				return returnModel;
			}
    }
    /**
     * 菜品类别添加
     * @param tokenid
     * @param category_id
     * @param category_name
     * @return
     */
    @POST
    @Path("/dishcategory")
    @Produces("application/json")
    public DishcategoryReturnModel addDishcategory(@HeaderParam("tokenid") String tokenid,@HeaderParam("category_rid") String category_rid, @HeaderParam("category_name") String category_name,@HeaderParam("action") String action){
    	
    	if(action!=null && "PUT".equals(action.toUpperCase()))
    	{
    		DishcategoryReturnModel model= dishesService.updateDishcategory(tokenid, category_rid, category_name);
    		orderService.addInvokeHistory("updateDishcategory", new Date(), model.getFlag(),tokenid);
    		return model;
    	}
    	else if(action!=null && "DELETE".equals(action.toUpperCase()))
    	{
    		DishcategoryReturnModel model=  dishesService.deleteDishcategory(tokenid, category_rid);
    		orderService.addInvokeHistory("deleteDishcategory", new Date(), model.getFlag(),tokenid);
    		return model;
    	}
    	else
    	{
    		DishcategoryReturnModel model=  dishesService.addDishcategory(tokenid, category_rid, category_name);
    		orderService.addInvokeHistory("addDishcategory", new Date(), model.getFlag(),tokenid);
    		return model;
    	}
    }
    
    /**
     * 菜品类别修改
     * @param tokenid
     * @param category_id
     * @param category_name
     * @return
     */
    @PUT
    @Path("/dishcategory/{rid}")
    @Produces("application/json")
    public DishcategoryReturnModel updateDishcategory(@HeaderParam("tokenid") String tokenid,@PathParam("rid") String rid, @HeaderParam("category_name") String category_name){
    	DishcategoryReturnModel model=dishesService.updateDishcategory(tokenid, rid, category_name);
    	orderService.addInvokeHistory("updateDishcategory", new Date(), model.getFlag(),tokenid);
    	return model;
    }
    
    /**
     * 菜品类别删除
     * @param tokenid
     * @param category_id
     * @return
     */
    @DELETE
    @Path("/dishcategory/{rid}")
    @Produces("application/json")
    public DishcategoryReturnModel deleteDishcategory(@HeaderParam("tokenid") String tokenid,@PathParam("rid") String rid){
    	DishcategoryReturnModel model= dishesService.deleteDishcategory(tokenid, rid);
    	orderService.addInvokeHistory("deleteDishcategory", new Date(), model.getFlag(),tokenid);
    	return model;
    }
    /**
     * 菜品类别查询
     * @param tokenid
     * @return
     */
    @GET
    @Path("/dishcategory")
    @Produces("application/json")
    public DishcategoryReturnModel findDishcategory(@HeaderParam("tokenid") String tokenid){
    	DishcategoryReturnModel model= dishesService.findDishcategory(tokenid);
    	if(model.getData()!=null)
    		orderService.addInvokeHistory("findDishcategory", new Date(),  "共获取"+model.getData().size()+"条数据",tokenid);
    	else
    		orderService.addInvokeHistory("findDishcategory", new Date(), model.getFlag(),tokenid);
    	return model;
    }
    /**
     * 菜品添加
     * @param tokenid
     * @param dishes_id
     * @param dishes_name
     * @return
     */
    @POST
    @Path("/dish")
    @Produces("application/json")
    public DishesReturnModel addDish(@HeaderParam("tokenid") String tokenid,@HeaderParam("dishes_id") String dishes_id, @HeaderParam("dishes_name") String dishes_name,@HeaderParam("action") String action){
    	if(action!=null && "PUT".equals(action.toUpperCase()))
    	{
    		DishesReturnModel model= dishesService.updateDishes(tokenid, dishes_id, dishes_name);
    		orderService.addInvokeHistory("updateDishes", new Date(), model.getFlag(),tokenid);
    		return model;
    	}
    	else if(action!=null && "DELETE".equals(action.toUpperCase()))
    	{
    		DishesReturnModel model= dishesService.deleteDishes(tokenid, dishes_id);
    		orderService.addInvokeHistory("deleteDishes", new Date(), model.getFlag(),tokenid);
    		return model;
    	}
    	else
    	{
    		DishesReturnModel model= dishesService.addDishes(tokenid, dishes_id, dishes_name);
    		orderService.addInvokeHistory("addDishes", new Date(), model.getFlag(),tokenid);
    		return model;
    	}
    }
    
    /**
     * 菜品修改
     * @param tokenid
     * @param dishes_id
     * @param dishes_name
     * @return
     */
    @PUT
    @Path("/dish/{rid}")
    @Produces("application/json")
    public DishesReturnModel updateDish(@HeaderParam("tokenid") String tokenid,@PathParam("rid") String rid, @HeaderParam("dishes_name") String dishes_name){
    	DishesReturnModel model=dishesService.updateDishes(tokenid, rid, dishes_name);
		orderService.addInvokeHistory("updateDishes", new Date(), model.getFlag(),tokenid);
		return model;
    }
    /**
     * 菜品删除
     * @param tokenid
     * @param dish_id
     * @return
     */
    @DELETE
    @Path("/dish/{rid}")
    @Produces("application/json")
    public DishesReturnModel deleteDish(@HeaderParam("tokenid") String tokenid,@PathParam("rid") String rid){
    	DishesReturnModel model= dishesService.deleteDishes(tokenid, rid);
		orderService.addInvokeHistory("deleteDishes", new Date(), model.getFlag(),tokenid);
		return model;
    }
    /**
     * 菜品查询
     * @param tokenid
     * @return
     */
    @GET
    @Path("/dish")
    @Produces("application/json")
    public DishesReturnModel findDish(@HeaderParam("tokenid") String tokenid){
    	DishesReturnModel model= dishesService.findDishes(tokenid);
    	if(model.getData()!=null)
    		orderService.addInvokeHistory("findDishes", new Date(),  "共获取"+model.getData().size()+"条数据",tokenid);
    	else
    		orderService.addInvokeHistory("findDishes", new Date(), model.getFlag(),tokenid);
    	return model;
    }
    
    /**
     * 读取点菜状态为已点，待读订单
     * @param tokenid
     * @return
     */
    @GET
    @Path("/unreadorderwithdish")
    @Produces("application/json")
    public OrderReturnModel findUnreadorderwithdish(@HeaderParam("tokenid") String tokenid){
    	OrderReturnModel model= orderService.findUnreadorderwithdish(tokenid);
    	if(model.getData()!=null)
    		orderService.addInvokeHistory("findUnreadorderwithdish", new Date(),  "共获取"+model.getData().size()+"条数据",tokenid);
    	else
    		orderService.addInvokeHistory("findUnreadorderwithdish", new Date(), model.getFlag(),tokenid);
    	return model;
    }
	/**
	 * 修改订单点菜状态
	 * @param tokenid
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
    @POST
    @Path("/handleorderdishstatus/{id}")
    @Produces("application/json")
    public UpdateOrderReturnModel updatehandleorderdishstatus(@HeaderParam("tokenid") String tokenid,@PathParam("id") String id, @HeaderParam("status") String status){
    	
		UpdateOrderReturnModel model= orderService.updatehandleorderdishstatus(tokenid,id, status);
		orderService.addInvokeHistory("updatehandleorderdishstatus", new Date(), model.getFlag(),tokenid);
		return model;
   
    }
    
    
	/**
	 * 上传订单
	 * @param tokenid
	 * @param orderrid
	 * @return
	 */
    @POST
    @Path("/uploadorder")
    @Produces("application/json")
    @Consumes("application/json")
    public UploadOrderReturnModel uploadorder(@HeaderParam("tokenid") String tokenid,OrderModel ordermodel){
    	UploadOrderReturnModel model= orderService.uploadorder(tokenid,ordermodel);
		orderService.addInvokeHistory("uploadorder", new Date(), model.getFlag(),tokenid);
		return model;
   
    }
    
    /**
     * 读取已取消，待读订单
     * @param tokenid
     * @return
     */
    @GET
    @Path("/cancelunreadorder")
    @Produces("application/json")
    public OrderReturnModel cancelunreadorder(@HeaderParam("tokenid") String tokenid){
    	OrderReturnModel model= orderService.cancelunreadorder(tokenid);
    	if(model.getData()!=null)
    		orderService.addInvokeHistory("cancelunreadorder", new Date(),  "共获取"+model.getData().size()+"条数据",tokenid);
    	else
    		orderService.addInvokeHistory("cancelunreadorder", new Date(), model.getFlag(),tokenid);
    	return model;
    }
    
    
	/**
	 * 取消订单
	 * @param tokenid
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
    @POST
    @Path("/cancelorder/{id}")
    @Produces("application/json")
    public UpdateOrderReturnModel cancelorder(@HeaderParam("tokenid") String tokenid,@PathParam("id") String id){
    	
		UpdateOrderReturnModel model= orderService.cancelorder(tokenid,id);
		orderService.addInvokeHistory("cancelorder", new Date(), model.getFlag(),tokenid);
		return model;
   
    }
}