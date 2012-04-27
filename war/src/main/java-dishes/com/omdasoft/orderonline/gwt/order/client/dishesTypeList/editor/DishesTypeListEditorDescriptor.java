/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.plugin.DishesTypeListConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class DishesTypeListEditorDescriptor implements EditorDescriptor {

	final Provider<DishesTypeListEditor> editProvider;

	@Inject
	DishesTypeListEditorDescriptor(Provider<DishesTypeListEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DishesTypeListConstants.EDITOR_DISHESTYPELIST_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DishesTypeListEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("菜品类别管理");
		e.setModel(model);
		return e;
	}

}
