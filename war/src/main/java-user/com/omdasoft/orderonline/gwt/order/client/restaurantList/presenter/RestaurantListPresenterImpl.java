package com.omdasoft.orderonline.gwt.order.client.restaurantList.presenter;

import java.util.Comparator;

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
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.dataprovider.RestaurantListViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.model.RestaurantListClient;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.model.RestaurantListCriteria;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.model.RestaurantListCriteria.StaffStatus;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.request.RestaurantDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.request.RestaurantDeleteResponse;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.plugin.RestaurantSaveConstants;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.ui.HyperLinkCell;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.widget.Sorting;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.ConfirmHandler;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;

public class RestaurantListPresenterImpl extends
		BasePresenter<RestaurantListPresenter.RestaurantListDisplay> implements
		RestaurantListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<RestaurantListClient> cellTable;
	RestaurantListViewAdapter listViewAdapter;
	int pageSize=ViewConstants.per_page_number_in_entry;
	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public RestaurantListPresenterImpl(EventBus eventBus,
			RestaurantListDisplay display, DispatchAsync dispatch,
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
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						doSearch();
					}
				}));


		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								RestaurantSaveConstants.EDITOR_RESTAURANTSAVE_SEARCH,
								"EDITOR_RESTAURANTSAVE_SEARCH_DO_ID", null);
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
		display.initStaffStatus();
		buildTable();
		doSearch();
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<RestaurantListClient>();

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
		RestaurantListCriteria criteria = new RestaurantListCriteria();
		criteria.setStaffNameorNo(display.getStaffNameorNo().getValue());
		if(!"ALL".equals(display.getSttaffStatus()))
			criteria.setStaffStatus(StaffStatus.valueOf(display.getSttaffStatus()));
		if(!"ALL".equals(display.getSttaffRole()))
			criteria.setStaffRole(UserRoleVo.valueOf(display.getSttaffRole()));

		listViewAdapter = new RestaurantListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<RestaurantListClient> ref = new Sorting<RestaurantListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<RestaurantListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};

		cellTable.addColumn("序号", new TextCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return restaurant.getIndexNo();
					}
				});
		cellTable.addColumn("编号", new TextCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return restaurant.getId().substring(restaurant.getId().length()-4);
					}
				});
		cellTable.addColumn("分店缩写", new TextCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return restaurant.getDid();
					}
				});
		cellTable.addColumn("分店名", new TextCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return restaurant.getName();
					}
				}, ref, "corporation.name");
		cellTable.addColumn("城市", new TextCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return restaurant.getCity();
					}
				}, ref, "city");
		cellTable.addColumn("地址", new TextCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return restaurant.getAddress();
					}
				}, ref, "address");
		cellTable.addColumn("电话", new TextCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return restaurant.getPhone();
					}
				}, ref, "phone");
		cellTable.addColumn("分店管理员", new TextCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return restaurant.getDeptAdmin();
					}
				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return "修改";
					}
				}, new FieldUpdater<RestaurantListClient, String>() {

					@Override
					public void update(int index, RestaurantListClient o, String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								RestaurantSaveConstants.EDITOR_RESTAURANTSAVE_SEARCH,
								"EDITOR_RESTAURANTSAVE_SEARCH_DO_ID", o.getId());
					}

				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<RestaurantListClient, String>() {
					@Override
					public String getValue(RestaurantListClient restaurant) {
						return "删除";
					}
				}, new FieldUpdater<RestaurantListClient, String>() {

					@Override
					public void update(int index, final RestaurantListClient o, String value) {
						win.confirm("提示", "确定删除?",new ConfirmHandler() {
							
							@Override
							public void confirm() {
								dispatch.execute(new RestaurantDeleteRequest(o.getId(),sessionManager
										.getSession()), new AsyncCallback<RestaurantDeleteResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(RestaurantDeleteResponse response) {
										win.alert("删除成功!");
										buildTable();
										doSearch();
									}

								});
								
							}
						});
					}

				});
	}
	

}
