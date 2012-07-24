package com.omdasoft.orderonline.gwt.order.client.core.ui.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.omdasoft.orderonline.gwt.order.client.company.plugin.CompanyConstants;
import com.omdasoft.orderonline.gwt.order.client.core.ui.MenuItem;
import com.omdasoft.orderonline.gwt.order.client.core.ui.MenuProcessor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.event.MenuClickEvent;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.plugin.DictionaryListConstants;
import com.omdasoft.orderonline.gwt.order.client.dishesList.plugin.DishesListConstants;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.plugin.DishesTypeListConstants;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.orderList.plugin.OrderListConstants;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.plugin.RestaurantListConstants;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.userList.plugin.UserListConstants;
import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;

public class ButtonMenuProcessor implements MenuProcessor {

	final EventBus eventBus;
	final BreadCrumbsMenu breadCrumbsMenu;
	final SessionManager sessionManager;
	List<MenuItem> items = new LinkedList<MenuItem>();
	MenuNode root = new MenuNode(null);
	VerticalPanel grid;

	@Inject
	public ButtonMenuProcessor(EventBus eventBus,
			BreadCrumbsMenu breadCrumbsMenu,SessionManager sessionManager) {
		this.eventBus = eventBus;
		this.breadCrumbsMenu = breadCrumbsMenu;
		this.sessionManager=sessionManager;

	}

	public void add(MenuItem item) {
		items.add(item);
	}

	public MenuItem getMenuItem(String menuId) {
		if (menuId != null) {
			for (MenuItem i : items) {
				if (menuId.endsWith(i.getMenuId())) {
					return i;
				}
			}
		}
		return null;
	}

	public void render(Panel container) {
		Collections.sort(items, new Comparator<MenuItem>() {
			public int compare(MenuItem paramT1, MenuItem paramT2) {
				return paramT1.getMenuId().length()
						- paramT2.getMenuId().length();
			}
		});

		for (MenuItem m : items) {
			root.appendChild(new MenuNode(m));
		}
		List <UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
		UserRoleVo [] roles=sessionManager.getSession().getUserRoles();
		
		if(roles.length>0)
		{
			for (UserRoleVo r:roles) {
				roleslt.add(r);
			}
		}
		 String indexMenu="Order";
		 if(roleslt.contains(UserRoleVo.PLATFORM_ADMIN))
			  indexMenu="Company";

		
		ScrollPanel menuWrapper = new ScrollPanel(createButtonMenuWidget(indexMenu));
		container.add(menuWrapper);
	}

	/**
	 * 
	 * 
	 * @param parent
	 * @param node
	 */
	private Widget createButtonMenuWidget(String name) {
		breadCrumbsMenu.cleanBreadCrumbsItemAll();
		grid = new VerticalPanel();
		grid.setWidth("100%");
		// int i = 0;
		for (MenuNode node : root.getChildren()) {
			final Anchor button = new Anchor();
			final MenuItem menuItem = node.getValue();
			if (name != null) {
				List<String> items = getMenuItemName(name);
				if (!items.contains(menuItem.getMenuId()))
					continue;
			} else {
				break;
			}

			button.setText(menuItem.getTitle());
			button.setStyleName("menu-link");

			// 判断第一个进入默认样式
						String menuId = menuItem.getMenuId();
						if (menuId.equals(UserListConstants.MENU_USERLIST_SEARCH)
								|| menuId
										.equals(OrderListConstants.MENU_ORDERLIST_SEARCH)
								|| menuId.equals(DishesListConstants.MENU_DISHESLIST_SEARCH)) {

							button.setStyleName("menu-link menu-selected");
							breadCrumbsMenu.cleanBreadCrumbsItemTop();
							if (menuId.equals(UserListConstants.MENU_USERLIST_SEARCH))
								breadCrumbsMenu.addBreadCrumbsItemTop("用户管理", menuItem);
							else if (menuId
									.equals(OrderListConstants.MENU_ORDERLIST_SEARCH))
								breadCrumbsMenu.addBreadCrumbsItemTop("订单管理", menuItem);
							else if (menuId.equals(DishesListConstants.MENU_DISHESLIST_SEARCH))
								breadCrumbsMenu.addBreadCrumbsItemTop("菜单管理", menuItem);
							else if (menuId.equals(CompanyConstants.MENU_COMPANY_SEARCH))
								breadCrumbsMenu.addBreadCrumbsItemTop("公司管理",menuItem);
							
							breadCrumbsMenu.addBreadCrumbsItem(menuItem.getTitle(),
									menuItem);
						}
			

			button.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent paramClickEvent) {
					button.setStyleName("menu-link menu-selected");
					breadCrumbsMenu.cleanBreadCrumbsItem();
					breadCrumbsMenu.addBreadCrumbsItem(menuItem.getTitle(),
							menuItem);
					eventBus.fireEvent(new MenuClickEvent(menuItem));
					for (int i = 0; i < grid.getWidgetCount(); i++) {
						if (grid.getWidget(i) instanceof Anchor) {
							if (!button.getText().equals(
									((Anchor) grid.getWidget(i)).getText())) {
								grid.getWidget(i).setStyleName("menu-link");
							}
						}
					}
				}
			});
			grid.add(button);
			// i++;
		}

		return grid;
	}

	private List<String> getMenuItemName(String keyname) {
		List<String> items = new ArrayList<String>();
		if("User".equals(keyname))
		{
			items.add(UserListConstants.MENU_USERLIST_SEARCH);
			items.add(RestaurantListConstants.MENU_RESTAURANTLIST_SEARCH);
			items.add(DictionaryListConstants.MENU_DICTIONARYLIST_SEARCH);
		}
		if("Order".equals(keyname))
		{
			items.add(OrderListConstants.MENU_ORDERLIST_SEARCH);
		}
		if("Menu".equals(keyname))
		{
			items.add(DishesListConstants.MENU_DISHESLIST_SEARCH);
			items.add(DishesTypeListConstants.MENU_DISHESTYPELIST_SEARCH);
		}
		if ("Company".equals(keyname)) {
			items.add(CompanyConstants.MENU_COMPANY_SEARCH);
		}
		return items;
	}

	@Override
	public void initrender(Panel container, String name) {
		// organize tree
		// sort according to menuID string length
		Collections.sort(items, new Comparator<MenuItem>() {
			public int compare(MenuItem paramT1, MenuItem paramT2) {
				return paramT1.getMenuId().length()
						- paramT2.getMenuId().length();
			}
		});

		// pack into the MenuNode structure
		// for (MenuItem m : items) {
		// // append children recursively
		// root.appendChild(new MenuNode(m));
		// }

		ScrollPanel menuWrapper = new ScrollPanel(createButtonMenuWidget(name));
		container.clear();
		container.add(menuWrapper);

	}

	@Override
	public void changItemColor(String menuName) {
		for (int i = 0; i < grid.getWidgetCount(); i++) {
			if (grid.getWidget(i) instanceof Anchor) {
				System.out.println(((Anchor) grid.getWidget(i)).getText()+"");
				if (!menuName.equals(((Anchor) grid.getWidget(i)).getText())) {
					grid.getWidget(i).setStyleName("menu-link");
				} else {
					grid.getWidget(i).setStyleName("menu-link menu-selected");
				}
			}
		}

	}

}
