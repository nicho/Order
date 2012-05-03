package com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.model.DictionaryListClient;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.model.DictionaryListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.SearchDictionaryListRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.SearchDictionaryListResponse;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesClient;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesList;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.DishesListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.DishesListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListResponse;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveResponse;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.dataprovider.OrdersDishesViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.view.DishesDetailedWidget;
import com.omdasoft.orderonline.gwt.order.client.ui.HyperLinkCell;
import com.omdasoft.orderonline.gwt.order.client.ui.MyAnchor;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.widget.Sorting;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;


public class OrdersDishesPresenterImpl extends
		BasePresenter<OrdersDishesPresenter.OrdersDishesDisplay> implements
		OrdersDishesPresenter {

	private final DispatchAsync dispatch;

	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<DishesListClient> cellTable;
	ListCellTable<BookingDishesClient> cellBookingTable;
	OrdersDishesViewAdapter listViewAdapter;
	OrderSaveRequest request;
	Map<Integer,String> map=new HashMap<Integer,String>();
	private final EltGinjector injector = GWT.create(EltGinjector.class);
	List<String> dwlt=new ArrayList<String>();
	List<String> kwlt=new ArrayList<String>();
	@Inject
	public OrdersDishesPresenterImpl(EventBus eventBus,
			OrdersDishesDisplay display, DispatchAsync dispatch,ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler=errorHandler;
	}

	@Override
	public void bind() {
		init();
	
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						setRequestDishesList();
						if(verificationRequest(request))
						{
						dispatch.execute(request,
								new AsyncCallback<OrderSaveResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(
											OrderSaveResponse response) {
										Window.alert("保存成功!");
										RootLayoutPanel.get().clear();
										RootLayoutPanel.get().add(injector.getOrderIndexPresenter().getDisplay().asWidget());
										injector.getFrontOrderListPresenter().bind();
										injector.getOrderIndexPresenter().initPresenter(injector.getFrontOrderListPresenter().getDisplay().asWidget());
										injector.getOrderIndexPresenter().bind();
										
									}

								});
						}
					}
				}));
		registerHandler(display.getReturnBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						setRequestDishesList();
						RootLayoutPanel.get().clear();
						RootLayoutPanel.get().add(injector.getOrderIndexPresenter().getDisplay().asWidget());
						
						injector.getOrderIndexPresenter().initPresenter(injector.getOrderSubmitPresenter().getDisplay().asWidget());
						injector.getOrderIndexPresenter().bind();
						
						injector.getOrderSubmitPresenter().initOrderRequest(request);
						injector.getOrderSubmitPresenter().bind();
					}
				}));

	}

	private void setRequestDishesList()
	{
		
		if(cellBookingTable.getVisibleItems()!=null && cellBookingTable.getVisibleItems().size()>0)
		{
			List<BookingDishesList> bookingDishesList=new ArrayList<BookingDishesList>();
			for (int i = 0; i < cellBookingTable.getVisibleItems().size(); i++) {
				BookingDishesClient cc=cellBookingTable.getVisibleItems().get(i);
				BookingDishesList list=new BookingDishesList();
				list.setId(cc.getId());
				list.setName(cc.getName());
				list.setNumber(cc.getNumber());
				list.setPrice(cc.getPrice());
				list.setTaste(cc.getTaste());
				list.setUnit(cc.getUnit());
				bookingDishesList.add(list);
			}
			
			request.setBookingDishesList(bookingDishesList);
		}

	
	}
	String allCss;
	List<MyAnchor> anchorList=new ArrayList<MyAnchor>();
	private void init() {
		 allCss=display.getTypeall().getElement().getParentElement().getClassName();
		initOrderMessage();
		initDwKw();
		createTab();
		buildTable();
		doSearch(null);
		display.getTypeall().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				cleanAnchorCss();
				display.getTypeall().getElement().getParentElement().setClassName(allCss);
				buildTable();
				doSearch(null);
			}
		});
		
	}
	private void initOrderMessage()
	{
		if(request!=null)
		{
			display.setCity(request.getCity());
			display.setNumber(request.getAmountOfClient()+"");
			display.setOrderUser(request.getOrderPersonName());
			display.setRestaurant(request.getRestaurantName());
			if(request.getFavoriteRoom()==1)
				display.setRoom("只订大厅");
			else if(request.getFavoriteRoom()==2)
				display.setRoom("只订包间");
			else if(request.getFavoriteRoom()==3)
				display.setRoom("先订大厅，如无大厅，订包间");
			else if(request.getFavoriteRoom()==4)
				display.setRoom("先订包间，如无包间，订大厅");
		}
	}
	public void initDwKw()
	{
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(0);
		pagination.setLimit(0);
		
		DictionaryListCriteria criteria = new DictionaryListCriteria();
		criteria.setPagination(pagination);
		criteria.setDictionaryType(0);
		criteria.setDeptId(request.getRestaurantId());
		dispatch.execute(new SearchDictionaryListRequest(criteria, null), new AsyncCallback<SearchDictionaryListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchDictionaryListResponse response) {
				List<DictionaryListClient> result=response.getResult();
				if(result!=null && result.size()>0)
				{
					kwlt.clear();
					dwlt.clear();
					for (DictionaryListClient cc:result) {
						if(cc.getDictionaryType()==1)
							kwlt.add(cc.getName());
						else if(cc.getDictionaryType()==2)
							dwlt.add(cc.getName());
					}

				}
				Label lb=new Label("还未点菜");
				lb.setStyleName("redlable");
				display.getBookingPanel().clear();
				display.getBookingPanel().add(lb);
				display.hiddenDishesNumber(true);
			}

		});
	}
	private void cleanAnchorCss()
	{
		if(anchorList!=null && anchorList.size()>0)
		{
			for (int i = 0; i < anchorList.size(); i++) {
				MyAnchor ma=anchorList.get(i);
				ma.getElement().getFirstChildElement().setClassName("");
			}
		}
	}
	private void createTab()
	{
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(0);
		pagination.setLimit(0);
		
		DishesTypeListCriteria criteria = new DishesTypeListCriteria();
		criteria.setPagination(pagination);
		criteria.setDeptId(request.getRestaurantId());
		dispatch.execute(new SearchDishesTypeListRequest(criteria,null), new AsyncCallback<SearchDishesTypeListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchDishesTypeListResponse response) {
				List<DishesTypeListClient> typeList=response.getResult();
				if(typeList!=null && typeList.size()>0)
				{
//					map.clear();
//					// Create a tab panel
//				    TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Unit.EM);
//				    tabPanel.setAnimationDuration(1000);
//				    tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);
				    display.getTabpage().clear();

				    for (int i=0;i<typeList.size();i++) {
				    	final DishesTypeListClient client=typeList.get(i);
				    	final MyAnchor ac=new MyAnchor(client.getName());
				    	  ac.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent arg0) {
								cleanAnchorCss();
								
								ac.getElement().getFirstChildElement().setClassName(allCss);
								display.getTypeall().getElement().getParentElement().setClassName("");
								buildTable();
								doSearch(client.getId());
								
							}
						});
				    	  anchorList.add(ac);
				    	  display.getTabpage().add(ac);
//					    // Add a tab
//					    HTML moreInfo = new HTML(client.getId());
//					    tabPanel.add(moreInfo, client.getName());
//					    map.put(i, client.getId());
					}


