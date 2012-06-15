package com.omdasoft.orderonline.gwt.order.client.ui;

import com.google.gwt.user.client.ui.Anchor;

public class MyAnchor extends Anchor {

	public MyAnchor(String name)
	{
		super.setHTML("<em><i><a style=\"color:bule;\" href=\"javascript:void(0);\">"+name+"<em></em></a></i></em>");
	}


}
