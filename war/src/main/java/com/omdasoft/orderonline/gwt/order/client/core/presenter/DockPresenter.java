package com.omdasoft.orderonline.gwt.order.client.core.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface DockPresenter extends Presenter<DockPresenter.DockDisplay> {

	public static interface DockDisplay extends Display {
		
		HasClickHandlers getbtnUser();
		HasClickHandlers getbtnOrder();
		HasClickHandlers getbtnDishesMenu();
		HasClickHandlers getbtnPassward();
		
		HasClickHandlers getlogBtn();


		DockLayoutPanel getDock();
		
		Panel getMenu();
		
		void setMessage(String userName);
		void setMenu(Panel panel);
		void setMenuTitle(String title);
		
		void disableManagementCenter();
		void disableDishes();

		
		void changeTopMenu(String key);

	}
	
	
}
