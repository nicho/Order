package com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.model.MenuBreadVo;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface BreadCrumbsPresenter extends	Presenter<BreadCrumbsPresenter.BreadCrumbsDisplay> {

	void setChildName(String name);
	void cleanChildName();
	/**
	 * 加载List页面,调用
	 */
	void loadListPage();
	/**
	 * 加载其他子页面,调用,传入子菜单的名称
	 */
	void loadChildPage(String menuName);
	/**
	 * 返回上一页
	 */
	void getGoHistory();
	public static interface BreadCrumbsDisplay extends Display {

		void setTitleText(List<MenuBreadVo> menuBreadVo);
		HasClickHandlers getGoHistory();
	}
}
