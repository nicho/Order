/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderView.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderView.plugin.OrderViewConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class OrderViewEditorDescriptor implements EditorDescriptor {

	final Provider<OrderViewEditor> editProvider;

	@Inject
	OrderViewEditorDescriptor(Provider<OrderViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderViewConstants.EDITOR_ORDERVIEW_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderViewEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("订单保存");
		e.setModel(model);
		return e;
	}

}
