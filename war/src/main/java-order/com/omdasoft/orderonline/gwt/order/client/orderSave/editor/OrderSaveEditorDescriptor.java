/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderSave.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderSave.plugin.OrderSaveConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class OrderSaveEditorDescriptor implements EditorDescriptor {

	final Provider<OrderSaveEditor> editProvider;

	@Inject
	OrderSaveEditorDescriptor(Provider<OrderSaveEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderSaveConstants.EDITOR_ORDERSAVE_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderSaveEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("订单保存");
		e.setModel(model);
		return e;
	}

}
