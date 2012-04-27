/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.restaurantSave.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.plugin.RestaurantSaveConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class RestaurantSaveEditorDescriptor implements EditorDescriptor {

	final Provider<RestaurantSaveEditor> editProvider;

	@Inject
	RestaurantSaveEditorDescriptor(Provider<RestaurantSaveEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return RestaurantSaveConstants.EDITOR_RESTAURANTSAVE_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		RestaurantSaveEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("分店保存");
		e.setModel(model);
		return e;
	}

}
