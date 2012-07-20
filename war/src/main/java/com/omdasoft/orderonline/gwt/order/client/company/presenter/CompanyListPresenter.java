package com.omdasoft.orderonline.gwt.order.client.company.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;


public interface CompanyListPresenter extends Presenter<CompanyListPresenter.CompanyListDisplay> {

	/**
	 * Logical UI structure for the Enterprise Window/Widget
	 * 
	 * @author sunny
	 * 
	 */
	
	public static interface CompanyListDisplay extends Display {
		
		public HasClickHandlers getSearchBtnClickHandlers();
		public HasClickHandlers getAddCompanyBtnClickHandlers();
		TextBox getCompanyName();
		Panel getResultPanel();
		Panel getResultpage();
		void setBreadCrumbs(Widget breadCrumbs);
		ListBox getPageNumber();
	}

}
