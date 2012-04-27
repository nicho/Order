/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesList.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesList.plugin.DishesListConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class DishesListEditorDescriptor implements EditorDescriptor {

	final Provider<DishesListEditor> editProvider;

	@Inject
	DishesListEditorDescriptor(Provider<DishesListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DishesListConstants.EDITOR_DISHESLIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DishesListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("菜品管理");
		e.setModel(model);
		return e;
	}

}
