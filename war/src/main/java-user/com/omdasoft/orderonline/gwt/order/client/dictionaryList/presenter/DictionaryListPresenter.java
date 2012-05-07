package com.omdasoft.orderonline.gwt.order.client.dictionaryList.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface DictionaryListPresenter extends Presenter<DictionaryListPresenter.DictionaryListDisplay> {
	
	public void initDictionary(int dictionaryType);
	public static interface DictionaryListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();
		public HasClickHandlers getAddBtnClickHandlers();
		void initStaffStatus();
		String getSttaffStatus();
		String getSttaffRole();
		HasValue<String> getStaffNameorNo();

		void setDataCount(String text);
		void setBreadCrumbs(Widget breadCrumbs);
		
		Panel getResultPanel();
		Panel getResultpage();
		public ListBox getPageNumber();


	}
}
