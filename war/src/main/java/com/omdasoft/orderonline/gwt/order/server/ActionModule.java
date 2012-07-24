package com.omdasoft.orderonline.gwt.order.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyInitRequest;
import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyListRequest;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.request.CompanyAddRequest;
import com.omdasoft.orderonline.gwt.order.client.core.request.ImageUrlInitRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.DictionaryDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.SearchDictionaryListRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.DictionarySaveRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.request.FindDictionaryRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesCopyRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.SearchDishesListRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.DishesSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.FindDishesRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.DishesTypeCopyRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.DishesTypeDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypePanelRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.DishesTypeSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.request.FindDishesTypeRequest;
import com.omdasoft.orderonline.gwt.order.client.enterprise.request.EnterpriseInitRequest;
import com.omdasoft.orderonline.gwt.order.client.enterprise.request.EnterpriseRequest;
import com.omdasoft.orderonline.gwt.order.client.login.LastLoginRoleRequest;
import com.omdasoft.orderonline.gwt.order.client.login.LoginRequest;
import com.omdasoft.orderonline.gwt.order.client.login.TokenValidRequest;
import com.omdasoft.orderonline.gwt.order.client.orderList.request.OrderDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.orderList.request.SearchOrderListRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.CityInitRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.FindOrderRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderInitRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.orderView.request.SearchOrderDishesListRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersLogin.request.OrderLoginRequest;
import com.omdasoft.orderonline.gwt.order.client.ordersWait.request.OrderWaitRequest;
import com.omdasoft.orderonline.gwt.order.client.password.request.PasswordRequest;
import com.omdasoft.orderonline.gwt.order.client.register.request.RegisterInitRequest;
import com.omdasoft.orderonline.gwt.order.client.register.request.RegisterRequest;
import com.omdasoft.orderonline.gwt.order.client.registerHr.request.RegisterHrRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.request.RestaurantDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.request.SearchDepartmentByCorpIdRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.request.SearchRestaurantListRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.FindRestaurantRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.RestaurantSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.request.SearchLeaderRequest;
import com.omdasoft.orderonline.gwt.order.client.server.dictionary.DictionarySaveActionHandler;
import com.omdasoft.orderonline.gwt.order.client.server.dictionary.FindDictionaryActionHandler;
import com.omdasoft.orderonline.gwt.order.client.server.order.CityInitActionHandler;
import com.omdasoft.orderonline.gwt.order.client.server.order.FindOrderActionHandler;
import com.omdasoft.orderonline.gwt.order.client.server.order.OrderDeleteActionHandler;
import com.omdasoft.orderonline.gwt.order.client.server.order.OrderInitActionHandler;
import com.omdasoft.orderonline.gwt.order.client.server.order.OrderSaveActionHandler;
import com.omdasoft.orderonline.gwt.order.client.server.order.SearchOrderDishesListActionHandler;
import com.omdasoft.orderonline.gwt.order.client.server.order.SearchOrderListActionHandler;
import com.omdasoft.orderonline.gwt.order.client.userAdd.request.UserAddRequest;
import com.omdasoft.orderonline.gwt.order.client.userList.request.SearchUserListRequest;
import com.omdasoft.orderonline.gwt.order.client.userList.request.UpdateUserPwdRequest;
import com.omdasoft.orderonline.gwt.order.client.userList.request.UserDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.userView.request.UserViewRequest;
import com.omdasoft.orderonline.gwt.order.server.enterprise.CompanyAddActionHandler;
import com.omdasoft.orderonline.gwt.order.server.enterprise.CompanyInitActionHandler;
import com.omdasoft.orderonline.gwt.order.server.enterprise.EnterpriseActionHandler;
import com.omdasoft.orderonline.gwt.order.server.enterprise.EnterpriseInitActionHandler;
import com.omdasoft.orderonline.gwt.order.server.enterprise.SearchCompanyListActionHandler;
import com.omdasoft.orderonline.gwt.order.server.login.ImageUrlActionHandler;
import com.omdasoft.orderonline.gwt.order.server.login.LoginActionHandler;
import com.omdasoft.orderonline.gwt.order.server.login.TokenValidActionHandler;
import com.omdasoft.orderonline.gwt.order.server.login.UpdatelastLoginRoleActionHandler;
import com.omdasoft.orderonline.gwt.order.server.ordersLogin.OrderLoginActionHandler;
import com.omdasoft.orderonline.gwt.order.server.ordersLogin.OrderWaitActionHandler;
import com.omdasoft.orderonline.gwt.order.server.register.RegisterActionHandler;
import com.omdasoft.orderonline.gwt.order.server.register.RegisterHrActionHandler;
import com.omdasoft.orderonline.gwt.order.server.register.RegisterInitActionHandler;
import com.omdasoft.orderonline.gwt.order.server.restaurant.FindRestaurantActionHandler;
import com.omdasoft.orderonline.gwt.order.server.restaurant.RestaurantDeleteActionHandler;
import com.omdasoft.orderonline.gwt.order.server.restaurant.RestaurantSaveActionHandler;
import com.omdasoft.orderonline.gwt.order.server.restaurant.SearchDepartmentByCorpIdHandler;
import com.omdasoft.orderonline.gwt.order.server.restaurant.SearchLeaderHandler;
import com.omdasoft.orderonline.gwt.order.server.user.DictionaryDeleteActionHandler;
import com.omdasoft.orderonline.gwt.order.server.user.SearchDictionaryListActionHandler;
import com.omdasoft.orderonline.gwt.order.server.user.SearchRestaurantListActionHandler;
import com.omdasoft.orderonline.gwt.order.server.user.SearchUserListActionHandler;
import com.omdasoft.orderonline.gwt.order.server.user.SearchUserViewActionHandler;
import com.omdasoft.orderonline.gwt.order.server.user.UpdateStaffPwdActionHandler;
import com.omdasoft.orderonline.gwt.order.server.user.UpdateUserPwdActionHandler;
import com.omdasoft.orderonline.gwt.order.server.user.UserAddActionHandler;
import com.omdasoft.orderonline.gwt.order.server.user.UserDeleteActionHandler;

