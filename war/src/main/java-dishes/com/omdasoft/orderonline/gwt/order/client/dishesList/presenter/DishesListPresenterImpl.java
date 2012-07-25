package com.omdasoft.orderonline.gwt.order.client.dishesList.presenter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
import com.omdasoft.orderonline.gwt.order.client.dishesList.dataprovider.DishesListViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.DishesListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.DishesListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dishesList.plugin.DishesListConstants;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesCopyRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesCopyResponse;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesDeleteResponse;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.plugin.DishesSaveConstants;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListResponse;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.ui.HyperLinkCell;
import com.omdasoft.orderonline.gwt.order.client.ui.MyAnchor;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.widget.Sorting;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.ConfirmHandler;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class DishesListPresenterImpl extends
		BasePresenter<DishesListPresenter.DishesListDisplay> implements
		DishesListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<DishesListClient> cellTable;
	DishesListViewAdapter listViewAdapter;
	int pageSize=ViewConstants.per_page_number_in_entry;
	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public DishesListPresenterImpl(EventBus eventBus,
			DishesListDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,Win win,BreadCrumbsPresenter breadCrumbs,ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler=errorHandler;
		this.win=win;
		this.breadCrumbs=breadCrumbs;
	}

	@Override
	public void bind() {

		if(UserRoleVo.CORP_ADMIN==sessionManager.getSession().getLastLoginRole())
		{
			display.hiddenCopyBtn();
		}
		
		
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						buildTable();
						doSearch(null);
					}
				}));
		registerHandler(display.getPageNumber().addChangeHandler(new ChangeHandler() {			
			@Override
			public void onChange(ChangeEvent event) {
				pageSize=Integer.parseInt(display.getPageNumber().getValue(display.getPageNumber().getSelectedIndex()));
				buildTable();
				doSearch(null);
			}
		}));
		registerHandler(display.getCopyBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						win.confirm("提示","确定复制餐厅菜品?(会删除当前分店菜品)",new ConfirmHandler() {
							
							@Override
							public void confirm() {
								dispatch.execute(new DishesCopyRequest(sessionManager
										.getSession().getToken()), new AsyncCallback<DishesCopyResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(DishesCopyResponse response) {
										win.alert("复制成功!");
										Platform.getInstance()
										.getEditorRegistry()
										.openEditor(
												DishesListConstants.EDITOR_DISHESLIST_SEARCH,
												"EDITOR_DISHESLIST_SEARCH_DO_ID", null);
									}

								});
								
							}
						});
					}
				}));
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								DishesSaveConstants.EDITOR_DISHESSAVE_SEARCH,
								"EDITOR_DISHESSAVE_SEARCH_DO_ID", null);
					}
				}));
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
	String allCss="all cur";
	List<MyAnchor> anchorList=new ArrayList<MyAnchor>();
	private void init() {
		
		 createTab();
			buildTable();
			doSearch(null);
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<DishesListClient>();

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

	private void doSearch(String typeId) {
		DishesListCriteria criteria = new DishesListCriteria();
		criteria.setKeyName(display.getSearchName().getValue());
		if(!StringUtil.isEmpty(typeId))
		criteria.setTypeId(typeId);
		listViewAdapter = new DishesListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}
	private void createTab()
	{
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(0);
		pagination.setLimit(0);
		
		DishesTypeListCriteria criteria = new DishesTypeListCriteria();
		criteria.setPagination(pagination);
		criteria.setSession(sessionManager.getSession());
		dispatch.execute(new SearchDishesTypeListRequest(criteria,sessionManager.getSession()), new AsyncCallback<SearchDishesTypeListResponse>() {
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
					}

				}
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

		cellTable.addColumn("序号", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getIndexNo();
					}
				});
		cellTable.addColumn("编号", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getId().substring(dishes.getId().length()-4);
					}
				});
		cellTable.addColumn("菜品", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getName();
					}
				}, ref, "name");
		cellTable.addColumn("描述", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getDescription();
					}
				});
		cellTable.addColumn("图片", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getPhoto();
					}
				});
		cellTable.addColumn("类别", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getDishesType();
					}
				});
		cellTable.addColumn("价格", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getPrice();
					}
				});
		cellTable.addColumn("rid", new TextCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishes) {
						return dishes.getRid();
					}
				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishesType) {
						return "修改";
					}
				}, new FieldUpdater<DishesListClient, String>() {

					@Override
					public void update(int index, DishesListClient o, String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								DishesSaveConstants.EDITOR_DISHESSAVE_SEARCH,
								"EDITOR_DISHESSAVE_SEARCH_DO_ID", o.getId());
					}

				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<DishesListClient, String>() {
					@Override
					public String getValue(DishesListClient dishesType) {
						return "删除";
					}
				}, new FieldUpdater<DishesListClient, String>() {

					@Override
					public void update(int index, final DishesListClient o, String value) {
						win.confirm("提示", "确定删除?",new ConfirmHandler() {
							
							@Override
							public void confirm() {
								dispatch.execute(new DishesDeleteRequest(o.getId(),sessionManager
										.getSession()), new AsyncCallback<DishesDeleteResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(DishesDeleteResponse response) {
										win.alert("删除成功!");
										buildTable();
										doSearch(null);
									}

								});
								
							}
						});
					}

				});
		
	}
	

}
