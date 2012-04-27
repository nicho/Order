/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.restaurantList.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.plugin.RestaurantListConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class RestaurantListEditorDescriptor implements EditorDescriptor {

	final Provider<RestaurantListEditor> editProvider;

	@Inject
	RestaurantListEditorDescriptor(Provider<RestaurantListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RestaurantListConstants.EDITOR_RESTAURANTLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RestaurantListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("分店列表");
		e.setModel(model);
		return e;
	}

}
