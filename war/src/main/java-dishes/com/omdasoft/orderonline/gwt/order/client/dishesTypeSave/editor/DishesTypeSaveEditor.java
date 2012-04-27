package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.presenter.DishesTypeSavePresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class DishesTypeSaveEditor extends AbstractEditor {

	final DishesTypeSavePresenter DishesTypeSavePresenter;
	Object model;

	@Inject
	protected DishesTypeSaveEditor(DictionaryListEditorDescriptor editorDescriptor,
			DishesTypeSavePresenter DishesTypeSavePresenter) {
		super(editorDescriptor);
		this.DishesTypeSavePresenter = DishesTypeSavePresenter;
	}

	@Override
	public Widget asWidget() {
		return DishesTypeSavePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		DishesTypeSavePresenter.unbind();
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
		if(model!=null)
			DishesTypeSavePresenter.initDishesType((String)model);
		DishesTypeSavePresenter.bind();
	}
}
