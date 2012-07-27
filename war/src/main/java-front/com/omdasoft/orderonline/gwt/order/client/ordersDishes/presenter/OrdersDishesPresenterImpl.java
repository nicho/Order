package com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.Elt;
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
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.dataprovider.OrdersDishesViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.dialog.DishesDetailedDialog;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.view.DishesTypeLatticeWidget;
import com.omdasoft.orderonline.gwt.order.client.ui.ImageLinkCell;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.widget.Span;
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

	Map<Integer,String> map=new HashMap<Integer,String>();
	private final EltGinjector injector;
	List<String> dwlt=new ArrayList<String>();
	List<String> kwlt=new ArrayList<String>();
	int pageSize=ViewConstants.per_page_number_in_12;
	OrdersDishesPresenter ordersDishesPresenter;
	String deptId=null;
	private final Provider<DishesDetailedDialog> dishesDetailedDialogProvider;

	@Inject
	public OrdersDishesPresenterImpl(EventBus eventBus,
			OrdersDishesDisplay display, DispatchAsync dispatch,ErrorHandler errorHandler,
			Provider<DishesDetailedDialog> dishesDetailedDialogProvider,EltGinjector injector) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler=errorHandler;
		this.dishesDetailedDialogProvider=dishesDetailedDialogProvider;
		this.ordersDishesPresenter=this;
		this.injector=injector;
	}

	@Override
	public void bind() {
		init();
		registerHandler(display.getOrdernow().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				injector.getOrderIndexPresenter().initPresenter(injector.getOrderLoginPresenter());

			}
		}));
		
		registerHandler(display.getPageNumber().addChangeHandler(new ChangeHandler() {			
			@Override
			public void onChange(ChangeEvent event) {
				pageSize=Integer.parseInt(display.getPageNumber().getValue(display.getPageNumber().getSelectedIndex()));
				cleanAnchorCss();
			//	display.getTypeall().getElement().getParentElement().setClassName(allCss);
				buildTable();
				doSearch(null);
			}
		}));
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if(cellBookingTable==null || cellBookingTable.getVisibleItems().size()<=0)
						{
							Window.alert("请先点菜！");
						}
						else
						{
							setRequestDishesList();
							injector.getOrdersConfirmPresenter().initDwKw(dwlt, kwlt);
							injector.getOrderIndexPresenter().initPresenter(injector.getOrdersConfirmPresenter());
						}

					}
				}));
		registerHandler(display.getReturnBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
					
	
						injector.getOrderManager().getOrderRequest().setBookingDishesList(null);
						injector.getOrderManager().getOrderRequest().setBookingDishesListClient(null);
						List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
						cellBookingTable.setRowData(lt);
						sumNumberMoney();

					}
				}));
		registerHandler(display.getMenutypeAll().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.getQueryKey().setValue("");
						buildTable();
						doSearch(null);
					}
				}));
		registerHandler(display.getQuerybtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						buildTable();
						doSearch(null);
					}
				}));
		registerHandler(display.getQueryKey().addKeyUpHandler(
				new KeyUpHandler() {
					@Override
					public void onKeyUp(KeyUpEvent e) {
						if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
							buildTable();
							doSearch(null);
						}
					}
				}));
	}

	private void setRequestDishesList()
	{
		
		if(cellBookingTable!=null && cellBookingTable.getVisibleItems()!=null && cellBookingTable.getVisibleItems().size()>0)
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
			
			injector.getOrderManager().getOrderRequest().setBookingDishesList(bookingDishesList);
			injector.getOrderManager().getOrderRequest().setBookingDishesListClient(cellBookingTable.getVisibleItems());
		}

	
	}
	
	
	String allCss;
	List<Span> anchorList=new ArrayList<Span>();
	private void init() {
		initDwKw();
		initOrderMessage();
		createTab();
		buildTable();
		doSearch(null);

		
	}
	private void initOrderMessage()
	{
		if( injector.getOrderManager().getOrderRequest()!=null)
		{
			display.setCity(injector.getOrderManager().getOrderRequest().getCity());
			if(injector.getOrderManager().getOrderRequest().getAmountOfClient()!=0)
				display.setNumber(injector.getOrderManager().getOrderRequest().getAmountOfClient()+"");
			else
				display.setNumber("");
			display.setOrderUser(injector.getOrderManager().getOrderRequest().getOrderPersonName());
			display.setRestaurant(injector.getOrderManager().getOrderRequest().getRestaurantName());
			display.setphone(injector.getOrderManager().getOrderRequest().getOrderPersonPhone());
			if(injector.getOrderManager().getOrderRequest().getFavoriteRoom()==1)
				display.setRoom("只订大厅");
			else if(injector.getOrderManager().getOrderRequest().getFavoriteRoom()==2)
				display.setRoom("只订包间");
			else if(injector.getOrderManager().getOrderRequest().getFavoriteRoom()==3)
				display.setRoom("先订大厅，如无大厅，订包间");
			else if(injector.getOrderManager().getOrderRequest().getFavoriteRoom()==4)
				display.setRoom("先订包间，如无包间，订大厅");
			else
				display.setRoom("");
			
			if(injector.getOrderManager().getOrderRequest().getBookingDishesListClient()!=null && injector.getOrderManager().getOrderRequest().getBookingDishesListClient().size()>0)
			{
				buildBookingTable();
				display.hiddenDishesNumber(false);
				cellBookingTable.setRowData(injector.getOrderManager().getOrderRequest().getBookingDishesListClient());
				sumNumberMoney();
			}

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
		criteria.setCorpId(Elt.CORPORATIONID);
//		criteria.setDeptId(request.getRestaurantId());
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

				if(cellBookingTable==null)
				{
					display.setBookingTitle("还未点菜");
					display.getBookingPanel().clear();
					display.hiddenDishesNumber(true);
				}
			}

		});
	}
	private void cleanAnchorCss()
	{
		if(anchorList!=null && anchorList.size()>0)
		{
			for (int i = 0; i < anchorList.size(); i++) {
				Span ma=anchorList.get(i);
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
		criteria.setCorpId(Elt.CORPORATIONID);
		if(!StringUtil.isEmpty(deptId))
		{
			criteria.setDeptId(deptId);
		}
		else
		{
			criteria.setDeptId(Elt.DEPARTMENTID);
		}
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
				    display.getTabpage().clear();
				    Map<String,List<DishesTypeListClient>> typeMap=new HashMap<String, List<DishesTypeListClient>>();
				    for (int i=0;i<typeList.size();i++) {
				    	final DishesTypeListClient client=typeList.get(i);
				    	
				    	if(!StringUtil.isEmpty(client.getDishesType()))
				    	{
				    		List<DishesTypeListClient> tList=typeMap.get(client.getDishesType());
				    		if(tList==null)
				    		{
				    			tList=new ArrayList<DishesTypeListClient>();
				    			tList.add(client);
				    			typeMap.put(client.getDishesType(), tList);
				    		}
				    		else
				    		{
				    			tList.add(client);
				    		}
				    	}
				    	else
				    	{
				    		List<DishesTypeListClient> tList=typeMap.get("未分类");
				    		if(tList==null)
				    		{
				    			tList=new ArrayList<DishesTypeListClient>();
				    			tList.add(client);
				    			typeMap.put("未分类", tList);
				    		}
				    		else
				    		{
				    			tList.add(client);
				    		}
				    		
				    	}
				    	
				    	

					}
			        Set<Map.Entry<String, List<DishesTypeListClient>>> set = typeMap.entrySet();
			        for (Iterator<Map.Entry<String, List<DishesTypeListClient>>> it = set.iterator(); it.hasNext();) {
			            Map.Entry<String, List<DishesTypeListClient>> entry = (Map.Entry<String, List<DishesTypeListClient>>) it.next();
			            
				    	DishesTypeLatticeWidget dtw=new DishesTypeLatticeWidget(entry.getKey(),entry.getValue(),ordersDishesPresenter);
				    	
				    	display.getTabpage().add(dtw.asWidget());
			        }

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
		display.setBookingTitle("");
		display.getBookingPanel().clear();
		display.getBookingPanel().add(cellBookingTable);
		cellBookingTable.setRowCount(0);
		
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<DishesListClient>();

	//	initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(pageSize);
	//	cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		cellTable.setLoadingIndicator(new Image("elt/images/loadlist.gif"));
		
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
	
	}

	private void doSearch(String typeId) {
		DishesListCriteria criteria = new DishesListCriteria();
		if(!StringUtil.isEmpty(typeId))
		criteria.setTypeId(typeId);
		criteria.setCorpId(Elt.CORPORATIONID);
		if(!StringUtil.isEmpty(deptId))
		{
			criteria.setDeptId(deptId);
		}
		else
		{
			criteria.setDeptId(Elt.DEPARTMENTID);
		}
		if(!StringUtil.isEmpty(display.getQueryKey().getValue()))
			criteria.setKeyName(display.getQueryKey().getValue());
		listViewAdapter = new OrdersDishesViewAdapter(dispatch, criteria,
				errorHandler, null,display,this,dishesDetailedDialogProvider);
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
					money+=Double.parseDouble(cellBookingTable.getRowElement(i).getCells().getItem(2).getFirstChildElement().getInnerText());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			display.setDishesNumber(number+"");
			display.setDishesMoney(money+"");
		}
		else
		{
			display.setDishesNumber(0+"");
			display.setDishesMoney(0+"");
		}
	}
//	private void initTableColumns() {
//		Sorting<DishesListClient> ref = new Sorting<DishesListClient>() {
//			@Override
//			public void sortingCurrentPage(Comparator<DishesListClient> comparator) {
//				// listViewAdapter.sortCurrentPage(comparator);
//			}
//
//			@Override
//			public void sortingAll(String sorting, String direction) {
//				listViewAdapter.sortFromDateBase(sorting, direction);
//
//			}
//		};
//
//
//		cellTable.addColumn("菜品", new TextCell(),
//				new GetValue<DishesListClient, String>() {
//					@Override
//					public String getValue(DishesListClient dishes) {
//						return dishes.getName();
//					}
//				}, ref, "name");
//
//		cellTable.addColumn("图片",new HyperLinkCell(),
//				new GetValue<DishesListClient, String>() {
//					@Override
//					public String getValue(DishesListClient dishes) {
//						return  "<img width='50px' height='50px' src='imageshow?imageName="+dishes.getPhoto()+"'>";
//					}
//				}, new FieldUpdater<DishesListClient, String>() {
//
//					@Override
//					public void update(int index, DishesListClient o, String value) {
//						display.getDetailPanel().clear();
//						display.getDetailPanel().add(new DishesDetailedWidget(o.getName(),o.getPrice(),o.getDescription(),o.getPhoto()));
//					}
//
//				});
//
//		cellTable.addColumn("价格", new TextCell(),
//				new GetValue<DishesListClient, String>() {
//					@Override
//					public String getValue(DishesListClient dishes) {
//						return dishes.getPrice();
//					}
//				}, ref, "name");
//		cellTable.addColumn("操作", new HyperLinkCell(),
//				new GetValue<DishesListClient, String>() {
//					@Override
//					public String getValue(DishesListClient dishesType) {
//						return "查看详细";
//					}
//				}, new FieldUpdater<DishesListClient, String>() {
//
//					@Override
//					public void update(int index, DishesListClient o, String value) {
//						display.getDetailPanel().clear();
//						display.getDetailPanel().add(new DishesDetailedWidget(o.getName(),o.getPrice(),o.getDescription(),o.getPhoto()));
//					}
//
//				});
//		cellTable.addColumn("操作", new HyperLinkCell(),
//				new GetValue<DishesListClient, String>() {
//					@Override
//					public String getValue(DishesListClient dishesType) {
//						return "添加";
//					}
//				}, new FieldUpdater<DishesListClient, String>() {
//
//					@Override
//					public void update(int index, DishesListClient o, String value) {
//					//	Window.alert(cellTable.getRowElement(index).getCells().getItem(4).getFirstChildElement().getFirstChildElement().getFirstChildElement().getAttribute("id"));
//						if(cellBookingTable==null)
//						{
//							buildBookingTable();
//							display.hiddenDishesNumber(false);
//						}
//						System.out.println(cellBookingTable.getRowCount());
//						if(cellBookingTable.getRowCount()>=1)
//						{
//						boolean fal=false;
//						for (int i = 0; i < cellBookingTable.getRowCount(); i++) {
//							if(cellBookingTable.getVisibleItem(i).getId().equals(o.getId()))
//							{
//								int number=(cellBookingTable.getVisibleItem(i).getNumber()+1);
//								cellBookingTable.getRowElement(i).getCells().getItem(1).getFirstChildElement().setInnerText(number+"");
//								cellBookingTable.getVisibleItem(i).setNumber(number);
//								
//								cellBookingTable.getRowElement(i).getCells().getItem(4).getFirstChildElement().setInnerText((number*Double.parseDouble(cellBookingTable.getVisibleItem(i).getPrice()))+"");
//								fal=true;
//							}
//							
//						}
//						if(fal==false)
//						{
//
//								BookingDishesClient col=new BookingDishesClient();
//								col.setId(o.getId());
//								col.setName(o.getName());
//								col.setNumber(1);
//								col.setPrice(o.getPrice());
//								if(kwlt.size()>0)
//								col.setTaste(kwlt.get(0));
//								if(dwlt.size()>0)
//								col.setUnit(dwlt.get(0));
//			
//								
//								List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
//								for (int i = 0; i < cellBookingTable.getVisibleItems().size(); i++) {
//										lt.add(cellBookingTable.getVisibleItems().get(i));
//								}
//								lt.add(col);
//								cellBookingTable.setRowData(lt);
//							
//						}
//					}
//						else{
//							BookingDishesClient col=new BookingDishesClient();
//							col.setId(o.getId());
//							col.setName(o.getName());
//							col.setNumber(1);
//							col.setPrice(o.getPrice());
//							if(kwlt.size()>0)
//							col.setTaste(kwlt.get(0));
//							if(dwlt.size()>0)
//							col.setUnit(dwlt.get(0));
//							List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
//							for (int i = 0; i < cellBookingTable.getVisibleItems().size(); i++) {
//									lt.add(cellBookingTable.getVisibleItems().get(i));
//							}
//							lt.add(col);
//							cellBookingTable.setRowData(lt);
//						}
//
//						
//						sumNumberMoney();
//					}
//
//				});
//	
//	}
	
	private void initBookingTableColumns() {
		


		cellBookingTable.addColumn("菜品", new TextCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getName();
					}
				});

		//Column<BookingDishesClient, String> numberColumn =
				cellBookingTable.addColumn("数量", new EditTextCell(),
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
						     cellBookingTable.getRowElement(index).getCells().getItem(2).getFirstChildElement().setInnerHTML((Double.parseDouble(value))* Double.parseDouble(o.getPrice())+"");
						     o.setNumber(Integer.parseInt(value));
						     o.setPrice((Double.parseDouble(value))* Double.parseDouble(o.getPrice())+"");
						     sumNumberMoney();
						}catch (Exception e) {
							 cellBookingTable.getRowElement(index).getCells().getItem(2).getFirstChildElement().setInnerHTML((1* Double.parseDouble(o.getPrice())+""));
							 o.setNumber(1);
						     o.setPrice(1* Double.parseDouble(o.getPrice())+"");
						}
					}

				});


