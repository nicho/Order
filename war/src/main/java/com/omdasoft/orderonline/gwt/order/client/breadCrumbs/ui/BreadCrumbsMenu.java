package com.omdasoft.orderonline.gwt.order.client.breadCrumbs.ui;

import java.util.List;

import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.model.MenuBreadVo;
import com.omdasoft.orderonline.gwt.order.client.core.ui.MenuItem;


public interface BreadCrumbsMenu {

	void addBreadCrumbsItem(String name,MenuItem menuItem);
	void addBreadCrumbsItemTop(String name,MenuItem menuItem);
	void cleanBreadCrumbsItem();
	void cleanBreadCrumbsItemTop();
	void cleanBreadCrumbsItemAll();
	void cleanChildName();
	List<MenuBreadVo> getBreadCrumbsItem();
	List<MenuBreadVo> getHistoryBreadCrumbsItem();
	void setChildName(String name);

}
