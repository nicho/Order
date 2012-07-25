package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.presenter;

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
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.dataprovider.DishesTypeListViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.DishesTypeDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.DishesTypeDeleteResponse;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.plugin.DishesTypeSaveConstants;
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
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;

public class DishesTypeListPresenterImpl extends
		BasePresenter<DishesTypeListPresenter.DishesTypeListDisplay> implements
		DishesTypeListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<DishesTypeListClient> cellTable;
	DishesTypeListViewAdapter listViewAdapter;
	int pageSize=ViewConstants.per_page_number_in_entry;
	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public DishesTypeListPresenterImpl(EventBus eventBus,
			DishesTypeListDisplay display, DispatchAsync dispatch,
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
								DishesTypeSaveConstants.EDITOR_DISHESTYPESAVE_SEARCH,
								"EDITOR_DISHESTYPESAVE_SEARCH_DO_ID", null);
					}
				}));
		
//		registerHandler(display.getcopyBtnClickHandlers().addClickHandler(
//				new ClickHandler() {
//					@Override
//					public void onClick(ClickEvent event) {
//						win.confirm("提示","确定复制餐厅菜品类别?(会删除当前分店菜品类别)",new ConfirmHandler() {
//							
//							@Override
//							public void confirm() {
//								dispatch.execute(new DishesTypeCopyRequest(sessionManager
//										.getSession().getDepartmentId()), new AsyncCallback<DishesTypeCopyResponse>() {
//									@Override
//									public void onFailure(Throwable e) {
//										errorHandler.alert(e.getMessage());
//									}
//
//									@Override
//									public void onSuccess(DishesTypeCopyResponse response) {
//										win.alert("复制成功!");
//										Platform.getInstance()
//										.getEditorRegistry()
//										.openEditor(
//												DishesTypeListConstants.EDITOR_DISHESTYPELIST_SEARCH,
//												"EDITOR_DISHESTYPELIST_SEARCH_DO_ID", null);
//									}
//
//								});
//								
//							}
//						});
//					}
//				}));
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
		cellTable = new ListCellTable<DishesTypeListClient>();

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
		DishesTypeListCriteria criteria = new DishesTypeListCriteria();
		criteria.setSession(sessionManager.getSession());
		criteria.setKeyName(display.getSearchName().getValue());
		listViewAdapter = new DishesTypeListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<DishesTypeListClient> ref = new Sorting<DishesTypeListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<DishesTypeListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};

		cellTable.addColumn("序号", new TextCell(),
				new GetValue<DishesTypeListClient, String>() {
					@Override
					public String getValue(DishesTypeListClient dishes) {
						return dishes.getIndexNo();
					}
				});
		cellTable.addColumn("编号", new TextCell(),
				new GetValue<DishesTypeListClient, String>() {
					@Override
					public String getValue(DishesTypeListClient dishesType) {
						return dishesType.getId().substring(dishesType.getId().length()-4);
					}
				});
		cellTable.addColumn("大类", new TextCell(),
				new GetValue<DishesTypeListClient, String>() {
					@Override
					public String getValue(DishesTypeListClient dishesType) {
						return dishesType.getDishesType();
					}
				}, ref, "dishesType");
		cellTable.addColumn("类名", new TextCell(),
				new GetValue<DishesTypeListClient, String>() {
					@Override
					public String getValue(DishesTypeListClient dishesType) {
						return dishesType.getName();
					}
				}, ref, "name");
		cellTable.addColumn("rid", new TextCell(),
				new GetValue<DishesTypeListClient, String>() {
					@Override
					public String getValue(DishesTypeListClient dishesType) {
						return dishesType.getRid();
					}
				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<DishesTypeListClient, String>() {
					@Override
					public String getValue(DishesTypeListClient dishesType) {
						return "修改";
					}
				}, new FieldUpdater<DishesTypeListClient, String>() {

					@Override
					public void update(int index, DishesTypeListClient o, String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								DishesTypeSaveConstants.EDITOR_DISHESTYPESAVE_SEARCH,
								"EDITOR_DISHESTYPESAVE_SEARCH_DO_ID", o.getId());
					}

				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<DishesTypeListClient, String>() {
					@Override
					public String getValue(DishesTypeListClient dishesType) {
						return "删除";
					}
				}, new FieldUpdater<DishesTypeListClient, String>() {

					@Override
					public void update(int index, final DishesTypeListClient o, String value) {
						win.confirm("提示", "确定删除?",new ConfirmHandler() {
							
							@Override
							public void confirm() {
								dispatch.execute(new DishesTypeDeleteRequest(o.getId(),sessionManager
										.getSession()), new AsyncCallback<DishesTypeDeleteResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(DishesTypeDeleteResponse response) {
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
