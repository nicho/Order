package com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter;

import com.omdasoft.orderonline.gwt.order.client.mvp.DialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;

public interface DishesDetailedPresenter extends
		DialogPresenter<DishesDetailedPresenter.DishesDetailedDisplay> {
	public void initDishesDetail(String name,String prices,String description,String photo);
	public static interface DishesDetailedDisplay extends Display {

		void setName(String text);

		void setPrice(String text);

		void setDescription(String text);

		void setPhoto(String text);
	}
}
