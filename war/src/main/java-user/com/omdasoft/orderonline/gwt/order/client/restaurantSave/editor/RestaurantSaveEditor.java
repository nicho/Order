package com.omdasoft.orderonline.gwt.order.client.restaurantSave.editor;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractEditor;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.presenter.RestaurantSavePresenter;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class RestaurantSaveEditor extends AbstractEditor {

	final RestaurantSavePresenter RestaurantSavePresenter;
	Object model;

	@Inject
	protected RestaurantSaveEditor(DictionaryListEditorDescriptor editorDescriptor,
			RestaurantSavePresenter RestaurantSavePresenter) {
		super(editorDescriptor);
		this.RestaurantSavePresenter = RestaurantSavePresenter;
	}

	@Override
	public Widget asWidget() {
		return RestaurantSavePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		RestaurantSavePresenter.unbind();
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
			RestaurantSavePresenter.initRestaurant((String)model);
		RestaurantSavePresenter.bind();
	}
}
