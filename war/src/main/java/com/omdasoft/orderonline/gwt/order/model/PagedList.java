package com.omdasoft.orderonline.gwt.order.model;

import java.io.Serializable;
import java.util.List;

public interface PagedList<T> extends Serializable {

	int getTotal();

	List<T> getList();
}
