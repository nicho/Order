package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesClient;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesList;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.AlertErrorWidget;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.orderSave.request.OrderSaveResponse;
import com.omdasoft.orderonline.gwt.order.client.ordersConfirm.dialog.kwDialog;
import com.omdasoft.orderonline.gwt.order.client.ui.DialogBox;
import com.omdasoft.orderonline.gwt.order.client.ui.ImageLinkCell;
import com.omdasoft.orderonline.gwt.order.client.ui.SelectHyperLinkCell;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;
import com.omdasoft.orderonline.gwt.order.client.win.confirm.OrderConfirmWidget;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;


public class OrdersConfirmPresenterImpl extends
		BasePresenter<OrdersConfirmPresenter.OrdersConfirmDisplay> implements
		OrdersConfirmPresenter {

	private final DispatchAsync dispatch;

	final ErrorHandler errorHandler;
	private final EltGinjector injector;
	ListCellTable<BookingDishesClient> cellBookingTable;
	List<String> dwlt=new ArrayList<String>();
	List<String> kwlt=new ArrayList<String>();
	private final Provider<kwDialog> kwDialogProvider;
	@Inject
	public OrdersConfirmPresenterImpl(EventBus eventBus,
			OrdersConfirmDisplay display, DispatchAsync dispatch,ErrorHandler errorHandler,EltGinjector injector,Provider<kwDialog> kwDialogProvider) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler=errorHandler;
		this.injector=injector;
		this.kwDialogProvider=kwDialogProvider;
	}

	@Override
	public void bind() {
		buildBookingTable();
		cellBookingTable.setRowData(injector.getOrderManager().getOrderRequest().getBookingDishesListClient());
		sumNumberMoney();
		
		registerHandler(display.getNextBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setRequestDishesList();
		//		保存
				injector.getOrderManager().getOrderRequest();
				OrderSaveRequest request=new OrderSaveRequest();
//				request=injector.getOrderManager().getOrderRequest();
				request.setId(injector.getOrderManager().getOrderRequest().getId());
				request.setOrderPersonPhone(injector.getOrderManager().getOrderRequest().getOrderPersonPhone());
				request.setDishesOrRoomFal("DISHES");
				request.setBookingDishesList(injector.getOrderManager().getOrderRequest().getBookingDishesList());
				request.setRestaurantId(injector.getOrderManager().getOrderRequest().getRestaurantId());
				request.setRestaurantName(injector.getOrderManager().getOrderRequest().getRestaurantName());
				request.setCity(injector.getOrderManager().getOrderRequest().getCity());
				dispatch.execute(request,
						new AsyncCallback<OrderSaveResponse>() {
							@Override
							public void onFailure(Throwable e) {
								errorHandler.alert(e.getMessage());
							}

							@Override
							public void onSuccess(
									OrderSaveResponse response) {
								injector.getOrderManager().getOrderRequest().setId(response.getOrderId());
								
								if(!"NOTRESERVATION".equals(response.getRoomState()))
								{
									injector.getOrdersWaitPresenter().setRoomFal(true);
									injector.getOrderIndexPresenter().initPresenter(injector.getOrdersWaitPresenter());
								}
								else
								{		
									final OrderConfirmWidget ae = new OrderConfirmWidget();
									final DialogBox dialogBoxae = new DialogBox();
									ae.getOkBtn().addClickHandler(new ClickHandler() {
										@Override
										public void onClick(ClickEvent arg0) {
											dialogBoxae.hide();
											injector.getOrderSubmitPresenter().initOrderPhone(injector.getOrderManager().getOrderRequest().getOrderPersonPhone());
											injector.getOrderIndexPresenter().initPresenter(injector.getOrderSubmitPresenter());
										}
									});
									
									ae.getCancelBtn().addClickHandler(new ClickHandler() {
										@Override
										public void onClick(ClickEvent arg0) {
											dialogBoxae.hide();
											injector.getOrdersWaitPresenter().setRoomFal(false);
											injector.getOrderIndexPresenter().initPresenter(injector.getOrdersWaitPresenter());
										}
									});
									ae.setMsg("您所点的菜品已经保存在网上，当您前往餐厅消费时，可到营业厅通过手机号下载使用您的菜单!<br><br><font color='blue'>需要网上订房吗？</font>");
									dialogBoxae.setWidget(ae);
									dialogBoxae.setGlassEnabled(true);
									dialogBoxae.setAnimationEnabled(true);
									dialogBoxae.setWidth("350px");
									dialogBoxae.setText("提示");
									dialogBoxae.center();
									dialogBoxae.show();
									
									
								}
						
							}

						});
			}
		}));
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
private void initBookingTableColumns() {
		


		cellBookingTable.addColumn("菜品", new TextCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getName();
					}
				});
		cellBookingTable.addColumn("单价", new TextCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getUnitprice();
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
						     cellBookingTable.getRowElement(index).getCells().getItem(5).getFirstChildElement().setInnerHTML((Double.parseDouble(value))* Double.parseDouble(o.getPrice())+"");
						     o.setNumber(Integer.parseInt(value));
						     o.setPrice((Double.parseDouble(value))* Double.parseDouble(o.getPrice())+"");
						     sumNumberMoney();
						}catch (Exception e) {
							 cellBookingTable.getRowElement(index).getCells().getItem(5).getFirstChildElement().setInnerHTML((1* Double.parseDouble(o.getPrice())+""));
							 o.setNumber(1);
						     o.setPrice(1* Double.parseDouble(o.getPrice())+"");
						}
					}

				});


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
		
		final int x=display.getBookingPanel().getAbsoluteLeft();
		final int y=display.getBookingPanel().getAbsoluteTop();
		cellBookingTable.addColumn("口味", new SelectHyperLinkCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getTaste();
					}
				}, new FieldUpdater<BookingDishesClient, String>() {
					
					@Override
					public void update(final int index, BookingDishesClient o, String value) {
						if(o.getTasteList()!=null && o.getTasteList().size()>0)
						{
						final kwDialog dialog = kwDialogProvider.get();
						dialog.initKW(o.getTasteList(),value);
						
						final AlertErrorWidget ae = new AlertErrorWidget();
						final DialogBox dialogBoxae = new DialogBox();
						ae.getOkBtn().addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								dialogBoxae.hide();
							}
						});
						dialog.getPresenter().getDisplay().getButtonSelect().addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								dialogBoxae.hide();
								if(dialog.getkwlt()!=null && dialog.getkwlt().size()>0)
								{
									String strkw="";
									for (int i = 0; i < dialog.getkwlt().size(); i++) {
										strkw+=dialog.getkwlt().get(i)+",";
									}
									List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
									BookingDishesClient xxx=cellBookingTable.getVisibleItem(index);
									if(!StringUtil.isEmpty(strkw))
										xxx.setTaste(strkw.substring(0,strkw.length()-1));
									
									lt.add(xxx);
									cellBookingTable.setRowData(index,lt);
								}
								else
								{
									List<BookingDishesClient> lt=new ArrayList<BookingDishesClient>();
									BookingDishesClient xxx=cellBookingTable.getVisibleItem(index);
									xxx.setTaste("");
									lt.add(xxx);
									cellBookingTable.setRowData(index,lt);
								}
								
							}
						});
					
						dialogBoxae.setWidget(dialog.asWidget());
						dialogBoxae.setGlassEnabled(true);
						dialogBoxae.setAnimationEnabled(true);
						dialogBoxae.setWidth("100px");
						dialogBoxae.setText("口味");
	
						dialogBoxae.setPopupPosition(x+400, y+(index*25));
//						dialogBoxae.center();
						dialogBoxae.show();
					}
						else
							Window.alert("无口味");		
					}

				
						

			} );
		
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
						setRequestDishesList();
					}

				});
		
	
	}
private void sumNumberMoney()
{
	if(cellBookingTable!=null && cellBookingTable.getRowCount()>0)
	{
		int number=0;
		double money=0;
		for (int i = 0; i < cellBookingTable.getRowCount(); i++) {
			try {
				number+=Integer.parseInt(cellBookingTable.getRowElement(i).getCells().getItem(2).getFirstChildElement().getInnerText());
				money+=Double.parseDouble(cellBookingTable.getRowElement(i).getCells().getItem(5).getFirstChildElement().getInnerText());
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
		
		injector.getOrderManager().getOrderRequest().setBookingDishesList(bookingDishesList);
		injector.getOrderManager().getOrderRequest().setBookingDishesListClient(cellBookingTable.getVisibleItems());
	}


}

@Override
public void initDwKw(List<String> dwlt, List<String> kwlt) {
	this.dwlt=dwlt;
	this.kwlt=kwlt;
}
}
