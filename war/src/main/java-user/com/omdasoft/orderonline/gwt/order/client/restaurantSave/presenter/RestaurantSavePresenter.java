package com.omdasoft.orderonline.gwt.order.client.restaurantSave.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.model.OrganicationClient;
import com.omdasoft.orderonline.gwt.order.client.widget.SpecialTextArea;

public interface RestaurantSavePresenter extends Presenter<RestaurantSavePresenter.RestaurantSaveDisplay> {
	
public void initRestaurant(String orderId);
	public static interface RestaurantSaveDisplay extends Display {

		public HasClickHandlers getAddBtnClickHandlers();
		
		void setBreadCrumbs(Widget breadCrumbs);
		TextBox getCity();
		
		TextBox getRestaurant();
		
		TextBox getAddress();
		TextBox getPhone();
		TextBox getDid();
		
		public HasClickHandlers getChooseLeaderBtnClick();
	    public SpecialTextArea<OrganicationClient> getLeaderArea();
		

	}
}
