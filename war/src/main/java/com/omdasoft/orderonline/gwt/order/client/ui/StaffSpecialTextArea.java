package com.omdasoft.orderonline.gwt.order.client.ui;


import com.google.gwt.view.client.ProvidesKey;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.StaffClient;
import com.omdasoft.orderonline.gwt.order.client.widget.SpecialTextArea;

/**
 * @author Cream
 * @since 0.2.0
 */
public class StaffSpecialTextArea extends SpecialTextArea<StaffClient> {

	private static ProvidesKey<StaffClient> keyProvider = new ProvidesKey<StaffClient>() {
		@Override
		public Object getKey(StaffClient item) {
			return item.getId();
		}
	};

	public StaffSpecialTextArea() {
		super.setKeyProvider(keyProvider);
	}

	@Override
	protected void render(StaffClient value, StringBuffer sb) {
		sb.append(value.getName());
	}

}