/**
 * 
 * @author cyril
 * 
 */
public class ActionModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		// login module
		bindHandler(LoginRequest.class, LoginActionHandler.class);
		bindHandler(LastLoginRoleRequest.class, UpdatelastLoginRoleActionHandler.class);

		// 登录验证token
		bindHandler(TokenValidRequest.class, TokenValidActionHandler.class);
		bindHandler(SearchUserListRequest.class, SearchUserListActionHandler.class);
		bindHandler(SearchOrderListRequest.class, SearchOrderListActionHandler.class);
		bindHandler(SearchDictionaryListRequest.class, SearchDictionaryListActionHandler.class);
		bindHandler(SearchRestaurantListRequest.class, SearchRestaurantListActionHandler.class);
		bindHandler(SearchDishesListRequest.class, SearchDishesListActionHandler.class);
		bindHandler(SearchDishesTypeListRequest.class, SearchDishesTypeListActionHandler.class);
		bindHandler(OrderSaveRequest.class, OrderSaveActionHandler.class);
		bindHandler(FindOrderRequest.class, FindOrderActionHandler.class);
		
		bindHandler(DictionarySaveRequest.class, DictionarySaveActionHandler.class);
		bindHandler(FindDictionaryRequest.class, FindDictionaryActionHandler.class);
		
		bindHandler(RestaurantSaveRequest.class, RestaurantSaveActionHandler.class);
		bindHandler(FindRestaurantRequest.class, FindRestaurantActionHandler.class);
		
		bindHandler(DishesSaveRequest.class, DishesSaveActionHandler.class);
		bindHandler(DishesTypeSaveRequest.class, DishesTypeSaveActionHandler.class);
		bindHandler(FindDishesRequest.class, FindDishesActionHandler.class);
		bindHandler(FindDishesTypeRequest.class, FindDishesTypeActionHandler.class);
		
		
		bindHandler(EnterpriseRequest.class, EnterpriseActionHandler.class);
		bindHandler(EnterpriseInitRequest.class, EnterpriseInitActionHandler.class);
		bindHandler(RegisterRequest.class, RegisterActionHandler.class);
		bindHandler(RegisterHrRequest.class, RegisterHrActionHandler.class);
		bindHandler(RegisterInitRequest.class, RegisterInitActionHandler.class);
		bindHandler(OrderInitRequest.class, OrderInitActionHandler.class);
		bindHandler(CityInitRequest.class, CityInitActionHandler.class);
		bindHandler(SearchOrderDishesListRequest.class, SearchOrderDishesListActionHandler.class);
		bindHandler(SearchDepartmentByCorpIdRequest.class,SearchDepartmentByCorpIdHandler.class);		
		//员工添加
		bindHandler(UserAddRequest.class,UserAddActionHandler.class);
		//员工详细信息
		bindHandler(UserViewRequest.class,SearchUserViewActionHandler.class);
		
		//选择Leader
		bindHandler(SearchLeaderRequest.class,SearchLeaderHandler.class);
		
		//各种删除
		
		bindHandler(RestaurantDeleteRequest.class,RestaurantDeleteActionHandler.class);
		bindHandler(DictionaryDeleteRequest.class,DictionaryDeleteActionHandler.class);
		bindHandler(UserDeleteRequest.class,UserDeleteActionHandler.class);
		bindHandler(DishesDeleteRequest.class,DishesDeleteActionHandler.class);
		bindHandler(DishesTypeDeleteRequest.class,DishesTypeDeleteActionHandler.class);
		bindHandler(OrderDeleteRequest.class,OrderDeleteActionHandler.class);
		
		
		bindHandler(PasswordRequest.class,UpdateStaffPwdActionHandler.class);
		bindHandler(UpdateUserPwdRequest.class,UpdateUserPwdActionHandler.class);
		bindHandler(OrderLoginRequest.class,OrderLoginActionHandler.class);
		bindHandler(OrderWaitRequest.class,OrderWaitActionHandler.class);
		bindHandler(DishesTypePanelRequest.class,DishesTypePanelActionHandler.class);
		bindHandler(ImageUrlInitRequest.class,ImageUrlActionHandler.class);
		
		
		//公司管理
		bindHandler(CompanyListRequest.class,SearchCompanyListActionHandler.class);
		//公司添加
		bindHandler(CompanyAddRequest.class,CompanyAddActionHandler.class);
		//公司修改
		bindHandler(CompanyInitRequest.class, CompanyInitActionHandler.class);
		
		bindHandler(DishesCopyRequest.class, DishesCopyActionHandler.class);
		bindHandler(DishesTypeCopyRequest.class, DishesTypeCopyActionHandler.class);
		
	}
}
