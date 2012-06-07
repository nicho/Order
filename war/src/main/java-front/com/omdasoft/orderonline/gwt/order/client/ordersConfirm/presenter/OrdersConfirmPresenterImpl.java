package com.omdasoft.orderonline.gwt.order.client.ordersConfirm.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.EltGinjector;
import com.omdasoft.orderonline.gwt.order.client.core.view.constant.ViewConstants;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesClient;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.BookingDishesList;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.ui.ImageLinkCell;
import com.omdasoft.orderonline.gwt.order.client.widget.GetValue;
import com.omdasoft.orderonline.gwt.order.client.widget.ListCellTable;


public class OrdersConfirmPresenterImpl extends
		BasePresenter<OrdersConfirmPresenter.OrdersConfirmDisplay> implements
		OrdersConfirmPresenter {

	private final DispatchAsync dispatch;

	final ErrorHandler errorHandler;
	private final EltGinjector injector;
	ListCellTable<BookingDishesClient> cellBookingTable;
	@Inject
	public OrdersConfirmPresenterImpl(EventBus eventBus,
			OrdersConfirmDisplay display, DispatchAsync dispatch,ErrorHandler errorHandler,EltGinjector injector) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler=errorHandler;
		this.injector=injector;
	}

	@Override
	public void bind() {
		buildBookingTable();
		cellBookingTable.setRowData(injector.getOrderManager().getOrderRequest().getBookingDishesListClient());
		sumNumberMoney();
		
		registerHandler(display.getNextBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Window.alert("待实现");
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


		cellBookingTable.addColumn("单位", new TextCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getUnit();
					}
				});
		
		cellBookingTable.addColumn("口味", new TextCell(),
				new GetValue<BookingDishesClient, String>() {
					@Override
					public String getValue(BookingDishesClient dishes) {
						return dishes.getTaste();
					}
				});
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
}
