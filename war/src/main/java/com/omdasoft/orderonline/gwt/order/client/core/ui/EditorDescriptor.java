package com.omdasoft.orderonline.gwt.order.client.core.ui;

public interface EditorDescriptor {

	String getEditorId();

	Editor createEditor(String instanceId, Object model);
}
