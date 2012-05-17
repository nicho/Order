package com.omdasoft.orderonline.gwt.order.client.awardShopLattice.view;

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
import com.omdasoft.orderonline.gwt.order.client.awardShopLattice.presenter.AwardShopLatticePresenter.AwardShopLatticeDisplay;
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
	Anchor exchangeBtn;
	
	private static AwardShopLatticeWidgetUiBinder uiBinder = GWT
			.create(AwardShopLatticeWidgetUiBinder.class);

	interface AwardShopLatticeWidgetUiBinder extends
			UiBinder<Widget, AwardShopLatticeWidget> {
	}

	public AwardShopLatticeWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public AwardShopLatticeWidget(final String awardName,final String integral,String indate,String photo,final String id) {
		initWidget(uiBinder.createAndBindUi(this));
		this.awardName.setText(awardName);
		this.integral.setText(integral);
		
		if(!StringUtil.isEmpty(photo))
		this.photo.setUrl("imageshow?imageName="+photo);
		if(id!=null)
		{
		exchangeBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			//	win.alert("礼品名称:"+awardName+"-----积分:"+integral+"ID:"+id);

			}
		});
		this.awardName.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

			}
		});
		}
		else
		{
			exchangeBtn.setEnabled(false);
		}
		
		
	}

}
