package com.omdasoft.orderonline.gwt.order.client.dictionarySave.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface DictionarySavePresenter extends Presenter<DictionarySavePresenter.DictionarySaveDisplay> {
	
public void initDictionary(String orderId);
public void initDictionaryType(int dictionaryType);
	public static interface DictionarySaveDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();
		TextBox getName();
		void setBreadCrumbs(Widget breadCrumbs);
	}
}
