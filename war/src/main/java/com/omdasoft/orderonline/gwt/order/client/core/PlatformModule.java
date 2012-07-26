package com.omdasoft.orderonline.gwt.order.client.core;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.omdasoft.orderonline.gwt.order.client.company.plugin.CompanyListPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.companyAdd.plugin.CompanyAddPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.core.impl.CorePluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.core.impl.GinPluginManager;
import com.omdasoft.orderonline.gwt.order.client.core.impl.InMemoryMenuRoleStore;
import com.omdasoft.orderonline.gwt.order.client.core.impl.InMemoryPluginSet;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorRegistry;
import com.omdasoft.orderonline.gwt.order.client.core.ui.MenuProcessor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.SiteManager;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.ButtonMenuProcessor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.SimpleEditorRegistry;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.SimpleSiteManager;
import com.omdasoft.orderonline.gwt.order.client.dictionaryList.plugin.DictionaryListPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dictionarySave.plugin.DictionarySavePluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesList.plugin.DishesListPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.plugin.DishesSavePluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.plugin.DishesTypeListPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.plugin.DishesTypeSavePluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.enterprise.plugin.EnterprisePluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderIndex.plugin.OrderIndexPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderList.plugin.OrderListPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderSave.plugin.OrderSavePluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderSubmit.plugin.OrderSubmitPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.orderView.plugin.OrderViewPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.registerHr.plugin.RegisterHrPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.restaurantList.plugin.RestaurantListPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.restaurantSave.plugin.RestaurantSavePluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.userAdd.plugin.UserAddPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.userList.plugin.UserListPluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.userView.plugin.UserViewPluginDescriptor;

public class PlatformModule extends AbstractGinModule {

	InMemoryPluginSet pluginSet = null;

	@Override
	protected void configure() {
		bind(Platform.class).in(Singleton.class);
		bind(PluginManager.class).to(GinPluginManager.class)
				.in(Singleton.class);

		bind(MenuProcessor.class).to(ButtonMenuProcessor.class).in(
				Singleton.class);
		bind(SiteManager.class).to(SimpleSiteManager.class).in(Singleton.class);
		bind(EditorRegistry.class).to(SimpleEditorRegistry.class).in(
				Singleton.class);

		bind(MenuRoleStore.class).to(InMemoryMenuRoleStore.class).in(
				Singleton.class);

		// ---- PLUGINS DEFINE BELOW (1) ----
		bind(CorePluginDescriptor.class).in(Singleton.class);

	}

	@Provides
	@Named("admin")
	PluginSet providePluginSet(
	// ---- PLUGINS DEFINE BELOW (2) ----
			CorePluginDescriptor core, // core
			DictionaryListPluginDescriptor dictionaryList,
			RestaurantListPluginDescriptor restaurantList,
			UserListPluginDescriptor userList,
			
			OrderListPluginDescriptor orderList,
			OrderSavePluginDescriptor orderSave,
			DictionarySavePluginDescriptor dictionarySave,
			RestaurantSavePluginDescriptor restaurantSave,
			DishesListPluginDescriptor dishesList,
			DishesTypeListPluginDescriptor dishesTypeList,
			DishesSavePluginDescriptor dishesSave,
			DishesTypeSavePluginDescriptor dishesTypeSave,
			EnterprisePluginDescriptor enterprise,
			OrderSubmitPluginDescriptor orderSubmit,
			OrderViewPluginDescriptor OrderView,
			UserViewPluginDescriptor UserView,
			UserAddPluginDescriptor UserAdd
			

	) {

		if (pluginSet == null) {
			pluginSet = new InMemoryPluginSet();
			pluginSet.registerPlugin(core);
			pluginSet.registerPlugin(dictionaryList);
			pluginSet.registerPlugin(restaurantList);
			pluginSet.registerPlugin(userList);

			pluginSet.registerPlugin(orderList);
			pluginSet.registerPlugin(orderSave);
			pluginSet.registerPlugin(dictionarySave);
			pluginSet.registerPlugin(restaurantSave);

			pluginSet.registerPlugin(enterprise);
			pluginSet.registerPlugin(orderSubmit);
			pluginSet.registerPlugin(OrderView);
			pluginSet.registerPlugin(UserView);
			pluginSet.registerPlugin(UserAdd);
			pluginSet.registerPlugin(dishesSave);
			pluginSet.registerPlugin(dishesTypeSave);
			pluginSet.registerPlugin(dishesList);
			pluginSet.registerPlugin(dishesTypeList);
		}
		
		return pluginSet;
	}

	
	@Provides
	@Named("dept")
	PluginSet provideDeptPluginSet(
	// ---- PLUGINS DEFINE BELOW (2) ----
			CorePluginDescriptor core, // core
			
			OrderListPluginDescriptor orderList,
			OrderSavePluginDescriptor orderSave,
			DishesListPluginDescriptor dishesList,

			DishesSavePluginDescriptor dishesSave,
			DishesTypeSavePluginDescriptor dishesTypeSave,
			EnterprisePluginDescriptor enterprise,
			OrderSubmitPluginDescriptor orderSubmit,
			OrderViewPluginDescriptor OrderView	
	) {

		if (pluginSet == null) {
			pluginSet = new InMemoryPluginSet();
			pluginSet.registerPlugin(core);
			pluginSet.registerPlugin(orderList);
			pluginSet.registerPlugin(orderSave);
			pluginSet.registerPlugin(enterprise);
			pluginSet.registerPlugin(orderSubmit);
			pluginSet.registerPlugin(OrderView);
			pluginSet.registerPlugin(dishesSave);
			pluginSet.registerPlugin(dishesTypeSave);
			pluginSet.registerPlugin(dishesList);

		}
		return pluginSet;
	}
	@Provides
	@Named("order")
	PluginSet provideOrderPluginSet(
	// ---- PLUGINS DEFINE BELOW (2) ----
			CorePluginDescriptor core,
			OrderIndexPluginDescriptor orderIndex
//			OrdersDishesPluginDescriptor ordersDishes
	) {

		if (pluginSet == null) {
			pluginSet = new InMemoryPluginSet();
			pluginSet.registerPlugin(core);
			pluginSet.registerPlugin(orderIndex);
//			pluginSet.registerPlugin(ordersDishes);

		}
		return pluginSet;
	}
	
	
	@Provides
	@Named("platformAdmin")
	PluginSet providePluginSetPlatformAdmin(
			// ---- PLUGINS DEFINE BELOW (2) ----
			CorePluginDescriptor core, // core
			CompanyListPluginDescriptor companyList,
			CompanyAddPluginDescriptor companyAddList,
			RegisterHrPluginDescriptor registerhr
			) {

		if (pluginSet == null) {
			pluginSet = new InMemoryPluginSet();
			pluginSet.registerPlugin(core);
			pluginSet.registerPlugin(companyList);
			pluginSet.registerPlugin(companyAddList);
			pluginSet.registerPlugin(registerhr);
		}

		return pluginSet;
	}
}
