package com.omdasoft.orderonline.gwt.order.client.orderList.presenter;

import java.util.Comparator;
import java.util.Date;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderList.dataprovider.OrderListViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListClient;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria;
import com.omdasoft.orderonline.gwt.order.client.orderList.model.OrderListCriteria.OrderStatus;
import com.omdasoft.orderonline.gwt.order.client.orderSave.plugin.OrderSaveConstants;
import com.omdasoft.orderonline.gwt.order.client.orderView.plugin.OrderViewConstants;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.ui.HyperLinkCell;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.widget.Sorting;
import com.omdasoft.orderonline.gwt.order.util.DateTool;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;


public class OrderListPresenterImpl extends
		BasePresenter<OrderListPresenter.OrderListDisplay> implements
		OrderListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	//private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<OrderListClient> cellTable;
	OrderListViewAdapter listViewAdapter;
	int pageSize=ViewConstants.per_page_number_in_entry;
	private final BreadCrumbsPresenter breadCrumbs;
	String dateType="";
	@Inject
	public OrderListPresenterImpl(EventBus eventBus,
			OrderListDisplay display, DispatchAsync dispatch, //Win win,
			SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs,ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler=errorHandler;
		//this.win=win;
		this.breadCrumbs=breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						buildTable();
						doSearch();
					}
				}));
		registerHandler(display.getAddOrderBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								OrderSaveConstants.EDITOR_ORDERSAVE_SEARCH,
								"EDITOR_ORDERSAVE_SEARCH_DO_ID", null);
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
		
		registerHandler(display.getNoDate().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dateType="placeOrderTime";
						display.getNoDate().getElement().getParentElement().getParentElement().setClassName("all cur");
						display.getDate1().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate2().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate3().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate4().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate5().getElement().getParentElement().getParentElement().setClassName("");
						
						
						display.getDateStart().setValue(null);
						display.getDateEnd().setValue(null);
						
						display.getDay1().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay2().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay3().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay4().getElement().getParentElement().getParentElement().setClassName("");
					}
				}));
		registerHandler(display.getDate1().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dateType="placeOrderTime";
						display.getDate1().getElement().getParentElement().getParentElement().setClassName("all cur");
						display.getDate2().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate3().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate4().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate5().getElement().getParentElement().getParentElement().setClassName("");
						display.getNoDate().getElement().getParentElement().getParentElement().setClassName("");
					}
				}));
		registerHandler(display.getDate2().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dateType="handleTime";
						display.getDate1().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate2().getElement().getParentElement().getParentElement().setClassName("all cur");
						display.getDate3().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate4().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate5().getElement().getParentElement().getParentElement().setClassName("");
						display.getNoDate().getElement().getParentElement().getParentElement().setClassName("");
						
					}
				}));
		registerHandler(display.getDate3().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dateType="modifyTime";
						display.getDate1().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate2().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate3().getElement().getParentElement().getParentElement().setClassName("all cur");
						display.getDate4().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate5().getElement().getParentElement().getParentElement().setClassName("");
						display.getNoDate().getElement().getParentElement().getParentElement().setClassName("");
					}
				}));
		registerHandler(display.getDate4().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dateType="finishTime";
						display.getDate1().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate2().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate3().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate4().getElement().getParentElement().getParentElement().setClassName("all cur");
						display.getDate5().getElement().getParentElement().getParentElement().setClassName("");
						display.getNoDate().getElement().getParentElement().getParentElement().setClassName("");
					}
				}));
		registerHandler(display.getDate5().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dateType="reserveTimeDate";
						display.getDate1().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate2().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate3().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate4().getElement().getParentElement().getParentElement().setClassName("");
						display.getDate5().getElement().getParentElement().getParentElement().setClassName("all cur");
						display.getNoDate().getElement().getParentElement().getParentElement().setClassName("");
					}
				}));
		
		registerHandler(display.getDay1().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.getDateStart().setValue(new Date());
						display.getDateEnd().setValue(new Date());
						
						display.getDay1().getElement().getParentElement().getParentElement().setClassName("all cur");
						display.getDay2().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay3().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay4().getElement().getParentElement().getParentElement().setClassName("");
					}
				}));
		registerHandler(display.getDay2().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {

						display.getDateStart().setValue(DateTool.delSomeDay(new Date(), 1));
						display.getDateEnd().setValue(DateTool.delSomeDay(new Date(), 1));
						
						display.getDay1().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay2().getElement().getParentElement().getParentElement().setClassName("all cur");
						display.getDay3().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay4().getElement().getParentElement().getParentElement().setClassName("");
					}
				}));
		registerHandler(display.getDay3().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Date de=new Date();
						@SuppressWarnings("deprecation")
						int x=de.getDay();
						
						display.getDateStart().setValue(DateTool.delSomeDay(new Date(),x-1));
						display.getDateEnd().setValue(DateTool.addSomeDay(new Date(), 7-x));
						
						display.getDay1().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay2().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay3().getElement().getParentElement().getParentElement().setClassName("all cur");
						display.getDay4().getElement().getParentElement().getParentElement().setClassName("");
					}
				}));
		registerHandler(display.getDay4().addClickHandler(
				new ClickHandler() {
					@SuppressWarnings("deprecation")
					@Override
					public void onClick(ClickEvent event) {
						Date de=new Date();
						de.setDate(1);
						
						display.getDateStart().setValue(de);
						display.getDateEnd().setValue(DateTool.delSomeDay(DateTool.addSomeMonth(de, 1), 1));
						
						display.getDay1().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay2().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay3().getElement().getParentElement().getParentElement().setClassName("");
						display.getDay4().getElement().getParentElement().getParentElement().setClassName("all cur");
					}
				}));
	}
	
	private void init() {	
		display.initStaffStatus();
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
		cellTable.getColumn(0).setCellStyleNames("widthcell");
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
		
	}

	private void doSearch() {
		OrderListCriteria criteria = new OrderListCriteria();
		criteria.setSession(sessionManager.getSession());
		if(!StringUtil.isEmpty(display.getStaffNameorNo().getValue()))
			criteria.setPhoneorName(display.getStaffNameorNo().getValue());
		if(!"ALL".equals(display.getSttaffStatus()))
			criteria.setStatus(OrderStatus.valueOf(display.getSttaffStatus().toString()));
		if(!StringUtil.isEmpty(dateType))
		{
			criteria.setDateType(dateType);
			criteria.setDateStart(display.getDateStart().getValue());
			criteria.setDateEnd(display.getDateEnd().getValue());
		}
		listViewAdapter = new OrderListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<OrderListClient> ref = new Sorting<OrderListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<OrderListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};

		cellTable.addColumn("序号", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return order.getIndexNo();
					}
				});
		cellTable.addColumn("订单编号", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return order.getCode();
					}
				}, ref, "code");
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
						if(order.getReserveTimeDate()!=null)
							return order.getReserveTimeDate()+" "+order.getReserveTimeDateH()+"点"+order.getReserveTimeDateS()+"分";
						else
							return "";
					}
				}, ref, "reserveTimeDate");
		cellTable.addColumn("就餐人数", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return order.getAmountOfClient()+"";
					}
				}, ref, "amountOfClient");
		cellTable.addColumn("包间要求", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						if(order.getFavoriteRoom()==1)
							return "只订大厅";
						else if(order.getFavoriteRoom()==2)
							return "只订包间";
						else if(order.getFavoriteRoom()==3)
							return "先订大厅，如无大厅，订包间";
						else if(order.getFavoriteRoom()==4)
							return "先订包间，如无包间，订大厅";
						else 
							return "";
					}
				});
		cellTable.addColumn("下单时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						return DateTool.dateToStringEn(order.getPlaceOrderTime());
					}
				}, ref, "placeOrderTime");
		cellTable.addColumn("订单状态", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						if(order.getOrderStatus()!=null)
						return order.getOrderStatus().getDisplayName();
						else
						return "";
					}
				});
		cellTable.addColumn("订房状态", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
						if(!StringUtil.isEmpty(order.getRoomState()))
						return order.getRoomState();
						else
						return "";
					}
				});
		cellTable.addColumn("处理结果", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient order) {
							if(!StringUtil.isEmpty(order.getResult()) && order.getResult().length()>50)
								return order.getResult().substring(0,50)+"...";
							else
								return order.getResult();
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
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient restaurant) {
						return "查看";
					}
				}, new FieldUpdater<OrderListClient, String>() {

					@Override
					public void update(int index, OrderListClient o, String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								OrderViewConstants.EDITOR_ORDERVIEW_SEARCH,
								"EDITOR_ORDERVIEW_SEARCH_DO_ID", o.getId());
					}

				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient restaurant) {
						return "修改";
					}
				}, new FieldUpdater<OrderListClient, String>() {

					@Override
					public void update(int index, OrderListClient o, String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								OrderSaveConstants.EDITOR_ORDERSAVE_SEARCH,
								"EDITOR_ORDERSAVE_SEARCH_DO_ID", o.getId());
					}

				});
//		cellTable.addColumn("操作", new HyperLinkCell(),
//				new GetValue<OrderListClient, String>() {
//					@Override
//					public String getValue(OrderListClient restaurant) {
//						return "删除";
//					}
//				}, new FieldUpdater<OrderListClient, String>() {
//
//					@Override
//					public void update(int index, final OrderListClient o, String value) {
//						win.confirm("提示", "确定删除?",new ConfirmHandler() {
//							
//							@Override
//							public void confirm() {
//								dispatch.execute(new OrderDeleteRequest(o.getId(),sessionManager
//										.getSession()), new AsyncCallback<OrderDeleteResponse>() {
//									@Override
//									public void onFailure(Throwable e) {
//										errorHandler.alert(e.getMessage());
//									}
//
//									@Override
//									public void onSuccess(OrderDeleteResponse response) {
//										win.alert("删除成功!");
//										buildTable();
//										doSearch();
//									}
//
//								});
//								
//							}
//						});
//					}
//
//				});

	}
	

}
