package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface DishesTypeListPresenter extends Presenter<DishesTypeListPresenter.DishesTypeListDisplay> {
	

	public static interface DishesTypeListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();
		public HasClickHandlers getAddBtnClickHandlers();
		public HasClickHandlers getcopyBtnClickHandlers();

		void setDataCount(String text);
		void setBreadCrumbs(Widget breadCrumbs);
		
		Panel getResultPanel();
		Panel getResultpage();

		public ListBox getPageNumber();
		HasValue<String> getSearchName();
		
		void hiddenCopyBtn();
	}
}
