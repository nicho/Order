package com.omdasoft.orderonline.gwt.order.client.mvp;

public interface Presenter<D extends Display> {

	void bind();

	void unbind();

	D getDisplay();

}