package com.omdasoft.orderonline.gwt.order.client.ordersDishes.dataprovider;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.omdasoft.orderonline.gwt.order.client.awardShopLattice.view.AwardShopLatticeWidget;
import com.omdasoft.orderonline.gwt.order.client.dataprovider.BaseDataProvider;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.DishesListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesList.model.DishesListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.SearchDishesListRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesList.request.SearchDishesListResponse;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter.OrdersDishesDisplay;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;

public class OrdersDishesViewAdapter extends BaseDataProvider<DishesListClient> {

	final DispatchAsync dispatch;
	final OrdersDishesDisplay display;
	DishesListCriteria criteria;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	public OrdersDishesViewAdapter(DispatchAsync dispatch,
			DishesListCriteria criteria, ErrorHandler errorHandler,
			SessionManager sessionManager, OrdersDishesDisplay display) {
		this.dispatch = dispatch;
		this.criteria = criteria;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.display=display;
	}

	public void fetchData(final int start, final int length) {
		// if (!GWT.isScript()) {
		// List<DishesListClient> list = new ArrayList<DishesListClient>();
		// for (int i = start; i < start + length; i++) {
		// DishesListClient item = new DishesListClient();
		// item.setId("id" + i);
		// item.setName("rewards" + i);
		// //item.setStatus(OrdersDishesStatus.TO_BE_ISSUE);
		// list.add(item);
		// }
		//
		// updateRowData(start, list);
		// updateRowCount(100, true);
		// } else {
		PaginationDetailClient pagination = new PaginationDetailClient();
		pagination.setStart(start);
		pagination.setLimit(length);
		getCriteria().setPagination(pagination);
		if (getSorting() != null) {
			getCriteria().setSorting(getSorting());
		}
		dispatch.execute(new SearchDishesListRequest(getCriteria(), null), new AsyncCallback<SearchDishesListResponse>() {
			@Override
			public void onFailure(Throwable e) {
				errorHandler.alert(e.getMessage());
			}

			@Override
			public void onSuccess(SearchDishesListResponse response) {
			//	updateRowData(start, response.getResult());
				updateRowCount(response.getTotal(), true);
			
				List<DishesListClient> giftList=response.getResult();
				int index=0;
				 Grid grid = new Grid(2, 5);
					
				    // Add images to the grid
				    int numRows = grid.getRowCount();
				    int numColumns = grid.getColumnCount();
				    for (int row = 0; row < numRows; row++) {
				      for (int col = 0; col < numColumns; col++) {
				    	  if(index<giftList.size())	
				    	  {
				    		  DishesListClient clint=giftList.get(index);
				    		  grid.setWidget(row, col,new AwardShopLatticeWidget(clint.getName(),clint.getPrice()+"","",clint.getPhoto(),clint.getId()).asWidget());
				    	  	  index++;
				    	  }
				    	  else
				    	  {
//				    		  grid.setWidget(row, col,new AwardShopLatticeWidget("","0","",null,null).asWidget());
				    		  break;
				    	  }
				      }
				    }

				    // Return the panel
				    grid.ensureDebugId("cwGrid");
				    
					display.getResultPanel().clear();
					display.getResultPanel().add(grid);
				
				
			}

		});
		// }
	}


	public void setCriteria(DishesListCriteria criteria) {
		this.criteria = criteria;
	}

	private DishesListCriteria getCriteria() {
		if (criteria == null) {
			criteria = new DishesListCriteria();
		}

		return criteria;
	}
}
