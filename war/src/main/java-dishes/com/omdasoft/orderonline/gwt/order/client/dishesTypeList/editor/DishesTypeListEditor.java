package com.omdasoft.orderonline.gwt.order.client.dishesTypeList.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.presenter.DishesTypeListPresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class DishesTypeListEditor extends AbstractEditor {

	final DishesTypeListPresenter dishesTypeListPresenter;
	Object model;

	@Inject
	protected DishesTypeListEditor(DictionaryListEditorDescriptor editorDescriptor,
			DishesTypeListPresenter dishesTypeListPresenter) {
		super(editorDescriptor);
		this.dishesTypeListPresenter = dishesTypeListPresenter;
	}

	@Override
	public Widget asWidget() {
		return dishesTypeListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		dishesTypeListPresenter.unbind();
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
		dishesTypeListPresenter.bind();
	}
}
