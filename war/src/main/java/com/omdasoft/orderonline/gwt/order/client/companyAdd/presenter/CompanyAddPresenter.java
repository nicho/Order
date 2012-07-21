package com.omdasoft.orderonline.gwt.order.client.companyAdd.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.omdasoft.orderonline.gwt.order.client.mvp.Display;
import com.omdasoft.orderonline.gwt.order.client.mvp.Presenter;

public interface CompanyAddPresenter extends
		Presenter<CompanyAddPresenter.CompanyAddDisplay> {

	public void initCompanyUpdate(String companyId);

	public static interface CompanyAddDisplay extends Display {
		HasClickHandlers getAddBtnClickHandlers();
		String getEnterpriseName();
    	void setEnterpriseName(String name); 
    	String getAddress();
		void setAddress(String address);
        String  getLinkman();
        void  setLinkman(String man);
        String getTell();
		void setTell(String tell);
		String getEmail();
		void setEmail(String email);
		String getCallphone();
		void setCallphone(String callphone);


		TextBox getCid();
		
	
		
		void setAddBtnValue(String text);
		
		void setBreadCrumbs(Widget breadCrumbs);
		void setTitleText(String text);
	}
}
