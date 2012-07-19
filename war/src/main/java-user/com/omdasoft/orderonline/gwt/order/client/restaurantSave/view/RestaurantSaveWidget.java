package com.omdasoft.orderonline.gwt.order.client.restaurantSave.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.model.OrganicationClient;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.presenter.RestaurantSavePresenter.RestaurantSaveDisplay;
import com.omdasoft.orderonline.gwt.order.client.view.OrganizationSpecialTextArea;
import com.omdasoft.orderonline.gwt.order.client.widget.SpecialTextArea;

public class RestaurantSaveWidget extends Composite implements RestaurantSaveDisplay {
	
	@UiField
	Button addBtn;
	@UiField
	TextBox city;
	@UiField
	TextBox restaurant;
	@UiField
	TextBox address;
	@UiField
	TextBox phone;
	// leader
	@UiField
	Panel leaderPanel;
	@UiField
	Panel breadCrumbs;
	@UiField
	TextBox did;
	
	SpecialTextArea<OrganicationClient> leaderArea;	
	SpecialTextArea<OrganicationClient> preLeaderArea;

	@UiField
	Button chooseLeaderBtn;
	private static RestaurantSaveWidgetUiBinder uiBinder = GWT
			.create(RestaurantSaveWidgetUiBinder.class);

	interface RestaurantSaveWidgetUiBinder extends UiBinder<Widget, RestaurantSaveWidget> {
	}

	public RestaurantSaveWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		leaderArea = new OrganizationSpecialTextArea();


		leaderPanel.clear();
		leaderPanel.add(leaderArea);// leader面板

	}

	
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public HasClickHandlers getAddBtnClickHandlers() {
		return addBtn;
	}

	@Override
	public TextBox getCity() {
		return city;
	}

	@Override
	public TextBox getRestaurant() {
		return restaurant;
	}

	@Override
	public TextBox getAddress() {
		return address;
	}

	@Override
	public TextBox getPhone() {
		return phone;
	}

	@Override
	public HasClickHandlers getChooseLeaderBtnClick() {
		return chooseLeaderBtn;
	}

	@Override
	public SpecialTextArea<OrganicationClient> getLeaderArea() {
		return leaderArea;
	}


	@Override
	public TextBox getDid() {
		return did;
	}






}
