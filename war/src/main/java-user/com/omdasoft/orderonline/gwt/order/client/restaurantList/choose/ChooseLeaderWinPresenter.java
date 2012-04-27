package com.omdasoft.orderonline.gwt.order.client.restaurantList.choose;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.DialogPresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.widget.SpecialTextArea;

public interface ChooseLeaderWinPresenter extends
		DialogPresenter<ChooseLeaderWinPresenter.ChooseLeaderWinDisplay> {

	void setNominee(boolean isLimitByNominee, boolean isChooseAll,
			List<String> orgIds);

	void setLeaderOnly(boolean staffOnly);

	public static interface ChooseLeaderWinDisplay extends Display {
		HasValue<String> getName();

		HasClickHandlers getSearchBtn();

		HasClickHandlers getResetBtn();

		HasClickHandlers getChooseBtn();

		HasClickHandlers getCancelBtn();

		Widget asWidget();

		/**
		 * 重置查询信息
		 */
		void reset();

		SpecialTextArea<StaffClient> getSpecialTextBox();

		Panel getResultPanel();

		Panel getResultPage();

		String getDeptId();

	}

}
