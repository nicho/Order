package com.omdasoft.orderonline.gwt.order.client.dishesList.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface DishesListPresenter extends Presenter<DishesListPresenter.DishesListDisplay> {
	

	public static interface DishesListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();
		public HasClickHandlers getAddBtnClickHandlers();
		public HasClickHandlers getCopyBtnClickHandlers();

		void hiddenCopyBtn();
		HasValue<String> getSearchName();

		void setDataCount(String text);
		void setBreadCrumbs(Widget breadCrumbs);
		
		Panel getResultPanel();
		Panel getResultpage();

		public ListBox getPageNumber();
		Panel getTabpage();
		Anchor getTypeall();
	}
}
