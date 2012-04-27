/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.plugin.DishesTypeSaveConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class DishesTypeSaveEditorDescriptor implements EditorDescriptor {

	final Provider<DishesTypeSaveEditor> editProvider;

	@Inject
	DishesTypeSaveEditorDescriptor(Provider<DishesTypeSaveEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DishesTypeSaveConstants.EDITOR_DISHESTYPESAVE_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DishesTypeSaveEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("菜单类别");
		e.setModel(model);
		return e;
	}

}
