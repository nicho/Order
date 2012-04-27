package com.omdasoft.orderonline.gwt.order.client.dishesList.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesList.presenter.DishesListPresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class DishesListEditor extends AbstractEditor {

	final DishesListPresenter dishesListPresenter;
	Object model;

	@Inject
	protected DishesListEditor(DictionaryListEditorDescriptor editorDescriptor,
			DishesListPresenter dishesListPresenter) {
		super(editorDescriptor);
		this.dishesListPresenter = dishesListPresenter;
	}

	@Override
	public Widget asWidget() {
		return dishesListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		dishesListPresenter.unbind();
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
		dishesListPresenter.bind();
	}
}