//				    // Return the content
//				    tabPanel.selectTab(0);
//				    tabPanel.ensureDebugId("cwTabPanel");
//				    tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
//						
//						@Override
//						public void onSelection(SelectionEvent<Integer> event) {
//						//	Window.alert("当前类型"+map.get(event.getSelectedItem()));
//							buildTable();
//							doSearch(map.get(event.getSelectedItem()));
//						}
//					});
				    
				  
				    
//				    display.getTabpage().clear();
//				    display.getTabpage().add(tabPanel);
				}
			}

		});
		
	}
	private void buildBookingTable() {
		// create a CellTable
		cellBookingTable = new ListCellTable<BookingDishesClient>();

		initBookingTableColumns();
		cellBookingTable.setWidth(ViewConstants.page_width);
		cellBookingTable.setPageSize(100);
	//	cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		display.getBookingPanel().clear();
		display.getBookingPanel().add(cellBookingTable);
		cellBookingTable.setRowCount(0);
		
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<DishesListClient>();

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

	private void doSearch(String typeId) {
		DishesListCriteria criteria = new DishesListCriteria();
		if(!StringUtil.isEmpty(typeId))
		criteria.setTypeId(typeId);
		criteria.setDeptId(request.getRestaurantId());
		listViewAdapter = new OrdersDishesViewAdapter(dispatch, criteria,
				errorHandler, null,display);
		listViewAdapter.addDataDisplay(cellTable);

	}
	private void sumNumberMoney()
	{
		if(cellBookingTable!=null && cellBookingTable.getRowCount()>0)
		{
			int number=0;
			double money=0;
			for (int i = 0; i < cellBookingTable.getRowCount(); i++) {
				try {
					number+=Integer.parseInt(cellBookingTable.getRowElement(i).getCells().getItem(1).getFirstChildElement().getInnerText());
					money+=Double.parseDouble(cellBookingTable.getRowElement(i).getCells().getItem(4).getFirstChildElement().getInnerText());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			display.setDishesNumber(number+"");
			display.setDishesMoney(money+"");
		}
	}
	private void initTableColumns() {
		Sorting<DishesListClient> ref = new Sorting<DishesListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<DishesListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};


		cellTable.addColumn("菜品", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getName();
					}
				}, ref, "name");

		cellTable.addColumn("图片",new HyperLinkCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return  "<img width='50px' height='50px' src='imageshow?imageName="+dishes.getPhoto()+"'>";
					}
				}, new FieldUpdater<DishesListClient, String>() {

					@Override
					public void update(int index, DishesListClient o, String value) {
						display.getDetailPanel().clear();
						display.getDetailPanel().add(new DishesDetailedWidget(o.getName(),o.getPrice(),o.getDescription(),o.getPhoto()));
					}

				});

		cellTable.addColumn("价格", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getPrice();
					}
				}, ref, "name");
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishesType) {
						return "查看详细";
					}
				}, new FieldUpdater<DishesListClient, String>() {

					@Override
					public void update(int index, DishesListClient o, String value) {
						display.getDetailPanel().clear();
						display.getDetailPanel().add(new DishesDetailedWidget(o.getName(),o.getPrice(),o.getDescription(),o.getPhoto()));
					}

				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishesType) {
						return "添加";
					}
				}, new FieldUpdater<DishesListClient, String>() {

					@Override
					public void update(int index, DishesListClient o, String value) {
					//	Window.alert(cellTable.getRowElement(index).getCells().getItem(4).getFirstChildElement().getFirstChildElement().getFirstChildElement().getAttribute("id"));
						if(cellBookingTable==null)
						{
							buildBookingTable();
							display.hiddenDishesNumber(false);
						}
						
						if(cellBookingTable.getRowCount()>=1)
						{
						boolean fal=false;
						for (int i = 0; i < cellBookingTable.getRowCount(); i++) {
							if(cellBookingTable.getVisibleItem(i).getId().equals(o.getId()))
							{
								int number=(cellBookingTable.getVisibleItem(i).getNumber()+1);
								cellBookingTable.getRowElement(i).getCells().getItem(1).getFirstChildElement().setInnerText(number+"");
								cellBookingTable.getVisibleItem(i).setNumber(number);
								System.out.println(cellBookingTable.getVisibleItem(i).getPrice());
								cellBookingTable.getRowElement(i).getCells().getItem(4).getFirstChildElement().setInnerText((number*Double.parseDouble(cellBookingTable.getVisibleItem(i).getPrice()))+"");
								fal=true;
							}
							
						}
						if(fal==false)
						{

								BookingDishesClient col=new BookingDishesClient();
								col.setId(o.getId());
								col.setName(o.getName());
								col.setNumber(1);
								col.setPrice(o.getPrice());
								if(kwlt.size()>0)
								col.setTaste(kwlt.get(0));
								if(dwlt.size()>0)
								col.setUnit(dwlt.get(0));
								List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
										lt.add(col);
								cellBookingTable.setRowData(cellBookingTable.getRowCount(), lt);
							
						}
					}else
					{
						BookingDishesClient col=new BookingDishesClient();
						col.setId(o.getId());
						col.setName(o.getName());
						col.setNumber(1);
						col.setPrice(o.getPrice());
						if(kwlt.size()>0)
						col.setTaste(kwlt.get(0));
						if(dwlt.size()>0)
						col.setUnit(dwlt.get(0));
						List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
								lt.add(col);
						cellBookingTable.setRowData(cellBookingTable.getRowCount(), lt);
						}

						
						sumNumberMoney();
					}

				});
	
	}
	
	private void initBookingTableColumns() {
		


		cellBookingTable.addColumn("菜品", new TextCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getName();
					}
				});

		Column<BookingDishesClient, String> numberColumn =cellBookingTable.addColumn("数量", new EditTextCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getNumber()+"";
					}
				},new FieldUpdater<BookingDishesClient, String>() {

					@Override
					public void update(int index, BookingDishesClient o, String value) {
						try
						{
						     cellBookingTable.getRowElement(index).getCells().getItem(4).getFirstChildElement().setInnerHTML((Double.parseDouble(value))* Double.parseDouble(o.getPrice())+"");
						     cellBookingTable.getVisibleItem(index).setNumber(Integer.parseInt(value));
						     sumNumberMoney();
						}catch (Exception e) {
							 cellBookingTable.getRowElement(index).getCells().getItem(4).getFirstChildElement().setInnerHTML((1* Double.parseDouble(o.getPrice())+""));
						}
					}

				});
		cellBookingTable.setColumnWidth(numberColumn, 167,Unit.PX);


		cellBookingTable.addColumn("单位", new SelectionCell(dwlt),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getUnit();
					}
				}, new FieldUpdater<BookingDishesClient, String>() {

					@Override
					public void update(int index, BookingDishesClient o, String value) {
						o.setUnit(value);
					}

				});
		

		cellBookingTable.addColumn("口味", new SelectionCell(kwlt),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getTaste();
					}
				}, new FieldUpdater<BookingDishesClient, String>() {

					@Override
					public void update(int index, BookingDishesClient o, String value) {
						o.setTaste(value);
					}

				});
		cellBookingTable.addColumn("价格", new TextCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getPrice();
					}
				});
		cellBookingTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishesType) {
						return "从菜单删除";
					}
				}, new FieldUpdater<BookingDishesClient, String>() {

					@Override
					public void update(int index, BookingDishesClient o, String value) {
//						cellBookingTable.setRowCount(index, false);
						cellBookingTable.getRowElement(index).removeFromParent();
						sumNumberMoney();
					}

				});
		
	
	}

	@Override
	public void initOrdersDishes(OrderSaveRequest request) {
		this.request=request;

	}
	private boolean verificationRequest(OrderSaveRequest req)
	{
		if (StringUtil.isEmpty(req.getAmountOfClient()+"") || req.getAmountOfClient()==0) {
			Window.alert("请填写就餐人数");
			return false;
		}
		if (StringUtil.isEmpty(req.getFavoriteRoom()+"") || req.getFavoriteRoom()==0) {
			Window.alert("请选择预定类型");
			return false;
		}
		if (StringUtil.isEmpty(req.getOrderPersonName())) {
			Window.alert("请填写姓名");
			return false;
		}
		if (StringUtil.isEmpty(req.getOrderPersonPhone())) {
			Window.alert("请填写手机号");
			return false;
		}

		return true;
	}
}
