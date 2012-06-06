package com.omdasoft.orderonline.gwt.order.client.orderIndex.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.presenter.OrderIndexPresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class OrderIndexEditor extends AbstractEditor {

	final OrderIndexPresenter orderIndexPresenter;
	Object model;

	@Inject
	protected OrderIndexEditor(OrderIndexEditorDescriptor editorDescriptor,
			OrderIndexPresenter orderIndexPresenter) {
		super(editorDescriptor);
		this.orderIndexPresenter = orderIndexPresenter;
	}

	@Override
	public Widget asWidget() {
		return orderIndexPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		orderIndexPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(Object model) {
		this.model = model;
		orderIndexPresenter.bind();
	}
}
