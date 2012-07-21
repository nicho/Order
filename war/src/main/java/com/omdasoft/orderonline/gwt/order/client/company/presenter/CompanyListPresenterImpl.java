package com.omdasoft.orderonline.gwt.order.client.company.presenter;


import java.util.Comparator;
import java.util.Date;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.company.dataprovider.CompanyListViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.company.model.CompanyListClient;
import com.omdasoft.orderonline.gwt.order.client.company.model.CompanySearchVo;
import com.omdasoft.orderonline.gwt.order.client.company.presenter.CompanyListPresenter.CompanyListDisplay;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.plugin.CompanyAddConstants;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
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
/**
 * 
 * @author sunny
 * 公司列表
 */
public class CompanyListPresenterImpl extends BasePresenter<CompanyListDisplay> implements
		CompanyListPresenter {

	
	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
	int pageSize=ViewConstants.per_page_number_in_dialog;
	DateTimeFormat dateFormat = DateTimeFormat.getFormat(ViewConstants.date_format);
	EltNewPager pager;
	ListCellTable<CompanyListClient> cellTable;
	CompanyListViewAdapter listViewAdapter;
	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public CompanyListPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs,
			CompanyListDisplay display, Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
        this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		init();
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						doSearch();
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
        registerHandler(display.getAddCompanyBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								CompanyAddConstants.EDITOR_COMPANYADD_SEARCH,
								"EDITOR_COMAPNYADD_SEARCH_DO_ID", null);
					}
				}));
    }

	private void init() {
		buildTable();
		doSearch();
	}
	
	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<CompanyListClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(pageSize);

		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
	}

	private void doSearch() {
		CompanySearchVo criteria = new CompanySearchVo();
		criteria.setName(display.getCompanyName().getValue());
		listViewAdapter = new CompanyListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);
	}

	private void initTableColumns() {
		Sorting<CompanyListClient> ref = new Sorting<CompanyListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<CompanyListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);
			}
		};
		
		cellTable.addColumn("序号", new TextCell(),
				new GetValue<CompanyListClient, String>() {
					@Override
					public String getValue(CompanyListClient com) {
						return com.getCompanyNo();
					}
				});
		cellTable.addColumn("企业简称", new TextCell(),
				new GetValue<CompanyListClient, String>() {
					@Override
					public String getValue(CompanyListClient com) {
						return com.getCid();
					}
				});
		cellTable.addColumn("企业名称", new TextCell(),
				new GetValue<CompanyListClient, String>() {
					@Override
					public String getValue(CompanyListClient com) {
						return com.getName();
					}
				}, ref, "name");

		cellTable.addColumn("联系人", new TextCell(),
				new GetValue<CompanyListClient, String>() {
					@Override
					public String getValue(CompanyListClient com) {
						return com.getLinkman();
					}
				});

		cellTable.addColumn("创建日期", new DateCell(dateFormat),
				new GetValue<CompanyListClient, Date>() {
					@Override
					public Date getValue(CompanyListClient com) {
						return com.getCrearteAt();
					}
				},ref,"createdAt");
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<CompanyListClient, String>() {
					@Override
					public String getValue(CompanyListClient com) {
						return "修改";
					}
				}, new FieldUpdater<CompanyListClient, String>() {

					@Override
					public void update(int index, final CompanyListClient o,
							String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								CompanyAddConstants.EDITOR_COMPANYADD_SEARCH,
								"EDITOR_COMPANY_UPDATE_DO_ID", o.getId());
					}

				});
	
	}
}
