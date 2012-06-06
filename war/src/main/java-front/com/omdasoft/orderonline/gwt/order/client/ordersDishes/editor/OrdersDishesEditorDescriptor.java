/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.ordersDishes.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.plugin.OrdersDishesConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class OrdersDishesEditorDescriptor implements EditorDescriptor {

	final Provider<OrdersDishesEditor> editProvider;

	@Inject
	OrdersDishesEditorDescriptor(Provider<OrdersDishesEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrdersDishesConstants.EDITOR_ORDERSDISHES_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrdersDishesEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("");
		e.setModel(model);
		return e;
	}

}
