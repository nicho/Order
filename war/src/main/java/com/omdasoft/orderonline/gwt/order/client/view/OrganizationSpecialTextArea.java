/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.view;

import com.google.gwt.view.client.ProvidesKey;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.model.OrganicationClient;
import com.omdasoft.orderonline.gwt.order.client.widget.SpecialTextArea;

/**
 * @author Cream
 * @since 0.2.0
 */
public class OrganizationSpecialTextArea extends
		SpecialTextArea<OrganicationClient> {

	private static ProvidesKey<OrganicationClient> keyProvider = new ProvidesKey<OrganicationClient>() {
		@Override
		public Object getKey(OrganicationClient item) {
			return item.getId();
		}
	};

	public OrganizationSpecialTextArea() {
		super.setKeyProvider(keyProvider);
	}

	@Override
	protected void render(OrganicationClient value, StringBuffer sb) {
		sb.append(value.getName());
	}

}
