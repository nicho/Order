package com.omdasoft.orderonline.gwt.order.client.dishesList.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
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
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.DishesDeleteResponse;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.plugin.DishesSaveConstants;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.ui.HyperLinkCell;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.widget.Sorting;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.ConfirmHandler;

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
								DishesSaveConstants.EDITOR_DISHESSAVE_SEARCH,
								"EDITOR_DISHESSAVE_SEARCH_DO_ID", null);
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
		cellTable = new ListCellTable<DishesListClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number_in_dialog);
		cellTable.getColumn(0).setCellStyleNames("widthcell");
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
		
	}

	private void doSearch() {
		DishesListCriteria criteria = new DishesListCriteria();
	

		listViewAdapter = new DishesListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

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
										doSearch();
									}

								});
								
							}
						});
					}

				});
		
	}
	

}
