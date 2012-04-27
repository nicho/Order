package com.omdasoft.orderonline.gwt.order.client.core.presenter;

import com.google.gwt.inject.client.GinModules;
import com.omdasoft.orderonline.gwt.order.client.PresenterModule;

/**
 * Centralize all Presenter/Display binding here. As Presenter depends some
 * basic infrastructure from MVP, so it extends MVPGinjector
 * 
 * @author yanxin
 * 
 */
@GinModules({ PresenterModule.class })
public interface PresentersGinjector {

}