//		cellBookingTable.addColumn("单位", new SelectionCell(dwlt),
//				new GetValue<BookingDishesClient, String>() {
//					@Override
//					public String getValue(BookingDishesClient dishes) {
//						return dishes.getUnit();
//					}
//				}, new FieldUpdater<BookingDishesClient, String>() {
//
//					@Override
//					public void update(int index, BookingDishesClient o, String value) {
//						o.setUnit(value);
//					}
//
//				});
//		
//
//		cellBookingTable.addColumn("口味", new SelectionCell(kwlt),
//				new GetValue<BookingDishesClient, String>() {
//					@Override
//					public String getValue(BookingDishesClient dishes) {
//						return dishes.getTaste();
//					}
//				}, new FieldUpdater<BookingDishesClient, String>() {
//
//					@Override
//					public void update(int index, BookingDishesClient o, String value) {
//						o.setTaste(value);
//					}
//
//				});
		cellBookingTable.addColumn("价格", new TextCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getPrice();
					}
				});
		cellBookingTable.addColumn("取消", new ImageLinkCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishesType) {
						return "http://www.4008823823.com.cn/kfcios/images2/btn_close.gif";
					}
				}, new FieldUpdater<BookingDishesClient, String>() {

					@Override
					public void update(int index, BookingDishesClient o, String value) {
						cellBookingTable.flush(); 
					
						List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
								for (int i = 0; i < cellBookingTable.getVisibleItems().size(); i++) {
									if(!cellBookingTable.getVisibleItems().get(i).getId().equals(o.getId()))
										lt.add(cellBookingTable.getVisibleItems().get(i));
								}
						cellBookingTable.setRowData(lt);
						cellBookingTable.flush(); 
						sumNumberMoney();
					}

				});
		
	
	}


