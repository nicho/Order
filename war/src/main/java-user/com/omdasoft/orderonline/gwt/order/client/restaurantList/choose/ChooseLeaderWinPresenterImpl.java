package com.omdasoft.orderonline.gwt.order.client.restaurantList.choose;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
import com.omdasoft.orderonline.gwt.order.client.mvp.BaseDialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.ChooseLeaderWinPresenter.ChooseLeaderWinDisplay;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.dataprovider.SearchLeaderAsyncDataProvider;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.widget.Sorting;

public class ChooseLeaderWinPresenterImpl extends
		BaseDialogPresenter<ChooseLeaderWinDisplay> implements
		ChooseLeaderWinPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	final EltNewPager simplePager = new EltNewPager(TextLocation.CENTER);
	ListCellTable<StaffClient> resultTable;
	SearchLeaderAsyncDataProvider listViewAdapter;

	// 为LeaderAsyncDataProvider准备的数据。
	boolean isChooseAll = false;
	/**
	 * 候选人(员工或部门)ids,得奖的人需要从中选择
	 */
	List<String> orgIds;

	boolean isLimitByNominee = false;
	boolean staffOnly = false;

	@Inject
	public ChooseLeaderWinPresenterImpl(EventBus eventBus,
			ChooseLeaderWinDisplay display, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
	}

	public Widget asWidget() {
		return display.asWidget();
	}

	private void init() {
		buildTable();
	}

	public void bind() {
		init();

		/*** 查询员工 ***/
		registerHandler(display.getSearchBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						doSearch();
					}
				}));

		/*** 重置查询信息 ***/
		registerHandler(display.getResetBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						display.reset();
					}
				}));
		// add
		registerHandler(display.getChooseBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						//
						eventBus.fireEvent(new ChooseLeaderEvent(
								getRealLeaders()));
						closeDialog();
					}
				}));
		registerHandler(display.getCancelBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						closeDialog();
					}
				}));
	}

	private void buildTable() {

		resultTable = new ListCellTable<StaffClient>();
		final SingleSelectionModel<StaffClient> selectionModel = setSelectionModel(resultTable);
		initTableColumns(selectionModel);

		simplePager.setDisplay(resultTable);
		resultTable.setWidth(ViewConstants.page_width);
		resultTable.setPageSize(ViewConstants.per_page_number_in_dialog);
		resultTable.setPageStart(0);
		display.getResultPanel().clear();
		display.getResultPanel().add(resultTable);
		display.getResultPage().add(simplePager);
	}

	private SingleSelectionModel<StaffClient> setSelectionModel(
			CellTable<StaffClient> cellTable) {
		final SingleSelectionModel<StaffClient> selectionModel = new SingleSelectionModel<StaffClient>();

		SelectionChangeEvent.Handler selectionHandler = new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				StaffClient staff = selectionModel.getSelectedObject();
				if (!display.getSpecialTextBox().containsItem(staff)) {
					display.getSpecialTextBox().clear();//清空原有选项，只能选一个
					display.getSpecialTextBox().addItem(staff);
				}
			}

		};
		selectionModel.addSelectionChangeHandler(selectionHandler);
		// cellTable.setSelectionEnabled(true); // FIXME make sure it works
		cellTable.setSelectionModel(selectionModel);

		return selectionModel;
	}

	/**
	 * 初始化table
	 * 
	 * @param selectionModel
	 */
	private void initTableColumns(
			final SelectionModel<StaffClient> selectionModel) {

		Sorting<StaffClient> ref = new Sorting<StaffClient>() {
			@Override
			public void sortingCurrentPage(Comparator<StaffClient> comparator) {
				listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);
			}
		};

		resultTable.addColumn("员工姓名", new TextCell(),
				new GetValue<StaffClient, String>() {
					@Override
					public String getValue(StaffClient staff) {
						return staff.getName();
					}
				}, ref, "staffName");


	}

	/**
	 * returns value will only get ID and name.
	 * 
	 * @return
	 */
	private List<StaffClient> getRealLeaders() {
		List<StaffClient> result = new ArrayList<StaffClient>();
		for (StaffClient staff : display.getSpecialTextBox().getItemList()) {
			result.add(staff);
		}
		return result;
	}

	/**
	 * 查询员工
	 */
	private void doSearch() {
		LeaderSearchCriteria criteriaVo = new LeaderSearchCriteria();
		criteriaVo.setKey(display.getName().getValue());
		criteriaVo.setDeptId(display.getDeptId());
		if (isLimitByNominee) {
			criteriaVo.setChooseAll(isChooseAll);
			criteriaVo.setOrgIds(orgIds);
		}
		resultTable.setPageStart(0);
		resultTable.setRowCount(0, false);

		listViewAdapter = new SearchLeaderAsyncDataProvider(dispatch, errorHandler,
				sessionManager, criteriaVo, false);// 提供数据
		listViewAdapter.addDataDisplay(resultTable);
	}

	public void setNominee(boolean isLimitByNominee, boolean isChooseAll,
			List<String> orgIds) {
		if (isLimitByNominee) {
			this.isChooseAll = isChooseAll;
			this.orgIds = orgIds;
			this.isLimitByNominee = isLimitByNominee;
		}
		doSearch();
	}

	@Override
	public void setLeaderOnly(boolean staffOnly) {
		this.staffOnly = staffOnly;
		buildTable();
		doSearch();
	}

}
