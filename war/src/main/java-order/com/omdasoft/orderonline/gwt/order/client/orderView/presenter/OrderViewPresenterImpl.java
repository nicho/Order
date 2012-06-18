package com.omdasoft.orderonline.gwt.order.client.orderView.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.FindOrderRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.FindOrderResponse;
import com.omdasoft.orderonline.gwt.order.client.orderView.dateprovider.OrderDishesListAdapter;
import com.omdasoft.orderonline.gwt.order.client.orderView.model.OrderDishesCriteria;
import com.omdasoft.orderonline.gwt.order.client.orderView.model.OrderDishesListClient;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.ui.HyperLinkCell;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.util.DateTool;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class OrderViewPresenterImpl extends
		BasePresenter<OrderViewPresenter.OrderViewDisplay> implements
		OrderViewPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
//	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<OrderDishesListClient> cellTable;
	OrderDishesListAdapter listViewAdapter;
	private final BreadCrumbsPresenter breadCrumbs;
	String orderId = null;

	@Inject
	public OrderViewPresenterImpl(EventBus eventBus, OrderViewDisplay display,
			DispatchAsync dispatch, SessionManager sessionManager,
			BreadCrumbsPresenter breadCrumbs, ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
	//	this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("查看订单");
			FindOrderRequest findrequest = new FindOrderRequest();
			findrequest.setOrderId(orderId);
			dispatch.execute(findrequest,
					new AsyncCallback<FindOrderResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(FindOrderResponse response) {
							initOrderRequest(response);
						}

					});
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		initOrderDishesList();
	}
	void initOrderDishesList()
	{
		buildTable();
		doSearch();
	}
	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<OrderDishesListClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number_in_dialog);
	//	cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
		
	}

	private void doSearch() {
		OrderDishesCriteria criteria = new OrderDishesCriteria();
		criteria.setOrderId(orderId);
		listViewAdapter = new OrderDishesListAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
//		Sorting<OrderDishesListClient> ref = new Sorting<OrderDishesListClient>() {
//			@Override
//			public void sortingCurrentPage(Comparator<OrderDishesListClient> comparator) {
//				// listViewAdapter.sortCurrentPage(comparator);
//			}
//
//			@Override
//			public void sortingAll(String sorting, String direction) {
//				listViewAdapter.sortFromDateBase(sorting, direction);
//
//			}
//		};
		cellTable.addColumn("类别", new TextCell(),
				new GetValue<OrderDishesListClient, String>() {
					@Override
					public String getValue(OrderDishesListClient dishes) {
						return dishes.getDishesType();
					}
				});
		cellTable.addColumn("菜品", new TextCell(),
				new GetValue<OrderDishesListClient, String>() {
					@Override
					public String getValue(OrderDishesListClient dishes) {
						return dishes.getDishesName();
					}
				});

		cellTable.addColumn("图片",new HyperLinkCell(),
				new GetValue<OrderDishesListClient, String>() {
					@Override
					public String getValue(OrderDishesListClient dishes) {
						return  "<img width='50px' height='50px' src='"+StringUtil.getThumbImageUrl(dishes.getPhoto())+"'>";
					}
				});

		cellTable.addColumn("数量", new TextCell(),
				new GetValue<OrderDishesListClient, String>() {
					@Override
					public String getValue(OrderDishesListClient dishes) {
						return dishes.getNumber();
					}
				});
		cellTable.addColumn("单位", new TextCell(),
				new GetValue<OrderDishesListClient, String>() {
					@Override
					public String getValue(OrderDishesListClient dishes) {
						return dishes.getUnit();
					}
				});
		cellTable.addColumn("口味", new TextCell(),
				new GetValue<OrderDishesListClient, String>() {
					@Override
					public String getValue(OrderDishesListClient dishes) {
						return dishes.getTaste();
					}
				});
		cellTable.addColumn("单价", new TextCell(),
				new GetValue<OrderDishesListClient, String>() {
					@Override
					public String getValue(OrderDishesListClient dishes) {
						return dishes.getPrice();
					}
				});
		cellTable.addColumn("总价", new TextCell(),
				new GetValue<OrderDishesListClient, String>() {
					@Override
					public String getValue(OrderDishesListClient dishes) {
						return dishes.getTotalprice();
					}
				});
	

	}
	public void initOrderRequest(FindOrderResponse request) {

		if(!StringUtil.isEmpty(request.getAmountOfClient()+"") && request.getAmountOfClient()!=0)
		{
			display.getAmountOfClient().setText(request.getAmountOfClient()+"");
		}
		if(!StringUtil.isEmpty(request.getCity()))
		{
			display.getCity().setText(request.getCity());
		}
		if(!StringUtil.isEmpty(request.getRestaurantName()))
		{
			display.getrestaurant().setText(request.getRestaurantName());
		}
		if(!StringUtil.isEmpty(request.getContactPersonName()))
		{
			display.getContactPersonName().setText(request.getContactPersonName());
			display.getOtherPersonCheckbox().setValue(true);
		}
		if(!StringUtil.isEmpty(request.getContactPersonPhone()))
			display.getContactPersonPhone().setText(request.getContactPersonPhone());
		
		if(!StringUtil.isEmpty(request.getFavoriteRoom()+"") && request.getFavoriteRoom()!=0)
		{
			String room="";
			if (request.getFavoriteRoom()== 1)
				room="只订大厅";
			else if (request.getFavoriteRoom()== 2)
				room="只订包间";
			else if (request.getFavoriteRoom()== 3)
				room=">先订大厅，如无大厅，订包间";
			else if (request.getFavoriteRoom()== 4)
				room="先订包间，如无包间，订大厅";
			
			display.getFavoriteRoom().setText(room);
		}
		if(!StringUtil.isEmpty(request.getMemo()))
			display.getMemo().setText(request.getMemo());
		if(!StringUtil.isEmpty(request.getOrderPersonName()))
			display.getOrderPersonName().setText(request.getOrderPersonName());
		if(!StringUtil.isEmpty(request.getOrderPersonPhone()))
			display.getOrderPersonPhone().setText(request.getOrderPersonPhone());
		if(!StringUtil.isEmpty(request.getOrderPersonSex()))
		{
			display.getOrderPersonSex().setText(request.getOrderPersonSex());
		}
		if(!StringUtil.isEmpty(request.getContactPersonSex()))
		{
			display.getContactPersonSex().setText(request.getContactPersonSex());
		}
		
		if(!StringUtil.isEmpty(request.getReserveTimeDate()) && !StringUtil.isEmpty(request.getReserveTimeDateH()) && !StringUtil.isEmpty(request.getReserveTimeDateS()))
		{
			display.getReserveTime().setText(DateTool.getDateStr(request.getReserveTimeDate(), request.getReserveTimeDateH(), request.getReserveTimeDateS()));
		}
		if(request.getPlaceOrderTime()!=null)
		{
			display.getPlaceOrderTime().setText(DateTool.dateToStringEn(request.getPlaceOrderTime()));
		}
		
		
		if(!StringUtil.isEmpty(request.getResult()))
		{
			display.getResult().setText(request.getResult());
		}
	}
	

	@Override
	public void initOrder(String orderId) {
		this.orderId = orderId;
	}

}
