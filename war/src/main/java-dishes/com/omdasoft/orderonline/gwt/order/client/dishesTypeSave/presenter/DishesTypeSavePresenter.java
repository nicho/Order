package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface DishesTypeSavePresenter extends Presenter<DishesTypeSavePresenter.DishesTypeSaveDisplay> {
	
public void initDishesType(String orderId);
	public static interface DishesTypeSaveDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();
		TextBox getName();
		TextBox getDishestype();
		void setBreadCrumbs(Widget breadCrumbs);
		void setRid(String text);
		void hiddenRid();
		Panel getDishestypePanel();
	}
}
