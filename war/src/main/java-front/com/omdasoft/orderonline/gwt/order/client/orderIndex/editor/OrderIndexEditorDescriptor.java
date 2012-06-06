/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.orderIndex.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.plugin.OrderIndexConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class OrderIndexEditorDescriptor implements EditorDescriptor {

	final Provider<OrderIndexEditor> editProvider;

	@Inject
	OrderIndexEditorDescriptor(Provider<OrderIndexEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return OrderIndexConstants.EDITOR_ORDERINDEX_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		OrderIndexEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("");
		e.setModel(model);
		return e;
	}

}
