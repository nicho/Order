package com.omdasoft.orderonline.gwt.order.client.core.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface PlatformPresenter extends
		Presenter<PlatformPresenter.PlatformDisplay> {

	public static interface PlatformDisplay extends Display {

		HasClickHandlers getlogBtn();

		HasClickHandlers getBtnCompany();

		HasClickHandlers getBtnCollection();

		HasClickHandlers getPlatformManagement();

		DockLayoutPanel getDock();

		void changeTopMenu(String key);

		Panel getMenu();

		void setMessage(String userName);

		void setMenu(Panel panel);

		void setMenuTitle(String title);

 
	}

}