//	private boolean verificationRequest(OrderSaveRequest req)
//	{
//		if (StringUtil.isEmpty(req.getAmountOfClient()+"") || req.getAmountOfClient()==0) {
//			Window.alert("请填写就餐人数");
//			return false;
//		}
//		if (StringUtil.isEmpty(req.getFavoriteRoom()+"") || req.getFavoriteRoom()==0) {
//			Window.alert("请选择预定类型");
//			return false;
//		}
//		if (StringUtil.isEmpty(req.getOrderPersonName())) {
//			Window.alert("请填写姓名");
//			return false;
//		}
//		if (StringUtil.isEmpty(req.getOrderPersonPhone())) {
//			Window.alert("请填写手机号");
//			return false;
//		}
//
//		return true;
//	}

	@Override
	public void updateDishesList(String id,String name,String price,List<String> tasteList) {
		if( injector.getOrderManager().getOrderRequest()==null || injector.getOrderManager().getOrderRequest().getOrderPersonPhone()==null)
		{
			injector.getOrderIndexPresenter().initPresenter(injector.getOrderLoginPresenter());
			return;
		}
		if(cellBookingTable==null)
		{
			buildBookingTable();
			display.hiddenDishesNumber(false);
		}
		System.out.println(cellBookingTable.getRowCount());
		if(cellBookingTable.getRowCount()>=1)
		{
		boolean fal=false;
		for (int i = 0; i < cellBookingTable.getRowCount(); i++) {
			if(cellBookingTable.getVisibleItem(i).getId().equals(id))
			{
				int number=(cellBookingTable.getVisibleItem(i).getNumber()+1);
				cellBookingTable.getRowElement(i).getCells().getItem(1).getFirstChildElement().setInnerText(number+"");
				cellBookingTable.getVisibleItem(i).setNumber(number);
				
				cellBookingTable.getRowElement(i).getCells().getItem(2).getFirstChildElement().setInnerText((number*Double.parseDouble(cellBookingTable.getVisibleItem(i).getPrice()))+"");
				fal=true;
			}
			
		}
		if(fal==false)
		{

				BookingDishesClient col=new BookingDishesClient();
				col.setId(id);
				col.setName(name);
				col.setNumber(1);
				col.setPrice(price);
				col.setUnitprice(price);
				col.setTasteList(tasteList);
//				if(kwlt.size()>0)
//				col.setTaste(kwlt.get(0));
//				if(dwlt.size()>0)
//				col.setUnit(dwlt.get(0));

				
				List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
				for (int i = 0; i < cellBookingTable.getVisibleItems().size(); i++) {
						lt.add(cellBookingTable.getVisibleItems().get(i));
				}
				lt.add(col);
				cellBookingTable.setRowData(lt);
			
		}
	}
		else{
			BookingDishesClient col=new BookingDishesClient();
			col.setId(id);
			col.setName(name);
			col.setNumber(1);
			col.setPrice(price);
			col.setUnitprice(price);
			col.setTasteList(tasteList);
//			if(kwlt.size()>0)
//			col.setTaste(kwlt.get(0));
//			if(dwlt.size()>0)
//			col.setUnit(dwlt.get(0));
			List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
			for (int i = 0; i < cellBookingTable.getVisibleItems().size(); i++) {
					lt.add(cellBookingTable.getVisibleItems().get(i));
			}
			lt.add(col);
			cellBookingTable.setRowData(lt);
		}

		
		sumNumberMoney();
	}

	@Override
	public void refulDishes(String typeId) {

			buildTable();
			doSearch(typeId);

	}

	@Override
	public void initDishesList(List<BookingDishesClient> dishesList) {
		cellBookingTable.setRowData(dishesList);

	}

	@Override
	public void cleanDishes() {
		injector.getOrderManager().getOrderRequest().setBookingDishesList(null);
		injector.getOrderManager().getOrderRequest().setBookingDishesListClient(null);
		List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
		if(cellBookingTable!=null)
		cellBookingTable.setRowData(lt);
		sumNumberMoney();

	}

	@Override
	public void initDeptId(String deptId) {
		this.deptId=deptId;
		
	}
		
	
}
