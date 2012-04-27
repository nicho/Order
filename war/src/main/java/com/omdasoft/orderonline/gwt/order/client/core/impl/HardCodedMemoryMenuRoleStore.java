/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.core.impl;

import com.omdasoft.orderonline.gwt.order.model.user.UserRoleVo;

/**
 * 
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public class HardCodedMemoryMenuRoleStore extends InMemoryMenuRoleStore {

	/**
	 * 
	 */
	public HardCodedMemoryMenuRoleStore() {
		super();

		initialize();
	}

	protected void initialize() {

		initMenuForCorpAdmin();

	}

	/**
	 * Initialize menu for senior customer service
	 */


	/**
	 * Initialize menu for corporation admin.
	 */
	protected void initMenuForCorpAdmin() {

		this.addMenusToRole(UserRoleVo.CORP_ADMIN.name(), new String[] {});

	}


}
