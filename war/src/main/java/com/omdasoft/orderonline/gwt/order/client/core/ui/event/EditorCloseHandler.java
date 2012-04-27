package com.omdasoft.orderonline.gwt.order.client.core.ui.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditorCloseHandler extends EventHandler {

	void onClose(String editorId, String instanceId);

}
