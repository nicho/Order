package com.omdasoft.orderonline.gwt.order.client.frontOrderList.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
import com.omdasoft.orderonline.gwt.order.client.frontOrderList.dataprovider.FrontOrderListViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListClient;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.widget.Sorting;
import com.omdasoft.orderonline.gwt.order.util.DateTool;

public class FrontOrderListPresenterImpl extends
		BasePresenter<FrontOrderListPresenter.FrontOrderListDisplay> implements
		FrontOrderListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	// private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<OrderListClient> cellTable;
	FrontOrderListViewAdapter listViewAdapter;
	int pageSize=ViewConstants.per_page_number_in_entry;
	@Inject
	public FrontOrderListPresenterImpl(EventBus eventBus,
			FrontOrderListDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager, ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;

	}

	@Override
	public void bind() {

		init();
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						buildTable();
						doSearch();
					}
				}));
		registerHandler(display.getQueryphone().addKeyUpHandler(
				new KeyUpHandler() {
					@Override
					public void onKeyUp(KeyUpEvent e) {
						if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
							buildTable();
							doSearch();
						}
					}
				}));
		registerHandler(display.getPageNumber().addChangeHandler(new ChangeHandler() {			
			@Override
			public void onChange(ChangeEvent event) {
				pageSize=Integer.parseInt(display.getPageNumber().getValue(display.getPageNumber().getSelectedIndex()));
				buildTable();
				doSearch();
			}
		}));
	}

	private void init() {
		buildTable();
		doSearch();
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<OrderListClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(pageSize);
		// cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);

	}

	private void doSearch() {
		OrderListCriteria criteria = new OrderListCriteria();
		criteria.setPhone(display.getPhone().getValue());
		listViewAdapter = new FrontOrderListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<OrderListClient> ref = new Sorting<OrderListClient>() {
			@Override
			public void sortingCurrentPage(
					Comparator<OrderListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};

		cellTable.addColumn("订单编号", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return order.getCode();
					}
				});
		cellTable.addColumn("订餐人", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
							return order.getOrderPersonName();
					}
				});
		cellTable.addColumn("就餐时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						if (order.getReserveTimeDate() != null)
							return order.getReserveTimeDate() + " "
									+ order.getReserveTimeDateH() + "点"
									+ order.getReserveTimeDateS() + "分";
						else
							return "";
					}
				});
		cellTable.addColumn("就餐人数", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return order.getAmountOfClient() + "";
					}
				}, ref, "amountOfClient");
		cellTable.addColumn("包间要求", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						if (order.getFavoriteRoom() == 1)
							return "只订大厅";
						else if (order.getFavoriteRoom() == 2)
							return "只订包间";
						else if (order.getFavoriteRoom() == 3)
							return "先订大厅，如无大厅，订包间";
						else if (order.getFavoriteRoom() == 4)
							return "先订包间，如无包间，订大厅";
						else
							return "";
					}
				});
		cellTable.addColumn("下单时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return DateTool.dateToString(order.getPlaceOrderTime());
					}
				}, ref, "placeOrderTime");
		cellTable.addColumn("订单状态", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						if (order.getOrderStatus() != null)
							return order.getOrderStatus().getDisplayName();
						else
							return "";
					}
				});
		cellTable.addColumn("处理结果", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						if (order.getOrderStatus() != null)
							return order.getOrderStatus().getDisplayName();
						else
							return "";
					}
				});
		cellTable.addColumn("处理时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return DateTool.dateToString(order.getProcessingTime());
					}
				}, ref, "handleTime");
		cellTable.addColumn("修改时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return DateTool.dateToString(order.getLastUpdateTime());
					}
				}, ref, "modifyTime");
		cellTable.addColumn("完成时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return DateTool.dateToString(order.getCompleteTime());
					}
				}, ref, "finishTime");
	}

	@Override
	public void initFrontOrder(String phone) {
		display.getQueryphone().setText(phone);
		
	}

}
