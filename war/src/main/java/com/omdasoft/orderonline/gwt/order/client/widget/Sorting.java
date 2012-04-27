package com.omdasoft.orderonline.gwt.order.client.widget;

import java.util.Comparator;

public interface Sorting<T> {
	public void sortingCurrentPage(Comparator<T> comparator);

	public void sortingAll(String sorting, String direction);
}
