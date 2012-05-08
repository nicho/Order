package com.omdasoft.orderonline.gwt.order.client.dictionaryList.presenter;

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
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.dataprovider.DictionaryListViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.model.DictionaryListClient;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.model.DictionaryListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.DictionaryDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.request.DictionaryDeleteResponse;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.plugin.DictionarySaveConstants;
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

public class DictionaryListPresenterImpl extends
		BasePresenter<DictionaryListPresenter.DictionaryListDisplay> implements
		DictionaryListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<DictionaryListClient> cellTable;
	DictionaryListViewAdapter listViewAdapter;
	int dictionaryType = 0;
	private final BreadCrumbsPresenter breadCrumbs;
	int pageSize=ViewConstants.per_page_number_in_entry;
	@Inject
	public DictionaryListPresenterImpl(EventBus eventBus,
			DictionaryListDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,Win win,
			BreadCrumbsPresenter breadCrumbs, ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
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
										DictionarySaveConstants.EDITOR_DICTIONARYSAVE_SEARCH,
										"EDITOR_DICTIONARYSAVE_SEARCH_DO_ID",
										dictionaryType);
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
		cellTable = new ListCellTable<DictionaryListClient>();

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
		DictionaryListCriteria criteria = new DictionaryListCriteria();
		criteria.setDictionaryType(dictionaryType);
		listViewAdapter = new DictionaryListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<DictionaryListClient> ref = new Sorting<DictionaryListClient>() {
			@Override
			public void sortingCurrentPage(
					Comparator<DictionaryListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};
		cellTable.addColumn("序号", new TextCell(),
				new GetValue<DictionaryListClient, String>() {
					@Override
					public String getValue(DictionaryListClient staff) {
						return staff.getIndexNo();
					}
				});
		cellTable.addColumn("编号", new TextCell(),
				new GetValue<DictionaryListClient, String>() {
					@Override
					public String getValue(DictionaryListClient staff) {
						return staff.getId().substring(
								staff.getId().length() - 4);
					}
				});
		cellTable.addColumn("名称", new TextCell(),
				new GetValue<DictionaryListClient, String>() {
					@Override
					public String getValue(DictionaryListClient staff) {
						return staff.getName();
					}
				}, ref, "name");
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<DictionaryListClient, String>() {
					@Override
					public String getValue(DictionaryListClient dishesType) {
						return "删除";
					}
				}, new FieldUpdater<DictionaryListClient, String>() {

					@Override
					public void update(int index, final DictionaryListClient o, String value) {
						win.confirm("提示", "确定删除?",new ConfirmHandler() {
							
							@Override
							public void confirm() {
								dispatch.execute(new DictionaryDeleteRequest(o.getId(),sessionManager
										.getSession()), new AsyncCallback<DictionaryDeleteResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(DictionaryDeleteResponse response) {
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

	@Override
	public void initDictionary(int dictionaryType) {
		this.dictionaryType = dictionaryType;

	}

}
