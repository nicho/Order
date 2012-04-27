/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dictionarySave.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.plugin.DictionarySaveConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class DictionarySaveEditorDescriptor implements EditorDescriptor {

	final Provider<DictionarySaveEditor> editProvider;

	@Inject
	DictionarySaveEditorDescriptor(Provider<DictionarySaveEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DictionarySaveConstants.EDITOR_DICTIONARYSAVE_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DictionarySaveEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("字典保存");
		e.setModel(model);
		return e;
	}

}
