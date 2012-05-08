package com.omdasoft.orderonline.gwt.order.client.userList.presenter;

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
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.ui.HyperLinkCell;
import com.omdasoft.orderonline.gwt.order.client.ui.UniversalCell;
import com.omdasoft.orderonline.gwt.order.client.userAdd.plugin.UserAddConstants;
import com.omdasoft.orderonline.gwt.order.client.userList.dataprovider.UserListViewAdapter;
import com.omdasoft.orderonline.gwt.order.client.userList.model.UserListClient;
import com.omdasoft.orderonline.gwt.order.client.userList.model.UserListCriteria;
import com.omdasoft.orderonline.gwt.order.client.userList.model.UserListCriteria.StaffStatus;
import com.omdasoft.orderonline.gwt.order.client.userList.request.UpdateUserPwdRequest;
import com.omdasoft.orderonline.gwt.order.client.userList.request.UpdateUserPwdResponse;
import com.omdasoft.orderonline.gwt.order.client.userList.request.UserDeleteRequest;
import com.omdasoft.orderonline.gwt.order.client.userList.request.UserDeleteResponse;
import com.omdasoft.orderonline.gwt.order.client.userView.plugin.UserViewConstants;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager;
import com.omdasoft.orderonline.gwt.order.client.widget.EltNewPager.TextLocation;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.ConfirmHandler;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class UserListPresenterImpl extends
		BasePresenter<UserListPresenter.UserListDisplay> implements
		UserListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<UserListClient> cellTable;
	UserListViewAdapter listViewAdapter;
	int pageSize=ViewConstants.per_page_number_in_entry;
	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public UserListPresenterImpl(EventBus eventBus,
			UserListDisplay display, DispatchAsync dispatch,Win win,
			SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs,ErrorHandler errorHandler) {
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


		registerHandler(display.getAddStaffBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								UserAddConstants.EDITOR_STAFFADD_SEARCH,
								"EDITOR_STAFFADD_SEARCH_DO_ID", null);
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
		cellTable = new ListCellTable<UserListClient>();

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
		UserListCriteria criteria = new UserListCriteria();
		criteria.setStaffNameorNo(display.getStaffNameorNo().getValue());
		if(!"ALL".equals(display.getSttaffStatus()))
			criteria.setStaffStatus(StaffStatus.valueOf(display.getSttaffStatus()));
		if(!"ALL".equals(display.getSttaffRole()))
			criteria.setStaffRole(UserRoleVo.valueOf(display.getSttaffRole()));

		listViewAdapter = new UserListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
//		Sorting<UserListClient> ref = new Sorting<UserListClient>() {
//			@Override
//			public void sortingCurrentPage(Comparator<UserListClient> comparator) {
//				// listViewAdapter.sortCurrentPage(comparator);
//			}
//
//			@Override
//			public void sortingAll(String sorting, String direction) {
//				listViewAdapter.sortFromDateBase(sorting, direction);
//
//			}
//		};

		cellTable.addColumn("序号", new TextCell(),
				new GetValue<UserListClient, String>() {
					@Override
					public String getValue(UserListClient order) {
						return order.getIndexNo();
					}
				});
		cellTable.addColumn("用户名", new TextCell(),
				new GetValue<UserListClient, String>() {
					@Override
					public String getValue(UserListClient staff) {
						return staff.getStaffName();
					}
				});

		cellTable.addColumn("电话", new TextCell(),
				new GetValue<UserListClient, String>() {
					@Override
					public String getValue(UserListClient staff) {
						return staff.getPhone();
					}
				});
		cellTable.addColumn("状态", new TextCell(),
				new GetValue<UserListClient, String>() {
					@Override
					public String getValue(UserListClient staff) {
						return staff.getStatus();
					}
				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<UserListClient, String>() {
					@Override
					public String getValue(UserListClient rewards) {
						return "查看";
					}
				}, new FieldUpdater<UserListClient, String>() {

					@Override
					public void update(int index, final UserListClient o,
							String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								UserViewConstants.EDITOR_STAFFVIEW_SEARCH,
								"EDITOR_STAFFVIEW_SEARCH_DO_ID", o.getStaffId());
					}

				});
		cellTable.addColumn("操作", new UniversalCell(),
				new GetValue<UserListClient, String>() {
					@Override
					public String getValue(UserListClient rewards) {
						if(!StringUtil.isEmpty(rewards.getStaffId()))
						return "<a style=\"color:bule;\" href=\"javascript:void(0);\">重置密码</a>";
						else
						return "<span  style='color: rgb(221, 221, 221);'>重置密码</span>";
					}
				}, new FieldUpdater<UserListClient, String>() {

					@Override
					public void update(int index, final UserListClient o,
							String value) {
						if(!StringUtil.isEmpty(o.getStaffId()))
						win.confirm("提示", "确定重置 <font color='blue'>"+o.getStaffName()+"</font> 的密码", new ConfirmHandler() {
							
							@Override
							public void confirm() {
								dispatch.execute(new UpdateUserPwdRequest(o.getStaffId(),"123",sessionManager.getSession()),
										new AsyncCallback<UpdateUserPwdResponse>() {

											@Override
											public void onFailure(Throwable t) {
												win.alert(t.getMessage());
											}

											@Override
											public void onSuccess(UpdateUserPwdResponse resp) {
												if("success".equals(resp.getMessage()))
												{
													win.alert("密码重置成功!初始密码:123");
												
												}
												
											}
										});
								}
						});
						
						
					}

				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<UserListClient, String>() {
					@Override
					public String getValue(UserListClient restaurant) {
						if(!"已启用".equals(restaurant.getStatus()))
							return "启用";
						else 
							return "停用";
					}
				}, new FieldUpdater<UserListClient, String>() {

					@Override
					public void update(int index, final UserListClient o, String value) {
						if(!"已启用".equals(o.getStatus()))
						{
						win.confirm("提示", "确定启用?",new ConfirmHandler() {
							@Override
							public void confirm() {
								dispatch.execute(new UserDeleteRequest(o.getStaffId(),sessionManager
										.getSession(),0), new AsyncCallback<UserDeleteResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(UserDeleteResponse response) {
										win.alert("启用成功!");
										buildTable();
										doSearch();
									}

								});
								
							}
						});
						}
						else if(!"已停用".equals(o.getStatus()))
						{
						win.confirm("提示", "确定停用?",new ConfirmHandler() {
							
							@Override
							public void confirm() {
								dispatch.execute(new UserDeleteRequest(o.getStaffId(),sessionManager
										.getSession(),1), new AsyncCallback<UserDeleteResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(UserDeleteResponse response) {
										win.alert("停用成功!");
										buildTable();
										doSearch();
									}

								});
								
							}
						});
						}
					}

				});
		
	}
	

}
