package com.omdasoft.orderonline.gwt.order.client.ordersDishes.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListClient;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter;

public class DishesTypeLatticeWidget extends Composite {

	@UiField
	Panel tabpage;
	@UiField
	InlineLabel menutype;
	
	private static AwardShopLatticeWidgetUiBinder uiBinder = GWT
			.create(AwardShopLatticeWidgetUiBinder.class);

	interface AwardShopLatticeWidgetUiBinder extends
			UiBinder<Widget, DishesTypeLatticeWidget> {
	}

	public DishesTypeLatticeWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public DishesTypeLatticeWidget(final String typeName, final List<DishesTypeListClient> typeMap,final OrdersDishesPresenter ordersDishesPresenter) {
		initWidget(uiBinder.createAndBindUi(this));
		menutype.setText(typeName);
		
		for (DishesTypeListClient ct:typeMap) {
			DishesTypeLatticeTooWidget dtw=new DishesTypeLatticeTooWidget(ct.getId(),ct.getName(),ordersDishesPresenter);
	    	
			tabpage.add(dtw.asWidget());
		}
    	

	}

}
