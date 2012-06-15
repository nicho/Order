package com.omdasoft.orderonline.gwt.order.client.awardShopLattice.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.awardShopLattice.presenter.AwardShopLatticePresenter.AwardShopLatticeDisplay;
import com.omdasoft.orderonline.gwt.order.client.login.presenter.AlertErrorWidget;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.dialog.DishesDetailedDialog;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter.OrdersDishesDisplay;
import com.omdasoft.orderonline.gwt.order.client.ui.DialogBox;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;

public class AwardShopLatticeWidget extends Composite implements
		AwardShopLatticeDisplay {
	@UiField
	Anchor awardName;
	@UiField
	InlineLabel integral;

	@UiField
	Image photo;
	@UiField
	Image exchangeBtn;
	
	private static AwardShopLatticeWidgetUiBinder uiBinder = GWT
			.create(AwardShopLatticeWidgetUiBinder.class);

	interface AwardShopLatticeWidgetUiBinder extends
			UiBinder<Widget, AwardShopLatticeWidget> {
	}

	public AwardShopLatticeWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public AwardShopLatticeWidget(final String awardName,final String integral,final String indate,final String photo,final String id,final OrdersDishesDisplay display,final OrdersDishesPresenter ordersDishesPresenter,final Provider<DishesDetailedDialog> dishesDetailedDialogProvider,final List<String> tasteList) {
		initWidget(uiBinder.createAndBindUi(this));
		this.awardName.setText(awardName);
		this.integral.setText(integral);
		
		if(!StringUtil.isEmpty(photo))
		{
			this.photo.setUrl(StringUtil.getThumbImageUrl(photo));
		}
		if(id!=null)
		{
		exchangeBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ordersDishesPresenter.updateDishesList(id, awardName, integral,tasteList);
			}
		});
		this.awardName.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
//				display.getDetailPanel().clear();
//				display.getDetailPanel().add(new DishesDetailedWidget(awardName,integral,indate,photo));
//				

				final DishesDetailedDialog dialog = dishesDetailedDialogProvider.get();
				dialog.initDishesDetailed(awardName, integral, indate, photo);

				
				final AlertErrorWidget ae = new AlertErrorWidget();
				final DialogBox dialogBoxae = new DialogBox();
				ae.getOkBtn().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						dialogBoxae.hide();
					}
				});
			
				dialogBoxae.setWidget(dialog.asWidget());
				dialogBoxae.setGlassEnabled(true);
				dialogBoxae.setAnimationEnabled(true);
				dialogBoxae.setWidth("600px");
				dialogBoxae.setText("菜品详细");
				dialogBoxae.center();
				dialogBoxae.show();
			}
		});
		}
		
		
		this.photo.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final DishesDetailedDialog dialog = dishesDetailedDialogProvider.get();
				dialog.initDishesDetailed(awardName, integral, indate, photo);

				
				final AlertErrorWidget ae = new AlertErrorWidget();
				final DialogBox dialogBoxae = new DialogBox();
				ae.getOkBtn().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						dialogBoxae.hide();
					}
				});
			
				dialogBoxae.setWidget(dialog.asWidget());
				dialogBoxae.setGlassEnabled(true);
				dialogBoxae.setAnimationEnabled(true);
				dialogBoxae.setWidth("600px");
				dialogBoxae.setText("菜品详细");
				dialogBoxae.center();
				dialogBoxae.show();
				
			}
		});
		
		
	
	}

}
