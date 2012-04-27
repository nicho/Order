/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderSubmit.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.plugin.OrderSubmitConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class OrderSubmitEditorDescriptor implements EditorDescriptor {

	final Provider<OrderSubmitEditor> editProvider;

	@Inject
	OrderSubmitEditorDescriptor(Provider<OrderSubmitEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderSubmitConstants.EDITOR_ORDERSUBMIT_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderSubmitEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("订单保存");
		e.setModel(model);
		return e;
	}

}
