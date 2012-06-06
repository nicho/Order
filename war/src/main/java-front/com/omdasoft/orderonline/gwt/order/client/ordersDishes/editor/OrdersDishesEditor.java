package com.omdasoft.orderonline.gwt.order.client.ordersDishes.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.ordersDishes.presenter.OrdersDishesPresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class OrdersDishesEditor extends AbstractEditor {

	final OrdersDishesPresenter ordersDishesPresenter;
	Object model;

	@Inject
	protected OrdersDishesEditor(OrdersDishesEditorDescriptor editorDescriptor,
			OrdersDishesPresenter ordersDishesPresenter) {
		super(editorDescriptor);
		this.ordersDishesPresenter = ordersDishesPresenter;
	}

	@Override
	public Widget asWidget() {
		return ordersDishesPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		ordersDishesPresenter.unbind();
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
		ordersDishesPresenter.bind();
	}
}
